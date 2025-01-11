package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Eclipse extends Entity{

	GamePanel gp;
	public static final String objName = "Eclipse";
	
	public OBJ_Eclipse(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		type = type_pickupOnly;
		name = objName;
		down1 = setup("/objects/eclipse");
		
		setDialogues();
	}
	public void setDialogues() {
		
		dialogues[0][0] = "Hayatın Yapıtaşı Eclipse taşını buldun";
	}
	
	public boolean use(Entity entity) {
		
		gp.gameState = gp.cutsceneState;
		gp.csManager.sceneNum = gp.csManager.ending;
		return true;
	}
}
