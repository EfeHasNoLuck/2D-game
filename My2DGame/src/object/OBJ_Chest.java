package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity{

	GamePanel gp;
	
	public OBJ_Chest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_obstacle;
		name = "Chest";
		image = setup("/objects/chest");
		image2 = setup("/objects/Cat");
		down1 = image;
		collision = true;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = 40;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	public void setLoot(Entity loot) {
		this.loot = loot;
	}
	public void interact() {
		
		gp.gameState = gp.dialogueState;
		
		if(opened == false) {
			gp.playSE(11);
			
			StringBuilder sb = new StringBuilder();
			sb.append("Sandığı açtın ve " + loot.name + " buldun!");
			
			if(gp.player.canObtainItem(loot) == false) {
				sb.append("\n...Ama daha fazla taşıyamazsın!");
			}
			else {
				sb.append("\n" + loot.name + " Kazandın!");
				down1 = image2;
				opened = true;
			}
			gp.ui.currentDialogue = sb.toString();
		}
		else {
			gp.ui.currentDialogue = "Sandık Boş";
		}
	}
}

