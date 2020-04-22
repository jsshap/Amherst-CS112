import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
 
public class Spinning extends JPanel{
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;
    public static final int FPS = 60;

    public static Sphere[] spheres;
    
    public Spinning(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
     
    public void Go(){
	while(true){
	    
	    for (Sphere s : spheres){
		s.update(1.0 / (double)FPS, WIDTH, HEIGHT);
		
	    }
	    //don't mess too much with the rest of this method
	    repaint();
	    try{
		Thread.sleep(1000/FPS);
	    }
	    catch(InterruptedException e){}
	}
    }
     
    public static void main(String[] args){
	int numSpheres=0;
	if (args.length < 1){
	    System.out.println("When you run this, you can specifiy the number of spheres.");
	    System.out.println("e.g., java Spinning 300");
	    numSpheres=300;
	}
	else{
	    System.out.println("You specified that there should be " + args[0] + " spheres.");
	    numSpheres = Integer.parseInt(args[0]);
	}
	
	//here you'll set up the spheres array
	spheres = new Sphere[numSpheres];
	for (int i=0; i<numSpheres; i++)
	    spheres[i]=new Sphere(WIDTH, HEIGHT);
	
     
	JFrame frame = new JFrame("Spinning Spheres");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Spinning world = new Spinning();
	frame.setContentPane(world);
	frame.pack();
	frame.setVisible(true);
	world.Go();
    }
 
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
	for (Sphere s : spheres){
	    s.draw(g);
	   
	}
    }
}
    
 
 
