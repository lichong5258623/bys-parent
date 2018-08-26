package com.chong.bystest.java.test;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JavaTest {

	/**
	 * 经测试map取值为为null时不会报异常
	 */
	@Test
	public void test1(){
		
		Map<String, User> map = new HashMap<>();
		
//		map.put("a", "sss");
		
		User user = new User();
		
		user.setId("1");
		user.setName("11");
		user.setAddress("111");
		
		map.put("111", user);
		
		map.put("222", user);
		
		map.get("111").setName("22");
		
		System.out.println(map.get("222").getName());
		
	}


	@Test
	public void test2(){

		String[] atp = {"Rafael Nadal", "Novak Djokovic",
				"Stanislas Wawrinka",
				"David Ferrer","Roger Federer",
				"Andy Murray","Tomas Berdych",
				"Juan Martin Del Potro"};
		List<String> players =  Arrays.asList(atp);


		for (String player : players) {
			if (player.equals("Tomas Berdych")) {
				log.info("Yes I'm Tomas Berdych");
			} else {
				log.info("Sorry I'm " + player);
			}
		}

	}



	/**
	 * Description: 向FTP服务器上传文件
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param path FTP服务器保存目录
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);//连接FTP服务器
			//如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			boolean login = ftp.login(username, password);//登录
			log.info(login+"");
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);

			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	@Test
	public void testftp() throws FileNotFoundException {

		File file = new File("D:/280832.jpg");

		FileInputStream in = new FileInputStream(file);

		boolean b = uploadFile("byu4212250001.my3w.com", 21, "byu4212250001", "lichong1203", "/htdocs/", "first.jpg", in);
		if(b){
			log.info("上传成功");
		}else {
			log.info("上传失败");
		}

	}

}
















