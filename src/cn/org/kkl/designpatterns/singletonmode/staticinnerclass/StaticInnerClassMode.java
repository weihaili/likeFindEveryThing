package cn.org.kkl.designpatterns.singletonmode.staticinnerclass;

public class  StaticInnerClassMode {
	
	private StaticInnerClassMode() {
	}
	
	private static class Creatator{
		private static final StaticInnerClassMode instance=new StaticInnerClassMode();
	}
	
	public static  StaticInnerClassMode newInstance() {
		return Creatator.instance;
	}

}
