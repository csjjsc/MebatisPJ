package org.rockyJie.v2.session;

import org.rockyJie.v2.binding.MapperRegistry;
import org.rockyJie.v2.executors.Executor;
import org.rockyJie.v2.executors.SimpleExecutor;
import org.rockyJie.v2.plugin.Interceptor;
import org.rockyJie.v2.plugin.InterceptorChain;

import java.io.File;
import java.util.*;

/**
 * RockeyJie
 * 2020/7/16
 */
public class Configuration {

    private static ResourceBundle properties;
    private static ResourceBundle sqlMapper;
    private static final MapperRegistry MAPPER_REGISTRY = new MapperRegistry();
    private static final Map<String, String> mappedStatement = new HashMap<>();
    private InterceptorChain interceptorChain = new InterceptorChain();  //插件链
    private List<Class<?>> mapperList = new ArrayList<>();
    private List<String> classFilePath = new ArrayList<>();

    static {
        properties = ResourceBundle.getBundle("mebatis");
        sqlMapper = ResourceBundle.getBundle("sql");
    }

    public Configuration() {
        //解析sql.properties
        for (String key : sqlMapper.keySet()) {
            Class mapperClass = null; //mapper的Class 对象
            String statement = null; //sql 语句
            String pojoStr = null;  //pojo全类名
            Class pojoClass = null;  //pojoClass对象
            String value = sqlMapper.getString(key);
            statement = value.split("--")[0];
            pojoStr = value.split("--")[1];
            try {
                mapperClass = Class.forName(key.substring(0, key.lastIndexOf(".")));
                pojoClass = Class.forName(pojoStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            MAPPER_REGISTRY.addMapper(mapperClass, pojoClass);   //将mapper 的类对象 对应pojo 的类对象
            mappedStatement.put(key, statement);    //用mapper的全类名+方法名  对应 sql语句
        }
        //解析mapper
        String mapperPath = properties.getString("mapper.path");
        scanPackage(mapperPath);  //扫描mapper 所在的位置，并保存Mapper类对象

        //解析插件
        String pluginValue = properties.getString("plugin.path");
        String[] plugins = pluginValue.split(",");
        if (null != plugins) {
            for (String pluginPath : plugins) {
                Interceptor interceptor = null;
                try {
                    interceptor = (org.rockyJie.v2.plugin.Interceptor) Class.forName(pluginPath).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                interceptorChain.pluginAll(interceptor);
            }
        }
    }

    private void parseClass(Class mapper) {

    }

    public Executor newExcutor() {
        Executor executor = new SimpleExecutor();
        if(interceptorChain.hasPlugin()){
           return (Executor) interceptorChain.pluginAll(executor);
        }
        return executor;
    }

    public boolean hasStatement(String statementId) {
        return mappedStatement.containsKey(statementId);
    }

    public String getMappedStatement(String statementId) {
        return mappedStatement.get(statementId);
    }

    private void scanPackage(String mapperPath) {
        //获得绝对路径
        String classPath = this.getClass().getResource("/").getPath();
        mapperPath = mapperPath.replace(".", "/");
        String mainPath = classPath + mapperPath;
        System.out.println("mainPath:" + mainPath);
        doPath(new File(mainPath));
        for (String className : classFilePath) {
            className = className.replace(
                    classPath.replace("/", "\\").replaceFirst("\\\\", ""),
                    "").replace("\\", ".").replace(".class", "");
            System.out.println("className:" + className);
            Class<?> clazz = null;
            try {
                clazz = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (clazz.isInterface()) {
                mapperList.add(clazz);
            }
        }
    }

    //获取路径下的所有类文件
    private void doPath(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                doPath(f);
            }
        } else {
            if (file.getName().endsWith(".class")) {
                classFilePath.add(file.getPath());
            }
        }
    }


    public <T> T getMapper(Class clazz, SqlSession sqlSession) {
        return (T) MAPPER_REGISTRY.getMapper(sqlSession, clazz);
    }

    public static String getJDBCDriver() {
        return properties.getString("jdbc.driver");
    }

    public static String getDBUser() {
        return properties.getString("jdbc.user");
    }

    public static String getDBURL() {
        return properties.getString("jdbc.url");
    }

    public static String getDBPassWord() {
        return properties.getString("jdbc.password");
    }
}
