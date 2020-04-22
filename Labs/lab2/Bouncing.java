import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class Bouncing extends JPanel{
    public static final int WIDTH = 1424;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;
    public static final int RADIUS = 50;
    public static final double accelGravity= 9.8;
    double positionX;
    double positionY;

    double positionX2;
    double positionY2;

    double positionX3;
    double positionY3;

    //Note: The following are not used yet, you should use them in writing your code.
    double velocityX;
    double velocityY;

    double velocityX2;
    double velocityY2;

    double velocityX3;
    double velocityY3;

    double accelerationX;
    double accelerationY;
    
    class Runner implements Runnable{
        public Runner()
        {

	    
            //X and Y are for the white ball, X2 and Y2 are green, X3 and Y3 are the person
	    //THESE ARE ALL INITIAL VALUES
            positionX = 275;
            positionY = HEIGHT - 275;
	    positionX2 = (int) (Math.random()*WIDTH);
            positionY2 = (int) (Math.random()*HEIGHT);
	    positionX3 = (int) (Math.random()*WIDTH);
            positionY3 = (int) (Math.random()*HEIGHT);
	    

	    //THESE ARE INITIAL VELOCITY VALUES
	    velocityX = 350;
            velocityY = -400;
	    velocityX2 = 20;
            velocityY2 = -25;
	    velocityX3 = -900;
            velocityY3 = -500;
            
        }
	
        public void run()
        {
	   
            while(true){

		
			
		velocityY += accelGravity; //gravity
		
		velocityY2 += accelGravity;//gravity

		accelerationX = (positionX-positionX2)/4;///////////////vortex makes the green ball accel towards the white one
		accelerationY = -(positionY2-positionY)/4;//vortex

		velocityY2 += accelerationY;
		velocityX2 += accelerationX;

		/////end of vortex code
		
		velocityY3 += accelGravity;//graivity

		//THIS CHANGES THE POSTION BY ADDING THE VELOCITY. THE VELOCITY IS CHANGED BY THE ACCELERATIONS ABOVE
		
                positionX += velocityX/ (double)FPS;
                positionY += velocityY / (double)FPS;
		positionX2 += velocityX2/ (double)FPS;
                positionY2 += velocityY2 / (double)FPS;
		positionX3 += velocityX3/ (double)FPS;
                positionY3 += velocityY3 / (double)FPS;


		//BOUNCING.
		//BASICALLY:
		//if (off edge)
		//       {make velocity opposite direction && and place on edge};

		if (positionX<=0){
		    velocityX *= -.95;
		    positionX=0;
		}
		if (positionX>=WIDTH-RADIUS){
		    velocityX *= -.95;
		    positionX=WIDTH-RADIUS;
		}

		if (positionY<=0){
		    velocityY *= -.95;
		    positionY=0;
		}
		if (positionY>=HEIGHT-RADIUS){
		    velocityY *= -.95;
		    positionY=HEIGHT-RADIUS;
		    velocityX *=(.75);
		}
		// NOW FOR THE SECOND OBJECT....
		
		if (positionX2<=0){
		    velocityX2 *= -.85;
		    positionX2=0;
		}
		if (positionX2>=WIDTH-RADIUS){
		    velocityX2 *= -.85;
		    positionX2=WIDTH-RADIUS;
		}

		if (positionY2<=0){
		    velocityY2 *= -.85;
		    positionY2=0;
		}
		if (positionY2>=HEIGHT-RADIUS){
		    velocityY2 *= -.85;
		    positionY2=HEIGHT-RADIUS;
		    velocityX2 *=(.85);
		}
		// NOW FOR THE THIRD OBJECT....
		if (positionX3<=0){
		    velocityX3 *= -.25;
		    positionX3=0;
		}
		if (positionX3>=WIDTH-RADIUS){
		    velocityX3 *= -.25;
		    positionX3=WIDTH-RADIUS;
		}

		if (positionY3<=0){
		    velocityY3 *= -.25;
		    positionY3=0;
		}
		
		if (positionY3>=HEIGHT-100){
		    velocityY3 = -250;
		    positionY3=HEIGHT-110;
		    velocityX3 *=(.5);
		}

              

                repaint();
                try{
                    Thread.sleep(1000/FPS);
                }
                catch(InterruptedException e){}
            }
        }
	
	
    }
    
    public Bouncing(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        Thread mainThread = new Thread(new Runner());
	

        mainThread.start();
    }
    public static void main(String[] args){
        JFrame frame = new JFrame("Physics!!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Bouncing world = new Bouncing();
        frame.setContentPane(world);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);            

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //The cannon you see is actually *not* a photograph of a real cannon.
        //It's drawn by the following. 
        g.setColor(Color.ORANGE);
        int xpts[] = {75, 275, 275, 350, 325, 150};
        int ypts[] = {HEIGHT-50, HEIGHT-250, HEIGHT-275, HEIGHT- 175, HEIGHT-175, HEIGHT-25};
        g.drawPolygon(xpts, ypts, 6);
                
        g.setColor(Color.BLUE);
        g.drawOval(150, HEIGHT-200, 200, 200);

        //this is where the sphere is drawn. As a bonus make it draw something else
        // (e.g., your object from the previous lab).
        g.setColor(Color.WHITE);
        g.fillOval((int)positionX, (int)positionY,  RADIUS,  RADIUS);
	
	if (positionX==positionX2 || positionY==positionY2)//Honestly, I don't know why this is || not &&. I wrote it that way accidently, and then when I fixed it to make it &&, it stopped working. I suspect that it's because even when they are "at rest" on the ground, they still technically bounce and are never at the same Y coordinate.
	    g.setColor(Color.CYAN);
	else
	    g.setColor(Color.GREEN);
	g.fillOval((int)positionX2, (int) positionY2,  RADIUS,  RADIUS);
	g.setColor(Color.RED);
	drawPerson((int)positionX3, (int) positionY3, 26, 26, g);
	int red=0;
	red= (int) Math.sqrt((positionX-positionX2)*(positionX-positionX2)+(positionY-positionY2)*(positionY-positionY2))*2;//the amount of red relates to the distance between the balls
	if (red>255) red=255;
	Color fun = new Color (red, 0, 1);
	g.setColor(fun);
	if (!(positionX==positionX2 || positionY==positionY2)){
	    g.drawLine((int) positionX + RADIUS/2, (int) positionY+RADIUS/2, (int) positionX2+RADIUS/2, (int) positionY2+RADIUS/2);
	}
	
	
    }
    public void drawPerson(int x, int y, int z, int v, Graphics g){
	g.drawOval(x, y, z, v);//(x, y) is top left of person. v is width of head, z is height of head
	g.drawLine((x+v/2), (y+z), (x+v/2), ((y+z)+(z*2)));//spine of stick figure
	g.drawLine(x, (y+2*z), (x+z), (y+2*z));//arms
	g.drawLine(x+v/2,((y+z)+(z*2)), x, ((y+z)+(z*2))+z);//leg 1
	g.drawLine(x+v/2,((y+z)+(z*2)), x+z, ((y+z)+(z*2))+z);// leg 2
	g.drawLine(x,y,x+v,y);//brim of hat
	g.fillRect(x+v/8,y-z/2,3*(v/4),z/2);//top of hat	
   
	}
}
