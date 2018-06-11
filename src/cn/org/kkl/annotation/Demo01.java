package cn.org.kkl.annotation;

import java.util.Date;

/**
 * 理解注解的作用：
 * 1. 校对方法的声明
 * 2. 抑制编译器警告，使代码更加清爽
 * @author Admin
 *
 */
@SuppressWarnings(value = { "all" })
public class Demo01 {

	@Override
	public String toString() {
		return "";
	}
	
	public static void main(String[] args) {
		Date date=new Date();
		
		long s=Date.parse("");
	}
	
	

}
