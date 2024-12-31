package object;

import entity.Projectile;
import main.GamePanel;

public class OBJ_ElectroBall extends Projectile{

	GamePanel gp;
	
	public OBJ_ElectroBall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "ElectroBall";
		speed = 10;
		maxLife = 80;
		life = maxLife;
		attack = 2;
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

}
