/*
 * COPYRIGHT (c) Enliple 2019
 * This software is the proprietary of Enliple
 *
 * @author <a href=“mailto:jhna@enliple.com“>jhna</a>
 * @since 2020-01-13
 */
package com.mobon.kafka.core;



import com.mobon.kafka.consumer.model.CookieVo;
import com.mobon.kafka.core.service.CategoryToMariaDB;
import com.mobon.kafka.core.service.dao.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;



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

@Configuration
@Service
public class ConsumerProcess {

    CategoryToMariaDB categoryToMariaDB = new CategoryToMariaDB();
    //key 값으로 분기..어떤 방식이 좋을까? 우선 if or
    public void processMain(CookieVo cookieVo){
        System.out.println("11111111111111111");

        if("CAT".equals(cookieVo.getName())){
            categoryToMariaDB.categoryMain(cookieVo);
        }

    }
}