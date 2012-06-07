package regenerate;

import toxi.geom.Vec2D;

public class masterNode extends Node{

	private static float life = 120;
	private static int color = 155;
	
	masterNode(int id, Vec2D center, ReGenerate p_) {
		super(id, center, life, p_);
		setColor(255);
	}
	
	masterNode(int id, Vec2D center, float life, ReGenerate p_) {
		super(id, center, life, p_);
	}

	public void update()
	{
		
	}
}
