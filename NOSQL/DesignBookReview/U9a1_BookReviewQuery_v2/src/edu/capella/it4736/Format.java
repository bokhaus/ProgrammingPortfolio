/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

/**
 *
 * @author BrianBok
 */

public class Format {
    private String book_format;
    private String page_count_minutes;
    private String ISBN;

    public Format(String book_format, String page_count_minutes, String ISBN) {
        this.book_format = book_format;
        this.page_count_minutes = page_count_minutes;
        this.ISBN = ISBN;
    }

    public String getBookFormat() {
        return book_format;
    }

    public void setBookFormat(String book_format) {
        this.book_format = book_format;
    }

    public String getPageCount() {
        return page_count_minutes;
    }

    public void setPageCount(String page_count_minutes) {
        this.page_count_minutes = page_count_minutes;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    
    @Override
    public String toString() {
        // Use StringBuilder to create string efficiently
        StringBuilder formatInfo = new StringBuilder();
        formatInfo.append("Format: ").append(book_format).append(" ").append(page_count_minutes);
        formatInfo.append(" (ISBN: ").append(ISBN).append(")").append(" ");
        return formatInfo.toString();
    }

}
