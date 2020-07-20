package org.rockyJie.v2.session;

import org.rockyJie.v2.executors.Executor;

/**
 * RockeyJie
 * 2020/7/16
 */
public class DefaultSqlSession implements SqlSession{

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
        this.executor = configuration.newExcutor();
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public <T> T selectOne(String statement, Object[] params, Class clazz) {
        String sql = getConfiguration().getMappedStatement(statement);
        return executor.query(sql, params, clazz);
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz, this);
    }
}
