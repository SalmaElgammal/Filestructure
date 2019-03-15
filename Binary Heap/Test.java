package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import eg.edu.alexu.csd.filestructure.sort.INode;
//import eg.edu.alexu.csd.filestructure.sort.ISort;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import eg.edu.alexu.csd.filestructure.sort.INode;
//import eg.edu.alexu.csd.filestructure.sort.cs28.Heap;
//import eg.edu.alexu.csd.filestructure.sort.cs28.Sort;
import eg.edu.alexu.csd.filestructure.sort.IHeap;

public class Test {

	public static void main (String[] arg0) {
		testBuildInput();
		testInsertHeapElement();
		testExtractHeapElement();
		testEmptyHeap();
		testBuildMaxHeap();
		testGenericTypeBuildInput();
		testMaxHeapify();
		testMaxHeapExtract();
		testMaxHeapInsert();
		System.out.println("(Test heap structure)\nSmoke Test Passed");
		System.out.println("\n======================\n(Test hesp sort)");
		//testFastSort(); NOT included.
		//testSlowSort(); NOT included.
		testHeapSort();
	}

	public static void testBuildInput() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		Collection<Integer> input = Util.toCollection(6, 3, 5, 7, 1, 4, 2);
		heap.build(input);
		Assert.assertEquals(input.size(), heap.size());
	}

	public static void testInsertHeapElement() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		Assert.assertEquals(0, heap.size());
		heap.insert(1000);
		heap.insert(36);
		heap.insert(19);
		Assert.assertEquals(3, heap.size());
	}

	public static void testExtractHeapElement() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		heap.insert(1000);
		heap.insert(36);
		heap.insert(19);
		int sizeBefore = heap.size();
		Integer max = heap.extract();
		Assert.assertNotNull(max);
		int sizeAfter = heap.size();
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}

	public static void testEmptyHeap() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		heap.insert(1000);
		heap.insert(36);
		heap.insert(19);
		int size = heap.size();
		for (int i = 0; i < size; i++)
			Assert.assertNotNull(heap.extract());
		Assert.assertEquals(0, heap.size());
	}

	// tests buildMaxHeap small
	public static void testBuildMaxHeap() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		int nElements = 1000;
		ArrayList<Integer> input = Util.buildRandomInput(nElements);
		// Collections.shuffle(input);
		heap.build(input);
		Assert.assertNotNull(heap.getRoot());
		ArrayList<Integer> list = new ArrayList<Integer>();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), list));
		Assert.assertEquals(input.size(), list.size());
		Collections.sort(list);
		Integer[] inputArray = new Integer[input.size()];
		input.toArray(inputArray);
		Arrays.sort(inputArray);
		boolean coverageFlag = true;
		for (int i = 0; i < inputArray.length && coverageFlag; i++) {
			coverageFlag &= inputArray[i].equals(list.get(i));
		}
		Assert.assertTrue(coverageFlag);
	}

	public static void testGenericTypeBuildInput() {
		@SuppressWarnings("unchecked")
		IHeap<String> heap = (IHeap<String>) new Heap<String>();
		// Collection <Integer> input = Util.toCollection(6, 3, 5, 7, 1, 4, 2);
		String[] inputArray = { "Fawzy", "Caroline", "Ebtehal", "Gamal", "Ahmed", "Dina", "Bahi" };
		ArrayList<String> input = new ArrayList<String>();
		for (String s : inputArray)
			input.add(s);
		heap.build(input);
		Assert.assertNotNull(heap.getRoot());
		ArrayList<String> list = new ArrayList<String>();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), list));
		Assert.assertEquals(input.size(), list.size());
		Collections.sort(list);
		Arrays.sort(inputArray);
		boolean coverageFlag = true;
		for (int i = 0; i < inputArray.length && coverageFlag; i++) {
			coverageFlag &= inputArray[i].equals(list.get(i));
		}
		Assert.assertTrue(coverageFlag);
	}

	public static void testMaxHeapify() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		Collection<Integer> input = Util.toCollection(6, 3, 5, 7, 1, 4, 2);
		heap.build(input);
		// expected heap tree order according to order of input: 7,6,5,3,1,4,2
		// test (a): heapify a leaf node:
		INode<Integer> leaf = heap.getRoot().getLeftChild().getLeftChild();
		heap.heapify(leaf);
		ArrayList<Integer> holder = new ArrayList<Integer>();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), holder));
		// expected heap tree order after maxHeapify: 7,6,5,3,1,4,2
		// test (b): heapfiy an intermediate
		INode<Integer> intermediate1 = heap.getRoot().getRightChild();
		intermediate1.setValue(1);
		heap.heapify(intermediate1);
		holder.clear();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), holder));
		// expected heap tree order after maxHeapify: 7,6,4,3,1,1,2
		// test (c): heapify a root node, but not till leaves
		INode<Integer> root1 = heap.getRoot();
		root1.setValue(3);
		heap.heapify(root1);
		holder.clear();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), holder));
		// expected heap tree order after maxHeapify: 6,3,4,3,1,1,2

		// test (d): heapify a root node, down to a leaves
		INode<Integer> root2 = heap.getRoot();
		root2.setValue(0);
		heap.heapify(root2);
		holder.clear();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), holder));
		// expected heap tree order after maxHeapify: 4,3,2,3,1,1,0

	}

	public static void testMaxHeapExtract() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		Collection<Integer> input = Util.toCollection(6, 3, 5, 7, 1, 4, 2);
		heap.build(input);
		// expected heap tree order according to order of input: 7,6,5,3,1,4,2
		ArrayList<Integer> holder = new ArrayList<Integer>();
		Integer[] sortedInput = new Integer[input.size()];
		input.toArray(sortedInput);
		Arrays.sort(sortedInput);

		for (int i = sortedInput.length - 1; i > 0; --i) {
			Integer max = heap.extract();
			Assert.assertEquals(sortedInput[i], max);
			Assert.assertTrue(Util.validateHeap(heap.getRoot(), holder));
			holder.clear();
		}
	}

	public static void testMaxHeapInsert() {
		@SuppressWarnings("unchecked")
		IHeap<Integer> heap = (IHeap<Integer>) new Heap<Integer>();
		heap.build(new ArrayList<Integer>()); // empty heap
		Collection<Integer> input = Util.toCollection(6, 3, 5, 7, 1, 4, 2);
		for (Integer element : input) {
			heap.insert(element);
		}
		ArrayList<Integer> holder = new ArrayList<Integer>();
		Assert.assertTrue(Util.validateHeap(heap.getRoot(), holder));
	}
	
	public static void testHeapSort() {
		
		ISort <Integer> instance = new Sort<Integer>();
//		Collection <Integer> inputCollection = Util.toCollection(6, 3, 5, 7, 1, 4, 2);
		ArrayList <Integer> input = new ArrayList <Integer> ();
		input.add(6);
		input.add(3);
		input.add(5);
		input.add(7);
		input.add(1);
		input.add(4);
		input.add(2);

		Integer[]sortedInput=new Integer[input.size()];
		input.toArray(sortedInput);
		Arrays.sort(sortedInput);
		IHeap <Integer> sorted=instance.heapSort(input);
		ArrayList <Integer> bfs=new ArrayList <Integer>();

		Queue <INode	<Integer>> q = new LinkedList <INode <Integer>> ();
		q.add(sorted.getRoot());
		while(!q.isEmpty()) {
			INode <Integer> current = q.poll();
			bfs.add(current.getValue());
			if (current.getLeftChild() != null)
			q.add(current.getLeftChild());
			if (current.getRightChild() != null)
			q.add(current.getRightChild());
		}
		boolean sortedFlag = true;
		for(int i = 0;i < sortedInput.length&&sortedFlag;++i) {
			sortedFlag &= sortedInput[i] == bfs.get(i);
		}
		System.out.println("Expected output");
		for(int i = 0;i < sortedInput.length;++i) {
			System.out.print(sortedInput[i] + " ");
		}
		System.out.println();
		System.out.println("\nYour output");
		for(int i = 0;i < bfs.size();++i) {
			System.out.print(bfs.get(i) + " ");
		}


		System.out.println(sortedFlag? "\n\nSantyTest passed" : "\n\nSantyTest failed");
	}
	
	public static class Util {
		
		public static Collection <Integer> toCollection(Integer... elements){
		Collection <Integer> input = new LinkedList <Integer> ();
		for(Integer i : elements)
		input.add(i);
		return input;
		}

		public static <T extends Comparable <T>> boolean validateHeap(INode<T> node, ArrayList <T> inOrderList) {
		T v = node.getValue();
		inOrderList.add(node.getValue());
		INode<T> left = node.getLeftChild();
		if (left != null) {
		if (v.compareTo(left.getValue()) < 0 || !validateHeap(left, inOrderList))
		return false;
		}
		INode<T> right = node.getRightChild();
		if (right != null) {
		if (v.compareTo(right.getValue()) < 0 || !validateHeap(right, inOrderList))
		return false;
		}
		return true;
		}
		public static ArrayList <Integer> buildRandomInput(int nElements) {
		int maxVal = (int) (0.8 * nElements);
		Random randGen = new Random(0);
		ArrayList <Integer> input = new ArrayList <Integer> (nElements);
		for (int i = 0; i < nElements; i++) {
		int value = randGen.nextInt() % maxVal;
		input.add(value);
		}
		return input;
		}
		}

}