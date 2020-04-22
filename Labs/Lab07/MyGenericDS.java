
public class MyGenericDS<E> implements GenericOrderedCollection<E>{


    public int length;
    public GenericNode<E> end;
    public MyGenericDS(){
	end = null;
	length=0;
    }
    
    public void append(E toAppend){
	this.length++;
	GenericNode<E> addition= new GenericNode<E> (toAppend);
	addition.next=end;
	end=addition;
    }
    
    public E peek(){
	if (end == null)
	    return null;
	else
	    return end.elem;
	
    }
    public E pop(){
	E popped = this.peek();
	if (end != null)
	    end = end.next;
	this.length--;
	return popped;
    }
    public void remove(int index){
	boolean done=false;
	GenericNode<E> check=end;
	if (index==length-1)
	    end=end.next;
	else{
	    int i=length-1;
	    while (!done){
		if (i-1==index){
		    check.next=check.next.next;
		    done=true;
		}
		check=check.next;
		i--;	    
	    }	    
	}
	length--;
    }

    
    public String toString(){
	String printing="";
	GenericNode<E> adding=end;
	while (adding!=null){
	    printing=adding.elem.toString()+" "+printing;
	    adding=adding.next;
	}
	return printing;
	
    }
    public int length(){
	return this.length;
	
    
    }
}


class GenericNode<E>{
    public GenericNode<E> next;
    public E elem;
    
    public GenericNode(E elem){
	this.elem=elem;
    }
}
