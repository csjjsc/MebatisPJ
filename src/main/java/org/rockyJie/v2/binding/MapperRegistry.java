package org.rockyJie.v2.binding;

import org.rockyJie.v2.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * RockeyJie
 * 2020/7/16
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>();


    public void addMapper(Class mapperClass, Class pojo){
        knownMappers.put(mapperClass, new MapperProxyFactory(mapperClass, pojo));
    }

    public <T> T getMapper(SqlSession sqlSession, Class<T> clazz){
        MapperProxyFactory proxyFactory = knownMappers.get(clazz);
        if(null == proxyFactory){
            throw new RuntimeException("Type " + clazz +" can not find!");
        }
        return (T) proxyFactory.newInstance(sqlSession);
    }
}
