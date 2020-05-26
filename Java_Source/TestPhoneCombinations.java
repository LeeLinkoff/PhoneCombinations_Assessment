import java.util.*;  //for Vector class
import java.io.*;

public class TestPhoneCombinations
{
	public static void main(String[] args)
	{
		try
		{
			String phoneNum;

			BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter a phone number: ");
			phoneNum = reader.readLine();
			
			PhoneNumberCombinations combinations = new PhoneNumberCombinations(phoneNum);
			
			System.out.println();
			System.out.println("Total number of combinations: " + combinations.size());
			System.out.println();
		

			for (String number : combinations.getItems())
			{
				System.out.println(number);
				System.out.println();
			}
		}
		catch (Exception ex)
		{
		  System.out.println(ex.getMessage());
		}
	}
}