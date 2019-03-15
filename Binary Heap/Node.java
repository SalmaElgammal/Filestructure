package eg.edu.alexu.csd.filestructure.sort;

import eg.edu.alexu.csd.filestructure.sort.INode;

public class Node<T extends Comparable<T>> implements INode<T> {

	int index;
	T value;
	INode<T> leftChild,rightChild,parent;
	public INode<T> getLeftChild() {
		return leftChild;
		
	}


	public INode<T> getRightChild() {
		return rightChild;
	}


	public INode<T> getParent() {
		return parent;
		
	}


	public T getValue() {
		// TODO Auto-generated method stub
		return value;
	}


	public void setValue(T value) {
		this.value= value;
		
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int n) {
		index=n;
	}
	
	public void setLeftChild(INode<T> n) {
		leftChild=n;
	}
	public void setRightChild(INode<T> n) {
		rightChild=n;
	}
	public void setParent(INode<T> n) {
		parent=n;
	}
	

}
