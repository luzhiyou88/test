package com.education.classroom.core.common.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.education.classroom.utils.Encodes;

/**
 * excel导出工具类
 * @Class Name ExcelExportUtil
 * @author 张永生
 * @Create In 2016年5月26日
 */
public class ExcelExportUtil {
	public static void export(List<?> entities, String template,
			OutputStream out) throws IOException, InvalidFormatException {
		ExcelExporter.export(entities, template, out);
	}

	public static void export(List<?> entities, String template,
			HttpServletResponse response, String fileName) throws IOException,
			InvalidFormatException {
		response.setContentType("application/msexcel");
		response.setHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode(fileName, "utf-8") + ";filename*=UTF-8''"
				+ Encodes.urlEncode(fileName));
		ExcelExporter.export(entities, template, response.getOutputStream());
	}

	public static void export(List<?> entities, Object headerInfo,
			String template, OutputStream out) throws IOException,
			InvalidFormatException {
		ExcelExporter.export(entities, headerInfo, template, out);
	}

	public static void export(List<?> entities, Object headerInfo,
			String template, HttpServletResponse response, String fileName)
			throws IOException, InvalidFormatException {
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ URLEncoder.encode(fileName, "UTF-8") + ";filename*=UTF-8''"
				+ Encodes.urlEncode(fileName));
		ExcelExporter.export(entities, headerInfo, template,
				response.getOutputStream());
	}
}
