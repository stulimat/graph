package graph;
import java.util.ArrayList;

public class GraphVertex
{
  //Identifying value of the graph.
  private int value;
  //List of unique(non-repeating) edges. 
  private  ArrayList<Integer> nearestNeighbors;
  //Multiple public constructors for different graph vertex initialization types.
  //Default constructor initializes empty vertex
  private boolean isVisited;
  //Sets the predecessor of the vertex that was visited to get here.
  private int predecessor;
  //The depth of the node in the search.
  private int depth;
  
  public GraphVertex()
  {
	  this.value = new Integer(null);
	  this.nearestNeighbors = new ArrayList<Integer>();
	  this.isVisited = false;
	  this.predecessor = new Integer(null);
  }
  //Constructor for which a value is provided, ArrayList<Integer>s up empty adjacency list.
  public GraphVertex(int value)
  {
	  this.value = value;
	  this.nearestNeighbors = new ArrayList<Integer>();
	  this.isVisited = false;
	  this.predecessor = new Integer(null);
  }
  //Constructor for which an adjacency list but no value is provided.
  public GraphVertex(ArrayList<Integer> list)
  {
	  this.value = new Integer(null);
	  this.nearestNeighbors = list;
	  this.isVisited = false;
	  this.predecessor = new Integer(null);
  }
  //Constructor for which an adjacency list and value are provided.
  public GraphVertex(int value, ArrayList<Integer> list)
  {
	  this.value = value;
	  this.nearestNeighbors = list;
	  this.isVisited = false;
	  this.predecessor = new Integer(null);
  }
  // Get a list of the nearest neighbors.
 
  public ArrayList<Integer> getNearestNeighbors()
  {
	  return this.nearestNeighbors;
  }
  
  // Add a nearest neighbor to this vertex If the neighbor exists in the set already, return false;
 
  public boolean addNeighbor(int neighbor)
  {
	return this.nearestNeighbors.contains(neighbor) ? false : this.nearestNeighbors.add(neighbor);
  }
  
  // Remove the neighbor from the list, effectively removing its connection to this node. 
  // Note that you will have to remove the corresponding links from the other nearest neighbors vertexes. 
  
  public void removeNeighbor(int neighbor)
  {
	this.nearestNeighbors.remove(neighbor);
  }
  
  //Set the value/name of the node.
  
  public void setValue(int value)
  {
	 this.value = value;
  }
  
  //Get the value/name of the node
  public int getValue()
  {
	  return this.value;
  }
  
  //Overridden equals method for determining equality.
  //Since each vertex should have a unique name, the name/value of the vertex is an identifying factor.
  public boolean equals(GraphVertex vertex)
  {
	  return this.value == vertex.getValue();
  }
  
  // Mark the node as visited.
  public void visit()
  {
    this.isVisited = true;
  }
  
  // Un-visit the node.
  public void unvisit()
  {
    this.isVisited = false;
  }
  
  // Check to see if the node is visited or not.
  public boolean isVisted()
  {
    return this.isVisited;
  }
  
  //Set the predecessor
  public void setPredecessor(int predecessor)
  {
    this.predecessor = predecessor;
  }
  
  //Clear the predecessor of this node. 
  public void clearPredecessor()
  {
    this.predecessor = new Integer(null);
  }
  
  //Return the predecessor of this node.
  public int getPredecessor()
  {
    return this.predecessor;
  }
  
  public int getDepth()
  {
    return this.depth;
  }
  
  public void setDepth(int depth)
  {
    this.depth = depth;
  }
}
