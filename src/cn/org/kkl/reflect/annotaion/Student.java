package cn.org.kkl.reflect.annotaion;

@KklTable("an_student")
public class Student {
	
	@An_Field(columnName="id",dataType="int",length=5)
	private int id;
	
	@An_Field(columnName="name",dataType="varchar",length=30)
	private String name;
	
	@An_Field(columnName="age",dataType="int",length=3)
	private int age;

	public Student() {
		super();
	}
	
	public Student(int id, String name, int age) {
		this();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@An_method("check")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
