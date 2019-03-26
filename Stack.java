
package data_structures;

import java.util.Iterator;

/**
 *
 * @author mert
 * @param <E>
 */
public class Stack<E extends Comparable<E>> implements Iterable<E> {
    private UnorderedListADT<E> list;
    public Stack() {
        list = new LinkedList<>();
    }                      
    public void push(E obj) {
        list.addFirst(obj);
    }
    public E pop() {
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

    @Override
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
