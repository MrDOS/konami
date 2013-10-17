import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UnionFindTest {
    @Test
    public void allInOneTest() throws Exception {
        UnionFind<Integer> uf = new UnionFind<Integer>();
        
        assertFalse(uf.connected(1, 2));
        assertNull(uf.find(1));
        
        assertTrue(uf.add(1));
        uf.union(1, 2);
        assertNotNull(uf.find(1));
        assertNotNull(uf.find(2));
        assertEquals(uf.find(1), uf.find(2));
        assertTrue(uf.connected(1, 2));

        uf.add(3);
        assertFalse(uf.connected(1, 3));
        assertFalse(uf.connected(2, 3));

        uf.union(2, 3);
        assertTrue(uf.connected(1, 3));
        assertTrue(uf.connected(2, 3));
        assertTrue(uf.connected(1, 2));
    }
}
