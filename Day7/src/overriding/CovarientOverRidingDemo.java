package overriding;

//Parent class
class Colour{

	protected Colour getColour()
	{
		Colour s=new Colour();
		return s;
	}
}
//child class
class Red extends Colour{
	
	@Override
	protected Red getColour()
	{
		Red s=new Red();
		return s;
	}
	
	
}
//child class
class Blue extends Colour{
	
	@Override
	protected Blue getColour()
	{
		Blue s=new Blue();
		return s;
	}
	
	
}



public class CovarientOverRidingDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
