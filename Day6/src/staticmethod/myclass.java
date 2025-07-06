package staticmethod;

public class myclass {

	private int section;   //non static varible or instance varible
		
	private static int srno; // static or class variable 
		
		
	//static block
	static 
	{
		System.out.println("------Within Static block-----");
		srno=1000;
	//	section=101;
	}
	//defalt constructor 
	public myclass() {
			
	System.out.println("----Within DFefault constructor ----");
	srno++;
	section++;
	}
	@Override
	public String toString() {
		return "MyClass [serial No "+srno+",section=" + section + "]";
	}
		
	//static method 
	static void display()
	{
		//System.out.println("Section :"+ section);
			
		System.out.println("Serial no :"+srno);
			
			
	}
		
		
}