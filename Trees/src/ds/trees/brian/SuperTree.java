package ds.trees.brian;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SuperTree<Path>implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Node<File> root;
	
	public SuperTree(){
		root = null;
	}

	public void makeEmpty(){
		root = null;
	}
	
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public File contains(File x){
		return contains(x, root);
	}
	
	public File findMin(){
		return findMin(root);
		
	}
	
	public File findMax(){
		Node<File> t = this.root;
		return findMax(t);
	}
	
	public void printTree(){
		Node<File> t = this.root;
		ArrayList<Node<File>> nodes = t.getList();
		ArrayList<File> files = t.getFiles();
		if(nodes != null){
			for(Node<File> f : nodes){
				f.printTree();
			}
		}
		if(files != null){
			for(File currentFile: files){
				System.out.println(currentFile.toString());
			}
		}
	}
	
	private File contains(File x, Node<File> t){
		File same= null;
		if(x.equals(t.element)){
			same = t.element;
			return same;
		}else if(x != (t.element)){
			for(Node<File> current : t.getList()){
					same = contains(x,current);
					if(same!= null)
						return same;
			}
			for(File f : t.getFiles()){	
					if(x.equals(f))
						same = f;
					return same;
			}
		}
		return same;
	}
	
	private File findMin(Node<File> t){
		long min = t.element.length();
		File minFile = t.element;
			for(Node<File> current : t.getList()){
				if( min > findMin(current).length()){
					min = findMin(current).length();
					minFile = findMin(current);
				}
			}
			for(File f : t.getFiles()){
				if(f.length() < min){
					min = f.length();
					minFile = f;
			}
		}
		return minFile;
	}
	
	private File findMax(Node<File> t){
		long max = t.element.length();
		File maxFile = t.element;
			for(Node<File> current : t.getList()){
				if( max < findMax(current).length()){
					max = findMax(current).length();
					maxFile = findMax(current);
				}
			}
			for(File f : t.getFiles()){
				if(f.length() > max){
					max = f.length();
					maxFile = f;
			}
		}
		return maxFile;
	}
	
	public Node<File> insert(File x){
		Node<File> z = new Node<File>(x);
		root = z;
		return z;
	}

	
	public void save(){
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("SuperTree.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this.root);
			out.close();
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void load(){
		try{
			FileInputStream fileIn = new FileInputStream("SuperTree.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			this.root = (Node<File>) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i){
			System.err.println(i);
		}catch(ClassNotFoundException c){
			System.out.println("Node class not found");
		}
	}
	
	
}