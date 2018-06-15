package cn.org.kkl.designpatterns.singletonmode.staticinnerclass;

public class TestStaticInnerClassMode {
	
	public static void main(String[] args) {
		TestStaticInnerClassMode t=new TestStaticInnerClassMode();
		t.testSingleThreadSafety();
		t.testMultiThreadSafety();
	}

	private void testMultiThreadSafety() {
		for (int i = 0; i <1000; i++) {
			TestMultiThread t=new TestMultiThread();
			t.start();
			System.out.println(t.getId()+t.getName());
		}
		
	}

	private void testSingleThreadSafety() {
		
		StaticInnerClassMode s1 = StaticInnerClassMode.newInstance();
		System.out.println(s1.hashCode());
		StaticInnerClassMode s2 = StaticInnerClassMode.newInstance();
		System.out.println(s2.hashCode());
		StaticInnerClassMode s3 = StaticInnerClassMode.newInstance();
		System.out.println(s3.hashCode());
		System.out.println(s1==s2?(s2==s3?true:false):false);
		
	}
}

class TestMultiThread extends Thread{

	public void run() {
		StaticInnerClassMode s = StaticInnerClassMode.newInstance();
		System.out.println(s.hashCode());
	}
	
}
