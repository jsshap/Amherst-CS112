import java.util.List;
import java.util.LinkedList;
import java.util.Random;
public class SubstitutionCipher extends Cipher{

    private long key;
    int[][] table;
    

    public SubstitutionCipher (long k){
	super(k);//_key=k;
	table = new int[2][256];
	Random rand = new Random(getKey());
	for (int i=0; i<256; i++){
	    table[0][i]= i;//This is the top row with the normal set of characters
	    table[1][i]=1999;//This is the bottom row. It will receive values
	}
	int i=0;
	boolean toAdd = true;
	while (i<256){
	    int sub = rand.nextInt(256);
	    
	    for (int j=0; j<256; j++){
		if (table[1][j]==sub){
		    toAdd=false;
		    
		 
		}
	    }
	    
	    if (toAdd){
		table[1][i]=sub;
		i++;
	    }
	    toAdd=true;
		
	}

    }

    public List<Character> encrypt(List<Character> clearText){
	
	
	List<Character> encrypted= new LinkedList<Character>();
	for (char c : clearText){
	    for (int k=0; k<256; k++)
		if (table [0][k]== (int) c)
		    encrypted.add((char) table[1][k]);
	}
	return encrypted;
    }

    public List<Character> decrypt(List<Character> encrypted){
	LinkedList<Character> decrypted=new LinkedList<Character>();
	for (char c : encrypted){
	    for (int k=0; k<256; k++)
		if (table [1][k]==(int) c)
		    decrypted.add((char) table[0][k]);
	}

	return decrypted;
    }

	

}
