package com.chong.bys.service.impl;

import com.chong.bys.dao.BysAArticleMapper;
import com.chong.bys.domain.BysAArticle;
import com.chong.bys.service.IBysAArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
