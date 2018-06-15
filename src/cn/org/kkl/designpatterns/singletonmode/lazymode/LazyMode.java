package cn.org.kkl.designpatterns.singletonmode.lazymode;

public class LazyMode {
	
	private static LazyMode instance;
	
	private LazyMode() {
	}
	
	public static synchronized LazyMode newInstance() {
		if(instance==null) {
			instance=new LazyMode();
		}
		return instance;
	}
	

}
