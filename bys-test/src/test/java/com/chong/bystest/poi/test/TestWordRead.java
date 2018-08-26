/**
 * 
 */
package com.chong.bystest.poi.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author newli
 *
 */
public class TestWordRead {

	Logger logger = LoggerFactory.getLogger(getClass());

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
	public void test3(){
		
		java.util.logging.Logger logger2 = java.util.logging.Logger.getLogger("aaaa");
		logger2.warning("aaaa");
	}
	
	
	@Test
	public void test4(){
		logger.info("bbbb");
	}

}
