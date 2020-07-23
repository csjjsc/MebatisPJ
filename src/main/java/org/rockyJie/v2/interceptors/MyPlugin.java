package org.rockyJie.v2.interceptors;

import org.rockyJie.v2.annotation.Intercepts;
import org.rockyJie.v2.plugin.Interceptor;
import org.rockyJie.v2.plugin.Invocation;
import org.rockyJie.v2.plugin.Plugin;

/**
 * RockeyJie
 * 2020/7/23
 */
@Intercepts("update")
public class MyPlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.warp(target, this);
    }
}
