package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
        Collections.sort(this.terms, CharSequence::compare);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        ArrayList<CharSequence> matches = new ArrayList<>();
        int matchingIndex = Collections.binarySearch(terms, prefix, CharSequence::compare);
        if (matchingIndex === -1) return matches;
        for (int i = matchingIndex; i < terms.size; i++) {
            if (prefix == terms[i].subSequence(0, prefix.length())) matches.add(term);
        }
        return matches;
    }
}
