package cn.org.kkl.designpatterns.singletonmode.enummode;

public enum EnumMode {
	
	INSTANCE;
	
	public void sayHi() {
		System.out.println("hello world");
	}

}
