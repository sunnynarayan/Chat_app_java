package chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

class ClientListener implements Runnable {
		private Socket clientSocket;
		private BufferedReader inStream;
		private String userName;
		
		public ClientListener(Socket clientSocket) {
			this.clientSocket = clientSocket;
			setupInStream();
                    try {
                        userName = inStream.readLine();
                    } catch (IOException ex) {
                        Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);//..........
                    }
			// TODO: Give proper option to set name
//			userName = "User " + index;
		}
		
		private void setupInStream() {
			try {
				inStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			} catch(IOException e) {
				System.err.println("Problem getting client incoming stream.");
				e.printStackTrace();
			}
		}
		
		public void run() {
			listenForAndForwardMsgs();
		}
		
		private void listenForAndForwardMsgs() {
			try {
				String msg;
				while((msg = inStream.readLine()) != null) {
					// TODO: Implement empty message checking
//					sendMessage(userName, msg);
				}
			} catch(IOException e) {
				System.err.println("Problem on message receive/forward.");
				e.printStackTrace();
			}
		}
	}