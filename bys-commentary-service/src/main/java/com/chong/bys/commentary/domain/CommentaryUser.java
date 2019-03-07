package com.chong.bys.commentary.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lichong
 */

@Entity
@Table(name = "t_commentary_user")
@Data
public class CommentaryUser {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String content;
    /**
     *
     */
    @Column(nullable = false,columnDefinition = "datetime comment '我是年龄注释...'")
    private Date createTime;

    private Boolean deleteFlag;

}
