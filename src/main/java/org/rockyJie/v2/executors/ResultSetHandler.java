package org.rockyJie.v2.executors;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * RockeyJie
 * 2020/7/20
 */
public class ResultSetHandler {


    public <T> T handle(ResultSet resultSet, Class<T> pojo) {
        List<Object> list = new ArrayList<>();
        try {
            Field[] pojoDeclaredFields = pojo.getDeclaredFields();
            while (resultSet.next()) {
                Object obj = pojo.newInstance();
                for (Field field : pojoDeclaredFields) {
                    setValue(obj, field, resultSet);
                }
                list.add(obj);
            }
            if(list.size() == 1){
                return (T) list.get(0);
            }else{
                return (T) list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setValue(Object pojo, Field field, ResultSet resultSet) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = pojo.getClass().getDeclaredMethod("set" + firstUpperCase(field.getName()), field.getType());
        method.invoke(pojo, getDBValue(resultSet, field));
    }

    private Object getDBValue(ResultSet resultSet, Field field) {
        Class<?> type = field.getType();
        String column = camleToUnderLine(field.getName());
        try {
            if (type == Integer.class) {
                return resultSet.getInt(column);
            } else if (type == Double.class) {
                return resultSet.getDate(column);
            } else if (type == Float.class) {
                return resultSet.getFloat(column);
            } else if (type == String.class) {
                return resultSet.getString(column);
            } else if (type == java.sql.Date.class) {
                return resultSet.getDate(column);
            } else if (type == Boolean.class) {
                return resultSet.getBoolean(column);
            } else {
                return resultSet.getObject(column);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String camleToUnderLine(String fieldName) {
        StringBuilder sb = new StringBuilder(fieldName);
        int temp = 0;
        if (!fieldName.contains("_")) {
            for (int i = 0; i < fieldName.length(); i++) {
                if (Character.isUpperCase(fieldName.charAt(i))) {
                    sb.insert(i + temp, "_");  //会导致sb的长度增加，在多个驼峰的时候，下划线的位置将不对
                    temp += 1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    private String firstUpperCase(String fieldName) {
        char tmp = fieldName.charAt(0);
        tmp = Character.toUpperCase(tmp);
        return tmp + fieldName.substring(1);
    }

}
