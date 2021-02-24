
/**
 * Huy Huynh
 */

import java.util.*;

public class PhotoLibrary extends PhotographContainer {
    /**
     * An integer containing the PhotoLibrary's unique id. This will stay constant
     */
    private final int id;

    /**
     * A hash set of albums within the photo library
     */
    private HashSet<Album> albums;

    /**
     * A constructor that creates a photo library using a name, id, and photos
     * 
     * @param name   The name of the photo library
     * @param id     The unique ID of the photo library
     * @param photos The list of photos in the photo library
     */
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
        this.photos = new ArrayList<Photograph>();
        this.albums = new HashSet<Album>();
    }

    /**
     * Accessor method that returns the id of the photo library
     * 
     * @return The ID
     */
    public int getId() {
        return id;
    }

    /**
     * Accessor method that returns the list of albums in the photo library
     * 
     * @return
     */
    public HashSet<Album> getAlbums() {
        return albums;
    }

    /**
     * Deletes a photo from a photo library if it is inside the photo library and from albums containing that photo
     * 
     * @param p Photo that will be attempted to be erased
     * @return true if the photo was removed, false if removal was unsuccessful
     */
    public boolean removePhoto(Photograph p) {
        boolean removedFromLibrary = false;
        boolean removedFromAlbum = false;
        if (this.photos.contains(p)) {
            this.photos.remove(p);
            removedFromLibrary = true;
        }
        for (Album a : albums) {
            if (a.hasPhoto(p)) {
                a.removePhoto(p);
                removedFromAlbum = true;
            }
        }
        if (removedFromLibrary || removedFromAlbum) {
            return true;
        }
        return false;
    }

    /**
     * Equals method to check if a photo library is equal to another photo library
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof PhotoLibrary) {
            PhotoLibrary p = (PhotoLibrary) o;
            return (this.id == p.id);
        } else {
            return false;
        }
    }

    /**
     * A toString method that returns the name, ID, list of photos in the photo library, and list of album names in the
     * albums collection
     */
    public String toString() {
        return "Name: " + this.name + ", ID: " + this.id + ", Photos: " + this.photos + ", Albums: " + this.albums;
    }

    /**
     * Returns a list of common photos between two photo libraries
     * 
     * @param a The first photo library that will be compared
     * @param b The second photo library that will be compared
     * @return An array list of the common photos between both
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {
        ArrayList<Photograph> common = new ArrayList<Photograph>();
        for (Photograph photo : a.photos) {
            for (Photograph photo1 : b.photos) {
                if ((photo.equals(photo1))) {
                    common.add(photo);
                }
            }
        }
        return common;
    }

    /**
     * Checks how similar two photo libraries are
     * 
     * @param a The first photo library that will be compared
     * @param b The second photo library that will be compared
     * @return A decimal number between 0 and 1, based on how similar the two photo libraries are
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        if ((a.photos.size() == 0) || (b.photos.size() == 0)) {
            return 0.0;
        }
        double cP = (commonPhotos(a, b)).size();
        double aDouble = a.photos.size();
        double bDouble = b.photos.size();
        if (aDouble > bDouble) {
            return cP / bDouble;
        }
        if (aDouble < bDouble) {
            return cP / aDouble;
        } else {
            return cP / aDouble;
        }

    }

    /**
     * Creates an album if an album with the name isn't already in the album list
     * 
     * @param albumName Name of the album
     * @return true if adding was successful and false if not
     */
    public boolean createAlbum(String albumName) {
        Album select = new Album(albumName);
        for (Album i : albums) {
            if (i.getName() == albumName) {
                return false;
            }
        }
        albums.add(select);
        return true;
    }

    /**
     * Removes an album from the album list if it exists in the list
     * 
     * @param albumName Name of the album to be removed
     * @return true if remove was successful and false if unsuccessful
     */
    public boolean removeAlbum(String albumName) {
        for (Album a : this.albums) {
            if (a.getName() == albumName) {
                albums.remove(a);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a photograph to an album in the set of albums if photo library's had the photo and that album didn't
     * 
     * @param p         Photograph to be added
     * @param albumName Name of album for photograph to be added in
     * @return true if adding was successful, false if it was not
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        if (photos.contains(p)) {
            for (Album a : albums) {
                if (a.getName() == albumName) {
                    if (!(a.hasPhoto(p))) {
                        a.photos.add(p);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes a photograph from an album in the albums set if it is in it
     * 
     * @param p         Photograph to be removed
     * @param albumName Name of the album where the photograph will be removed
     * @return true if removal was successful, false if removal wasn't successful
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        for (Album a : albums) {
            if (a.getName() == albumName) {
                if (a.hasPhoto(p)) {
                    a.removePhoto(p);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * If given an album name, returns the album that contains that name
     * 
     * @param albumName Name of album to be found
     * @return The album if it is found within the list of albums and returns null if not found
     */
    private Album getAlbumByName(String albumName) {
        for (Album a : albums) {
            if (a.getName() == albumName) {
                return a;
            }
        }
        return null;
    }

}
