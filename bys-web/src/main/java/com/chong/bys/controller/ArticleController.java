package com.chong.bys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chong.bys.base.Result;
import com.chong.bys.domain.pojo.Article;
import com.chong.bys.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

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


    @GetMapping("/getArticleById")
    @ResponseBody
    public Result getArticleById(@Valid Article article, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("错误字段：[");

            for (FieldError fieldError : fieldErrors) {
                stringBuilder.append(" && ").append(fieldError.getField()).append(":").append(fieldError.getDefaultMessage());
            }
            stringBuilder.append("]");
            throw new RuntimeException(stringBuilder.toString().replaceFirst(" && ", ""));
        }

        Article byId = articleService.getById(article.getId());

        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        return Result.success(byId);
    }

}
