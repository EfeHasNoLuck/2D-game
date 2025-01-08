package object;

import java.awt.Color;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Plasma extends Projectile{
	
	GamePanel gp;
	public static final String objName = "Plasma";
	
	public OBJ_Plasma(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objName;
		speed = 7;
		maxLife = 80;
		life = maxLife;
		attack = 2;
		knockBackPower = 0;
		useCost = 1;
		alive = false;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/Plasma");
		up2 = setup("/projectile/Plasma");
		down1 = setup("/projectile/Plasma");
		down2 = setup("/projectile/Plasma");
		left1 = setup("/projectile/Plasma");
		left2 = setup("/projectile/Plasma");
		right1 = setup("/projectile/Plasma");
		right2 = setup("/projectile/Plasma");
	}
	
	public Color getParticleColor() {
		Color color = new Color(240, 50, 0);
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
