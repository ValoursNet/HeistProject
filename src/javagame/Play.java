package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import person.Cop;
import person.Criminal;

public class Play extends BasicGameState{

	Image player;
	Image wall;
	Image cop;
	
	Render renderObj = new Render();
	UserInput inputObj = new UserInput();
	
	Map levelOne = new Map();
	Cop policeUnit = new Cop(levelOne);
	Criminal Player = new Criminal(levelOne);
	
	float offsetX = 0;
	float offsetY = 0;
	
	Projectiles projObj = new Projectiles();
	
	boolean drawDebug = false;
	
	public Play(int state){
	
	}

	@Override
	public int getID() {
		return 1;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		player = new Image("res/PlayerAKDrawn.png");
		cop = new Image("res/PlayerAKDrawn.png");
		
		renderObj.setImages(player,wall,cop);
		renderObj.setMap(levelOne);
		renderObj.setProjectiles(projObj);
		
		System.out.println("name:" + Player.name);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException {
		offsetX = (float) Player.offsetX;
		offsetY = (float) Player.offsetY;
		renderObj.update(gc, sbg, g, offsetX, offsetY, Player, policeUnit);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException {
		inputObj.inputHandler(Player, gc, offsetX, offsetY);
		policeUnit.update();
		Player.update();
	}
}
