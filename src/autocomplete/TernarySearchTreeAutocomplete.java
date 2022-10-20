package autocomplete;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Ternary search tree (TST) implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TernarySearchTreeAutocomplete implements Autocomplete {
    /**
     * The overall root of the tree: the first character of the first autocompletion term added to this tree.
     */
    private Node overallRoot;

    /**
     * Constructs an empty instance.
     */
    public TernarySearchTreeAutocomplete() {
        overallRoot = null;
    }

    public void put(String key) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with null key");
        }
        overallRoot = put(overallRoot, key, 0);
    }

    private Node put(Node x, String key, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node<>(c);
        }
        if (c < x.data) x.left = put(x.left, key, d);
        else if (c > x.data) x.right = put(x.right, key, d);
        else if (d < key.length() - 1) x.mid = put(x.mid, key,d+1);
        else x.isTerm = true;
        return x;
    }

    public List<CharSequence> keysWithPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("calls keysWithPrefix() with null argument");
        }
        List<CharSequence> matches = new ArrayList<>();
        Node x = get(overallRoot, prefix, 0);
        if (x == null) return matches;
        if (x.isTerm) matches.add(prefix);
        collect(x.mid, new StringBuilder(prefix), matches);
        return matches;
    }



    // return subtrie corresponding to given key
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (key.length() == 0) throw new IllegalArgumentException("key must have length >= 1");
        char c = key.charAt(d);
        if (c < x.data) return get(x.left,  key, d);
        else if (c > x.data) return get(x.right, key, d);
        else if (d < key.length() - 1) return get(x.mid, key,d+1);
        else return x;
    }

    private void collect(Node x, StringBuilder prefix, List<CharSequence> matches) {
        if (x == null) return;
        collect(x.left, prefix, matches);
        if (x.isTerm) matches.add(prefix.toString() + x.data);
        collect(x.mid, prefix.append(x.data), matches);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, matches);
    }
    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        for (CharSequence term : terms) {
            put(String.valueOf(term));
        }
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        return keysWithPrefix(String.valueOf(prefix));
    }

    /**
     * A search tree node representing a single character in an autocompletion term.
     */
    private static class Node<V> {
        private final char data;
        private boolean isTerm;
        private Node<V> left;
        private Node<V> mid;
        private Node<V> right;

        public Node(char data) {
            this.data = data;
            this.isTerm = false;
            this.left = null;
            this.mid = null;
            this.right = null;
        }
    }
}
