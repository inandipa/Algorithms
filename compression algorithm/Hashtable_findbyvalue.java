
/*

Name : IndraKiranReddy Nandipati
ID : 800937635
E-mailid : inandipa@uncc.edu

*/

import java.util.HashMap;
import java.util.Map;

public class Hashtable_findbyvalue { // this function receives string and returns key value of respective string.
	
	public int Table_check(HashMap<Integer, String> hmap, String s1){
		 int k= -1;
		for(Map.Entry<Integer,String> entry : hmap.entrySet()){
			if(entry.getValue().equals(s1)){
				 k = entry.getKey();
			}
		
	 }
		//System.out.print(s1+"    ");
		//System.out.println(k);
		return k;
		
	
	}

}

