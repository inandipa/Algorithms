/*Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Graph {

	 public static final int INFINITY = Integer.MAX_VALUE;
	 private static Map<String,Vertex> vertexMap = new TreeMap<String,Vertex>( );
	 private static Map<String,Edge> EdgeMap = new TreeMap<String,Edge>( );

	public static void main(String[] args) throws FileNotFoundException {
		Graph g= new Graph();		
		
        FileReader fin = new FileReader( args[0] );
        Scanner graphFile = new Scanner( fin );						//reading specified txt file
        Scanner s= new Scanner(System.in);
        // Read the edges and insert
        String line;
        while( graphFile.hasNextLine( ) )
        {
            line = graphFile.nextLine( );
            StringTokenizer st = new StringTokenizer( line );		

            try
            {
                if( st.countTokens( ) !=  3 )					//checking the line is in correct format or not.
                {
                    System.err.println( "Skipping ill-formatted line " + line );
                    continue;
                }
                String source  = st.nextToken( );
                String dest    = st.nextToken( );
				float weight    =  Float.parseFloat(st.nextToken( ));
	
				 g.addEdge( source, dest, weight );						//calling addedge to create edge and respective vertices.
				 g.addEdge(  dest,source, weight );
            }
            catch( NumberFormatException e )
              { System.err.println( "Skipping ill-formatted line " + line ); }
         }
    	graphFile.close();
  
    	while(true){

        	System.out.println("Enter the query");			//Asking query to operate on Graph.
        	
        	String command = s.nextLine();
        	String[] split = command.split(" ");
        	
        	
    	if(split[0].equals("addedge")){
			try{											//read input from standard.in and call the related function.
    		float f = Float.parseFloat(split[3]);
    		g.addEdge(split[1], split[2], f);
    		}
    		catch(ArrayIndexOutOfBoundsException e){
    			System.out.println("enter distance");
    		}
			}
    	else if(split[0].equals("deleteedge")){
    		
    		g.deleteEdge(split[1], split[2]);
    	}
    	else if(split[0].equals("edgedown")){
    		
    		g.edgedown(split[1], split[2]);
    	}
    	else if(split[0].equals("edgeup")){
    		
    		g.edgeup(split[1], split[2]);
    	}
    	else if(split[0].equals("vertexdown")){
    		
    		g.vertexdown(split[1]);
    	}
    	else if(split[0].equals("vertexup")){
    		
    		g.vertexup(split[1]);
    	}
    	else if(split[0].equals("print")){
    		g.printGraph();
    	}
    	else if(split[0].equals("reachable")){
    		g.reachableGraph();
    	}
    	else if(split[0].equals("path")){
    		
    		shortestpath  sp= new shortestpath(split[1], split[2],vertexMap);
    		sp.calculatepath();
    	}
    	else if(split[0].equals("Quit")){
    		break;
    	}
    	else{
    		System.out.println("Please Enter Valid Query");
    	}
    	
    	}
    	s.close();	
    	}
	
	

	private void reachableGraph() {
	
		BFS d = new BFS(vertexMap);
		d.reachable();
				
	}

	private void vertexup(String ver) {
		Vertex v = vertexMap.get(ver);
		v.status= 1;
	}

	private void vertexdown(String ver) {
		Vertex v = vertexMap.get(ver);
		v.status= 0;
		
	}

	private void edgeup(String source, String dest) {
		Edge x = getEdge(source,dest);
		x.status=1;
	}

	private void edgedown(String source, String dest) {
		Edge x = getEdge(source,dest);
		x.status=0;
	}

	private void deleteEdge(String source, String dest) {
		 Vertex v = getVertex( source );
	         String s= source.concat("_").concat(dest);
			if(EdgeMap.get( s )!=null){
				v.adj.remove(EdgeMap.get( s ));
				EdgeMap.remove(s);
				
			}
			
		
	}
													//printing graph
	private void printGraph() {
		for(Entry<String, Vertex> entry : vertexMap.entrySet()){
			System.out.print(entry.getKey());
			if(entry.getValue().status==0)
				System.out.print("_Down\n");
			else
				System.out.print("\n");
			for(Entry<String, Edge> e : EdgeMap.entrySet()){
				if(e.getValue().src.name.equals(entry.getKey())){
	    			System.out.print("    "+e.getValue().dest.name);
	    			System.out.print("  "+ e.getValue().weight);
	    			if(e.getValue().status==0)
						System.out.print("_Down\n");
					else
						System.out.print("\n");
	    			
				}
			}
		}
				
    	}
  
	private void addEdge(String source, String dest, float weight) {
		 Vertex v = getVertex( source );
		 Edge e = getEdge(source, dest);
		 e.weight=weight;
	        v.adj.add( e );
			
	}

	private Edge getEdge(String source,String dest) {
		 String s= source.concat("_").concat(dest);
		 Vertex v = getVertex( source );
		 Vertex w = getVertex( dest );
		Edge x = EdgeMap.get( s );
	        if( x == null )
	        {
	        	x = new Edge(s,v,w);
	            EdgeMap.put( s, x );
	        }
	        
	        return x;
	}


	private Vertex getVertex(String source) {
		 Vertex v = vertexMap.get( source );
	        if( v == null )
	        {
	            v = new Vertex( source );
	            vertexMap.put( source, v );
	        }
	        return v;
	}
}
