package cn.org.kkl.dynamicompile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @author Admin
 * 测试动态编译
 */
public class Test {
	
	public static void main(String[] args) throws IOException {
		Test t=new Test();
		t.test02();
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
		System.out.println(result);
	}

}
