package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Plasma extends Projectile{
	
	GamePanel gp;
	
	public OBJ_Plasma(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Plasma";
		speed = 8;
		maxLife = 80;
		life = maxLife;
		attack = 2;
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

}
