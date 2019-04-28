package Graphics;

import java.awt.image.BufferedImage;

public class Sprites {
	
	private static int Width = 64;
	private static int Height = 64;
	public static BufferedImage[] CaraLoftFront, CaraLoftLeft, CaraLoftRight, CaraLoftBack;
	public static BufferedImage[] enemy_left, enemy_right, enemy_down, enemy_up;
	public static BufferedImage Grass;
	public static BufferedImage Sandstone;
	public static BufferedImage WoodenTile;
	public static BufferedImage Hedge;
	public static BufferedImage Bush;
	public static BufferedImage Tree;
	public static BufferedImage Crate;
	public static BufferedImage Black;
	public static BufferedImage Apple;
	public static BufferedImage Key;
	
	
	public static void LoadSprites() {
		SpriteSheet Sheet = new SpriteSheet(TextureLoader.Image("/textures/CaraLoftFront.png"));
		SpriteSheet Tiles = new SpriteSheet(TextureLoader.Image("/textures/Tiles.png"));

		// initialized the character sprite arrays
		CaraLoftFront = new BufferedImage[4];
		CaraLoftLeft = new BufferedImage[4];
		CaraLoftRight = new BufferedImage[4];
		CaraLoftBack = new BufferedImage[4];
		
		//CaraLoft Animation
		for(int x = 0; x < CaraLoftFront.length; x++){
			CaraLoftFront[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < CaraLoftLeft.length; x++){
			CaraLoftLeft[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < CaraLoftRight.length; x++){
			CaraLoftRight[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < CaraLoftBack.length; x++){
			CaraLoftBack[x] = Sheet.CropSheet(Width, Height, Width * x, 0);
		}

		//Tiles
		Grass = Tiles.CropSheet(Width, Height, 0, 0);
		Sandstone = Tiles.CropSheet(Width, Height, 64, 0);
		WoodenTile = Tiles.CropSheet(Width, Height, 128, 0);
		Hedge = Tiles.CropSheet(Width, Height, 192, 0);
		Bush = Tiles.CropSheet(Width, Height, 0, 64);
		Tree = Tiles.CropSheet(Width, Height, 64, 64);
		Crate = Tiles.CropSheet(Width, Height, 128, 64);
		Black = Tiles.CropSheet(Width, Height, 192, 64);
		Apple = TextureLoader.Image("/textures/sprite_0.png");
		Key = TextureLoader.Image("/textures/Key.png");

		SpriteSheet sheet2 = new SpriteSheet((TextureLoader.Image("/textures/BODY_male_Epic_armors_Golden.png")));

		enemy_right = new BufferedImage[8];
		enemy_left  = new BufferedImage[8];
		enemy_down = new BufferedImage[8];
		enemy_up = new BufferedImage[8];

		for(int x = 0; x < enemy_up.length; x++){
			enemy_up[x] = sheet2.CropSheet(Width, Height, Width * x, 0);
		}
		for(int x = 0; x < enemy_left.length; x++){
			enemy_left[x] = sheet2.CropSheet(Width, Height, Width * x, Height * 1);
		}
		for(int x = 0; x < enemy_down.length; x++){
			enemy_down[x] = sheet2.CropSheet(Width, Height, Width * x, Height * 2);
		}
		for(int x = 0; x < enemy_right.length; x++){
			enemy_right[x] = sheet2.CropSheet(Width, Height, Width * x, Height * 3);
		}
		
	}
}