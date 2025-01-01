package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity{

	GamePanel gp;
	
	public OBJ_ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Mana Crystal";
		image = setup("/objects/lethal_company_1");
		image2 = setup("/objects/lethal_company_2");
	}

}
