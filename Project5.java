import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Project5 {

	public static void main(String[] args) throws FileNotFoundException {
		Country country;
		double x;
		
		Scanner in = new Scanner(System.in);//scanner to take input
		System.out.println("Please enter file name: ");
		String b = in.nextLine();// user input
		
		
		File file = new File(b);// takes the file 
		Scanner read = new Scanner(file);// allows for the file to be read 
		read.useDelimiter("\n|,");//allows parsing of each line
		read.nextLine();// skips the header
		
		HashTable test = new HashTable();
			
		for(int i = 0; i < 155; i++) {// for initializes each individual object in the array and assigns values to its attributes
			String ab = read.next();
			String ac = read.next();
			String ad = read.next();
			String ae = read.next();
			String af = read.next();
			String ag = read.next();
			country = new  Country(ab, ac, ad, ae, af, ag);
			country.setCountry(ab);
			country.setCountryCode(ac);
			country.setCapitol(ad);
			country.setPopulation(ae);
			country.setGDP(af);
			country.setHappy(ag);
			country.setperCap(ae, af);
			 
			
			x = country.getperCap();
			test.insert(ab, x);
			
			read.nextLine();	
		}
		test.display();
		
		test.delete("Cyprus");
		test.delete("Kazakhstan");
		test.delete("Hungary");
		test.delete("Japan");
		
		test.find("Costa Rica");
		test.find("North Cyprus");
		test.find("Hungary");
		
		test.delete("Zambia");
		test.delete("Canada");
		test.delete("Egypt");
		test.delete("Yemen");
		test.delete("India");
		
		test.display();
		
		test.printFreeAndCollisions();

	}

}
