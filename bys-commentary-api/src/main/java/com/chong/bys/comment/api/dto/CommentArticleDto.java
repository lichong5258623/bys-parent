package com.chong.bys.comment.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentArticleDto {

    private Long id;

    private String content;

    private Date createTime;

    private Boolean deleteFlag;
}
