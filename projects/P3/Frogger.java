import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;


public class Frogger extends JPanel implements KeyListener, MouseListener{

    // Variables ================================================================================================================================================
    
    public static final int WIDTH = 1050;
    public static final int HEIGHT = 750;
    public static final int FPS = 60;
    World world;
    public int livesRemaining=3;
    public int score;
    public int clickX;
    public int clickY;
    //^^^^^THESE TWO VALUES ARE HERE BECAUSE I WANT TO BE ABLE TO ACCESS THE COORDINATES OF THE CLICK AND Graphics g AT THE SAME TIME. THAT CAN'T BE DONE IF THEY ARE ONLY HELD INSIDE MOUSEPRESSED, SO THEY GET READ IN THAT METHOD AND THEN STORED UP HERE
    Thread mainThread;
    boolean temperatureEnabled=false;
    
 
    

    public boolean gameOver=false;
    public boolean started=false;

    //================================================================================================================================================================================================


    /*//////////////////////////////////////////////////////////////////////////
     *The following class Runner is the main loop of the game.
     *while the game is running, it updates the segments (which mainly entails moving the obstacles in the roads), updates projectiles, and updates the enemies
     *If the frog is killed by the enemies (boolean dead), it creats a new world
     *If an enemy is shot, it removes that enemy and creates a new one (that is inside world)
     *It calls check collision between the frog and the obstacles.
     *If the player runs out of lives, it sets gameOver=true, which will break the loop
     *This also calls for repainting of the main panel
     *This also manages the temperature timers

     *//////////////////////////////////////////////////////////////////////////

    //Methods ================================================================================================================================================

    class Runner implements Runnable{
	public void run()
	{
	    while(true){


		
		if (temperatureEnabled){
		    world.frog.tickTimer();
		    if (started && !gameOver)
			world.frog.checkTimer(world.biome);
		    if (world.frog.burned||world.frog.frozen && !gameOver && started){
			kill();
		    }
		}
		world.updateSegments();
		world.updateProjectiles();
		
		if (world.updateEnemies()&&!gameOver&&started){
		    /*world.updateEnemies serves a dual purpose. 
		      It updates the enemies and also returns true
		      if the enemy catches the frog*/
		    kill();
		}
		world.checkEnemiesShot();

		//If you got to the end of the level....
		if (world.frog.getY()==-50){
		    world = new World(WIDTH, HEIGHT);
		    if (!gameOver && started)
			score += 100;
		}
			
		checkLivesEaten();
		if (checkCollision()&&!gameOver&&started){//checkCollision returns true if an obstacle hits the frog
		    kill();
		}
		repaint();
		try{
		    Thread.sleep(1000/FPS);
		}
		catch(InterruptedException e){}

		//This next if clause is what starts/restarts the game
		if (clickX>=480 && clickX<=600 && clickY>=390 && clickY<= 410 && (gameOver || !started)){
		    clickX=0;
		    start();
		    repaint();
		    try{
			if (livesRemaining>0)
			    Thread.sleep(750);
		    }
		    catch(InterruptedException e){}
		}
	    }
	    
 
	}	
     
    }//Runner
    ////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////

    public void kill(){
	repaint();
	try{
	    if (livesRemaining>0)
		Thread.sleep(1000);
	}
	catch(InterruptedException e){}
	world = new World(WIDTH, HEIGHT);
	livesRemaining--;
	if (livesRemaining==0){
	    gameOver=true;
	}
    }//kill

    
    public boolean checkCollision(){
	/*Find out which segment the frog is in and then check the locations of that segments' obstacles to see any of them overlap with the frog
	 *If it is a safe area, an exception gets thrown and caught because there are no obstacles
	 */
	try
	    {int segmentNum = (HEIGHT-(world.frog.getY()+50))/50;//find out what segment the frog is in
		GenericNode<Obstacle> checking = world.segments.locate(segmentNum).obstacles.end;//THIS THROWS A NULL POINTER EXCEPTION IF IT IS A SAFE AREA, BUT THE TRY/CATCH HANDLES IT
		for (int i=0; i<150; i++){//Do the obstacles occupy the same space as the frog?
		    if (//IF THE FROG OVERLAPS WITH AN OBSTACLE.....
			(checking.elem.xPosition+checking.elem.length)>=(world.frog.getX()+15)
			&& (checking.elem.xPosition)<=(world.frog.getX()+world.frog.sizeX-23)
			&& (checking.elem.yPosition)==(world.frog.getY())
			)
			return true;
		    checking=checking.next;
		}
	    }//THIS IS THE END OF THE TRY BLOCK
	catch (Exception e){}//THIS CATCHES A NULL POINTER EXCEPTION CAUSED BY TRYING TO ACCESS OBSTACLES OF A SAFE AREA.
	return false;
    }//checkCollision

    ////////////////////////////////////
    //
    //Has the frog moved onto an extra life??
    //
    ///////////////////////////////////
    
    public void checkLivesEaten(){
	GenericNode<Life> checking = world.lives.end;
	for (int i=0; i<world.lives.length(); i++){//Do the extra lives occupy the same space as the frog?
	    if (
		checking.elem.x==world.frog.getX()
		&& checking.elem.y==world.frog.getY()
		&& !checking.elem.eaten
		){
		checking.elem.eaten=true;
		livesRemaining++;
	    }
	    checking=checking.next;
	}


    }//checkLivesEaten




    
    ///////////////////////////////////////////////////////////
    /*THIS IS CODE FOR THE MOUSE AND CLICKING EVENTS AND SUCH*/
    
    public void mouseClicked(MouseEvent e) {
	int x = e.getX();
	int y = e.getY();
    }
    public void mouseExited(MouseEvent e) {
	return;
    }
    public void mouseEntered(MouseEvent e) {
	return;
    }
    public void mouseReleased(MouseEvent e) {
	return;
    }
    public void mousePressed(MouseEvent e) {
	int x = e.getX();
	int y = e.getY();
	clickX=x;
	clickY=y;
	world.projectiles.add(new Projectile(world.frog.getX(), world.frog.getY(), x, y, 10));//RIGHT NOW, WE're hardcoding projectile power of 10
    }
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //
    /*CODE FOR KEYBOARD INPUT*/
 
    public void keyPressed(KeyEvent e) {
	char c=e.getKeyChar();
    }//keyPressed

    
    public void keyReleased(KeyEvent e) {
	char c=e.getKeyChar();
    }//keyReleased
    
  
    public void keyTyped(KeyEvent e) {
	char c = e.getKeyChar();
	if (c=='w' || c=='a' || c=='s' || c=='d'){
	    world.frog.jump(c);
	}
	if (c=='r'){
	    score=0;
	    livesRemaining=3;
	    world=new World(WIDTH, HEIGHT);
	}
	if (c=='0'){
	    world.enemiesAllowed=false;
	    world = new World(WIDTH, HEIGHT);
	}
	if (c=='-'){
	    world.enemiesAllowed=true;
	    world = new World(WIDTH, HEIGHT);
	}
	if (c=='e')
	    world.addMoreEnemies();
	if (c=='l')
	    livesRemaining++;
	if (c==';')
	    livesRemaining--;
	if (c=='='){
	    start();
	}
	if (c=='z')
	    gameOver=true;
	if (c=='m')
	    started=false;
	if (c=='t')
	    temperatureEnabled=!temperatureEnabled;
	    
    }//keyTyped

    ////////////////////////////////////////////
    ////////////////////////////////////////////
    ////////////////////////////////////////////
    ////////////////////////////////////////////

    
    
    public void addNotify() {
	super.addNotify();
	requestFocus();
    }//addNotify

    
    // Constructor ================================================================================================================================================
    
    public Frogger(){

	//Constructor for the only instance of Frogger
	Sedan.readImage();//Read in these 3 images as static variables before we create any worlds and need to draw them
	Truck.readImage();
	Enemy.readImage();
	SafeArea.readImage();
	world = new World(WIDTH, HEIGHT);
	addKeyListener(this);
	addMouseListener(this);
	mainThread = new Thread(new Runner()); //creates the runner which is the main loop of the game
	this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	mainThread.start();
	
    }//Constructor
    //================================================================================================================================================

    // Methods cont. ================================================================================================

    public void start(){
	score=0;
	world.enemiesAllowed=false;
	world=new World(WIDTH, HEIGHT);
	livesRemaining=3;
	gameOver=false;
	started=true;
	

    }//start
    //================================================================================================================================================

    // Main ========================================================================================================================================================
     
    public static void main(String[] args){ //This is called main, but it only create the JFrame and the Frogger
	JFrame frame = new JFrame("Extreme Frogger");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Frogger mainInstance = new Frogger();   
	frame.setContentPane(mainInstance);
	frame.pack();
	frame.setVisible(true);
	
    }//main
    //================================================================================================================================================




 
 
    public void paintComponent(Graphics g) {


	/*///////////////////////////////////////////////////////////////////////
	 *This is the code that calls all of the draw methods and prints the score and lives remaining at the top of the screen
	 */////////////////////////////////////////////////////////////////////////
	
	super.paintComponent(g);        
	
	g.setColor(Color.DARK_GRAY);
	g.fillRect(0, 0, WIDTH, HEIGHT);
	g.setColor(Color.WHITE);
	
	
	world.drawSegments(g, world.biome);
	world.frog.draw(g, world.biome);
	g.setColor(Color.WHITE);
	if (world.biome.equals("Tundra")||world.biome.equals("Desert")){
	    g.setColor(Color.BLACK);
	}
	g.drawString("Score: " + String.valueOf(score), 775, 20);
	g.drawString("Lives remaining: " + String.valueOf(livesRemaining), 900, 20);


	if (((world.biome.equals("Desert"))||(world.biome.equals("Tundra"))) && temperatureEnabled)
	    world.frog.drawMovementTimer(g, world.biome);

	
	for(Projectile p: world.projectiles){
	    p.draw(g);
	}
	for(Enemy e: world.enemies){
	    e.draw(g);
	}
	world.drawLives(g, world.biome);
	if (!started){
	    drawStartScreen(g);
	    drawInstructions(g);
	}
	if(gameOver){
	    drawEndScreen(g, score);
	}

    }//paintComponent
    

    public static void drawStartScreen(Graphics g){
	g.setColor(Color.LIGHT_GRAY);
	g.fillRoundRect(360,330,380,100,50,50);
	g.setColor(Color.BLACK);
	g.drawString("Welcome to Extreme Frogger!", WIDTH/2-70, HEIGHT/2-10);
	g.drawString("Instructions -->", 490, HEIGHT/2+7);
	g.setColor(Color.BLUE);
	g.fillRect(480, 390, 120, 20);
	g.setColor(Color.WHITE);
	g.drawString("Start", 525, 405);
    }//drawStartScreen
    
    
    public static void drawEndScreen(Graphics g, int score){
	g.setColor(Color.LIGHT_GRAY);
	g.fillRect(350,330,400,100);
	g.setColor(Color.BLACK);
	g.drawString("GAME OVER. You got " + String.valueOf(score) + " points.", WIDTH/2-80, HEIGHT/2);
	g.setColor(Color.BLUE);
	g.fillRect(480, 390, 120, 20);
	g.setColor(Color.WHITE);
	g.drawString("Restart", 515, 405);
	
    }//drawEndScreen
    

    public static void drawInstructions(Graphics g){
	g.setColor(new Color (54, 200, 75));
	g.fillRoundRect(740, 75, 310, 600, 60, 60);
	g.setColor(Color.BLACK);
	g.drawString("Instructions", 850, 90);
	g.drawString("Maneuver the frog (with wasd) to the top", 750, 110);
	g.drawString("of the screen without being hit by the cars or", 750, 130);
	g.drawString("eaten by the birds chasing you. You can shoot", 750, 150);
	g.drawString("the birds by clicking them. When you get to", 750, 170);
	g.drawString("the top, hit w one more time to get 100 points", 750, 190);
	g.drawString("and start a new level.", 750, 210); 
	g.drawString("If you find yourself in a desert", 750, 230);
	g.drawString("or tundra biome, make sure you keep moving", 750, 250);
	g.drawString("or you might burn your feet or freeze and die...", 750, 270);
	g.drawString("Developer Cheat Codes", 840, 320);
	g.drawString("Disable enemies with \"0\"", 745, 340);
	g.drawString("Enable enmies with \"-\"", 745, 360);
	g.drawString("Add more enemies with \"e\"", 745, 380);
	g.drawString("Add and substract lives with \"l\" and \";\"", 745, 400);
	g.drawString("Reset the world with \"r\"", 745, 420);
	g.drawString("Reset the game with \"m\"", 745, 440);
	g.drawString("Toggle temperature timer with \"t\"", 745, 460);
	g.drawString("Happy jumping and good luck!", 800, 500);

    }//drawInstructions

    //================================================================================================================================================================================================
 
}//Frogger============================================================================================================================================================================================
