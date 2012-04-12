package graph;

import java.util.ArrayList;
import java.util.List;

public class GraphTester
{
  static List<GraphVertex> traversalOrder;

  static ArrayList<Integer> adjacencyList;
  public static void main(String[] args)
  {
   Graph someGraph;
   traversalOrder = new ArrayList<GraphVertex>();
   adjacencyList = new ArrayList<Integer>();
   someGraph = makeGraph();
   dfsGraph(someGraph);
   someGraph.resetGraph();
   bfsGraph(someGraph);
  }

  private static Graph makeGraph()
  {
    
    adjacencyList.add(2);
    adjacencyList.add(3);
    //Wrap the adjacency list in a new arraylist or the hashmap gets finicky. 
    GraphVertex vertex = new GraphVertex(1,new ArrayList(adjacencyList));
    Graph theGraph = new Graph(vertex);
    adjacencyList.clear();
    adjacencyList.add(4);
    vertex = new GraphVertex(2,new ArrayList(adjacencyList));
    theGraph.add(vertex);
    adjacencyList.clear();
    vertex = new GraphVertex(3,new ArrayList(adjacencyList));
    theGraph.add(vertex);
    adjacencyList.clear();
    
    vertex = new GraphVertex(4,new ArrayList(adjacencyList));
    theGraph.add(vertex);
    return theGraph;
  }
  private static void dfsGraph(Graph theGraph)
  {
    traversalOrder.clear();
    System.out.println("Beginning dfs");
    traversalOrder = theGraph.dfs(new GraphVertex(1));
    System.out.println(theGraph.toString());
    System.out.println("Should traverse in order 1,2,4,3:");
    for(GraphVertex v : traversalOrder)
    {
      System.out.println("Depth First SearchTraversed Node" + Integer.toString(v.getValue()));
    }
    theGraph.resetGraph();
  }
  private static void bfsGraph(Graph theGraph)
  {
    System.out.println("Beginning bfs");
    traversalOrder.clear();
    traversalOrder = theGraph.bfs(new GraphVertex(1));
    System.out.println(theGraph.toString());
    System.out.println("Should traverse in order 1,2,3,4:");
    for(GraphVertex v : traversalOrder)
    {
      System.out.println("Breadth First SearchTraversed Node" + Integer.toString(v.getValue()));
    }
    theGraph.resetGraph();
  }
}
