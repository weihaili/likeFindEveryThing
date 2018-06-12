package cn.org.kkl.dynamicompile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;



/**
 * @author Admin
 * 测试动态编译
 */
public class Test {
	
	public static void main(String[] args) throws IOException {
		Test t=new Test();
		t.test04();
	}
	
	/**
	 * 使用反射运行已经编译好的.class文件
	 * 此处在使用反射api 动态调用main方法传实参时，需要把new String[]{}强制类型转换
	 * 为Object，代表该实参表示一个对象，而不是数组中的每一个值代表一个实参,如果不强转，会出现
	 * 参数不匹配的异常，因为在main方法中只有一个字符串数组，此字符串数组中不管有多少个字符串，都
	 * 表示该方法只接收一个参数，即把该字符串数组作为一个整体，才是main方法的参数。
	 */
	private void test04() {
		try {
			URL[] urls=new URL[] {new URL("File:/" +"D:/temp/")};
			URLClassLoader loader=new URLClassLoader(urls);
			Class c=loader.loadClass("DynamicCompile");
			c.getMethod("main", String[].class).invoke(null, (Object)new String[] {});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *启用新的进程运行编译好的.class文件 
	 *Runtime.exec("java -cp d:/temp  DynamicCompile")；获取到一个进程
	 *从此进程中可以获取到此文件执行后的结果
	 * @throws IOException 
	 */
	private void test03() throws IOException {
		Runtime run=Runtime.getRuntime();
		Process process=run.exec("java -cp D:"+File.separator+"temp  DynamicCompile");
		InputStream is = process.getInputStream();
		StringBuilder sb = new StringBuilder();
		byte[] flush=new byte[516];
		int len=0;
		while(-1!=(len=is.read(flush))) {
			sb.append(new String(flush, 0, len));
		}
		System.out.println(sb.toString());
		
		//BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		/*char[] flush=new char[256];
		int len=br.read(flush);
		String info=new String(flush, 0, len);
		System.out.println(info);*/
		
		/*String info="";
		while(null!=(info=br.readLine())) {
			System.out.println(info);
		}*/
		
		CloseUtil.closeIo(is);
		System.out.println("run success");
	}


	/**
	 * 获取动态编译器，调动run（）方法实现动态编译
	 */
	private void test01() {
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		String[] sourceFile= {"D:"+File.separator+"temp"+File.separator+"Helloworld.java"};
		int result=compiler.run(null,null,null,sourceFile);
		System.out.println(result==0?"编译成功":"编译失败");
	}
	
	/**
	 *将字符串存为临时文件，调用动态编译，生成.class文件 
	 * @throws IOException 
	 */
	private void test02() throws IOException {
		String sorcejava="public class DynamicCompile {\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		System.out.println(\"dynamic compile this file\");\r\n" + 
				"	}\r\n" + 
				"}";
		File javaFile=new File("D:"+File.separator+"temp"+File.separator+"DynamicCompile.java");
		InputStream is=null;
		OutputStream os=null;
		
		if(!javaFile.exists()) {
			javaFile.createNewFile();
		}
		if(javaFile.exists()) {
			if(!sorcejava.isEmpty()) {
				os=new FileOutputStream(javaFile);
				os.write(sorcejava.trim().getBytes());
				os.flush();
			}
		}
		CloseUtil.closeIo(os,is);
		test03("D:"+File.separator+"temp"+File.separator+"DynamicCompile.java");
	}
	
	private void test03(String str) {
		if (str.isEmpty()) {
			return;
		}
		JavaCompiler compiler=ToolProvider.getSystemJavaCompiler();
		int result=compiler.run(null, null, null, str);
		System.out.println(result==0?"complie success":"compile fail");
	}
	

}
