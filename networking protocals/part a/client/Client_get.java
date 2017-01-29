import java.io.*;
import java.net.Socket;

public class Client_get {
	
	 public static void getResponse(String Server, int port, String filename)throws IOException {
		 
	 Socket Clientsocket = new Socket(Server, port);
	   System.out.println("Connection Established to " + Server + ":"	+ port);
	   System.out.println();  
		BufferedReader br = new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));	
		PrintWriter pw = new PrintWriter(Clientsocket.getOutputStream());
		pw.println("GET /" + filename + " HTTP/1.1");
		pw.println("Accept: text/plain, text/html, text/*");
		pw.println();
		pw.flush();

		
		String tvar;
		
		String line = br.readLine();
		System.out.println(line);
		
		if (line == null) {
			System.out.println("HTTP/1.1 404 not found\r\n");
		}
		else if (line.contains("200")) {
			System.out.println(line);
			while ((tvar = br.readLine()) != null)
				System.out.println(tvar);
			br.close();
		} 
		Clientsocket.close();
		
	}
}
