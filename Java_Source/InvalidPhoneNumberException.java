public class InvalidPhoneNumberException extends Exception
{
	private static final long serialVersionUID = 7718828512143293558L;

	public InvalidPhoneNumberException()
	{
		super();
	}


	public InvalidPhoneNumberException(String phoneNumber)
	{
		super("'" + phoneNumber + "'" + " is not a valid phone number");
	}

}