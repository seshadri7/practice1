package com.mobile.app.pages.tools;

public class TestQA {

	int a, b;

	public TestQA(int x, int y) {

		a = x;
		b = y;

	}

	public void printV() {

		System.out.println(a + b);
	}

	public static void main(String[] args) {

		new TestQA(10, 11).printV();

	}

}
