import java.net.*; 
import java.io.*;  
public class UDPServer {

  private static final int ECHOMAX = 255;  // Maximum size of echo datagram

  public static void main(String[] args) throws IOException {

    int servPort = Integer.parseInt(args[0]);

    DatagramSocket socket = new DatagramSocket(servPort);
	System.out.println("Server is running");
    DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);

    for (;;) {  
      socket.receive(packet);     // Receive packet from client
	  System.out.println("Handling client at " +
        packet.getAddress().getHostAddress() + " on port " + packet.getPort());
	  //System.out.println("Received Message" + new String(packet.getData()));
      socket.send(packet);       // Send the same packet back to client
      packet.setLength(ECHOMAX); // Reset length to avoid shrinking buffer
    }
    
  }
}