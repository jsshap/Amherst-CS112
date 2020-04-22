import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;

class Pair{
    public double x;
    public double y;
    public Pair(double a, double b){
	this.x =a;
	this.y =b;
    }
    public Pair add(Pair p){
	return (new Pair(this.x+p.x, this.y+p.y));
    }
    public Pair times(double d){
	return (new Pair(this.x*d, this.y*d));
    }
    public void flipX(){
	this.x *= (-1);	    
    }
    public void flipY(){
	this.y *= (-1);
    }
    public Pair divide(double d){
	return (new Pair(this.x/d, this.y/d));	
    }
}

abstract class Shape{
    Pair position;
    Pair velocity;
    Pair acceleration;
    double width;
    double halfwidth;
    double dampening;
    Color color;
    public Shape()
    {
        Random rand = new Random(); 
        position = new Pair(500.0, 500.0);
        velocity = new Pair((double)(rand.nextInt(1000) - 500), (double)(rand.nextInt(1000) - 500));
        acceleration = new Pair(0.0, 200.0);//setGravity(rand.nextInt(9));
        width = 50;
        halfwidth = width / 2.0;
        dampening = 1.1;
        color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    }
    public Pair setGravity(int i){//not currently in use. ENABLE ON LINE 45//THIS ASSIGNS EACH SPHERE IT'S OWN GRAVITY DIRECTION
	//	System.out.println(i);
	switch (i){
	case 0: return new Pair(0.0, 200.0);
	case 1: return new Pair(200.0, 200.0);
	case 2: return new Pair(-200.0, 200.0);
	case 3: return new Pair(200.0, 0.0);
	case 4: return new Pair(200.0, -200.0);
	case 5: return new Pair(-200.0, -200.0);
	case 6: return new Pair(0.0, -200.0);
	case 7: return new Pair(-200.0, 0.0);
	case 8: return new Pair(0.0, 0.0);
	}
	throw new RuntimeException ("Set accel is broken");

    }

    public void update(World w, double time){
        position = position.add(velocity.times(time));
        velocity = velocity.add(acceleration.times(time));
        bounce(w);
	collision(w);//////////////////////
    }
    
    public void setPosition(Pair p){
        position = p;
    }

    public void setVelocity(Pair v){
        velocity = v;
    }

    public void setAcceleration(Pair a){
        acceleration = a;
    } 

    abstract public void draw(Graphics g);

    private void bounce(World w){
        Boolean bounced = false;
        if (position.x - halfwidth < 0){
            velocity.flipX();
            position.x = halfwidth;
            bounced = true;
        }
        else if (position.x + halfwidth > w.width){
            velocity.flipX();
            position.x = w.width - halfwidth;
            bounced = true;
        }
        if (position.y - halfwidth < 0){
            velocity.flipY();
            position.y = halfwidth;
            bounced = true;
        }
        else if(position.y + halfwidth >  w.height){
            velocity.flipY();
            position.y = w.height - halfwidth;
            bounced = true;
        }
        if (bounced){
            velocity = velocity.divide(dampening);
        }
    }
	//world.shapes is an array of shapes
	//implementing code for making shapes bounce off of other shapes

      ////////////////////
    public void collision(World w){
	/*for (int i= 0, i<w.spheres.length; i++){
	    for (int j=0, j<w.spheres.length; j++){
		if (j!=i){
		    if (shapes[i].position.x==shapes[j].position.x+radius)
			
			}
	    }
	    }*/
	/*for (Shape s : w.shapes){
	    if (this.position.x+width<=s.position.x && this.position.x+width>=s.position.x+width){
		if ((this.position.y+width>=s.position.y && this.position.y+width <= s.position.y+width) || (this.position.y<=s.position.y+width && this.position.y>=s.position.y))
		    this.velocity.flipX();
	    }
	    else if (this.position.x>=s.position.x+width && this.position.x<=s.position.x){
		if ((this.position.y+width>=s.position.y && this.position.y+width <= s.position.y+width) || (this.position.y<=s.position.y+width && this.position.y>=s.position.y))
		    this.velocity.flipX();
	    }
	    else if (this.position.y+width<=s.position.y && this.position.y+width >= s.position.y+width){
		if ((this.position.x+width>=s.position.x && this.position.x+width<=s.position.x+width)||(this.position.x<=s.position.x+width && this.position.x>=s.position.x))
		    this.velocity.flipY();
	    }
	    else if (this.position.y>=s.position.y+width && this.position.y<=s.position.y){
		if ((this.position.x+width>=s.position.x && this.position.x+width<=s.position.x+width)||(this.position.x<=s.position.x+width && this.position.x>=s.position.x))
		    this.velocity.flipY();
	    }

	
	    }*/
    }
    
}

class Sphere extends Shape{
    public void draw(Graphics g){
        Color c = g.getColor();
        
        g.setColor(color);
        g.drawOval((int)(position.x - halfwidth), (int)(position.y - halfwidth), (int)(width), (int)(width));
        g.setColor(c);
    }
}

class Square extends Shape{
    public void draw(Graphics g){
        Color c = g.getColor();
        
        g.setColor(color);
        g.drawRect((int)(position.x - width/2), (int)(position.y - width/2), (int)(width), (int)(width));
        g.setColor(c);
    }
}

class World{
    int height;
    int width;
    
    int numShapes;
    Shape shapes[];
    int gravityDirection;

    public World(int initWidth, int initHeight, int initNumShapes){
        width = initWidth;
        height = initHeight;

        numShapes = initNumShapes;
        shapes  = new Shape[numShapes];
	Random r = new Random();

	
        
        for (int i = 0; i < numShapes; i ++)
            {
                if (i % 2 == 0)
                    shapes[i] = new Sphere();
                else
                    shapes[i] = new Square();
            }
	
	

    }

    public void drawShapes(Graphics g){
        for (int i = 0; i < numShapes; i++){
            shapes[i].draw(g);
        }
    }

    public void updateShapes(double time){
        for (int i = 0; i < numShapes; i ++)
            {
                shapes[i].update(this, time);
            }
    }

}

public class OOBouncing extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    World world;

    public void run()
    {
        while(true){
            world.updateShapes(1.0 / (double)FPS);
            repaint();
            try{
                Thread.sleep(1000/FPS);
            }
            catch(InterruptedException e){}
        }

    }

    public OOBouncing(){
        world = new World(WIDTH, HEIGHT, 50);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Physics!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OOBouncing mainInstance = new OOBouncing();
        frame.setContentPane(mainInstance);
        frame.pack();
        frame.setVisible(true);
        mainInstance.run();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);            
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        world.drawShapes(g);
    }
}
