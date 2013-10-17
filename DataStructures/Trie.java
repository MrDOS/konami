import java.util.*;

/**
 * A prefix trie which allows insertion of words and querying of word
 * existance (or prefix existance).
 */
public class Trie
{
    private static class TrieNode
    {
        private String v;
        private Map<Character, TrieNode> children =
            new HashMap<Character, TrieNode>();
        private boolean word;

        public TrieNode(String aV) {
            v = aV;
        }
    }
    private TrieNode root = new TrieNode("");

    /**
     * Add a word to the Trie.
     * @param word the word to add
     */
    public void put(String word) {
        char[] wChars = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < wChars.length; i++) {
            if (!cur.children.containsKey(wChars[i]))
                cur.children.put(wChars[i], new TrieNode(cur.v + wChars[i]));
            cur = cur.children.get(wChars[i]);
        }
        cur.word = true;
    }

    /**
     * Does the search string exist in the Trie?
     * @param search the search string
     * @param exact whether we match based on whole string or just the prefix
     * @return whether the search string exists in the Trie
     */
    public boolean get(String search, boolean exact) {
        TrieNode cur = root;
        char[] sChars = search.toCharArray();
        for (int i = 0; i < sChars.length && cur != null; i++)
            cur = cur.children.get(sChars[i]);
        return cur != null && (!exact || cur.word);
    }
}
