import java.util.ArrayList;
import java.util.Stack;

public class sequence 
{
	private class position
	{ 
		private Stack<Account> stack; 
		private int index; 
		
		//constructors
		public position() 
		{ 
			this.stack=new Stack<Account>(); 
			this.index=0;
		} 
		
		public position(int index, Account acc) 
		{
			this.index=index; 
			this.stack=new Stack<Account>(); 
			stack.push(acc);
		} 
		
		//muatators 
		public int getIndex() 
		{ 
			return index;
		} 
		public void setIndex(int index) 
		{ 
			this.index=index;
		} 
		
		public Stack<Account> getStack() 
		{ 
			return stack;
		} 
		public void setStack(Stack<Account> newStack) 
		{ 
			this.stack=newStack;
		} 	
	}
	
	
	
	
	
	
	
	
	
	private int size;  
	//private int tail;
	private int elementsNum;
	//private int currentIndex;
	private ArrayList<position> Arr;
	public sequence() 
	{  
		//currentIndex=0; 
		size=0;
		Arr= new ArrayList<position>(); ;
	}  
	//add first method
	public void add(Account account) 
	{ 
		for(int i=0; i<size; i++) 
		{ 
				//if already in array, push into its stack
				if((Arr.get(i).getStack().peek().getVIN()).equals(account.getVIN())) 
				{ 
					Arr.get(i).getStack().push(account); 
					break;
				} 
				//if not in array, make new entry for it
				else if(!(Arr.get(i).getStack().peek().getVIN()).equals(account.getVIN()) && i==size-1) 
				{
					position added=new position(size, account);  
					Arr.add(added);
					//currentIndex++; 
					size++;
				}
		}
	} 
	
	//addIndex  
	//don't think this method is necessary for assignment 
	/**
	public void addIndex(int ind, Account account) 
	{ 
		position added=new position(ind, account);  
		Arr.add(ind, added); 
		size++; 
		//update indexes of position node
		updateIndex();
	}
	*/ 
	
	//resizeArray and updates index
	public void resize() 
	{ 
		Arr.trimToSize(); 
		updateIndex();
	} 
	
	//remove method 
	public void removeVIN(String VIN) 
	{ 
		for (int i=0; i<size; i++) 
		{ 
			if(size==0 || (!VIN.equals(Arr.get(i).getStack().peek().getVIN()) && i==size-1)) 
			{ 
				System.out.println("The Sequence does not contain this VIN"); 
				break;
			}
			else if(VIN.equals(Arr.get(i).getStack().peek().getVIN())) 
			{ 
				Arr.remove(i); 
				resize(); 
				size--; 
				System.out.println("Successfully removed " +VIN+" and associated values");
			}
		}
	} 
	
	//update indexes 
	public void updateIndex() 
	{ 
		for (int i=0; i<size; i++) 
		{ 
			if(Arr.get(i).getIndex() != i) 
			{ 
				Arr.get(i).setIndex(i);
			}
		}
	} 
	
	//Get Values 
	//Will be used in CVR for both the getValues method (return all values) 
	//and prevAccids method (return only the accidents not entire account)
	public Stack<Account> getAccount(String VIN) 
	{ 
		for (int i=0; i<size; i++) 
		{ 
			if(size==0) 
			{ 
				System.out.println("The Sequence is empty"); 
				break;
			}
			else if(VIN.equals(Arr.get(i).getStack().peek().getVIN())) 
			{ 
				return Arr.get(i).getStack();
			}
		} 
		return null;
	}  
	//get previous VIN method
	public String preVIN(String VIN) 
	{ 
		for (int i=0; i<size; i++) 
		{ 
			if((Arr.get(i).getStack().peek().getVIN()).equals(VIN)) 
			{ 
				if(i==0) 
				{ 
					return "There is no previous VIN, this is the first one";
				} 
				return Arr.get(i-1).getStack().peek().getVIN();
			} 
		}
		return null;
	} 
	
	//get next VIN method
	public String nextVIN(String VIN) 
	{ 
		for (int i=0; i<size; i++) 
		{ 
			if((Arr.get(i).getStack().peek().getVIN()).equals(VIN)) 
			{ 
				if(i==size-1) 
				{ 
					return "There is no next VIN, this is the last one";
				} 
				return Arr.get(i+1).getStack().peek().getVIN();
			} 
		}
		return null;
	}  
	
	//Lexicographic sort method (will be called every time something is added to the 
	//array 
	public void sort() 
	{ 
		
	}
	
}
