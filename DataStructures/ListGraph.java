import java.util.*;

/**
 * Directed, weighted graph.  Object oriented, adjacency list version.
 * T = vertex type, P = precision type
 * i.e. String, Double
 */
public class ListGraph<T, P extends Number & Comparable<? super P>> {
    class Vertex {
        private T label;
        private List<Edge> edges = new ArrayList<Edge>();

        public Vertex(T aLabel) {
            label = aLabel;
        }
    }

    class Edge {
        private Vertex target;
        private P weight;

        public Edge(Vertex aTarget, P aWeight) {
            target = aTarget;
            weight = aWeight;
        }
    }

    private Map<T, Vertex> vertices = new HashMap<T, Vertex>();

    /**
     * Adds a vertex to the graph.
     * @return true if added, false if not
     */
    public boolean addVertex(T vertex) {
        if (vertices.get(vertex) != null)
            return false;
        vertices.put(vertex, new Vertex(vertex));
        return true;
    }

    /**
     * Adds an edge from start to end to the graph.
     * @return true if edge added,
     * false if edge already exists (or start or end null
     */
    public boolean addEdge(T start, T end, P weight) {
        Vertex sV = vertices.get(start);
        Vertex eV = vertices.get(end);
        if (sV == null || eV == null || getEdgeCost(start, end) != null)
            return false;
        sV.edges.add(new Edge(eV, weight));
        return true;
    }

    /**
     * @return the cost of the edge between start and end
     * or null if an edge doesn't exist (or start or end null)
     */
    public P getEdgeCost(T start, T end) {
        Vertex sV = vertices.get(start);
        Vertex eV = vertices.get(end);
        if (sV == null || eV == null)
            return null;
        for (Edge e : sV.edges)
            if (e.target == eV)
                return e.weight;
        return null;
    }



    /* Algorithms */
    /**
     * Performs a breadth-first traversal starting at a given origin.
     * @return a queue of vertices in the traversal, including the origin
     * at the front of the queue
     */
    public Deque<T> BFS(T origin) {
        Deque<T> order = new LinkedList<T>();
        Deque<Vertex> queue = new LinkedList<Vertex>();
        Set<Vertex> visited = new HashSet<Vertex>();

        Vertex oV = vertices.get(origin);
        if (oV == null)
            return order;
        order.addFirst(oV.label);
        queue.addFirst(oV);
        visited.add(oV);

        while (queue.size() > 0) {
            Vertex cur = queue.pollFirst();
            List<Edge> cE = cur.edges;
            for (Edge e : cE) {
                Vertex n = e.target;
                if (!visited.contains(n)) {
                    visited.add(n);
                    order.add(n.label);
                    queue.add(n);
                }
            }
        }

        return order;
    }

    /**
     * Performs a depth-first traversal starting at a given origin.
     * @return a queue of vertices in the traversal, including the origin
     * at the front of the queue
     */
    public Deque<T> DFS(T origin) {
        Deque<T> order = new LinkedList<T>();
        Map<Vertex, Integer> vStatus = new HashMap<Vertex, Integer>();
        Map<Edge, Integer> eStatus = new HashMap<Edge, Integer>();
        Vertex oV = vertices.get(origin);
        if (oV != null)
            dfsHelper(oV, order, vStatus, eStatus);
        return order;
    }

    private void dfsHelper(Vertex v, Deque<T> order,
        Map<Vertex, Integer> vStatus, Map<Edge, Integer> eStatus) {
        final int DISC = 1;
        final int BACK = 2;
        final int EXPL = 3;
        Integer vS = vStatus.get(v);
        if (vS == null || vS != DISC)
            order.addLast(v.label);
        vStatus.put(v, DISC);

        for (Edge e : v.edges) {
            if (eStatus.get(e) == null) // unexplored {
                Vertex w = e.target;
                Integer wS = vStatus.get(w);
                if (wS == null || wS != EXPL) // unexplored {
                    eStatus.put(e, DISC);
                    dfsHelper(w, order, vStatus, eStatus);
                }
                else
                    eStatus.put(e, BACK);
            }
        }

        vStatus.put(v, EXPL);
    }

    /**
     * Performs a topological sort of the vertices without cycles.
     * @return a list of vertices in topological order or null if cycle found
     * Modified from code found at:
     * http://www.keithschwarz.com/interesting/code/
     *                             topological-sort/TopologicalSort.java.html
     */
    public List<T> topologicalOrder() {
        ListGraph<T, P> reverse = new ListGraph<T, P>();
        for (T t : vertices.keySet())
            reverse.addVertex(t);
        for (Vertex v : vertices.values())
            for (Edge e : v.edges)
                reverse.addEdge(e.target.label, v.label, e.weight);

        List<T> result = new ArrayList<T>();
        Set<Vertex> visited = new HashSet<Vertex>();
        Set<Vertex> expanded = new HashSet<Vertex>();

        for (Vertex v : reverse.vertices.values()) {
            try {
                topologicalOrderHelper(v, reverse, result, visited, expanded);
            }
            catch (IllegalArgumentException e) {
                return null;
            }
        }

        return result;
    }

    private void topologicalOrderHelper(Vertex v,
        ListGraph<T, P> graph, List<T> result,
        Set<Vertex> visited, Set<Vertex> expanded) {
        if (visited.contains(v)) {
            if (expanded.contains(v))
                return;
            throw new IllegalArgumentException("Cycle");
        }
        visited.add(v);
        for (Edge e : v.edges)
            topologicalOrderHelper(e.target, graph, result, visited, expanded);
        result.add(v.label);
        expanded.add(v);
    }
 
    /**
     * Computes the shortest paths to all other vertices from an origin vertex.
     * Note: edge weights must be non-negative
     * @param paths if required, this should be a map which will be filled in
     * with each endpoint -> the list of vertices taken to reach it from the
     * origin or null if no path exists -- pass null if this is not required
     * @param helper should be a little helper class which provides
     * zero and the maximum value of the generic P and adds two Ps together.
     * @return a map of each end point vertex with the minimum total cost of
     * the path to reach it
     */
    public Map<T, P> dijkstra(T origin, Map<T, List<T>> paths,
        final DijkHelper<P> helper) {
        class dijkNode implements Comparable<dijkNode> {
            Vertex v;
            P minCost = helper.max();
            Vertex prev;
            public dijkNode(Vertex aV) {
                v = aV;
            }
            public int compareTo(dijkNode o) {
                return minCost.compareTo(o.minCost);
            }
        }

        Vertex oV = vertices.get(origin);
        if (oV == null)
            return null;

        PriorityQueue<dijkNode> queue = new PriorityQueue<dijkNode>();
        Map<Vertex, dijkNode> nMap = new HashMap<Vertex, dijkNode>();
        Map<T, P> ret = new HashMap<T, P>();
        ret.put(origin, helper.zero());
        for (Vertex t : vertices.values())
            nMap.put(t, new dijkNode(t));
        dijkNode start = nMap.get(oV);
        start.minCost = helper.zero();
        queue.add(start);

        while (queue.size() > 0) {
            dijkNode u = queue.poll();
            for (Edge e : u.v.edges) {
                dijkNode t = nMap.get(e.target);
                P weightViaU = helper.add(u.minCost, e.weight);
                if (weightViaU.compareTo(t.minCost) < 0) {
                    queue.remove(t);
                    t.minCost = weightViaU;
                    t.prev = u.v;
                    queue.add(t);
                }
            }
        }

        for (Vertex target : vertices.values()) {
            ret.put(target.label, nMap.get(target).minCost);
            if (paths != null) {
                LinkedList<T> p = new LinkedList<T>();
                for (Vertex v = target; v != null; v = nMap.get(v).prev)
                    p.addFirst(v.label);
                paths.put(target.label, p.get(0) == origin ? p : null);
            }
        }

        return ret;
    }
}

/* Dijkstra Helper Interface */
interface DijkHelper<P> {
    public P zero();
    public P max();
    public P add(P a, P b);
}
