package cn.org.kkl.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.org.kkl.reflect.bean.User;

/**
 * 通过反射API动态的操作：调用构造器，方法，属性
 * @author Admin
 */
public class Test03 {
	
	public static void main(String[] args) {
		Test03 t=new Test03();
		t.test03();
	}

	/**
	 * 1.通过类获取到该类的Class对应的对象
	 * 2.通过该对象使用反射API获取到该类的构造器
	 * 3.使用该构造器创建该类的实例
	 * 4.使用反射API获取到指定的属性
	 * 5.通过反射API操作该属性:私有的或者受访问修饰符限制的属性可以通过该属性的
	 * 	field.setAccessible(true)方法，使访问修饰符的限制失效。从而可以访问修改私有属性。
	 * 	可以通过filed.get(实例)获取到该实例的该属性的值。
	 */
	private void test03() {
		Class<?> clazz=User.class;
		try {
			Constructor<?> constructor=clazz.getDeclaredConstructor(String.class,String.class,int.class);
			User user=(User) constructor.newInstance("kkl","123456",18);
			Field field=clazz.getDeclaredField("name");
			field.setAccessible(true);//使该属性的访问修饰符失效
			field.set(user, "mayun");
			System.out.println(user.getName());
			String name=(String) field.get(user);
			System.out.println(name);
		} catch (NoSuchMethodException e) {
			System.out.println("指定参数类型的构造器不存在");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("无权限访问该构造器");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("初始化异常，参数指定的不匹配");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("非法访问,访问修饰符限制");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("指定的实参异常");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("方法调用异常");
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			System.out.println("指定的属性不存在");
			e.printStackTrace();
		}
	}

	/**
	 * 1. 通过类的对象获取到该类的Class对应的对象
	 * 2. 通过反射API获取到该类的有参构造器
	 * 3. 床架该类的对象
	 * 4. 调用该类的普通方法
	 * 	step：1.通过该类的Class获取到该类的对象
	 *       2.通过反射API获取到该类的构造器
	 *       3. 获取到该类的一个实例
	 *       4.通过该类的Class对象使用反射API获取到想调用的方法method
	 *       5.使用该method.invoke(class,methodArguments)
	 */
	private void test02() {
		Class<?> clazz=new User().getClass();
		try {
			Constructor<?> constructor=clazz.getDeclaredConstructor(String.class,String.class,int.class);
			User user=(User) constructor.newInstance("kkl","123456",18);
			//正常调用
			user.setAdult(true);
			System.out.println(user);
			//使用反射API调用
			Method setAdultMethod=clazz.getMethod("setAdult", boolean.class);
			setAdultMethod.invoke(user, false);
			System.out.println(user);
		} catch (NoSuchMethodException e) {
			System.out.println("指定参数类型的构造器不存在");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("无权限访问该构造器");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("对象初始化异常，构造参数自动异常");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("构造器访问修饰符限制，无权限访问该构造器");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("创建对象时指定的参数异常");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("调用构造方法异常");
			e.printStackTrace();
		}
		
	}

	/**
	 *1. 通过类的对象获取到该类对应的Class对象 
	 *2. 通过操作该对象获取该类的属性
	 *3. 动态调用该类的构造器:值得注意的是最好使用getConstructors()
	 *	  或者getConstructor(parameterType ... class)
	 *   通过操作使用public访问修饰符修饰的构造器来调用newInstance(parameterType ... class)
	 *   来实现动态创建对象的目的。
	 */
	private void test01() {
		Class<?> clazz=new User().getClass();
		try {
			Constructor<?> constructor=clazz.getDeclaredConstructor(String.class,String.class);
			/*User user=(User) constructor.newInstance("kkl","123456");
			System.out.println(user);*/
			Constructor<?> constructor2=clazz.getDeclaredConstructor(String.class,String.class,int.class);
			User user2=(User) constructor2.newInstance("kkl2","123456",18);
			System.out.println(user2);
		} catch (NoSuchMethodException e) {
			System.out.println("指定参数的构造器不存在");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("无权限访问该构造器");
			e.printStackTrace();
		} catch (InstantiationException e) {
			System.out.println("初始化异常");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("非法访问,构造器的访问修饰符限制");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("非法参数");
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println("调用构造方法异常");
			e.printStackTrace();
		}
	}

}
