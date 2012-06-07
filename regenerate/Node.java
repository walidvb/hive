package regenerate;

import ddf.minim.signals.SineWave;
import toxi.geom.*;
import toxi.gui.*;
import toxi.math.waves.FMSineWave;
import toxi.physics2d.VerletParticle2D;
import toxi.processing.ToxiclibsSupport;

public class Node extends VerletParticle2D{
	
	//Needed attributes
	static final double dx = 0.2;
	static final double dy = 0.2;
	static ReGenerate p5;

	ToxiclibsSupport gfx;
	VerletParticle2D center;
	
	//Own Attributes
	private int id;
	@GUIElement(label = "life")
	static final float maxLife = 500;
	static final float minLife = 40;
	private float life = 80;
	private double rate = 0.995;
	private boolean alive = true;
	private int color = 155;
	Ellipse e;
	SineWave w;
	
	//Constructor
	Node(int id, Vec2D center, ReGenerate p_)
	{
		super(center);
		//this = new Ellipse(center, life);
		this.id = id;
		this.x = center.x;
		this.y = center.y;
		this.center = new VerletParticle2D(center);
		e = new Ellipse(this, life);
		Node.p5 = p_;
		float a;
		float b;
		//w = new SineWave((float) 0, (float) 20,(float) 10);
	}
	
	Node(int id, Vec2D center, float life, ReGenerate p_)
	{
		super(center);
		//this = new Ellipse(center, life);
		this.id = id;
		this.life = life;
		this.x = center.x;
		this.y = center.y;
		this.center = new VerletParticle2D(center);
		e = new Ellipse(this, life);
		Node.p5 = p_;
		//w = new SineWave((float) 0, (float) 20,(float) 10);
	}
	
	//methods
	
	public void draw(){
		p5.fill(color);
		p5.stroke(20);
		e.x = x*w;
		e.y = y*w.value;
		e.setRadii(life, life);
		p5.gfx.ellipse(e);
	}

	public void update(){
		this.life *= rate;
		if(life < minLife)
		{
			rate *= 0.95;
			if(life < 10)
			{
				this.alive = false;
			}
		}
	}

	public void reset(){
		this.life = maxLife;
	}
	
	public void print()
	{
		System.out.println("ID: " + id + " LIFE: " + getLife() + " CENTER: " + this);
	}
	
	//Getters and Setters
	public boolean isAlive()
	{
		return this.alive;
	}
	
	public void kill()
	{
		this.alive = false;
	}
	public Vec2D getCenter()
	{
		return this.center;
	}
	
	public void setCenter(Vec2D center)
	{
		//this.center;
	}
	public float getLife()
	{
		return this.life;
	}
	
	public void setLife(float newLife)
	{
		 this.life = newLife;
	}	
	
	public void giveLife(float more)
	{
		this.life += more;
	}
	
	public void takeLife(float less)
	{
		this.life -= less;
	}
	
	public void setColor(int color)
	{
		this.color = color;
	}
}
