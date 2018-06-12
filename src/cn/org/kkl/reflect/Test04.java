package cn.org.kkl.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.org.kkl.reflect.bean.User;

/**
 * 用于性能比较即
 * 1.普通对象方法调用
 * 2.反射API接收访问修饰符检查
 * 3.拒绝反射API访问修饰符检查
 * 调用同一方法耗时比较
 * @author Admin
 *
 */
public class Test04 {
	
	public static void main(String[] args) {
		Test04 t=new Test04();
		t.test01();
		t.test02();
		t.test03();
	}

	/**
	 *接收访问修饰符检查 使用反射API调用方法 
	 */
	private void test03() {
		long start=System.currentTimeMillis();
		Class<User> clazz=User.class;
		User user;
		Method method;
		try {
			user = clazz.newInstance();
			method=clazz.getMethod("getName");
			method.setAccessible(false);
			for (int i = 0; i < 1000000000; i++) {
				method.invoke(user);
			}
		} catch (InstantiationException e) {
			System.out.println("参数指定异常，该对象无法初始化");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("访问修饰符限制，无权限访问该构造器");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("指定参数类型的方法不存在");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("访问修饰符限制");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		System.out.println("接受修饰符检查反射API调用方法耗时："+(end-start)+" ms");
	}

	
	/**
	 *拒绝访问修饰符检查 使用反射API调用方法
	 */
	private void test02() {
		long start=System.currentTimeMillis();
		Class<User> clazz=User.class;
		User user;
		Method method;
		try {
			user = clazz.newInstance();
			method=clazz.getMethod("getName");
			method.setAccessible(true);
			for (int i = 0; i < 1000000000; i++) {
				method.invoke(user);
			}
		} catch (InstantiationException e) {
			System.out.println("参数指定异常，该对象无法初始化");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("访问修饰符限制，无权限访问该构造器");
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			System.out.println("指定参数类型的方法不存在");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("访问修饰符限制");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long end=System.currentTimeMillis();
		System.out.println("拒绝访问修饰符检查反射API调用方法耗时："+(end-start)+" ms");
	}

	/**
	 *对象的普通调用方法 
	 */
	private void test01() {
		long start=System.currentTimeMillis();
		User user=new User();
		for (int i = 0; i < 1000000000; i++) {
			user.getName();
		}
		long end=System.currentTimeMillis();
		System.out.println("对象调用方法耗时："+(end-start)+" ms");
		
	}

}
