package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary implements IDictionary{

	IAVLTree dictionary ;
	int size = 0 ;
	public void load(File file) {
		String line = null ;
		size = 0;
		try{
			FileReader f = new FileReader(file);
			BufferedReader b = new BufferedReader(f);
			line = b.readLine();
			for(int i = 0 ; !line.equals(null) ; i++){
				dictionary .insert(line);
				size++;
				line = b.readLine();
			}
			b.close();
		}catch (FileNotFoundException e){
			System.out.println("no file");
		}catch (IOException e){
			System.out.println("no");
		}
	}

	public boolean insert(String word) {
		boolean search = dictionary.search(word);
		if(search){
		  return false;	
		}
		else{
			dictionary.insert(word);
			size++;
			return true;
		}
	}

	public boolean exists(String word) {
		return (dictionary.search(word));
	}

	public boolean delete(String word) {
		boolean del = dictionary.search(word);
		if(del){
			size--;
		}
		return del;
	}

	public int size() {
		return size;
	}

	public int height() {
		return dictionary.height();
	}

}

