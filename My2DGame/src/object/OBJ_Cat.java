package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Cat extends Entity {

	public OBJ_Cat(GamePanel gp) {
		super(gp);
		
		name = "Cat";
		down1 = setup("/objects/cat");
		
	}

}
