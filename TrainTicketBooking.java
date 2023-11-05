package zoho;
import java.util.*;


public class TrainTicketBooking {
	public static Scanner in=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		boolean loop=true;
		Train train=new Train();
		
		while(loop)
		{
			System.out.println("1.Booking \n2.Cancel \n3.Available Tickets"
								+ " \n4.Booked Tickets \n5.Exit");
			int choice=in.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("------- BOOKING SECTION -------");				
					if(train.WL_list.size()>=1)
					{
						System.out.println("Tickets not available at this time");
						break;
					}
	
					//GETTING DETAILS FROM THE PASSENGER
					System.out.println("Enter your age and berth-preference(U,L & M)");
					int age=in.nextInt();
					String berth=in.next();
					System.out.println("Enter your name:");
					String name=in.next();
					//CREATING PASSENGER OBJECT
					Passenger passenger=new Passenger(name,age,berth);
					//CALLING BOOKING FUNCTION
					train.bookTicket(passenger);
					break;
					
				case 2:
					System.out.println("------- CANCEL SECTION -------");
					System.out.println("Enter your passenger id ");
					int id=in.nextInt();
					
					//IF ID IS AVAILABLE THEN ONLY WE CAN CANCEL THE TICKET
					if(!train.passenger_list.containsKey(id))
						System.out.println("With given id number,No passenger is available in this id number");  
					else
						train.cancel(id);
					break;
					
				case 3:
					System.out.println("------- AVAILABLE TICKET DETAILS -------");
					train.availableTickets();
					break;
					
				case 4:
					System.out.println("------- BOOKED TICKET DETAILS -------");
					train.bookedTickets();
					break;
					
				default:
					loop=false;
					break;
			}
		}
		
		System.out.println("GOOD BYE!!! COME AGAI1N");
	}
	
	
}
