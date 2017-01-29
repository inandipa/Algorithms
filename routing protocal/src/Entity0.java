public class Entity0 extends Entity
{     int[] d = new int[NetworkSimulator.NUMENTITIES];
    // Perform any necessary initialization in the constructor
    public Entity0()
    {
    	 for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
         {
             for (int j = 0; j < NetworkSimulator.NUMENTITIES; j++)
             {
            	 distanceTable[i][j]=999;           
             }
         }
    	 for (int i = 0; i < NetworkSimulator.NUMENTITIES; i++)
        	 distanceTable[i][i]=NetworkSimulator.cost[0][i];
    	
    	 minEntity();

    	 System.out.println("Initilizing Table through Constructure");
    	 System.out.println("Table 0 changed");
    	 printDT();
    	 
    	 for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
    		 Packet p = new Packet(0,i,d);
     		if(i!=0)
     		NetworkSimulator.toLayer2(p);
     	}
      
    }
    
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
    		System.out.println("Table 0 changed");
    		printDT();
    		for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
        		Packet p1 = new Packet(0,i,d);
        		if(i!=0)
        		NetworkSimulator.toLayer2(p1);
        	}
    	}
    	else{
    		System.out.println("Table 0 does not change");
    		printDT();
    	}
    }
    
    public void minEntity(){
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
     		int x=999;
     		for(int j=0;j<NetworkSimulator.NUMENTITIES;j++){
     			if(j==0){
     				continue;
     			}
         		if(distanceTable[i][j]<x){
         			x=distanceTable[i][j];
         		}
         	}
     		distanceTable[i][0]=x;
     	}
    	distanceTable[0][0]=0;
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++)
    		d[i]=distanceTable[i][0];
    }
    
    public void linkCostChangeHandler(int whichLink, int newCost)
    {
    	distanceTable[whichLink][whichLink]=newCost;
    	minEntity();
    	System.out.println("Table 0 changed");
    	printDT();
    	for(int i=0;i<NetworkSimulator.NUMENTITIES;i++){
    		Packet p = new Packet(0,i,d);
    		if(i!=0)
    		NetworkSimulator.toLayer2(p);
    	}
    }
    
    public void printDT()
    {
        System.out.println();
        System.out.println("           via");
        System.out.println(" D0 |   1   2   3");
        System.out.println("----+------------");
        for (int i = 1; i < NetworkSimulator.NUMENTITIES; i++)
        {
            System.out.print("   " + i + "|");
            for (int j = 1; j < NetworkSimulator.NUMENTITIES; j++)
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