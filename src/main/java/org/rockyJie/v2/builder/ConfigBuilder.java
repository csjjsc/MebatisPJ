package org.rockyJie.v2.builder;

import org.rockyJie.v2.session.Configuration;

/**
 * RockeyJie
 * 2020/7/16
 */
public class ConfigBuilder {

    private static Configuration configuration;

    public static Configuration getConfiguration(){
        if(null == configuration){
            configuration = new Configuration();
        }
        return configuration;
    }
}
