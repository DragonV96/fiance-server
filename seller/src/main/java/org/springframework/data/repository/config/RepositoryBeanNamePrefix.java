package org.springframework.data.repository.config;

import java.lang.annotation.*;

/**
 * @author : glw
 * @date : 2019/8/2
 * @time : 15:51
 * @Description : Repository Bean 名称的前缀
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RepositoryBeanNamePrefix {
    String value();
}
