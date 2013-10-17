import java.util.*;

/**
 * Also known as disjoint sets; they allow two fast operations:
 * Union between two elements and find.
 * Based on code found at:
 * http://www.keithschwarz.com/interesting/code/union-find/UnionFind.java.html
 */
public class UnionFind<T>
{
    private static class Link<T>
    {
        private T parent;
        private int rank = 0;
        public Link(T aParent) {
            parent = aParent;
        }
    }
    private Map<T, Link<T>> map = new HashMap<T, Link<T>>();

    /**
     * Add a new element to the set.
     * @param element the element to add
     * @return whether the operation was successful
     */
    public boolean add(T element) {
        if (element == null || map.containsKey(element)) return false;
        map.put(element, new Link<T>(element));
        return true;
    }

    /**
     * Union two elements (and their sets) together.
     * @param i an element
     * @param j another element
     */
    public void union(T i, T j) {
        if (i == null || j == null) return;
        add(i);
        add(j);
        Link<T> iL = map.get(find(i));
        Link<T> jL = map.get(find(j));
        if (iL == jL) return;
        if (iL.rank > jL.rank) jL.parent = iL.parent;
        else if (iL.rank < jL.rank) iL.parent = jL.parent;
        else
        {
            jL.parent = iL.parent;
            iL.rank++;
        }
    }

    /**
     * Are two elements connected?  i.e. are they in the same set?
     * @param i an element
     * @param j another element
     * @return whether they are connected
     */
    public boolean connected(T i, T j) {
        T rI = find(i);
        T rJ = find(j);
        return rI != null && rJ != null && rI == rJ;
    }

    /**
     * Find the representative element for a given element.
     * @param element the element to find
     * @return the representative element or null if element not found
     */
    public T find(T element) {
        if (!map.containsKey(element)) return null;
        return findHelper(element);
    }

    /**
     * Find the representative element for a given element.
     * (Compress paths along the way.)
     */
    private T findHelper(T element) {
        Link<T> cur = map.get(element);
        if (cur.parent == element) return element;
        cur.parent = findHelper(cur.parent);
        return cur.parent;
    }
}
