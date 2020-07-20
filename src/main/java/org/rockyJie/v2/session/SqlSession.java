package org.rockyJie.v2.session;

/**
 * RockeyJie
 * 2020/7/16
 */
public interface SqlSession {

    <T> T selectOne(String statement, Object[] params, Class clazz);

    Configuration getConfiguration();

    <T> T getMapper(Class<T> clazz);
}
