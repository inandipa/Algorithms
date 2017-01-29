import java.io.*;
import java.net.Socket;

public class Client_put {
	 public static void putResponse(String Server, int port, String filename)throws IOException {
		 
		 Socket Clientsocket = new Socket(Server, port);
		   System.out.println("Connection Established to " + Server + ":"	+ port);
		   System.out.println();  
			
		   PrintWriter pw = new PrintWriter(Clientsocket.getOutputStream());
			pw.print("PUT /" + filename + " HTTP/1.1\r\n");
			pw.print("Accept: text/plain, text/html, text/*\r\n\r\n");
			
			
			// send a file code
			try {
				filename = System.getProperty("user.dir")+"/"+filename;
				FileReader fr = new FileReader(filename);
				BufferedReader bfr = new BufferedReader(fr);
				String line;

				while ((line = bfr.readLine()) != null) {
					pw.write(line);
			//		System.out.println(line);
				}
				pw.flush();

				// Receive OK 201
				BufferedReader br = new BufferedReader(new InputStreamReader(
						Clientsocket.getInputStream()));
				System.out.println(br.readLine());
				br.close();
				bfr.close();

			} catch (IOException e) {
				System.err.println(e.getMessage());
				pw.close();
			}
		   Clientsocket.close();
	 }
}
