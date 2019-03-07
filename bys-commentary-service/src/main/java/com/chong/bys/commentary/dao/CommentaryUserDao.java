package com.chong.bys.commentary.dao;

import com.chong.bys.commentary.domain.CommentaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

/**
 * @author lichong
 */

public interface CommentaryUserDao extends JpaRepository<CommentaryUser,Long> , JpaSpecificationExecutor<CommentaryUser>, Serializable {




}
