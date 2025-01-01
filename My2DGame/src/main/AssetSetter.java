package main;

import entity.NPC_;
import entity.NPC_Eray;
import entity.NPC_Feyza;
import monster.MON_RedSlime;
import object.OBJ_Axe;
import object.OBJ_Chest;
import object.OBJ_Coin_tl;
import object.OBJ_Door;
import object.OBJ_Energy;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import tile_interactive.IT_DryTree;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setObject() {
		
		int i = 0;
		gp.obj[i] = new OBJ_Coin_tl(gp);
		gp.obj[i].worldX = 23 * gp.tileSize;
		gp.obj[i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Coin_tl(gp);
		gp.obj[i].worldX = 23 * gp.tileSize;
		gp.obj[i].worldY = 40 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Key(gp);
		gp.obj[i].worldX = 38 * gp.tileSize;
		gp.obj[i].worldY = 8 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 10 * gp.tileSize;
		gp.obj[i].worldY = 11 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 8 * gp.tileSize;
		gp.obj[i].worldY = 28 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Door(gp);
		gp.obj[i].worldX = 12 * gp.tileSize;
		gp.obj[i].worldY = 22 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Chest(gp);
		gp.obj[i].worldX = 10 * gp.tileSize;
		gp.obj[i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Energy(gp);
		gp.obj[i].worldX = 37 * gp.tileSize;
		gp.obj[i].worldY = 42 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = 3 * gp.tileSize;
		gp.obj[i].worldY = 3 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_Heart(gp);
		gp.obj[i].worldX = 15 * gp.tileSize;
		gp.obj[i].worldY = 15 * gp.tileSize;
		i++;
		gp.obj[i] = new OBJ_ManaCrystal(gp);
		gp.obj[i].worldX = 13 * gp.tileSize;
		gp.obj[i].worldY = 13 * gp.tileSize;
		
		gp.obj[i] = new OBJ_Axe(gp);
		gp.obj[i].worldX = 27 * gp.tileSize;
		gp.obj[i].worldY = 27 * gp.tileSize;
	}
	
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_(gp);
		gp.npc[0].worldX = gp.tileSize*21;
		gp.npc[0].worldY = gp.tileSize*21;
		
		gp.npc[1] = new NPC_Feyza(gp);
		gp.npc[1].worldX = gp.tileSize*25;
		gp.npc[1].worldY = gp.tileSize*25;
		
		gp.npc[2] = new NPC_Eray(gp);
		gp.npc[2].worldX = gp.tileSize*26;
		gp.npc[2].worldY = gp.tileSize*26;
	}
	
	
	public void setMonster() {
		
		int i = 0;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize*10;
		gp.monster[i].worldY = gp.tileSize*8;
		i++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize*15;
		gp.monster[i].worldY = gp.tileSize*17;
		i++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize*18;
		gp.monster[i].worldY = gp.tileSize*15;
		i++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize*19;
		gp.monster[i].worldY = gp.tileSize*15;
		i++;
		gp.monster[i] = new MON_RedSlime(gp);
		gp.monster[i].worldX = gp.tileSize*11;
		gp.monster[i].worldY = gp.tileSize*10;
		i++;
	}
	
	public void setInteractiveTile() {
		
		int i = 0;
		gp.iTile[i] = new IT_DryTree(gp,28,28);i++;
		gp.iTile[i] = new IT_DryTree(gp,29,28);i++;
		gp.iTile[i] = new IT_DryTree(gp,30,28);i++;
		gp.iTile[i] = new IT_DryTree(gp,31,28);i++;
		gp.iTile[i] = new IT_DryTree(gp,32,28);i++;
	}
}
