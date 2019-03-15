package eg.edu.alexu.csd.filestructure.avl;

public class AVLTree <T extends Comparable<T>> implements IAVLTree<T>{
	
	private static final int ALLOWED_IMBALANCE = 1;
	INode<T> root=null;

	@Override
	public void insert(T key) {
		root=insertNode(key,(Node<T>) root);
		
	}

	@Override
	public boolean delete(T key) {
		if(search(key)) {
			root=remove(key,(Node<T>) root);
			return true;
		}
		return false;
	}

	@Override
	public boolean search(T key) {
		return search(root,key);
	}
	private boolean search(INode<T> r, T val)
    {
        boolean found = false;
        while ((r != null) && !found)
        {
            T rval = r.getValue();
            if (val.compareTo(rval)<0)
                r = r.getLeftChild();
            else if (val.compareTo(rval)>0)
                r = r.getRightChild();
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
	public INode<T> getNode(T key){
		INode<T> node=getTree();
		while(node!=null && !node.getValue().equals(key)) {
			if(key.compareTo(node.getValue())<0) {
				node=node.getLeftChild();
			}else {
				node=node.getRightChild();
			}
		}
		return node;
	}
	
	public INode<T> findMin(INode<T> root){
		INode<T> x=root;
		while(x.getLeftChild()!=null) {
			x=x.getLeftChild();
		}
		return x;
			
	}
	public int height() {
		// TODO Auto-generated method stub
		return heightNode((Node<T>) root)+1;
	}

	@Override
	public INode<T> getTree() {
		// TODO Auto-generated method stub
		return null;
	}
	private Node<T> insertNode( T x, Node<T> t )
	{
		if( t == null || t.getValue()==null)
		return new Node<>( x, null, null );
		int compareResult = x.compareTo( t.getValue() );
		if( compareResult < 0 )
			t.setLeftChild(insertNode( x,  (Node<T>) t.getLeftChild() ));
		//else if( compareResult > 0 )
			//t.setRightChild(insertNode( x,  (tempNode<T>) t.getRightChild() ));
		else
			t.setRightChild(insertNode( x,  (Node<T>) t.getRightChild() ));
		 // Duplicate; do nothing
		return balance( t );
	}
	
	private Node<T> balance( Node<T> t )
	{
	if( t == null )
	return t;
	if( heightNode( (Node<T>) t.getLeftChild() ) - heightNode( (Node<T>) t.rightChild ) > ALLOWED_IMBALANCE )
	if( heightNode( (Node<T>) t.getLeftChild().getLeftChild() ) >= heightNode( (Node<T>) t.getLeftChild().getRightChild() ) )
	t = rotateWithLeftChild( t );
	else
	t = doubleWithLeftChild( t );
	else
	if( heightNode( (Node<T>) t.getRightChild() ) - heightNode( (Node<T>) t.getLeftChild() ) > ALLOWED_IMBALANCE )
	if( heightNode(  (Node<T>) t.getRightChild().getRightChild() ) >= heightNode( (Node<T>) t.getRightChild().getLeftChild() ) )
	t = rotateWithRightChild( t );
	else
	t = doubleWithRightChild( t );
	t.setHeight( Math.max( heightNode( (Node<T>) t.getLeftChild() ), heightNode( (Node<T>) t.getRightChild() ) ) + 1);
	return t;
	}
	
	
	private Node<T> rotateWithLeftChild( Node<T> k2 )
	{
		Node<T> k1 = (Node<T>) k2.getLeftChild();
		k2.setLeftChild(k1.getRightChild());
		k1.setRightChild(k2);
		k2.setHeight ( Math.max( heightNode( (Node<T>) k2.getLeftChild() ), heightNode( (Node<T>) k2.getRightChild() ) ) + 1);
		k1.setHeight( Math.max( heightNode( (Node<T>) k1.getLeftChild() ), k2.getHeight() ) + 1);
		return k1;
	}
	
	private Node<T> rotateWithRightChild( Node<T> k2 )
	{
		Node<T> k1 = (Node<T>) k2.getRightChild();
		k2.setRightChild(k1.getLeftChild());
		k1.setLeftChild(k2);
		k2.setHeight ( Math.max( heightNode( (Node<T>) k2.getRightChild() ), heightNode( (Node<T>) k2.getLeftChild() ) ) + 1);
		k1.setHeight( Math.max( heightNode( (Node<T>) k1.getRightChild() ), k2.getHeight() ) + 1);
		return k1;
	}
	
	private Node<T> doubleWithLeftChild( Node<T> k3 )
	{
		k3.setLeftChild( rotateWithRightChild( (Node<T>) k3.getLeftChild()));
		return rotateWithLeftChild( k3 );
	}
	
	private Node<T> doubleWithRightChild( Node<T> k3 )
	{
		k3.setRightChild( rotateWithLeftChild( (Node<T>) k3.getRightChild()));
		return rotateWithRightChild( k3 );
	}
	
	
	private Node<T> remove( T x, Node<T> t )
	{
		if( t == null )
		return t;
		// Item not found; do nothing
		int compareResult = x.compareTo( t.getValue() );
		if( compareResult < 0 )
		t.setLeftChild( remove( x, (Node<T>) t.getLeftChild() ));
		else if( compareResult > 0 )
		t.setRightChild( remove( x, (Node<T>) t.getRightChild() ));
		else if( t.getLeftChild() != null && t.getRightChild() != null ) // Two children
		{
		t.setValue( findMin( t.getRightChild() ).getValue());
		t.setRightChild(remove( t.getValue(), (Node<T>) t.getRightChild() ));
		}
		else
		t=( t.getLeftChild() != null ) ?  (Node<T>)t.getLeftChild() : (Node<T>)t.getRightChild();
		return balance( t );
	}
	
	
	private int heightNode( Node<T> t )
	{
	return t == null ? -1 : t.getHeight();
	}
}
