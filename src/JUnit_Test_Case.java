
/**
 * Huy Huynh
 */

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;

public class JUnit_Test_Case {

    /**
     * Tests removePhoto() method, by creating photolibraries and implementing the method on them. It was successful.
     */
    @Test
    public void testRemovePhoto() {
        Photograph p1 = new Photograph("caption1", "filename1", "2000-10-05", 1);
        Photograph p2 = new Photograph("caption2", "filename2", "2000-10-10", 2);
        Photograph p3 = new Photograph("caption3", "filename3", "2000-10-15", 3);
        Photograph p4 = new Photograph("caption4", "filename4", "2000-12-20", 4);
        PhotoLibrary pL1 = new PhotoLibrary("name1", 100);
        pL1.addPhoto(p1);
        pL1.addPhoto(p2);
        PhotoLibrary pL2 = new PhotoLibrary("name2", 101);
        pL2.addPhoto(p3);
        pL2.addPhoto(p4);

        pL1.removePhoto(p1);
        pL2.removePhoto(p3);

        ArrayList<Photograph> expected1 = new ArrayList<Photograph>();
        expected1.add(p2);
        ArrayList<Photograph> expected2 = new ArrayList<Photograph>();
        expected2.add(p4);
        ArrayList<Photograph> actual1 = pL1.getPhotos();
        ArrayList<Photograph> actual2 = pL2.getPhotos();
        assertEquals("The result of the list is not the same", expected1, actual1);
        assertEquals("The result of the list is not the same", expected2, actual2);
    }

    /**
     * Tests compareto() method, by creating photos and implementing the method on them. It was successful.
     */
    @Test
    public void testCompareTo() {
        Photograph p1 = new Photograph("caption1", "filename1", "2000-10-05", 1);
        Photograph p2 = new Photograph("caption2", "filename2", "2000-10-10", 2);
        Photograph p3 = new Photograph("caption3", "filename3", "2000-10-15", 3);
        Photograph p4 = new Photograph("caption4", "filename4", "2000-12-20", 4);

        int actual1 = p1.compareTo(p2);
        int actual2 = p3.compareTo(p2);
        assertTrue(actual1 < 0);
        assertTrue(actual2 > 0);
    }

    /**
     * Tests comparator method, by creating photos and implementing the method on them. It was successful.
     */
    @Test
    public void testCompare() {
        Photograph p1 = new Photograph("caption1", "filename1", "2000-10-05", 1);
        Photograph p2 = new Photograph("caption2", "filename2", "2000-10-10", 4);
        Photograph p3 = new Photograph("caption3", "filename3", "2000-10-15", 3);
        Photograph p4 = new Photograph("caption4", "filename4", "2000-12-20", 2);

        ArrayList<Photograph> pl1 = new ArrayList<Photograph>();
        ArrayList<Photograph> pl2 = new ArrayList<Photograph>();
        pl1.add(p4);
        pl1.add(p3);
        pl1.add(p2);
        pl1.add(p1);
        pl2.add(p2);
        pl2.add(p1);
        pl2.add(p4);
        Collections.sort(pl1, new CompareByCaption());
        Collections.sort(pl2, new CompareByCaption());
        assertTrue(pl1.get(0) == p1);
        assertTrue(pl2.get(0) == p1);
        Collections.sort(pl1, new CompareByRating());
        Collections.sort(pl2, new CompareByRating());
        assertTrue(pl1.get(0) == p2);
        assertTrue(pl2.get(0) == p2);
    }
}
