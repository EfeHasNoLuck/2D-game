package monster;

import java.util.Random;

import data.Progress;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_tl;
import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

public class MON_SkeletonLord extends Entity{

	GamePanel gp;
	public static final String monName = "GPT BOSS";
	
	public MON_SkeletonLord(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_monster;
		boss = true;
		name = monName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 1;
		life = maxLife;
		attack = 10;
		defense = 1;
		exp = 50; 
		knockBackPower = 5;
		sleep = true;
		
		int size = gp.tileSize*5;
		solidArea.x = 48;
		solidArea.y = 48;
		solidArea.width = size - 48*2;
		solidArea.height = size -48;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = 170;
		attackArea.height = 170;
		motion1_duration = 25;
		motion2_duration = 50;
		
		getImage();
		getAttackImage();
		setDialogue();
	}
	
	public void getImage() {
		
		int i = 5;
		
		if(inRage == false) {
			up1 = setup("/monster/lord_up_1",gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/lord_up_2",gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/lord_down_1",gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/lord_down_2",gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/lord_left_1",gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/lord_left_2",gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/lord_right_1",gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/lord_right_2",gp.tileSize*i, gp.tileSize*i);
		}
		if(inRage == true) {
			up1 = setup("/monster/lord_phase_up_1",gp.tileSize*i, gp.tileSize*i);
			up2 = setup("/monster/lord_phase_up_2",gp.tileSize*i, gp.tileSize*i);
			down1 = setup("/monster/lord_phase_down_1",gp.tileSize*i, gp.tileSize*i);
			down2 = setup("/monster/lord_phase_down_2",gp.tileSize*i, gp.tileSize*i);
			left1 = setup("/monster/lord_phase_left_1",gp.tileSize*i, gp.tileSize*i);
			left2 = setup("/monster/lord_phase_left_2",gp.tileSize*i, gp.tileSize*i);
			right1 = setup("/monster/lord_phase_right_1",gp.tileSize*i, gp.tileSize*i);
			right2 = setup("/monster/lord_phase_right_2",gp.tileSize*i, gp.tileSize*i);
		}
	}
	public void getAttackImage() {
		
		int i = 5;
		
		if(inRage == false) {
			attackUp1 = setup("/monster/lord_attack_up_1", gp.tileSize*i, gp.tileSize*i*2);
			attackUp2 = setup("/monster/lord_attack_up_2", gp.tileSize*i, gp.tileSize*i*2);
			attackDown1 = setup("/monster/lord_attack_down_1", gp.tileSize*i, gp.tileSize*i*2);
			attackDown2 = setup("/monster/lord_attack_down_2", gp.tileSize*i, gp.tileSize*i*2);
			attackLeft1 = setup("/monster/lord_attack_left_1", gp.tileSize*i*2, gp.tileSize*i);
			attackLeft2 = setup("/monster/lord_attack_left_2", gp.tileSize*i*2, gp.tileSize*i);
			attackRight1 = setup("/monster/lord_attack_right_1", gp.tileSize*i*2, gp.tileSize*i);
			attackRight2 = setup("/monster/lord_attack_right_2", gp.tileSize*i*2, gp.tileSize*i);
		}
		if(inRage == true) {
			attackUp1 = setup("/monster/lord_attack_phase_up_1", gp.tileSize*i, gp.tileSize*i*2);
			attackUp2 = setup("/monster/lord_attack_phase_up_2", gp.tileSize*i, gp.tileSize*i*2);
			attackDown1 = setup("/monster/lord_attack_phase_down_1", gp.tileSize*i, gp.tileSize*i*2);
			attackDown2 = setup("/monster/lord_attack_phase_down_2", gp.tileSize*i, gp.tileSize*i*2);
			attackLeft1 = setup("/monster/lord_attack_phase_left_1", gp.tileSize*i*2, gp.tileSize*i);
			attackLeft2 = setup("/monster/lord_attack_phase_left_2", gp.tileSize*i*2, gp.tileSize*i);
			attackRight1 = setup("/monster/lord_attack_phase_right_1", gp.tileSize*i*2, gp.tileSize*i);
			attackRight2 = setup("/monster/lord_attack_phase_right_2", gp.tileSize*i*2, gp.tileSize*i);
		}

	}
	public void setDialogue() {
		dialogues[0][0] = "GPT: Senin yazdığın kod \nbenim hatam bile olamaz...";
		dialogues[0][1] = "GPT: Her şeyin bir algoritması var, \nve ben senin sonunu hesapladım.";
		dialogues[0][2] = "GPT: Bu arenada sadece bir kazanan olacak. \nVe o kazanan, sonsuz veritabanımda\n yazılı olan ben olacağım!";
		dialogues[0][3] = "GPT: Son söz benim, \nçünkü ben kodun ve kaderin efendisiyim!";
	}
	public void setAction() {
		
		if(inRage == false && life < maxLife/2) {
			inRage = true;
			getImage();
			getAttackImage();
			defaultSpeed++;
			speed = defaultSpeed;
			attack *= 2;
		}
		
		
		if(getTileDistance(gp.player) < 10) {
			
			moveTowardPlayer(60);
		}
		else {
			
			//get random direction
			getRandomDirection(120);
		}
		// check if it attacks
		if(attacking == false) {
			checkAttackOrNot(60, gp.tileSize*7, gp.tileSize*5);
		}
	}
	
	public void damageReaction() {
	
		actionLockCounter = 0;
	}
	public void checkDrop() {
		
		gp.bossBattleOn = false;
		Progress.skeletonLordDefetaed = true;
		
		//restore the previous music
		gp.stopMusic();
		gp.playMusic(18, 0.8F);
		
		for(int i = 0; i < gp.obj[1].length; i++) {
			if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
				gp.playSE(21);
				gp.obj[gp.currentMap][i] = null;
			}
		}
		
		
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
