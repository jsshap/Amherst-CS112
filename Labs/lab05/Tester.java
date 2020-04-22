
public class Tester{
    public static void main(String args[]){
        MyDS ds = new MyDS();
	testDS(ds);
    }

    public static void testDS(OrderedCollection ds){
	
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
    
    }
}
