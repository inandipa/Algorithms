/*
Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu
*/

public class Edge {				// Edge calss for storing Edge datalis
	String name;
	Vertex src;
	Vertex dest;
	float weight;
	int status;
	 public Edge( String nm , Vertex source, Vertex destination )
     {	name = nm;   
     	status = 1; 
     	src = source;
     	dest = destination;
     	reset();
     }
	 
	 public void reset( )
     { weight = Graph.INFINITY;}    
   


}
