package com.chong.bys.commentary.dao;

import com.chong.bys.commentary.domain.CommentaryArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author lichong
 */

public interface CommentaryArticleDao extends JpaRepository<CommentaryArticle,Long> , JpaSpecificationExecutor<CommentaryArticle>, Serializable {




}
