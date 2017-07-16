package org.hoss.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TheClient {

	public static void main(String[] args) {
		int portNumber = 2000;
		String hostname = "localhost";

		try (Socket echoSocket = new Socket(hostname, portNumber);
				PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));) {
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				if (userInput.equalsIgnoreCase("quit")) {
					break;
				}
				out.println(userInput);
				System.out.println("echo: " + in.readLine());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
