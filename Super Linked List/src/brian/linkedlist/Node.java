package brian.linkedlist;

public class Node<AnyType> {

	public AnyType data;
	public Node<AnyType> prev;
	public Node<AnyType> next;
	public int counter;
	
	public Node(AnyType d, Node<AnyType> p, Node<AnyType> n, int c){
		
		
		counter = c;
		data = d; 
		prev = p; 
		next = n;
		}
	
}
