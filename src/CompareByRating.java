
/**
 * Huy Huynh
 */

import java.util.Comparator;

/**
 * A comparator method that checks the rating. If the rating is the same, checks the alphabetical and numerical order of
 * caption
 */
public class CompareByRating implements Comparator<Photograph> {
    public int compare(Photograph p1, Photograph p2) {
        if (p1.getRating() > p2.getRating()) {
            return -1;
        } else if (p1.getRating() < p2.getRating()) {
            return 1;
        }
        for (int i = 0; i < p1.getCaption().length(); i++) {
            if (p1.getCaption().charAt(i) < p2.getCaption().charAt(i)) {
                return -1;
            } else if (p1.getCaption().charAt(i) > p2.getCaption().charAt(i)) {
                return 1;
            }
        }
        return 0;
    }
}
