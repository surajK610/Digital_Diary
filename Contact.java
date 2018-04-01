package application;

public class Contact {

private String phone_number;
private String first_name, last_name;
private String address;
private String email_address;

	public Contact(String last_name, String first_name, String phone_number, String address, String email_address)
	{
		this.phone_number = phone_number;
		this.first_name = first_name;
		this.last_name = last_name;
		this.address = address;
		this.email_address = email_address;
	}
	
	public String getFirstName()
	{
		return first_name;
	}
	
	public String getLastName()
	{
		return last_name;
	}
	
	public String getPhoneNumber()
	{
		return phone_number;
	}
	public String getAddress()
	{
		return address;
	}
	
	public String getEmailAddress()
	{
		return email_address;
	}
	
	public void setPhoneNumber(String phone_number)
	{
		this.phone_number = phone_number;
	}

	public void setFirstName(String first_name)
	{
		this.first_name = first_name;
	}
	
	public void setLastName(String last_name)
	{
		this.last_name = last_name;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setEmailAddress(String email_address)
	{
		this.email_address = email_address;
	}
	
	public String toString()
	{
		return last_name + ", " + first_name + "\nPhone Number: " + phone_number + "\nEmail Address: " + email_address + "\nAddress: " + address;
	}
	
}
