package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

import javax.print.Doc;
import java.awt.*;
import java.security.Key;
import java.util.ArrayList;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        final ArrayList<Symbol> document=new ArrayList<Symbol>();
        caret karetka= new caret();
        Label FontFamilyLabel=new Label("Шрифт:");
        FontFamilyLabel.setLayoutX(500);
        FontFamilyLabel.setLayoutY(55);

        ObservableList<String> FontFamilies = FXCollections.observableArrayList("Cambria", "TimesRoman", "Arial", "Comic Sans MS","Trebuchet MS");
        ComboBox<String> FontComboBox = new ComboBox<String>(FontFamilies);
        FontComboBox.setLayoutX(550);
        FontComboBox.setLayoutY(50);
        FontComboBox.setValue("TimesRoman");


        FontComboBox.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
               FontComboBox.setFocusTraversable(false);
            }
    });
        FontComboBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FontComboBox.setFocusTraversable(true);
            }
        });


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Canvas canvas = new Canvas(410,800);
                GraphicsContext gr=canvas.getGraphicsContext2D();
                String key = event.getCode().toString();
                if(key.equals(KeyCode.BACK_SPACE.toString()) && document.size()>0 && !(karetka.positionRow==1 && karetka.positionColumn==1)){
                    document.remove(karetka.positionColumn-2+(karetka.positionRow-1)*56);
                    if(karetka.positionColumn>1 ) karetka.positionColumn--; else {
                        if(karetka.positionRow>1) karetka.positionRow--; karetka.positionColumn=56;
                    }

                }

                if(key.equals(KeyCode.ENTER.toString())) {
                    for(int j = karetka.positionColumn; j<56; j++) {
                        document.add(karetka.positionColumn-1+(karetka.positionRow-1)*56,new Symbol("\n"));
                    }
                    karetka.positionColumn = 0;
                    karetka.positionRow++;
                }

                if(key.equals(KeyCode.RIGHT.toString())) if(karetka.positionColumn<56 && karetka.positionRow<=document.size()/56 ) karetka.positionColumn++;
                else if(karetka.positionRow>document.size()/56 && karetka.positionColumn<=document.size()%56) karetka.positionColumn++;
                if(key.equals(KeyCode.LEFT.toString())) if(karetka.positionColumn>1)karetka.positionColumn--;
                if(key.equals(KeyCode.UP.toString())) if(karetka.positionRow>1) karetka.positionRow--;
                if(key.equals(KeyCode.DOWN.toString())) {if(karetka.positionRow<=document.size()/56 && document.size()>=karetka.positionColumn+ karetka.positionRow*56-1) karetka.positionRow++; }

                //System.out.println(key);
                int j=0,k=1;
                for(int i=0;i<document.size();i++,k++) {
                    gr.setFont(document.get(i).SymbolFont);
                    if( k*7>=393) {
                       if(document.get(i-3).SymbolToRepresent.equals("\n") && key.equals(KeyCode.BACK_SPACE.toString()) && i==karetka.positionRow*56 && karetka.positionColumn!=1 ){
                        document.add(i-2,new Symbol("\n"));
                        }
                        if(document.get(i-3).SymbolToRepresent.equals("\n") && key.equals(KeyCode.BACK_SPACE.toString()) && i==karetka.positionRow*56 && karetka.positionColumn==55 ) {
                            document.remove(i-3);
                        }
                    j++;  k=1; ;}
                    gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                }

                gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);

                ScrollPane scrollPane = new ScrollPane(canvas);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setPrefViewportHeight(500);
                scrollPane.setPrefViewportWidth(410);
                scrollPane.setPannable(false);
                scrollPane.setVvalue((karetka.positionRow-1)*0.02);


                StackPane textpane = new StackPane(scrollPane);

                textpane.setLayoutX(50);
                textpane.setLayoutY(25);

                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                Group group =new Group(textpane,FontComboBox,FontFamilyLabel);
                Scene scene = new Scene(group);
                primaryStage.setScene(scene);

            }
        });


        primaryStage.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Canvas canvas = new Canvas(410,800);
                GraphicsContext gr=canvas.getGraphicsContext2D();
                Font CurrentFont=new Font(FontComboBox.getValue(),12);

                CurrentFont.font(FontComboBox.getValue(),FontWeight.BOLD, FontPosture.REGULAR,12);

                String key = event.getCharacter() ;
                if(!key.toString().equals("\b")) {
                    Symbol KeySymbol=new Symbol(key.toString(),CurrentFont);
                    document.add(karetka.positionColumn-1+(karetka.positionRow-1)*56,KeySymbol);
                    if(karetka.positionColumn<56) karetka.positionColumn++;
                    else {karetka.positionColumn=1; karetka.positionRow++;  }
                }

                int j=0,k=1;
                for(int i=0;i<document.size();i++,k++) {
                        gr.setFont(document.get(i).SymbolFont);

                        if( k*7>=393) { if(document.get(i-1).SymbolToRepresent.equals("\n") && i==karetka.positionRow*56 && !key.toString().equals("\b")) document.remove(i);
                            j++;  k=1; }
                        gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                    }
                gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);

                ScrollPane scrollPane = new ScrollPane(canvas);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setPrefViewportHeight(500);
                scrollPane.setPrefViewportWidth(410);
                scrollPane.setPannable(false);
                scrollPane.setVvalue((karetka.positionRow-1)*0.02);


                StackPane textpane = new StackPane(scrollPane);
               // StackPane textpane = new StackPane(canvas);

//                textpane.setMaxSize(400,500);
//                textpane.setPrefSize(400,500);
//                textpane.setMinSize(400,500);
                textpane.setLayoutX(50);
                textpane.setLayoutY(25);

                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                Group group =new Group(textpane,FontComboBox,FontFamilyLabel);
                Scene scene = new Scene(group);
                primaryStage.setScene(scene);

            }
        });


        Canvas canvas = new Canvas(410,800);
        GraphicsContext gr=canvas.getGraphicsContext2D();
//        int j=0,k=1;
//        for(int i=0;i<document.size();i++,k++) {
//            gr.setFont(document.get(i).SymbolFont);
//            if(document.get(i).SymbolToRepresent.equals("\n")) {j++; k=-1; }
//            gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
//        }

        ScrollPane scrollPane = new ScrollPane(canvas);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.setPrefViewportWidth(410);
        scrollPane.setPannable(false);
        scrollPane.setVvalue(0.0);


        StackPane textpane = new StackPane(scrollPane);
        textpane.setLayoutX(50);
        textpane.setLayoutY(25);

        textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        Group group =new Group(textpane,FontComboBox,FontFamilyLabel);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();


    }

    public static void main(String[] args) {

        launch(args);
    }
}

