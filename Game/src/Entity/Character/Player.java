package Entity.Character;

import java.awt.*;
import java.awt.image.BufferedImage;
import Entity.Entity;
import Graphics.Sprites;
import Main.Handler;
import Graphics.Animation;

public class Player extends Character {
	// Animations
	private Animation rightCaraAnim, leftCaraAnim, downCaraAnim, upCaraAnim, lastAnim;
	private int i = 0;
	
	public Player(Handler Handler,Identifier id, float PosX, float PosY) {
		super(Handler, DefaultWidth, DefaultHeight, id, PosX, PosY);

		colBoundary.x = 12;
		colBoundary.y = 32;
		colBoundary.width = 38;
		colBoundary.height = 24;

		//Animations init
		rightCaraAnim = new Animation(100, Sprites.CaraLoftRight);
		leftCaraAnim = new Animation(100, Sprites.CaraLoftLeft);
		downCaraAnim = new Animation(100, Sprites.CaraLoftFront);
		upCaraAnim = new Animation(100, Sprites.CaraLoftBack);
		lastAnim = downCaraAnim;

	}
	
	private void GetKeyboardInput() {
		XSpeed = 0;
		YSpeed = 0;

		collision();
		
		if(Handler.GetKeyboardInput().W == true) {
			YSpeed = -Speed * SpeedFactor;
		}
		if(Handler.GetKeyboardInput().A == true) {
			XSpeed = -Speed * SpeedFactor;
		}
		if(Handler.GetKeyboardInput().S == true) {
			YSpeed = Speed * SpeedFactor;
		}
		if(Handler.GetKeyboardInput().D == true) {
			XSpeed = Speed * SpeedFactor;
		}
		
	}
	private void collision(){
		for(int i = 0; i < Handler.objects.size(); i++) {
			Entity tempObject = Handler.objects.get(i);
			if (tempObject.getId() == Identifier.Wall) {
				if (getBounds().intersects(tempObject.getBounds())) {
					PosX += XSpeed * -1;
					PosY += YSpeed * -1;
				}
			}
		}
	}
	
	@Override
	public void Tick() {
		//Animations
		rightCaraAnim.tick();
		leftCaraAnim.tick();
		downCaraAnim.tick();
		upCaraAnim.tick();

		GetKeyboardInput();
		MovementSpeed();
		Handler.GetCamera().Centre(this);
	}

	@Override
	public void Render(Graphics GraphicsObj) { 
		//Hard coded spritesheet of 4 column, doesnt really work
		//TODO
		GraphicsObj.drawImage(getCurrentAnimationFrame(), (int)(PosX - Handler.GetCamera().GetOffsetX()), (int)(PosY - Handler.GetCamera().GetOffsetY()), Width, Height, null);
	}

	@Override
	public Identifier getId() {
		return id;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)PosX, (int)PosY, Width, Height);
	}

	private BufferedImage getCurrentAnimationFrame(){ // make the correct animation come up depending on the direction

		if(XSpeed < 0){
			lastAnim = leftCaraAnim;
			return leftCaraAnim.getCurrentFrame();
		}else if(XSpeed > 0){
			lastAnim = rightCaraAnim;
			return rightCaraAnim.getCurrentFrame();
		}else if(YSpeed < 0){
			lastAnim = upCaraAnim;
			return upCaraAnim.getCurrentFrame();
		}else if(YSpeed > 0){
			lastAnim = downCaraAnim;
			return downCaraAnim.getCurrentFrame();
		}else{
			return lastAnim.getFirstFrame();
		}
	}
}