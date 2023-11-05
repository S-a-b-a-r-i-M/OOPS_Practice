package zoho;

import java.util.*;

public class BusReservation {

	public static Scanner in=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//CREATING LIST TO STORE BUS OBJECTS
		List<Bus> busses=new ArrayList<>();
		//2 IS EXAMPLE WE CAN CREATE 'n' NUMBER OF BUSES
		busses.add(new Bus(10,100));
		busses.add(new Bus(5,50));
		
		boolean loop=true;
		while(loop) 
		{
			System.out.println("Enter 1->Booking  2->Cancel  3->Print Details  4->Exit");
			int choice=in.nextInt();
			switch(choice)
			{
				case 1:
				{
					System.out.println("-------- Booking Section-------");
					
					System.out.println("Enter your name & bus number(id)");
					String name=in.next();
					int bus_id=in.nextInt();
					if(bus_id>busses.size())
					{
						System.out.println("Bus number is wrong...");
						break;
					}
					Bus bus=busses.get(bus_id-1);
					System.out.println(bus);
					
					System.out.println("Enter the number of tickets you want");
					int num_of_tickets=in.nextInt();
					if(num_of_tickets > bus.getSeat_numbers_size() || bus.getSeat_numbers_size()==0)
					{
						System.out.println("Not Enough Tickets");
						break;
					}
					
					Customer customer=new Customer(name,num_of_tickets);
					bus.bookTicket(customer);
					break;
			    }
				case 2:
				{
					System.out.println("------ Ticket Cancellation -----");
					
					System.out.println("Enter the bus id");
					int bus_id=in.nextInt();
					if(bus_id>busses.size() || bus_id<1)
					{
						System.out.println("Bus number is wrong...");
						break;
					}
					Bus bus=busses.get(bus_id-1);
					
					System.out.println("Enter your customer id");
					int cus_id=in.nextInt();
					if(cus_id<1 || cus_id>Customer.cus_id)
					{
						System.out.println("Invalid customer id!!!!");
						break;
					}
					
					bus.cancel(cus_id);
					break;
				}
				case 3:
					System.out.println("-------- Details Section -------");
					printDetails(busses);
					break;
				default:
					loop=false;
			}
		}
		System.out.println("Good bye!!! See you again");
	}
	
	
	public static void printDetails(List<Bus> busses)
	{
		for(Bus bus : busses)
		{
			System.out.println("Bus id -> "+bus.getId()+" Available seats -> "+ bus.getSeat_numbers_size());
			System.out.println("---------------------------------------------------------");
			Map<Integer,String> trip_details=bus.getTrip_details();
			System.out.printf("%5s %10s  %10s  %10s\n","cus_id","name","price","ticket");
			for(String trip:trip_details.values())
			{
				System.out.println(trip);
			}
			System.out.println();
		}
	}
}

class Customer
{
	public static int cus_id=1;
	
	int id;
	int price;
	String name;
	int num_of_tickets;
	List<Integer> seat_number;
	
	
	Customer(String name,int num_of_tickets)
	{
		this.id=cus_id++;
		this.price=0;
		this.name=name;
		this.num_of_tickets=num_of_tickets;
		this.seat_number=new ArrayList<>();
	}
	
	public void setPrice(int price) {
		this.price=price;
	}
}
