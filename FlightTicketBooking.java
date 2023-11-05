package zoho;

import java.util.*;

public class FlightTicketBooking {

	public static Scanner in=new Scanner(System.in);
	static int customer_id=1;
	
	public static void main(String[] args) {
		
		//CREATING 2 FLIGHT OBJECTS
		Flight[] flight=new Flight[2];
		
		for(int i=0; i<2; ++i)
			flight[i]=new Flight();
		
		while(true)
		{
			System.out.println("Enter 1.Booking  2.Cancel  3.Print Details 4.Exit");
			int input=in.nextInt();
			
			switch(input)
			{
				case 1:
					System.out.println("-------Ticket booking section------");
					System.out.println("Enter flight id");
					int flight_id=(in.nextInt())-1;
					if(flight_id<0 && flight_id>=flight.length)
					{
						System.out.println("Flight id is invalid");
						break;
					}
					System.out.println("Ticket Price in flight"+(flight_id+1)+" : "+flight[flight_id].price+"\nAvailabe seats are : "+flight[flight_id].capacity);
					System.out.println("How many seats do you want sir?");
					int seats=in.nextInt();
					bookTicket(flight_id,seats,flight);
					break;
					
				case 2:
					System.out.println("-------Ticket cancel section------");
					System.out.println("Enter your Flight Id and Customer Id");
					flight_id=(in.nextInt())-1;
					if(flight_id<0 && flight_id>=flight.length)
					{
						System.out.println("Flight id is invalid");
						break;
					}
					int cus_id=in.nextInt();
					if(!(0<cus_id && cus_id<flight[flight_id].list.size()))
					{
						System.out.println("Wrong Customer id");
						break;
					}
					
					flight[flight_id].cancel(cus_id);
					break;
					
				case 3:
					System.out.println("--------- Details section -------");
					System.out.println("Enter flight id:");
					int id=(in.nextInt())-1;
					flight[id].print();
					break;
					
				case 4:
					return;
			}
		}
		
	}

	private static void bookTicket(int id, int seats, Flight[] flight) {
		
		if(seats <= flight[id].capacity)
			flight[id].confirmBooking(seats);
		else
			System.out.println("Seats are not available");
			
	}

}
