package chat2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sunny
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class server implements Runnable{
	public ServerSocket socket;
	public List<Thread> clientListenThreads = new ArrayList<Thread>();
	public List<Socket> connectionSockets = new ArrayList<Socket>();
        public List<String> usernames = new ArrayList<String>();
	
	public void start() {
               startListeningOnPort(5000); 
               System.out.println("Server Started..\nWaiting for Clients..");
		while(true) {
			Socket clientSocket = waitAndReceiveConnection();
			connectionSockets.add(clientSocket);
			setupClientListenThread(clientListenThreads.size(), clientSocket);
		}
	}
        
	public void startListeningOnPort(int port) {
		try {
			socket = new ServerSocket(port);
		} catch(IOException e) {
			System.err.println("Couldn't open server socket.");
			e.printStackTrace();
		}
	}
	
	public Socket waitAndReceiveConnection() {
		try {
			Socket clientSocket = socket.accept();
			//Inet address is better from remote IP address
			System.out.println("Connected to " + clientSocket.getInetAddress().toString());
			return clientSocket;
		} catch(IOException e) {
			System.err.println("Error on receiving incoming connection.");
			e.printStackTrace();
		}
		return null;
	}
	
	public void setupClientListenThread(int index, Socket clientSocket) {
		ClientListener clientListener = new ClientListener(index, clientSocket);
		Thread clientThread = new Thread(clientListener);
		clientListenThreads.add(clientThread);
		clientThread.start();
	}

    @Override
    public void run() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        start();
    
    }
	
	class ClientListener implements Runnable {
		public int indexInLists;
		public Socket clientSocket;
		public BufferedReader inStream;
		public String userName;
		
		public ClientListener(int index, Socket clientSocket) {
			indexInLists = index;
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
		
		public void setupInStream() {
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
		
		public void listenForAndForwardMsgs() {
			try {
				String msg;
				while((msg = inStream.readLine()) != null) {
					// TODO: Implement empty message checking
					sendMessage(userName, msg);
				}
			} catch(IOException e) {
				System.err.println("Problem on message receive/forward.");
				e.printStackTrace();
			}
		}
	}
	
	public void sendMessage(String userName, String msg) {
		
		for (Socket s : connectionSockets) {
			PrintWriter socketOutput = getSocketOutWriter(s);
			socketOutput.println(userName + ": " + msg); 
			socketOutput.flush();
		}
	}
	
	public PrintWriter getSocketOutWriter(Socket socket) {
		try {
			PrintWriter socketOutput = new PrintWriter(socket.getOutputStream());
			return socketOutput;
		} catch(IOException e) {
			System.err.println("Error on getting socket OutputStream.");
			e.printStackTrace();
		}
		return null;
	}
}

