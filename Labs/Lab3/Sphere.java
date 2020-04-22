
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
class Sphere{
    
    public double positionX;
    public double positionY;

    private double velocityX;
    private double velocityY;

    private int radius;
    private Color scolor;

    private boolean counterclockwise;

    
 
     
    public Sphere(int WIDTH, int HEIGHT, int i){
    //This is the constructor
	if (Math.random()>.5)
	    counterclockwise=true;
	else
	    counterclockwise=false;
	positionX= ((int) (Math.random()*(WIDTH)));
	positionY= ((int) (Math.random()*(HEIGHT)));
	radius=(int) (Math.random()*150)+1;
	velocityX= ((int) (Math.random()*(1000)));
	velocityY= ((int) (Math.random()*(1000)));


	
	
	scolor = new Color((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255));
	    
       
    }

    public void update(double t, int WIDTH, int HEIGHT){

	int centerX= WIDTH/2-25;
	int centerY= HEIGHT/2-25;
	
	double accela= positionX-centerX;
	double accelb= positionY-centerY;
	    
	velocityY=(accela);
	velocityX=-(accelb);

	if (!counterclockwise){
	    velocityY*=-1;
	    velocityX*=-1;
	}

	


	

	positionX-=velocityX*t;
	positionY-=velocityY*t;
	
	
	

	if (positionX >= WIDTH-radius){
	    positionX = WIDTH-radius;
	    velocityX *= (-1);
	}
	if (positionY >= HEIGHT-radius){
	    positionY = HEIGHT-radius;
	    velocityY *= (-1);
	}
	if (positionX <= 0){
	    positionX = 0;
	    velocityX *= (-1);
	}
	if (positionY <= 0){
	    positionY = 0;
	    velocityY *= (-1);
	}


	

	
	
	

	
    }

    public void draw(Graphics g){
	
	g.setColor(scolor);
	g.fillOval((int) positionX, (int) positionY, radius, radius);
    }
     
 
 
     
}
