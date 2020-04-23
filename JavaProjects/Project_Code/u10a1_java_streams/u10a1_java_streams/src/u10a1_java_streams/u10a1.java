package u10a1_java_streams;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author bberkland
 */
public class u10a1 extends Application {
    
    //TableView
    final private TableView<AssignmentInfo> table = new TableView<>();
    
    //Creates logger CONSTANT.   
    static final Logger LOG = Logger.getLogger(u10a1.class.getName()); 
    
   
    public static void main(String[] args)  {
        //Format the logger in both log file and console
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        
        //FileHandler for logger
        try{
            FileHandler fileHandler = new FileHandler("u10a1_TableView_AssignmentAverages.log");
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
                
        
        launch(args);
    }
    @SuppressWarnings({"unchecked"})
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(new Group());
        stage.setTitle("Assignment Averages");
        stage.setWidth(360);
        stage.setHeight(550);
        
        // Data in table is not editable, but it is sortable and selectable
        table.setEditable(false);
        
        // Define table columns and set data source for each column
        // Assignment ID column
        TableColumn<AssignmentInfo, String> assignmentIDColumn = new TableColumn<>("Assignment ID");
        assignmentIDColumn.setMinWidth(160);
        assignmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("assignmentID"));
        //Set first column data in cell alignment 
        //Can also be "-fx-alignment: CENTER-RIGHT;" or "CENTER-LEFT" OR LEFT BLANK
        assignmentIDColumn.setStyle( "-fx-alignment: CENTER;");
        
        // Average column
        // Added formatting in AssignmentInfo class to limit number of positions
        // past decimal point to two.
        TableColumn<AssignmentInfo, Double> averageColumn = new TableColumn<>("Average");
        averageColumn.setCellValueFactory(new PropertyValueFactory<>("average"));
        averageColumn.setMinWidth(160);
        //Set data in cell aligned to CENTER
        averageColumn.setStyle( "-fx-alignment: CENTER;");
        
        /* To delete third column use .setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
           Extra space in table column header will be distributed among the columns. 
           In this distribution the columns' max and min widths are taken into account.
           By default the TableView.UNCONSTRAINED_RESIZE_POLICY is used where the tablecolumns 
           will take their preferred width initially.
        */
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
        // Add columns to TableView and set data source to observable list
        table.getColumns().addAll(assignmentIDColumn, averageColumn);
        
        // Label to display messages below TableView. Will be used for DB connect errors and to 
        // display text of selected row in table. 
        Label messageLabel = new Label();
        Label selectionLabel = new Label();
        messageLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        //underlining the text     
        messageLabel.setUnderline(true);  
        messageLabel.setTextFill(Color.DODGERBLUE);
        
        selectionLabel.setFont(Font.font("Arial", 15));
        selectionLabel.setTextFill(Color.BLACK);
        
        // Show selected row's data in message label when mouse is clicked
        table.setOnMouseClicked(e -> {
            
            if(table.getSelectionModel().getSelectedIndex() > -1) {    
                //Sets label as selected data
                AssignmentInfo selected = table.getSelectionModel().getSelectedItem();
                messageLabel.setText("You've selected: ");
                selectionLabel.setText("Assignment ID: " + selected.getAssignmentID() + "\nAverage Score: " + selected.getAverage());

                LOG.log(Level.INFO, "Display message with column selection using messageLabel. \n Course: {0}\tAverage Score: {1}", 
                        new Object[]{selected.getAssignmentID(), selected.getAverage()});
                
            }
           
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table, messageLabel,selectionLabel);
        
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        
        stage.setScene(scene);
        stage.show();
        LOG.log(Level.INFO, "Create User Interface and show stage");
        /* =====================================================
         *   Java 8 Stream to read data from file
         * =====================================================
        */
        // Open a fileDataStream from data file using try with resource
        try(Stream<String> fileDataStream = Files.lines(Paths.get("grade.data.txt"), Charset.defaultCharset())) {
            
            // Create fileDataStream of GradeEntry objects from text file
            Stream<GradeEntry> gradeStream = fileDataStream.parallel()
                    .map(g -> g.split(","))//split at commas
                    .map(gradeData -> new GradeEntry(gradeData[0], gradeData[1], gradeData[2], gradeData[3]));//map columns to array
            
            LOG.log(Level.INFO,"Create Stream<GradeEntry> gradeStream -> map data for (new GradeEntry)");
            
            // Create Map of assignment averages by processing gradeStream
            // Since processed in parallel stream, this will be unsorted.
            Map<String, Double> assignmentAverage = gradeStream.parallel()
                .collect( 
                    Collectors.groupingBy(
                            GradeEntry:: getAssignment, 
                            Collectors.averagingDouble(GradeEntry::getScore))
                );
            LOG.log(Level.INFO,"Map<String, Double> assignmentAverage -> collector for GradeEntry (terminal operation)");
            
            
            // Create ArrayList of AssignmentInfo objects as source for ObservableList
            ArrayList<AssignmentInfo> assignmentInfoList = new ArrayList<>();
            
            assignmentAverage.keySet().forEach(
                    (key) -> {assignmentInfoList.add(
                            new AssignmentInfo(key, assignmentAverage.get(key)));
            });
            LOG.log(Level.INFO,"ArrayList<AssignmentInfo> assignmentInfoList -> set key/value pairs in array");
                  
            // Convert to ObservableList to serve as data source for TableView, if there is
            // data to display
            if(assignmentInfoList.size() > 0) {
                
                // Create ObservableList from assignmentInfoList
                ObservableList<AssignmentInfo> courseAvg = FXCollections.observableArrayList(assignmentInfoList); 
                LOG.log(Level.INFO,"Cast ArrayList<AssignmentInfo> assignmentInfoList as an ObservableList<AssignmentInfo> courseAvg");
                
                //ObservableList to serve as data source for TableView
                table.setItems(courseAvg);
                LOG.log(Level.INFO,"Set items from ObservableList<AssignmentInfo> courseAvg to tableview");
            }
        }
        catch(IOException e){
            LOG.log(Level.SEVERE, "{0}{1}{2}", new Object[]{e.getMessage(), e.getClass()});
            System.exit(1);
            
        }

    }               
}    


