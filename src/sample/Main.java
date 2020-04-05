package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

import java.awt.*;
import java.security.Key;
import java.util.ArrayList;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        final ArrayList<Symbol> document=new ArrayList<Symbol>();
        caret karetka= new caret();


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Canvas canvas = new Canvas(400,500);
                GraphicsContext gr=canvas.getGraphicsContext2D();

                String key = event.getCode().toString();
                if(key.equals(KeyCode.BACK_SPACE.toString()) && document.size()>0){
                    document.remove(document.size()-1);
                }

                if(key.equals(KeyCode.ENTER.toString())) document.add(new Symbol("\n"));
                if(key.equals(KeyCode.RIGHT.toString())) if(karetka.positionColumn*7<=393) karetka.positionColumn++;
                if(key.equals(KeyCode.LEFT.toString())) if(karetka.positionColumn>0)karetka.positionColumn--;
                //System.out.println(key);

                int j=0,k=1;
                for(int i=0;i<document.size();i++,k++) {
                    gr.setFont(document.get(i).SymbolFont);
                    if(document.get(i).SymbolToRepresent.equals("\n") || k*7>=393) {j++;  k=-1; }
                    gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                }

                gr.fillText("_",karetka.positionColumn*7,(j+1)*10);

                StackPane textpane = new StackPane(canvas);

                textpane.setMaxSize(400,500);
                textpane.setPrefSize(400,500);
                textpane.setMinSize(400,500);
                textpane.setLayoutX(150);
                textpane.setLayoutY(25);

                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                Group group =new Group(textpane);
                Scene scene = new Scene(group);
                primaryStage.setScene(scene);

            }
        });


        primaryStage.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                Canvas canvas = new Canvas(400,500);
                GraphicsContext gr=canvas.getGraphicsContext2D();

                String key = event.getCharacter() ;
                if(!key.toString().equals("\b")) {
                    Symbol KeySymbol=new Symbol(key.toString());
                    document.add(karetka.positionColumn,KeySymbol);
                }
                //for(int i=0;i<10000;i++){}
                int j=0,k=1;
                for(int i=0;i<document.size();i++,k++) {
                        gr.setFont(document.get(i).SymbolFont);
                        if(document.get(i).SymbolToRepresent.equals("\n") || k*7>=393) {j++;  k=-1; }
                        gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                    }

                gr.fillText("_",karetka.positionColumn*7,(j+1)*10);
             


                StackPane textpane = new StackPane(canvas);

                textpane.setMaxSize(400,500);
                textpane.setPrefSize(400,500);
                textpane.setMinSize(400,500);
                textpane.setLayoutX(150);
                textpane.setLayoutY(25);

                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                Group group =new Group(textpane);
                Scene scene = new Scene(group);
                primaryStage.setScene(scene);

            }
        });



        Canvas canvas = new Canvas(400,500);
        GraphicsContext gr=canvas.getGraphicsContext2D();
        int j=0,k=1;
        for(int i=0;i<document.size();i++,k++) {
            gr.setFont(document.get(i).SymbolFont);
            if(document.get(i).SymbolToRepresent.equals("\n")) {j++; k=-1; }
            gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
        }

        StackPane textpane = new StackPane(canvas);

        textpane.setMaxSize(400,500);
        textpane.setPrefSize(400,500);
        textpane.setMinSize(400,500);
        textpane.setLayoutX(150);
        textpane.setLayoutY(25);

        textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        Group group =new Group(textpane);
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

