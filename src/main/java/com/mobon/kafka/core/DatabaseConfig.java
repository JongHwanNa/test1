/*
 * COPYRIGHT (c) Enliple 2019
 * This software is the proprietary of Enliple
 *
 * @author <a href=“mailto:jhna@enliple.com“>jhna</a>
 * @since 2020-01-14
 */
package com.mobon.kafka.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * create on 2020-01-14.
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author jhna
 * @version 1.0
 * @see
 * @since 지원하는 자바버전 (ex : 5+ 5이상)
 */
@Configuration
@MapperScan(basePackages = "com.mobon.kafka.core.service.dao")
public class DatabaseConfig {

}