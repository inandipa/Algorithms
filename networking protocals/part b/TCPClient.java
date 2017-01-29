import java.net.*;  
import java.io.*;  
import java.lang.*;

public class TCPClient {

  public static void main(String[] args) throws IOException {
	  
	  String msgsent = "";
	  String padding = "AAAAAAAAAAA";  // input character string need to be updated. 
	  long starttime = 0;
	  long [][] ETE  = new long [5][1000];
	  long [] ETEmax = new long[5];
	  long [] ETEavg = new long[5];

    if ((args.length < 1) || (args.length > 2))  // Test for correct # of args
      throw new IllegalArgumentException("Parameter(s): <Server> [<Port>]");

    String server = args[0];       // Server name or IP address
   

    int servPort = Integer.parseInt(args[1]);
	Socket socket=null;
    // Create socket that is connected to server on specified port
   try{
  
	
	 socket = new Socket(InetAddress.getByName(server), servPort);
	System.out.println("Socket created Successfully");
    }
    catch(Exception ex){
    	ex.printStackTrace();
    }
	System.out.println("Connected to server...sending echo string");


    InputStream in = socket.getInputStream();
    OutputStream out = socket.getOutputStream();
	for (int repeat=0;repeat<5;repeat++){
		for(int seq=0;seq<1000;seq++){
			msgsent = seq + 1 + padding;
			 byte[] byteBuffer = msgsent.getBytes();
			starttime=System.nanoTime();
			//System.out.println("Starting time"+ starttime );
			out.write(byteBuffer);//Send the encoded string to server
		
			
      // Receive the same string back from the server
          int totalBytesRcvd = 0;  // Total bytes received so far
          int bytesRcvd;           // Bytes received in last read
          while (totalBytesRcvd < byteBuffer.length) {
             if ((bytesRcvd = in.read(byteBuffer, totalBytesRcvd,  
                        byteBuffer.length - totalBytesRcvd)) == -1)
             throw new SocketException("Connection close prematurely");
             totalBytesRcvd += bytesRcvd;
        }
		
    //System.out.println("Received: " + new String(byteBuffer));
	ETE[repeat][seq]= (System.nanoTime() - starttime)/2 ;

			
		}
		
		
		int flag = 0;
		 // System.out.println("Length of array ETE "+rep+"= "+ETE[rep].length);
		  ETEmax[repeat] = ETE[repeat][0];
		  ETEavg[repeat] = ETE[repeat][0];
		  for ( int i = 1; i < ETE[repeat].length; i++) {
		      if ( ETE[repeat][i] > ETEmax[repeat]) {
		        ETEmax[repeat] = ETE[repeat][i];
		        flag = i+1;
		      }
		      ETEavg[repeat] = ETEavg[repeat] + ETE[repeat][i];
		  }
		  ETEavg[repeat] = ETEavg[repeat]/1000;
		  System.out.println("Round Number : "+ repeat);
		  System.out.println("Sequence Number for max. ETE = "+flag);
		  System.out.println("Maximum ETE = "+ETEmax[repeat]);
		  System.out.println("Average ETE = "+ETEavg[repeat]);
		  System.out.println();
		
	
	}

    socket.close(); 
  
  }
}