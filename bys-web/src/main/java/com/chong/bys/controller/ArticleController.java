package com.chong.bys.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Validator;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lichong
 * @since 2018-10-26
 */
@Slf4j
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private Validator validator;

}