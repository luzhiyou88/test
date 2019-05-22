package com.education.classroom.utils;

import java.util.ArrayList;
import java.util.List;

public class FieldEntity {

	// field name
	private String fieldname;

	// field value
	private Object value;

	// field value's class type
	@SuppressWarnings("rawtypes")
	private Class clazz;

	private List<String> errorMsg = new ArrayList<String>();

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@SuppressWarnings("rawtypes")
	public Class getClazz() {
		return clazz;
	}

	@SuppressWarnings("rawtypes")
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public List<String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(List<String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	public FieldEntity() {
	}

	@SuppressWarnings("rawtypes")
	public FieldEntity(String fieldname, Object value, Class clazz) {
		this.fieldname = fieldname;
		this.value = value;
		this.clazz = clazz;
	}

	@SuppressWarnings("unused")
	private FieldEntity(String fieldname, List<String> errorMsg) {
		this.fieldname = fieldname;
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "FieldEntity [fieldname=" + fieldname + ", value=" + value
				+ ", clazz=" + clazz + ", errorMsg=" + errorMsg + "]";
	}

}