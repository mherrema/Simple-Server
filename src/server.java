import java.io.*;
import java.net.*;
import java.util.Scanner;


public class server {

	
	public static void main(String[] args) throws IOException {
        
//        if (args.length != 1) {
//            System.err.println("Usage: java server <port number>");
//            System.exit(1);
//        }
 
        int portNumber = Integer.parseInt("4444");
 
        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintStream out =
                new PrintStream(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ) {
         
            String inputLine, outputLine;
            boolean gotHeader = false;
            String[] inputHeader = null;
            
            
            
            // Initiate conversation with client
            outputLine = processInput(null);
//            out.println(outputLine);
 
            while ((inputLine = in.readLine()) != null) {
            	if(inputLine.equals("")){
            		break;
            	}
            	if(!gotHeader){
            		inputHeader = inputLine.split(" ");
            		gotHeader = true;
            	}
            	System.out.println(inputLine);
            	
            	
            	
//                outputLine = processInput(inputLine);
//                out.println(outputLine);
                
                
            }
//            out.println(inputHeader[1]);
            File file = new File(inputHeader[1].substring(1, inputHeader[1].length()));
            if (!file.exists()) {
              out.println("HTTP/1.1 404 NOT FOUND");
              out.println("Content-Type: \"text/plain\"");
              out.println("Content-Length: 33");
              out.println("Connection: close");
              out.println("");
              out.println("File not found");
            }
            else{
            	InputStream fileIn = null;
                try {
                	
                	String[] fileNameArray = inputHeader[1].split(".");
                	System.out.println(fileNameArray[0]);
                	
                	
                   fileIn = new FileInputStream(file); // Can also pass the constructor a String
                    byte[] buffer = new byte[1024];
                    int amount_read = fileIn.read(buffer); // read up to 1024 bytes of raw data

                    // You need to wrap the InputStream inside a BufferedReader to
                    // access character data.
                    BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileIn));

                    String line_of_text = fileReader.readLine(); // read one line of text
                    out.println(line_of_text);
                    // Two other options if you are reading the file as text only are FileReader and
                    // Scanner:

                    BufferedReader fileReader2 = new BufferedReader(new FileReader(file));
                    fileReader2.readLine();

                    Scanner input = new Scanner(file);
                    while (input.hasNext()) {
                        input.nextLine();
                   }
            }
                finally{
                	
                }
            
        }
            } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
	
	public static String processInput(String theInput) throws IOException {
        String theOutput = null;
        
        
        
//        URL yahoo = new URL("http://www.yahoo.com/");
//        URLConnection yc = yahoo.openConnection();
//        BufferedReader in = new BufferedReader(
//                                new InputStreamReader(
//                                yc.getInputStream()));
//        String inputLine;
//
//        while ((inputLine = in.readLine()) != null) 
//            if(theOutput == null){
//            	theOutput = inputLine;
//            }
//            else{
////            	theOutput+= "\n";
//            	theOutput+= inputLine;
//            }
//        in.close();
        
        return "Hello World";
    }
	
}
