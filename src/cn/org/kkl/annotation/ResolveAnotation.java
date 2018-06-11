package cn.org.kkl.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射读取注解附加信息，模拟处理注解的流程
 * @author Admin
 *
 */
@SuppressWarnings(value="all")
public class ResolveAnotation {

	public static void main(String[] args) {
		ResolveAnotation resolveAnnotaion = new ResolveAnotation();
		resolveAnnotaion.resolveAnnotation();
	}
	
	private void resolveAnnotation() {
		try {
			Class clzz=Class.forName("cn.org.kkl.annotation.Student");
			KklTable table=(KklTable) clzz.getAnnotation(KklTable.class);
			System.out.println(table.value());
			Field id=clzz.getDeclaredField("id");
			Annotation[] id_annotations=id.getAnnotations();
			for (Annotation annotation : id_annotations) {
				System.out.println(id_annotations.length+":"+annotation.annotationType());
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
