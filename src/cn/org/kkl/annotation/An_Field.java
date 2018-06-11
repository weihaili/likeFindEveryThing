package cn.org.kkl.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value= {ElementType.FIELD})
public @interface An_Field {
	
	String columnName();
	
	String dataType();
	
	int length();

}
