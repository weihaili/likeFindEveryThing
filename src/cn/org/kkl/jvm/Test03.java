package cn.org.kkl.jvm;

import java.io.File;

public class Test03 {
	public static void main(String[] args) {
		FileSystemClassLoader loader=new FileSystemClassLoader("D:"+File.separator+"temp"+File.separator);
		try {
			loader.findClass("cn.test.Emp");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

