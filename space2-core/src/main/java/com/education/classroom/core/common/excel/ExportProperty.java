package com.education.classroom.core.common.excel;

/**
 * 导出属性
 * @Class Name ExportProperty
 * @author 张永生
 * @Create In 2016年5月26日
 */
public class ExportProperty {

	private String propertyName;
	private int rowIndex;
	private int columnIndex;
	private int columnSpan;

	public ExportProperty(String propertyName, int rowIndex, int columnIndex) {
		this.propertyName = propertyName;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.columnSpan = 1;
	}

	public String getPropertyName() {
		return this.propertyName;
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public int getColumnIndex() {
		return this.columnIndex;
	}

	public int getColumnSpan() {
		return this.columnSpan;
	}

	public void setColumnSpan(int columnSpan) {
		this.columnSpan = columnSpan;
	}

	public int getLastColumnIndex() {
		return this.columnIndex + this.columnSpan - 1;
	}
}
