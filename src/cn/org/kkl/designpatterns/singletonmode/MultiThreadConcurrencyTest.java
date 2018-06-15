package cn.org.kkl.designpatterns.singletonmode;

import java.util.concurrent.CountDownLatch;

import cn.org.kkl.designpatterns.singletonmode.doublechecklockmode.DoubleCheckLockMode;
import cn.org.kkl.designpatterns.singletonmode.enummode.EnumMode;
import cn.org.kkl.designpatterns.singletonmode.hungrymode.HungryMode;
import cn.org.kkl.designpatterns.singletonmode.lazymode.LazyMode;
import cn.org.kkl.designpatterns.singletonmode.staticinnerclass.StaticInnerClassMode;

public class MultiThreadConcurrencyTest {
	
	private int times =1000000;
	
	private int threadCount = 10;
	
	final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
	
	public static void main(String[] args) {
		MultiThreadConcurrencyTest mtct=new MultiThreadConcurrencyTest();
		//mtct.testLazyMode();//1101ms
		//mtct.testHungryMode();//911ms
		//mtct.testDoubleCheckLockMode();
		//mtct.testStaticInnerClassMode();
		//mtct.testEnumMode();
		
		
	}
	
	/**
	 * test enumerate mode 
	 * 54ms
	 */
	private void testEnumMode() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < times; j++) {
						EnumMode e=EnumMode.INSTANCE;
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
			long end=System.currentTimeMillis();
			System.out.println("enumerate mode boot 10 threads invoke new instance 1000000 spend time :"+(end-start)+" ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * static inner class mode test
	 * 37ms
	 */
	private void testStaticInnerClassMode() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < times; j++) {
						StaticInnerClassMode.newInstance();
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
			long end=System.currentTimeMillis();
			System.out.println("static inner class mode boot 10 threads invoke new instance 1000000 spend time :"+(end-start)+" ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 *test double check lock mode
	 *28ms 
	 */
	private void testDoubleCheckLockMode() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < times; j++) {
						DoubleCheckLockMode.newInstance();
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
			long end=System.currentTimeMillis();
			System.out.println("double check lock mode boot 10 threads invoke new instance 1000000 spend time :"+(end-start)+" ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *hungry mode multiThread concurrency test 
	 */
	private void testHungryMode() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < times; j++) {
						HungryMode.newInstance();
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
			long end=System.currentTimeMillis();
			System.out.println("hungry mode boot 10 threads invoke new instance 1000000 spend time :"+(end-start)+" ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	/**
	 *lazy mode multiThrad concurrency test 
	 */
	private void testLazyMode() {
		long start=System.currentTimeMillis();
		for (int i = 0; i < threadCount; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < times; j++) {
						LazyMode.newInstance();
					}
					countDownLatch.countDown();
				}
			}).start();
		}
		try {
			countDownLatch.await();
			long end=System.currentTimeMillis();
			System.out.println("laze mode boot 10 threads invoke new instance 1000000 spend time :"+(end-start)+" ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
