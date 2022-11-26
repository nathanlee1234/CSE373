package autocomplete.cities;

import autocomplete.Autocomplete;
import autocomplete.BinarySearchAutocomplete;
import autocomplete.TreeSetAutocomplete;

/**
 * Tests for the {@link BinarySearchAutocomplete} class.
 *
 * @see BinarySearchAutocomplete
 */
public class TreeSetAutocompleteTests extends AutocompleteTests {
    @Override
    public Autocomplete createAutocomplete() {
        return new TreeSetAutocomplete();
    }
}

