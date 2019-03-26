
package data_structures;

import java.util.Iterator;

/**
 *
 * @author mert
 */
public class Queue<E extends Comparable<E>> implements Iterable<E> {
    private UnorderedListADT<E> list;
    
    public Queue() {
        list = new LinkedList<>();
    }
    public void enqueue(E obj) {
        list.addLast(obj);
    }   
    public E dequeue() {
        return list.removeFirst();
    }   
    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public boolean isFull() {
        return list.isFull();
    }
    public E peek() {
        return list.get(1);
    }
    public boolean contains(E obj) {
        return list.contains(obj);
    }
    public void makeEmpty() {
        list.clear();
    } 
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            Iterator<E> l_it = list.iterator();
            @Override
            public boolean hasNext() {
                return l_it.hasNext();
            }

            @Override
            public E next() {
                return l_it.next();
            }

            @Override
            public void remove() {
                l_it.remove();
            }            
            
        };
        return it;
    }

}
