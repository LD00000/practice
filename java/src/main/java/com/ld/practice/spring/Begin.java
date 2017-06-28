package com.ld.practice.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Begin {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext context = null;
        context = new ClassPathXmlApplicationContext(new String[]{"spring-context.xml"});
        context.getBean("firstBean");
    }

}
