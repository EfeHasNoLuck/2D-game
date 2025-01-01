package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{

	GamePanel gp;
	
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = "Mana Crystal";
		value = 1;
		down1 = setup("/objects/lethal_company_1");
		image = setup("/objects/lethal_company_1");
		image2 = setup("/objects/lethal_company_2");
	}

	public void use(Entity entity) {
		
		gp.playSE(3);
		gp.ui.addMessage("Mana +" + value);
		entity.mana += value;
	}
}
