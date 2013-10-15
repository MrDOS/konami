import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class ListGraphTest {
    @Test
    public void addVertexTest() throws Exception {
        ListGraph<String, Integer> l = new ListGraph<String, Integer>();
        assertTrue(l.addVertex("Test 1"));
        assertTrue(l.addVertex("Test 2"));
        assertFalse(l.addVertex("Test 1"));
        assertTrue(l.addVertex("Test 3"));
        assertFalse(l.addVertex("Test 2"));
    }

    @Test
    public void addEdgeTest() throws Exception {
        ListGraph<String, Double> l = new ListGraph<String, Double>();
        assertFalse(l.addEdge("I don't exist", "Me neither", 1.));
        l.addVertex("I exist");
        assertFalse(l.addEdge("I exist", "I still don't", 1.));
        l.addVertex("Me too");
        assertTrue(l.addEdge("I exist", "Me too", 1.));
        assertFalse(l.addEdge("I exist", "Me too", 1.));
        l.addVertex("One more");
        l.addVertex("still works");
        assertTrue(l.addEdge("One more", "still works", 1.));
        assertFalse(l.addEdge("I exist", "Me too", 1.));
    }

    @Test
    public void getEdgeCostTest() throws Exception {
        ListGraph<String, Integer> l = new ListGraph<String, Integer>();
        String v1 = "Vertex 1";
        String v2 = "Vertex 2";
        String v3 = "Vertex 3";
        Integer costV1V2 = 123;
        Integer costV2V3 = 4;

        assertEquals(null, l.getEdgeCost(v1, v2));
        l.addVertex(v1);
        l.addVertex(v2);
        l.addVertex(v3);
        l.addEdge(v1, v2, costV1V2);
        l.addEdge(v2, v3, costV2V3);
        assertEquals(costV1V2, l.getEdgeCost(v1, v2));
        assertEquals(costV2V3, l.getEdgeCost(v2, v3));
        assertNull(l.getEdgeCost(v1, v3));
        assertNull(l.getEdgeCost(v2, v1));
    }

    @Test
    public void BFSTest() throws Exception {
        ListGraph<String, Integer> l = new ListGraph<String, Integer>();
        Deque<String> ret;

        ret = l.BFS("Doesn't exist");
        assertEquals(0, ret.size());

        int size = smallGraph(l);
        ret = l.BFS("A");
        assertEquals(size, ret.size());

        String s = "";
        for (String p : ret)
            s = s + p;
        assertEquals(s, "ABDEGFHCI");
    }

    @Test
    public void DFSTest() throws Exception {
        ListGraph<String, Integer> l = new ListGraph<String, Integer>();
        Deque<String> ret;

        ret = l.DFS("Doesn't exist");
        assertEquals(0, ret.size());

        int size = smallGraph(l);
        ret = l.DFS("A");
        assertEquals(size, ret.size());

        String s = "";
        for (String p : ret)
            s = s + p;
        assertEquals(s, "ABEFCHIDG");
    }


    // shamelessly borrowed from my data structures text book
    private static int smallGraph(ListGraph<String, Integer> graph) {
        String[] vertices = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
        for (String v : vertices)
            graph.addVertex(v);
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "D", 1);
        graph.addEdge("A", "E", 1);
        graph.addEdge("B", "E", 1);
        graph.addEdge("C", "B", 1);
        graph.addEdge("D", "G", 1);
        graph.addEdge("E", "F", 1);
        graph.addEdge("E", "H", 1);
        graph.addEdge("F", "C", 1);
        graph.addEdge("F", "H", 1);
        graph.addEdge("G", "H", 1);
        graph.addEdge("H", "I", 1);
        graph.addEdge("I", "F", 1);
        return vertices.length;
    }

    @Test
    public void topologicalOrderTest() throws Exception {
        ListGraph<String, Integer> l = new ListGraph<String, Integer>();
        List<String> ret;

        ret = l.topologicalOrder();
        assertEquals(0, ret.size());

        int size = smallDAG(l);
        ret = l.topologicalOrder();
        assertEquals(size, ret.size());

        String s = "";
        for (String p : ret)
            s = s + p;
        assertTrue(s.startsWith("1"));
        assertTrue(s.endsWith("3") || s.endsWith("5")
            || s.endsWith("8") || s.endsWith("10"));

        ListGraph<String, Integer> i = new ListGraph<String, Integer>();
        size = wikiDAG(i);
        ret = i.topologicalOrder();
        assertEquals(size, ret.size());
        s = "";
        for (String p : ret)
            s = s + p;
        assertTrue(s.startsWith("3") || s.startsWith("5") || s.startsWith("7"));
        assertTrue(s.endsWith("2") || s.endsWith("9") || s.endsWith("10"));

        ListGraph<String, Integer> cycle = new ListGraph<String, Integer>();
        cycle.addVertex("1");
        cycle.addVertex("2");
        cycle.addVertex("3");
        cycle.addEdge("1", "2", 1);
        cycle.addEdge("2", "3", 1);
        cycle.addEdge("3", "1", 1);
        ret = cycle.topologicalOrder();
        assertNull(ret);
    }

    @Test
    public void dijkstraTest() throws Exception {
        ListGraph<Integer, Integer> l = new ListGraph<Integer, Integer>();
        class Helper implements DijkHelper<Integer> {
            public Integer zero() {
                return 0;
            }
            public Integer max() {
                return Integer.MAX_VALUE;
            }
            public Integer add(Integer a, Integer b) {
                return a + b;
            }
        }
        romaniaGraph(l);
        Map<Integer, Integer> minCosts = l.dijkstra(0, null, new Helper());
        assertEquals(374 + "", minCosts.get(3) + "");
        l.addVertex(1000); // not connected

        Map<Integer, List<Integer>> paths =
            new HashMap<Integer, List<Integer>>();
        minCosts = l.dijkstra(0, paths, new Helper());
        assertEquals(418 + "", minCosts.get(1) + "");
        assertEquals("[0, 15, 14, 13, 1]", paths.get(1).toString());
        assertEquals(Integer.MAX_VALUE + "", minCosts.get(1000) + "");
        assertNull(paths.get(1000));
        assertEquals(0 + "", minCosts.get(0) + "");
        assertEquals("[0]", paths.get(0).toString());
    }

    // shamelessly borrowed from my data structures text book
    private static int smallDAG(ListGraph<String, Integer> graph) {
        String[] vertices = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        for (String v : vertices)
            graph.addVertex(v);
        graph.addEdge("9", "10", 1);
        graph.addEdge("1", "2", 1);
        graph.addEdge("2", "3", 1);
        graph.addEdge("2", "5", 1);
        graph.addEdge("2", "4", 1);
        graph.addEdge("4", "6", 1);
        graph.addEdge("4", "7", 1);
        graph.addEdge("6", "8", 1);
        graph.addEdge("7", "8", 1);
        graph.addEdge("7", "9", 1);
        return vertices.length;
    }

    // shamelessly borrowed from wikipedia
    private static int wikiDAG(ListGraph<String, Integer> graph) {
        String[] vertices = {"2", "3", "5", "7", "8", "9", "10", "11"};
        for (String v : vertices)
            graph.addVertex(v);
        graph.addEdge("7", "8", 1);
        graph.addEdge("7", "11", 1);
        graph.addEdge("11", "2", 1);
        graph.addEdge("11", "9", 1);
        graph.addEdge("11", "10", 1);
        graph.addEdge("8", "9", 1);
        graph.addEdge("5", "11", 1);
        graph.addEdge("3", "8", 1);
        return vertices.length;
    }

    // shamelessly borrowed from my AI course
    private static void romaniaGraph(ListGraph<Integer, Integer> graph) {
        for (int i = 0; i < 19; i++)
            graph.addVertex(i);
        graph.addEdge(0, 15, 140);
        graph.addEdge(0, 16, 118);
        graph.addEdge(0, 19, 75);
        graph.addEdge(1, 5, 211);
        graph.addEdge(1, 6, 90);
        graph.addEdge(1, 13, 101);
        graph.addEdge(1, 17, 85);
        graph.addEdge(2, 3, 120);
        graph.addEdge(2, 13, 138);
        graph.addEdge(2, 14, 146);
        graph.addEdge(3, 2, 120);
        graph.addEdge(3, 10, 75);
        graph.addEdge(4, 7, 86);
        graph.addEdge(5, 1, 211);
        graph.addEdge(5, 15, 99);
        graph.addEdge(6, 1, 90);
        graph.addEdge(7, 4, 86);
        graph.addEdge(7, 17, 98);
        graph.addEdge(8, 11, 87);
        graph.addEdge(8, 18, 92);
        graph.addEdge(9, 10, 70);
        graph.addEdge(9, 16, 111);
        graph.addEdge(10, 3, 75);
        graph.addEdge(10, 9, 70);
        graph.addEdge(11, 8, 87);
        graph.addEdge(12, 15, 151);
        graph.addEdge(12, 19, 71);
        graph.addEdge(13, 1, 101);
        graph.addEdge(13, 2, 138);
        graph.addEdge(13, 14, 97);
        graph.addEdge(14, 2, 146);
        graph.addEdge(14, 13, 97);
        graph.addEdge(14, 15, 80);
        graph.addEdge(15, 0, 140);
        graph.addEdge(15, 5, 99);
        graph.addEdge(15, 12, 151);
        graph.addEdge(15, 14, 80);
        graph.addEdge(16, 0, 118);
        graph.addEdge(16, 9, 111);
        graph.addEdge(17, 1, 85);
        graph.addEdge(17, 7, 98);
        graph.addEdge(17, 18, 142);
        graph.addEdge(18, 8, 92);
        graph.addEdge(18, 17, 142);
        graph.addEdge(19, 0, 75);
        graph.addEdge(19, 12, 71);
    }
}
