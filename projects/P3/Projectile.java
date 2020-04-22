
import java.awt.Color;
import java.awt.Graphics;

public class Projectile{


    //Variables ================================================================================================
    public double x;
    public double y;
    public double xVelocity;
    public double yVelocity;
    public int size=8;
    public int power;

    //================================================


    //Constructor================================================


    Projectile(int initialX, int initialY, int finalX, int finalY, int power){//This finds the location of the frog and the click, calculates the angle, calculates the x and y components of velocity and sets the proper values for instance variables.
	x=initialX;
	y=initialY;
	double theta = Math.atan(   ((double) (finalY-initialY))   /   ((double) (finalX-initialX))  );
	if (finalX<=initialX)
	  theta+=Math.PI;
	xVelocity =  (15*Math.cos(theta));
	yVelocity =  (15*Math.sin(theta));
	this.power=power;//For now, power is always passed in as 10. This can be changed in Frogger
	
    }
    //================================================


    //Methods================================================


    public void update (){
	x+=xVelocity;
	y+=yVelocity;
    }//update
    
    public void draw (Graphics g){
	g.setColor(Color.WHITE);
	g.fillOval((int)x+size/2, (int)y+size/2, size, size);
    }//draw


}//Projectile
