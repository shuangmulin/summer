package org.summer.container.annotation;

import java.lang.annotation.*;

/**
 * 表名是否要成为容器的bean
 *
 * @author 钟宝林
 * @date 2018-11-05 16:32
 **/
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Component {

    /**
     * bean在容器里的名称
     */
    String value() default "";

}
