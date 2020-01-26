import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class NameIndex extends TreeMap {
	public NameIndex() {
		
	}
	public static NameIndex readDataFromFile() {
		try {
			FileInputStream fileInMap3=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\nameMap.txt");
			ObjectInputStream inMap3=new ObjectInputStream(fileInMap3);
			return(NameIndex) inMap3.readObject();
		}
		catch(Exception e) {
			System.out.println("NameIndex File contains no data");
			return new NameIndex();
		}
	}
	public void writeDataToFile() throws IOException {
		FileOutputStream fileOutMap3=new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\nameMap.txt");
		ObjectOutputStream outMap3=new ObjectOutputStream(fileOutMap3);
		outMap3.writeObject(this);
	}
	
	

}
