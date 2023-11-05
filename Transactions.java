package zoho;

public class Transactions {
	
	public static int trans_id=1;
	
	private int id;
	private String type;
	private long amount;
	private long balance;
	
	Transactions(){	}

	Transactions(String type,long amount,long balance)
	{
		this.id=trans_id++;
		this.type=type;
		this.amount=amount;
		this.balance=balance;
	}
}
