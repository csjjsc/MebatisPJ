package org.rockyJie.v2.executors;

/**
 * RockeyJie
 * 2020/7/16
 */
public interface Executor {

    <T> T query(String statement,Object[] params,Class pojo);
}
