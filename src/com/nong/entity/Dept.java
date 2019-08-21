package com.nong.entity;


public class Dept implements java.io.Serializable{

	private Integer id;		// id
	private String name;	// 部门名称
	private String remark;	// 详细描述
	// 无参数构造器
	public Dept() {
		super();
	}
	// setter和getter方法

	public Integer getId() {
		return id;
	}

	public Dept setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Dept setName(String name) {
		this.name = name;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public Dept setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	@Override
	public String toString() {
		return "Dept [id=" + id + ", name=" + name + ", remark=" + remark + "]";
	}




} 