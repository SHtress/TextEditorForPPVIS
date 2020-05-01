package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.Group;;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.canvas.*;

import java.awt.*;
import java.util.ArrayList;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        final ArrayList<Symbol> document=new ArrayList<Symbol>();
        caret karetka= new caret();
        Buffer buffer=new Buffer();

        Canvas canvas = new Canvas(410,800);
        GraphicsContext gr=canvas.getGraphicsContext2D();

        ToggleButton Boldbutton = new ToggleButton("Жирный");
        Boldbutton.setLayoutX(500);
        Boldbutton.setLayoutY(80);

        ToggleButton ItalicButton = new ToggleButton("Курсив");
        ItalicButton.setLayoutX(570);
        ItalicButton.setLayoutY(80);

        Label FontFamilyLabel=new Label("Шрифт:");
        FontFamilyLabel.setLayoutX(500);
        FontFamilyLabel.setLayoutY(55);


        ObservableList<String> FontFamilies = FXCollections.observableArrayList("Cambria", "TimesRoman", "Arial", "Comic Sans MS","Trebuchet MS");
        ComboBox<String> FontComboBox = new ComboBox<String>(FontFamilies);
        FontComboBox.setLayoutX(550);
        FontComboBox.setLayoutY(50);
        FontComboBox.setValue("Cambria");

        ObservableList<Double> FontSizes = FXCollections.observableArrayList(8.0,10.0,12.0,14.0);
        ComboBox<Double> FontSizeComboBox = new ComboBox<Double>(FontSizes);
        FontSizeComboBox.setLayoutX(690);
        FontSizeComboBox.setLayoutY(50);
        FontSizeComboBox.setValue(12.0);

//----------------------------------------------------------------------------------------------------------------------
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

//----------------------------------------------------------------------------------------------------------------------
        FontSizeComboBox.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                FontSizeComboBox.setFocusTraversable(false);
            }
        });
        FontSizeComboBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FontSizeComboBox.setFocusTraversable(true);
            }
        });

//----------------------------------------------------------------------------------------------------------------------
        Boldbutton.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
               Boldbutton.setFocusTraversable(false);
            }
        });
        FontSizeComboBox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               Boldbutton.setFocusTraversable(true);
            }
        });

//----------------------------------------------------------------------------------------------------------------------
        ItalicButton.addEventFilter(KeyEvent.ANY, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                ItalicButton.setFocusTraversable(false);
            }
        });
        ItalicButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               ItalicButton.setFocusTraversable(true);
            }
        });

//----------------------------------------------------------------------------------------------------------------------
//        primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if(mouseEvent.getX()>50 && mouseEvent.getX()<460 && mouseEvent.getY()>25 && mouseEvent.getY()<525) {
//                    karetka.positionColumn=(int)(mouseEvent.getX()-50)/7;
//                    karetka.positionRow=(int)(mouseEvent.getY()-25)/10;
//                    System.out.println(karetka.positionRow);
//                    System.out.println(mouseEvent.getY()-25);
//                }
//                Canvas canvas = new Canvas(410,800);
//                GraphicsContext gr=canvas.getGraphicsContext2D();
//                gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);
//                int j=0,k=1;
//                for(int i=0;i<document.size();i++,k++) {
//                    gr.setFont(document.get(i).SymbolFont);
//
//                    if( k*7>=393) { j++;  k=1; }
//                    gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
//                }
//                System.out.println(karetka.positionRow*10);
//
//
//                ScrollPane scrollPane = new ScrollPane(canvas);
//                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//                scrollPane.setPrefViewportHeight(500);
//                scrollPane.setPrefViewportWidth(410);
//                scrollPane.setPannable(false);
//              //  scrollPane.setVvalue((karetka.positionRow-1)*0.02);
//
//                StackPane textpane = new StackPane(scrollPane);
//
//                textpane.setLayoutX(50);
//                textpane.setLayoutY(25);
//
//                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
//                Group group =new Group(textpane,FontComboBox,FontFamilyLabel,FontSizeComboBox,Boldbutton,ItalicButton);
//                Scene scene = new Scene(group);
//                primaryStage.setScene(scene);
//            }
//        });
// ----------------------------------------------------------------------------------------------------------------------
        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
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

                if(key.equals(KeyCode.CONTROL.toString())) buffer.StartPosition=(karetka.positionRow-1)*56+karetka.positionColumn-1;


            //    for(int i=0;i<buffer.size();i++) System.out.println(buffer.get(i).SymbolToRepresent);

                if(key.equals(KeyCode.RIGHT.toString())) if(karetka.positionColumn<56 && karetka.positionRow<=document.size()/56 ) karetka.positionColumn++;
                else if(karetka.positionRow>document.size()/56 && karetka.positionColumn<=document.size()%56) karetka.positionColumn++;
                else if(karetka.positionColumn==56 && karetka.positionRow<=document.size()/56) {karetka.positionRow++; karetka.positionColumn=1;}
                if(key.equals(KeyCode.LEFT.toString())) if(karetka.positionColumn>1) karetka.positionColumn--; else
                    if(karetka.positionColumn==1 && karetka.positionRow>1) {karetka.positionRow--; karetka.positionColumn=56; }
                if(key.equals(KeyCode.UP.toString())) if(karetka.positionRow>1) karetka.positionRow--;
                if(key.equals(KeyCode.DOWN.toString())) {if(karetka.positionRow<=document.size()/56 && document.size()>=karetka.positionColumn+ karetka.positionRow*56-1) karetka.positionRow++; }


                if(event.isControlDown()) buffer.EndPosition=(karetka.positionRow-1)*56+karetka.positionColumn-1;

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

                    if((buffer.StartPosition<=i && i<=buffer.EndPosition && event.isControlDown()) || (buffer.StartPosition>=i && i>=buffer.EndPosition && event.isControlDown())) gr.setFill(Color.BLUE); else gr.setFill(Color.BLACK);
                    gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                }


                if(event.isControlDown() && key.equals(KeyCode.C.toString())) {
                    if(buffer.EndPosition>document.size()-1) buffer.EndPosition=document.size()-1;
                    if(buffer.StartPosition>document.size()-1) buffer.StartPosition=document.size()-1;
                    buffer.Content.clear();

                    if (buffer.EndPosition > buffer.StartPosition)
                        for (int i = buffer.StartPosition; i <= buffer.EndPosition; i++){
                            buffer.Content.add(document.get(i));
                            //System.out.println(document.get(i).SymbolToRepresent);
                        }

                    if (buffer.EndPosition < buffer.StartPosition)
                        for (int i = buffer.EndPosition; i <= buffer.StartPosition; i++) {
                            buffer.Content.add(document.get(i));
                            //System.out.println(document.get(i).SymbolToRepresent);
                        }
                }

                if(event.isControlDown() && key.equals(KeyCode.V.toString()))
                    for(int i=0;i<buffer.Content.size();i++)
                        document.add((karetka.positionRow-1)*56+karetka.positionColumn-1,buffer.Content.get(buffer.Content.size()-i-1));


                ScrollPane scrollPane = new ScrollPane(canvas);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setPrefViewportHeight(500);
                scrollPane.setPrefViewportWidth(410);
                scrollPane.setPannable(false);


                scrollPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        scrollPane.setFocusTraversable(true);
                        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                        if(mouseEvent.getSceneX()>50 && mouseEvent.getSceneX()<460 && mouseEvent.getSceneY()>25 && mouseEvent.getSceneY()<525) {
                            karetka.positionColumn = (int) (mouseEvent.getSceneX() - 50) / 7;
                            karetka.positionRow = (int) (mouseEvent.getSceneY() - 25) / 10+1;

                            if(karetka.positionColumn+(karetka.positionRow-1)*56>document.size()) {
                                karetka.positionRow=document.size()/56+1;
                                karetka.positionColumn=document.size()%56+1;}

                            int j=0,k=1;
                            for(int i=0;i<document.size();i++,k++) {
                                gr.setFont(document.get(i).SymbolFont);

                                if( k*7>=393) { j++;  k=1; }
                                gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                            }
                            gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);
                            canvas.requestFocus();
                        }
                    }
                });

                scrollPane.setVvalue((karetka.positionRow-1)*0.02);

                gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);


                StackPane textpane = new StackPane(scrollPane);

                textpane.setLayoutX(50);
                textpane.setLayoutY(25);

                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                Group group =new Group(textpane,FontComboBox,FontFamilyLabel,FontSizeComboBox,Boldbutton,ItalicButton);
                Scene scene = new Scene(group);
                primaryStage.setScene(scene);

            }
        });

//----------------------------------------------------------------------------------------------------------------------
        primaryStage.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                Font CurrentFont=new Font(FontComboBox.getValue(),FontSizeComboBox.getValue());

                if(Boldbutton.isSelected() && ItalicButton.isSelected()) CurrentFont=CurrentFont.font(FontComboBox.getValue(),FontWeight.BOLD,FontPosture.ITALIC,FontSizeComboBox.getValue());
                if(!Boldbutton.isSelected() && ItalicButton.isSelected()) CurrentFont=CurrentFont.font(FontComboBox.getValue(),FontWeight.NORMAL,FontPosture.ITALIC,FontSizeComboBox.getValue());
                if(Boldbutton.isSelected() && !ItalicButton.isSelected()) CurrentFont=CurrentFont.font(FontComboBox.getValue(),FontWeight.BOLD,FontPosture.REGULAR,FontSizeComboBox.getValue());
                if(!Boldbutton.isSelected() && !ItalicButton.isSelected()) CurrentFont=CurrentFont.font(FontComboBox.getValue(),FontWeight.NORMAL,FontPosture.REGULAR,FontSizeComboBox.getValue());


                String key = event.getCharacter() ;
                if(!key.toString().equals("\b") && !event.isControlDown()) {
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


                ScrollPane scrollPane = new ScrollPane(canvas);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setPrefViewportHeight(500);
                scrollPane.setPrefViewportWidth(410);
                scrollPane.setPannable(false);
                scrollPane.setVvalue((karetka.positionRow-1)*0.02);

                scrollPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        scrollPane.setFocusTraversable(true);
                        gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                        if(mouseEvent.getSceneX()>50 && mouseEvent.getSceneX()<460 && mouseEvent.getSceneY()>25 && mouseEvent.getSceneY()<525) {
                            karetka.positionColumn = (int) (mouseEvent.getSceneX() - 50) / 7;
                            karetka.positionRow = (int) ((mouseEvent.getSceneY() - 25) / 10 +1 );
                            if(karetka.positionColumn+(karetka.positionRow-1)*56>document.size()) {
                                karetka.positionRow=document.size()/56+1;
                                karetka.positionColumn=document.size()%56+1;}

                            int j=0,k=1;
                            for(int i=0;i<document.size();i++,k++) {
                                gr.setFont(document.get(i).SymbolFont);

                                if( k*7>=393) { j++;  k=1; }
                                gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                            }
                            gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);
                            if(scrollPane.isFocused()) System.out.println("NO");
                        }
                    }
                });



                gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);
                StackPane textpane = new StackPane(scrollPane);

                textpane.setLayoutX(50);
                textpane.setLayoutY(25);

                textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                Group group =new Group(textpane,FontComboBox,FontFamilyLabel,FontSizeComboBox,Boldbutton,ItalicButton);
                Scene scene = new Scene(group);
                primaryStage.setScene(scene);

            }
        });

//----------------------------------------------------------------------------------------------------------------------

        ScrollPane scrollPane = new ScrollPane(canvas);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefViewportHeight(500);
        scrollPane.setPrefViewportWidth(410);
        scrollPane.setPannable(false);
        scrollPane.setVvalue(0.0);

        scrollPane.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                scrollPane.setFocusTraversable(true);
                gr.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                if(mouseEvent.getSceneX()>50 && mouseEvent.getSceneX()<460 && mouseEvent.getSceneY()>25 && mouseEvent.getSceneY()<525) {
                    karetka.positionColumn = (int) (mouseEvent.getSceneX() - 50) / 7;
                    karetka.positionRow = (int) ((mouseEvent.getSceneY() - 25) / 10 );
                    if(karetka.positionColumn+(karetka.positionRow-1)*56>document.size()) {
                        karetka.positionRow=document.size()/56+1;
                        karetka.positionColumn=document.size()%56+1;}

                    int j=0,k=1;
                    for(int i=0;i<document.size();i++,k++) {
                        gr.setFont(document.get(i).SymbolFont);

                        if( k*7>=393) { j++;  k=1; }
                        gr.fillText(document.get(i).SymbolToRepresent, k * 7, (j+1)*10);
                    }
                    gr.fillText("_",karetka.positionColumn*7,karetka.positionRow*10);
                    if(scrollPane.isFocused()) System.out.println("NO");
                }
            }
        });

        StackPane textpane = new StackPane(scrollPane);
        textpane.setLayoutX(50);
        textpane.setLayoutY(25);

        textpane.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        Group group =new Group(textpane,FontComboBox,FontFamilyLabel,FontSizeComboBox,Boldbutton,ItalicButton);
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

