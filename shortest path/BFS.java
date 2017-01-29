/*
Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public class BFS {
	int k=0;
	private static Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
	 public BFS(Map<String, Vertex> v ){
		vertexMap = v;		
	}
	 
	 public void reachable(){
		 for(Entry<String, Vertex> entry : vertexMap.entrySet()){
			if(entry.getValue().status==1){									//checking weather vertex is up or down and running BFS on ALL UP Vertices
			 Vertex v= entry.getValue();
			 for(Entry<String, Vertex> en : vertexMap.entrySet())
	    		en.getValue().reset();
	    		 Queue<Vertex> q = new LinkedList<Vertex>( );				// Queue for storing vertices
	    	        q.add( v ); v.reachable =1;
	    	
	    	        while( !q.isEmpty( ) )								// simple BFS
	    	        {
	    	            Vertex x = q.remove( );

	    	            for( Edge e : x.adj )
	    	            {	
	    	                if( e.dest.reachable==0 && e.status==1)
	    	                {
	    	                	e.dest.reachable=1;
	    	                    q.add( e.dest);
	    	                }
	    	            }
	    	        } 	
	    	        
	    	        
	    	        System.out.println(v.name);							// printing OUTPUT
	    	        for(Entry<String, Vertex> en : vertexMap.entrySet()){
	    	        	if(en.getValue().reachable == 1 && en.getValue()!=v)
	    	        		System.out.println("   "+en.getValue().name);
	    	        }
	    	        
	    	        
	    	        
	    	        
	    	        
		 }
	 }
	 }
		
}
