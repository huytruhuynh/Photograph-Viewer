import java.util.ArrayList;

/**
 * Huy Huynh
 */

public class Album extends PhotographContainer {

    /**
     * A constructor that creates an album using a name
     * 
     * @param name This is the name of the album
     */
    public Album(String name) {
        super(name);
        this.name = name;
        this.photos = new ArrayList<Photograph>();
    }

}
