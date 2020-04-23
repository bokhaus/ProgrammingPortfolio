/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.capella.it4736;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.bson.Document;

/**
 *
 * @author BrianBok
 */

public class U9a1_BookReviewQuery_v2 {

    
    
        //Creates logger CONSTANT.   
    static final Logger LOG = Logger.getLogger(U9a1_BookReviewQuery_v2.class.getName());
    
    //Variables
    private static String username;//Class variable for authentication
    private static String encoded_pwd;//Encoded variable converts special characters to use in MongoDB for authentication
    private static MongoClient mongoClient;// Private class variable to provide access to appropriate client
    private static MongoCollection<Document> collection;// Private class variable to provide access to appropriate collection
    private static MongoDatabase mongoDB;// Private class variable to provide access to appropriate database
    private static String selection; //variable for selection of Book object book_id
    private static int cumm_user_ranking;//variable to calculate cumm user rating in MariaDb 
    
    //Experimental variables for use in methods to verify user connection
    private static final ArrayList<Integer> STATUS = new ArrayList<>();
    private static final int STATUS_VALUE = 0;
    
    /* --------------------------------------------------------------
        >>> CONNECTION INFORMATION BELOW <<<
     MariaDB Connection information for MongoDB on localhost 
    -------------------------------------------------------------- */
    //MariaDB login info CONSTANTS. Order NEEDED for constructor (USERNAME, PASSWORD, DB_URL, SQL)
    private static final String USERNAME = "registrar";
    private static final String PASSWORD = "P@ssword";
    private static final String DB_URL = "jdbc:mariadb://localhost:3306/registration";  
    
    
    // Query to get list of available books
    //***COMPLETE
    private static final String REVIEW_SQL = "SELECT * FROM book_review.review_ratings WHERE book_id = ?";
        
    // Query to calculate overall user's rating out of 10
    // Query creates a temp column which holds the calculation
    // Calculation is done by dividing total value of reviews by total reviews.
    // ***COMPLETE 
    private static final String CUMM_RANKING = "SELECT user_name, " + 
                                "((SELECT SUM(reviewer_rating) " +
				"FROM book_review.review_ratings " +
				"WHERE user_name = ?) / " +
				"(SELECT SUM(num_of_reviews)FROM book_review.review_ratings " +
				"WHERE user_name = ?)) AS cumm_user_ranking " +
                                "FROM book_review.review_ratings " +
                                "WHERE user_name = ?" +
                                "LIMIT 1" ;
  
    
    public static void main(String[] args){
        
    //Format the logger in both log file and console
    SimpleFormatter simpleFormatter = new SimpleFormatter();
    
    // Adjust logging level for MongoDB client to avoid too much info
    Logger mongoLogger = Logger.getLogger( "org.mongodb.driver" );
    mongoLogger.setLevel(Level.WARNING); 
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("U9a1_BookReviewQuery_v2.log");
            fileHandler.setFormatter(simpleFormatter);
            LOG.addHandler(fileHandler);
            
            //Logger for log file --> U9a1_BookReviewQuery_v2.log -> Records all levels
            LOG.setLevel(Level.ALL);
            
            //Logger for console --> ONLY records Level.SEVERE
            Logger.getLogger("").getHandlers()[0].setLevel(Level.SEVERE);
            
        //multi-catch block for Exceptions
        } catch (IOException | SecurityException ex) {
            LOG.log(Level.SEVERE,ex.getMessage());
            System.exit(1);
        }
        LOG.log(Level.INFO, "****** Program Start ******");
        LOG.log(Level.CONFIG, "Creating logger for program.");
        
        Scanner input = new Scanner(System.in);
        
        //User login 
        System.out.print("User Name: ");
        username = input.nextLine();
        System.out.print("User Password: ");
        String userPwd = input.nextLine();
        
        
        //Encodes password special characters such as @ which is equal to %40
        try {
            encoded_pwd = URLEncoder.encode(userPwd, "ASCII");
        } catch (UnsupportedEncodingException ex) {
            LOG.log(Level.INFO, "UnsupportedEncodingException{0}{1}{2}", new Object[]{ex.getMessage(), ex.getClass()});
        }
        /* --------------------------------------------------------------
                >>> CONNECTION INFORMATION BELOW <<<
             MongoDB Connection information for MongoDB on localhost 
        -------------------------------------------------------------- */
           
        /* Set mongoURI for localhost and database authentication -> /?authSource=book_review */
        String mongoURI = "mongodb://" + username + ":" + encoded_pwd + "@localhost:27017/?authSource=book_review";

        /* Set database to name of database with book info */
        String database = "book_review";
        /* Set collectionName to name of collection with book data */
        String collectionName = "books";
        
        
        MongoClientURI mongoConnURI = new MongoClientURI(mongoURI);

        try{ //MongoDB connection 
            mongoClient = new MongoClient (mongoConnURI);
            mongoDB = mongoClient.getDatabase(database);
            collection = mongoDB.getCollection(collectionName);
            
            System.out.println("\nConnected to the database successfully!");
            
            }
            catch(MongoException me){
            
            System.out.println("\nLOGIN ERROR");
            mongoClient.close();
            System.exit(1);
            
            LOG.log(Level.WARNING, "Failed to connect to database{0}{1}{2}", new Object[]{me.getMessage(), me.getClass()});
            }
            
        //Variables for do-while continue loop
        char yesOrNo = 'Y';
        
        String genre = null;
        
        //DO-while loop to continue for other book searches
        do{
            System.out.print("\nEnter a genre (i.e.,Fiction): ");
            genre = input.next();
            
            switch (yesOrNo){
                case 'Y':
                    
                    //Get book choices
                    MongoCursor<Document> cursor = findBooks(genre);
                    
                    //Get book_id
                    selection = getBookChoice(cursor);
                    
                    //Display data from methods to console
                    while(!selection.equals("0")) {
                        
                        try {
                            
                            //Get Selection of TITLE from book_id
                            Book r = getBookInfo(selection);
                            System.out.println(String.format("%3s %50s %15s", " ", " ", " ").replace(" ", "-"));
                            System.out.println(r);
                            LOG.log(Level.INFO, "Displaying TITLE INFORMATION for the book: ({0})from getBookInfo()", r.getTitle());
                            
                            //Pause for affect
                            try {
                                Thread.sleep(800);
                            }
                            catch (InterruptedException ex) {
                                Logger.getLogger(U9a1_BookReviewQuery_v2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            //Get formats of TITLE
                            System.out.println(String.format("%3s %50s %15s", " ", " ", " ").replace(" ", "-"));
                            System.out.println("\nFormats Available for (" + r.getTitle() + "):");
                            getBookFormats(r);
                            LOG.log(Level.INFO, "Displaying FORMAT INFORMATION for the book: ({0})from getBookFormats()", r.getTitle());
                            
                            System.out.println(String.format("\n%3s %50s %15s", " ", " ", " ").replace(" ", "-"));
                            
                            //Pause for affect
                            try {
                                Thread.sleep(800);
                            }
                            catch (InterruptedException ex) {
                                Logger.getLogger(U9a1_BookReviewQuery_v2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            //creating future object called reviewers and passing parameters for method call
                            Future<ArrayList<Reviewer>> reviewers = getReviewerInfoAsync(DB_URL, USERNAME, PASSWORD, selection);
                            
                            //FOR loop to iterate all Reviewer data for MongoDB database
                            try{
                                for(Reviewer rev : reviewers.get()){
                                    
                                    //Get Reviews of TITLE geCummReviewerRating
                                    System.out.println("\nReviews for (" + r.getTitle() + "):");
                                    
                                    //Uses method call getCummReviewerRating to calculate the overall rating of the user considering all reviews
                                    System.out.println("\nUser: " + rev.getUserName() +"'s overall rating for all reviews posted is "
                                            + getCummReviewerRating(DB_URL, USERNAME, PASSWORD, rev.getUserName()) + " out of 10.");
                                    System.out.println("\nThis is what " + rev.getUserName() + " has said about the book...\n");
                                    
                                    //Get book Review
                                    getBookReviews(r);
                                    LOG.log(Level.INFO, "Displaying REVIEW INFORMATION for the book: ({0})from getBookReviews()", r.getTitle());
                                    
                                    System.out.println("");
                                }
                            }
                            catch (InterruptedException | ExecutionException ex) {
                                LOG.log(Level.WARNING, "Asynchronous call error. Exiting system: {0}", ex.getMessage());
                                
                                System.exit(1);
                            }
                            
                            LOG.log(Level.INFO,"Call Async method for thread query");
                            
                            
                            System.out.println("");
                            
                            LOG.log(Level.INFO, "Exit Do_While LOOP in case ''Y'' Locating book and displaying Title INFORMATION for the book: ({0})", r.getTitle());
                            
                            break;
                        } 
                        catch (SQLException ex) {
                            Logger.getLogger(U9a1_BookReviewQuery_v2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                default:
                    LOG.log(Level.INFO, "Exit Do_While LOOP in the 'default' case...Select different book or Close program.");
                    
                    break;
            }  
            System.out.println(String.format("%3s %50s %15s", " ", " ", " ").replace(" ", "-"));
            
            //Pause for affect
            try {
                Thread.sleep(500);
            } 
            catch (InterruptedException ex) {
                
                Logger.getLogger(U9a1_BookReviewQuery_v2.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.print("\nWould you like to search for another book? (Yes/No): ");
                yesOrNo = input.next().toUpperCase().charAt(0);
        }
        while(yesOrNo == 'Y');{
        
        //printing statement while closing book review
        int count = 0;
        System.out.println("\nThe book search will now exit......");
        
        System.out.println("\nClosing ......");
        while(count <5) {
            try{
                Thread.sleep(500);
                System.out.println("\t......");
                count ++;
            }
            catch(InterruptedException ex){
                LOG.log(Level.WARNING, "Error in call to Thread.sleep{0}{1}", new Object[]{ex.getMessage(), ex.getClass()});
            }
        }
        System.out.println("\nThank you for visiting!\n");
        LOG.log(Level.INFO, "Exit yesOrNo SWITCH...***Closing Program***");
        }    
    }//End of Main
    
    //method to return input for genre in do-while loop
     public static String getChoice(Scanner input) {
        System.out.print("\nEnter a genre (i.e.,Fiction): ");
             
        return (input.next());
    }

    //Finds all books for genre
    public static MongoCursor<Document> findBooks(String genre) {
        
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
    }//End findBooks method
    
    //Method to select single book and returns book_id
    public static String getBookChoice(MongoCursor<Document> cursor) {
        int refNumber = 0;
        // ArrayList of book IDs
        ArrayList<String> id = new ArrayList<>();
        System.out.println("");
        
        // Print header for list of books
        System.out.println(String.format("%5s %-50s %s", "[#]", "Title", "Genre"));
        System.out.println(String.format("%3s %50s %15s", " ", " ", " ").replace(" ", "-"));
        
        //Retrieve Cursor and add genre to id ArrayList format output
        while(cursor.hasNext()) {
            Document bookDoc = cursor.next();
            String output = String.format("%5s %-50s %s", "["  + ++refNumber + "]", bookDoc.getString("title"), 
                    bookDoc.getString("genre"));
            System.out.println(output);
            id.add(bookDoc.getString("book_id"));
            
            LOG.log(Level.CONFIG, "Format initial selections table");
        }
        
        System.out.print("\nEnter selection number (0 to quit): ");
        Scanner input = new Scanner(System.in);
        
        //Select choice
        int choice = input.nextInt();
        String book_id;
        // choice not 0 and not too high
        if(choice > 0 && choice <= refNumber) {
            book_id = id.get(choice - 1);
        }
        else {
            book_id = "0";
        }
        LOG.log(Level.INFO, "Book selection made ''{0}''", book_id);
        
        return book_id;
        
    }//End getBookChoice Method 
    
    //Method to get Book object, returns Book object
    public static Book getBookInfo(String book_id) {
            
        BasicDBObject query = new BasicDBObject();
        /* Use BasicDBObject put() method to add criteria for the query */
        query.put("book_id", new BasicDBObject("$regex", "^.*" + book_id + ".*").append("$options", "i"));
        
        // Result goes into List, even though this program returns only 1 book
        //Book(String book_id, String title, String author, String genre, Publisher publishInfo)
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
            
            //Retrieve subDoc of publishInfo data
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
                    
                    // Access and display userReview
                    String reviewer_id = reviewDoc.getString("reviewer_id");
                    
                    //reviews appended to book object
                    //adds reviews to arraylist
                    bookReviews.add(new Review(d, reviewer_id, bookRate, userReview));
                    
                    //appends book object Review(Date date, int book_rating, String review)
                    r.getReview().add(new Review(d, reviewer_id, bookRate, userReview));
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
        LOG.log(Level.INFO, "Return NEW Book    param: {0}", book_id);
        
        return r;
    }//End getBookInfo Method
    
    //Method to get Reviewer information
    private  static ArrayList<Reviewer> getReviewerInfo(String dbURL, String user, String password, String selection) throws SQLException {
        ArrayList<Reviewer> reviewers = new ArrayList<>();
        Reviewer rI = null;
        //DB connection
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            PreparedStatement stmt = conn.prepareStatement(REVIEW_SQL);
            stmt.setString(1, selection);
            ResultSet result = stmt.executeQuery();

            LOG.log(Level.INFO, " Connection is valid: {0}", conn.isValid(0));
            
            //Iterate cursor over resultSet to add Reviewer
            while(result.next()) {
                String book_id = result.getString("book_id");
                String user_id = result.getString("user_id");
                String user_name = result.getString("user_name");
                int num_of_reviews = result.getInt("num_of_reviews");
                int reviewer_rating = result.getInt("reviewer_rating");
                
                rI = new Reviewer(book_id, user_id, user_name, num_of_reviews, reviewer_rating);

                reviewers.add(new Reviewer(book_id, user_id, user_name, num_of_reviews, reviewer_rating));
            }
            LOG.log(Level.INFO, "Return NEW Reviewer    params:  dbURL, user, password, {0}", selection);
            return reviewers;
        }
    } // end of getReviewerInfo();
    
    //Async Call for getReviewerInfo
    private  static Future<ArrayList<Reviewer>> getReviewerInfoAsync(String dbURL, String user, String password, String selection) throws SQLException {
        CompletableFuture<ArrayList<Reviewer>> result = new CompletableFuture<>();
        
        new Thread(()->{
            
            try {
                result.complete(getReviewerInfo(dbURL, user, password, selection));
                
                
            } catch (SQLException ex) {
                Logger.getLogger(U9a1_BookReviewQuery_v2.class.getName()).log(Level.SEVERE, null, ex);
                LOG.log(Level.WARNING, "SQL Exception: {0}{1}", new Object[]{ex.getMessage(), ex.getSQLState()});
            }
            
        }).start();
        LOG.log(Level.INFO, "queryDatabaseAsync Method Start....params:  dbURL, user, password, {0}", selection);
        
        return result;
    }//end Async method

    //Gets cumm_rating for reviewer
    private  static int getCummReviewerRating(String dbURL, String user, String password, String user_name) throws SQLException {
        //DB connection
        try(Connection conn = DriverManager.getConnection(dbURL, user, password)){
            PreparedStatement stmt = conn.prepareStatement(CUMM_RANKING);
            stmt.setString(1, user_name);
            stmt.setString(2, user_name);
            stmt.setString(3, user_name);
            ResultSet result = stmt.executeQuery();

            LOG.log(Level.INFO, " Connection is valid: {0}", conn.isValid(0));
            
            while(result.next()) {
                cumm_user_ranking = result.getInt("cumm_user_ranking");
                
            }
            LOG.log(Level.INFO, "Return NEW Reviewer    params:  dbURL, user, password, {0}", user_name);
            
            return cumm_user_ranking;
        }
    } // end of getCummReviewerRating Method

    //Prints format information
    private static void getBookFormats(Book r){
        System.out.println(r.getFormats().toString());
        
        LOG.log(Level.INFO, "getBookFormats Method (toString) Start....params: \n{0}", r);
    }//End getBookFormats Method
     
    //Prints Review information
    private static void getBookReviews(Book r){
        System.out.println(r.getReview().toString());
        
        LOG.log(Level.INFO, "getBookReviews Method (toString) Start....params: \n{0}", r);
    }//End getBookReviews Method
   
}//End Program

