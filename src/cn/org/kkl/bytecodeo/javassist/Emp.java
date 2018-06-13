package cn.org.kkl.bytecodeo.javassist;

@Author(name="kkl",year=2018)
public class Emp {
	
	private int empno;
	
	private String ename;

	public Emp() {
		super();
	}
	
	public int takeOver(int a,int b) {
		int c=a%b;
		return c;
	}
	
	public Emp(int empno, String ename) {
		this();
		this.empno = empno;
		this.ename = ename;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
}
