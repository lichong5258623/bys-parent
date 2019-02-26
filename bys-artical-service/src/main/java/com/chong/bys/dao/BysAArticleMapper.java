package com.chong.bys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chong.bys.domain.BysAArticle;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author lichong
 * @since 2018-12-11
 */
public interface BysAArticleMapper extends BaseMapper<BysAArticle> {
    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     */
    @Override
    int insert(BysAArticle entity);
}