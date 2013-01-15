package server;

import java.io.*;
import java.net.*;

import javagame.Projectiles;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ServerThread extends Thread
{
	// The Server that spawned us
	private Server server;
	// The Socket connected to our client
	private Socket socket;
	
	Projectiles projectiles;

	// Constructor.
	public ServerThread(Server server, Socket socket, Projectiles projectiles) {
		// Save the parameters
		this.server = server;
		this.socket = socket;
		this.projectiles = projectiles;
		// Start up the thread
		start();
	}

	// This runs in a separate thread when start() is called in the
	// constructor.
	public void run() {
		try {
			// Create a DataInputStream for communication; the client
			// is using a DataOutputStream to write to us
			DataInputStream din = new DataInputStream( socket.getInputStream() );
			// Over and over, forever ...
			while (true) {
			// ... read the next message ...
			String message = din.readUTF();
			
			System.out.println("server msg read: " + message);
		
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(message);
			JSON.parseJson(element, projectiles);
			// ... tell the world ...
			//System.out.println("Sending message");
			// ... and have the server send it to all clients
			server.sendToAll( message );
			}
		} catch( EOFException ie ) {
			// This doesn't need an error message
		} catch( IOException ie ) {
			// This does; tell the world!
			ie.printStackTrace();
		} finally {
			// The connection is closed for one reason or another,
			// so have the server dealing with it
			server.removeConnection(socket);
		}
	}
}