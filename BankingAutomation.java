package zoho;

import java.util.*;

public class BankingAutomation {
	
	public static Scanner in=new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		
		FileHandler fileHandler=new FileHandler();
		try {
			fileHandler.initialize();
		}
		catch(Exception exp){
			exp.printStackTrace();
		}
		
		Bank bank=new Bank();
		boolean loop=true;
		
		while(loop)
		{
			System.out.println("Enter 1-> Customer Details  2-> Create New Account");
			System.out.println("      3-> Financial Transactions  4->Transaction history  ");
			int choice=in.nextInt();	
			
			switch(choice)
			{
				case 1:
					bank.printCustomerDetails();
					break;
				case 2:
				{
					System.out.println("-------Createing New Account-------");
					System.out.println("Enter your name");
					String name=in.next();
					
					System.out.println("Enter password(No special characters)");
					String password=in.next();
					if(!pwdIsValid(password))//CHECK IF PASSWORD IS VALID OR NOT
						throw new PasswordException("Failed : Invalid Password");
					
					System.out.println("Re-enter password");
					if(!password.equals(in.next()))//USER HANDELED EXCEPTION
						throw new PasswordException("Failed : Mismatch Password");
					
					bank.addCustomer(name,password);
					break;
				}
				case 3:
				{
					System.out.println("Enter 1-> Doposit  2-> Withdrawel 3-> Fund Transfer");
					int choice2=in.nextInt();
					
					System.out.println("Enter your customer id");
					int id=in.nextInt();
					System.out.println("Enter password");
					String password=in.next();
					//CHECK CUSTOMER IS A VALID ONE
					if(!bank.authentication(id,password))
						break;
					
					switch(choice2)
					{
						case 1:
						{	
							System.out.println("-------Cash Deposit Section---------");
							
							System.out.print("Amount:");
							int amount=in.nextInt();
							bank.deposit(id,amount);
							System.out.println("Success!!! \n");
							break;
						}
						case 2:
						{
							System.out.println("-------Cash Withdrawel Section-------");
							
							System.out.print("Amount:");
							int amount=in.nextInt();
							bank.withdrawel(id,amount);
							System.out.println("Success!!! \n");
							break;
						}
						case 3:
						{
							System.out.println("---------Fund Transfer Section---------");
							System.out.println("Enter receiver's customer id");
							int to_id=in.nextInt();
							
							System.out.print("Amount:");
							int amount=in.nextInt();
							bank.transfer(id,to_id,amount);
							break;
						}
					 }
					break;
				}
				case 4:
				{
					System.out.println("Enter your customer id");
					int id=in.nextInt();
					System.out.println("Enter password");
					String password=in.next();
					//CHECK CUSTOMER IS A VALID ONE
					if(!bank.authentication(id,password))
						break;
					
					bank.printTransaction(id);
					break;
				}
				default:
					System.out.println("Good Bye!!! See you again...");
					loop=false;
			}
		}
		
	}
	
	public static boolean pwdIsValid(String password)
	{
		//a - 97   to   z - 122
		//A - 65   to   Z - 91
		//0 - 48   to   9 - 57
		for(char ch:password.toCharArray())
		{
			if( !((ch>=65 && ch<=91) || (ch>=97 && ch<=122) || (ch>=48 && ch<=57)) )
				return false;
		}
		
		return true;
	}
}

//USERD DEFINED EXCEPTION 
class PasswordException extends Exception
{
	PasswordException(String str)
	{
		super(str);
		return;
	}
}

