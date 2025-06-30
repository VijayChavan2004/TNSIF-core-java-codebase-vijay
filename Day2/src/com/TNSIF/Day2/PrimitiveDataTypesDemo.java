package com.TNSIF.Day2;

public class PrimitiveDataTypesDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//byte takes 1 byte
		//1 bytes=8 bits
		//256/2
		//128
		
		byte byteMax=127;
		byte byteMin=-128;
		
		System.out.println("Min range of my byte is : "+ byteMin + 
				"Max range of my byte is : "+ byteMax);
		
		//short-----2 byte
		short shortMin = -23456;
		short shortMax = 23456;
		System.out.print("Min range of short is :"+ shortMin + 
				"Max range of my short is :"+ shortMax);
		
		//int-------4 byte
		int i1 = 350;
		System.out.println(i1);
		
		
		//long-------8 byte
		long l1 = 56746376476373L;
		System.out.println(l1);
		
		
		float f=5666777;
		System.out.println(f);
		double d=4566789643.28;
		System.out.println(d);
		
		boolean flag1=false;
		boolean flag2=true;
		System.out.println(flag1);
		System.out.println(flag2);
	}

}
