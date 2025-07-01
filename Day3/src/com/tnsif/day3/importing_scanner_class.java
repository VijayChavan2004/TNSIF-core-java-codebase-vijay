package com.tnsif.day3;

import java.util.Scanner;

public class importing_scanner_class {
	public static void main(String[] args) {
		//scanner object to accept user input
		Scanner ob=new Scanner(System.in);
		
		String name;
		System.out.println("enter person name : ");
		name=ob.next();
		System.out.println("enter age : ");
		int age =ob.nextInt();
		System.out.println("enter gender : ");
		String gender=ob.next();
		System.out.println("enter mobile number : ");
		long mobilenumber=ob.nextLong();
		System.out.println("enter income : ");
		int income=ob.nextInt();
		
		Person person=new Person();
		person.setName(name);
		person.setAge(age);
		person.setGender(gender);
		person.setIncome(income);
		person.setMobilenumber(mobilenumber);
		
		System.out.println(person.getName() );
		System.out.println(person.getAge());
		System.out.println(person.getGender());
		System.out.println(person.getIncome());
		System.out.println(person.getMobilenumber());
		System.out.println(person.getTax());
		
		//display person details using toString() method 
		System.out.println(person);	
		System.out.println("After creating tax object ");
		taxcalculation calc=new taxcalculation();
		calc.calculatetax(person);
		System.out.println("After Calculation of tax :");
				
		System.out.println(person );
	}

}