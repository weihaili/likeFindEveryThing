package cn.org.kkl.annotation;


public class SelfDefinationTest {

	public static void main(String[] args) {

	}
	
	@Kkl(age=18,name="kkl",schools= {"xd","hd"},id=1001)
	public void test01() {
		
	}
	
	@Kkl02("zhang")
	public void test02() {
		
	}

}
