package lab04;

import java.util.ArrayList;

public class lab04 {
	
	public String[] getArray() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("Hello");
		String[] list = array.toArray(new String[array.size()]);
		return list;
	}

}
