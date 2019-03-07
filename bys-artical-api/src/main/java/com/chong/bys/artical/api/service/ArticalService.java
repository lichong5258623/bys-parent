package com.chong.bys.artical.api.service;

import com.chong.bys.artical.api.dto.ArticalDto;

import java.io.Serializable;
import java.util.List;

public interface ArticalService {

    boolean createArtical(ArticalDto articalDto);

    void delteArticalById(Serializable id);

    ArticalDto selectArticalById(Serializable id);

    void updateArticalById(ArticalDto articalDto);

    List<ArticalDto> selectArticalList();
}
