package graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Queue;

public class Graph 
{
  private HashMap<Integer,GraphVertex> vertices;
  //K-V pair that holds a vertex and its depth. If not found, the 
  private HashMap<Integer,Integer> visitedVertexes = new HashMap<Integer,Integer>();
  //K-V pair that holds a vertex and its parent.
  private HashMap<Integer,Integer> parentVertex = new HashMap<Integer, Integer>();
  //Holds the finish time for the vertex and its parent.
  private HashMap<Integer,Integer> finishTimes = new HashMap<Integer,Integer>();
  private List<GraphVertex> visitedOrderList = new ArrayList<GraphVertex>();
  //Constructor for Graph class
  //Basic Constructor instantiates a null set
  public Graph()
  {
    this.vertices = new HashMap<Integer,GraphVertex>();
  }
  //Constructor passed a set of vertices
  public Graph(HashMap<Integer,GraphVertex> vertices)
  {
    this.vertices = vertices;
  }
  //Constructor passed a GraphVertex as the first vertex in the graph. Makes a one-node graph.
  public Graph(GraphVertex vertex)
  {
    this.vertices = new HashMap<Integer,GraphVertex>();
    this.vertices.put(vertex.getValue(),vertex);
  }
  public void add(GraphVertex vertex)
  {
    this.vertices.put(vertex.getValue(),vertex);
  }
  
  //Assuming an undirected graph, each of the vertices listed should have this vertex
  //listed as one of its nearest neighbors. Removing the vertex we must remove any of its 
  //edges connecting to other graphs to maintain graph integrity. 
  public void remove(GraphVertex vertex)
  {
	if(this.vertices.containsKey(vertex.getValue()))
	{
      ArrayList<Integer> nearestNeighbors = vertex.getNearestNeighbors();
      for(int neighbor : nearestNeighbors)
      {
        ((GraphVertex) this.vertices.get(neighbor)).removeNeighbor(neighbor);
      }
    }  
  }
  
  //Execute a depth-first search and print out the nodes in DFS order.
  public List<GraphVertex> dfs(GraphVertex g)
  {
    int time = 0;
    resetGraph();
    if(!this.visitedVertexes.containsKey(g.getValue()))
    {
      dfsVisit(this.vertices.get(g.getValue()),time);
    }
    return this.visitedOrderList;
  }
  //Perform a depth-first-search visit to a node 
  public void dfsVisit(GraphVertex vertex,int time)
  {
    time++;
    //Put the value as "null" to mark as "gray". 
    makeGray(vertex);
    //Add to the list of visited vertexes
    this.visitedOrderList.add(vertex);
    for(int vertexID : vertex.getNearestNeighbors())
    {
      if(!hasVisited(vertexID))
      {
        this.parentVertex.put(vertexID,time);
        dfsVisit((GraphVertex) this.vertices.get(vertexID),time);
      }
    }
    this.visitedVertexes.put(vertex.getValue(), time);
    time++;
    this.finishTimes.put(vertex.getValue(), time);
  }
  //"Mark" a node as grey-in process visited. 
  public void makeGray(GraphVertex vertex)
  {
    this.visitedVertexes.put(vertex.getValue(), null);
  }
  //"Mark" a node as black-it has been visited.
  public void makeBlack(GraphVertex vertex,int depth)
  {
    this.visitedVertexes.put(vertex.getValue(), depth);
  }
  //Reset the graph to initial conditions.
  public void resetGraph()
  {
      this.finishTimes.clear();
      this.parentVertex.clear();
      this.visitedVertexes.clear();
      this.visitedOrderList.clear();
  }
  //Checks to see if a node has been visited or not:
  public boolean hasVisited(int name)
  {
    return this.visitedVertexes.containsKey(name);
  }
  public String toString()
  {
    String output = new String();
    for(GraphVertex v : this.vertices.values())
    {
      output += v.getValue() + "{" + v.getNearestNeighbors().toString() + "}";
    }
    return output;
  }
  
  public List<GraphVertex> bfs(GraphVertex g)
  {
    resetGraph();
    int time = 0;
    g = this.vertices.get(g.getValue());
   
    makeGray(g);
    Queue<GraphVertex> q = new LinkedList<GraphVertex>();
    q.add(g);
    this.visitedVertexes.put(g.getValue(), time);
    while(!q.isEmpty())
    {
      GraphVertex u = q.remove();
      this.visitedOrderList.add(u);
      for(int vertexID : u.getNearestNeighbors())
      {
        if(!hasVisited(vertexID))
        {
          makeGray(this.vertices.get(vertexID));
          this.visitedVertexes.put(vertexID, time + 1);
          this.parentVertex.put(vertexID, u.getValue());
          q.add(this.vertices.get(vertexID));
        }
      }
      this.finishTimes.put(u.getValue(), time);
      
    }
    return this.visitedOrderList;
  }
}

