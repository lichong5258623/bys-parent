package com.chong.bys.articalservice;

import com.alibaba.dubbo.config.annotation.Service;
import com.chong.bys.artical.dto.ArticalDto;
import com.chong.bys.artical.service.ArticalService;
import com.chong.bys.domain.BysAArticle;
import com.chong.bys.service.IBysAArticleService;
import com.chong.bys.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Service
@Component
public class ArticalServiceProvider implements ArticalService {

    @Autowired
    IBysAArticleService bysAArticleService;

    @Override
    public boolean createArtical(ArticalDto articalDto) {

        BysAArticle bysAArticle = new BysAArticle();
        BeanUtil.convert(articalDto, bysAArticle);
        return bysAArticleService.save(bysAArticle);
    }

    @Override
    public void delteArticalById(Serializable id) {
        bysAArticleService.removeById(id);
    }

    @Override
    public ArticalDto selectArticalById(Serializable id) {
        BysAArticle byId = bysAArticleService.getArticalById(id);
        ArticalDto articalDto = new ArticalDto();
        if (byId == null) {
            return articalDto;
        }
        BeanUtils.copyProperties(byId, articalDto);
        return articalDto;
    }

    @Override
    public void updateArticalById(ArticalDto articalDto) {
        BysAArticle bysAArticle = new BysAArticle();
        BeanUtil.convert(articalDto, bysAArticle);
        bysAArticleService.updateById(bysAArticle);
    }

    @Override
    public List<ArticalDto> selectArticalList() {
        return null;
    }
}
