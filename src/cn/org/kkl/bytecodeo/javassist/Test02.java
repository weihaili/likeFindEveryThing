package cn.org.kkl.bytecodeo.javassist;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Modifier;
import javassist.NotFoundException;

/**
 * @author Admin
 * javassist API
 *
 */
@SuppressWarnings(value="all")
public class Test02 {
	
	private String classNameStr="cn.org.kkl.bytecodeo.javassist.Emp";

	public static void main(String[] args) {
		Test02 t=new Test02();
		t.test07();
	}

	/**
	 * javassist API对注解的操作
	 * 1. 获取到所有的注解
	 * 2. 读取到注解中的附带信息
	 */
	private void test07() {
		ClassPool pool=ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
			Object[] objects=cc.getAnnotations();
			System.out.println(objects.length);
			for (Object object : objects) {
				System.out.println(object.getClass());
			}
			Author author=(Author) objects[0];
			System.out.println(author.name());
			System.out.println(author.year());
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}

	/**
	 * javassist API 构造器操作 
	 * 获取到所有的构造器
	 * 修改构造器的方法和修改普通方法的方式相同
	 */
	private void test06() {
		ClassPool pool=ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
			
			CtConstructor[] constructors=cc.getConstructors();
			System.out.println(constructors.length);
			for (CtConstructor ctConstructor : constructors) {
				System.out.println(ctConstructor.getModifiers());
				System.out.println(ctConstructor.getLongName());
				CtClass[] classes=ctConstructor.getParameterTypes();
				System.out.println(classes.length);
				for (CtClass ctClass : classes) {
					System.out.println(ctClass.getName());
					//System.out.println(ctClass.getClass());
				}
			}
			CtConstructor constructor=cc.getDeclaredConstructor(new CtClass[] {CtClass.intType,pool.get("java.lang.String")});
			constructor.insertBefore("System.out.println(\"parameter constructor is updated at:"+new Date()+"\");");
			
			Class clzz=cc.toClass();
			Constructor constructor2=clzz.getDeclaredConstructor(int.class,String.class);
			Emp emp=(Emp) constructor2.newInstance(1001,"mayun");
			System.out.println(emp.getEname()+emp.getEmpno());
			
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}


	/**
	 * 使用javassistAPI操作属性
	 * 1. 首先获取到该类的CtClass对象
	 * 2. 操作该类的CtClass对象获取到指定的属性
	 * 3. 新添加属性
	 * 4. 快速添加get，set方法:使用CtNewMethod.get()/.set()快速添加get set方法 
	 * 5. 使用反射获取到添加的属性的名称，新添加的get set方法 以及参数列表
	 */
	private void test05() {
		ClassPool pool = ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
			CtField enameField = cc.getDeclaredField("ename");
			System.out.println(enameField.getModifiers());
			System.out.println(enameField.getName());
			System.out.println(enameField.getType());
			
			CtField pwd=CtField.make("private String password;", cc);
			cc.addField(pwd);
			
			CtMethod getPassword=CtNewMethod.getter("getPassword", pwd);
			CtMethod setPassword=CtNewMethod.setter("setPassword", pwd);
			cc.addMethod(setPassword);
			cc.addMethod(getPassword);
			
			CtField salary = new CtField(CtClass.doubleType, "salary", cc);
			salary.setModifiers(Modifier.PRIVATE);
			cc.addField(salary);
			
			CtMethod setSalary=CtNewMethod.setter("setSalary", salary);
			CtMethod getSalary=CtNewMethod.getter("getSalary", salary);
			cc.addMethod(getSalary);
			cc.addMethod(setSalary);
			
			Class clazz=cc.toClass();
			Emp emp=(Emp) clazz.newInstance();
			Field[] fields=clazz.getDeclaredFields();
			System.out.println(fields.length);
			for (Field field : fields) {
				System.out.println(field.getName());
				System.out.println(field.getModifiers());
			}
			Method[] methods=clazz.getDeclaredMethods();
			System.out.println(methods.length);
			for (Method method : methods) {
				System.out.println(method.getModifiers());
				System.out.println(method.getName());
				System.out.println(method.getParameterCount());
				Type[] types=method.getGenericParameterTypes();
				for (Type type : types) {
					System.out.println(type.getTypeName());
				}
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 使用javassist修改类中已经存在的方法
	 * 1. 使用完整类名获取到该类的CtClass对象
	 * 2. 操作该类的CtClass对象获取到需要修改的方法。
	 * 3. 使用javassistAPI修改该方法
	 * 4. 可以在已经存在的方法中添加其他逻辑，修改已存在的逻辑。
	 */
	private void test04() {
		ClassPool pool=ClassPool.getDefault();
		try {
			CtClass cc=pool.get(classNameStr);
			CtMethod takeOver=cc.getDeclaredMethod("takeOver", new CtClass[] {CtClass.intType,CtClass.intType});
			takeOver.insertBefore("System.out.println($1+$2);System.out.println(\"takeOver method is updated"+new Date()+"\");");
			takeOver.insertAfter("System.out.println(\"hello\"+$1);");
			takeOver.insertAt(14, "a+=b+9;");
			
			Class<Emp> clazz=(Class<Emp>) cc.toClass();
			Emp emp=clazz.newInstance();
			Method takeOverF = clazz.getDeclaredMethod("takeOver", int.class,int.class);
			Object result=takeOverF.invoke(emp, 11,10);
			if(result instanceof Integer) {
				System.out.println((int)result);
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 新增方法的两种方式
	 * 1. 直接使用字符串形式创建方法
	 * 2. 使用javassist API完整的创建一个新方法
	 * 3. 使用反射调用在类中添加的方法
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
