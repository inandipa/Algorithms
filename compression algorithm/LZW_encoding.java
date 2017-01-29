
/*

Name : IndraKiranReddy Nandipati
ID : 800937635
E-mailid : inandipa@uncc.edu

*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class LZW_encoding {

	public static void main(String[] args) throws IOException {

	
	
		String file_name = args[0];   //filename taken from input is stored in file_name.
		file_name = System.getProperty("user.dir")+"/"+file_name;// Filename taken from input is made more specific by adding path.
		int N = Integer.parseInt(args[1]); // integer taken from input to decide the table size.
		int MAX_TABLE_SIZE=(int) Math.pow(2,N); // maximum table size is set to 2^N.
		int Table_size = 0;  //initial index is set to zero for table. 
		
		
		String sub_name = file_name.substring(0,file_name.indexOf('.'));  // set name for output file.
		FileOutputStream fos = new FileOutputStream(sub_name+".lzw");   
	

		Hashtable_findbyvalue h = new Hashtable_findbyvalue();  //declaring class which is used to find Key for given value.
		
		HashMap<Integer,String> Hmap = new HashMap<Integer,String>(); // initializing HMAP for storing key(index) and Value(String) in HashMap.
		
		for(;Table_size<256;Table_size++){    // for loop for storing 0 to 255 ASCII chars to Hmap.
			char c = (char)Table_size;
			String str = String.valueOf(c);
			Hmap.put(Table_size, str);
		}
		
		String str="";                         // Initialize string to null. 
		
		FileInputStream fileInput = new FileInputStream(file_name);  // read from from input txt file.
		int r;
		while ((r = fileInput.read()) != -1) {         // for every single character present in file.
		   char c = (char) r;
		   String s = String.valueOf(c);              //converting char to string.
		   
		   String s_concated= str.concat(s);           // concat the character to previous string.
		
		 int k = h.Table_check( Hmap, s_concated );      // check weather the concated string is present in our hmap using Table_check class.
		 
		if(k==-1){   // if not present

			
			if(Table_size<MAX_TABLE_SIZE){    //add concated string to our hmap.
				  s_concated= str.concat(s);
				   
				Hmap.put(Table_size, s_concated);
				//System.out.println(Table_size+"  :  "+"'"+Hmap.get(Table_size)+"'");
				//System.out.println("-----------------------------------------------");
				Table_size++;
				
			}
			
			
			System.out.println("output   :   "+"("+str+")"+"    :   "+h.Table_check( Hmap, str ));
			int r1 = h.Table_check( Hmap, str );
			   String s2 = String.valueOf((char) r1);     // and convert the String before adding character to UTF-16BE formate and save to lzw file. 
			byte[] utf16 = s2.getBytes("UTF-16BE");
			fos.write(utf16);

			str = s;
		}
		else
			str= str.concat(s);   //if the concated string is present in hmap just upadate string to concated string.
		
			}
		System.out.println("output   :   "+"("+str+")"+"    :   "+h.Table_check( Hmap, str ));
		
		int r1 = h.Table_check( Hmap, str );   // After reading all characters from file just print remaining string to lzw file
		//System.out.println(r1);
		   String s2 = String.valueOf((char) r1);
		byte[] utf16 = s2.getBytes("UTF-16BE");
		fos.write(utf16);
			fos.close();     // close all opened files.
		fileInput.close();

	}
}
