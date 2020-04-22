
public class MyDS extends OrderedCollection{

    public int length;
    public Node end;
    public MyDS(){
	end = null;
	length=0;
	
    }

    
    public void append(int toAppend){
	this.length++;
	Node addition= new Node (toAppend);
	addition.next=end;
	end=addition;
	if (toAppend == 9)
	    this.checkForPi();
	
    }
    public void checkForPi(){
	
	if (this.toString().contains("3 1 4 1 5 9"))
	    System.out.println("Who has pi on their face now, Pr0HaX0r?");
	
    }
    public int peek(){
	if (end == null)
	    return 0;
	else
	    return end.i;
	}
    public int pop(){
	int popped = this.peek();
	if (end != null)
	    end = end.next;
	this.length--;
	return popped;		
    }
    public String toString(){

	String integers="";
	Node adding=end;
	while (adding!=null){
	    integers=adding.i+" "+integers;
	    adding=adding.next;
	}
	return integers;
	    	    
    }
    public int length(){
	return this.length;
    }
}

class Node{
    public Node next;
    public int i;
    
    public Node(int i){
	this.i=i;
    }

}
