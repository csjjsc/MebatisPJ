package org.rockyJie.v2.executors;

import org.rockyJie.v2.handlers.ParameterHandler;
import org.rockyJie.v2.session.Configuration;

import java.sql.*;

/**
 * RockeyJie
 * 2020/7/20
 */
public class StatementHandler {

    private ResultSetHandler resultSetHandler = new ResultSetHandler();

    /**
     *
     * @param sql    语句
     * @param params    参数
     * @param pojo   对象数据类型
     * @param <T>
     * @return
     */
    public <T> T query(String sql, Object[] params, Class pojo){
        Connection conn = null;
        PreparedStatement ps = null;
        Object result = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ParameterHandler parameterHandler = new ParameterHandler(ps);
            parameterHandler.setParams(params);
            ResultSet resultSet = ps.executeQuery();
            result = resultSetHandler.handle(resultSet, pojo);
            return (T) result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    private Connection getConnection(){
        String driver = Configuration.getJDBCDriver();
        String url = Configuration.getDBURL();
        String user = Configuration.getDBUser();
        String pwd = Configuration.getDBPassWord();
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}
