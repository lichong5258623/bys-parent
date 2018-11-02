package com.chong.bys.service.impl;

import com.chong.bys.domain.pojo.Article;
import com.chong.bys.dao.ArticleMapper;
import com.chong.bys.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lichong
 * @since 2018-10-26
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {
	
}
