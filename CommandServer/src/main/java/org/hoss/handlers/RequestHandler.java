package org.hoss.handlers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.net.ServerSocket;
import java.net.Socket;

public class RequestHandler implements Runnable {

	private final Socket client;
	ServerSocket serverSocket = null;

	public RequestHandler(Socket client) {
		this.client = client;
	}

	@Override
	public void run() {
		try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));) {
			System.out.println("Thread Started with name: " + Thread.currentThread().getName());
			String userInput;
			while ((userInput = in.readLine()) != null) {
				userInput = userInput.replace("[^A-Za-z0-9 ]", "");
				System.out.println("Received message from : " + Thread.currentThread().getName() + " : " + userInput);
				if (userInput.trim().length()>0) {
				out.write("Server Received : " + userInput);
				out.newLine();
				out.flush();
				ProcessBuilder builder = new ProcessBuilder();
				String [] parts = userInput.split("#");
				builder.command(parts);
				builder.directory(new File(System.getProperty("user.home")));
				builder.redirectOutput(Redirect.INHERIT);
				Process process = builder.start();
				} else {
					out.write("No Command Received");
					out.newLine();
					out.flush();
				}
				

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
