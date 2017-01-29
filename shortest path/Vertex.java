/*
Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu
*/
import java.util.LinkedList;

public class Vertex {										// Vertex call to store vertex details.
	public String     name;   // Vertex name
    public LinkedList<Edge> adj;    // Adjacent vertices
    public Vertex     prev;   // Previous vertex on shortest path
    public float        dist;   // Distance of path
    public int		 status;
    public int 		reachable;
 
    public Vertex( String nm )
      { name = nm; adj = new LinkedList<Edge>( ); status =1; reset( ); }

    public void reset( )
      { dist = Graph.INFINITY; prev = null; reachable = 0;  }    
    

}
