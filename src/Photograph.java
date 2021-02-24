
/**
 * Huy Huynh
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Photograph implements Comparable<Photograph> {

    /**
     * Caption is a string that each photograph has
     */
    private String caption;

    /**
     * Filename is the name of the photograph
     */
    private String filename;

    /**
     * A String containing the date that the photograph was taken in the format "YYYY-MM-DD"
     */
    private String dateTaken;

    /**
     * An integer that is the rating of the photo from 0 to 5
     */
    private int rating;

    /**
     * Contains the image
     */
    protected BufferedImage imageData;

    /**
     * Contains the file where the image is from
     */
    private File imageFile;

    /**
     * This constructor creates a new photograph using caption and filename only
     * 
     * @param caption  The caption in the photograph
     * @param filename The file name of the photograph
     */
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        this.dateTaken = "1901-01-01";
        this.rating = 0;
        imageFile = new File(filename);
    }

    /**
     * A constructor that takes in a caption, filename, dateTaken, and rating to make a photograph. It makes a default date
     * for invalid dates and invaid ratings
     * 
     * @param caption   Caption for the photograph
     * @param filename  Filename for the photograph
     * @param dateTaken Date when the photograph was taken
     * @param rating    Rating of the photograph
     */
    public Photograph(String caption, String filename, String dateTaken, int rating) {
        this.caption = caption;
        this.filename = filename;
        if (dateTaken.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")) {
            this.dateTaken = dateTaken;
        } else {
            this.dateTaken = "1901-01-01";
        }
        if (0 <= rating && rating <= 5) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
        this.imageFile = new File(filename);
    }

    /**
     * Accessor method that returns the caption of the photo
     * 
     * @return The caption of the photograph
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Accessor method that returns the file name of the photo
     * 
     * @return The file name of the photograph
     */
    public String getFilename() {
        return filename;
    }

    /**
     * Accessor method that returns when the photo was taken
     * 
     * @return The date taken of the photo
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * Accessor method that returns the rating of the photo
     * 
     * @return The rating of the photo
     */
    public int getRating() {
        return rating;
    }

    /**
     * Mutator method that sets a caption for the photo
     * 
     * @param caption The caption that will be set for the photo
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * If rating is invalid, sets rating to 0, if it is valid, then set the rating to the given rating
     * 
     * @param rating The rating that will be set for the photo
     */
    public void setRating(int rating) {
        if (0 <= rating && rating <= 5) {
            this.rating = rating;
        } else {
            this.rating = 0;
        }
    }

    /**
     * Accessor method that returns the image data
     * 
     * @return the buffered image
     */
    public BufferedImage getImageData() {
        return imageData;
    }

    /**
     * Mutator method that sets the image data of a buffered image
     * 
     * @param imageData What to replace the buffered image with
     */
    public void setImageData(BufferedImage imageData) {
        this.imageData = imageData;
    }

    /**
     * Accessor method that returns the file of the photograph itself
     * 
     * @return file of the photograph
     */
    public File getImageFile() {
        return imageFile;
    }

    /**
     * Mutator method that changes the image file to the file that was entered
     * 
     * @param imageFile File that will replace the previous file
     */
    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * A toString method that returns the filename and caption
     */
    public String toString() {
        return "Caption: " + this.caption + ", Filename: " + this.filename;
    }

    /**
     * An equals method to check if two photographs are equal
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof Photograph) {
            Photograph z = (Photograph) o;
            if ((this.caption == (z.caption)) && (this.filename.equals(z.filename))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * A hashCode method that overrides the default hashCode
     */
    public int hashCode() {
        return this.filename.hashCode();
    }

    /**
     * A compare to method that puts dates in ascending order, and if dates are the same, orders from caption in
     * alphabetical order
     */
    public int compareTo(Photograph p) {
        if (this.dateTaken != p.dateTaken) {
            return (this.dateTaken.compareTo(p.dateTaken));
        } else if (this.caption != p.caption) {
            return this.caption.compareTo(p.caption);
        } else {
            return 0;
        }
    }

    /**
     * Loads the image data of the filename and changes the filename and the image data gotten from the file
     * (didn't really find optimal use for this method in the photoviewer class, used File instead)
     * 
     * @param filename File that will be accessed
     * @return true if successful, false if unsuccessful
     */
    public boolean loadImageData(String filename) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(filename));

        } catch (IOException e) {
            return false;
        }
        this.filename = filename;
        this.imageData = img;
        return true;
    }

}
