package com.TNSIF.Day2;

public class IncrementDecrement {

	public static void main(String[] args) {
		
		int a = 10;
		int b = 20;
		
		System.out.println("A and B value before the operator : "+a +" " +b);
		
		++a;
		
		int c = (++a) + (b) +(a--);
		System.out.println("c value after the operator : " + c);
	}

}
