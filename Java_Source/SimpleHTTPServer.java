import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.*;


public class SimpleHTTPServer
{

	private static final String DELIMITER = "\"";
	private static final String SEPARATOR = ",";

    public static void main(String args[] ) throws IOException
	{
		String errorMessage = "";
		String body = "";
		String size = "";
		String combinationsList = "";
		String numberOfCombinationsNameValuePair = "";
		String combinationsNameValuesPair = "";
		Boolean errorOccurred;

        ServerSocket server = new ServerSocket(8000);
        System.out.println("Listening for connection on port 8000 ....");
		
        while (true)
		{
			errorOccurred = false;
			
            Socket clientSocket = server.accept();

			Date today = new Date();
			System.out.println("Connection accepted at " + today + " on port " + clientSocket.getPort());
			System.out.println();
			System.out.println();			
						
			// Getting HTTP headers
            InputStreamReader isr =  new InputStreamReader(clientSocket.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();            
            while (!line.isEmpty())
			{
                System.out.println(line);
                line = reader.readLine();
            }
			
			System.out.println("After printing headers");
			System.out.println();

            String phoneNumber = "";
			StringBuilder payload = new StringBuilder();
			while(reader.ready())
			{
				int b = reader.read();
				if (b != -1)
				{
					payload.append((char) b);					
				}
            }
			phoneNumber = payload.toString();
			phoneNumber = phoneNumber.replace("\"","");
			phoneNumber = phoneNumber.trim();

			System.out.println("Payload data is: " + phoneNumber);
			System.out.println();

			try
			{				
				PhoneNumberCombinations combinations = new PhoneNumberCombinations(phoneNumber);
				StringBuilder csvBuilder = new StringBuilder();
				size = String.valueOf(combinations.size());
				
				for(String combination : combinations.getItems())
				{
					csvBuilder.append(DELIMITER);
					csvBuilder.append(combination);
					csvBuilder.append(DELIMITER);
					csvBuilder.append(SEPARATOR);
				}
				combinationsList = csvBuilder.toString();
				if (combinationsList.length() > 0)
				{
					combinationsList = "[" + combinationsList.substring(0, (combinationsList.length() - DELIMITER.length())) + "]";
				}
				else
				{
					combinationsList = "[]";
				}
			}
			catch (Exception ex)
			{
				System.out.println("error occurred");
				errorOccurred = true;
				errorMessage = ex.getMessage();
			}
			

			String httpResponse = "";
			
			if (!errorOccurred)
			{
				numberOfCombinationsNameValuePair = "\"" + "numberOfCombinations" + "\"" + ":" + size;
				combinationsNameValuesPair = "\"" + "combinationsArray" + "\"" + ":" + combinationsList;
				body = "{" + numberOfCombinationsNameValuePair + "," + combinationsNameValuesPair + "}";
			}
			else
			{
				body = "{" + "\"" + "error" + "\"" + ":" + "\"" + errorMessage + "\"" + "}";
			}
				
            httpResponse = "HTTP/1.1 200 OK\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Headers: *\r\n\r\n" + body;
            
			System.out.println("Sending following response\r\n"+ httpResponse);
			System.out.println();
			System.out.println();
			
			clientSocket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
			
			clientSocket.close();
        }
    }

}
