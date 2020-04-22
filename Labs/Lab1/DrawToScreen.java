
    import java.awt.Color;
    import java.awt.Graphics;
    import java.awt.Dimension;
    import javax.swing.JPanel;
    import javax.swing.JFrame;
    
    public class DrawToScreen extends JPanel{
        public static final int BOX_WIDTH = 1024;
        public static final int BOX_HEIGHT = 768;
    
        public DrawToScreen(){
            this.setPreferredSize(new Dimension(BOX_WIDTH, BOX_HEIGHT));
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
        
            
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
	    for (int i=1; i<=5; i++){
		for (int j=1; j<=5; j++){
		    drawPerson(i*60, j*120, 26/((j*i)/2+1), 26/((i*j)/2+1), g);
		}
	    }
	    // drawPerson (50, 50, 50, 50, g);
	    //   drawPerson(100, 50, 50, 50, g);
            //Your code here: feel free to remove what is below        
	    /* g.setColor(Color.BLACK);
            g.fillRect(0, 0, BOX_WIDTH, BOX_HEIGHT);
    
            g.setColor(Color.GREEN);
            g.fillOval(50, 50, 30, 20);
    
            g.setColor(Color.RED);
            g.fillRect(100, 100, 20, 30);
    
            g.setColor(Color.BLUE);
            g.drawRoundRect(300, 200, 100, 200, 50, 100);
    
            g.setColor(Color.ORANGE);
            g.drawArc(200, 100, 200, 300, 90, 140);*/
        }
        
        public static void main(String args[]){
            JFrame frame = new JFrame("DrawToScreen");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new DrawToScreen());
            frame.pack();
            frame.setVisible(true);
        }
    }
