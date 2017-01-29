
import java.io.*;


public class Client_side {
	
		
		public static void main(String[] args) throws IOException {
			
			String Server = args[0];   // server ip or name 
			int port = Integer.parseInt(args[1]);   // port number
			String commandtype = args[2];   // PUT or GET
			String filename = args[3];    // file name
			 
			
			
			if (commandtype.equalsIgnoreCase("get")) {
				    Client_get.getResponse(Server, port, filename);
				
			} else if (commandtype.equalsIgnoreCase("put")) {
				
					Client_put.putResponse(Server, port,filename);
			} else {
				System.out.println("Only GET or PUT supported");
			}
			
	}
		
	
}
