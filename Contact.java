import java.io.Serializable;

public class Contact implements Serializable {
	
	 private String name;
		private String address;
		private long number;
		private String email;
	    private long uid;
	    
		public Contact() {
			
		}
		public Contact(String name,long number,String email,String address,long uid) {
			this.name=name;
			this.number=number;
			this.address=address;
			this.email=email;
			this.uid=uid;
		}
		public String getName() {
			return this.name;
		}
		
		public String getAddress() {
			return this.address;
		}
		
		public long getNumber() {
			return this.number;
		}
		
		public String getEmail() {
			return this.email;
		}
		
		public long getUid() {
			return this.uid;
		}
		
		public void setName(String name) {
			this.name=name;
		}
		
		public void setAddress(String address) {
			this.address=address;
		}
		
		public void setNumber(long number) {
			this.number=number;
		}
		
		public void setEmail(String email) {
			this.email=email;
		}
		
		public void setUid(long uid) {
			this.uid=uid;
		}
		
		public String toString() {
			return "Name:"+name+"\n\t->Number:+91"+number+"\n\t->Email:"+email+"\n\t->Address:"+address+"\n";
		}
		
		
	
	
}
