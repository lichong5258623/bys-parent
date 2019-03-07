package com.chong.bys.comment.api;

import com.chong.bys.comment.api.dto.CommentArticleDto;

/**
 * 文章评论服务接口
 * @author lichong
 * @date 2019/03/07 22:14
 */
public interface CommentArticleService {

    /**
     * 根据id获取评论数据
     * @param id 主键
     * @return 评论数据
     */
    CommentArticleDto getCommentArticleByid(Long id);

    /**
     * 新增文章评论
     * @param commentArticleDto
     */
    void addCommentArticle(CommentArticleDto commentArticleDto);

    /**
     * 统计数量
     */
    long count();
}
