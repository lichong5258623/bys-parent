package com.chong.bys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chong.bys.domain.BysAArticle;

import java.io.Serializable;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lichong
 * @since 2018-12-11
 */
public interface BysAArticleMapper extends BaseMapper<BysAArticle> {

     BysAArticle getByArticleId(Serializable id);
}