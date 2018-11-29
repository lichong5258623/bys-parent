package com.chong.bys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chong.bys.base.Result;
import com.chong.bys.domain.pojo.Article;
import com.chong.bys.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
    private IArticleService articleService;

    @Autowired
    private Validator validator;


    @GetMapping("/getArticleById")
    @ResponseBody
    public Result getArticleById(@Valid Article article, BindingResult bindingResult) {

        //校验参数
        validatorParam(bindingResult);

        Article byId = articleService.getById(article.getId());

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        return Result.success(byId);
    }

}
