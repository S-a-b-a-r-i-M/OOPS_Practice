package zoho;
import java.util.*;
import java.util.List;


class Taxi
{
	static int taxi_count=1;
	
	int id;
	boolean booked;
	char current_place;
	int free_time;
	int earnings;
	List<String> trip_details;
	
	Taxi()//DEFAULT CONSTRUCTOR
	{
		this.id=taxi_count++;
		this.booked=false;
		this.current_place='A';
		this.free_time=6;
		this.earnings=0;
		this.trip_details=new ArrayList<>();
	}
	
	//UPDATING NEW VALUES
	public void setDetails(boolean status,char current_place,int duration,int earnings,String trip_detail)
	{
		this.booked=status;
		this.current_place=current_place;
		this.free_time=duration;
		this.earnings+=earnings;
		this.trip_details.add(trip_detail);
	}
	
}
