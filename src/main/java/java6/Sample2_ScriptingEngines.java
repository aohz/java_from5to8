package main.java.java6;

import java.util.List;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Sample2_ScriptingEngines {

	public static void main(String[] args) {

		Sample2_ScriptingEngines.listScriptEngineFactories();
		Sample2_ScriptingEngines.runEngine();
		Sample2_ScriptingEngines.runJsCodeFromJava();
		Sample2_ScriptingEngines.runJavaCodeFromJS();
	}

	public static void listScriptEngineFactories() {

		System.out.println("=== ScriptEngineFactories Info ===");

		ScriptEngineManager mgr = new ScriptEngineManager();
		List<ScriptEngineFactory> factories = mgr.getEngineFactories();

		for (ScriptEngineFactory factory : factories) {

			System.out.println("ScriptEngineFactory Info");
			String engName = factory.getEngineName();
			String engVersion = factory.getEngineVersion();
			String langName = factory.getLanguageName();
			String langVersion = factory.getLanguageVersion();
			System.out.printf("\tScript Engine: %s (%s)\n", engName, engVersion);
			List<String> engNames = factory.getNames();

			for (String name : engNames) {
				System.out.printf("\tEngine Alias: %s\n", name);
			}

			System.out.printf("\tLanguage: %s (%s)\n", langName, langVersion);
		}
	}

	public static void runEngine() {

		System.out.println("=== Run JS Engine ===");

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine jsEngine = mgr.getEngineByExtension("js");

		try {
			jsEngine.eval("print('Hello, world!')");
		} catch (ScriptException ex) {
			ex.printStackTrace();
		}
	}

	public static void runJsCodeFromJava() {

		System.out.println("=== Run JS Engine (invoking JS from java)===");

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine jsEngine = mgr.getEngineByExtension("js");

		try {
			jsEngine.eval("function sayHello() {" + "  print('Hello, world!');" + "}");
			Invocable invocableEngine = (Invocable) jsEngine;
			invocableEngine.invokeFunction("sayHello");
		} catch (NoSuchMethodException | ScriptException ex) {
			ex.printStackTrace();
		}
	}

	public static void runJavaCodeFromJS() {

		System.out.println("=== Run JS Engine (invoking Java from Js)===");

		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine jsEngine = mgr.getEngineByExtension("js");

		try {
			jsEngine.eval("var MyJavaClass = Java.type('main.java.java6.Sample2_ScriptingEngines');"
					+ "var result = MyJavaClass.transformToUpperCase('John Doe');" + "print(result);");

		} catch (ScriptException ex) {
			ex.printStackTrace();
		}
	}

	public static String transformToUpperCase(String name) {
		return name.toUpperCase();
	}

}
