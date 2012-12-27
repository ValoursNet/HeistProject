package javagame;

import java.util.HashSet;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import person.Cop;
import person.Criminal;
import person.Person;

public class Play extends BasicGameState{

	public Image player;
	public Image wall;
	public Image cop;
	
	public Projectiles projectiles = new Projectiles();
	
	Render renderObj = new Render();
	UserInput inputObj = new UserInput();
	
	Map levelOne = new Map();
	Cop policeUnit;
	Criminal Player;
	
	public static HashSet<Person> people = new HashSet<Person>();
	
	float offsetX = 0;
	float offsetY = 0;
	
	boolean drawDebug = false;
	
	public Play(int state){
	
	}

	public Play() throws SlickException {
		player = new Image("res/PlayerAKDrawn.png");
		cop = new Image("res/PlayerAKDrawn.png");
	}
	
	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//player = new Image("res/PlayerAKDrawn.png");
		//cop = new Image("res/PlayerAKDrawn.png");
		
		projectiles.setMap(levelOne);
		
		policeUnit = new Cop(levelOne, projectiles);
		Player = new Criminal(levelOne, projectiles);
		
		renderObj.setImages(player,wall,cop);
		renderObj.setMap(levelOne);
		renderObj.setProjectiles(projectiles);
		
		people.add(policeUnit);
		people.add(Player);
		
		System.out.println("name:" + Player.name);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		offsetX = (float) Player.offsetX;
		offsetY = (float) Player.offsetY;
		renderObj.update(gc, sbg, g, offsetX, offsetY, people);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		inputObj.inputHandler(Player, gc, offsetX, offsetY);
		policeUnit.update();
		Player.update();
	}
	
}
