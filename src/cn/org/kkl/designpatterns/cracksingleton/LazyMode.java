package cn.org.kkl.designpatterns.cracksingleton;

import java.io.Serializable;

public class LazyMode implements Serializable{
	
	private static LazyMode instance;
	
	/**
	 *refuse reflection invoke this constructor create new instance; 
	 */
	private LazyMode() {
		if(instance!=null) {
			throw new RuntimeException("this objec is singleton can not invoke this constructor create new instance");
		}
	}
	
	public static synchronized LazyMode newInstance() {
		if (instance == null) {
			instance=new LazyMode();
		}
		return instance;
	}
	
	private Object readResolve() {
		return instance;
	}

}
