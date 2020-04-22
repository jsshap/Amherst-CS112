
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.util.Random;


public class ObjectHolder<E extends drawable>{

    /*
     *ObjectHolder is a generic LinkedList. It is unique in that it has draw and update methods that iterate through all of its elements and calls their respective draw/update methods.
     *
     */

    //Variables================================================

    private int length;
    public GenericNode<E> end;
    //================================================

    // Constructor ================================================
    
    public ObjectHolder(){
	end = null;
	length=0;
    }

    //================================================

    //Methods ================================================
    
    
    public void append(E toAppend){
	this.length++;
	GenericNode<E> addition= new GenericNode<E> (toAppend);
	addition.next=end;
	end=addition;
    }//append
    
    public void update(){//This does not update the ObjectHolder, but rather each element in the ObjectHolder
	

	GenericNode<E> updating = this.end;
	for (int i=0; i<this.length; i++){
	    updating.elem.update();
	    updating=updating.next;

	}
     }//update

    public void draw(Graphics g, String biome){//This does not draw the ObjectHolder, but rather each element in the ObjectHolder

	GenericNode<E> drawing=this.end;
	for (int i=0; i<this.length(); i++){
	    drawing.elem.draw(g, biome);
	    drawing=drawing.next;

	}
    }//draw



    public E locate(int index){//Returns the object at the specific index in the ObjectHolder
	boolean done=false;
	GenericNode<E> check=end;
	if (index==length-1)
	    return end.elem;
	else{
	    int i=length-1;
	    while (!done){
		if (i-1==index){
		    return check.next.elem;
		    
		}
		check=check.next;
		i--;	    
	    }	    
	}
	return null;//SHOULDN'T BE REACHABLE, BUT THE COMPILER NEEDED IT to work
    }//locate

    

    public int length(){
	return this.length;
    }//length

    
}//ObjectHolder

//================================================================================================================================================
//================================================================================================================================================
//================================================================================================================================================
//================================================================================================================================================


class GenericNode<E>{//This is a helper class for ObjectHolder. ObjectHolders are linked lists of GenericNodes
    public GenericNode<E> next;
    public E elem;
    
    public GenericNode(E elem){
	this.elem=elem;
    }
    public GenericNode(){
    }
}//GenericNode
