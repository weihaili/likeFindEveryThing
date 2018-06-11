package cn.org.kkl.reflect.bean;

public class User {
	
	private String name;
	
	private String password;
	
	private int age;
	
	private char gender;
	
	private double salary;
	
	private boolean isAdult;
	
	public User() {
		super();
	}

	public User(String name, String password, int age, char gender, double salary, boolean isAdult) {
		this();
		this.name = name;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.salary = salary;
		this.isAdult = isAdult;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isAdult() {
		return isAdult;
	}

	public void setAdult(boolean isAdult) {
		this.isAdult = isAdult;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", password=" + password + ", age=" + age + ", gender=" + gender + ", salary="
				+ salary + ", isAdult=" + isAdult + "]";
	}
	
	
	

}
