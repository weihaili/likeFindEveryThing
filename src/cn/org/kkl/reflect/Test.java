package cn.org.kkl.reflect;

import cn.org.kkl.reflect.bean.User;
	
@SuppressWarnings(value="all")
public class Test {

	public static void main(String[] args) {
		Test t=new Test();
		Class t1=t.test1();
		Class t2=t.test2();
		Class t3=t.test3();
		System.out.println(t1==t2);
		System.out.println(t1==t3);
		System.out.println(t3==t2);
		//同一个类及其对象仅有一个Class对象

	}
	/**
	 * 测试通过完整的类路径，动态获取该类的Class对象，从而获取到该类的完整结构属性信息
	 */
	private Class test1() {
		String path="cn.org.kkl.reflect.bean.User";
		Class clazz=null;
		try {
			clazz=Class.forName(path);//加载字符串对应的类
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	/**
	 * 测试通过类获取到该类对应的Class对象，从而获取到该类的完整结构属性信息
	 */
	private Class test2() {
		Class clazz=User.class;
		return clazz;
	}
	
	/**
	 * 测试通过类的对象获取到该类的Class对象，从而获取到该类的完整结构属性信息
	 */
	private Class test3() {
		User u=new User();
		Class clazz=u.getClass();
		return clazz;
	}

}
