package States;

import Entity.Character.Enemy;
import Entity.Character.Boss;
import Entity.Character.Player;
import Entity.Character.Identifier;
import Entity.Apple;
import Entity.Entity;
import Entity.Item;
import Entity.Key;
import Graphics.Sounds;
import Graphics.TextureLoader;
import Rooms.Rooms;
import Main.Handler;
import Main.Helpers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.util.Comparator;

public class  StateGame extends StatesAbstract {

	private Player CaraLoft;
	private Enemy enemy1, enemy2, enemy3, enemy4, enemy5, enemy6;
	private Boss Boss;
	private Apple apple1, apple2, apple3;
	private Key key1;
	private Rooms Outside;
	private Rooms Hall;
	private Rooms Office;
	private int CurrentRoom = 0;
	private boolean PauseScreen = false;
	public Comparator<Entity> SortRender = new Comparator<Entity>(){
		@Override
		public int compare(Entity a, Entity b){
			if(a.GetPosY() + a.GetHeight() < b.GetPosY() + b.GetHeight()){
				return -1;
			}
			return 1;
		}
	};

	
	public StateGame(Handler Handler) {
		super(Handler);
		CurrentRoom = 0;
		PauseScreen = false;
		
		//Loads up the layouts for each room
		Outside = new Rooms(Handler ,"resources/rooms/outside.txt");
		Hall = new Rooms(Handler, "resources/rooms/hall.txt");
		Office = new Rooms(Handler, "resources/rooms/office.txt");
		Handler.SetRoom(Outside);

		//Add objects
		Handler.addEntity(CaraLoft = new Player(Handler, Identifier.Player, 800, 800));
		Handler.addEntity(enemy1 = new Enemy(Handler, Identifier.Enemy, 200, 200, CaraLoft));
		Handler.addEntity(enemy2 = new Enemy(Handler, Identifier.Enemy, 500, 400, CaraLoft));
		Handler.addEntity(enemy3 = new Enemy(Handler, Identifier.Enemy, 400, 700, CaraLoft));
		Handler.addEntity(enemy4 = new Enemy(Handler, Identifier.Enemy, 1200, 550, CaraLoft));
		Handler.addEntity(apple1 = new Apple(Handler, 500, 500));
		Handler.addEntity(apple2 = new Apple(Handler, 1000, 1000));
		Handler.addEntity(apple3 = new Apple(Handler, 1400, 800));

	}


	@Override
	public void Render(Graphics GraphicsObj) {
		if(CurrentRoom == 0) {
			Outside.Render(GraphicsObj);
			//enemy1.Render(GraphicsObj);
		}
		else if(CurrentRoom == 1) {
			Hall.Render(GraphicsObj);
			//enemy2.Render(GraphicsObj);
		}
		else if (CurrentRoom == 2) {
			Office.Render(GraphicsObj);
			//enemy3.Render(GraphicsObj);
		}
		else {
			Outside.Render(GraphicsObj);
			CurrentRoom = 0;
		}
		for(int i = 0; i < Handler.getEntities().size(); i++){
			Handler.getEntities().get(i).Render(GraphicsObj);
		}
		
		
		//Draws the player UI
		Font Font1 = new Font("Arial", Font.BOLD, 20);
		Font Font2 = new Font("Arial", Font.BOLD, 15);
		
		GraphicsObj.setColor(Color.black);
		GraphicsObj.fillRect(615, 795, 210, 60);
		GraphicsObj.setColor(Color.green);
		GraphicsObj.fillRect(620, 800, CaraLoft.GetHealth()*2, 50);
		GraphicsObj.setFont(Font1);
		GraphicsObj.setColor(Color.white);
		GraphicsObj.drawString("Health: " + CaraLoft.GetHealth(), 675, 830);
		
		
		//Draw Player's objctive
		GraphicsObj.setFont(Font2);
		GraphicsObj.drawString("Objective: " + Helpers.GetObjective(CurrentRoom), 50, 50);
		
		//Draw Time Left
		GraphicsObj.setFont(Font1);
		GraphicsObj.setColor(Color.white);
		if (Handler.GetMain().GetSecondsleft() > 9) {
			GraphicsObj.drawString("Time Left: " + (int)Handler.GetMain().GetMinutesLeft() + ":" + (int)Handler.GetMain().GetSecondsleft(), 650, 50);	
		}
		else {
			GraphicsObj.drawString("Time Left: " + (int)Handler.GetMain().GetMinutesLeft() + ":0" + (int)Handler.GetMain().GetSecondsleft(), 650, 50);
		}
		
		//Draw Stunned
		if (CaraLoft.GetCurrentlyStunned() == true) {
			GraphicsObj.setFont(Font1);
			GraphicsObj.drawString("STUNNED!", 700, 430);
		} 
		
		//Inventory Screen
		if (Handler.GetKeyboardInput().I == true) {
			GraphicsObj.drawImage(TextureLoader.Image("/textures/Inventory.png"), 1109, 707, null);
			if (CaraLoft.getHasKey() == true) {
				GraphicsObj.drawImage(TextureLoader.Image("/textures/Key.png"), 1114, 712, null);
			}
		}
		
	}

	@Override
	public void Tick() {
		//Cheat to boss room
		if(Handler.GetKeyboardInput().PageDown) {
			Handler.clearEntities();
			Handler.SetRoom(Office);
			CurrentRoom = 2;
			CaraLoft.SetPosX(Office.GetInitialX() * 64);
			CaraLoft.SetPosY(Office.GetInitialY() * 64);
			Handler.addEntity(Boss = new Boss(Handler, Identifier.Boss, 500, 200, CaraLoft));
			Handler.addEntity(apple1 = new Apple(Handler,800, 700));
			Handler.addEntity(apple2 = new Apple(Handler,1000, 750));
			Handler.addEntity(apple3 = new Apple(Handler,850, 800));
		}
		
		//Checks which room to Tick
		if (CurrentRoom == 0) {
			Outside.Tick();
			//enemy1.Tick();
		}
		else if (CurrentRoom == 1) {
			Hall.Tick();
			//enemy2.Tick();
		}
		else if (CurrentRoom == 2) {
			Office.Tick();
			//enemy3.Tick();
		}
		
		//Pickup Items
		for(int i = 0; i < Handler.getEntities().size(); i++){
			Handler.getEntities().get(i).Tick();
			Entity Entity = Handler.getEntities().get(i);
			
			if(Handler.getEntities().get(i).getId() == Identifier.Key){
				if(Handler.getEntities().get(i).getBounds().intersects(CaraLoft.getBounds())){
					Handler.getEntities().remove(i);
					CaraLoft.setHasKey(true);
				}
			}else if (Handler.getEntities().get(i).getId() == Identifier.Apple){
				if(Handler.getEntities().get(i).getBounds().intersects(CaraLoft.getBounds())){
					Handler.getEntities().remove(i);
					CaraLoft.SetHealth(CaraLoft.GetHealth() + 20);
				}
			}
					
			//Removes dead entities
			if(Entity.GetAlive() == false) {
				Handler.getEntities().remove(Handler.getEntities().get(i));
			}
		}
		Handler.getEntities().sort(SortRender);
		
		//Checks if player is trying to move to new room
		if (CurrentRoom == 0) {
			if (CaraLoft.GetPosX() > 11*64 & CaraLoft.GetPosX() < 13*64) { //704 < X 832
				if (CaraLoft.GetPosY() > 64 & CaraLoft.GetPosY() < 2*64) { //0 < 64
					if(Handler.GetMain().getEliminated() > 3) {
						Handler.clearEntities();
						CurrentRoom = 1;
						//Sets the player to the initial position for the room
						CaraLoft.SetPosX(Hall.GetInitialX() * 64);
						CaraLoft.SetPosY(Hall.GetInitialY() * 64);
						Handler.SetRoom(Hall);
						Handler.addEntity(enemy1 = new Enemy(Handler, Identifier.Enemy, 400, 900, CaraLoft));
						Handler.addEntity(enemy2 = new Enemy(Handler, Identifier.Enemy, 400, 1200, CaraLoft));
						Handler.addEntity(enemy3 = new Enemy(Handler, Identifier.Enemy, 1200, 600, CaraLoft));
						Handler.addEntity(enemy4 = new Enemy(Handler, Identifier.Enemy, 900, 1000, CaraLoft));
						Handler.addEntity(enemy5 = new Enemy(Handler, Identifier.Enemy, 1800, 1900, CaraLoft));
						Handler.addEntity(enemy6 = new Enemy(Handler, Identifier.Enemy, 850, 1600, CaraLoft));
						Handler.addEntity(apple1 = new Apple(Handler,200, 1100));
						Handler.addEntity(apple2 = new Apple(Handler,1800, 1800));
						Handler.addEntity(apple3 = new Apple(Handler,1700, 200));
						Handler.addEntity(key1 = new Key(Handler, Item.DefaultWidth, Item.DefaultHeight, Identifier.Key, 250, 1100));
					}
				}
			}
		}
		if (CurrentRoom == 1)
			//Enter the office
			if (CaraLoft.GetPosX() > 15*64 & CaraLoft.GetPosX() < 17*64) { 
				if (CaraLoft.GetPosY() > 64 & CaraLoft.GetPosY() < 2*64) { 
					if (Handler.GetMain().getEliminated() > 9 & CaraLoft.getHasKey()) {
						Handler.clearEntities();
						CurrentRoom = 2;
						//Sets the player to the initial position for the room
						CaraLoft.SetPosX(Office.GetInitialX() * 64);
						CaraLoft.SetPosY(Office.GetInitialY() * 64);
						Handler.SetRoom(Office);
						Handler.addEntity(Boss = new Boss(Handler, Identifier.Boss, 500, 200, CaraLoft));
						Handler.addEntity(apple1 = new Apple(Handler,400, 400));
						Handler.addEntity(apple2 = new Apple(Handler,600, 500));
						Handler.addEntity(apple3 = new Apple(Handler,800, 400));
					}
				}
			}
		
		//Pause screen
		if (Handler.GetKeyboardInput().P == true || Handler.GetKeyboardInput().Esc == true) {
			//if (PauseScreen == false) {
				Handler.GetMain().SetState(Handler.GetMain().StatePause);
				Sounds.playSound(new File("resources/sounds/back_style_2_001.wav"));
				//PauseScreen = true;
			//}
		}
	}
	
}
