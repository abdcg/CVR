//this method is similar to a node, contains 
//VIN, Owner, Accidents details
public class Account 
{
	private String VIN; 
	private String owner; 
	private String accidents; 
	
	public Account() {}; 
	public Account(String VIN) 
	{ 
		this.VIN=VIN; 
		this.owner=null; 
		this.accidents=null;
	}
	
	public Account(String VIN, String owner, String accidents) 
	{ 
		this.VIN=VIN; 
		this.owner=owner; 
		this.accidents=accidents;
	}  
	//mutators
	public void setVIN(String VIN) 
	{ 
		this.VIN=VIN;
	} 
	public String getVIN() 
	{ 
		return VIN;
	} 
	
	public void setOwner(String owner) 
	{ 
		this.owner=owner;
	} 
	public String getOwner() 
	{ 
		return owner;
	} 
	
	public void setAccids(String accidents) 
	{ 
		this.accidents=accidents;
	} 
	public String getAccids() 
	{ 
		return accidents;
	}
	
}
