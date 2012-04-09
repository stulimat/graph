package graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Graph 
{
  HashMap vertices;
  //K-V pair that holds a vertex and its depth. If not found, the 
  HashMap visitedVertexes = new HashMap();
  //K-V pair that holds a vertex and its parent.
  HashMap parentVertex = new HashMap();
  //Holds the finish time for the vertex and its parent.
  HashMap finishTimes = new HashMap(); 
  //Constructor for Graph class
  //Basic Constructor instantiates a null set
  public Graph()
  {
    this.vertices = new HashMap();
  }
  //Constructor passed a set of vertices
  public Graph(HashMap vertices)
  {
    this.vertices = vertices;
  }
  //Constructor passed a GraphVertex as the first vertex in the graph. Makes a one-node graph.
  public Graph(GraphVertex vertex)
  {
    this.vertices = new HashMap();
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
  public void dfs()
  {
    int time = 0;
    resetGraph();
    Set<Integer> keys = this.vertices.keySet();
    for(int name : keys)
    {
      if(this.visitedVertexes.containsKey(name))
      {
        dfsVisit((GraphVertex) this.vertices.get(name),time);
      }
    }
  }
  //Perform a depth-first-search visit to a node 
  public void dfsVisit(GraphVertex vertex,int time)
  {
    time++;
    //Put the value as "null" to mark as "gray". 
    makeGray(vertex);
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
  }
  //Checks to see if a node has been visited or not:
  public boolean hasVisited(int name)
  {
    return this.visitedVertexes.containsKey(name);
  }
  
  
}

