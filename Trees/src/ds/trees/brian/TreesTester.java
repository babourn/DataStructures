package ds.trees.brian;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class TreesTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean exit = true;
		SuperTree<File> t = new SuperTree<File>();
		String home = System.getProperty("user.home") + ">";
		while(exit){
			Scanner in = new Scanner(System.in);
			System.out.print(System.getProperty("user.home") + ">");
			String cmd = in.next();
			if(cmd.equals("create")){
				System.out.print(home +"directory?");
				String dir = in.next();
				Path p = Paths.get(home + "\\" + dir);
				File f = p.toFile();
				t.insert(f);
			}else if(cmd.equals("print")){
				if(t.root == null){
					System.out.println("Null Tree");
				}else{
					t.printTree();
				}
			}else if(cmd.equals("contains")){
				if(t.root == null){
					System.out.println("Null Tree");
				}else{
					System.out.println("Path to file to match");
					String pathName = in.next();
					Path p = Paths.get(pathName);
					File f = p.toFile();
					t.contains(f);
				}
			}else if(cmd.equals("findMax")){
				if(t.root == null){
					System.out.println("Null Tree");
				}else{
					System.out.println(t.findMax().toString());
				}
			}else if(cmd.equals("findMin")){
				if(t.root == null){
					System.out.println("Null Tree");
				}else{
					System.out.println(t.findMin().toString());
				}
			}else if(cmd.equals("save")){
				t.save();
			}else if(cmd.equals("load")){
				t.load();
			}else if(cmd.equals("exit")){
				exit = false;
			}else{
				System.out.println("not a valid command");
			}
		}
	}

}
