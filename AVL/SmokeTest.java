package eg.edu.alexu.csd.filestructure.avl;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import eg.edu.alexu.csd.filestructure.avl.IAVLTree;




public class SmokeTest {
	public static void main (String[] arg0) {
		testHeight();
		testSearch();
		System.out.println("Done");
	}
	
	
	
	public static Class <?> getSpecifications(){
	return IAVLTree.class;
	}
	
	@org.junit.Test
	public static void testHeight() {
	@SuppressWarnings("unchecked")
	IAVLTree <Integer> avl = (IAVLTree <Integer> ) new AVLTree();
	assertEquals("Invalid height", 0, avl.height());
	avl.insert(123);
	assertEquals("Invalid height", 1, avl.height());
	avl.insert(456);
	assertEquals("Invalid height", 2, avl.height());
	avl.delete(456);
	assertEquals("Invalid height", 1, avl.height());
	avl.delete(123);
	assertEquals("Invalid height", 0, avl.height());
	for(int i=0; i<1000; i++)
	avl.insert(55);
	assertTrue("Invalid height after adding duplicates", avl.height() >1);
	}
	
	@org.junit.Test
	public static void testSearch() {
	@SuppressWarnings("unchecked")
	IAVLTree <Integer> avl = (IAVLTree <Integer> ) new AVLTree();
	Set <Integer> elements = new HashSet <Integer> ();
	while(elements.size() <1000)
	elements.add((int)(Math.random() * 100000));
	for(Integer e : elements){
	assertFalse("Find non existing element", avl.search(e));
	avl.insert(e);
	assertTrue("Can't non existing element", avl.search(e));
	}
	}
}