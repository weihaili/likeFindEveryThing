package cn.org.kkl.bytecodeo.javassist;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import javassist.NotFoundException;

/**
 * @author Admin
 *使用javassit创建一个类
 */
public class Test {
	
	public static void main(String[] args) {
		
		Test t = new Test();
		t.test();
	}
	
	/**
	 * 使用javassist步骤
	 * 1. 获取类池 classpool
	 * 2. 调用javassistAPI 给定类名或接口注解等名，创建类，接口，注解获取到创建类的CtClass对象
	 * 3. 使用该对象，创建类的属性，方法，构造器。
	 */
	private void test() {
		ClassPool pool=ClassPool.getDefault();
		CtClass cc=pool.makeClass("cn.org.kkl.bytecodeo.javassist.Emp");
		
		try {
			CtField empnoField=CtField.make("private int empno;", cc);
			CtField enameField=CtField.make("private String ename;", cc);
			cc.addField(empnoField);
			cc.addField(enameField);
			
			CtMethod getEmpnoMethod=CtMethod.make("public int getEmpno(){return empno;}", cc);
			CtMethod setEmpnoMethod=CtMethod.make("public void setEmpno(int empno){this.empno=empno;}", cc);
			CtMethod getEnameMethod=CtMethod.make("public String getEname(){return ename;}", cc);
			CtMethod setEnameMethod=CtMethod.make("public void setEname(String ename){this.ename=ename;}", cc);
			cc.addMethod(setEmpnoMethod);
			cc.addMethod(getEmpnoMethod);
			cc.addMethod(setEnameMethod);
			cc.addMethod(getEnameMethod);
			
			CtConstructor constructor=new CtConstructor(new CtClass[] {}, cc);
			constructor.setBody("{super();}");
			//CtConstructor constructor2 = new CtConstructor(new CtClass[] {CtClass.intType,pool.get("java.lang.String")}, cc);
			//constructor2.setBody("{this();this.empno = empno; this.ename = ename;}");
			cc.addConstructor(constructor);
			//cc.addConstructor(constructor2);
			
			cc.debugWriteFile("D:"+File.separator+"temp");
			System.out.println("run success");
			
		} catch (CannotCompileException e) {
			e.printStackTrace();
		} /*catch (NotFoundException e) {
			e.printStackTrace();
		}*/
		
		
	}

}

