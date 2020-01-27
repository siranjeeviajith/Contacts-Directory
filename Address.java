import java.io.Serializable;

public class Address implements Serializable {
	private String doorNo;
	private String street;
	private String city;
	private int pincode;
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String toString() {
		return doorNo+" "+street+"\n\t\t  "+city+"\n\t\t  "+pincode;
	}
	

}
