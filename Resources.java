package greenSheet;


public class Resources {
	
	private String username	 = "/Users/syamsalik/Developer/UGK/username.txt";
	private String password	 = "/Users/syamsalik/Developer/UGK/password.txt";
	private String KronosURL = "https://cust02-prd01-ath01.prd.mykronos.com/authn/XUI/?realm=/abrailservices_prd_01#login&goto=https%3A%2F%2Fabrailservices.prd.mykronos.com%3A443%2F";
	private String FileOutPutLocation = "/Users/syamsalik/Developer/UGK/Schedule.txt";

	public String getUserName() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}

	public String getKronosURL() {
		return this.KronosURL;
	}
	
	public String getFileOutPutLocation() {
		return this.FileOutPutLocation;
	}
	

	
	}
	



