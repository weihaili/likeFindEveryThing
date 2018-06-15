package cn.org.kkl.designpatterns.singletonmode.hungrymode;

public class TestHungryMode {

	public static void main(String[] args) {
		TestHungryMode t=new TestHungryMode();
		t.testSingleThread();
		t.testMultiThreadSafety();
	}

	/**
	 * test multiThread safety
	 */
	private void testMultiThreadSafety() {
		for (int i = 0; i < 1000; i++) {
			TestMultiThread t = new TestMultiThread();
			t.start();
			System.out.println("thread name= "+t.getName()+" thread id= "+t.getId());
		}
	}

	/**
	 * test single thread safety
	 */
	private void testSingleThread() {
		HungryMode h1 = HungryMode.newInstance();
		System.out.println(h1.hashCode());
		HungryMode h2=HungryMode.newInstance();
		System.out.println(h2.hashCode());
		HungryMode h3=HungryMode.newInstance();
		System.out.println(h3.hashCode());
		System.out.println(h1==h2?(h2==h3?true:false):false);
	}

}

class TestMultiThread extends Thread{

	@Override
	public void run() {
		HungryMode h=HungryMode.newInstance();
		System.out.println(h.hashCode());
	}
	
}
