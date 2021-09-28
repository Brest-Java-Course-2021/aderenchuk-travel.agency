package com.aderenchuk.brest.dao.jdbc;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

@Component
public class InjectSqlBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean.getClass().isAnnotationPresent(Repository.class)) {
            Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectSql.class)) {
                    InjectSql injectSql = field.getAnnotation(InjectSql.class);
                    String path = injectSql.value();
                    field.setAccessible(true);
                    try (InputStream classPathResource = new ClassPathResource(path).getInputStream();
                         InputStreamReader isr = new InputStreamReader(classPathResource);
                         BufferedReader br = new BufferedReader(isr)) {
                        String line = br.readLine();
                        String sql = null;
                        while (line != null) {
                            System.out.println(line);
                            sql = line;
                            line = br.readLine();
                        }
                        ReflectionUtils.setField(field, bean, sql);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

}
