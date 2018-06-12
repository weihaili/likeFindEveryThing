package cn.org.kkl.scriptengine;

import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Test {
	
	public static void main(String[] args) {
		Test t=new Test();
		t.test03();
		
	}
	
	
	/**
	 * 导入java包，使用java中的API 
	 */
	private void test03() {
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine engine=sem.getEngineByName("js");
		String jsCode=" var list=java.util.Arrays.asList([\"mayun\",\"liuqiangdong\",\"leijun\"]);";
		try {
			engine.eval(jsCode);//执行js代码
			
			List<String> list2=(List<String>) engine.get("list");//从上下文中获取到list变量，在java平台使用
			for (String temp : list2) {
				System.out.println(temp);
			}
			
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 定义函数
	 */
	private void test02() {
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine engine=sem.getEngineByName("js");
		
		try {
			engine.eval("function add(a,b){var sum=a+b;return sum;}");
			
			Invocable jsInvoke=(Invocable) engine;//取得调用函数接口
			Object result=jsInvoke.invokeFunction("add", new Object[]{12,20});//调用函数
			System.out.println(result);
			
		} catch (ScriptException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 在java平台使用脚本引擎：
	 * 使用javascript脚本引擎执行js代码后，将结果返回在控制台。
	 * 上下文变量既可以被js操作也可以被java代码操作
	 */
	private void test01() {
		ScriptEngineManager sem=new ScriptEngineManager();
		ScriptEngine engine = sem.getEngineByName("javascript");
		
		
		engine.put("msg", "script engine");
		String str =" var user= {name:'kkl',age:18,schools:['aks','dqwz']};";
		str+="print(user.schools);";
		
		
		try {
			engine.eval(" msg='hello world';");//js 修改上下问信息
			Object obj=engine.eval(str);
			System.out.println(engine.get("msg"));
			
		} catch (ScriptException e) {
			e.printStackTrace();
		}
		
	}

}
