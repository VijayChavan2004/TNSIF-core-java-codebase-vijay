package com.tnsif.day3;

public class taxcalculation {
	//function to calculate tax
	public void calculatetax(Person a)
	{
		if(a.getAge()>65 || a.getGender().equalsIgnoreCase("female")) //FEMALE Female female FeMale all have different unicodes
		{
			a.setTax(0);
			System.out.println("Tax is not applicable ");
		}
		else {
			if(a.getIncome()<=160000)
			{
				a.setTax(0);
				System.out.println("Tax is not applicable ");
			}
			else if(a.getIncome() > 160000 && a.getIncome()<=500000)
			{
				a.setTax((a.getIncome()-160000)*10/100);
			}
			else if(a.getIncome() > 500000 && a.getIncome()<=800000)
			{
				a.setTax((a.getIncome()-500000)*20/100 +  34000);
			}
			else {
				a.setTax((a.getIncome()-800000)*30/100 + 94000);
			}
		}
	}

}