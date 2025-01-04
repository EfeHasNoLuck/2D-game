package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_tl extends Entity{

	GamePanel gp;
	
	public OBJ_Coin_tl(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Turk Lirasi";
		value = 1;
		down1 = setup("/objects/Coin_tl");
	}

	public boolean use(Entity entity) {
		
		gp.playSE(3);
		gp.ui.addMessage("Coin +" + value);
		gp.player.coin += value;
		return true;
	}
}
