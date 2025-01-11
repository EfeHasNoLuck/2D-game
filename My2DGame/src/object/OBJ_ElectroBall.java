package object;

import java.awt.Color;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_ElectroBall extends Projectile{

	GamePanel gp;
	public static final String objName = "ElectroBall";
	
	public OBJ_ElectroBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		speed = 8;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		knockBackPower = 5;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/purple_up_1");
		up2 = setup("/projectile/purple_up_2");
		down1 = setup("/projectile/purple_down_1");
		down2 = setup("/projectile/purple_down_2");
		left1 = setup("/projectile/purple_left_1");
		left2 = setup("/projectile/purple_left_2");
		right1 = setup("/projectile/purple_right_1");
		right2 = setup("/projectile/purple_right_2");
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.mana >= useCost) {
			haveResource = true;
		}
		return haveResource;
 	}
	
	public void subtractResource(Entity user) {
		user.mana -= useCost;
	}
	
	public Color getParticleColor() {
		Color color = new Color(157, 0, 255);
		return color;
	} 
	public int getParticleSize() {
		int size = 7;
		return size;
	}
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	public int getParticleMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
