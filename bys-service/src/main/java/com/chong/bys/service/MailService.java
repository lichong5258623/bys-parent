/**
 * 
 */
package com.chong.bys.service;

/**
 * @author lichong
 *
 */
public interface MailService {
	
	public void sendSimpleMail(String emailAddress, String title, String content);

    public void sendHtmlMail(String emailAddress, String title, String content);

    public void sendAttachmentsMail(String emailAddress, String title, String content, String filePath);

    public void sendInlineResourceMail(String emailAddress, String title, String content, String imagesPath, String rscPath);

}
