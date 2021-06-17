package sample;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    public Button calc;

    @FXML
    private TextField subject;
    @FXML
    private TextField score;
    @FXML
    private TextField creditload;

    @FXML
    private TableView<Marks> table;
    @FXML
    private TextField cgpa;
    @FXML
    private TableColumn<Marks,String> subj;
    @FXML
    private TableColumn<Marks,Integer> cre;
    @FXML
    private TableColumn<Marks,Double> sc;
    @FXML
    private TableColumn<Marks,Integer> grad;

    @FXML
    private StackPane root;

    private int credit=0;
    private double scre=0;
    private int marker;
    private String grade;
    private Formatter x=null;
    private Date date;
    private int count=0;

    public Controller() {
        Path path= Paths.get(System.getProperty("user.home")+"/documents/CGPA CALCULATOR/");
        try {
            if (!path.toFile ().exists ())
                Files.createDirectory(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        date = new Date();
        openingnewfile();
    }

    public void add(){
        if (score.getText ().equals ("")||creditload.getText ().equals ("")||subject.getText ().equals ("")){
            Alert alert= new Alert (Alert.AlertType.WARNING);
            alert.setHeaderText (null);
            alert.initStyle (StageStyle.UTILITY);
            alert.setContentText ("Apply Inputs to all Fields!");
            alert.showAndWait ();
        }else {
            //reads score
            check (score);

            //checks grade
            marker (marker);

            //view
            view ( );

            //writing to file
            x.format ("%s%s%s%s%n", "Subject:" + subject.getText ( ), "\tScore:" + score.getText ( ), "\tCredit:" + creditload.getText ( ),
                    "\tGrade:" + grade);

            //clear all text field
            score.clear ( );
            creditload.clear ( );
            subject.clear ( );
        }
    }

    private void view() {
        //defining parameters from Marks class to tablecolumns cell factory
        subj.setCellValueFactory(new PropertyValueFactory<> ("subject"));
        cre.setCellValueFactory(new PropertyValueFactory<>("credit"));
        sc.setCellValueFactory(new PropertyValueFactory<>("score"));
        grad.setCellValueFactory(new PropertyValueFactory<>("grade"));

        Marks marks = new Marks(subject.getText(),Integer.parseInt(creditload.getText()),
                Double.parseDouble(score.getText()),grade);
        table.getItems().add(marks);
    }

    public void calculate(){
        //resets
        if (scre>0||credit>0){
            scre=0;
            credit=0;
        }
        //credit load value
        int sc;
        TableColumn col =table.getColumns ().get (2);
        List<Integer> list=new ArrayList<> ();
        for (Marks marks:table.getItems ()){
            list.add ((Integer) col.getCellObservableValue (marks).getValue ());
        }
        for (Integer aList : list) {
            sc = aList;
            //total credit load
            credit += sc;
        }

        //grade value
        int grad=0;
        int val = 0;
        TableColumn cel=table.getColumns ().get (3);
        for (Marks marks:table.getItems ()){
            //value for credit
            int value= (int) col.getCellObservableValue (marks).getValue ();
            //value for grade
            String s = (String) cel.getCellObservableValue (marks).getValue ();
            switch (s){
                case "A":
                    grad=5;
                    break;
                case "B":
                    grad=4;
                    break;
                case "C":
                    grad=3;
                    break;
                case "D":
                    grad=2;
                    break;
                case "E":
                    grad=1;
                    break;
                case "F":
                    grad=0;
                    break;
            }
            //performing arithemetic
            val=value*grad;

            //total score inside the loop
            scre+=val;
        }
        double result=scre/credit;
        cgpa.setText(String.format ("%.2f",result));
    }

    private void check(TextField s){
        double st= Double.parseDouble(s.getText());
        if (st>=70&&st<=100){
            marker=5;
        }else if (st>=60&&st<=100){
            marker=4;
        }
        else if (st>=50&&st<=100){
            marker=3;
        }else if (st>=45&&st<=100){
            marker=2;
        }else if (st>=40&&st<=100){
            marker=1;
        }else if (st>=0&&st<=100){
            marker=0;
        }
    }

    public void save(){
        Alert alert= new Alert (Alert.AlertType.INFORMATION);
        alert.initStyle (StageStyle.UTILITY);
        alert.setHeaderText (null);
        x.format("%n%s","CGPA: "+cgpa.getText());
        x.close();
        alert.setContentText ("Saved!");
        alert.showAndWait ();
        create ();
    }

    public void ClearALL(){
        table.getItems ().clear ();
    }

    private void marker(int s){
        if (s==5){
            grade="A";
        }else if (s==4){
            grade="B";
        }else if (s==3){
            grade="C";
        }else if (s==2){
            grade="D";
        }else if (s==1){
            grade="E";
        }else if (s==0){
            grade="F";
        }
    }

    public void remove(){
        ObservableList<Marks> selecteditems, allitems;

        allitems=table.getItems();
        selecteditems= table.getSelectionModel().getSelectedItems();
        selecteditems.forEach(allitems::remove);
    }
    private void openingnewfile(){
      if (Main.isSplashedLoaded){
            try {
                x= new Formatter(System.getProperty("user.home")+"/documents/CGPA CALCULATOR/"+date.getTime()+".txt");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private void loadsplashscreen(){
        Main.isSplashedLoaded=true;
        try {
            AnchorPane pane= FXMLLoader.load (getClass ().getResource ("splash.fxml"));
            root.getChildren ().setAll (pane);

            FadeTransition fadin = new FadeTransition (Duration.seconds (3),pane);
            fadin.setFromValue (0);
            fadin.setToValue (1);
            fadin.setCycleCount (1);

            FadeTransition fadout = new FadeTransition (Duration.seconds (3),pane);
            fadout.setFromValue (1);
            fadout.setToValue (0);
            fadout.setCycleCount (1);

            fadin.play ();

            fadin.setOnFinished (event -> {
                fadout.play ();
            });
            fadout.setOnFinished (event -> {
                try {
                    StackPane pane1= FXMLLoader.load (getClass ().getResource ("sample.fxml"));
                    root.getChildren ().setAll (pane1);
                } catch (IOException e) {
                    e.printStackTrace ( );
                }
            });

        } catch (IOException e) {
            e.printStackTrace ( );
        }
    }

    public void create(){
        count++;
        subject.clear();
        creditload.clear();
        cgpa.clear();
        score.clear();
        table.getItems().clear();
        if (x!=null){
            x.close ();
        }
        try {
            x= new Formatter (System.getProperty("user.home")+"/documents/CGPA CALCULATOR/"+date.getTime()+""+count+".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace ( );
        }
    }
    public void about(){
        About a = new About();
        Stage stage = new Stage();
        try {
            a.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Exit(){
        System.exit(-1);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!Main.isSplashedLoaded) {
            loadsplashscreen ( );
        }
    }
}