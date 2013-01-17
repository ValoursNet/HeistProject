package javagame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class DummyPlay extends BasicGameState {

	Play play = null;
	
	public DummyPlay(int num) {
		play = new Play(num);
	}

	@Override
	public void enter(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		
	}

	@Override
	public int getID() {
		return play.getID();
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		play.init(arg0, arg1);
	}

	@Override
	public void leave(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		//play.leave(arg0, arg1);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		play.render(arg0, arg1, arg2);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2)	throws SlickException {
		play.update(arg0, arg1, arg2);
	}

}
