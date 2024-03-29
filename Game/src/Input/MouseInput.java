package Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{
	
	//Main Menu Buttons
	public boolean Play = false;
	public boolean Settings = false;
	public boolean Exit = false;
	//Pause Menu Buttons
	public boolean Resume = false;
	public boolean Quit = false;
	//Settings Button
	public boolean Return = false;
	//End Screen Button
	public boolean ExitEnd = false;
	
	public MouseInput() {
		
	}
	
	public void Refresh() {
		Play = false;
		Settings = false;
		Exit = false;
		Resume = false;
		Quit = false;
		Return = false;
	}
	
	public void Tick() {

	}

	@Override
	public void mouseClicked(MouseEvent Event) {
		
	}

	@Override
	public void mouseEntered(MouseEvent Event) {

	}

	@Override
	public void mouseExited(MouseEvent Event) {
		
	}

	@Override
	public void mousePressed(MouseEvent Event) {
		int PosX = Event.getX();
		int PosY = Event.getY();
		
		//Main Menu Buttons
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 250 & PosY <= 400) {
				Play = true;
			}
		}
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 430 & PosY <= 580) {
				Settings = true;
			}
		}
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 610 & PosY <= 760) {
				Exit = true;
			}
		}
		
		//Pause Menu Buttons
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 300 & PosY <= 450) {
				Resume = true;
			}
		}
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 530 & PosY <= 680) {
				Quit = true;
			}
		}
		
		//Settings Menu Button
		if (PosX >= 900 & PosX <= 1150) {
			if (PosY >= 700 & PosY <= 800) {
				Return = true;
			}
		}
		
		//End Screen Button
		if (PosX >= 520 & PosX <= 920) {
			if (PosY >= 400 & PosY <= 550) {
				ExitEnd = true;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent Event) {
		
		
	}
	
	//GETTERS & SETTERS
	//Play 
	public boolean GetPlayStatus() {
		return Play;
	}
	public void SetPlayStatus(boolean Status) {
		Play = Status;
	}
	
	//Settings
	public boolean GetSettingsStatus() {
		return Settings;
	}
	public void SetSettingsStatus(boolean Status) {
		Settings = Status;
	}
	
	//Exit
	public boolean GetExitStatus() {
		return Exit;
	}
	public void SetExiteStatus(boolean Status) {
		Exit = Status;
	}
}
