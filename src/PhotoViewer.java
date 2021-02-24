
/**
 * Huy Huynh, hth5cf Sources: Websites:
 * https://www.w3schools.com/html/html_formatting.asp
 * https://docs.oracle.com/javase/7/docs/api/java/awt/event/MouseListener.html
 * https://docs.oracle.com/javase/7/docs/api/java/awt/Image.html
 * https://stackoverflow.com/questions/11069807/jpanel-doesnt-update-until-resize-jframe
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html
 * https://developer.mozilla.org/en-US/docs/Web/HTML/Element/br
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/ButtonGroup.html
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JFrame.html
 */
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhotoViewer extends JFrame {

    /**
     * A photograph container which contains the files
     */
    private PhotographContainer imageLibrary;

    /**
     * JLABELS
     */
    
    /**
     * JLabel for first photograph
     */
    private JLabel p1;

    /**
     * JLabel for second photograph
     */
    private JLabel p2;

    /**
     * JLabel for third photograph
     */
    private JLabel p3;

    /**
     * JLabel for fourth photograph
     */
    private JLabel p4;

    /**
     * JLabel for fifth photograph
     */
    private JLabel p5;

    /**
     * JLabel for the big image that will be displayed
     */
    private JLabel displayImage;

    /**
     * JLabel for the caption next to the actual ratings
     */
    private JLabel ratingLabel;

    /**
     * JPANELS
     */
    
    /**
     * JPanel that will hold the small thumbnails
     */
    private JPanel thumbnails;

    /**
     * JPanel that will hold the buttons
     */
    private JPanel buttonsPanel;

    /**
     * JPanel that will hold the display image
     */
    private JPanel displayImagePanel;

    /**
     * JPanel that will hold the sort buttons
     */
    private JPanel sortPanel;

    /**
     * JPanel to hold the rating
     */
    private JPanel rating;
    
    /**
     * JBUTTONS
     */
    
    /**
     * JButton to show the previous image
     */
    private JButton previous;

    /**
     * JButton to show the next image
     */
    private JButton next;

    /**
     * JButton to sort the images by their caption
     */
    private JButton sortByCaption;

    /**
     * JButton to sort the images by their date
     */
    private JButton sortByDate;

    /**
     * JButton to sort the images by their rating
     */
    private JButton sortByRating;

    /**
     * JRADIOBUTTONS
     */
    
    /**
     * JRadioButton to set rating to 1
     */
    private JRadioButton r1;

    /**
     * JRadioButton to set rating to 2
     */
    private JRadioButton r2;

    /**
     * JRadioButton to set rating to 3
     */
    private JRadioButton r3;

    /**
     * JRadioButton to set rating to 4
     */
    private JRadioButton r4;

    /**
     * JRadioButton to set rating to 5
     */
    private JRadioButton r5;

    /**
     * A button group that holds all the radio buttons
     */
    private ButtonGroup currentRating;

    /**
     * Index number for the display image
     */
    int displayImageIndex = 0;

    /**
     * Creates an instance of the current class and displays it
     */
    private void createAndShowGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Photo Viewer");
        this.setSize(400, 120);
        // setLocationRelativeTo(null); removing this will actually make the photo viewer launch in the center, I don't know why
        addComponentsToPane(this.getContentPane());
        pack();
        setVisible(true);
    }

    /**
     * Helper method that is used alongside setIcon, makes everything scale smooth. Only takes width because height will be
     * -1
     * 
     * @param bi    Buffered image that will be resized
     * @param width Size that will be rescaled
     * @return
     */
    public ImageIcon updateImagesSetIcon(BufferedImage bi, int width) {
        return new ImageIcon((bi.getScaledInstance(width, -1, Image.SCALE_SMOOTH)));
    }

    /**
     * Helper method which resets icons for photographs
     */
    public void updateImages() {
        try {
            p1.setIcon(updateImagesSetIcon(ImageIO.read(this.imageLibrary.photos.get(0).getImageFile()), 200));
            p2.setIcon(updateImagesSetIcon(ImageIO.read(this.imageLibrary.photos.get(1).getImageFile()), 200));
            p3.setIcon(updateImagesSetIcon(ImageIO.read(this.imageLibrary.photos.get(2).getImageFile()), 200));
            p4.setIcon(updateImagesSetIcon(ImageIO.read(this.imageLibrary.photos.get(3).getImageFile()), 200));
            p5.setIcon(updateImagesSetIcon(ImageIO.read(this.imageLibrary.photos.get(4).getImageFile()), 200));

        } catch (IOException e) {
            p1.setText("Images unable to be read");
            p2.setText("Images unable to be read");
            p3.setText("Images unable to be read");
            p4.setText("Images unable to be read");
            p5.setText("Images unable to be read");
        }
    }

    /**
     * Helper method that returns the captions to update caption text
     * 
     * @param index Index of the photo that the caption needs to get
     * @return the text that will be applied for each image
     */
    public String updateCaptionsSetText(int index) {
        return "<html><center>" + this.imageLibrary.photos.get(index).getCaption() + "<br>" + "Date: "
                + this.imageLibrary.photos.get(index).getDateTaken() + "<br>" + "Rating: "
                + this.imageLibrary.photos.get(index).getRating() + "</html></center>";
    }

    /**
     * Helper method that updates caption of the displayed image
     */
    public void updateDisplayedImageCaption() {
        displayImage.setText(updateCaptionsSetText(displayImageIndex));
    }

    /**
     * Helper method that updates the captions in thumbnail images
     */
    public void updateCaptions() {
        p1.setText(updateCaptionsSetText(0));
        p2.setText(updateCaptionsSetText(1));
        p3.setText(updateCaptionsSetText(2));
        p4.setText(updateCaptionsSetText(3));
        p5.setText(updateCaptionsSetText(4));
    }

    /**
     * Helper method that changes display image to what you input
     * 
     * @param index Index of photo
     */
    public void changeMainImage(int index) {
        try {
            displayImage.setIcon(updateImagesSetIcon(ImageIO.read(this.imageLibrary.photos.get(index).getImageFile()), 700));
            updateDisplayedImageCaption();
        } catch (IOException e) {
            System.out.println("Display image could not be read");
        }
    }

    /**
     * Helper method that changes the display image (this one also takes in a JLabel)
     * 
     * @param index Index of photo
     * @param p     JLabel of the thumbnail (p1, p2,...)
     */
    public void changeImage(int index, JLabel p) {
        try {
            displayImageIndex = index;
            displayImage.setIcon(updateImagesSetIcon(ImageIO.read(imageLibrary.photos.get(index).getImageFile()), 700));
            updateDisplayedImageCaption();
        } catch (IOException e) {
            p.setText("Image" + index + 1 + "could not be read");
        }
    }

    /**
     * Helper method that sets the rating of a photo
     * 
     * @param rating Rating to be changed to
     */
    public void changeRating(int rating) {
        imageLibrary.photos.get(displayImageIndex).setRating(rating);
        updateDisplayedImageCaption();
        updateCaptions();
    }

    /**
     * Most of the work goes here, it creates all the components by adding them to a panel and then a pane. Tells buttons
     * what to do.
     */
    public void addComponentsToPane(Container pane) {

        pane.setLayout(new BorderLayout());

        /**
         * Initializes the JLabels
         */
        p1 = new JLabel();
        p2 = new JLabel();
        p3 = new JLabel();
        p4 = new JLabel();
        p5 = new JLabel();
        displayImage = new JLabel(this.imageLibrary.photos.get(0).getCaption());
        changeMainImage(0);
        ratingLabel = new JLabel("I rate this: ");

        /**
         * Initializes the JPanels
         */
        displayImagePanel = new JPanel();
        thumbnails = new JPanel();
        thumbnails.setLayout(new BoxLayout(thumbnails, BoxLayout.Y_AXIS));
        buttonsPanel = new JPanel();
        sortPanel = new JPanel();
        sortPanel.setLayout(new BoxLayout(sortPanel, BoxLayout.Y_AXIS));
        rating = new JPanel();

        /**
         * Initializes the JButtons
         */
        previous = new JButton("Previous");
        next = new JButton("Next");
        sortByCaption = new JButton("Sort By Caption");
        sortByRating = new JButton("Sort By Rating");
        sortByDate = new JButton("Sort By Date");

        /**
         * Initializes the JRadioButtons
         */
        r1 = new JRadioButton("1");
        r2 = new JRadioButton("2");
        r3 = new JRadioButton("3");
        r4 = new JRadioButton("4");
        r5 = new JRadioButton("5");

        /**
         * Initializes the Buttongroup (this allows the filled in dot to disappear after clicking on another rating)
         */
        currentRating = new ButtonGroup();

        /**
         * Adds everything to their corresponding groups and adds to the pane
         */
        displayImagePanel.add(displayImage);
        updateImages();
        updateCaptions();
        thumbnails.add(p1);
        thumbnails.add(p2);
        thumbnails.add(p3);
        thumbnails.add(p4);
        thumbnails.add(p5);
        buttonsPanel.add(previous);
        buttonsPanel.add(next);
        sortPanel.add(sortByCaption);
        sortPanel.add(sortByRating);
        sortPanel.add(sortByDate);
        currentRating.add(r1);
        currentRating.add(r2);
        currentRating.add(r3);
        currentRating.add(r4);
        currentRating.add(r5);
        rating.add(ratingLabel);
        rating.add(r1);
        rating.add(r2);
        rating.add(r3);
        rating.add(r4);
        rating.add(r5);
        pane.add(thumbnails, BorderLayout.EAST);
        pane.add(displayImagePanel, BorderLayout.CENTER);
        pane.add(buttonsPanel, BorderLayout.SOUTH);
        pane.add(sortPanel, BorderLayout.WEST);
        pane.add(rating, BorderLayout.NORTH);

        /**
         * Add listeners for each thumbnail so it will change to become the displayed image
         */
        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeImage(0, p1);
            }
        });

        p2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeImage(1, p2);
            }
        });

        p3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeImage(2, p3);
            }
        });

        p4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeImage(3, p4);
            }
        });

        p5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeImage(4, p5);
            }
        });

        /**
         * Creates buttons and assign listeners to them to make them do what they are intended to do 
         * Next goes to next photo
         * Previous goes to previous photo
         */

        previous.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (displayImageIndex > 0) {
                    displayImageIndex--;
                } else {
                    displayImageIndex = 4;
                }
                changeMainImage(displayImageIndex);
                updateCaptions();
            }
        });

        next.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                if (displayImageIndex < 4) {
                    displayImageIndex++;
                } else {
                    displayImageIndex = 0;
                }
                changeMainImage(displayImageIndex);
                updateCaptions();
            }
        });

        /**
         * Sort By buttons will sort into what order they are assigned to
         */

        sortByCaption.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                displayImageIndex = 0;
                Collections.sort(imageLibrary.getPhotos(), new CompareByCaption());
                updateImages();
                updateCaptions();
                changeMainImage(0);
            }
        });

        sortByRating.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                displayImageIndex = 0;
                Collections.sort(imageLibrary.getPhotos(), new CompareByRating());
                updateImages();
                updateCaptions();
                changeMainImage(0);
            }
        });

        sortByDate.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                displayImageIndex = 0;
                Collections.sort(imageLibrary.getPhotos());
                updateImages();
                updateCaptions();
                changeMainImage(0);
            }
        });

        /**
         * Radio buttons for 1 to 5 in the rating, which will update to whichever is chosen by assigning buttons to listeners
         */

        r1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeRating(1);
            }
        });

        r2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeRating(2);
            }
        });

        r3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeRating(3);
            }
        });

        r4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeRating(4);
            }
        });

        r5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                changeRating(5);
            }
        });
    }

    public static void main(String[] args) {
        PhotoViewer photoViewer = new PhotoViewer();
        String imageDirectory = "images\\";
        Photograph p1 = new Photograph("AlaskanAurora", imageDirectory + "AlaskanAurora.jpg", "2011-04-20", 5);
        Photograph p2 = new Photograph("Beach", imageDirectory + "Beach.jpg", "2012-09-12", 3);
        Photograph p3 = new Photograph("Dock", imageDirectory + "Dock.png", "2014-08-07", 2);
        Photograph p4 = new Photograph("Mountains", imageDirectory + "Mountains.jpg", "2014-08-29", 1);
        Photograph p5 = new Photograph("Road", imageDirectory + "Road.jpg", "2013-03-16", 4);
        photoViewer.imageLibrary = new PhotoLibrary("PhotoLibrary", 1);
        photoViewer.imageLibrary.addPhoto(p1);
        photoViewer.imageLibrary.addPhoto(p2);
        photoViewer.imageLibrary.addPhoto(p3);
        photoViewer.imageLibrary.addPhoto(p4);
        photoViewer.imageLibrary.addPhoto(p5);
        Collections.sort(photoViewer.imageLibrary.photos);
        javax.swing.SwingUtilities.invokeLater(() -> photoViewer.createAndShowGUI());
    }
}
