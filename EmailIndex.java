import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.map.MultiKeyMap;

public class EmailIndex extends HashMap{
		public EmailIndex() {
			
		}
		public static EmailIndex readDataFromFile() {
			try {
				FileInputStream fileInMap2=new FileInputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\emailMap.txt");
				ObjectInputStream inMap2=new ObjectInputStream(fileInMap2);
				return(EmailIndex) inMap2.readObject();
			}
			catch(Exception e) {
				System.out.println("EmailIndex File contains no data");
				return new EmailIndex();
			}
		}
		public void writeDataToFile() throws IOException {
			FileOutputStream fileOutMap2=new FileOutputStream("C:\\Users\\User\\eclipse-workspace\\ContactsDirectory\\src\\emailMap.txt");
			ObjectOutputStream outMap2=new ObjectOutputStream(fileOutMap2);
			outMap2.writeObject(this);
		}
		
}
