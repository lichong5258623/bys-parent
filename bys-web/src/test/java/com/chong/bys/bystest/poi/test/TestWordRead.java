/**
 * 
 */
package com.chong.bys.bystest.poi.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author newli
 *
 */
@Slf4j
public class TestWordRead {

	@Test
	public void test() throws Exception {
//		InputStream fis = new FileInputStream("E:\\小程序接口.docx");
//		XWPFDocument xdoc = new XWPFDocument(fis);
//		XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
//		String doc1 = extractor.getText();
//		POIXMLDocument document = extractor.getDocument();
//		document.logger.info(doc1);
		// System.out.println(doc1);
//		fis.close();
	}

	@Test
	public void word2007ToHtml() throws Exception {
//		String filepath = "e:/files/";
//		String sourceFileName = filepath + "小程序接口.docx";
//		String targetFileName = filepath + "1496717486420.html";
//		String imagePathStr = filepath + "/image/";
//		OutputStreamWriter outputStreamWriter = null;
//		try {
//			XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName));
//			XHTMLOptions options = XHTMLOptions.create();
//			// 存放图片的文件夹
//			options.setExtractor(new FileImageExtractor(new File(imagePathStr)));
//			// html中图片的路径
//			options.URIResolver(new BasicURIResolver("image"));
//			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8");
//			XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
//			xhtmlConverter.convert(document, outputStreamWriter, options);
//		} finally {
//			if (outputStreamWriter != null) {
//				outputStreamWriter.close();
//			}
//		}
	}


	@Test
	public void createExcelTest() throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		hssfWorkbook.createSheet("偏远地区");
		hssfWorkbook.createSheet("一般偏远地区");
		hssfWorkbook.createSheet("超偏远地区");
		File file = new File("test01.xls");

		OutputStream fileOutputStream = new FileOutputStream(file);
		hssfWorkbook.write(fileOutputStream);
	}
	@Test
	public void readExcelTest() throws Exception {
		Workbook workbook = WorkbookFactory.create(new File("test01.xls"));
	}

}
