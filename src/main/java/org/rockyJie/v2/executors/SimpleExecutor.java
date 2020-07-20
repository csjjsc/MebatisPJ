package org.rockyJie.v2.executors;

import java.sql.Connection;

/**
 * RockeyJie
 * 2020/7/16
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> T query(String sql, Object[] params, Class pojo) {
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(sql, params, pojo);
    }


}
