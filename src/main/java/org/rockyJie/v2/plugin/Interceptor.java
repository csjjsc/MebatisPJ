package org.rockyJie.v2.plugin;

/**
 * RockeyJie
 * 2020/7/21
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    Object plugin(Object target);

}
