package application;

import java.util.Date;

public class Event {

	private Date time;
	private String eventName;
	private String event;
	
	public Event(String eventName)
	{
		this.eventName = eventName;
	}
	
	public Event(String eventName, Date time)
	{
		this.time = time;
		this.eventName = eventName;
		this.event = "";
	}
	
	public Event(String eventName, String event,  Date time)
	{
		this.time = time;
		this.eventName = eventName;
		this.event = event;
	}
	
	
	public Date getTime()
	{
		return time;
	}
	
	public String getTimeString()
	{
		System.out.println(time.toString());
		return time.toString();
	}
	
	public String getEventName()
	{
		return eventName;
	}
	
	public String getEvent()
	{
		return event;
	}
	
	public void setTime(Date time)
	{
		this.time = time;
	}
	
	public void setEventName(String eventName)
	{
		this.eventName = eventName;
	}
	
	public void setEvent(String event)
	{
		this.event = event;
	}
	
	
}
