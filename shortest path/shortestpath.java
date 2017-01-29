/*
Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class shortestpath {

	public String source;
	public String dest;
	ArrayList<Vertex> list = new ArrayList<Vertex>();  
	 private static Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
	 
	
	public shortestpath(String source, String dest, Map<String, Vertex> v) {
		this.source=source;
		this.dest= dest;
		vertexMap=v;
		
	}

	 private void printPath( Vertex dest )			//printing path from source to destination
	    {
	        if( dest.prev != null )
	        {
	            printPath( dest.prev );
	            System.out.print( " --> " );
	        }
	        System.out.print( dest.name );
	    }
	 
	void calculatepath(){
		for(Entry<String, Vertex> entry : vertexMap.entrySet()){
    		Vertex v= entry.getValue();
    		v.reset();
		}
		try{
		Vertex v= vertexMap.get(source);
		v.dist=0;
		}
		catch(NullPointerException e){
			System.out.println("Enter a Vaild Vertex");
		}
		
		for(Entry<String, Vertex> entry : vertexMap.entrySet()){
    		list.add(entry.getValue());
    	}
		Bulid_Min_Heap(list,list.size());
	
		while(!list.isEmpty()){
																						// Dijkstra's algorithm implemetation.
		Vertex v= Heap_Extract_Min(list,list.size());
		if(v!=null){
			for( Edge e : v.adj){
			
				if(e.status==1){
    			Vertex w = e.dest;
    			if(w.status==1){
    			if(w.dist>e.weight+v.dist){
    			
    			w.dist=e.weight+v.dist;
    			//System.out.print(w.name+" "+w.dist);
    			w.prev=v;
    			}
    			}
			}
			}
			Bulid_Min_Heap(list,list.size());
			
		}
	}
		try{
			Vertex w = vertexMap.get(dest);
			printPath(w);
			double f=Math.round(w.dist*100)/100.0;
			System.out.println("  "+f);
			
		}
		catch(NullPointerException e){
			System.out.println("Enter a Vaild destination Vertex");
		}
	}	


	public void Bulid_Min_Heap(ArrayList<Vertex> A, int ArraySize) {		//Build_Min_heap for creating binary heap.
		
		for( int i=(ArraySize/2)-1; i>=0;i--){
			
			Min_Heapify(A,i,ArraySize);
		}
	}

	public void Min_Heapify(ArrayList<Vertex> A, int i, int n) {
		
		int l=(2*i)+1;
		int r=l+1;
		int min = i;
		if(l<=n-1 ){
			
			if( A.get(l).dist<A.get(i).dist ){
			min=l;
			}
		}
		if(r<=n-1  ){
			if( A.get(r).dist<A.get(min).dist){
			min=r;
			}
		}
		if(min!=i){
			
			Vertex x = A.get(i);
			A.set(i, A.get(min));
			A.set(min, x);
			
			Min_Heapify(A,min,n);
		}
	}
	public Vertex Heap_Extract_Min(ArrayList<Vertex> A, int n){
		if(n<1){
			System.out.println("Que is empty");
		return null;
		}
		Vertex min=A.get(0);
		A.set(0, A.get(n-1));
		A.remove(n-1);
		return min;
	}
	
	
}
