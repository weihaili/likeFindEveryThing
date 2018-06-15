package cn.org.kkl.designpatterns.singletonmode.lazymode;

public class TestLazyMode {

	public static void main(String[] args) {
		TestLazyMode t = new TestLazyMode();
		t.testSingleThread();
		System.out.println("++++++++++++multiThead test result++++++++++");
		t.testMultiThreadSafety();
	}

	/**
	 * test multiThread safety
	 */
	private void testMultiThreadSafety() {
		for (int i = 0; i < 1000; i++) {
			TestMultithread t = new TestMultithread();
			t.start();
			System.out.println("thread id= " + t.getId() + " thread name= " + t.getName());

		}
	}

	/**
	 * test single thread
	 */
	private void testSingleThread() {
		LazyMode lazyMode1 = LazyMode.newInstance();
		System.out.println(lazyMode1.hashCode());
		LazyMode lazyMode2 = LazyMode.newInstance();
		System.out.println(lazyMode2.hashCode());
		LazyMode lazyMode3 = LazyMode.newInstance();
		System.out.println(lazyMode3.hashCode());
		System.out.println(lazyMode1 == lazyMode2 ? (lazyMode2 == lazyMode3 ? true : false) : false);
	}

}

/**
 * @author Admin test multiThread lazy mode thread safety
 *
 */
class TestMultithread extends Thread {

	@Override
	public void run() {

		LazyMode lazyMode = LazyMode.newInstance();
		System.out.println(lazyMode.hashCode());
	}

}
