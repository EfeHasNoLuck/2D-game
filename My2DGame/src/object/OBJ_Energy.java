package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Energy extends Entity{

	public static final String objName = "Energy";
	
	public OBJ_Energy(GamePanel gp) {
		super(gp);
		
		name = objName;
		down1 = setup("/objects/energy");
	}
}
