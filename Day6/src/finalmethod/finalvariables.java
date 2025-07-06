package finalmethod;

public class finalvariables {
	
	//final int a;
	final int x=100;
	final static int y;
	final static int z=10;
	
	//instance method(non static method)
	
	void change()
	{
		//x=30;
		//z=50;
		
	}

	@Override
	public String toString() {
		return "finalvariables [x=" + x +",y="+y+ "]";
	}
	
	static
	{
		y=20;
		System.out.println("value of y : "+y);
	}

}
