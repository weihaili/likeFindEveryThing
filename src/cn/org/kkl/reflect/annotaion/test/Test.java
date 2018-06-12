package cn.org.kkl.reflect.annotaion.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.org.kkl.reflect.annotaion.An_Field;
import cn.org.kkl.reflect.annotaion.An_method;
import cn.org.kkl.reflect.annotaion.KklTable;
import cn.org.kkl.reflect.annotaion.Student;

/**
 * @author Admin
 * 反射读取注解信息
 */
public class Test {
	
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		Test test1=new Test();
		test1.test01();
	}
	
	
	/**
	 * 1. 使用类获取到该类对应的Class的对象
	 * 2. 使用该对象获取到该类中所有的Taret为Type的注解
	 * 3. 通过注解名获取到该字符串对应的注解
	 * 4. 获得属性上使用的注解
	 * 		step：
	 * 		1. 使用反射API 获取到该属性field
	 * 		2. 使用反射API 获取到属性注解的Class对象 anClazz
	 * 		3. 使用反射API 调用field.getAnnotation(fieldAnnotation.class)获取到该注解在该属性上的实例
	 * 		4. 通过调用该实例的方法获取到指定的注解值
	 * 5. 获取到方法上使用的注解
	 * 		step：
	 * 		1. 使用反射API 获取到该方法method
	 * 		2. 使用反射API 获取到方法注解的Class对象anClazz
	 * 		3. 使用反射API 调用method.getAnnotation(methodAnnotaion.class)获取到该注解在该方法上的实例
	 * 		4. 通过操作该实例获取到该注解中的附带信息
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @throws NoSuchMethodException 
	 */
	private void test01() throws NoSuchFieldException, SecurityException, NoSuchMethodException {
		Class<Student> clazz=Student.class;
		Annotation[] annotations=clazz.getAnnotations();
		for (Annotation annotation : annotations) {
			System.out.println(annotation);
		}
		System.out.println("**********************************");
		Annotation[] annotations2=clazz.getDeclaredAnnotations();
		for (Annotation annotation : annotations2) {
			System.out.println(annotation);
		}
		
		KklTable annotation=(KklTable)clazz.getAnnotation(KklTable.class);
		System.out.println(annotation.value());
		
		System.out.println("***********************************");
		Field idField=clazz.getDeclaredField("id");
		An_Field anField=idField.getAnnotation(An_Field.class);
		System.out.println(anField.columnName()+"***"+anField.dataType()+"***"+anField.length());
		
		System.out.println("*************************************");
		Method getNameMethod=clazz.getMethod("getName");
		An_method anMethod=getNameMethod.getAnnotation(An_method.class);
		System.out.println(anMethod.value()[0]);
	}
}
