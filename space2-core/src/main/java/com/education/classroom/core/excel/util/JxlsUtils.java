package com.education.classroom.core.excel.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

import com.education.classroom.utils.Encodes;

public class JxlsUtils<E> {

	/**
	 * 工作薄对象
	 */
	private Workbook wb;

	/**
	 * 导出文件，流文件写入 2016年3月30日 By 王彬彬
	 * 
	 * @param templateFile
	 * @param beans
	 * @param os
	 */
	public void exportExcel(String templateFile, Map<String, Object> beans,
			OutputStream os) {
		XLSTransformer transformer = new XLSTransformer();
		ClassLoader classLoader = getClass().getClassLoader();

		InputStream is = classLoader.getResourceAsStream(templateFile);
		try {
			Workbook workbook = transformer.transformXLS(is, beans);
			workbook.write(os);

			Runtime.getRuntime().gc();
		} catch (Exception e) {
			throw new RuntimeException("导出excel错误!");
		}
	}

	/**
	 * 导出文件（下载） 2016年3月30日 By 王彬彬
	 * 
	 * @param templateFile
	 *            模版路径
	 * @param beans
	 *            导出对象，数据源
	 * @param outfileName
	 *            导出文件名
	 * @param fileType
	 *            导出文件扩展名
	 * @param response
	 *            响应
	 */
	public void exportExcel(String templateFile, Map<String, Object> beans,
			String outfileName, String fileType, HttpServletResponse response) {
		XLSTransformer transformer = new XLSTransformer();
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream(templateFile);
		OutputStream out = null;
		try {
			this.wb = transformer.transformXLS(is, beans);
			out = response.getOutputStream();

			// 将内容写入输出流并把缓存的内容全部发出去
			write(response, outfileName + fileType);

			out.flush();
			out.close();
		} catch (Exception e) {
			throw new RuntimeException("导出excel错误!");
		}

		Runtime.getRuntime().gc();
	}

	/**
	 * 导出文件（指定路径） 
	 * 2016年3月29日 
	 * By 王彬彬
	 * 
	 * @param templateFile
	 *            模版文件
	 * @param beans
	 *            导出对象，数据源
	 * @param out
	 */
	public void exportExcel(String templateFile, Map<String, Object> beans,
			String out) {
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(templateFile, beans, out);

			Runtime.getRuntime().gc();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导出excel错误!");
		}
	}

	/**
	 * 导出文件下载 2016年3月30日 By 王彬彬
	 * 
	 * @param response
	 *            文件头响应
	 * @param fileName
	 *            导出、下载文件名
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public JxlsUtils write(HttpServletResponse response, String fileName)
			throws IOException {
		response.reset();
		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Disposition",
				"attachment; filename=" + Encodes.urlEncode(fileName)
						+ ";filename*=UTF-8''" + Encodes.urlEncode(fileName));

		write(response.getOutputStream());
		return this;
	}

	/**
	 * 输出数据流
	 * 
	 * @param os
	 *            输出数据流
	 */
	@SuppressWarnings("rawtypes")
	public JxlsUtils write(OutputStream os) throws IOException {
		wb.write(os);
		return this;
	}
}
