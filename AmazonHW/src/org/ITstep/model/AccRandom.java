package org.ITstep.model;

public class AccRandom {
	
	public  String getEmail() {
		String email = "";
		int rndInt = 100000 + (int) (Math.random() * (10000));
		email = firstName1() + "." + secondName1() + "." + rndInt + "@" + getDomain();

		return email;
	}

	public  String getPassword() {

		return secondName1() + String.valueOf(10000 + (int) (Math.random() * (10000)));
	}
	
	public String firstName1() {
		String words="qwertyuioplkjhgfdsazxcvbnm";
//		char[] charwords=words.toCharArray();
		String firstName="";
		for(int i=0;i<8;i++) {
			firstName+=String.valueOf(words.toCharArray()[(int)(Math.random() * (words.length()-1))]);	
		}
		
		
		return firstName;
		
	}
	
	public static String secondName1() {
		String words="qwertyuioplkjhgfdsazxcvbnm";
//		char[] charwords=words.toCharArray();
		String secondName="";
		for(int i=0;i<8;i++) {
			secondName+=String.valueOf(words.toCharArray()[(int)(Math.random() * (words.length()-1))]);
		}
		
		
		return secondName;
		
	}

	private static String getDomain() {
		int rndInt = ((int) (Math.random() * 5));
		switch (rndInt) {
		case 0:
			return "gmail.com";
		case 1:

			return "inbox.com";
		case 2:

			return "mail.com";
		case 3:

			return "bigmir.net";
		case 4:

			return "rk.com";
		default:
			return "hotmail.com";
		}
	}

}
