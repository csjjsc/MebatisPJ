package org.rockyJie.v2.session;

import org.rockyJie.v2.builder.ConfigBuilder;

/**
 * RockeyJie
 * 2020/7/16
 */
public class SqlSessionFactory {

    private Configuration configuration;

    public SqlSessionFactory bulid(){
        configuration = ConfigBuilder.getConfiguration();
        return this;
    }

    public SqlSession openSession(){
        return new DefaultSqlSession(configuration);
    }
}
