package cn.org.kkl.jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NetClassLoader extends ClassLoader {

private String url;
	
	public NetClassLoader(String url) {
		this.url=url;
	}

	/* 
	 * 类加载过程：
	 * 1. 查找方法区中是否存在该字符串对应的类（该字符串对应的类是否已经被加载过，若加载过，直接返回）
	 * 2. 如果没加载过，则委派给父类加载进行加载,父类如果完成加载，则返回该类的Class对象
	 * 3. 如果父类无法完成加载，则又该加载器完成加载
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> c=null;
		if (name != null && !name.isEmpty()) {
			try {
				c=findLoadedClass(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null!=c) {
			return c;
		}else {
			ClassLoader parent=this.getParent();
			c=parent.loadClass(name);
			if(null!=c) {
				return c;
			}else {
				byte[] classByte=getClassData(name);
				if(0==classByte.length) {
					throw new ClassNotFoundException();
				}else {
					c=defineClass(name,classByte, 0, classByte.length);
				}
			}
		}
		
		return c;
	}

	
	/**
	 * @param name：指定的文件
	 * @return：字符串对应类的字节数组数据
	 */
	private byte[] getClassData(String name) {
		byte[] classData=new byte[2018];
		String path=url+name.replace(".", File.separator)+".class";
		if (null!=name && name.isEmpty() ) {
			return null;
		}
		//可以使用IoUtils工具将流中数据转化为字节数组
		InputStream is=null;
		ByteArrayOutputStream baos=null;
		try {
			URL url=new URL(path);
			is=url.openStream();
			baos=new ByteArrayOutputStream();
			int len=0;
			while (-1!=(len=is.read(classData))) {
				baos.write(classData, 0, len);
			}
			baos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null!=is) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null!=baos) {
				try {
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos.toByteArray();
	}
		
}
