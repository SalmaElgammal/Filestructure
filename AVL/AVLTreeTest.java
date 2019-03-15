package eg.edu.alexu.csd.filestructure.avl;
import static org.junit.Assert.*;
import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.AVLUtil;
public class AVLTreeTest {
static AVLUtil <Integer> validator=new AVLUtil <Integer> ();


public static void main (String[] arg0) {
	testInsert();
	testInsertSkewed();
	testSearch();
	testDelete();
	System.out.println("Doneeeee :D");
}

public AVLTreeTest() {
	validator = new AVLUtil <Integer> ();
}
public static Class <?> getSpecifications(){
	return IAVLTree.class;
}

@org.junit.Test
public static void testInsert() {
	@SuppressWarnings("unchecked")
	IAVLTree <Integer> avl = (IAVLTree <Integer> ) new AVLTree();
	int[] input = {13,8,5,9,4,6,12,2,1,3};
	 int[] height = {0,1,1,2,2,2,2,3,3,3};
	//int[] height = {1,2,2,3,3,3,3,4,4,4};
	
	for (int i = 0; i<input.length; ++i) {
		avl.insert(input[i]);
		assertEquals(avl.height(), height[i]);
	}
}
@org.junit.Test
public static void testInsertSkewed() {
	@SuppressWarnings("unchecked")
	IAVLTree <Integer> avl = (IAVLTree <Integer> ) new AVLTree();
	for (int i = 1; i<1000; ++i) {
		avl.insert(i);
		//System.out.println(i);
	}
	assertTrue(validator.validateAVL(avl.getTree()));
	
}
@org.junit.Test
public static void testSearch() {
	@SuppressWarnings("unchecked")
	IAVLTree <Integer> avl = (IAVLTree <Integer> ) new AVLTree();
	int[] input = {13,8,5,9,4,6,12,2,1,3};
	int[] positive = {8,12,3};
	int[] negative = {0,11,20};
	
	for (int i = 0; i<input.length; ++i) {
		avl.insert(input[i]);
	}
	for (int q : positive)
		assertTrue(avl.search(q));
	for (int q : negative)
		assertFalse(avl.search(q));
}
@org.junit.Test
public static void testDelete() {
	@SuppressWarnings("unchecked")
	IAVLTree <Integer> avl = (IAVLTree <Integer> ) new AVLTree();
	int[] input = {13,8,5,9,4,6,12,2,1,3};
	
	for (int i = 0; i<input.length; ++i)
		avl.insert(input[i]);
	// try deleting non-existing elements
	for (int i = -1; i >= -5; --i) {
		assertFalse(avl.delete(i));
	}
	// check that the tree structure is not affected
	assertTrue(validator.validateAVL(avl.getTree()));
	// delete all existing elements
	int[] deleteOrder = {8,4,2,12,9,13,5,3,1,6};
	for (int element : deleteOrder) {
		assertTrue(avl.delete(element));
		assertTrue(validator.validateAVL(avl.getTree()));
	}
}
}