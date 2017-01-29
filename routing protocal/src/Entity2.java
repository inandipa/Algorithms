public class Entity2 extends Entity
{     int[] d = new int[NetworkSimulator.NUMENTITIES];
    // Perform any necessary initialization in the constructor
    public Entity2()
    {

   	 for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++)
            {
           	 distanceTable[i][j]=999;           
            }
        }
   	 for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
       	 distanceTable[i][i]=NetworkSimulator.cost[2][i];
   	int[] d = new int[NetworkSimulator.NUMENTITIES];
   	 minEntity();
	 System.out.println("Initilizing Table through Constructure");
 	System.out.println("Table 2 changed");
   	printDT();
   	 for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
    		Packet p = new Packet(2,i,d);
    		if(i!=2)
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
    		System.out.println("Table 2 changed");
    		printDT();
    		for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
        		Packet p1 = new Packet(2,i,d);
        	if(i!=2)
        		NetworkSimulator.toLayer2(p1);
        	}
    	}
    	else{
    		System.out.println("Table 2 does not change");
    		printDT();
    	}
    }
    
    public void minEntity(){
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
     		int x=999;
     		for(int j=0;j<NetworkSimulator.NUMENTITIES;j++){
     			if(j==2){
     				continue;
     			}
         		if(distanceTable[i][j]<x){
         			x=distanceTable[i][j];
         		}
         	}
     		distanceTable[i][2]=x;
     	}
    	distanceTable[2][2]=0;
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++)
    		d[i]=distanceTable[i][2];
    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    	distanceTable[whichLink][whichLink]=newCost;
    	minEntity();
    	System.out.println("Table 2 changed");
    	printDT();
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
    		Packet p = new Packet(2,i,d);
    		if(i!=2)
    		NetworkSimulator.toLayer2(p);
    	}
    }
    
    
    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D2 |   0   1   3");
        System.out.println("----+------------");
        for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        {
            if (i == 2)
            {
                continue;
            }
            
            System.out.print("   " + i + "|");
            for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++)
            {
                if (j == 2)
                {
                    continue;
                }
                
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