package org.rockyJie.v2.plugin;

import java.lang.reflect.Method;

/**
 * RockeyJie
 * 2020/7/21
 */
public class Invocation {

    private Method method;
    private Object target;
    private Object[] args;

    public Invocation(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
