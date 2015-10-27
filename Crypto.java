import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;

class Vigenere {
    /*
      Author: Dustin Hu
      Date: 15-10-2014
      Purpose: To hold the key
      Fields: 
          Key
    */

    protected String key;

    public Vigenere(String key){
	this.key = key;
    }
    
}

public class Crypto{
    /*
      Author: Dustin Hu
      Date: 15-10-2014
      Purpose: To decipher and encrypt a file

      Fields: 
          key: A vinegre key
      Methods:
          mai:n : To be the main
          decrypt: To decrypt the input file
          encrypt: To encrypt the input file
          decryptLine: Takes in a string and decrypts said string
          encryptLine: Takes in a string and encrypts said string
     */
    protected Vigenere key;

    public static void main(String[] args) throws IOException, FileNotFoundException{
	// Author: Dustin Hu
	// Date: 15-10-2014
	// Purpose: To be the m/ain
	// Input: None
	// Output: None
	String userInput;
	String filename;
	String key;
	Crypto userEncryption;
	Boolean quit = false;
	BufferedReader input = new BufferedReader(
						  new InputStreamReader(System.in));


	userInput = "";

	while (quit == false){
	    System.out.println("Select from the following");
	    System.out.println("1. Encrypt a file");
	    System.out.println("2. Decrypt a file");
	    System.out.println("3. Quit");
	    System.out.print("> ");
	    userInput = input.readLine();
	    
	    if (userInput.equals("1")){
		System.out.println("Your key?");
		key = input.readLine();
		userEncryption = new Crypto(key);

		System.out.println("Your filename, minus the extension (Must be a .txt file!)");
		filename = input.readLine();
		System.out.println(userEncryption.encrypt(filename) + " has been created!");
	    }
	    else if (userInput.equals("2")){
		System.out.println("Your key?");
		key = input.readLine();
		userEncryption = new Crypto(key);

		System.out.println("Your filename, minus the extension (Must be a .cyp file!)");
		filename = input.readLine();
		System.out.println(userEncryption.decrypt(filename) + " has been created!");
	    }
	    else if (userInput.equals("3")){
		System.out.println("Bye bye!");
		quit = true;
	    }
	    else{
		System.out.println("Invalid input!");
	    }
	}
	

    }

    public String decrypt(String fileName) throws IOException, FileNotFoundException {
	// Author: Dustin Hu
	// Date: 15-10-2014
	// Purpose: To decrypt a whole file
	// Input: The file name to open
	// Output: The output filename
	BufferedReader file;
	PrintWriter output = new PrintWriter(new FileWriter(fileName + ".pln"));


	file = new BufferedReader(new FileReader(fileName+ ".cyp"));

	while (file.ready()){
	    output.println(decryptLine(file.readLine()));
	}
	output.close();
	return fileName + ".pln";
    }
    
    public String encrypt(String fileName) throws IOException, FileNotFoundException {
	// Author: Dustin Hu
	// Date: 15-10-2014
	// Purpose: To encrypt a whole file
	// Input: The file name to open
	// Output: The output filename
	BufferedReader file;
	PrintWriter output = new PrintWriter(new FileWriter(fileName + ".cyp"));


	file = new BufferedReader(new FileReader(fileName+ ".txt"));

	while (file.ready()){
	    output.println(encryptLine(file.readLine()));
	}
	output.close();
	return fileName + ".cyp";
    }

    public String decryptLine(String input){
	// Author: Dustin Hu
	// Date: 15-10-2014
	// Purpose: To decrypt the input string
	// Input: The encrypted string
	// Output: The decrypted string
	int offset;
	int current;
	int result;
	String output= "";

	for (int i = 0; i < input.length(); i++){
	    current = (int) input.charAt(i);
	    offset = (int) this.key.key.charAt(i % this.key.key.length()) - 32;
	    result = current - offset;
	    if (result < 32){
		result = result + 95;
	    }
	    output = output + (char) result;
	}
	return output;
    }

    public String encryptLine(String input){
	// Author: DUstin Hu
	// Date: 15-10-2014
	// Purpose: To encrypt a string
	// Input: THe string to encrypt
	// Output: THe encrypted string
	
	String output = "";
	int current;
	int offset;
	int result;
	for (int i = 0; i < input.length(); i++){
	    current = (int) input.charAt(i);
	    offset = (int) this.key.key.charAt(i % this.key.key.length()) -32;
	    result = current + offset;
	    if (result > 126){
		result = result - 95;
	    }
	    output = output + (char) result;
	}
	return output;
    }

    public Crypto(String key){
	this.key = new Vigenere(key);
    }
}
