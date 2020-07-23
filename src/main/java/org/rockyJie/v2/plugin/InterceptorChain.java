package org.rockyJie.v2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * RockeyJie
 * 2020/7/21
 */
public class InterceptorChain {

    private List<Interceptor> interceptors = new ArrayList<>();

    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }

    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            interceptor.plugin(target);
        }
        return target;
    }

    public boolean hasPlugin() {
        if (interceptors.size() > 0) {
            return true;
        }
        return false;
    }
}
