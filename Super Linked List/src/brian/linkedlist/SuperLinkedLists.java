package brian.linkedlist;

import java.util.ArrayList;
import java.util.Iterator;

public class SuperLinkedLists<AnyType> implements Iterable<AnyType> {

	public class Node<AnyType> {

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
		public int counter;
		
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n, int c){

			
			counter =c;
			data = d; 
			prev = p; 
			next = n;
			}
		
	}
	
	public SuperLinkedLists(){
		doClear();
	}
	
	public void clear(){
		doClear();
	}
	
	private void doClear(){
		beginMarker = new Node<AnyType>(null,null,null, 1);
		endMarker = new Node<AnyType>(null, beginMarker, null,1);
		beginMarker.next = endMarker;
		
		theSize = 0;
		theTotal = 0;
		modCount++;
	}
	
	public int total(){
		return theTotal;
	}
	
	public int size(){
		return theSize;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public boolean add(AnyType x){
		add(size(),x);
		return true;
	}
	
	public void add(int idx, AnyType x){
		addBefore(getNode(idx,0,size()),x);
	}
	
	public AnyType get(int idx){
		return getNode(idx).data;
	}

	public AnyType set(int idx, AnyType newVal){
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}

	private AnyType remove( Node<AnyType> d ) throws NodeNotFoundException{
		Node<AnyType> p;
		p = beginMarker;
		boolean removed = false;
		for(int i = 0; i< size(); i++){
			if(d.data==p.data){
				if(d.counter == 1){
					  removeEntire(p);
				      removed = true;
				}
				else{
					d.counter--;
					removed = true;
				}
			}
		}
		if(!removed)
			throw new NodeNotFoundException();
		else{
			theSize--;
			modCount++;
		}
		return p.data;
	}
	
	private Node<AnyType> removeEntire( Node<AnyType> p){
		  p.next.prev = p.prev;
	      p.prev.next = p.next;
	      theSize--;
	      theTotal--;
		
		return p;
		
	}
	
	public AnyType remove(int idx) throws NodeNotFoundException{
		return remove(getNode(idx));
	}
	
	private void addBefore(Node<AnyType> p, AnyType x){
		boolean added = false;
		for(int i = 0; i< total(); i++){
			Node<AnyType> d = getNode(i);
			if(x == d.data){
				d.counter++;
				added = true;
			}
		}
		if(!added){
			Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p, 0);
			newNode.prev.next = newNode;
			p.prev = newNode;
			theTotal++;
			modCount++;
			added =false;
		}
		theSize++;
	}
	
	private void addBefore(Node<AnyType> p, AnyType x, int c){
		boolean added = false;
		for(int i = 0; i< total(); i++){
			Node<AnyType> d = getNode(i);
			if(x == d.data){
				d.counter++;
				added = true;
			}
		}
		if(!added){
			Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p, c);
			newNode.prev.next = newNode;
			p.prev = newNode;
			theTotal++;
			modCount++;
			added =false;
		}
		theSize++;
	}
	
	
	private Node<AnyType> getNode(int idx){
		return getNode(idx, 0, size()-1);
	}
	
	private Node<AnyType> getNode(int idx, int lower, int upper){
		Node<AnyType> p;
		
		if(idx < lower || idx > upper){
			System.out.println(idx);
			throw new IndexOutOfBoundsException();
		}
		if(idx < size()/2){
			p = beginMarker.next;
			for(int i = 0; i < idx; i++)
				p=p.next;
		}else{
			p=endMarker;
			for(int i = size(); i > idx; i--)
				p=p.prev;
		}
		
		return p;
	}
	
	public void sort(){
		int i = 0;
		
		Node<AnyType> p = beginMarker;
		
		while(i<= total()){
				if(p.counter >p.next.counter){
					Node<AnyType> temp = p;
					p.prev.next = p.next;
					p.next.prev = p.prev;
					proper(temp);
				}
			}
			i++;
		}
	
	
	public void proper(Node<AnyType> n){
		Node<AnyType> p = beginMarker;
		boolean inserted = false;
		while(p.next != null && !inserted){
			if(n.counter > p.counter){
				addBefore(p, n.data, n.counter);
				
			}
		}
	}
	
	// prints out the list in order
	public void printOut(){
		Node<AnyType> p;
		p = beginMarker.next;
		for(int i = 0; i<total(); i++){
			System.out.println(p.data +" "+(double) p.counter*100/(double)size() + "%");
			p=p.next;
		}
	}

	@Override
	public Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}
	
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( !okToRemove )
                throw new IllegalStateException( );
                
            try {
				SuperLinkedLists.this.remove( current.prev );
			} catch (NodeNotFoundException e) {

				e.printStackTrace();
			}
            okToRemove = false;       
        }
    }


	private int theSize;
	private int theTotal;
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;

}


