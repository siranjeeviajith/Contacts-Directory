import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.MultiKeyMap;

public class ContactApplication  {
	static Scanner scan = new Scanner(System.in);
	static long uids = 0;

	public static void createContact(MultiKeyMap nameUidIndex, Map<String, Contact> emailIndex, Map<String, Long> nameIndex) {
		long number;
		long uid;
		String name;
		String email;
		Contact contact;
		
		try {
			System.out.println("Creating New Contact:");
			System.out.println("Enter Name:");
			name = scan.nextLine();
			if(nameIndex.containsKey(name.toLowerCase())) {
				throw new Exception("Name "+name+" is already exists.");
			}
			System.out.println("Enter 6 - 15 digit Phone Number");
			String temp=scan.nextLine();
			if(!checkIsValidNumber(temp)) {
				throw new Exception("Number "+temp+" is not valid");
			}
			number=Long.parseLong(temp);
			System.out.println("Enter  Email");
			email = scan.nextLine();
			
			if(!checkIsValidEmail(email)) {
				throw new Exception("Email:"+email+" is not valid ..");
			}
			if(emailIndex.containsKey(email)) {
				throw new Exception("Email "+email+" is aleady exists");
			}
			
			Address address=new Address();
			getAddress(address);
			contact = new Contact(name, number, email, address,0);
			uid = contact.hashCode();
			contact.setUid(uid);
			nameUidIndex.put(name.toLowerCase(), uid, contact);
			emailIndex.put(email, contact);
			nameIndex.put(name.toLowerCase(), uid);
			System.out.println(contact);
			System.out.println("Contact created");
		} catch (Exception e) {
			System.out.println(e + "Try Again");
		}

	}

	public static void searchContact(MultiKeyMap nameUidIndex,Map<String,Contact> emailIndex,Map<String, Long> nameIndex) {
		int option;
		long uid;
		String email;
		String name;
		String output;
		Contact contact;
		
	try {	
		System.out.println("Search a Contact by:\n"
				           + "1.By Email\n"
				           + "2.By Name");
		option = Integer.parseInt(scan.nextLine());
		if(option == 1) {
			System.out.println("Enter the email to search:");
			email=scan.nextLine();
			if(emailIndex.containsKey(email)) {
				contact=emailIndex.get(email);
				System.out.println("Contact found:\n"+contact);
			}else {
				throw new Exception("Email:"+email+" is not found.");
			}
		}
		else if(option==2) {
			System.out.println("Enter the Name to search:");
			name=scan.nextLine();
			if(nameIndex.containsKey(name.toLowerCase())) {
				uid=nameIndex.get(name.toLowerCase());
				System.out.println("Name found:\n"+nameUidIndex.get(name.toLowerCase(),uid));
			}
			else {
				throw new Exception("Name:"+name+" is not found.");
			}
		}
	}catch(Exception e) {
		System.out.println(e+"  Try again!");
	}
		
		
	}

	public static void deleteContact(MultiKeyMap nameUidIndex, Map<String, Contact> emailIndex, Map<String, Long> nameIndex) {
		String email;
		Contact contact;
		try {

			System.out.println("Deleting Contact:");
			System.out.println("Enter a contact email to delete:");
			email = scan.nextLine();
			if (emailIndex.containsKey(email)) {
				contact = emailIndex.get(email);
				nameIndex.remove(contact.getName().toLowerCase());
				nameUidIndex.remove(contact.getName().toLowerCase(), contact.getUid());
				emailIndex.remove(email);
				System.out.println("Email:" + email + " is deleted");
			} else {
				System.out.println("Email"+email+" is not found! Try again");
			}
		} catch (Exception e) {
			System.out.println(e + "  Try Again");
		}
	}

	public static void updateContact(MultiKeyMap nameUidIndex, Map<String, Contact> emailIndex, Map<String, Long> nameIndex) {
		long number;
		int option;

		String address;
		String email;
		String newEmail;
	try {
		System.out.println("Updating a contact:");
		System.out.println("Enter a email contact you want to update:");
		email = scan.nextLine();
		if (emailIndex.containsKey(email)) {
			System.out.println("Enter a option:\n" 
		                       + "1 - Name\n" 
					           + "2 - Email\n" 
		                       + "3 - Address\n" 
					           + "4 - Number\n");
			option = Integer.parseInt(scan.nextLine());
			switch (option) {
			case 1:
				updateName(nameUidIndex, emailIndex, nameIndex, email);
				break;
			case 2:
				updateEmail(nameUidIndex, emailIndex, nameIndex, email);
				break;
			case 3:
				updateAddress(nameUidIndex, emailIndex, nameIndex, email);
				break;
			case 4:
				updateNumber(nameUidIndex, emailIndex, nameIndex, email);
				break;
			default:
				System.out.println("Try Again!");
			}
			System.out.println("Contact updated.");
		} else {
			System.out.println("The email:" + email + " is not present. Try Again!");
		}
	}catch(Exception e) {
		System.out.println(e+" Try again!");
	}

	}

	public static void updateName(MultiKeyMap nameUidIndex,Map<String,Contact> emailIndex,Map<String, Long> nameIndex,String email) {
		String nameOld;
		String nameNew;
		Contact contact;
		try {
		
		System.out.println("Enter the name:");
		nameNew=scan.nextLine();
		if(nameNew.isEmpty()) {
			throw new Exception("Please enter a name.");
		}
		if(nameIndex.containsKey(nameNew)) {
			throw new Exception("Name:"+nameNew+" already exist!");
		}
		contact=emailIndex.get(email);
		nameOld=contact.getName();
		contact.setName(nameNew);
		emailIndex.put(email,contact);
		nameIndex.remove(nameOld.toLowerCase());
		nameIndex.put(nameNew.toLowerCase(),contact.getUid());
		nameUidIndex.remove(nameOld.toLowerCase(),contact.getUid());
		nameUidIndex.put(nameNew.toLowerCase(),contact.getUid(),contact);		
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
		
	}
	
	public static void updateEmail(MultiKeyMap nameUidIndex,Map<String,Contact> emailIndex,Map<String, Long> nameIndex,String email) {
		
		try {
		
		String emailNew;
		Contact contact;
		
		System.out.println("Enter the email:");
		emailNew=scan.nextLine();
		if(emailIndex.containsKey(emailNew)) {
			throw new Exception("Email :"+emailNew+" is already exists");
		}
		if(!checkIsValidEmail(emailNew)) {
			throw new Exception("Email:"+emailNew+" is not valid ..");
		}
		contact=emailIndex.get(email);
		contact.setEmail(emailNew);
		emailIndex.remove(email);
		emailIndex.put(emailNew,contact);
		nameUidIndex.put(contact.getName().toLowerCase(),contact.getUid(),contact);
		
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
	}
	public static void updateAddress(MultiKeyMap nameUidIndex,Map<String,Contact> emailIndex,Map<String, Long> nameIndex,String email) {
		
		Contact contact;
		
		try {
		
		contact=emailIndex.get(email);
		
		getAddress(contact.address);
		
		emailIndex.put(email,contact);
		nameUidIndex.put(contact.getName().toLowerCase(),contact.getUid(),contact);
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
	}
	public static void updateNumber(MultiKeyMap nameUidIndex,Map<String,Contact> emailIndex,Map<String, Long> nameIndex,String email) {
		try {
		long numberNew;
		Contact contact;
		System.out.println("Enter the 6 - 15 digit Number :");
		String temp=scan.nextLine();
		if(!checkIsValidNumber(temp)) {
			throw new Exception("Number "+temp+" is not valid.");
		}
		numberNew=Long.parseLong(temp);
		contact=emailIndex.get(email);
		contact.setNumber(numberNew);
		emailIndex.put(email, contact);
		nameUidIndex.put(contact.getName(),contact.getUid(),contact);
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
	}
	public static void getAddress(Address address) {
		String doorNo;
		String street;
		String city;
		int pincode;
		try {
		System.out.println("Enter Address to Update:");
		System.out.println("Enter DoorNo:");
		doorNo=scan.nextLine();
		
		System.out.println("Enter Street:");
		street=scan.nextLine();
		
		System.out.println("Enter City:");
		city=scan.nextLine();
		
		System.out.println("Enter pincode");
		pincode=Integer.parseInt(scan.nextLine());
		address.setDoorNo(doorNo);
		address.setStreet(street);
		address.setCity(city);
		address.setPincode(pincode);
		}catch(Exception e) {
			System.out.println(e+" Try Again!");
			//getAddress(address);
		}
		
	}
	public static boolean checkIsValidEmail(String email) {
		String eFormat = "\\w+@\\w+\\.\\w+";
		Pattern pat = Pattern.compile(eFormat);
		Matcher match=pat.matcher(email);
		if(match.matches()) {
			return true;
			
		}
		return false;
	}
	public static boolean checkIsValidNumber(String number) {
		String eFormat = "\\d+{5,16}";
		Pattern pat = Pattern.compile(eFormat);
		Matcher match=pat.matcher(number);
		if(match.matches()) {
			return true;
		}
		return false;
		
	}
	  

	public static void displayContacts(Map<String, Long> nameIndex,MultiKeyMap nameUidIndex) {
		if (!nameIndex.isEmpty()) {
			for (String name : nameIndex.keySet()) {
				System.out.println(nameUidIndex.get(name,nameIndex.get(name)));
			}
		} else {
			System.out.println("There is no contact in the Directory!");
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		MultiKeyMap nameUidIndex = NameUidIndex.readDataFromFile();
		Map<String,Contact> emailIndex =  EmailIndex.readDataFromFile();
		Map<String,Long> nameIndex = NameIndex.readDataFromFile();
		
		
		
		while(true) {
		try {
			System.out.println("Contacts Directory:");
			System.out.println("1 - Create a Contact\n"
					+ "2 - Search a Contact\n"
					+ "3 - Update a Contact\n"
					+ "4 - Delete a Contact\n"
					+ "5 - Display Contacts\n"					
					+ "6 - Exit");
			int input=0;
			System.out.println("Enter a option:");
			input=Integer.parseInt(scan.nextLine());
			//if(scan.hasNext()) {
			switch(input) {
			case 1 : createContact(nameUidIndex,emailIndex,nameIndex);
					break;
			case 2 : searchContact(nameUidIndex,emailIndex,nameIndex);
					break;
			case 3 : updateContact(nameUidIndex,emailIndex,nameIndex);
			        break;
			case 4 : deleteContact(nameUidIndex,emailIndex,nameIndex);
					break;
			case 5 : displayContacts(nameIndex,nameUidIndex);
					break;
			case 6 : System.out.println("Application closing! Bye..");
					((NameUidIndex) nameUidIndex).writeDataToFile();
					 ((EmailIndex) emailIndex).writeDataToFile();
					((NameIndex) nameIndex).writeDataToFile();
					System.exit(0);
					break;
			default :System.out.println("Try Again!");
		 //   }
			}
		}
		catch(Exception e) {
			System.out.println(e+" Wrong Input! Please try again!");
			}
		
	}
		
		}
	}


