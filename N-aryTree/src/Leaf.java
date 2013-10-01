
public class Leaf {

	private String msg;
	
	public String getMessagge()
	{
		return msg;
	}
	
	public Leaf(String mess)
	{
		msg = mess;
	}
	
	public String toString()
	{
		return "\"msg\" : " + "\"" + msg +"\"";
	}
}
