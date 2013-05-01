package ds.trees.brian;

import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;


@SuppressWarnings("hiding")
public class Node<File> implements Serializable {
	File element;
	ArrayList<File> files;
	ArrayList<Node<File>> list;
	long size;
	
	@SuppressWarnings("unchecked")
	public Node(File theElement)
	{
		if(theElement != null){
			element =  theElement;
			ArrayList<Node<File>> k = new ArrayList<Node<File>>();
			ArrayList<File> f = new ArrayList<File>();
			files = null;
			boolean truth = ((java.io.File) element).isDirectory();
			if(truth){
					for(java.io.File file: ((java.io.File) element).listFiles()){
						if(file.isDirectory()){
							k.add(new Node<File>((File) file));
						}
						else{
							f.add((File) file);
						}
					}
					setList(k);
					setFiles(f);
			}
		}
	}
	
	private void setFiles(ArrayList<File> f) {
		files = f;
		
	}
	
	public ArrayList<File> getFiles(){
		return this.files;
	}

	private void setList(ArrayList<Node<File>> k){
		list = k;
	}
	
	public ArrayList<Node<File>> getList(){
		return this.list;
	}
	
	public void printTree(){
		System.out.println(this.element.toString());
		for(Node<File> node : this.list){
			node.printTree();
		}
		for(File f : this.files){
			System.out.println(f.toString());
		}
	}
	
}