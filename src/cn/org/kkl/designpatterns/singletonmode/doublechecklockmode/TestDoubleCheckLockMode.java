package cn.org.kkl.designpatterns.singletonmode.doublechecklockmode;

public class TestDoubleCheckLockMode {
	
	public static void main(String[] args) {
		TestDoubleCheckLockMode t=new TestDoubleCheckLockMode();
		t.testSingleThreadSafety();
	}

	/**
	 *test single thread safety 
	 */
	private void testSingleThreadSafety() {
		DoubleCheckLockMode d1 = DoubleCheckLockMode.newInstance();
		System.out.println(d1.hashCode());
		DoubleCheckLockMode d2 = DoubleCheckLockMode.newInstance();
		System.out.println(d2.hashCode());
		DoubleCheckLockMode d3=DoubleCheckLockMode.newInstance();
		System.out.println(d3.hashCode());
		System.out.println(d1==d2?(d2==d3?true:false):false);
	}

}
