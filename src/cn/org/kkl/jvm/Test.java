package cn.org.kkl.jvm;


public class Test {
	
	public static void main(String[] args) {
		A a= new A();
		System.out.println(A.width);
		System.out.println(a.getName());
	}
}

class A{
	public static int width=100;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	static {
		System.out.println("类A的静态块");
		width=300;
	}
	
	public A() {
		this.name="a";
		System.out.println("A的无参构造器，创建A类对象");
	}
}
