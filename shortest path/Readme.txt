
Details:
Name - IndraKiranReddy Nandipati
ID - 800937635
Email-id - inandipa@uncc.edu

Programming :
programming is done in java language. java version is "1.8.0_73".


Executution steps :
change directory to the file that has these 5 source code and also place any TEXT file with format mentioned in this folder for compiling  - 
Dir/algos_project> javac Graph.java

Run the code using command  - 
Dir/algos_project> java Graph network.txt

*if textfile name is network.txt otherwise change name.
and Please try to Enter Queries without out spelling mistake. 


Files and Design Description:

Project has total 5 source code :-

Graph.java - It has main function and takes one argument as input file and read data from it. After develping Graph it will ask user to ENTER QUERY.
Queries should be given as per PDF as mentioned in project description.Functionality is implemnted as requested.
This class will calss shortestpath class when path query is implemented, and BFS class will be called when reachable query is implemented.

shortestpath.java - It will take two arguments (src, dest) to calculate shortest path and print the path between two given vertices. I have implemeted the prority Que using Binary Heap.
This has simple Dijkstra's algorithm implementation along with min_heapify functions. Build_Min_Heap is buid at sart of Dijkstra's algorithm with source distance set to zero.
Min_heapify is called at many places to arrange vertex as per the prority. Extract_min is used at start of Dijkstra's to get min dist vertex and Dijkstra's algorithm will end when Heap has no values left.

BFS.java - It is called when reachable query is implemented.It runs BFS Over each vertx and displays the reachable vertices from that node.

Vertex.java - This class is used to store all the data related to vertex. It has name,dist,previous vertex,adj Edge(array). initially while creating vetex dis is set to infinity and previous is set to null.

Edge.java -  This class is used to store all the data related to Edge. 	It has name, head and tail vertex and cost of link.


DataStructures used :
1. Two TreeMaps for storing Vetices and Edges in given graph(tree is used over hashmap. Because we need to print in alphabetical order. )
2. LinkedList for passing on Vertex array to Heapify for Heapsort.(As array size will be varying arraylist is best for this situation)
3. Build_Min_Heap , Min_heapify , Extract_Min are develped from scratches based on sudo-code from course.
4. LinkedList is used to stored adjacent Edges in Vertex class.


Comments :

Project is completed and running as Expected.
Priority Queue is implemented using Binary heap.
Reachable is implemented using BFS with running time of V(V+E). I tried hard to implement using different alogorithms, but running time is always O(V^2).
