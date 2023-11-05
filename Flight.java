package zoho;

import java.util.*;
import java.util.List;

public class Flight {

	//ID FOR FLIGHT WHICH IS ALLOTED EVERYTIME A NEW FLIGHT OBJECT IS CREATED
	static int flight_id=1;
	//FLIGHT ID AND TOTAL CAPACITY AND TICKET PRICES
	int id, capacity, price;
	//UNIQUE CUSTOMER ID 
	int customer_id;
	List<String> list;
	List<Integer> seat_list;
	
	    //< ID , AMOUNT>
	Map<Integer,Integer> amount_map;
	
	public Flight()
	{
		id=flight_id++;
		capacity=50;
		price=5000;
		customer_id=1;
		amount_map=new HashMap<>();
		seat_list=new ArrayList<>();
		list=new ArrayList<>();
		
		seat_list.add(0);
		list.add("");
	}
	
	public void confirmBooking(int seats)
	{
		capacity-=seats;
		int amount=seats*price;
		amount_map.put(customer_id,amount);
		seat_list.add(seats);
		//UPDATING PRICE
		price=seats*200+price;
		list.add("customer id => "+customer_id+"  seats => "+seats+"  amount => "+amount);
		System.out.println("Ticket booked successfully. Your Customer id "+customer_id+" and total price "+amount+"\n");
		customer_id++;
	}
	
	public void cancel(int c_id)
	{
		list.remove(c_id);
	    int refund_amount=amount_map.getOrDefault(c_id,0);
	    //REMOVING THE CUSTOMER AMOUNT IN MAP
	    amount_map.remove(c_id);
	    int cancel_seats=seat_list.remove(c_id);
	    price-=cancel_seats*200;
	    capacity+=cancel_seats;
	    System.out.println("Ticket canceled successfully \nYour Refund amount : "+refund_amount+"\n");
//	    System.out.println("Current ticket pricce :"+price);
	}
	
	//FUNCTION TO PRINT FLIGHT PASSENGER DETAILS 
	public void print()
	{
		System.out.println("Flight "+id+" =>");
		if(list.size()==1)
			System.out.println("*** No Records To Display ***");
		else
			for(String str:list)
				System.out.println(str);
		
		System.out.println();
	}
	
	public String toString()
	{
		return "flight id is "+ this.id + " has "+capacity+" seats";
	}
}
