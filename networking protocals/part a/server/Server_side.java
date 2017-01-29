
import java.io.*;
import java.net.*;

public class Server_side {

	public static void main(String args[]) throws Exception {
		System.out.println("The server is up and running");
		int port = Integer.parseInt(args[0]);
		int clientNumber = 0;
		
		ServerSocket server = new ServerSocket(port);
		
		try {
			while (true) {
				new service(server.accept(), clientNumber++).start();
			}
		} finally {
			server.close();
		}
	}

	private static class service extends Thread {
		private Socket socket;
		private int clientNumber;

		public service(Socket socket, int clientNumber) {
			this.socket = socket;
			this.clientNumber = clientNumber;
			System.out.println("Serving client at " + socket);
		}

		public void run() {
			try {
				InputStream in = socket.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String request = br.readLine(); 
				String[] requestParam = request.split(" ");
				String path = requestParam[1].substring(1);
				System.out.println("Command Type: " + request);
				System.out.println("File Requested - " + path);
				DataOutputStream out = new DataOutputStream(
						socket.getOutputStream());

				if (request.contains("GET")) { 
					
					path = System.getProperty("user.dir")+"/"+path;
					System.out.println(path);
					File file = new File(path);
					if (!file.exists()) {
						
						System.out.println("file does not exist");
						out.writeBytes("HTTP/1.1 404 not found\r\n");
						
					}
					else{
					
					out.writeBytes("HTTP/1.1 200 OK\r\n");
					FileReader fr = new FileReader(file);
					BufferedReader bfr = new BufferedReader(fr);
					String line;
					out.flush();
					while ((line = bfr.readLine()) != null) {
						out.writeBytes(line + "\n");
						out.flush();
					}
					System.out.println("Sending Requested File");
					bfr.close();
					br.close();
					}
				}

				else if (request.contains("PUT")) {

					try {
						out.writeBytes("HTTP/1.1 200 OK File Created\r\n");
						out.flush();
						
						path = System.getProperty("user.dir")+"/"+path;
							
					

						request = br.readLine();
						System.out.println(request);
						File file = new File(path);
						System.out.println( "File stored in Path - "+path);
						String line;
						
						BufferedWriter bufferedWriter = new BufferedWriter(
								new FileWriter(file));

						while ((line = br.readLine()) != null) {
							bufferedWriter.write(line);
							bufferedWriter.write("\r\n");
							bufferedWriter.flush();
							System.out.println(line);
						}
						bufferedWriter.flush();
						bufferedWriter.close();
						br.close();
						System.out.println("\nFile Copied ");
					} catch (IOException e) {
						System.err.println("Missing file from the client: "
								+ e.getMessage());
						br.close();
					}
				} else {
					System.out.println("Enter either GET or PUT");
				}

			} catch (IOException e) {
				System.out.println("Error handling client# " + clientNumber
						+ ": " + e.getMessage());
			} finally {
				try {
					//System.out.println("Closing Socket...");
					socket.close();
				} catch (IOException e) {
					System.out.println("Couldn't close a socket:"+e.getMessage());
				}
			}
		}

	}
}