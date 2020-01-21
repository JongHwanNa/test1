/*
 * COPYRIGHT (c) Enliple 2019
 * This software is the proprietary of Enliple
 *
 * @author <a href=“mailto:jhna@enliple.com“>jhna</a>
 * @since 2020-01-13
 */
package com.mobon.kafka.core.service.dao;

import com.mobon.kafka.core.model.CategoryVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * create on 2020-01-13.
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author jhna
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Repository
public class CategoryDao {

    @Resource(name = "sqlSession")
    private SqlSessionTemplate sqlSessionTemplate;

    public int insertCategory(List<CategoryVo> categoryVoList) {
        return this.sqlSessionTemplate.insert("insertCategory", categoryVoList);
    }


}