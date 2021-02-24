
/**
 * Huy Huynh
 */

import java.util.*;

/**
 * A comparator method that compares photographs by captions in alphabetical order first and then by rating in
 * descending order
 */
public class CompareByCaption implements Comparator<Photograph> {
    public int compare(Photograph p1, Photograph p2) {
        if (p1.getCaption() != p2.getCaption()) {
            return p1.getCaption().compareTo(p2.getCaption());
        }
        if (p1.getRating() != p2.getRating()) {
            return p2.getRating() - p1.getRating();
        } else {
            return 0;
        }
    }
}
