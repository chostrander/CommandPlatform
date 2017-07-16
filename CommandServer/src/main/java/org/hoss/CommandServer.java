package org.hoss;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.hoss.handlers.RequestHandler;

public class CommandServer {

	public static void main(String[] args) {
		int portNumber = 2000;
		
		
		System.out.println("Initiating Server...");
		try (ServerSocket serverSocket = new ServerSocket(portNumber);) {
			 Executor executor = Executors.newFixedThreadPool(5);
			System.out.println("Waiting for Clients...");
			while (true) {
				Socket client = serverSocket.accept();
				Runnable worker = new RequestHandler(client);
				executor.execute(worker);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

}
