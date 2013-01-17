package javagame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState{

	public String mouse = "no input yet!";
	Color colorA = Color.white;
	Color colorB = Color.white;
	
	public Menu(int state){
		
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		g.setColor(Color.white);
		g.drawString("HEIST", 480, 370);
		
		g.drawString("HOST", 240, 480);
		g.drawString("JOIN", 770, 480);
		
		
		g.setColor(colorA);
		g.fillRect(200,500,100,50); // x,y,width,height
		
		g.setColor(colorB);
		g.fillRect(750,500,100,50); // x,y,width,height
		
	}
	
	//Supressing static on host.
	@SuppressWarnings("static-access")
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		int xPos = Mouse.getX();
		int yPos = Mouse.getY();
	
		if(xPos>200 && xPos<300 && yPos<300){
			colorA = Color.red;
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
				System.out.println("sbg: "  );
				DummyPlay asd = (DummyPlay) sbg.getState(1);
				asd.play.host = true;
				asd.play.setupMultiplayer();
			}
		} else {
			colorA = Color.white;
		}
		
		if(xPos>750 && xPos<850 && yPos<300){
			colorB = Color.red;
			if(input.isMouseButtonDown(0)){
				sbg.enterState(1);
				System.out.println("sbg: "  );
				DummyPlay asd = (DummyPlay) sbg.getState(1);
				asd.play.host = false;
				asd.play.setupMultiplayer();
			}
		} else {
			colorB = Color.white;
		}
	}

	@Override
	public int getID() {
		return 0;
	}
	
}
