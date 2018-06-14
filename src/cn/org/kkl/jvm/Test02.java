package cn.org.kkl.jvm;

public class Test02 {
	
	public static void main(String[] args) {
		ClassLoader loader=ClassLoader.getSystemClassLoader();
		System.out.println(loader.getClass());
		ClassLoader parentClassLoader=loader.getParent();
		System.out.println(parentClassLoader.getClass());
		System.out.println(parentClassLoader.getParent());
	
		System.out.println(System.getProperty("java.class.path"));
		
	}

}
