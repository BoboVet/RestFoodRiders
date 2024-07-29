package appfolder;

import java.util.ArrayList;
import java.util.List;

// The Tags enum represents different categories/tags that can be associated with a dish.
enum Tags{
    ITALIAN, VEGETARIAN, INDIAN, DUTCH, VEGAN, MEXICAN, INDONESIAN, AMERICAN, MIDLLE_EASTERN, ASIAN, SPANISH;

    // Retrieves a list of all tag values as strings.
    public static List<String> getValues() {
        List<String> tagList = new ArrayList<>(); // Initialize a new list to store tag values.
        for (Tags tag : Tags.values()) {
            // Iterate over all values of the Tags enum and add the name of each tag to the list.
            tagList.add(tag.name());
        }
        return tagList;
    }
}

