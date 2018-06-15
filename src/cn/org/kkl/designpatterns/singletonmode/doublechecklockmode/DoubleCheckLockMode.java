package cn.org.kkl.designpatterns.singletonmode.doublechecklockmode;

public class DoubleCheckLockMode {
	
	private static DoubleCheckLockMode instance;
	
	private DoubleCheckLockMode() {
	}
	
	public static DoubleCheckLockMode newInstance() {
		if(instance==null) {
			DoubleCheckLockMode dclm;
			synchronized (DoubleCheckLockMode.class) {
				dclm=instance;
				if (dclm == null) {
					synchronized (DoubleCheckLockMode.class) {
						if (dclm == null) {
							dclm=new DoubleCheckLockMode();
						}
					}
					instance=dclm;
				}
			}
		}
		return instance;
	}

}
