import java.util.*;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTest {
    @Test
    public void trieTest() throws Exception {
        Trie t = new Trie();
        assertFalse(t.get("H", true));
        assertFalse(t.get("H", false));
        t.put("Hi there");
        assertFalse(t.get("H", true));
        assertTrue(t.get("H", false));
        t.put("Hi");
        assertTrue(t.get("Hi", true));
        assertTrue(t.get("Hi", false));
        assertFalse(t.get("i", true));
        assertFalse(t.get("i", false));
    }
}
