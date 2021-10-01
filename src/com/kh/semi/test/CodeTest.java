package com.kh.semi.test;

public class CodeTest {

	public static void main(String[] args) {

		String questionMark = "(";
		for (int i = 0; i < 5; i++) {
			if(i == 4) {
				questionMark += "?)";
			}else {
				questionMark += "?,";
			}

		}
		System.out.println(questionMark);
	}
}