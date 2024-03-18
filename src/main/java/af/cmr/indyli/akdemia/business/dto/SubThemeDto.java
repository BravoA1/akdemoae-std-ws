package af.cmr.indyli.akdemia.business.dto;

import java.util.Date;

public class SubThemeDto implements IEntity {
	
	private Integer id;
	private String subThemeTitle;
	private String description;
	private Date creationDate;
	private Date updateDate;
	
	

	public String getSubThemeTitle() {
		return subThemeTitle;
	}

	public void setSubThemeTitle(String subThemeTitle) {
		this.subThemeTitle = subThemeTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Integer getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id=id;
		// TODO Auto-generated method stub

	}

	@Override
	public Date getCreationDate() {
		return creationDate;
	}

	@Override
	public void setCreationDate(Date creationDate) {
		this.creationDate=creationDate;

	}

	@Override
	public Date getUpdateDate() {
		// TODO Auto-generated method stub
		return updateDate;
	}

	@Override
	public void setUpdateDate(Date updateDate) {
		this.updateDate=updateDate;

	}

}
