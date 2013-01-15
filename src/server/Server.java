package server;

import java.io.*;
import java.net.*;
import java.util.*;

import javagame.Play;
import javagame.Projectiles;

public class Server {

	private ServerSocket ss;
	private Hashtable<Socket, DataOutputStream> outputStreams = new Hashtable<Socket, DataOutputStream>();
	static final int PORT = 4444;

	Projectiles projectiles;

	public Server(int port, Projectiles projectiles) throws IOException {
		//Set projectiles before running listen(its an endless loop...)
		this.projectiles = projectiles;
		
		listen(port);		
	}

	private void listen(int port) throws IOException {
		ss = new ServerSocket(port);
		System.out.println("Listening on " + ss);
		final Server server = this;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (Play.isUpdate()) {
						Multiplayer.update(server);
					}
				}
			}
		};
		Thread thread = new Thread(r);
		thread.start();
		while (true) {
			Socket s = ss.accept();
			System.out.println("Connection from " + s);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			outputStreams.put(s, dout);
			new ServerThread(this, s, projectiles);
		}
	}

	Enumeration<DataOutputStream> getOutputStreams() {
		return outputStreams.elements();
	}

	void sendToAll(String message) {
		// System.out.println("Recieved: "+message);
		System.out.println("Sending message.");
		synchronized (outputStreams) {
			for (Enumeration<DataOutputStream> e = getOutputStreams(); e
					.hasMoreElements();) {
				DataOutputStream dout = (DataOutputStream) e.nextElement();
				try {
					dout.writeUTF(message);
				} catch (IOException ie) {
					ie.printStackTrace();
				}
			}
		}
	}

	void removeConnection(Socket s) {
		synchronized (outputStreams) {
			outputStreams.remove(s);
			try {
				s.close();
			} catch (IOException ie) {
				ie.printStackTrace();
			}
		}
	}

	// static public void main(String args[]) throws Exception{
	// new Server(PORT);
	// }

}