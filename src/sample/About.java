package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About {
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("About");
        Image img= new Image ("icon.png");
        Hyperlink link = new Hyperlink ();
        link.setText ("Gabriel40670@outlook.com");
        TextArea area = new TextArea ();
        area.setText ("CGPA CAlCULATOR version 1.0.1 ");
        area.appendText ("\n\nCopywrite (c) 2019");
        area.appendText ("\nDeveloped by Favour Gabriel");
        area.appendText ("\nThis is a free to use software for any educational field in tertiary institution" +
                "\n\nAll rights regarding this software is reserved");
        area.setEditable (false);
        area.setMaxSize (400,180);
        area.setPadding(new Insets (10,10,10,10));
        area.setFont(new Font ("Italics",13));

        Pane pane= new Pane();
        pane.getChildren().add(area);

        Scene scene = new Scene(pane);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.getIcons ().add (img);
        primaryStage.show();
    }
}
