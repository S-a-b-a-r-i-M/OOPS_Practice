package zoho;

import java.io.*;
import java.util.*;

public class FileHandler {

	public static final String filename="C:\\Javaprogram\\Customer_Details.txt";
	
	//CREATING A LIST WHICH IS GOING TO STORE CUSTOMER OBJECT 
	private static Map<Integer,BankCustomer> customer_list=new HashMap<>();
	
	public static void initialize() throws IOException
	{
		//BUFFERDREADER IS A CLASS USED TO READ LINE BY LINE FROM FILES AND ETC... 
		BufferedReader reader=new BufferedReader(new FileReader(filename));
		
		String customer_info=reader.readLine();
		if(customer_info==null)
		{
			System.out.println("No data");
			return;
		}
		
		do
		{
			String[] cus=customer_info.split("\t");	//SLIPT THE CUSTOMER DETAILS
			
			//ADDING CUSTOMER OBJECT INTO LIST
			int id=Integer.parseInt(cus[0]);
			long account_num=Long.parseLong(cus[1]);
			int balance=Integer.parseInt(cus[3]);
			customer_list.put(id,new BankCustomer(account_num, cus[2],balance,cus[4]));
			
			customer_info=reader.readLine();//READING NEXT LINE OF RECORD
		}while(customer_info!=null);
		
		reader.close();
	}
	
	
	//WRITING THE NEW CUSTOMER DETAILS INTO THE CUSTOMWR DETAILS FILE
	public static void addCustomerToFile(BankCustomer customer) throws Exception
	{
		BufferedWriter writer=new BufferedWriter(new FileWriter(filename,true));
		
		writer.append(customer.toString());
		
		writer.close();
	}

	
	public static Map<Integer,BankCustomer> getCustomerList()
	{
		return customer_list;
	}
}
