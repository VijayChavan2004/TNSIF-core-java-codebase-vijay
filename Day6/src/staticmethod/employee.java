package staticmethod;

public class employee {
	private String name;
	private int id;
	
	//Declare a static companyName with data type String of company name 
	static String companyname ="TNSIF";

	//Declaring two parameter constructor 
	public employee(String name, int id) {
		
		this.name = name;
		this.id = id;
	}

	@Override
	public String toString() {
		return "employee [name=" + name + ", id=" + id + ", "+ "Comapany= "+ companyname+"]";
	}
}
