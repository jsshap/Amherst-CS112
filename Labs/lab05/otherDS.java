public class otherDS{
    public OtherNode end;

    public otherDS(){
	end = new OtherNode();

    }

    public void addNumber(int num){
	//System.out.println("Checkpoint 1");
	if (end.i==0)///If this is the first number, add it to the
	    end.i=num;////////////////////////////end node
	
	else{
	    //System.out.println("Checkpoint 2");
	    OtherNode addTo=end;
	    boolean added=false;
	    while (!added){
		//	System.out.println("Checkpoint 3");
		if (num>=addTo.i){
		    //    System.out.println("Checkpoint 4");
		    if (addTo.upper==null){
			addTo.upper= new OtherNode();
			addTo.upper.i=num;
			added=true;
			//	System.out.println(num > addTo.i);
		    }
		    else
			addTo=addTo.upper;
		
		}
		else if (num<addTo.i){
		    // System.out.println("Checkpoint 5");
		    if (addTo.lower==null){
			//	System.out.println("Checkpoint 6");
			addTo.lower= new OtherNode();
			addTo.lower.i=num;
			added=true;
			//	System.out.println(num < addTo.i);
		    }
		    else
			addTo=addTo.lower;
		    // System.out.println("Checkpoint 7");
		}
		// System.out.println("Checkpoint 8");
	    }
	    //  System.out.println("Checkpoint 9");

	}

    }
    public void remove(int num){
	boolean found=false;
	OtherNode check=end;
	while (!found){//If it's less we can just go lower. If higher, go higher. if same, we still
	               //need to chceck lower, assuming we want the
	               //farthest down instance of the number.

	    if (num<check.i)
		check=check.lower;
	    
	    else if (num>=check.i){
		if (check.upper!=null)
		    check=check.upper;
	    }
	    found=true;//check now holds the value
	}
	
    }
    

    public int getLargest(){
	OtherNode check;
	check=this.end;
	while (check.upper!=null)
	    check=check.upper;
	return check.i;
    }
    public int getSmallest(){
	OtherNode check;
	check=this.end;
	while (check.lower!=null)
	    check=check.lower;
	return check.i;
    }

}
class OtherNode{
    public OtherNode upper;
    public OtherNode lower;
    public int i;

    public OtherNode(){
	upper=null;
	lower=null;
    }

}
