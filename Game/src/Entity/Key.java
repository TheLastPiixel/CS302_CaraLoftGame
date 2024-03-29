package Entity;

import java.awt.Graphics;

import Entity.Character.Identifier;
import Graphics.Sprites;

public class Key extends Item{

	public Key(Main.Handler Handler, int Width, int Height, Identifier id, float PosX, float PosY) {
		super(Handler, Width, Height, Identifier.Key, PosX, PosY);
		colBoundary.x = 0;
		colBoundary.y = 0;
		colBoundary.width = 64;
		colBoundary.height = 64;
	}

	public void Tick() {
	}

	public void Render(Graphics GraphicsObj) {
		GraphicsObj.drawImage(Sprites.Key, (int) (PosX - Handler.GetCamera().GetOffsetX()),
                (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);	
	}
	
	@Override
	public void Dead() {
		
	}
	
	//GETTERS & SETTERS
	public Identifier getId(){
		return Identifier.Key;
	}


}
