package com.dorm.bean;

import java.util.List;

public class User {
	
	private Integer id;
	private String name;
	private String passWord;
	private String stuCode;
	private String sex;
	private String tel;
	private Integer dormBuildId;
	private String dormCode;
	private Integer roleId;
	private Integer disabled;
	private Integer createUserId;

	private DormBuild dormBuild;
	private List<DormBuild>  dormBuilds;//宿舍管理员管理的宿舍楼
	
	
	public User() {
		super();
	}
	
	public User(String name, String passWord, String sex, String tel, Integer dormBuildId, Integer roleId) {
		super();
		this.name = name;
		this.passWord = passWord;
		this.sex = sex;
		this.tel = tel;
		this.dormBuildId = dormBuildId;
		this.roleId = roleId;
	}
	
	public User( Integer id, String name, String passWord, String sex, String tel, Integer dormBuildId, Integer roleId) {
		super();
		this.id = id;
		this.name = name;
		this.passWord = passWord;
		this.sex = sex;
		this.tel = tel;
		this.dormBuildId = dormBuildId;
		this.roleId = roleId;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getStuCode() {
		return stuCode;
	}

	public void setStuCode(String stuCode) {
		this.stuCode = stuCode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getDormBuildId() {
		return dormBuildId;
	}

	public void setDormBuildId(Integer dormBuildId) {
		this.dormBuildId = dormBuildId;
	}

	public String getDormCode() {
		return dormCode;
	}

	public void setDormCode(String dormCode) {
		this.dormCode = dormCode;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	
	
	public List<DormBuild> getDormBuilds() {
		return dormBuilds;
	}

	public void setDormBuilds(List<DormBuild> dormBuilds) {
		this.dormBuilds = dormBuilds;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", passWord=" + passWord + ", stuCode=" + stuCode + ", sex=" + sex
				+ ", tel=" + tel + ", dormBuildId=" + dormBuildId + ", dormCode=" + dormCode + ", roleId=" + roleId
				+ ", disabled=" + disabled + ", createUserId=" + createUserId + ", dormBuild=" + dormBuild
				+ ", dormBuilds=" + dormBuilds + ", getId()=" + getId() + ", getName()=" + getName()
				+ ", getPassWord()=" + getPassWord() + ", getStuCode()=" + getStuCode() + ", getSex()=" + getSex()
				+ ", getTel()=" + getTel() + ", getDormBuildId()=" + getDormBuildId() + ", getDormCode()="
				+ getDormCode() + ", getRoleId()=" + getRoleId() + ", getDisabled()=" + getDisabled()
				+ ", getDormBuilds()=" + getDormBuilds() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getDormBuild()=" + getDormBuild() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public DormBuild getDormBuild() {
		return dormBuild;
	}

	public void setDormBuild(DormBuild dormBuild) {
		this.dormBuild = dormBuild;
	}

	
}
