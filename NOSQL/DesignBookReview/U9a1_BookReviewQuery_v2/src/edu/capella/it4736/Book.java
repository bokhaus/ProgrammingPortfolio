/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author BrianBok
 */

public class Book {
    private String book_id;
    private String title;
    private String author;
    private String genre;
    private Publisher publishInfo;
    private final ArrayList<Review> reviews;
    private final ArrayList<Format> formats;
    private final ArrayList<Reviewer> reviewer;
    
    // Parameterized constructor, but review and format not passed as parameters. See addReview() and addFormat() and addReviewer below. 
    public Book(String book_id, String title, String author, String genre, Publisher publishInfo) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishInfo = publishInfo;
        this.reviews = new ArrayList<>();
        this.formats = new ArrayList<>();
        this.reviewer = new ArrayList<>();
    }

    public String getBook() {
        return book_id;
    }

    public void setBook(String book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Publisher getPublishInfo() {
        return publishInfo;
    }

    public void setPublishInfo(Publisher publishInfo) {
        this.publishInfo = publishInfo;
    }
    
    public ArrayList<Review> getReview() {
        return reviews;
    }

    public void addReview(Date date, String reviewer_id, String book_rating, String review) {
        this.reviews.add(new Review(date, reviewer_id, book_rating, review));
    }
    
    public ArrayList<Format> getFormats() {
        return formats;
    }

    public void addFormat(String book_format, String page_count_minutes, String ISBN) {
        this.formats.add(new Format(book_format, page_count_minutes, ISBN));
    }
    

    
    @Override
    public String toString() {
        // Use StringBuilder to create string efficiently
        StringBuilder bookInfo = new StringBuilder();
        bookInfo.append("\n").append("Title: ").append(title).append("\n");
        bookInfo.append("Author: ").append(author).append("\n");
        bookInfo.append("Genre: ").append(genre).append("\n");
        bookInfo.append(publishInfo.toString());
        return bookInfo.toString();
    }
}
