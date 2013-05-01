package brian.linkedlist;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;

public class HWTest {
	 public static void main( String [ ] args ) throws NodeNotFoundException
	    {
		 SuperLinkedLists<Integer> lst = new SuperLinkedLists<Integer>();
		 
		int c = 0;
		for(int i = 0; i<1000; i ++){
			 c = (int) (Math.random()*30);
			 lst.add(i, c);
	    }
		

		lst.printOut();
		
	    }
}
