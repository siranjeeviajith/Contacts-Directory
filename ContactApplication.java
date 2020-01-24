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

public class ContactApplication implements Serializable {
	static Scanner scan = new Scanner(System.in);
	static long uids = 0;

	public static void createContact(MultiKeyMap details, Map<String, Contact> emailMap, Map<String, Long> nameMap) {
		long number;
		long uid;
		String name;
		String email;
		String address;
		Contact contact;
		
		try {
			System.out.println("Creating New Contact:");
			System.out.println("Enter Name:");
			name = scan.nextLine();
			if(nameMap.containsKey(name)) {
				throw new Exception("Name is already exists.");
			}
			System.out.println("Enter 10 digit Phone Number");
			String temp=scan.nextLine();
			if(!checkIsValidNumber(temp)) {
				throw new Exception("Number exceeding 15 digits.");
			}
			number=Long.parseLong(temp);
			System.out.println("Enter  Email");
			email = scan.nextLine();
			
			if(!checkIsValidEmail(email)) {
				throw new Exception("Email is not valid ..");
			}
			if(emailMap.containsKey(email)) {
				throw new Exception("Email is aleady exists");
			}
			System.out.println("Enter Address:");
			address = scan.nextLine();
			contact = new Contact(name, number, email, address,0);
			uid = contact.hashCode();
			contact.setUid(uid);
			details.put(name, uid, contact);
			emailMap.put(email, contact);
			nameMap.put(name, uid);
			System.out.println(contact);
			System.out.println("Contact created");
		} catch (Exception e) {
			System.out.println(e + "Try Again");
		}

	}

	public static void searchContact(MultiKeyMap details,Map<String,Contact> emailMap,Map<String, Long> nameMap) {
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
			if(emailMap.containsKey(email)) {
				contact=emailMap.get(email);
				System.out.println("Contact found:\n"+contact);
			}else {
				System.out.println("No contact is present .Try Again!");
			}
		}
		else if(option==2) {
			System.out.println("Enter the Name to search:");
			name=scan.nextLine();
			if(nameMap.containsKey(name)) {
				uid=nameMap.get(name);
				System.out.println("Name found:\n"+details.get(name,uid));
			}
			else {
				System.out.println("No contact is present .Try Again!");
			}
		}
	}catch(Exception e) {
		System.out.println(e+"  Try again!");
	}
		
		
	}

	public static void deleteContact(MultiKeyMap details, Map<String, Contact> emailMap, Map<String, Long> nameMap) {
		String email;
		Contact contact;
		try {

			System.out.println("Deleting Contact:");
			System.out.println("Enter a contact email to delete:");
			email = scan.nextLine();
			if (emailMap.containsKey(email)) {
				contact = emailMap.get(email);
				nameMap.remove(contact.getName());
				details.remove(contact.getName(), contact.getUid());
				emailMap.remove(email);
				System.out.println("Email:" + email + " is deleted");
			} else {
				System.out.println("No email is found! Try again");
			}
		} catch (Exception e) {
			System.out.println(e + "  Try Again");
		}
	}

	public static void updateContact(MultiKeyMap details, Map<String, Contact> emailMap, Map<String, Long> nameMap) {
		long number;
		int option;

		String address;
		String email;
		String newEmail;
	try {
		System.out.println("Updating a contact:");
		System.out.println("Enter a email contact you want to update:");
		email = scan.nextLine();
		if (emailMap.containsKey(email)) {
			System.out.println("Enter a option:\n" 
		                       + "1 - Name\n" 
					           + "2 - Email\n" 
		                       + "3 - Address\n" 
					           + "4 - Number\n");
			option = Integer.parseInt(scan.nextLine());
			switch (option) {
			case 1:
				updateName(details, emailMap, nameMap, email);
				break;
			case 2:
				updateEmail(details, emailMap, nameMap, email);
				break;
			case 3:
				updateAddress(details, emailMap, nameMap, email);
				break;
			case 4:
				updateNumber(details, emailMap, nameMap, email);
				break;
			default:
				System.out.println("Try Again!");
			}
		} else {
			System.out.println("The email:" + email + " is not present. Try Again!");
		}
	}catch(Exception e) {
		System.out.println(e+" Try again!");
	}

	}

	public static void updateName(MultiKeyMap details,Map<String,Contact> emailMap,Map<String, Long> nameMap,String email) {
		String nameOld;
		String nameNew;
		Contact contact;
		try {
		
		System.out.println("Enter the name:");
		nameNew=scan.nextLine();
		if(!nameMap.containsKey(nameNew)) {
			throw new Exception("Name:"+nameNew+" already exist!");
		}
		contact=emailMap.get(email);
		nameOld=contact.getName();
		contact.setName(nameNew);
		emailMap.put(email,contact);
		nameMap.remove(nameOld);
		nameMap.put(nameNew,contact.getUid());
		details.remove(nameOld,contact.getUid());
		details.put(nameNew,contact.getUid(),contact);		
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
		
	}
	
	public static void updateEmail(MultiKeyMap details,Map<String,Contact> emailMap,Map<String, Long> nameMap,String email) {
		
		try {
		String emailOld;
		String emailNew;
		Contact contact;
		
		System.out.println("Enter the email:");
		emailNew=scan.nextLine();
		if(!emailMap.containsKey(emailNew)) {
			throw new Exception("Email :"+emailNew+" is already exists");
		}
		if(!checkIsValidEmail(email)) {
			throw new Exception("Email is not valid ..");
		}
		contact=emailMap.get(email);
		contact.setEmail(emailNew);
		emailMap.remove(email);
		emailMap.put(emailNew,contact);
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
	}
	public static void updateAddress(MultiKeyMap details,Map<String,Contact> emailMap,Map<String, Long> nameMap,String email) {
		String addressOld;
		String addressNew;
		Contact contact;
		try {
		System.out.println("Enter the address:");
		addressNew=scan.nextLine();
		contact=emailMap.get(email);
		contact.setAddress(addressNew);
		emailMap.put(email,contact);
		details.put(contact.getName(),contact.getUid(),contact);
		}catch(Exception e) {
			System.out.println(e+" Try again!");
		}
	}
	public static void updateNumber(MultiKeyMap details,Map<String,Contact> emailMap,Map<String, Long> nameMap,String email) {
		try {
		long numberNew;
		Contact contact;
		System.out.println("Enter the Number :");
		String temp=scan.nextLine();
		if(!checkIsValidNumber(temp)) {
			throw new Exception("Number exceeding 15 digits.");
		}
		numberNew=Long.parseLong(temp);
		contact=emailMap.get(email);
		contact.setNumber(numberNew);
		emailMap.put(email, contact);
		}catch(Exception e) {
			System.out.println(e+" Try again!");
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
	  

	public static void displayContacts(Map<String, Long> nameMap,MultiKeyMap details) {
		if (!nameMap.isEmpty()) {
			for (String name : nameMap.keySet()) {
				System.out.println(details.get(name,nameMap.get(name)));
			}
		} else {
			System.out.println("There is no contact in the Directory!");
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		MultiKeyMap details;
		Map<String,Contact> emailMap;
		Map<String,Long> nameMap;
		try {
		FileInputStream fileInMap1=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\details.txt");
		ObjectInputStream inMap1=new ObjectInputStream(fileInMap1);
		FileInputStream fileInMap2=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\emailMap.txt");
		ObjectInputStream inMap2=new ObjectInputStream(fileInMap2);
		FileInputStream fileInMap3=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\nameMap.txt");
		ObjectInputStream inMap3=new ObjectInputStream(fileInMap3);
		
		details=(MultiKeyMap) inMap1.readObject();
		emailMap=(HashMap) inMap2.readObject();
		nameMap=(TreeMap) inMap3.readObject();
		
		}
		catch(Exception e) {
			details=new MultiKeyMap();
			emailMap=new HashMap();
			nameMap=new TreeMap<>();
		}
		FileOutputStream fileOutMap1=new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\details.txt");
		ObjectOutputStream outMap1=new ObjectOutputStream(fileOutMap1);
		FileOutputStream fileOutMap2=new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\emailMap.txt");
		ObjectOutputStream outMap2=new ObjectOutputStream(fileOutMap2);
		FileOutputStream fileOutMap3=new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\nameMap.txt");
		ObjectOutputStream outMap3=new ObjectOutputStream(fileOutMap3);
		
		
		while(true) {
		try {
			System.out.println("Contacts Directory:");
			System.out.println("1 - Create a Contact\n"
					+ "2 - Search a Contact\n"
					+ "3 - Update a Contact\n"
					+ "4 - Delete a Contact\n"
					+ "5 - Display Contacts\n"					
					+ "6 - Exit");
			System.out.println("Enter a option:");
			int input=Integer.parseInt(scan.nextLine());
			switch(input) {
			case 1 : createContact(details,emailMap,nameMap);
					break;
			case 2 : searchContact(details,emailMap,nameMap);
					break;
			case 3 : updateContact(details,emailMap,nameMap);
			        break;
			case 4 : deleteContact(details,emailMap,nameMap);
					break;
			case 5 : displayContacts(nameMap,details);
					break;
			case 6 : System.out.println("Application closing! Bye..");
					outMap1.writeObject(details);
					outMap2.writeObject(emailMap);
					outMap3.writeObject(nameMap);
					System.exit(0);
					break;
			default :System.out.println("Try Again!");
		    }
		}
		catch(InputMismatchException e) {
			System.out.println("Wrong Input! Please try again!");
			}
	
		

	}
	}

}
