package com.tnsif.day3;

public class Switch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int userinput=1;
		switch(userinput)
		{
		case 1:System.out.println("current recharge details ");
		break;
		
		case 2:System.out.println("current recharge expiry ");
		break;
		
		case 3:System.out.println("new offer ");
		break;
		
		case 4:System.out.println("talk to our customer support person ");
		break;
		default:
			System.out.println("enter a valid option ");
		}

	}

}
