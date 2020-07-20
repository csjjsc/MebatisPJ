package org.rockyJie.v2.binding;

import org.rockyJie.v2.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * RockeyJie
 * 2020/7/16
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    private Class object;

    public MapperProxyFactory(Class<T> mapperInterface, Class pojo) {
        this.mapperInterface = mapperInterface;
        this.object = pojo;
    }

    public T newInstance(SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{mapperInterface},
                new MapperProxy(sqlSession,object));
    }
}
