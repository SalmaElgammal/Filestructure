package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.List;

public class main {
	static IHeap<Integer> h = new Heap<Integer>();
	public static void main(String[] args) {
		List<Integer> l=new ArrayList<Integer>();
		l.add(6);
		l.add(3);
		l.add(5);
		l.add(7);
		l.add(1);
		l.add(4);
		l.add(2);
		h.build(l);
		l=((Heap) h).getHeap();
		print();
		
		INode<Integer> intermediate1 = h.getRoot().getRightChild();
		h.extract();
		print();
		
		h.insert(10);
		print();
		
		

	}
	
	public static void print() {
		for(int i=0;i<h.size();i++) {
			System.out.print(((Heap<Integer>) h).getHeap().get(i).getValue()+" ");
		}
		System.out.println();
		System.out.println("*********");
	}

}
