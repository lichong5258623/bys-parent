package com.chong.bys.commentary.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.chong.bys.comment.api.CommentArticleService;
import com.chong.bys.comment.api.dto.CommentArticleDto;
import com.chong.bys.commentary.dao.CommentaryArticleDao;
import com.chong.bys.commentary.domain.CommentaryArticle;
import com.chong.bys.core.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lichong
 */
@Service
@Component
public class CommentaryServiceImpl implements CommentArticleService {

    @Autowired
    private CommentaryArticleDao commentaryArticleDao;

    /**
     * 根据id获取评论数据
     *
     * @param id 主键
     * @return 评论数据
     */
    @Override
    public CommentArticleDto getCommentArticleByid(Long id) {

        CommentaryArticle one = commentaryArticleDao.getOne(id);
        return getCommentArticleDto(one);
    }

    /**
     * 新增文章评论
     *
     * @param commentArticleDto
     */
    @Override
    public void addCommentArticle(CommentArticleDto commentArticleDto) {
        CommentaryArticle commentaryArticle = convertCommentaryArticle(commentArticleDto);
        commentaryArticle.setCreateTime(new Date());
        commentaryArticle.setDeleteFlag(false);
        commentaryArticleDao.saveAndFlush(commentaryArticle);
    }

    /**
     * 统计数量
     */
    @Override
    public long count() {
        return commentaryArticleDao.count();
    }

    private CommentaryArticle convertCommentaryArticle(CommentArticleDto commentArticleDto){
        CommentaryArticle commentaryArticle = new CommentaryArticle();
        BeanUtil.convert(commentArticleDto,commentaryArticle);
        return commentaryArticle;
    }

    private CommentArticleDto getCommentArticleDto(CommentaryArticle one) {

        CommentArticleDto commentArticleDto = new CommentArticleDto();
        BeanUtil.convert(one,commentArticleDto);
        return commentArticleDto;
    }
}
