package dao;

public class Count {

	private static int count = 1;
	private static int count1 = 1;
	private static int orderitemc =1;
	
	public static synchronized int getCount(){
	
		return count++; 
	}
	public static synchronized int getCount1(){
		
		return count1++; 
	}
public static synchronized int getOrderitemc (){
		
		return orderitemc ++; 
	}
}
