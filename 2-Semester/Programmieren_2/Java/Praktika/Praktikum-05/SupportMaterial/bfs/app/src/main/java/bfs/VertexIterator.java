package bfs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class VertexIterator implements Iterator<Vertex> {
    
    private LinkedList<Vertex> queue;
    
    public VertexIterator(LinkedList<Vertex> queue, Vertex wurzel){
        this.queue = queue;
        queue.add(wurzel);
    }

    @Override
    public boolean hasNext(){
        return !queue.isEmpty();
    }

    @Override
    public Vertex next(){
        if(!hasNext())
            throw new NoSuchElementException("End reached!");
        Vertex nextElem = queue.getFirst();
        if(nextElem.getLeft() != null)
            queue.addLast(nextElem.getLeft());
        if(nextElem.getRight() != null)
            queue.addLast(nextElem.getRight());
        queue.removeFirst();
        return nextElem;
    }
}
