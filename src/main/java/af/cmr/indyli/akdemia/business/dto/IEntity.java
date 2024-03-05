package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;

public interface IEntity {
	
	public Integer getId();
    public void setId(Integer id);
    public Date getCreationDate();
    public void setCreationDate(Date creationDate);
    public Date getUpdateDate();
    public void setUpdateDate(Date updateDate);
}
