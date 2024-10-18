package cc.realtec.real.xxljob.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XxlJobInfoRegistry {
    String schedule_type() default "CRON";
    String cron() default "0/5 * * * * ?";
    String jobDesc() default "default jobDesc";
    String author() default "default author";
}
