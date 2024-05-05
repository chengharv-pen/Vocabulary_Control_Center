package mainpackage;

import java.util.Comparator;

/**
 * 
 * This class implements the Comparator interface with type String and
 * overrides the compare method.
 * 
 */
public class SortLetterWords implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
	
}
