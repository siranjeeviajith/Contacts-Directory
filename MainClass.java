import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.keyvalue.MultiKey;
import org.apache.commons.collections4.map.MultiKeyMap;

public class MainClass implements Serializable{
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

		// Scanner sc=new Scanner(System.in);
//		String eFormat = "\\w+@\\w+\\.\\w+";
//		Pattern pat = Pattern.compile(eFormat);
//		Contact n1 = new Contact("Raja", 1234567890, "no6 west street", "hellothere@gmail.com", 1);
//		Contact n2 = new Contact("Aswath", 983456789, "no2 east street", "hitheree@gmail.com", 2);
//		Contact n3 = new Contact("Kabilesh", 987654321, "no4 north street", "howrue@gmail.com", 3);
//		Contact n4 = new Contact("Aswath", 876542790, "no6 south street", "welcome@gmail.com", 4);
//		Contact n5 = new Contact("Ramesh", 845434322, "no9 southWest street", "ramesh@gmail.com", 5);
//		Contact n6 = new Contact("Sanjay", 902134567, "no14 NorthEast street", "sanjay@gmail.com", 6);
//		Map<String, Contact> map = new HashMap<>(16, 0.95f);
//		Map<String, List<String>> map1 = new HashMap<>();
//		map.put("hellothere@gmail.com", n1);
//		map.put("hitheree@gmail.com", n2);
//		map.put("howrue@gmail.com", n3);
//		map.put("welcome@gmail.com", n4);
//		map.put("ramesh@gmail.com", n5);
//		map.put("sanjay@gmail.com", n6);
//		Matcher match = pat.matcher("w@g.c");
//		System.out.println(map);
//		System.out.println(match.matches());
		// Contacts.createContact(map);
		// Contacts.searchContact(map, "Aswath", null);
		// System.out.println(Contacts.mapToString(map));
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\store");
		ObjectInputStream input=new ObjectInputStream(fis);
		MultiKeyMap map3 = (MultiKeyMap)input.readObject();
//		map3.put("hellothere@gmail.com",n1);
//		map3.put("hitheree@gmail.com",n2);
//		map3.put("howrue@gmail.com",n3);
//		map3.put("welcome@gmail.com",n4);
//		map3.put("ramesh@gmail.com",n5);
//		map3.put("sanjay@gmail.com",n6);
//		Contact obj = new Contact();
//
//		map3.put("Raja", 1, n1);
//		map3.put("Aswath", 2, n2);
//		map3.put("Kabilesh", 3, n3);
//		map3.put("Aswath", 4, n4);
//		map3.put("Ramesh", 5, n5);
//		map3.put("Sanjay", 6, n6);
//		System.out.println(map3.containsKey("Raja", 1));
		System.out.println(map3);
//		FileOutputStream fos=new FileOutputStream("C:\\Users\\User\\Desktop\\store");
//		ObjectOutputStream oos=new ObjectOutputStream(fos);
//		oos.writeObject(map3);
//		System.out.println(map3.get("hellothere@gmail.com"));

	}

}
