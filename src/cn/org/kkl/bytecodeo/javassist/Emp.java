package cn.org.kkl.bytecodeo.javassist;

public class Emp {
	
	private int empno;
	
	private String ename;

	public Emp() {
		super();
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
