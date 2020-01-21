/*
 * COPYRIGHT (c) Enliple 2019
 * This software is the proprietary of Enliple
 *
 * @author <a href=“mailto:jhna@enliple.com“>jhna</a>
 * @since 2020-01-13
 */
package com.mobon.kafka.core.service;


import com.mobon.kafka.consumer.model.CookieVo;
import com.mobon.kafka.core.model.CategoryVo;
import com.mobon.kafka.core.service.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;

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
public class CategoryToMariaDB {

    @Autowired
    private CategoryDao categoryDao;
    //data를 Vo에 저장
    public void categoryMain (CookieVo cookieVo) {
        System.out.println("Consumed JSON Message: " + cookieVo.getName());
        System.out.println("Consumed JSON Message: " + cookieVo.getValue());
        System.out.println("Consumed JSON Message: " + cookieVo.getExpire());

        String valueData = cookieVo.getValue();

        //valueData 가공 후 LIST로 dao로 전달
        List<CategoryVo> categoryVoList = null;

        categoryDao.insertCategory(categoryVoList);

    }


}