package javagame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import person.Cop;
import person.Criminal;
import person.Person;
import server.Client;
import server.Multiplayer;
import server.Server;

//Suppressing unused multiplayer
//@SuppressWarnings("unused")
public class Play {

	public static boolean host = true;
	String ip = "127.0.0.1";
	Socket socket;
	DataInputStream din;
	DataOutputStream dout;

	static final int PORT = 4444;

	public Image player;
	public Image wall;
	public static Image cop;

	public static Projectiles projectiles = new Projectiles();

	Render renderObj = new Render();
	UserInput inputObj = new UserInput();

	Map levelOne = new Map();
	Cop policeUnit;
	public static Criminal playerUnit;

	Server server;

	public static HashSet<Person> people = new HashSet<Person>();

	float offsetX = 0;
	float offsetY = 0;

	boolean drawDebug = false;

	public static boolean update = false;

	public Play(int state) {

	}

	public Play() throws SlickException {
		player = new Image("res/RobberM4.png");
		cop = new Image("res/RobberM4.png");
	}

	public int getID() {
		return 1;
	}

	public void init(GameContainer arg0, StateBasedGame arg1)
			throws SlickException {
		
		
		
		player = new Image("res/RobberM4.png");
		 cop = new Image("res/RobberM4.png");

		projectiles.setMap(levelOne);

		policeUnit = new Cop(levelOne, projectiles);
		playerUnit = new Criminal(levelOne, projectiles);

		renderObj.setImages(player, wall, cop);
		renderObj.setMap(levelOne);
		renderObj.setProjectiles(projectiles);

		//people.add(policeUnit);
		people.add(playerUnit);
		Multiplayer.people.add(playerUnit);

		System.out.println("name:" + playerUnit.name);
	}
	
	public void setupMultiplayer(){
		
		
		
		System.out.println("GOGOGOG > HOSTING:" + host);
		
		if (host) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println("SERVVVVVVVEEEEEEERRRRRR");
						server = new Server(PORT, projectiles);
					} catch (IOException e) {
						System.out.println("Server could not start.");
						e.printStackTrace();
					}
				}
			};
			Thread thread = new Thread(r);
			thread.start();
		} else {
			try {
				socket = new Socket(ip, PORT);
				System.out.println("Connected to " + socket);
				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());
				new Client(din, dout);
			} catch (IOException ie) {
				System.out.println("Cannot connect to " + ip + " on port "
						+ String.valueOf(PORT) + ".");
				System.exit(0);
			}
		}
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		offsetX = (float) playerUnit.offsetX;
		offsetY = (float) playerUnit.offsetY;
		renderObj.update(gc, sbg, g, offsetX, offsetY, people);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		inputObj.inputHandler(playerUnit, gc, offsetX, offsetY);
		
		//crashes if certain person is removed?
		for (Person p : people) {
			//p.update();
			if(!p.isDead){
				//people.remove(p);
				p.update();
			}
		}
		//policeUnit.update();
		//Player.update();
		update = true;
	}

	public static boolean isUpdate() {
		if (update) {
			update = false;
			return true;
		} else {
			return false;
		}
	}

}
