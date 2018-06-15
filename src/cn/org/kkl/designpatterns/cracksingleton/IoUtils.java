package cn.org.kkl.designpatterns.cracksingleton;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class IoUtils {
	
	public static void closeAllIo(Closeable ... io) {
		for (Closeable stream : io) {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String replaceFileSeparator(String path) {
		if(path==null || path.isEmpty()) {
			System.out.println("import path is null please check");
			return "";
		}
		String usePath="";
		if (path.contains(".")) {
			String end=path.substring(path.indexOf('.'));
			String start=path.substring(0,path.indexOf('.'));
			start.replace('.', File.separatorChar);
			if(start.contains("\\") ) {
				start.replace("\\", File.separator);
			}
			if (start.contains("/")) {
				start.replace("/", File.separator);
			}
			usePath=start+end;
		}
		return usePath;
	}
	
	public static void serializeObj(String path,Object obj) {
		if(path==null || path.isEmpty()|| obj==null) {
			System.out.println("path or objec is null please check");
			return ;
		}
		String usePath=replaceFileSeparator(path);
		OutputStream os=null;
		ObjectOutputStream oos=null;
		try {
			if(usePath==null || usePath.isEmpty()) {
				System.out.println("replace file separator data lost");
				return;
			}
			os = new FileOutputStream(usePath);
			oos=new ObjectOutputStream(os);
			oos.writeObject(obj);
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeAllIo(oos,os);
		}
	}
	
	public static Object deSerializeObj(String path) {
		Object object=null;
		if(path==null || path.isEmpty()) {
			System.out.println("your import deSerializeObj path is null please check");
			return null;
		}
		String usePath=replaceFileSeparator(path);
		if(usePath==null || usePath.isEmpty()) {
			System.out.println("replace file separator data lost");
		}
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(usePath));
			object=ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			closeAllIo(ois);
		}
		return object;
	}

}
