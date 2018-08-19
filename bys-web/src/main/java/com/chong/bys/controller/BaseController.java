package com.chong.bys.controller;

import com.chong.bys.util.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


@Slf4j
public class BaseController {


    /**
     * redirect跳转
     * @param url 目标url
     */
    protected String redirect(String url) {

        return "redirect:" + url;
    }

    /**
     * 下载文件
     * @param file 文件
     */
    protected ResponseEntity<Resource> download(File file) {
        String fileName = file.getName();
        return download(file, fileName);
    }

    /**
     * 下载
     * @param file 文件
     * @param fileName 生成的文件名
     * @return {ResponseEntity}
     */
    protected ResponseEntity<Resource> download(File file, String fileName) {
        Resource resource = new FileSystemResource(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent");
        // 避免空指针
        header = header == null ? "" : header.toUpperCase();
        HttpStatus status;
        if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
            fileName = UriUtils.encode(fileName, Charsets.UTF_8);
            status = HttpStatus.OK;
        } else {
            fileName = new String(fileName.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
            status = HttpStatus.CREATED;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity<Resource>(resource, headers, status);
    }
}
