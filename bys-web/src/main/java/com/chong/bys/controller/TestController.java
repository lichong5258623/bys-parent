package com.chong.bys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chong.bys.artical.api.dto.ArticalDto;
import com.chong.bys.artical.api.service.ArticalService;
import com.chong.bys.core.base.Result;
import com.chong.bys.service.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author lichong
 * @version 1
 * @date 2018/8/15 21:30
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Reference
    ArticalService articalService;

    @Autowired
    MyUserDetailsService userDetailsService;

    @GetMapping("/getArticalById")
    public ArticalDto getArticalById(Long id) {

        return articalService.selectArticalById(id);
    }

    @RequestMapping("testUsermodel")
    public UserDetails testUsermodel(){
        UserDetails licihong01 = userDetailsService.loadUserByUsername("lichong01");
        return licihong01;
    }
    @PostMapping("/removeById")
    public Result<Object> removeById(Long id) {
        articalService.delteArticalById(id);
        return Result.success();
    }

    @GetMapping("/testException")
    public Result<Object> testException(String type){
        if(type.equals("error")){
            int s = 20/0;
        }
        return Result.success();
    }

    @PostMapping("/cerateArtical")
    public Result<Object> cerateArtical(){
        ArticalDto articalDto = new ArticalDto();
        articalDto.setAuthor("李崇");
        articalDto.setContent("这是一篇文章");
        articalDto.setCreateTime(new Date());
        articalDto.setCreateUser("lc");
        articalDto.setMainTitle("标题");
        articalDto.setSubHeading("二级标题");
        articalDto.setReadingVolume(0);
        boolean artical = articalService.createArtical(articalDto);
        log.info(artical+"");
        return Result.success();
    }
    @GetMapping("/getSessionId")
    @ResponseBody
    public String getLoginSessionId(HttpServletRequest request) {

        return request.getSession().getId();
    }
}
