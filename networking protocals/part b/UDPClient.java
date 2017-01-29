import java.net.*;  // for DatagramSocket, DatagramPacket, and InetAddress
import java.io.*;   // for IOException

public class UDPClient {

  private static final int TIMEOUT = 3000;   // Resend timeout (milliseconds)
  private static final int MAXTRIES = 5;     // Maximum retransmissions

  public static void main(String[] args) throws IOException {
     
	 String msgsent = "";
	  String padding = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
	  long starttime = 0;
	  long [][] ETE  = new long [5][1000];
	  long [] ETEmax = new long[5];
	  long [] ETEavg = new long[5];
	 
	 
	 
    if ((args.length < 1) || (args.length > 2))  // Test for correct # of args
      throw new IllegalArgumentException("Parameter(s): <Server> <Word> [<Port>]");

    InetAddress serverAddress = InetAddress.getByName(args[0]);  // Server address
   
    int servPort = (args.length == 2) ? Integer.parseInt(args[1]) : 7;

    DatagramSocket socket = new DatagramSocket();

    socket.setSoTimeout(TIMEOUT);  // Maximum receive blocking time (milliseconds)
	
	
	
	for(int repeat=0;repeat<5;repeat++){
		for(int seq=0;seq<1000;seq++){
			
			int tries=0; //packet may be lost,so we have to keep trying
			boolean receivedResponse=false;
			msgsent = seq + 1 + padding;
			byte[] bytesToSend = msgsent.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(bytesToSend,bytesToSend.length, serverAddress, servPort);  //send packet

            DatagramPacket receivePacket = new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);  //receive Packet
			
			
			do{
				socket.send(sendPacket);//Send the echo string
				starttime = System.nanoTime();
			//	System.out.println("Start time" + starttime);
				try{
					//System.out.println("testing");
					socket.receive(receivePacket);//Attempt echo reply reception
					ETE[repeat][seq] = (System.nanoTime() -starttime)/2;
					if(!receivePacket.getAddress().equals(serverAddress)) //check source
					throw new IOException("Received packet from unknown source");
					receivedResponse = true;
				}
				catch(Exception e){
					tries+=1;
					System.out.println("Timed out ,"+(MAXTRIES-tries)+"more tries...");
				}
			}while((!receivedResponse)&&(tries < MAXTRIES));
			
		}
		System.out.println("in the second for loop");
		int flag=0;
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