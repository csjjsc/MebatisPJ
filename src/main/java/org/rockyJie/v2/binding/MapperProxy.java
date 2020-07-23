package org.rockyJie.v2.binding;

import org.rockyJie.v2.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * RockeyJie
 * 2020/7/16
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;

    private Class pojo;

    public MapperProxy(SqlSession sqlSession, Class pojo) {
        this.sqlSession = sqlSession;
        this.pojo = pojo;
    }

    /***
     *
     * @param proxy     代理对象本身
     * @param method   代理对象执行的方法对象
     * @param args     方法对应的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MapperProxy invoke");
        String mapperInterfaceName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        String statementId = mapperInterfaceName + "." + methodName;
        if(sqlSession.getConfiguration().hasStatement(statementId)){
            return sqlSession.selectOne(statementId, args, pojo);
        }
        return method.invoke(proxy, args);
    }

}
