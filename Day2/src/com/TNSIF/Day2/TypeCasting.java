package com.TNSIF.Day2;

public class TypeCasting {

	public static void main(String[] args) {
		//widening  //implicit type casting
		
		byte b = 10;
		int i = b;
		System.out.println(i);
		
		float f = 22.34f;
		double d=f;
		System.out.println(d);
		
		double d1 = 10.39f;
		long l1 = (long) d1;
		System.out.println(l1);
		
		long l2 = 5462736253L;
		int i1 = (int) l2;
		System.out.println(i1);
		
		

	}

}
