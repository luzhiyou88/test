package com.education.classroom.core.common.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import com.google.common.collect.Lists;

public final class ExcelExporter {
	
	private static final String HEADER_INFO_PREFIX = "header.";
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private List<ExportProperty> exportProperties;
	private List<ExportProperty> headerProperties;

	public static void export(List<?> entities, String template,
			OutputStream out) throws IOException, InvalidFormatException {
		new ExcelExporter().exportExcel(entities, template, out);
	}

	public static void export(List<?> entities, Object headerInfo,
			String template, OutputStream out) throws IOException,
			InvalidFormatException {
		new ExcelExporter().exportExcel(entities, headerInfo, template, out);
	}

	public void exportExcel(List<?> entities, String template, OutputStream out)
			throws IOException, InvalidFormatException {
		exportExcel(entities, null, template, out);
	}

	public void exportExcel(List<?> entities, Object headerInfo,
			String template, OutputStream out) throws IOException,
			InvalidFormatException {
		InputStream in = new FileInputStream(template);
		Workbook templateWorkbook = WorkbookFactory.create(in);
		resolveTemplate(templateWorkbook);
		if ((headerInfo != null) && (this.headerProperties.size() > 0)) {
			generateHeaderInfo(headerInfo, templateWorkbook.getSheetAt(0),
					this.headerProperties);
		}
		if (this.exportProperties.size() > 0) {
			generateData(entities, templateWorkbook.getSheetAt(0),
					this.exportProperties);
		}
		templateWorkbook.write(out);
		in.close();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<ExportProperty> resolveTemplate(Workbook templateWorkbook)
			throws IOException, InvalidFormatException {
		Sheet sheet = templateWorkbook.getSheetAt(0);
		List mergedRegions = extractMergedRegion(sheet);
		this.exportProperties = Lists.newArrayList();
		this.headerProperties = Lists.newArrayList();
		Row row;
		for (Iterator iter = sheet.iterator(); iter.hasNext();) {
			row = (Row) iter.next();
			for (Cell cell : row)
				if (cell.getCellType() == 1) {
					String content = cell.getStringCellValue();
					if ((content.startsWith("${")) && (content.endsWith("}"))) {
						String property = StringUtils.substringBetween(
								cell.getStringCellValue(), "${", "}");
						if (StringUtils.isNotBlank(property))
							if (property.startsWith(HEADER_INFO_PREFIX)) {
								ExportProperty exportProperty = new ExportProperty(
										property.substring(HEADER_INFO_PREFIX.length()),
										row.getRowNum(), cell.getColumnIndex());
								exportProperty.setColumnSpan(extractCellColSpan(mergedRegions, row, cell));
								this.headerProperties.add(exportProperty);
							} else {
								ExportProperty exportProperty = new ExportProperty(
										property, row.getRowNum(),
										cell.getColumnIndex());
								exportProperty.setColumnSpan(extractCellColSpan(
												mergedRegions, row, cell));
								this.exportProperties.add(exportProperty);
							}
					}
				}
		}
		
		return this.exportProperties;
	}

	private int extractCellColSpan(List<CellRangeAddress> mergedRegion,
			Row row, Cell cell) {
		for (CellRangeAddress region : mergedRegion) {
			if (region.isInRange(row.getRowNum(), cell.getColumnIndex())) {
				return region.getNumberOfCells();
			}
		}
		return 1;
	}

	/**
	 * 导出合并区域
	 * 2016年5月26日
	 * By 张永生
	 * @param sheet
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<CellRangeAddress> extractMergedRegion(Sheet sheet) {
		List mergedRegion = Lists.newArrayList();
		int mergedRegionNumber = sheet.getNumMergedRegions();
		for (int i = 0; i < mergedRegionNumber; i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			mergedRegion.add(region);
		}
		return mergedRegion;
	}

	/**
	 * 生成header
	 * 2016年5月26日
	 * By 张永生
	 * @param headerInfo
	 * @param sheet
	 * @param exportProperties
	 */
	private void generateHeaderInfo(Object headerInfo, Sheet sheet,
			List<ExportProperty> exportProperties) {
		for (ExportProperty property : exportProperties) {
			Cell cell = sheet.getRow(property.getRowIndex()).getCell(
					property.getColumnIndex());
			setCellValue(cell, headerInfo, property.getPropertyName());
		}
	}

	/**
	 * 生成数据
	 * 2016年5月26日
	 * By 张永生
	 * @param entities
	 * @param sheet
	 * @param exportProperties
	 */
	@SuppressWarnings("rawtypes")
	private void generateData(List<?> entities, Sheet sheet,
			List<ExportProperty> exportProperties) {
		int rowIndex = ((ExportProperty) exportProperties.get(0)).getRowIndex();
		for (Iterator iter = entities.iterator(); iter.hasNext();) {
			Object entity = iter.next();
			generateRow(entity, rowIndex, sheet, exportProperties);
			rowIndex++;
		}
	}

	/**
	 * 生成excel的行
	 * 2016年5月26日
	 * By 张永生
	 * @param entity
	 * @param rowIndex
	 * @param sheet
	 * @param exportProperties
	 */
	private void generateRow(Object entity, int rowIndex, Sheet sheet,
			List<ExportProperty> exportProperties) {
		Row row = sheet.createRow(rowIndex);
		for (ExportProperty exportProperty : exportProperties) {
			Cell cell = row.createCell(exportProperty.getColumnIndex());
			if (exportProperty.getColumnSpan() > 1) {
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex,
						exportProperty.getColumnIndex(), exportProperty
								.getLastColumnIndex()));
			}
			setCellValue(cell, entity, exportProperty.getPropertyName());
		}
	}

	/**
	 * 设置excel中cell的数值
	 * 2016年5月25日
	 * By 张永生
	 * @param cell
	 * @param entity
	 * @param property
	 */
	@SuppressWarnings("rawtypes")
	private void setCellValue(Cell cell, Object entity, String property) {
		try {
			Object valueObject = PropertyUtils.getProperty(entity, property);
			if (valueObject != null) {
				Class valueClass = valueObject.getClass();
				if (valueClass == String.class) {
					cell.setCellValue(valueObject.toString());
				} else if (valueClass == Date.class) {
					cell.setCellValue(dateFormat.format((Date) valueObject));
				} else if (valueClass == Integer.class) {
					cell.setCellType(0);
					cell.setCellValue(((Integer) valueObject).intValue());
				} else if (valueClass == Double.class) {
					cell.setCellType(0);
					cell.setCellValue(((Double) valueObject).doubleValue());
				} else if (valueClass == Boolean.class) {
					cell.setCellValue(((Boolean) valueObject).booleanValue());
				} else {
					cell.setCellValue(valueObject.toString());
				}
			} else {
				cell.setCellValue("");
			}
		} catch (InvocationTargetException e) {
			cell.setCellValue("Error");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			cell.setCellValue("Error");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			cell.setCellValue("Error");
			e.printStackTrace();
		}
	}
}