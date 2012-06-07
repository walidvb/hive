package regenerate;

import java.util.ArrayList;

import controlP5.*;
import processing.core.*;
import toxi.geom.*;
import toxi.gui.*;
import toxi.physics2d.behaviors.*;
import toxi.physics2d.*;
import toxi.processing.ToxiclibsSupport;

@SuppressWarnings("serial")
public class ReGenerate extends PApplet {

	public ArrayList<Node> nodes = new ArrayList<Node>();
	public masterNode masterNode;
	private GUIManager gui;
	public ToxiclibsSupport gfx;
	VerletPhysics2D physics;
	
	public void setup() 
	{
		size(680, 640, JAVA2D);
		smooth();
		frameRate(20);
		initGUI();
		gfx = new ToxiclibsSupport(this);
		physics=new VerletPhysics2D();
		masterNode = new masterNode(1, new Vec2D(width/2, height/2), this);
		masterNode.lock();
		physics.addParticle(masterNode);
		physics.addBehavior(new GravityBehavior(new Vec2D((float) 0.5,(float) 0.9)));
	}

	public void draw() 
	{
		background(100);
		for(int i = 0; i < nodes.size(); i++)
		{
			if(nodes.get(i).isAlive())
			{	
				nodes.get(i).update();
				nodes.get(i).draw();
				gfx.line(nodes.get(i), masterNode);
			}
			else
			{
				nodes.remove(i);
			}
		}
		
		masterNode.draw();
		physics.update();
	}
	
	//methods
	public boolean checkCollision(Circle c1, Circle c2)
	{
		boolean collide = false;
		for(int i = 0; i < nodes.size()-1; i++)
		{
		}
		
		return collide;
	}
	
	public int isInNode(Vec2D pos)
	{
		int isIn = -1;
		for(int i = 0; i < nodes.size(); i++)
		{
			if(nodes.get(i).e.containsPoint(pos))
			{
				isIn = i;
			}
		}
		
		return isIn;
	}
	public void addCircle(Vec2D center)
	{
		System.out.println("Node " + nodes.size() + " added");
		Node n = new Node(nodes.size(), center, this);
		nodes.add(n);
		physics.addParticle(n);
		VerletSpring2D spring=new VerletSpring2D(n,masterNode, (float) (n.distanceTo(masterNode)*0.7),(float) 0.01);
		//VerletSpring2D spring=new VerletSpring2D(n,masterNode, 150 ,(float) 0.01);
		physics.addSpring(spring);
	}
	
	//controls
	public void mousePressed()
	{
		Vec2D pos = new Vec2D(mouseX, mouseY);
		//if(isInNode(pos) != -1)
		{
			addCircle(pos);
		}
	}
	
	public void keyPressed()
	{
		if(key == 'p' || key == 'P')
		{
			for(int i = 0; i < nodes.size(); i++)
			{
				nodes.get(i).print();
			}
		}
	}
	
	// utils
	
    private void initGUI() {
        ControlP5 cp5 = new ControlP5(this);
        gui = new GUIManager(cp5);
        gui.createControllers(this);
        gui.addListenerFor("isActive", "toggleActive", this);
        gui.addListenerFor("btRestart", "doRestart", this);
    }
}
