package com.chong.bys.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chong.bys.article.dao.BysAArticleMapper;
import com.chong.bys.article.domain.BysAArticle;
import com.chong.bys.article.service.IBysAArticleService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichong
 * @since 2018-12-11
 */
@Service
public class BysAArticleServiceImpl extends ServiceImpl<BysAArticleMapper, BysAArticle> implements IBysAArticleService {

    @Override
    public BysAArticle getArticalById(Serializable id) {
        return baseMapper.getByArticleId(id);
    }
}
