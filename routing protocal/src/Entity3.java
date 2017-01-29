public class Entity3 extends Entity
{     int[] d = new int[NetworkSimulator.NUMENTITIES];
    // Perform any necessary initialization in the constructor
    public Entity3()
    {

   	 for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++)
            {
           	 distanceTable[i][j]=999;           
            }
        }
   	 for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
       	 distanceTable[i][i]=NetworkSimulator.cost[3][i];
   	 
   	 minEntity();

	 System.out.println("Initilizing Table through Constructure");
   	System.out.println("Table 3 changed");
   	printDT();
   	 for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
    		Packet p = new Packet(3,i,d);
    		if(i!=3 && i!=1)
    		NetworkSimulator.toLayer2(p);
    	}
     
    }
    
    // Handle updates when a packet is received.  Students will need to call
    // NetworkSimulator.toLayer2() with new packets based upon what they
    // send to update.  Be careful to construct the source and destination of
    // the packet correctly.  Read the warning in NetworkSimulator.java for more
    // details.
    public void update(Packet p)
    {
    	int flag=0;
    	for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++){
    		if(distanceTable[i][p.getSource()]!= p.getMincost(i)+distanceTable[p.getSource()][p.getSource()]){
    			distanceTable[i][p.getSource()]= p.getMincost(i)+distanceTable[p.getSource()][p.getSource()];
    			if(distanceTable[i][p.getSource()]>999){
    				distanceTable[i][p.getSource()]=999;
    			}
    			flag=1;
    		}
    	}
    	if(flag==1){
    		minEntity();
    		System.out.println("Table 3 changed");
    		printDT();
    		for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
        		Packet p1 = new Packet(3,i,d);
        		if(i!=3 && i!=1)
        		NetworkSimulator.toLayer2(p1);
        	}
    	}
    	else{
    		System.out.println("Table 3 does not change");
    		printDT();
    	}
    }
    
    public void minEntity(){
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
     		int x=999;
     		for(int j=0;j<NetworkSimulator.NUMENTITIES;j++){
     			if(j==3){
     				continue;
     			}
         		if(distanceTable[i][j]<x){
         			x=distanceTable[i][j];
         		}
         	}
     		distanceTable[i][3]=x;
     	}
    	distanceTable[3][3]=0;
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++)
    		d[i]=distanceTable[i][3];
    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    	distanceTable[whichLink][whichLink]=newCost;
    	minEntity();
    	System.out.println("Table 3 changed");
    	printDT();
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
    		Packet p = new Packet(3,i,d);
    		if(i!=3 && i!=1)
    		NetworkSimulator.toLayer2(p);
    	}
    }
    
    
    public void printDT()
    {
        System.out.println("         via");
        System.out.println(" D3 |   0   2");
        System.out.println("----+--------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 3)
            {
                continue;
            }
            
            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j += 2)
            {
               
                if (distanceTable[i][j] < 10)
                {    
                    System.out.print("   ");
                }
                else if (distanceTable[i][j] < 100)
                {
                    System.out.print("  ");
                }
                else 
                {
                    System.out.print(" ");
                }
                
                System.out.print(distanceTable[i][j]);
            }
            System.out.println();
        }
    }
}