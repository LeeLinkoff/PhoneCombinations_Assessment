import java.util.*;
import java.io.*;


public class PhoneNumberCombinations
{
	 //Vector<String> letterCombinations;
	 List<String> phoneNumberCombinations;
	 
 	 public PhoneNumberCombinations(String phone) throws Exception
	 {
		phoneNumberCombinations = new Vector<String>();
	
		List<String> specialNumbers = Arrays.asList((new String[] {"7","9"}));
		List<String> ignoredNumbers = Arrays.asList((new String[] {"0","1"}));
		
		String characterInPhoneNumber;
		int actualNumberValueInPhoneNumber, numberOfLettersForKey, startingAsciiValueForLettersCombo;
		long count;
		
		String cleansedPhoneNumber = phone.replace(" ","").trim();
		if ( 
		     ( (cleansedPhoneNumber.length()) == 7 || (cleansedPhoneNumber.length() == 10) )
		      &&
			 ( cleansedPhoneNumber.matches("^[0-9]+$") )
			)
		{
			for (int i = 0; i < cleansedPhoneNumber.length(); ++i)
			{
				characterInPhoneNumber = String.valueOf(cleansedPhoneNumber.toCharArray()[i]);
				actualNumberValueInPhoneNumber = Integer.parseInt(characterInPhoneNumber);
				numberOfLettersForKey = 3;
				startingAsciiValueForLettersCombo = 65 + ((actualNumberValueInPhoneNumber - 2) * 3);

				if ( specialNumbers.contains(characterInPhoneNumber) )
				{
					numberOfLettersForKey = 4;
										
 					if (actualNumberValueInPhoneNumber == 9)
					{
						startingAsciiValueForLettersCombo += 1;
					} 
				}

				if ( !(ignoredNumbers.contains(characterInPhoneNumber)) )
				{
					for (int j = startingAsciiValueForLettersCombo; j < (startingAsciiValueForLettersCombo + numberOfLettersForKey); ++j)
					{
						StringBuilder newPhoneNumber = new StringBuilder(cleansedPhoneNumber);
						newPhoneNumber.setCharAt(i, (char) j);
						phoneNumberCombinations.add(newPhoneNumber.toString());
					}
				}
			}
		}
		else
		{
			throw new InvalidPhoneNumberException(phone);
		}
	 }


	 public int size()
	 {
		 return this.phoneNumberCombinations.size();
	 }

	 public List<String> getItems()
	 {
		 return this.phoneNumberCombinations;
	 }
}