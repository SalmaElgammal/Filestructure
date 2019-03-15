package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Sort<T extends Comparable<T>> implements ISort<T> {

	@Override
	public IHeap<T> heapSort(ArrayList<T> unordered) {
	
		return null;

	}

	@Override
	public void sortSlow(ArrayList<T> unordered) {
		int counter = 0;
		T num1;
		T num2;
		while (counter < (unordered.size() - 1)) {
			for (int i = 0; i < (unordered.size()-counter-1); i++) {
				num1 =  unordered.get(i);
				num2 =  unordered.get(i + 1);
				T temp;
				if ((num1.compareTo(num2))>0) {
					temp = unordered.get(i);
					unordered.set(i, unordered.get(i + 1));
					unordered.set(i + 1, temp);
				}
			}
			counter++;
		}	
		
	}

	@Override
	public void sortFast(ArrayList<T> unordered) {
		Collections.sort(unordered);
		
		//sortarr(unordered,0,(unordered.size()-1));
		
	}
	public void sortarr (ArrayList<T> arr,int start,int end){
		if(start!=end){
			int pivotIndex = start;
			int i = end;
			int j;
			boolean flage = false;
			T temp;
			while(i!=pivotIndex){
				T pivot = arr.get(pivotIndex);
				T num = arr.get(i);
				if((pivot).compareTo(num)<0){
					if(flage){
						i++;
					}
					else{
					temp = arr.get(pivotIndex);
					arr.set(pivotIndex, num);
					arr.set(i, temp);
					j = pivotIndex;
					pivotIndex = i; 
					i = j ;
					flage = true;
					i++;
					}
				}
				if((pivot).compareTo(num)>=0){
					 if(flage){
					temp = arr.get(pivotIndex);
					arr.set(pivotIndex, num);
					arr.set(i, temp);
					j = pivotIndex;
					pivotIndex = i; 
					i = j ;
					flage = false;
					i--;
					}
					else{
						i--;
					}
				}
		      }
			if(pivotIndex>start){
				sortarr(arr,start,pivotIndex-1);
			}
			if(pivotIndex < end){
				sortarr(arr,pivotIndex+1,end);
			}
		}
	}	



}
