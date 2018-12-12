package com.chong.bys.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lichong
 * @since 2018-12-11
 */

public class BysAArticle extends Model<BysAArticle> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubHeading() {
		return subHeading;
	}

	public void setSubHeading(String subHeading) {
		this.subHeading = subHeading;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getReadingVolume() {
		return readingVolume;
	}

	public void setReadingVolume(Integer readingVolume) {
		this.readingVolume = readingVolume;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
