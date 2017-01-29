
/*

Name : IndraKiranReddy Nandipati
ID : 800937635
E-mailid : inandipa@uncc.edu

*/

import java.io.*;
import java.util.HashMap;


public class LZW_decoding {

	public static void main(String[] args) throws IOException {

		String file_name = args[0];    //filename taken from input is stored in file_name.
		file_name = System.getProperty("user.dir")+"/"+file_name;// Filename taken from input is made more specific by adding path.
		short N = Short.parseShort(args[1]);  // integer taken from input to decide the table size.
		int MAX_TABLE_SIZE=(int) Math.pow(2,N); // maximum table size is set to 2^N.
		int Table_size = 0;       //initial index is set to zero for table.
		String sub_name = file_name.substring(0,file_name.indexOf('.'));
		PrintWriter out = new PrintWriter(sub_name+"_decoded.txt");       // set name for output file.
		
	
		File file = new File(file_name);
		BufferedReader reader = new BufferedReader(
	                        new InputStreamReader(new FileInputStream(file), "UTF-16BE"));

											//read from file in UTF-16BE format.
	
	HashMap<Integer,String> Hmap = new HashMap<Integer,String>();
	for(;Table_size<256;Table_size++){
		char c = (char)Table_size;
		String str = String.valueOf(c);      // update table with 0 to 255 characters to hashmap.
		//System.out.println(str);
		Hmap.put(Table_size, str);
	}
	
	String str="";
	String New_string;
	int r;
	while ((r = reader.read()) != -1) { // read every integer from lzw file
		
		System.out.println(r);
		if(r<Table_size){         // if the code is present in the table get string into New_string.
			
			New_string = Hmap.get(r);		
			}	
									//if not present concat string with its first character in string to New_string. 
		else{				
			New_string = str.concat(str.substring(0, 1));	
		}
		out.print(New_string);  		// print New_string to file.

									//update hashmap.
		if(Table_size<MAX_TABLE_SIZE && !str.equals("")){
			Hmap.put(Table_size, str.concat(New_string.substring(0,1)));
			Table_size++;
		}
		str= New_string;		
	
	}
	reader.close();				// close all opened files.
	out.close();
	}

}
