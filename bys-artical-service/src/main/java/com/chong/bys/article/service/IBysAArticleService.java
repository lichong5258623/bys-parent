package com.chong.bys.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chong.bys.article.domain.BysAArticle;

import java.io.Serializable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lichong
 * @since 2018-12-11
 */
public interface IBysAArticleService extends IService<BysAArticle> {

    BysAArticle getArticalById(Serializable id);
}
