package zoho;

import java.util.*;

public class Bus{
	
	static int bus_id=1;
	
	private int id;
	//COUNT OF HOW MANY SEATS IN A BUS
	private int capacity;
	private int price;
	private List<Integer> seat_numbers_list;
	public Map<Integer,String> trip_details;
	public Map<Integer,Customer> map;
	
	Bus(){	}
	
	Bus(int capacity,int price)
	{
		this.id=bus_id++;
		this.capacity=capacity;
		this.price=price;
		//CREATING A LIST TO ALLOCATE UNIQUE SEAT NUMBER FOR EVERY PASSENGER 
		seat_numbers_list=new ArrayList<>();
		for(int i=1; i<=capacity; ++i)
			seat_numbers_list.add(i);
		
		trip_details=new HashMap<>();
		map=new HashMap<>();
	}
	
	public void bookTicket(Customer customer)
	{
		customer.setPrice(price*customer.num_of_tickets);
		List<Integer> seat_number=customer.seat_number;
		
		for(int i=0; i<customer.num_of_tickets; ++i)
			seat_number.add(seat_numbers_list.remove(0));
			
		String str=String.format("%6s  %10s    %s%10s %5s",customer.id, customer.name,  customer.price, customer.num_of_tickets ,seat_number.toString());
		trip_details.put(customer.id,str);
		
		map.put(customer.id, customer);
		System.out.println("Ticket Booked Successfully!!!");
		System.out.println("Your id number is:"+customer.id+" and price is:"+customer.price+"\n");
	}
	
	public void cancel(int cus_id)
	{
		Customer customer=map.get(cus_id);
		int refund=customer.price;
		
		for(int seat_number:customer.seat_number)
		{
			seat_numbers_list.add(seat_number);
		}
		
		System.out.println("Your Tickets are cancelled");
		
		trip_details.remove(cus_id);
		map.remove(cus_id);
	}
	
	
	
	public int getId(){
		return this.id;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getSeat_numbers_size() {
		return seat_numbers_list.size();
	}

	public void setSeat_numbers(List<Integer> seat_numbers) {
		this.seat_numbers_list = seat_numbers;
	}

	public Map<Integer,String> getTrip_details() {
		return trip_details;
	}
	
	public String toString()
	{
		return "Bus"+this.id+" has "+this.seat_numbers_list.size()+"-seats"+" Ticket price is "+this.price ;
	}
}
