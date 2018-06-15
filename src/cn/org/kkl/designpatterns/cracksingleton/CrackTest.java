package cn.org.kkl.designpatterns.cracksingleton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings(value="all")
public class CrackTest {
	
	public static void main(String[] args) {
		CrackTest cTest=new CrackTest();
		cTest.testSingleThreadSafety();
		//cTest.testReflectCrack();
		cTest.testDeserializationCrack();
	}

	/**
	 * deserialized crack
	 * 
	 */
	private void testDeserializationCrack() {
		LazyMode lm1=LazyMode.newInstance();
		System.out.println(lm1.hashCode());
		LazyMode lm2=LazyMode.newInstance();
		String path="D:"+File.separator+"temp"+File.separator+"code.txt";
		IoUtils.serializeObj(path, lm1);
		LazyMode lm3=(LazyMode) IoUtils.deSerializeObj(path);
		System.out.println(lm3.hashCode());
	}

	/**
	 * reflect crack
	 * 1. get Class instance of LazyMode
	 * 2. get the constructor of LazyModel by this instance
	 * 3. refuse Access modifier permission checks
	 * 4. create instance by this constructor
	 * 5. compare two instance of LazyMode ,because invoke two times new object operation .so they are not the same
	 */
	private void testReflectCrack() {
		try {
			Class<LazyMode> clazz=(Class<LazyMode>) Class.forName("cn.org.kkl.designpatterns.cracksingleton.LazyMode");
			Constructor<LazyMode> constructor=clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			LazyMode lm1=constructor.newInstance();
			System.out.println(lm1);
			//LazyMode lm2=constructor.newInstance();
			//System.out.println(lm1==lm2);
		
		} catch (ClassNotFoundException e) {
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
	 * test single thread safety
	 */
	private void testSingleThreadSafety() {
		LazyMode lm=LazyMode.newInstance();
		LazyMode lm2=LazyMode.newInstance();
		System.out.println(lm==lm2);
	}

}
