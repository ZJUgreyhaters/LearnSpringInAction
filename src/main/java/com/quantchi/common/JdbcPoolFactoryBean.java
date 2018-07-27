package com.quantchi.common;

import com.quantchi.tianshu.common.JdbcPool;
import com.quantchi.tianshu.common.JdbcPool.JdbcPoolBuilder;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author hetong.
 */
public class JdbcPoolFactoryBean implements FactoryBean<JdbcPool> {

    private String jdbcDriver;
    private String url;
    private String username;
    private String password;

    @Override
    public JdbcPool getObject() throws Exception {
        return new JdbcPoolBuilder(url, username, password).setDriverClassName(jdbcDriver).build();
    }

    @Override
    public Class<?> getObjectType() {
        return JdbcPool.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}