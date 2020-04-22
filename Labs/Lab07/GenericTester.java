
public class GenericTester{
    public static void main(String args[]){
        MyGenericDS<int[]> ds = new MyGenericDS<int[]>();
	testDS(ds);
    }

    public static void testDS(GenericOrderedCollection<int[]> ds){


	
	
	ds.append(new int[5]);
	ds.append(new int[5]);
	ds.append(new int[5]);
	ds.append(new int[5]);
	ds.append(new int[5]);
	ds.remove(3);
	System.out.println(ds);
	ds.remove(1);
	System.out.println(ds);
	
	/*
	System.out.println(ds);
        ds.append(1);
        ds.append(2);
	System.out.println(ds.length());
        ds.append(3);
        ds.append(9);
        System.out.println(ds);
        ds.pop();
        System.out.println(ds.peek());
        System.out.println(ds);
        ds.append(4);
        ds.append(5);
        ds.append(6);
        ds.pop();
        ds.pop();
        ds.pop();
        ds.append(7);
        System.out.println(ds);
        ds.append(8);
        ds.append(9);
        ds.append(3);
	ds.append(1);
	ds.append(4);
	ds.append(1);
	ds.append(5);
	ds.append(9);
	ds.pop();
	System.out.println(ds);
	ds.pop();
	ds.pop();
	ds.append(1);
	ds.append(5);
	ds.append(9);
	System.out.println(ds);
        System.out.println(ds.length());
	ds.peek();
	*/
    
    }
}
