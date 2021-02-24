import java.util.*;

public class CVR 
{
	//this will be used to generate random alpha numeric numbers
	private final static String alphaNumeric="ABDCEFGHIJKLMNOPQRSTUVWXYZ0123456789"; 
	//key
	private String VIN; 
	
	//threshold (determines which ADT to use)
	private int threshold; 
	
	//length of key
	private int VINLength; 
	
	//this is an object of Archive which will hold the data associated with VIN
	private Account value; 
	
	//TBD
	//private Collection<Account> activeVINS;
	
	//HashMap to store all the key-value pairs 
	//the value come in the form of a stack because, 
	//multiple events can be associated with the same  
	//VIN, and must be shown in reverse-chronological order
	private TreeMap<String, Stack<Account>> treeRecords; 
	private sequence seqRecords;
	
	
	//This will keep track of all VINs and make sure  
	//none of them are repeated
	private HashSet<String> VINRecorder;
	
	
	private boolean hashTabl=false; 
	
	//default constructor
	public CVR(int threshold) throws Exception  
	{
		this.setThreshold(threshold); 
		if (threshold>=1000) 
		{
			treeRecords=new TreeMap<>(); 
			this.hashTabl=true; 
			
		} 
		else 
		{ 
			seqRecords=new sequence();
			this.hashTabl=false; 
		}
	}
	
	//not sure this is even needed
	//parameterized constructor for CVR, takes VIN 
	//and adds it to VINRecorder 
	//re-evaluate this method, with this a VIN is added to HashSet, but not to 
	//HashMap. At the same time I'm not sure We want VINs w/o associated accounts 
	//to be in HashMap. TBD 
	//For now actually, I will add them to HashMap, this may change down the line... 
	/**
	public CVR (String VIN) throws Exception 
	{
		this.VIN=VIN; 
		records=new Hashtable<>();  
		VINRecorder=new HashSet<>(); 
		add(VIN, null);
		//Stack<Account> stack = new Stack<Account>();
		//VINRecorder.add(VIN);
	}  
	**/
	
	//accessors and mutators 
	//VIN getters and setters
	public String getVIN() 
	{ 
		return VIN;
	} 
	public void setVIN(String VIN) 
	{ 
		this.VIN=VIN; 
		VINRecorder=new HashSet<>(); 
		VINRecorder.add(VIN);
	} 
	//threshold getters and setters 
	public int getThreshold() 
	{ 
		return threshold;
	} 
		//for this one we have to keep in mind the restriction set 
		//on us in the instructions
	public void setThreshold(int threshold) throws Exception
	{ 
		if(threshold<100 || threshold>900000) 
		{ 
			//System.out.println("Invalid input for threshold"); 
			throw new Exception("Invalid input for threshold");
		} 
		else 
		{ 
			this.threshold=threshold;
		}
	} 
	//VINLength getters and setters 
	public int getVINLength() 
	{ 
		return VINLength;
	} 
		//again for this one. we need to take the 
		//instructions into account for this special 
		//case 
	public void setVINLength(int VINLength) throws Exception 
	{ 
		if(VINLength<10 || VINLength>17) 
		{ 
			throw new Exception("Invalid input for VIN length"); 	
		} 
		else 
		{ 
			this.VINLength=VINLength;
		}
	} 
	
	
	//Now onto the methods 
	//Generate method 
	//This method should randomly generate a sequence 
	//containing n new non-existing valid keys 
	//***Must determine whether the output is a sequence or not
	public String generate(int size) throws Exception 
	{ 
		
		char[] Arr= alphaNumeric.toCharArray(); 
		String[] ender=new String[size];
		  
		
		//generating random number between 10 and 17 
		Random r= new Random(); 
		int low=10; 
		int high=17; 
		for(int x=0; x<size;x++) 
		{  
			int highLow=r.nextInt(high-low)+10;
			StringBuilder newString=new StringBuilder();
			//making string between length of 10 and 17 randomly
			for(int i=0; i<highLow; i++) 
			{ 
				newString.append(Arr[new Random().nextInt(Arr.length)]); 
			} 
			/////////////////// 
			String newVIN=newString.toString(); 
			//System.out.println(newVIN);  
			
			
			//This must be further explored, I do not know why, 
			//but for some reason it does not work if the first 
			//condition is not there, to be explored
			if(newVIN!=null) 
			{ 
			} 

			//stops here for some reason, must find out why, something is wrong with this statement
			else if(VINRecorder.contains(newVIN)) 
			{  
				x--;
			}  
			else 
			{ 
				ender[x]=newString.toString(); 
			}   
			
			ender[x]=newString.toString();
			
		}   
		//System.out.println("hello");
		System.out.println(Arrays.toString(ender));
		return Arrays.toString(ender);
	}
	
	//method allKeys 
	//this method should return all keys as a sorted 
	//sequence in lexicographic order 
	//the plan here is to use
	/**
	public LinkedList<Account> allKeys()
    {
		
    } 
    **/
	
	//add method 
	//****must check to see if must be resized later
	public void add(String VIN, Account value) throws Exception
	{ 
		
		System.out.println(hashTabl);
		if(hashTabl==true) 
		{	
			
			if(!VIN.equals(value.getVIN())) 
			{  
				System.out.println("Something went wrong :/");
				throw new Exception("VIN does not match account");  
			}  
			else if(treeRecords.containsKey(VIN)) 
			{ 
				System.out.println("VIN exists, adding to record");
				treeRecords.get(VIN).add(value); 
				System.out.println("Success!");
			} 
			else 
			{  
				System.out.println("New account made, record added!");
				Stack<Account> stack = new Stack<Account>(); 
				stack.add(value);
				treeRecords.put(VIN, stack); 
				System.out.println("Success!"); 
				//resize here 
				//
			} 
		}  
		else 
		{ 
			if(value==null) 
			{ 
				Account saveVIN=new Account(VIN); 
				seqRecords.add(saveVIN);
			}
			else 
			{
				seqRecords.add(value); 
			}
		}
	} 
	//remove method 
	//***must check to see if must be resized later 
	public void remove(String VIN) 
	{ 
		if(hashTabl==true) 
		{	
			if(treeRecords.containsKey(VIN)) 
			{ 
				treeRecords.remove(VIN); 
				//resize here 
				//
			} 
			else 
			{ 
				System.out.println("Key does not exist in HashTable");
			} 
		} 
		else 
		{ 
			seqRecords.removeVIN(VIN);
		}
	} 
	
	//getValues method 
	public Stack<Account> getValues(String VIN) 
	{ 
		if(hashTabl == true) 
		{
			if(treeRecords.containsKey(VIN)) 
			{ 
				Stack<Account> values = new Stack<Account>();
				values=treeRecords.get(VIN); 
				return values;
			} 
			else 
			{ 
				System.out.println("This VIN could not be found in directory"); 
				return null;
			} 
		} 
		else 
		{ 
			return seqRecords.getAccount(VIN);
		}
	}  
	
	//nextKey method 
	public String nextVIN(String VIN) 
	{ 
		//unfinished, not sure what to call here
		if(hashTabl==true) 
		{ 
			return treeRecords.higherKey(VIN);
		} 
		else 
		{ 
			return seqRecords.nextVIN(VIN);
		}
	}
	
	//prevKey methods 
		public String prevVIN(String VIN) 
		{ 
			//unfinished, not sure what to call here
			if(hashTabl==true) 
			{ 
				return treeRecords.lowerKey(VIN);
			} 
			else 
			{ 
				return seqRecords.nextVIN(VIN);
			}
		}
	
	//previous Accidents method 
	public Stack<Account> prevAccids(String VIN)
	{ 
		if(hashTabl == true) 
		{ 
			if(treeRecords.containsKey(VIN)) 
			{ 
				Stack<String> Accids= new Stack<String>(); 
				Stack<Account> temp; //=  new Stack<Account>(); 
				temp=treeRecords.get(VIN);  
				return temp;
				/**
				String tempString;
				while(!temp.isEmpty()) 
				{ 
					tempString=temp.pop().getAccids(); 
					Accids.push(tempString);
				} 
				temp=null;
				return Accids; 
				**/
			} 
			return null;

 		} 
		else 
		{ 
			Stack<Account> temp; 
			temp=seqRecords.getAccount(VIN); 
			if(temp==null || temp.isEmpty()) 
			{ 
				System.out.println("This VIN does not exist in the sequence"); 
				return null;
			} 
			else 
			{ 
				return temp;
			}
		}
	}
	
	
	
	
	//driver method
	public static void main(String[] args) throws Exception 
	{ 
		CVR hello= new CVR(1000); 
		try 
		{
			//System.out.println("hello");
			//hello.generate(5);  
			Account abdcg=new Account("adsj4jandnj4", "Muhammad Ferreira", "perfect record");  
			Account abdcg1=new Account("adsj4jandnj4","Myriam Ferreira", "Fender Bender"); 
			Account abdcg2= new Account("adsj4jandnj4", null, null); 
			Account abdcg3 = new Account("adsj3jandnj4");
			/////
			hello.add("adsj4jandnj4", abdcg); 
			hello.add("adsj4jandnj4", abdcg1); 
			hello.add("adsj4jandnj4", abdcg2); 
			hello.add("adsj3jandnj4", abdcg3);
			System.out.println(hello.nextVIN("adsj4jandnj4"));
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
