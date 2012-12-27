package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import javagame.Play;

public class Client {

	DataInputStream din;
	static DataOutputStream dout;
	
	public Client(DataInputStream din, DataOutputStream dout) {
		this.din  = din;
		Client.dout = dout;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (Play.isUpdate()) {
						Multiplayer.update(null);
					}
				}
			}
		};
		Thread thread = new Thread(r);
		thread.start();
		new ClientThread(din);
	}
	
	public static void sendToAll(String message) {
		try {
			dout.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

class ClientThread extends Thread {
	
	DataInputStream din;
	
	public ClientThread(DataInputStream din) {
		this.din = din;
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				String message = din.readUTF();
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(message);
				JSON.parseJson(element, Play.projectiles);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}