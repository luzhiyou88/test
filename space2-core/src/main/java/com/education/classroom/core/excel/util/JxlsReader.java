package com.education.classroom.core.excel.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.xml.sax.SAXException;

public class JxlsReader {

	/**
	 * 导入文件 2016年3月30日 By 王彬彬
	 * 
	 * @param xmlConfig
	 *            导入用模板xml
	 * @param file
	 *            导入文件
	 * @throws IOException
	 * @throws SAXException
	 * @throws InvalidFormatException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void readExcel(String xmlConfig, File file) throws IOException,
			SAXException, InvalidFormatException {
		try {
			InputStream inputXML = new BufferedInputStream(
					JxlsReader.class.getResourceAsStream(xmlConfig));
			XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML);
			InputStream inputXLS = new FileInputStream(file);

			List<?> listObjet = new ArrayList();
			Map beans = new HashMap();
			beans.put("list", listObjet);
			mainReader.read(inputXLS, beans);
			inputXLS.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
