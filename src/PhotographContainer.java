
/**
 * Huy Huynh
 */

import java.util.*;

public abstract class PhotographContainer {
    /**
     * A String containing Photograph Container's name
     */
    protected String name;

    /**
     * An arraylist of photos in the container
     */
    protected ArrayList<Photograph> photos;

    /**
     * A constructor that creates a photo container using a name
     * 
     * @param name This is the name of the album
     */
    public PhotographContainer(String name) {
        this.name = name;
        this.photos = new ArrayList<Photograph>();
    }

    /**
     * A getter that returns the name of the photo container
     * 
     * @return name of the album
     */
    public String getName() {
        return this.name;
    }

    /**
     * A setter that sets a new name for the photo container
     * 
     * @param name Takes in a new name for the photo container
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A getter that gets the array list of photographs from the photo container
     * 
     * @return an array list set of photographs
     */
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    /**
     * Adds a photograph to the album if it isn't already in it and returns true if successful and false if unsuccessful
     * 
     * @param p Photograph to be added
     * @return true if adding the photograph was successful and false if not successful
     */
    public boolean addPhoto(Photograph p) {
        if (p != null && this.photos.contains(p) == false) {
            return photos.add(p);
        }
        return false;
    }

    /**
     * Checks if a photograph is already in an photo container
     * 
     * @param p The photograph to be checked
     * @return true if the photograph is already in the album and false if it is not in the photo container
     */
    public boolean hasPhoto(Photograph p) {
        return this.photos.contains(p);
    }

    /**
     * Removes a photograph from the album if it is in the photo container
     * 
     * @param p The photograph to be deleted
     * @return true if the removal was successful and false if it was not
     */
    public boolean removePhoto(Photograph p) {
        if (photos.contains(p)) {
            photos.remove(p);
            return true;
        }
        return false;
    }

    /**
     * Returns the number of photos in the photo container
     * 
     * @return the number of photos in the set of photographs
     */
    public int numPhotographs() {
        return photos.size();
    }

    /**
     * An equals method that checks if an album is the same as another photo container
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o instanceof PhotographContainer) {
            PhotographContainer a = (PhotographContainer) o;
            return ((this.name == a.name));
        } else {
            return false;
        }
    }

    /**
     * A to string method that returns album as a string
     */
    public String toString() {
        return this.name + "/n" + this.photos;
    }

    /**
     * Overrides the hashcode and makes a unique integer for an photo container
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Returns an array list of photographs that have the same rating or higher than the one given in the parameter
     * 
     * @param rating The rating that will determine which photographs will be returned
     * @return Returns the list of photographs that have at least a higher rating than the one given
     */
    public ArrayList<Photograph> getPhotos(int rating) {
        ArrayList<Photograph> listofPhotographs = new ArrayList<Photograph>();
        if (5 <= rating && rating <= 0) {
            return null;
        }
        for (Photograph p : this.photos) {
            if (p.getRating() >= rating) {
                listofPhotographs.add(p);
            }
        }
        return listofPhotographs;
    }

    /**
     * Returns a list of photos from a certain given year
     * 
     * @param year The year where all the returned photos will be from
     * @return The list of photographs that took place in a given year
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        ArrayList<Photograph> listofPhotographs = new ArrayList<Photograph>();
        if (!(String.valueOf(year).length() == 4)) {
            return null;
        }
        for (Photograph p : this.photos) {
            if (Integer.parseInt(p.getDateTaken().substring(0, 4)) == year) {
                listofPhotographs.add(p);
            }
        }
        return listofPhotographs;
    }

    /**
     * Returns a list of photographs that have the same month and year given
     * 
     * @param month The month when all the returned photos were taken
     * @param year  The year when all the returned photos were taken
     * @return List of photographs that were taken in the given month and year
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        ArrayList<Photograph> listofPhotographs = new ArrayList<Photograph>();
        if (year >= 0 && year <= 9999 && month <= 12 && month >= 0)
            for (Photograph photo : photos) {
                if ((Integer.parseInt(photo.getDateTaken().substring(0, 4)) == year)
                        && (Integer.parseInt(photo.getDateTaken().substring(5, 7)) == month)) {
                    listofPhotographs.add(photo);
                } else {
                }
            }
        else if (month < 1 || month > 12) {
            return null;
        }
        return listofPhotographs;
    }

    /**
     * Returns a list of photographs that is inbetween two given dates
     * 
     * @param beginDate Beginning date of the range
     * @param endDate   End date of the range
     * @return The list of photographs inbetween the range
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {
        ArrayList<Photograph> listofPhotographs = new ArrayList<Photograph>();
        if (!(beginDate.length() == 10)) {
            return null;
        }
        if (!(endDate.length() == 10)) {
            return null;
        }
        // makes integers for all the parts of dates
        String beginDateYear1 = beginDate.substring(0, 4);
        String beginDateMonth1 = beginDate.substring(5, 7);
        String beginDateDay1 = beginDate.substring(8);
        String endDateYear1 = endDate.substring(0, 4);
        String endDateMonth1 = endDate.substring(5, 7);
        String endDateDay1 = endDate.substring(8);
        int beginDateYear = Integer.parseInt(beginDateYear1);
        int beginDateMonth = Integer.parseInt(beginDateMonth1);
        int beginDateDay = Integer.parseInt(beginDateDay1);
        int endDateYear = Integer.parseInt(endDateYear1);
        int endDateMonth = Integer.parseInt(endDateMonth1);
        int endDateDay = Integer.parseInt(endDateDay1);

        // combines dates into one number
        int intBeginDate = Integer.parseInt(beginDate.replace("-", ""));
        int intEndDate = Integer.parseInt(endDate.replace("-", ""));

        if (((!(0 <= beginDateDay && beginDateDay <= 31)) && (beginDateDay1.length() == 2))
                || (!((0 <= beginDateMonth && beginDateMonth <= 12) && (beginDateMonth1.length() == 2)))
                || (!(beginDateYear1.length() == 4))) {
            return null;
        }
        if (((!(0 <= endDateDay && endDateDay <= 31)) && (endDateDay1.length() == 2))
                || (!((0 <= endDateMonth && endDateMonth <= 12) && (endDateMonth1.length() == 2)))
                || (!(endDateYear1.length() == 4))) {
            return null;
        }
        if (beginDateYear > endDateYear) {
            return null;
        }
        if ((beginDateYear == endDateYear) && (beginDateMonth > endDateMonth)) {
            return null;
        }
        if ((beginDateYear == endDateYear) && (beginDateMonth == endDateMonth) && (beginDateDay > endDateDay)) {
            return null;
        }
        for (Photograph p : this.photos) {
            int pIntDate = Integer.parseInt(p.getDateTaken().replace("-", ""));
            if (intBeginDate <= pIntDate && pIntDate <= intEndDate) {
                listofPhotographs.add(p);
            }
        }
        return listofPhotographs;
    }

}
