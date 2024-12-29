package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Energy extends Entity{

	
	public OBJ_Energy(GamePanel gp) {
		super(gp);
		
		name = "Energy";
		down1 = setup("/objects/energy");

	}
}
