package main;

import entity.NPC_;
import entity.NPC_Ali;
import entity.NPC_BigRock;
import entity.NPC_Eray;
import entity.NPC_Feyza;
import entity.NPC_Makineci;
import monster.MON_Orc;
import monster.MON_RedSlime;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import object.OBJ_Pickaxe;
import tile_interactive.IT_DestructibleWall;
import tile_interactive.IT_MetalPlate;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setObject() {


		int mapNum = 0;
		int i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 75 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 34 * gp.tileSize;
		i++;
/*
		gp.obj[mapNum][i] = new OBJ_Coin_tl(gp);
		gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_tl(gp);
		gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 40 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 19 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Key(gp);
		gp.obj[mapNum][i].worldX = 38 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 11 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 28 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 22 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldX = 10 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Energy(gp);
		gp.obj[mapNum][i].worldX = 37 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = 3 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 3 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
		gp.obj[mapNum][i].worldX = 4 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 4 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldX = 15 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
		gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Axe(gp);
		gp.obj[mapNum][i].worldX = 27 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_ManaCrystal(gp);
		gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
		i++;
*/
		
		mapNum = 2;
		i = 0;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.obj[mapNum][i].worldX = 70 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 47 * gp.tileSize;
		i++;

/*
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Pickaxe(gp));
		gp.obj[mapNum][i].worldX = 33 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Pickaxe(gp);
		gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
		i++;
		gp.obj[mapNum][i] = new OBJ_Door_Iron(gp);
		gp.obj[mapNum][i].worldX = 31 * gp.tileSize;
		gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
		
*/
		mapNum = 1;
		i = 0;
	}
	
	
	public void setNPC() {


		int mapNum = 0;
		int i = 0;
		gp.npc[mapNum][i] = new NPC_(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*68;
		gp.npc[mapNum][i].worldY = gp.tileSize*37;
		i++;
		gp.npc[mapNum][i] = new NPC_Feyza(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*39;
		gp.npc[mapNum][i].worldY = gp.tileSize*25;
		i++;
		gp.npc[mapNum][i] = new NPC_Eray(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*55;
		gp.npc[mapNum][i].worldY = gp.tileSize*60;
		i++;
		gp.npc[mapNum][i] = new NPC_Ali(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*45;
		gp.npc[mapNum][i].worldY = gp.tileSize*53;
		

		mapNum = 1;
		i = 0;
		gp.npc[mapNum][i] = new NPC_Makineci(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*68;
		gp.npc[mapNum][i].worldY = gp.tileSize*48;
		i++;
		
		mapNum = 2;
		i = 0;
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*61;
		gp.npc[mapNum][i].worldY = gp.tileSize*38;
		i++;
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*50;
		gp.npc[mapNum][i].worldY = gp.tileSize*54;
		i++;
		gp.npc[mapNum][i] = new NPC_BigRock(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize*37;
		gp.npc[mapNum][i].worldY = gp.tileSize*50;
		i++;
	}

	
	public void setMonster() {
		
		int mapNum = 0;
		int i = 0;
		
		/*
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*10;
		gp.monster[mapNum][i].worldY = gp.tileSize*8;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*15;
		gp.monster[mapNum][i].worldY = gp.tileSize*17;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*18;
		gp.monster[mapNum][i].worldY = gp.tileSize*15;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*19;
		gp.monster[mapNum][i].worldY = gp.tileSize*15;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*11;
		gp.monster[mapNum][i].worldY = gp.tileSize*10;
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*6;
		gp.monster[mapNum][i].worldY = gp.tileSize*6;
		i++;
		gp.monster[mapNum][i] = new MON_SkeletonLord(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*35;
		gp.monster[mapNum][i].worldY = gp.tileSize*35;
		i++;	
*/
		
		i = 0;
		mapNum = 1;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*57;
		gp.monster[mapNum][i].worldY = gp.tileSize*46;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*55;
		gp.monster[mapNum][i].worldY = gp.tileSize*46;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*66;
		gp.monster[mapNum][i].worldY = gp.tileSize*43;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*71;
		gp.monster[mapNum][i].worldY = gp.tileSize*50;
		i++;
		
		i = 0;
		mapNum = 2;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*61;
		gp.monster[mapNum][i].worldY = gp.tileSize*36;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*56;
		gp.monster[mapNum][i].worldY = gp.tileSize*36;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*59;
		gp.monster[mapNum][i].worldY = gp.tileSize*39;
		i++;
		gp.monster[mapNum][i] = new MON_RedSlime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*55;
		gp.monster[mapNum][i].worldY = gp.tileSize*55;
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*49;
		gp.monster[mapNum][i].worldY = gp.tileSize*58;
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*42;
		gp.monster[mapNum][i].worldY = gp.tileSize*49;
		i++;
		gp.monster[mapNum][i] = new MON_Orc(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize*37;
		gp.monster[mapNum][i].worldY = gp.tileSize*53;
		i++;
	}
	
	public void setInteractiveTile() {


		int mapNum = 0;
		int i = 0;
//		gp.iTile[mapNum][i] = new IT_DryTree(gp,28,28);i++;
//		gp.iTile[mapNum][i] = new IT_DryTree(gp,29,28);i++;
//		gp.iTile[mapNum][i] = new IT_DryTree(gp,30,28);i++;
//		gp.iTile[mapNum][i] = new IT_DryTree(gp,31,28);i++;
//		gp.iTile[mapNum][i] = new IT_DryTree(gp,32,28);i++;
		
		mapNum = 1;
		i = 0;
//		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,16,22);i++;
//		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,16,21);i++;
//		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,16,20);i++;
//		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,16,19);i++;
//		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,16,18);i++;
		
//		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,35,11);i++;
//		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,33,11);i++;
//		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,32,11);i++;
		
		mapNum = 2;
		i = 0;
		
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,56,59);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,35,54);i++;
		gp.iTile[mapNum][i] = new IT_MetalPlate(gp,63,35);i++;
		
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,54,42);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,54,43);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,52,50);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,52,51);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,42,48);i++;
		gp.iTile[mapNum][i] = new IT_DestructibleWall(gp,43,48);i++;

	}
}
