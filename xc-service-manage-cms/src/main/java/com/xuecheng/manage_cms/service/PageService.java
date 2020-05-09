package com.xuecheng.manage_cms.service;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.BaseException;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-12 18:32
 **/
@Service
public class PageService {

    @Autowired
    CmsPageRepository cmsPageRepository;


    /**
     * 页面查询方法
     *
     * @param page             页码，从1开始记数
     * @param size             每页记录数
     * @param queryPageRequest 查询条件
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {

        //分页参数
        if (page <= 0) {
            throw new BaseException(CmsCode.CMS_COURSE_PERVIEWISNULL);
            //page = 1;
        }
        page = page - 1;
        if (size <= 0) {
            size = 10;
        }
        CmsPage cmsPage = new CmsPage();
        String pageAliase = queryPageRequest.getPageAliase();
        String siteId = queryPageRequest.getSiteId();
        String pageName = queryPageRequest.getPageName();
        Example<CmsPage> ex;
        if (StringUtils.isNotBlank(pageAliase)) {
            cmsPage.setPageAliase(pageAliase);
            //页面名称模糊查询，需要使用查询器
            ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher(pageAliase, ExampleMatcher.GenericPropertyMatchers.contains());
            //创建条件实例
            ex = Example.of(cmsPage, exampleMatcher);
        } else {
            ex = Example.of(cmsPage);
        }
        if (StringUtils.isNotBlank(siteId)) {
            cmsPage.setSiteId(siteId);
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(ex, pageable);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent());//数据列表
        queryResult.setTotal(all.getTotalElements());//数据总记录数
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult addPage(CmsPage cmsPage) {
        String pageName = cmsPage.getPageName();
        String siteId = cmsPage.getSiteId();
        String pageWebPath = cmsPage.getPageWebPath();
        //查询mongodb中是否存在
        CmsPage byPageNameAndSiteIdAndPageWebPath = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(pageName, siteId, pageWebPath);
        if (byPageNameAndSiteIdAndPageWebPath == null) {
            cmsPage.setPageId(null);
            cmsPageRepository.save(cmsPage);
            System.out.println(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }

        return new CmsPageResult(CommonCode.FAIL, null);
    }
}
