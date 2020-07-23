package org.rockyJie.v2.annotation;

import java.lang.annotation.*;

/**
 * RockeyJie
 * 2020/7/23
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    String value();
}
