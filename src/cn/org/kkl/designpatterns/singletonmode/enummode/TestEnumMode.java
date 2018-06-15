package cn.org.kkl.designpatterns.singletonmode.enummode;

public class TestEnumMode {
	
	public static void main(String[] args) {
		TestEnumMode t=new TestEnumMode();
		t.testSingleSafety();
		t.testMultiThreadSafety();
	}

	/**
	 * test multiThread safety
	 */
	private void testMultiThreadSafety() {
		for (int i = 0; i < 1000; i++) {
			MultiThreadTest t=new MultiThreadTest();
			t.start();
			System.out.println(t.getName());
			
		}
	}

	/**
	 * test single thread safety
	 */
	private void testSingleSafety() {
		EnumMode e=EnumMode.INSTANCE;
		e.sayHi();
		System.out.println(e.hashCode());
		EnumMode e2 = EnumMode.INSTANCE;
		System.out.println(e2.hashCode());
		EnumMode e3=EnumMode.INSTANCE;
		System.out.println(e3.hashCode());
		System.out.println(e==e2?(e2==e3?true:false):false);
	}
}

class MultiThreadTest extends Thread{

	@Override
	public void run() {
		EnumMode e=EnumMode.INSTANCE;
		System.out.println(e.hashCode());
		e.sayHi();
		
	}
	
	
}
