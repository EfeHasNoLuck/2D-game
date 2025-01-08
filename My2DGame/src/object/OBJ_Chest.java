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
		
		setDialogue();
	}
	public void setDialogue() {
	
		dialogues[0][0] = "Sandığı açtın ve " + loot.name + " buldun!" + "\n...Ama daha fazla taşıyamazsın!";
		dialogues[1][0] = "Sandığı açtın ve " + loot.name + " buldun!" + "\n" + loot.name + " Kazandın!";
		dialogues[2][0] = "Sandık Boş";
	}
	public void interact() {
		
		if(opened == false) {
			gp.playSE(11);
			
			if(gp.player.canObtainItem(loot) == false) {
				startDialogue(this, 0);
			}
			else {
				startDialogue(this, 2);
				down1 = image2;
				opened = true;
			}
		}
		else {
			startDialogue(this, 3);
		}
	}
}

