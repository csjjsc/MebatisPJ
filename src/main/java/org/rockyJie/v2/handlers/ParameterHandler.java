package org.rockyJie.v2.handlers;

import java.sql.PreparedStatement;

/**
 * RockeyJie
 * 2020/7/20
 */
public class ParameterHandler {

    private PreparedStatement preparedStatement;

    public ParameterHandler(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setParams(Object[] params) {
        try{
            if(null == params){
                return;
            }
            for (int i = 0; i < params.length; i++) {
                int index = i + 1;
                if (params[i] instanceof Integer) {
                    preparedStatement.setInt(index, (Integer) params[i]);
                }else if(params[i] instanceof Double){
                    preparedStatement.setDouble(index, (Double) params[i]);
                }else if(params[i] instanceof Float){
                    preparedStatement.setFloat(index, (Float) params[i]);
                }else if(params[i] instanceof String){
                    preparedStatement.setString(index, (java.lang.String) params[i]);
                }else if(params[i] instanceof java.sql.Date){
                    preparedStatement.setDate(index, (java.sql.Date) params[i]);
                }else if(params[i] instanceof Boolean){
                    preparedStatement.setBoolean(index, (Boolean) params[i]);
                }else{
                    preparedStatement.setObject(index, params[i]);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
