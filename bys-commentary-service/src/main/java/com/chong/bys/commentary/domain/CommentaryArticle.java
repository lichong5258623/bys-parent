package com.chong.bys.commentary.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lichong
 */

@Entity
@Table(name = "t_commentary_article")
@Data
public class CommentaryArticle {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private Date createTime;

    private Boolean deleteFlag;

}
