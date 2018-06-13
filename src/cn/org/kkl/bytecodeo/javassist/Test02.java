package cn.org.kkl.bytecodeo.javassist;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * @author Admin
 * javassist API
 *
 */
public class Test02 {
	
	private String classNameStr="cn.org.kkl.bytecodeo.javassist.Emp";

	public static void main(String[] args) {
		Test02 t=new Test02();
		t.test03();
	}

	/**
	 * 新增方法的两种方式
	 * 1. 直接使用字符串形式创建方法
	 * 2. 使用javassist API完整的创建一个新方法
	 * 3. 使用反射调用在类中添加的方法
	 * 
	 * 
	 */
	private void test03() {
		ClassPool pool=ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
		
			CtMethod method=CtMethod.make("public int add(int a,int b){return a+b;}", cc);
			cc.addMethod(method);
			
			CtMethod method2=new CtMethod(CtClass.intType, "product", new CtClass[] {CtClass.intType,CtClass.intType}, cc);
			method2.setModifiers(Modifier.PUBLIC);
			method2.setBody("{System.out.println(\"Emp.product()\");return $1*$2;}");
			cc.addMethod(method2);
			
			Class clazz=cc.toClass();
			Emp emp=(Emp) clazz.newInstance();
			Method addMethod=clazz.getDeclaredMethod("add", int.class,int.class);
			Method productMethod = clazz.getDeclaredMethod("product", int.class,int.class);
			int addResult=(Integer) addMethod.invoke(emp, 1,100);
			int productResult = (Integer) productMethod.invoke(emp, 19,10);
			System.out.println("addResult="+addResult);
			System.out.println("productResult="+productResult);
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 *获取方法信息：
	 *1. 获取到共有public修饰的方法，包括父类方法
	 *2. 获取到方法上的注解
	 *3. 获取参数类型
	 *4. 获取返回值类型
	 * 
	 */
	private void test02() {
		ClassPool pool=ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
			CtMethod[] methods=cc.getMethods();
			System.out.println(methods.length);
			for (CtMethod method : methods) {
				System.out.println("*********************");
				System.out.println(method.getLongName());
				System.out.println(method.getName());
				System.out.println(method.getSignature());
			}
			
			CtMethod[] methods2=cc.getDeclaredMethods();
			System.out.println(methods2.length);
			for (CtMethod method : methods2) {
				System.out.println("++++++++++++++++++++++++++++");
				System.out.println(method.getLongName());
				System.out.println(method.getName());
				System.out.println(method.getSignature());
				Object[] annotions=method.getAnnotations();
				System.out.println(annotions.length);
				for (Object annotaion : annotions) {
					System.out.println(annotaion.toString());
				}
				CtClass[] parameters=method.getParameterTypes();
				System.out.println(parameters.length);
				for (CtClass parameter : parameters) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					System.out.println(parameter.getName());
					System.out.println(parameter.getSimpleName());
				}
				CtClass value=method.getReturnType();
				System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
				System.out.println(value.getName());
				System.out.println(value.getSimpleName());
			}
			
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 *处理类的基本用法 ;
	 *1. 获取已存在类的信息 字节码
	 *2. 获取全类名 packagename.classname
	 *3. 获取类名 classname
	 *4. 获取分类 supperclass
	 *5. 获取该类实现的接口
	 *6. 获取到注解信息
	 */
	private void test01() {
		ClassPool pool=ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
			byte[] bytes=cc.toBytecode();
			System.out.println(Arrays.toString(bytes));
			
			String classNameStr=cc.getName();
			String classSimpleNameStr=cc.getSimpleName();
			System.out.println(classNameStr+"||"+classSimpleNameStr);
			CtClass supperClassName=cc.getSuperclass();
			System.out.println(supperClassName.getName());
			CtClass[] interfaces=cc.getInterfaces();
			System.out.println(interfaces.length);
			for (CtClass interfaceCt : interfaces) {
				System.out.println(interfaceCt.getName());
			}
			Object[] annotations= cc.getAnnotations();
			System.out.println(annotations.length);
			for (Object annotation : annotations) {
				System.out.println(annotation.toString());
			}
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
