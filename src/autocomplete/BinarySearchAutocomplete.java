package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        this.terms.sort(CharSequence::compare);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> matches = new ArrayList<>();
        int loc = Collections.binarySearch(this.terms, prefix, CharSequence::compare);
        if (loc < 0) loc = Math.abs(loc);
        for (int i = loc; i < this.terms.size(); i++) {
            if (Autocomplete.isPrefixOf(prefix, this.terms.get(i))) {
                matches.add(this.terms.get(i));
            } else {
                return matches;
            }
        }
        return matches;
    }
}
