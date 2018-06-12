package cn.org.kkl.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.org.kkl.reflect.bean.User;

/**
 * 通过反射API操作类的Class对象，获取该类的属性，方法，构造器等
 * @author Admin
 */
public class Test02 {
	
	public static void main(String[] args) {
		Test02 t=new Test02();
		t.test09();
	}
	
	
	/**
	 * 1. 通过对象获取到类对应Class的对象
	 * 2. 通过操作该对象获取到该类的所有构造器
	 * 	方法：getDeclaredConstructors()
	 *      getDeclaredConstructor(parameterType ...class)
	 */
	private void test09() {
		Class<?> clazz=new User().getClass();
		Constructor<?>[] constructors=clazz.getDeclaredConstructors();
		System.out.println(constructors.length);
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		try {
			Constructor<?> constructor=clazz.getDeclaredConstructor();
			System.out.println(constructor);
			Constructor<?> constructor2=clazz.getDeclaredConstructor(String.class,String.class);
			System.out.println(constructor2);
		} catch (NoSuchMethodException e) {
			System.out.println("不存在无参构造器");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("无权限访问该构造器");
			e.printStackTrace();
		}
	}


	/**
	 * 1. 根据对象获取到该对象对应的类，通过该类获取到该类对用的Class的对象
	 * 2. 通过操作该对象和获取到该类的构造器（仅能获取到使用public修饰的）
	 * 	method:getConstructors()
	 * 		   getConstructor(parameterType ... class)
	 */
	private void test08() {
		Class<?> clazz=new User().getClass();
		Constructor<?>[] constructors=clazz.getConstructors();
		System.out.println(constructors.length);
		for (Constructor<?> constructor : constructors) {
			System.out.println(constructor);
		}
		try {
			Constructor<?> constructor=clazz.getConstructor();
			System.out.println(constructor);
			Constructor<?> constructor2=clazz.getConstructor(String.class,String.class,int.class,char.class,double.class,boolean.class);
			System.out.println(constructor2);
			Constructor<?> constructor3=clazz.getConstructor(String.class,String.class);
			System.out.println(constructor3);
		} catch (NoSuchMethodException e) {
			System.out.println("不存在无参构造器");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("没有权限访问该构造器");
			e.printStackTrace();
		}
	}


	/**
	 * 1. 根据对象获取到该对象对应类的Class的对象
	 * 2. 通过操作该对象获取到该类所有方法或指定方法名和方法参数的方法
	 * 方法：getDeclaredMethods()
	 *     getDeclaredMethod("methodName",parameterType ...class);
	 */
	private void test07() {
		Class<?> clazz=new User().getClass();
		Method[] methods=clazz.getDeclaredMethods();
		System.out.println(methods.length);
		for (Method method : methods) {
			System.out.println(method);
		}
		try {
			Method method=clazz.getDeclaredMethod("getName");
			Method method2=clazz.getDeclaredMethod("setName", String.class);
			System.out.println(method);
			System.out.println(method2);
		} catch (NoSuchMethodException e) {
			System.out.println("不存在与之对应的同名方法");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("没有权限访问该类的该方法");
			e.printStackTrace();
		}
	}


	/**
	 * 1. 根据对象获取到该对象所对应类的Class的对象
	 * 2. 通过操作该对象获取到该类的方法(包含该类和其父类的所有使用public修饰的方法)
	 * 	对应方法：getMethods("methodName",parameterType)与getMethods()
	 */
	private void test06() {
		Class<?> clazz=new User().getClass();
		Method[] methods=clazz.getMethods();
		System.out.println(methods.length);
		for (Method method : methods) {
			System.out.println(method);
		}
		try {
			Method method=clazz.getMethod("getName");
			System.out.println(method);
		} catch (NoSuchMethodException e) {
			System.out.println("不存在与之同名的方法");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("没有权限访问该类的该方法");
			e.printStackTrace();
		}
		
	}


	/**
	 * 1. 使用对象获取到该类对应的Class的对象
	 * 2. 获取到所有该类中已经声明的属性
	 * 	  a. getDeclaredFields()返回一个数组，该数组中包含了所有该对象的属性
	 * 	  b. getDeclaredField("fieldName")获取到与字符串同名的属性，若不存在或无权访问则会抛出指定异常
	 */
	private void test05() {
		Class<?> clazz=new User().getClass();
		Field[] fields=clazz.getDeclaredFields();
		System.out.println(fields.length);
		for (Field field : fields) {
			System.out.println(field);
		}
		try {
			Field field=clazz.getDeclaredField("name");
			System.out.println(field);
		} catch (NoSuchFieldException e) {
			System.out.println("该类中没有找到与之指定的同名属性");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("没有权限访问该类的该属性");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 1. 通过对象获取到该类对应的Class的对象
	 * 2. 获取该类的属性(仅能得到访问修饰符为public修饰的属性)
	 * 	  a. getFields()获取到该类中所有的使用public访问修饰符修饰的属性名
	 * 	  b. getField("fieldName")根据指定的fieldName获取到该类中的该fieldName成员变量
	 */
	private void test04() {
		Class<?> clazz=new User().getClass();
		Field[] fields=clazz.getFields();
		System.out.println(fields.length);
		try {
			Field name=clazz.getField("uuid");
			System.out.println(null==name?null:name);
		} catch (NoSuchFieldException e) {
			System.out.println("指定的属性名在该类中不存在与之同名的属性");
			e.printStackTrace();
		} catch (SecurityException e) {
			System.out.println("系统安全限制访问该类的该属性");
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过完整的类的字符串表示形式获取该字符串对应类的Class对象
	 */
	private void test01() {
		String path="cn.org.kkl.reflect.bean.User";
		try {
			Class<?> clzz=Class.forName(path);
		} catch (ClassNotFoundException e) {
			System.out.println("指定的类全名有误,指定的类全名对应的类名不存在");
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过类获取到该类对应的Class对象
	 */
	private void test02() {
		Class<?> clazz=User.class;
		//获取类全名
		System.out.println(clazz.getName());
		//获取类名（不带包名）
		System.out.println(clazz.getSimpleName());
		//获取该类的类型（字符串表示形式）
		System.out.println(clazz.getTypeName());
	}
	
	/**
	 * 通过对象获取该类的Class对象
	 */
	private void test03() {
		Class<?> clazz=new User().getClass();
	}

}
