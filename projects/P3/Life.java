import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;

public class Life implements drawable{

    //variables ================================================
    public int y;
    public int x;
    private int size=50;
    private Random rand = new Random();
    public boolean eaten;
    //================================================


    //Constuctor================================================
    public Life (){//Randomly place lives
	this.y=(rand.nextInt(15))*50;
	this.x=rand.nextInt(21)*50;
	System.out.println(x + " " + y);
    }
    //================================================

    //Methods================================================

    public void draw(Graphics g, String s){
	if (!eaten){//Like the enemies, it's easier to just not draw them if they are eaten than it is to remove them
	    g.setColor(Color.WHITE);
	    g.fillRect(x,y,size,size);
	    g.setColor(Color.BLACK);
	    g.drawString("Life!!", x+7, y+25);
	}
    }//draw
    
    public void update(){//This allows them to be held in ObjectHolders; it serves no other purpose
    }//update
    //================================================



}//Life
