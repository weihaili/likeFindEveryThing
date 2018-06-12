package cn.org.kkl.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import cn.org.kkl.reflect.bean.User;

/**
 * 通过反射获取泛型信息
 * @author Admin
 *
 */
public class Test05 {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Test05.class.newInstance().test01Test();
		//Test05.class.newInstance().test02Test();
	}
	
	
	
	/**
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * 获取方法参数和参数的泛型
	 * 1.通过反射API 获取到特定方法method
	 * 2.使用反射API method.getGenericParameterTypes()获取到参数列表
	 * 3.判断参数列表中的参数是否具有泛型特征，若存在，则继续
	 * 4.使用反射API 调用parameter.getActualTypeArguments()获取到泛型参数中的实际泛型类型
	 */
	private void test01Test() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			Method mtest01=Test05.class.getMethod("test01", Map.class,List.class,int.class);
			Test05 t=Test05.class.newInstance();
			Type[] types=mtest01.getGenericParameterTypes();//获取参数列表
			for (Type type : types) {
				System.out.println("***parameter list*** "+type);
				if(type instanceof ParameterizedType) {//判断该参数是否具有泛型特征
					Type[] genericTypes=((ParameterizedType) type).getActualTypeArguments();//获取泛型参数中的实际泛型类型
					for (Type genericType : genericTypes) {
						System.out.println(genericType);
					}
				}
			}
			mtest01.invoke(t, null,null,0);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	

	public void test01(Map<String,User> map,List<User> list,int id) {
		
		System.out.println("Test05.test01(Map<String, User> map,List<User> list,int id)");
	}
	
	/**
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * 获取方法返回参数列表的泛型类型
	 * step:
	 * 1.使用反射API 获取到特定方法method
	 * 2.使用反射API 调用 method.getGenericReturnType()获取到方法的返回值
	 * 3.判断该返回值是否具有泛型特征，若有
	 * 4.使用反射API 调用returnValue.getActualTypeArguments()获取到泛型的实际类型
	 */
	private void test02Test() throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Method mtest02=Test05.class.getMethod("test02");
		Test05 t=Test05.class.newInstance();
		Type returnValueType=mtest02.getGenericReturnType();//获取返回参数列表
		System.out.println(returnValueType);
		if(returnValueType instanceof ParameterizedType) {//参数是否有泛型特征
			Type[] generTypes=((ParameterizedType) returnValueType).getActualTypeArguments();//获取实际的泛型类型
			for (Type type : generTypes) {
				System.out.println(type);
			}
		}
		mtest02.invoke(t);
	}
	
	public Map<Integer, User> test02() {
		System.out.println("Test05.test02()");
		return null;
	}

}
