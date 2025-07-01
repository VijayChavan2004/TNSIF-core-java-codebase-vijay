package com.tnsif.day3;

public class Nestedforloop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int beg=5;
		int end=20;
		for (int i=beg;i<=end;i++) //outer loop
		{
			for (int j = 1; j<=10; j++) //inner loop
			{
				System.out.println(i+"*"+ j+ "="+i*j);
			}
			System.out.println();
		}

	}

}
