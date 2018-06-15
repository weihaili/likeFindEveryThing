package cn.org.kkl.designpatterns.singletonmode.lazymode;

public class LazyMode {
	
	private static LazyMode instance=new LazyMode();
	
	private LazyMode() {
	}
	
	public static LazyMode newInstance() {
		return instance;
	}
	

}
