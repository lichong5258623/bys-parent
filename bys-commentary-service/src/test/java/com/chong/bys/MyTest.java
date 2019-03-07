package com.chong.bys;


import com.chong.bys.comment.api.CommentArticleService;
import com.chong.bys.comment.api.dto.CommentArticleDto;
import com.chong.bys.commentary.dao.CommentaryUserDao;
import com.chong.bys.commentary.domain.CommentaryUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {


    @Autowired
    private CommentArticleService commentArticleService;

    @Autowired
    private CommentaryUserDao commentaryUserDao;

    @Test
    public void test(){
        CommentArticleDto commentArticleDto = new CommentArticleDto();
        commentArticleDto.setContent("第一个评论");
        commentArticleService.addCommentArticle(commentArticleDto);
        long count = commentArticleService.count();
        System.out.println(count);
    }

    @Test
    public void test2(){
        CommentaryUser commentaryUser = new CommentaryUser();
        commentaryUser.setContent("第一个用户评论");
        commentaryUser.setCreateTime(new Date());
        commentaryUserDao.saveAndFlush(commentaryUser);
        long count = commentaryUserDao.count();
    }

}
