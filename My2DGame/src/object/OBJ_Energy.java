package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Energy extends SuperObject{

	public OBJ_Energy()
	{
		name = "Energy";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/energy.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
