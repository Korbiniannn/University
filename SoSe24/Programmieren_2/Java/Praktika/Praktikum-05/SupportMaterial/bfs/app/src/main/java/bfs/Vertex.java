package bfs;


import java.util.Iterator;
import java.util.LinkedList;

public class Vertex implements Iterable<Vertex> {

  private int value;

  private Vertex left;

  private Vertex right;

  public Vertex(int value, Vertex left, Vertex right){
    this.value = value;
    this.left = left;
    this.right = right;
  }

  public int getValue(){
    return value;
  }

  public Vertex getLeft(){
    return left;
  }

  public Vertex getRight(){
    return right;
  }

  @Override
  public Iterator<Vertex> iterator(){
    return new VertexIterator(new LinkedList<Vertex>(), this);
  }

}
