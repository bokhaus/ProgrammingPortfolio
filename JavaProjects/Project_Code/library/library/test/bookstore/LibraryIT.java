package bookstore;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author BrianBok
 */
public class LibraryIT {
    
    Book libBook1 = new Book("Howard Stern", "Howard Stern Comes Again", "Biography");
    Book libBook2 = new Book("David Baldacci", "Redemption", "Mystery");
    Book libBook3 = new Book("Alex Michaelides", "The Silent Patient", "Suspense");
    Book libBook4 = new Book("Wendy Walker", "The Night Before: A Novel", "Fiction");
    Book libBook5 = new Book("Richard Condon", "The Manchurian Candidate", "Fiction");
    Book libBook6 = new Book("Frances Liardet", "We Must Be Brave", "Fiction");
    Book libBook7 = new Book("Tom Clancy", "Rainbow Six", "Fiction");
    Book libBook8 = new Book("Tom Clancy", "Against All Enemies", "Fiction");
    Library instance = new Library();
    
    @Before
    public void setUp() {
        instance.addBook(libBook1);
        instance.addBook(libBook2);
        instance.addBook(libBook3);
        instance.addBook(libBook4);
        instance.addBook(libBook5);
        instance.addBook(libBook6);
        instance.addBook(libBook7);
        instance.addBook(libBook8);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
         
    @Test
    public void testDescribeBooksBy() {
        System.out.println("TEST: Instance Returns Multiple Books by Author");
        String author = "Tom Clancy";
        Iterable<? extends String> result = instance.describeBooksBy(author);
        assertNotNull("The result should not be null", result);
        System.out.println("***Author: " + author);
        System.out.println("***Result: " + result);
        System.out.println();

    }
    
    @Test
    public void getSingleBookByAuthor() {
        
        System.out.println("TEST: Instance Returns Single Book by Author");
        String author = "David Baldacci";
        String title = "Redemption";

        //Building expected result
        ArrayList<String> bookExpResult = new ArrayList<String>();
        bookExpResult.add(author + ": " + title);
        Iterable<? extends String> expResult = bookExpResult;
        //Actual result-Running the method
        Iterable<? extends String> result = instance.describeBooksBy(author);

        //Print out results
        System.out.println("Expected: " + expResult);
        System.out.println("Actual: " + result);
        assertEquals(expResult, result);
        System.out.println();
    }

    @Test
    public void testEmptySet() {
        
        System.out.println("TEST: Empty Set describeBooksBy()");
        System.out.println("Author NOT in library");
        String author = " Frederick Forsyth";
        List<String> booksByAuthor = new ArrayList<String>();
        Iterable<? extends String> expResult = booksByAuthor;
        Iterable<? extends String> result = instance.describeBooksBy(author);
        assertEquals(expResult, result);
        System.out.println("Author: " + author);
        System.out.println("***Expected: " + expResult);
        System.out.println("***Result: " + result);
        System.out.println();

    }     
    @Test
    public void getNullInput() {
        System.out.println("TEST: Null Value Passed to describeBooksBy().");
        try{
           String author = null;
           Iterable<? extends String> result = instance.describeBooksBy(author);
           System.out.println("Actual: " + result);
           fail("Expect null pointer expection.");
        }
        catch (NullPointerException e){
            System.out.println("***Null input exception: " + e.getMessage());
            System.out.println();
        }
    }

}