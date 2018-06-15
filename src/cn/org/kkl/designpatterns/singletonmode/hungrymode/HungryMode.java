package cn.org.kkl.designpatterns.singletonmode.hungrymode;

/**
 * @author Admin
 * hungry mode
 *
 */
public class HungryMode {
	
	private static HungryMode instance=new HungryMode();
	
	private HungryMode() {
	}
	
	public static synchronized HungryMode newInstance() {
		return instance;
	}
	
	public void sayHi() {
		System.out.println("how are you?");
	}
	
	public void sayHello() {
		System.out.println("fine");
	}
	
	public void sayGoodBye() {
		System.out.println("bye bye");
	}
}
