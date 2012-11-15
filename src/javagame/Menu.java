package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{

	public String mouse = "no input yet!";
	
	public Menu(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.drawString(mouse, 265, 110);
		
		g.fillRect(280,200,100,60); // x,y,width,height
		g.drawString("HEIST", 295, 170);
		g.drawString("CODE 211", 290, 230);
		
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
		mouse = "xPos: " + xPos + " yPos: " + yPos;
	
		if(xPos>75){
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
			}
		}
	}

	@Override
	public int getID() {
		return 0;
	}
	
}
