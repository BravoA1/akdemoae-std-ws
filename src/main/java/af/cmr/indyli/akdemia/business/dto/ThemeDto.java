package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;


public class ThemeDto implements IEntity {

	private Integer id;

    private String themeTitle;
    private String description;
    private Date creationDate;
    private Date updateDate;
    
	@Override
	public Integer getId() {
		return this.id;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getThemeTitle() {
		return themeTitle;
	}
	
	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public Date getCreationDate() {
		return this.creationDate;
	}
	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
}
