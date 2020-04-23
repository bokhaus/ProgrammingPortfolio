/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import java.util.Date;

/**
 *
 * @author BrianBok
 */

public class Publisher {
    private Date date_published;
    private String place_of_publication;
    private String publisher;

    public Publisher(Date date_published, String place_of_publication, String publisher) {
        this.date_published = date_published;
        this.place_of_publication = place_of_publication;
        this.publisher = publisher;
    }

    public Date getPublishDate() {
        return date_published;
    }

    public void setPublishDate(Date date_published) {
        this.date_published = date_published;
    }

    public String getPlacePub() {
        return place_of_publication;
    }

    public void setPlacePub(String place_of_publication) {
        this.place_of_publication = place_of_publication;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    @Override
    public String toString() {
        // Use StringBuilder to create string efficiently
        StringBuilder pubInfo = new StringBuilder();
        pubInfo.append("\nPublication Information: \n").append("Published Date: ").append(date_published);
        pubInfo.append("\nPlace of Publication: ").append(place_of_publication);
        pubInfo.append("\nPublisher: ").append(publisher).append("\n");
        return pubInfo.toString();
    }

}


