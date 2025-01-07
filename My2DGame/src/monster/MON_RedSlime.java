package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_tl;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Plasma;

public class MON_RedSlime extends Entity{

	GamePanel gp;
	
	public MON_RedSlime(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		name = "Slime";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 5;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new OBJ_Plasma(gp);
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		
		up1 = setup("/monster/redslime_up_1");
		up2 = setup("/monster/redslime_up_2");
		down1 = setup("/monster/redslime_down_1");
		down2 = setup("/monster/redslime_down_2");
		left1 = setup("/monster/redslime_left_1");
		left2 = setup("/monster/redslime_left_2");
		right1 = setup("/monster/redslime_right_1");
		right2 = setup("/monster/redslime_right_2");
	}

	public void setAction() {
		
//		int xDistance = Math.abs(worldX - gp.player.worldX);
//		int yDistance = Math.abs(worldY - gp.player.worldY);
//		int tileDistance = (xDistance + yDistance)/gp.tileSize;
		
		if(onPath == true) {
			
			// stop chasing distance
			checkStopChasingDistance(gp.player, 10, 100);
//			if(tileDistance > 20) {
//				onPath = false;
//			}
			
			//direction
			//	int goalCol = 3;
			//	int goalRow = 3;
			//	searchPath(goalCol, goalRow);
			
			searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
				
			//projectile
			checkShootOrNot(60, 30);

		}
		else {
			//chasing distance
			checkStartChasingDistance(gp.player, 5, 100);
			
			//get random direction
			getRandomDirection();
		}
	}
	
	public void damageReaction() {
	
		actionLockCounter = 0;
	//	direction = gp.player.direction;
		onPath = true;
	}
	public void checkDrop() {
		
		//cast a die
		int i = new Random().nextInt(100)+1;
		
		//monster drop
		if(i < 50) {
			dropItem(new OBJ_Coin_tl(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new OBJ_Heart(gp));
		}
		if(i >= 75 && i < 100) {
			dropItem(new OBJ_ManaCrystal(gp));
		}
	}
}
