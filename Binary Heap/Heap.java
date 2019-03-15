package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import eg.edu.alexu.csd.filestructure.sort.IHeap;
import eg.edu.alexu.csd.filestructure.sort.INode;

public class Heap<T extends Comparable<T>> implements IHeap<T> {

	//INode<T> A[];
	int size=0;
	ArrayList<INode<T>> A=new ArrayList<INode<T>>();
	public INode<T> getRoot() {
		// TODO Auto-generated method stub
		return A.get(0);
	}

	
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
	public void heapify(INode<T> node) {
		int i=((Node) node).getIndex();
		int l=2*((Node) node).getIndex()+1;
		int r=2*((Node) node).getIndex()+2;
		int largest;
		INode<T> temp;
		if(l<=size()-1 && A.get(l).getValue().compareTo(A.get(i).getValue())>0 ) {
			largest=l;
		}else {
			largest=i;
		}
		
		if(r<=size()-1 && A.get(r).getValue().compareTo(A.get(largest).getValue())>0 ) {
			largest=r;
		}
		
		if(largest!=i) {
			Collections.swap(A, i,largest);   //it swaps indexes..
			((Node) A.get(i)).setIndex(i);      
			((Node) A.get(largest)).setIndex(largest);
			setChildAndParent( i);
			setChildAndParent(largest);
			heapify(A.get(largest));
		}
		
	}

	
	public T extract() {

		INode<T> max=A.get(0);
		Collections.swap(A, 0,size-1);   //it swaps indexes..
		((Node) A.get(0)).setIndex(0);      
		((Node) A.get(size-1)).setIndex(size-1);
		size--;
		A.remove(size);
		heapify(A.get(0));
		return max.getValue();
	}

	
	public void insert(T element) {
		INode n = new Node();
		n.setValue(element);
		((Node) n).setIndex(size);
		size++;
		A.add(n);
		int index=size()-1;
		int parent=index/2;
		while(index>0 && A.get(parent).getValue().compareTo(A.get(index).getValue())<0) {
			Collections.swap(A, index,parent);
			((Node) A.get(index)).setIndex(index);
			((Node) A.get(parent)).setIndex(parent);
			index=parent;
			parent=index/2;
		}
		setChildAndParent(index);
		
	}

	
	public void build(Collection<T> unordered) {
		size=unordered.size();
		List<T> l=new ArrayList<T>(unordered);
		for(int i=0;i<size;i++) {
			INode node=new Node();
			node.setValue(l.get(i));
			((Node) node).setIndex(i);
			
			A.add(node);
		}
		for(int i=size/2;i>=0;i--) {
			if(size!=0) {
				heapify(A.get(i));
			}
		}
		for(int i=0;i<size;i++) {
			setChildAndParent(i);
			
		}
		
	}
	
	public ArrayList<INode<T>> getHeap(){
		return A;
	}
	
	
	public void minHeapify(INode<T> node) {

		int i=((Node) node).getIndex();
		int l=2*((Node) node).getIndex()+1;
		int r=2*((Node) node).getIndex()+2;
		int smallest;
		INode<T> temp,left,right;
		if(l<=size()-1 && A.get(l).getValue().compareTo(A.get(i).getValue())<0 ) {
			smallest=l;
		}else {
			smallest=i;
		}
		
		if(r<=size()-1 && A.get(r).getValue().compareTo(A.get(smallest).getValue())<0 ) {
			smallest=r;
		}
		
		if(smallest!=i) {
			Collections.swap(A, i,smallest);   //it swaps indexes..
			((Node) A.get(i)).setIndex(i);      
			((Node) A.get(smallest)).setIndex(smallest);
			setChildAndParent( i);
			setChildAndParent(smallest);
			heapify(A.get(smallest));
		}
	}
	public void minInsert(T element) {
		INode n = new Node();
		n.setValue(element);
		((Node) n).setIndex(size);
		size++;
		A.add(n);
		int index=size()-1;
		int parent=index/2;
		while(index>0 && A.get(parent).getValue().compareTo(A.get(index).getValue())>0) {
			Collections.swap(A, index,parent);
			((Node) A.get(index)).setIndex(index);
			((Node) A.get(parent)).setIndex(parent);
			index=parent;
			parent=index/2;
		}
		setChildAndParent( index);
		
	}
	
	public void setChildAndParent(int i) {
		int left,right;
		left=i*2+1;
		right=i*2+2;
		((Node) A.get(i)).setParent(A.get(i/2));
		if(left<=size()-1) {
			((Node) A.get(i)).setLeftChild(A.get(i*2+1));
		}else {
			((Node) A.get(i)).setLeftChild(null);
		}
		if(right<=size()-1) {
			((Node) A.get(i)).setRightChild(A.get(i*2+2));
		}else {
			((Node) A.get(i)).setRightChild(null);
		}
	}
	public T minExtract() {

		INode<T> max=A.get(0);
		Collections.swap(A, 0,size-1);   //it swaps indexes..
		((Node) A.get(0)).setIndex(0);      
		((Node) A.get(size-1)).setIndex(size-1);
		size--;
		minHeapify(A.get(0));
		return max.getValue();
	}


}
