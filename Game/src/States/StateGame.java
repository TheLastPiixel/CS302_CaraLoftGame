package States;

import Graphics.Sprites;
import Main.Main;
import Rooms.Rooms;
import Tiles.Tiles;
import Main.Handler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Entity.Character.Player;

public class  StateGame extends StatesAbstract {

	private Player CaraLoft;
	private Rooms Outside;
	private Rooms Hall;
	private int CurrentRoom = 0;
	private boolean PauseScreen = false;
	
	public StateGame(Handler Handler) {
		super(Handler);
		Outside = new Rooms(Handler ,"resources/rooms/outside.txt");
		Hall = new Rooms(Handler, "resources/rooms/hall.txt");
		Handler.SetRoom(Outside);
		CaraLoft = new Player(Handler , Handler.GetRoom().GetInitialX() * 64, Handler.GetRoom().GetInitialY() * 64);
		CurrentRoom = 0;
		PauseScreen = false;
	}	
	
	@Override
	public void Render(Graphics GraphicsObj) {
		if(CurrentRoom == 0) {
			Outside.Render(GraphicsObj);
		}
		else if(CurrentRoom == 1) {
			Hall.Render(GraphicsObj);
		}
		
		CaraLoft.Render(GraphicsObj);
		
//		if (PauseScreen == true) {
//			Handler.GetMain().GetState().Render(GraphicsObj);
//		}

	}

	@Override
	public void Tick() {
		//Checks which room to Tick
		if (CurrentRoom == 0) {
			Outside.Tick();
		}
		else if (CurrentRoom == 1) {
			Hall.Tick();
		}
		
		//Ticks the player
		CaraLoft.Tick();

		//Checks if player is trying to move to new room
		if (CaraLoft.GetPosX() > 11*64 & CaraLoft.GetPosX() < 13*64) { //704 < X 832
			if (CaraLoft.GetPosY() > 0 & CaraLoft.GetPosY() < 1*64) { //0 < 64
				CurrentRoom = 1;
				//Sets the player to the initial position for the room
				CaraLoft.SetPosX(Hall.GetInitialX() * 32);
				CaraLoft.SetPosY(Hall.GetInitialY() * 32);
				Handler.SetRoom(Hall);
			}
		}
		
		//Pause screen
		if (Handler.GetKeyboardInput().P == true || Handler.GetKeyboardInput().Esc == true) {
			//if (PauseScreen == false) {
				Handler.GetMain().SetState(Handler.GetMain().StatePause);
				//PauseScreen = true;
			//}
			
		}			

	}

}
