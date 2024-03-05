package af.cmr.indyli.akdemia.business.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import af.cmr.indyli.akdemia.business.dao.IEntityDAO;
import af.cmr.indyli.akdemia.business.dto.IEntity;

public abstract class AbstractEntityDAOImpl<E> implements IEntityDAO<E> {
	
	protected List<E> entityInMemory;
	
	public AbstractEntityDAOImpl() {
		this.entityInMemory = new ArrayList<>();
		this.initData();
	}
	
	public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public List<E> findAll() {
		return entityInMemory;
	}

	@Override
	public E findById(Integer id) {
		for (E entity : entityInMemory) {
            if (((IEntity) entity).getId().equals(id)) {
                return entity;
            }
        }
        return null;
	}

	@Override
	public void deleteById(Integer id) {
		
		Iterator<E> iterator = entityInMemory.iterator(); // Cette ligne crée un itérateur pour la liste entityInMemory.
		
		while (iterator.hasNext()) { // Cette boucle while va parcourir chaque élément de la liste tant qu'il y a un élément suivant.
	        
			E entity = iterator.next(); // À chaque itération de la boucle, iterator.next() renvoie l'élément suivant dans la liste, qui est assigné à la variable entity.
	        
			try { // Nous entrons dans un bloc try-catch pour gérer les exceptions qui pourraient être levées lors de l'utilisation de la réflexion pour obtenir l'ID de l'entité.
	            
	        	Method getIdMethod = entity.getClass().getMethod("getId"); // Cette ligne obtient la méthode getId() de l'entité courante en utilisant la réflexion.
	        	
	            Object entityId = getIdMethod.invoke(entity); // En utilisant cette méthode, nous invoquons dynamiquement la méthode getId() sur l'entité actuelle pour obtenir son ID.
	            
	            if (entityId != null && entityId.equals(id)) { // Si l'ID de l'entité correspond à l'ID que nous cherchons à supprimer, alors nous supprimons cette entité de la liste en utilisant iterator.remove().
	                
	            	iterator.remove(); // Supprime l'entité correspondante
	                
	            	return; // Sort de la méthode après la suppression
	            }
	        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	            throw new IllegalArgumentException("Erreur lors de la suppression de l'entité : " + e.getMessage()); // Si l'entité avec l'ID spécifié n'est pas trouvée dans la liste, nous lançons une exception pour le signaler
	        }
	    }
		throw new IllegalArgumentException("Aucune entité avec l'ID spécifié n'a été trouvée : " + id);
	}

	@Override
	public E create(E ent) {
		try {
			Class<?> clazz = ent.getClass();
			
			// Obtenez la méthode getId() de l'entité
			Method getIdMethod = clazz.getMethod("getId");
			
			// Obtenez la valeur de l'ID actuel
	        Object idObject = getIdMethod.invoke(ent);
	        Integer id = (Integer) idObject;
	        
		    if (id == null || !entityInMemory.contains(ent)) {
	            Integer newId = generateNewId();
	            
	            Method setIdMethod = clazz.getMethod("setId", Integer.class);
	            setIdMethod.invoke(ent, newId);
	            
	            entityInMemory.add(ent);
	            
		     }else {
	        	for (E entity : entityInMemory) {
	        		Object entityId = getIdMethod.invoke(entity);
	                if (entityId != null && entityId.equals(id)) {
	                    
	                	// Mettez à jour les attributs de l'entité existante avec les valeurs de l'entité mise à jour
	                    for (Method method : clazz.getMethods()) {
	                        if (method.getName().startsWith("get")) {
	                            String attributeName = method.getName().substring(3); // Remove "get"
	                            if (shouldUpdateAttribute(entity, ent, attributeName)) {
	                                Object updatedValue = method.invoke(ent); // Get the updated value
	                                Method setterMethod = clazz.getMethod("set" + attributeName, method.getReturnType());
	                                setterMethod.invoke(entity, updatedValue); // Mise à jour des attributs
	                            }
	                        }
	                    }
	                    
	                    break; // Sortez de la boucle une fois que l'entité est mise à jour
	                }
	        	}
		    }
	     }
		 catch (Exception e) {
			// TODO: handle exception
		}
		
		return ent;
	}
	
	private Integer generateNewId() {
	    Integer maxId = 0;
	    for (E entity : this.entityInMemory) {
	        if (((IEntity) entity).getId() != null && ((IEntity) entity).getId() > maxId) {
	            maxId = ((IEntity) entity).getId();
	        }
	    }
	    return maxId + 1;
	}
	
	private boolean shouldUpdateAttribute(E entity, E updatedEntity, String attributeName) {
	    try {
	        // Récupérer la classe de l'entité
	        Class<?> clazz = entity.getClass();
	        
	        // Récupérer la méthode getter correspondante à l'attribut
	        Method getterMethod = clazz.getMethod("get" + capitalize(attributeName));
	        
	        // Récupérer la valeur actuelle de l'attribut pour l'entité existante
	        Object currentValue = getterMethod.invoke(entity);
	        
	        // Récupérer la nouvelle valeur de l'attribut pour l'entité mise à jour
	        Object updatedValue = getterMethod.invoke(updatedEntity);
	        
	        // Comparer les valeurs
	        return !Objects.equals(currentValue, updatedValue);
	        
	    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	        throw new IllegalArgumentException("Erreur lors de la vérification de la mise à jour de l'attribut : " + e.getMessage());
	    }
	}
	
	private String capitalize(String str) {
	    if (str == null || str.isEmpty()) {
	        return str;
	    }
	    return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}
	
	public abstract void initData() ;

}
