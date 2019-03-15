package eg.edu.alexu.csd.filestructure.avl;

public class Node<T extends Comparable<T>> implements INode<T> {
	public Node( T theElement ){
		this( theElement, null, null );
		}
	public Node( T theElement, Node<T> lt, Node<T> rt )
	{ value = theElement; leftChild = lt; rightChild = rt; height = 0; }
	T value;
	INode<T> leftChild,rightChild,parent;
	int height;
	public Node() {
		
	}
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
	
	public void setLeftChild(INode<T> n) {
		leftChild=n;
	}
	public void setRightChild(INode<T> n) {
		rightChild=n;
	}
	public void setParent(INode<T> n) {
		parent=n;
	}
	public void setHeight(int h) {
		height=h;
	}
	public int getHeight() {
		return height;
	}
	

}
