package com.cognis.app.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement
@JsonPropertyOrder({"roleId","roleName","remark"})
public class RoleModel extends BaseModel{

	private Integer roleId;
	private String roleName;
	private String remark;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	
	@Override
	public String toString() {
		return "userRoleModel [ roleName=" + roleName + ", remark=" + remark + "]";
	}
	
}
