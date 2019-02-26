package com.chong.bys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichong
 * @since 2018-12-11
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BysAArticle extends Model<BysAArticle> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 标题
     */
	private String mainTitle;
    /**
     * 次级标题
     */
	private String subHeading;
    /**
     * 内容
     */
	private String content;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 创建人
     */
	private String createUser;
    /**
     * 作者
     */
	private String author;
    /**
     * 阅读量
     */
	private Integer readingVolume;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
