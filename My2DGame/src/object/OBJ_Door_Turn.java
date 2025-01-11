package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Turn extends Entity{

	GamePanel gp;
	public static final String objName = "Door_turn";
	
	public OBJ_Door_Turn(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = objName;
		down1 = setup("/objects/door_turn");
		collision = true;
		
		solidArea.x = 0;
		solidArea.y = 16;
		solidArea.width = 48;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	
		setDialogue();
	}
	public void setDialogue() {
		
		dialogues[0][0] = "Buradan Neden geçemiyorum";
	}
	public void interact() {
		
		startDialogue(this, 0);
	}
}