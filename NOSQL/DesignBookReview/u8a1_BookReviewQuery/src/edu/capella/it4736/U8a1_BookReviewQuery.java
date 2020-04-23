/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.bson.Document;

/**
 *
 * @author User
 */
public class U8a1_BookReviewQuery {

    // Private class field to provide access to appropriate collection
    private static MongoCollection<Document> collection;
    
        //Creates logger CONSTANT.   
    static final Logger LOG = Logger.getLogger(U8a1_BookReviewQuery.class.getName());
    

    //************************************************************** 
    //Book(String book_id, String title, String author, String genre)
    //Review(Date date, int book_rating, String review)
    //Format(String book_format, String page_count, int ISBN)
    //************************************************************** 
    
    public static void main(String[] args) {
        
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        // Adjust logging level for MongoDB client to avoid too much info
        Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
        mongoLogger.setLevel(Level.WARNING); 
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("U8a1_BookReviewQuery.log");
            fileHandler.setFormatter(simpleFormatter);
            LOG.addHandler(fileHandler);
            
            //Logger for log file --> weather.log. Records all levels
            LOG.setLevel(Level.ALL);
            
            //Logger for console --> ONLY records Level.SEVERE
            Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
            
        //multi-catch block   
        } catch (IOException | SecurityException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        }
        LOG.log(Level.INFO, "****** Program Start ******");
        LOG.log(Level.INFO, "Creating logger for program.");
        
        /* --------------------------------------------------------------
                >>> CONNECTION INFORMATION BELOW <<<
             MongoDB Connection information for MongoDB on localhost 
            -------------------------------------------------------------- */
        
        /* Set mongoURI to address for localhost /?authSource=book_review*/
        String mongoURI = "mongodb://reviewReader:P%40ssword1@localhost:27017/?authSource=book_review";
        /* Set database to name of database with book info */
        String database = "book_review";
        /* Set collectionName to name of collection with book data */
        String collectionName = "books";
        
        MongoClientURI mongoConnURI = new MongoClientURI(mongoURI);
        MongoClient mongoClient = new MongoClient(mongoConnURI);
        MongoDatabase mongoDB = mongoClient.getDatabase(database);
        collection = mongoDB.getCollection(collectionName);
                
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a genre: ");
        String genre = input.nextLine();
        
        //Get book choices
        try{
            MongoCursor<Document> cursor = findBooks(genre);
         //Get Book 
            String selection = getBookChoice(cursor);

            if(!selection.equals("0")) {
                Book r = getBookInfo(selection);
                System.out.println("\n----------------------------------------------------------------------");
                System.out.println(r);
                System.out.println("\n----------------------------------------------------------------------");
                System.out.println("Formats for " + r.getTitle() + ":");
                getBookFormats(r);
                System.out.println("\n----------------------------------------------------------------------");
                System.out.println("Reviews for " + r.getTitle() + ":");
                getBookReviews(r);
                
                System.out.println("");
            }
            else {
                System.out.println("Exiting...\n");
            }
        }
        catch(NullPointerException e){
            LOG.log(Level.WARNING, "NullPointerException{0}{1}", new Object[]{e.getMessage(), e.getCause()});
        }   
    }//End of Main

    static public MongoCursor<Document> findBooks(String genre) {
        // List of fields for projection
        String[] includeList = {"title", "book_id", "genre"};
        BasicDBObject query = new BasicDBObject();
        /* Use BasicDBObject put() method to add criteria for the query */
        query.put("genre", new BasicDBObject("$regex", "^.*" + genre + ".*").append("$options", "i"));
        // Run query with projection to limit fields returned
        MongoCursor<Document> cursor = collection.find(query)
                .projection(fields(include(includeList)))
                .iterator();
        
        return cursor;  
    }
    static public String getBookChoice(MongoCursor<Document> cursor) {
        int refNumber = 0;
        // ArrayList of book IDs
        ArrayList<String> id = new ArrayList<>();
        System.out.println("");
        // Print header for list of books
        System.out.println(String.format("%5s %-50s %s", "[#]", "Title", "Genre"));
        System.out.println(String.format("%3s %50s %15s", " ", " ", " ").replace(" ", "-"));
        while(cursor.hasNext()) {
            Document bookDoc = cursor.next();
            String output = String.format("%5s %-50s %s", "["  + ++refNumber + "]", bookDoc.getString("title"), 
                    bookDoc.getString("genre"));
            System.out.println(output);
            id.add(bookDoc.getString("book_id"));
        }
        System.out.print("\nEnter selection number (0 to quit): ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        String book_id;
        // choice not 0 and not too high
        if(choice > 0 && choice <= refNumber) {
            book_id = id.get(choice - 1);
        }
        else {
            book_id = "0";
        }
        return book_id;
    }//End getBookChoice Method    
    static public Book getBookInfo(String book_id) {
            
        BasicDBObject query = new BasicDBObject();
        /* Use BasicDBObject put() method to add criteria for the query */
        query.put("book_id", new BasicDBObject("$regex", "^.*" + book_id + ".*").append("$options", "i"));
        
        // Result goes into List, even though this program returns only 1 restaurant
        List<Document> books = (List<Document>) collection.find(query).into(new ArrayList<>());
        
        // ArrayList of bookReviews
        ArrayList<Review> bookReviews = new ArrayList<>();
        Book r = null;
        
        for(Document b : books) {
            
            //Retrieve main doc
            String bookID = b.getString("book_id");
            String title = b.getString("title");
            String author = b.getString("author");
            String genre = b.getString("genre");
            
            //Retrieve subDoc of published data
            Document bookInfoDoc = (Document) b.get("book_info");
            Date published = bookInfoDoc.getDate("date_published");
            String location = bookInfoDoc.getString("place_of_publication");
            String publisher = bookInfoDoc.getString("publisher");

            
            //Publisher(Date date_published, String place_of_publication, String publisher)
            //Building Publisher object 
            Publisher publInfo = new Publisher(published, location, publisher);

            //Book(String book_id, String title, String author, String genre)
            //constructor call --> book object
            r = new Book(bookID, title, author, genre, publInfo);
            
            // Pull out review subdocument
            List<Document> reviews = (List<Document>) b.get("reviews");
            
            if(reviews.size() > 0) {
                for(Document reviewDoc : reviews) {
                    // get date
                    Date d = reviewDoc.getDate("date");
                    
                    // Access and display book_rating
                    String bookRate = reviewDoc.getString("book_rating" );
                    
                    // Access and display userReview
                    String userReview = reviewDoc.getString("review");
                    
                    //reviews appended to book object
                    //adds reviews to arraylist
                    bookReviews.add(new Review(d, bookRate, userReview));
                    
                    //appends book object Review(Date date, int book_rating, String review)
                    r.getReview().add(new Review(d, bookRate, userReview));
                    
                    
                }
            }
            // ArrayList of bookReviews
            ArrayList<Format> bookFormats = new ArrayList<>();
            // Pull out review subdocument
            List<Document> formats = (List<Document>) b.get("formats");
            
            if(formats.size() > 0) {
                for(Document reviewDoc : formats) {
                    // Access and display book_rating
                    String bookFormat = reviewDoc.getString("book_format" );
                    
                    // Access and display userReview
                    String pageCount = reviewDoc.getString("page_count_minutes");
                    
                    // Access and display userReview
                    String ISBN = reviewDoc.getString("ISBN");
                    
                    //Format(String book_format, String page_count, int ISBN)
                    //adds reviews to arraylist
                    bookFormats.add(new Format(bookFormat, pageCount, ISBN));
                    
                    //appends book object Review(Date date, int book_rating, String review)
                    r.getFormats().add(new Format(bookFormat, pageCount, ISBN));
                    

                }
            }            
        }
        return r;
    }//End getRestaurantInfo Method
    public static void getBookReviews(Book r){
        System.out.println(r.getReview().toString());
    }
    private static void getBookFormats(Book r){
        System.out.println(r.getFormats().toString());
    }
}
    

