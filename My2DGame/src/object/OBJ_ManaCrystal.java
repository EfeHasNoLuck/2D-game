package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{

	GamePanel gp;
	public static final String objName = "Mana Crystal";
	
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		value = 1;
		down1 = setup("/objects/lethal_company_1");
		image = setup("/objects/lethal_company_1");
		image2 = setup("/objects/lethal_company_2");
	}

	public boolean use(Entity entity) {
		
		gp.ui.addMessage("Mana +" + value);
		entity.mana += value;
		gp.playSE(17);
		return true;
	}
}
