package com.exercise;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class RpnExercise {

	static Map<String, String> cache = new ConcurrentHashMap<>();

	private static void evoe(String str) {
		String[] strArr = str.split(" ");
		String charStr = "+-*/clearundosqrt";
		Stack<String> stack = new Stack<String>();
		// 2.0遍历数组中的每一个元素

		for (String s : strArr) {

			if (s != null && !("").equals(s.trim())) {
				if (!charStr.contains(s)) {// 如果是数字,放入栈中
					stack.push(s);
				} else if (("sqrt").equals(s)) {
					int c = Integer.valueOf(stack.pop());
					stack.push(String.valueOf(Math.sqrt(c)));
				} else if (("clear").equals(s)) {
					stack = new Stack<String>();
				} else if (("undo").equals(s)) {
					stack.pop();
				} else {
					int a = Integer.valueOf(stack.pop());
					int b = Integer.valueOf(stack.pop());
					switch (s) {
					case "+":
						stack.push(String.valueOf(a + b));
						break;
					case "-":
						stack.push(String.valueOf(b - a));
						break;
					case "*":
						stack.push(String.valueOf(a * b));
						break;
					case "/":
						stack.push(String.valueOf(b / a));
						break;
					}
				}
			}

		}

		String result = "";
		for (String s : stack) {
			result += s + " ";
		}
		cache.put("resultStr", result);
		System.out.println("stack:" + result);
	}

	private static Stack deal(String s) {
		Stack<String> stack = new Stack<String>();
		String[] strArr = s.split(" ");
		for (String a : strArr) {
			if (a != null && !("").equals(a.trim())) {
				stack.push(a);
			}
		}
		return stack;

	}

	/**
	 * 执行说明：<br>
	 * 以#为结束
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 1.0创建数组
		Scanner sc = new Scanner(System.in);
		String str = null;
		while (!(str = sc.nextLine()).equals("#")) {// 当输入的数据是exit退出输入
			try {
				String recordStr = cache.get("recordStr");
				if (recordStr != null && !("").equals(recordStr.trim())) {
					recordStr += " " + str;
				} else {
					recordStr = str;
				}
				cache.put("recordStr", recordStr);
				evoe(recordStr);

			} catch (Exception e) {
				System.out.println("error!");
			}
		}

	}
}
