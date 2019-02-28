package com.chong.bys.service;

import com.chong.bys.domain.BysAArticle;
import com.baomidou.mybatisplus.extension.service.IService;

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
