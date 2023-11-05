package zoho;
import java.util.*;

public class Train {
	//WE CAN DECLARE AS MANY AS TICKETS WE WANT
	static int tot_tickets=6;
	
	static List<Integer> available_lower_berth;
	static List<Integer> available_middle_berth;
	static List<Integer> available_upper_berth;
	//QUEUE IS CREATED TO MAINTAIN ORDERS
	Queue<Integer> RAC_list;
	Queue<Integer> WL_list;
	
	Map<Integer,Passenger> passenger_list;
	
	Train()
	{
//		lower =tot_tickets/3;
//		middle=tot_tickets/3;
//		upper =tot_tickets/3;
		
		available_lower_berth =new ArrayList<>(Arrays.asList(1));
		available_middle_berth=new ArrayList<>(Arrays.asList(1));
		available_upper_berth =new ArrayList<>(Arrays.asList(1));
		RAC_list=new LinkedList<>();
		WL_list=new LinkedList<>();
		
		passenger_list=new HashMap<>();
	}
	
	public void bookTicket(Passenger passenger)
	{
		
		if(passenger.preference.equalsIgnoreCase("U") && available_upper_berth.size()>0)
		{
			int number=available_upper_berth.remove(0);
			passenger.number=number;
			passenger.allocated_berth="U";
			System.out.println("Upper(prefered) berth is given");
			passenger.print();
		}
		else if(passenger.preference.equalsIgnoreCase("M") && available_middle_berth.size()>0)
		{
			int number=available_middle_berth.remove(0);
			passenger.number=number;
			passenger.allocated_berth="M";
			System.out.println("Middle(prefered) berth is given");
			passenger.print();
		}
		else if(passenger.preference.equalsIgnoreCase("L") && available_lower_berth.size()>0)
		{
			int number=available_lower_berth.remove(0);
			passenger.number=number;
			passenger.allocated_berth="L";
			System.out.println("Lower(prefered) berth is given");
			passenger.print();
		}
		else if(available_upper_berth.size()>0)
		{
			int number=available_upper_berth.remove(0);
			passenger.number=number;
			passenger.allocated_berth="U";
			System.out.println("Upper berth is given");
			passenger.print();
		}
		else if(available_middle_berth.size()>0)
		{
			int number=available_middle_berth.remove(0);
			passenger.number=number;
			passenger.allocated_berth="M";
			System.out.println("Middle berth is given");
			passenger.print();
		}
		else if(available_lower_berth.size()>0)
		{
			int number=available_lower_berth.remove(0);
			passenger.number=number;
			passenger.allocated_berth="L";
			System.out.println("Lower berth is given");
			passenger.print();
		}
		else 
			book_to_others(passenger);
			
		passenger_list.put(passenger.id, passenger);
		System.out.println();
		
	}
	
	protected void book_to_others(Passenger passenger)
	{
		if(RAC_list.size()<1)
		{
			RAC_list.add(passenger.id);
			passenger.number=RAC_list.size();
			passenger.allocated_berth="RAC";
			System.out.println("Ticket booked in RAC");
		}
		else
		{
			WL_list.add(passenger.id);
			passenger.number=WL_list.size();
			passenger.allocated_berth="WL";
			System.out.println("Ticket Booked in waiting list");
		}		
	}
	
	
	public void cancel(int id)
	{
			Passenger passenger=passenger_list.get(id);
			//REMOVING THE DETAILS OF A PASSENGER FROM LIST
			passenger_list.remove(id);
			
			System.out.println("Your ticket is cancelled");
			
			if(passenger.allocated_berth.equalsIgnoreCase("U"))
			{
				available_upper_berth.add(passenger.number);
			}
			else if(passenger.allocated_berth.equalsIgnoreCase("M"))
			{
				available_middle_berth.add(passenger.number);
			}
			else if(passenger.allocated_berth.equalsIgnoreCase("L"))
			{
				available_lower_berth.add(passenger.number);
			}
			
			
			if(!RAC_list.isEmpty())
			{
				int pass_id =RAC_list.poll();
				bookTicket(passenger_list.get(pass_id));
				
				if(!WL_list.isEmpty())
				{
					Passenger pass=passenger_list.get(WL_list.poll());
					RAC_list.add(pass.id);
					pass.number=RAC_list.size();
					pass.allocated_berth="RAC";
				}
			}
			
	}
	
	public void availableTickets()
	{
		System.out.println("Available lower berth tickets : "+available_lower_berth.size());
		System.out.println("Available middle berth tickets: "+available_middle_berth.size());
		System.out.println("Available upper berth tickets : "+available_upper_berth.size());
		System.out.println("Available R-A-C tickets  : "+(1-RAC_list.size()));
		System.out.println("Available Waiting List tickets: "+(1-WL_list.size()));
		System.out.println();
	}
	
	
	public void bookedTickets()
	{
		for(Passenger passenger : passenger_list.values())
		{
			System.out.println("id => "+passenger.id+" name :"+passenger.name+"  ticket : "+passenger.number+""+passenger.allocated_berth);
		}
		System.out.println();
	}	
}


class Passenger
{
	static int passenger_id=1;//STATIC VARIABLE FOR GIVE DISTINCT ID FOR EVERY NEW PASSENGER
	int id;
	String name;
	int age;
	String preference;
	int number;
	String allocated_berth;
	
	Passenger(String name, int age, String preference)
	{
		this.id=passenger_id++;
		this.name=name;
		this.age=age;
		this.preference=preference;
		this.number=0;
		this.allocated_berth="";
	}
	
	Passenger()
	{
		this.id=passenger_id++;
		this.number=0;
		this.allocated_berth="";
	}
	
	void print()
	{
		System.out.println("Your id is "+ id+" Ticket number is "+number+allocated_berth);
	}
	
	//SETTERS
	public void setName(String name) {
		this.name=name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}
	
	//GETTERS 
	public String getName(){
		return this.name;
	}
	
	public int getAge() {
		return age;
	}

	public String getPreference() {
		return preference;
	}
	
}





	
