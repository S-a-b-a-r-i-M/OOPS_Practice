package zoho;

import java.util.*;

public class Bank {
	
	public static Scanner in=new Scanner(System.in);
	public static int bank_id=1;
	public static final int INITIAL_BALANCE=5_000;
	
	int id;
	Map<Integer,BankCustomer> customer_list;
	BankCustomer customer=null;
	
	public Bank()
	{
		this.id=bank_id++;
		customer_list=FileHandler.getCustomerList();
	}
	
	public void printCustomerDetails()
	{
		System.out.println("\tAccount Holder's Details");
		System.out.println("-----------------------------------------");
		for(BankCustomer customer:customer_list.values())
			System.out.println(customer);
		
		System.out.println();
	}
	
	public void addCustomer(String name,String password) throws Exception
	{
		
		customer=new BankCustomer(name,INITIAL_BALANCE,password);
		customer_list.put(customer.id,customer);
		
		System.out.println("Your Details Added Succesfully!!");
		System.out.println(customer);
		
		//ADDING CUS DETAILS TO FILE
		FileHandler.addCustomerToFile(customer);
	}
	
	public boolean authentication(int id,String password) throws Exception
	{
		customer=customer_list.get(id);
		if(customer==null){
			System.out.println("Customer id is invalid");
			return false;
		}
		
		if(!password.equals(customer.password)) 
			throw new PasswordException("Failde : Password not matched");
		
		return true;
	}

	public void deposit(int id,int amount) 
	{
		customer=customer_list.get(id);
		customer.balance+=amount;
		System.out.println("Current balance : "+customer.balance);
		//WE ARE GOING TO SAVE THIS TRANSACTION INTO TRANSACTION LIST OF EACH CUSTOMER
		customer.addTransaction("Deposit\t"+amount+"\t"+customer.balance);
	}

	public boolean withdrawel(int id,int amount) 
	{
		customer=customer_list.get(id);
		int balance=customer.balance-amount;
		//MINIMUM BALANCE SHOULD BE GREATER THAN 999
		if(balance<1000)
		{
			System.out.println("Insuffucient Balance");
			return false;
		}
		customer.balance=balance;
		System.out.println("Current balance : "+customer.balance);
		//WE ARE GOING TO SAVE THIS TRANSACTION INTO TRANSACTION LIST OF EACH CUSTOMER
		customer.addTransaction("Withdraw\t"+amount+"\t"+customer.balance);
		return true;
	}
	
	public void transfer(int from_id,int to_id,int amount) 
	{
		//WE DON'T KNOW THE TO CUSTOMER(to_id) IS VALID OR NOT
		BankCustomer toCustomer=customer_list.get(to_id);
		if(toCustomer==null)
		{
			System.out.println("Receiver account is not exist!!!");
			return;
		}
		customer=customer_list.get(from_id);
		
		//MAKE WITHDRAWEL FROM FROM_ID ACCOUNT
		if(withdrawel(from_id,amount))
		{
			toCustomer.balance+=amount;//THEN DEPOSIT THE AMOUNT TO(to_id) ACCOUNT
			toCustomer.addTransaction("Deposit\t"+amount+" \t"+customer.balance);
			System.out.println("Success!!! \n");
		}
	}
	
	public void printTransaction(int id)
	{
		customer=customer_list.get(id);
		
		System.out.println("Name : "+customer.name);
		System.out.println("----------TRANSACTION HISTORY------------");
		System.out.println("Id\tType\tAmount\tBalnace");
		for(String str:customer.getTransaction()) {
			System.out.println(str);
		}
		
		System.out.println();
	}
}


class BankCustomer{
	public static int auto_id=1;
	public static long auto_account_num=0001;
	
	int id;
	long account_num;
	String name;
	int balance;
	String password;
	int trans_id;
	//transaction_id  type  amount  balance
	List<String> transactions;
	
	BankCustomer(){}
	
	//CREATING NEW ACCOUNT
	BankCustomer(String name,int balance,String password)
	{
		this.id=auto_id++;
		this.account_num=++auto_account_num;
		this.name=name;
		this.balance=balance;
		this.password=password;
		this.trans_id=1;
		this.transactions=new ArrayList<>();
		this.addTransaction("opning\t"+balance+"\t"+balance);
	}
	
	BankCustomer(long account_num, String name, int balance,String password)
	{
		
		this.id=auto_id++;
		this.account_num=account_num;
		//AUTO_ACCOUNT_NUMBER SAVES THE LAST CUSTOMERS ACCOUNT NUMBER
		auto_account_num=account_num;
		this.name=name;
		this.balance=balance;
		this.password=password;
		this.trans_id=1;
		this.transactions=new ArrayList<>();
		this.addTransaction("opning\t"+balance+"\t"+balance);
	}
	
	public void addTransaction(String str)
	{
		this.transactions.add(trans_id+"\t"+str);
		trans_id++;
	}
	
	public List<String> getTransaction()
	{
		return this.transactions;
	}
	
	public String toString()
	{
		return this.id+"\t"+this.account_num+"\t"+this.name+"\t"+this.balance+"\t"+this.password+"\n";
	}
}



