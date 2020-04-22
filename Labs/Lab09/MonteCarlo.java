
import java.util.Random;
import java.util.ArrayList;

class Circle{
    double centerX;
    double centerY;
    double radius;
    public Circle(double x, double y, double r){
        this.centerX = x;
        this.centerY = y;
        this.radius = r;
    }
}
public class MonteCarlo{
    static Random rand = new Random();
    public static void main(String[] args){
        ArrayList<Circle> circs = new ArrayList<Circle>();
        circs.add(new Circle(1, 1, 1));
        circs.add(new Circle(10, 10, 1));
        System.out.println("Estimated area:");
        System.out.println(estimateArea(circs, 1000000));
    }
    private static double sample(double min, double max){
        //This method returns a random number between min and max
        return min + (max - min) * rand.nextDouble();
    }
    private static boolean isIn(double x, double y, Circle c){
        //This method returns true if the point (x, y) is within the circle c, and false otherwise
        double dist = Math.sqrt(Math.pow(x - c.centerX, 2) + Math.pow(y - c.centerY, 2));
        return dist <= c.radius;
    }
    public static double estimateArea(ArrayList<Circle> circles, int numSamples){
	double minX=findMinX(circles);
	double maxX=findMaxX(circles);
	double minY=findMinY(circles);
	double maxY=findMaxY(circles);
	double area = (maxX-minX)*(maxY-minY);
	double countIn=0;
	boolean hit=false;
	for (int i=0; i<numSamples; i++){
	    double x=sample(minX, maxX);
	    double y=sample(minY, maxY);
	    for (Circle c : circles){
		if (isIn(x, y, c)){
		    countIn++;
		    hit=true;
		}
		if (hit){
		    hit=false;
		    break;
		}
	    }
	}
	return ((double) countIn/numSamples)*area;
    }
    public static double findMaxX(ArrayList<Circle> a){
	double maxX=0;
	for (Circle c : a)
	    if (c.centerX+c.radius>maxX)
		maxX=c.centerX+c.radius;
	return maxX;
    }
    
    public static double findMaxY(ArrayList<Circle> a){
	double maxY=0;
	for (Circle c : a)
	    if (c.centerY+c.radius>maxY)
		maxY=c.centerY+c.radius;
	return maxY;
    }

    public static double findMinX(ArrayList<Circle> a){
	double minX=0;
	for (Circle c : a)
	    if (c.centerX-c.radius<minX)
		minX=c.centerX-c.radius;
	return minX;
    }
    public static double findMinY(ArrayList<Circle> a){
	double minY=0;
	for (Circle c : a)
	    if (c.centerY-c.radius<minY)
		minY=c.centerX-c.radius;
	return minY;
    }


    
}
