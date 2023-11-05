package zoho;

import java.util.*;
/*
	The are 6 points(A,B,C,D,E,F) 15 KM apart 60 min travel between each, n taxis all taxis 
	at A starting. 100 rs for first 5 KM and then 10 for each of the further KMs, rate from 
	pickup to drop only pickup time example : 9 hrs, 15 hrs
	
	When a customer books a Taxi, a free taxi at that point is allocated
	-If no free taxi is available at that point, a free taxi at the nearest point is allocated.
	-If two taxi’s are free at the same point, one with lower earning is allocated
	-If no taxi is free at that time, booking is rejected
	
	
	Input 1:
	Customer ID: 1
	Pickup Point: A
	Drop Point: B
	Pickup Time: 9
	
	Output 1:
	Taxi can be allotted.
	Taxi-1 is allotted

*/

public class TaxiBooking
{
	public static Scanner in = new Scanner(System.in);
	
    public static void main(String[] args)
    {

       System.out.println("----WELCOME TO JET CARS----");
       boolean loop=true;
       int cus_id=1;
       
       Taxi[] taxi=new Taxi[4];//CREATING 4 TAXI OBJECTS
       for(int i=0; i<4; ++i)
			taxi[i]=new Taxi();
       
   
       while(loop)
       {
    	   System.out.println("Enter 1.Booking  2.TaxiDetails  3.Exit");
    	   int choice=in.nextInt();
    	   
    	   switch(choice)
    	   {
    	   	case 1:
    	   		System.out.println("------ TAXI BOOKING SECTION ------");
    	   		System.out.println("Enter pickup point ");
    	   		char pickup=in.next().charAt(0);
    	   		System.out.println("Enter drop point");
    	   		char drop=in.next().charAt(0);
    	   		
    	   		//CONSIDERING THE ERROR EDGE CASES
    	   		if(pickup<'A' || pickup>'F' || drop<'A' || drop>'F' || pickup==drop)
    	   		{
    	   			System.out.println("You entered the worng pickup(or)drop point!!! "
    	   					+ "Make sure that you have enter the currct locations->(A,B,C,D,E,F) \n");
    	   			break;
    	   		}
    	   		
    	   		System.out.println("Enter pickup time (railway time)");
    	   		int pickup_time=in.nextInt();
    	   		
    	   		//GET FREE TAXI LIST
    	   		List<Taxi> free_taxi=getFreeTaxi(pickup,pickup_time,taxi);
    	   		//CHECK ANY TAXI IS AVAILABLE OR NOT
    	   		if(free_taxi.size()==0)
    	   		{
    	   			System.out.println("No Taxi is available...");
    	   			break;
    	   		}
    	   		//SORTING BAASED ON EARNINGS - ASCENDING 
    	   		Collections.sort(free_taxi, (a,b) -> a.earnings - b.earnings);
    	   		
    	   		bookTaxi(free_taxi,cus_id,pickup,drop,pickup_time);
    	   		//INCREMENTING CUSTOMER ID
    	    	cus_id++;
    	   		break;
    	   		
    	   	case 2:
    	   		System.out.println("------- TAXI DETAILS SECTION -------\n");
    	   		printDetails(taxi);
    	   		break;
    	   		
    	   	default:
    	   		loop=false;
    	   }
    	  
       }
       
       System.out.println("Good Bye!!! Visit again");
    }
    
    public static List<Taxi> getFreeTaxi(int pickup, int pickup_time, Taxi[] taxi)
    {
    	List<Taxi> free_taxi=new ArrayList<>();
    	
    	for(Taxi t:taxi)
    	{    		
    		//ADDING TAXI, IF THE TAXI IS FREE BEFORE THE PICKUP TIME AND IT MUST ARRIVE THE PICKUP PLACE IN TIME
    		if(t.free_time <= pickup_time && Math.abs(t.current_place - pickup) <= (pickup_time - t.free_time))
    			free_taxi.add(t);
    	}
    	
    	return free_taxi;
    }
    
    public static void bookTaxi(List<Taxi> free_taxi,int cus_id,char pickup,char drop,int pickup_time)
    {
    	int min=111;//APPROXIMATE VALUE
    	
    	int distance_pickup_to_drop=0, earnings=0, duration=0;
    	
    	String details="";
    	
    	Taxi booked_taxi = null;
    	
    	for(Taxi t:free_taxi)
    	{
    		//JAVA AUTOMATICALLY CONVERTS CHAR -> ASCII VALUE WHEN WE PERFORM ANY ARITHMETIC OPERATION
    		int distance_customer_to_taxi=Math.abs(t.current_place - pickup) * 15;
    		
    		if(distance_customer_to_taxi < min)
    		{
    			min=distance_customer_to_taxi;
    			
    			//CALCULATING THE TRAVEL DISTACE FROM THE PICKUP POINT
    			distance_pickup_to_drop=Math.abs(pickup - drop) * 15;
    			//CALCULATING AMOUNT OF THIS TRIP
    			earnings=(distance_pickup_to_drop-5) * 10 + 100;
    			//CALCULATING THE DROP TIME
    			duration=(distance_pickup_to_drop / 15);
    			
    			details=t.id+"	 "+cus_id+"	  "+pickup+" 	 "+drop+"	  "+duration+"	  "+earnings;
    			booked_taxi=t;
    		}
    	}
    
    	//CALCULATING DROP TIME FROM THE PICKUP TIME BY ADDING THE DURATION OF TRAVEL
    	int drop_time=duration+pickup_time;
    	//UPDATING NEW VALUES 
    	booked_taxi.setDetails(true,drop,drop_time,earnings,details);
    	
    	System.out.println("Taxi "+booked_taxi.id+" booked successfully \n");
    }
    
    public static void printDetails(Taxi[] taxi)
    {
    	for(Taxi t:taxi)
    	{
    		System.out.println("Taxi Id - > "+t.id+"  Toatal earnings -> "+t.earnings+"  Free time -> "+t.free_time
    				+"   Current place -> "+t.current_place);
    		System.out.println("-----------------------------------------------------------------");
    		System.out.println("t_id	cus_id   from	to	duration   earnings");
    		for(String str:t.trip_details)
    		{
    			System.out.println(" "+str);
    		}
    		System.out.println();
    	}
    }
}





