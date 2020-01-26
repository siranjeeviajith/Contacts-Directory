import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.collections4.map.MultiKeyMap;

public class NameUidIndex extends MultiKeyMap {

	public NameUidIndex() {
		
	}
	public static NameUidIndex readDataFromFile() {
		try {
			FileInputStream fileInMap1=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\details.txt");
			ObjectInputStream inMap1=new ObjectInputStream(fileInMap1);
			return(NameUidIndex) inMap1.readObject();
		}
		catch(Exception e) {
			System.out.println("NameUidIndex File contains no data");
			return new NameUidIndex();
		}
	}
	public void writeDataToFile() throws IOException {
		FileOutputStream fileOutMap1=new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\details.txt");
		ObjectOutputStream outMap1=new ObjectOutputStream(fileOutMap1);
		outMap1.writeObject(this);
	}
	
	

}
