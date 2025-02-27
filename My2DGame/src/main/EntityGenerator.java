package main;

import entity.Entity;
import object.OBJ_Axe;
import object.OBJ_Cat;
import object.OBJ_Chest;
import object.OBJ_Coin_tl;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_ElectroBall;
import object.OBJ_Energy;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Pickaxe;
import object.OBJ_Plasma;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class EntityGenerator {

	GamePanel gp; 
	
	public EntityGenerator(GamePanel gp) {
		this.gp = gp;
	}
	
	public Entity getObject(String itemName) {
		
		Entity obj = null;
		
		switch(itemName) {
		case OBJ_Axe.objName: obj = new OBJ_Axe(gp); break;
		case OBJ_Cat.objName: obj = new OBJ_Cat(gp); break;
		case OBJ_Chest.objName: obj = new OBJ_Chest(gp); break;
		case OBJ_Coin_tl.objName: obj = new OBJ_Coin_tl(gp); break;
		case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp); break;
		case OBJ_Door.objName: obj = new OBJ_Door(gp); break;
		case OBJ_ElectroBall.objName: obj = new OBJ_ElectroBall(gp); break;
		case OBJ_Energy.objName: obj = new OBJ_Energy(gp); break;
		case OBJ_Heart.objName: obj = new OBJ_Heart(gp); break;
		case OBJ_Key.objName: obj = new OBJ_Key(gp); break;
		case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp); break;
		case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp); break;
		case OBJ_Plasma.objName: obj = new OBJ_Plasma(gp); break;
//      case "Blue Shiled": obj = new OBJ_Shield_Blue(gp); break;
		case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp); break;
		case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp); break;
		case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp); break;


		}
		return obj;
	}
}
