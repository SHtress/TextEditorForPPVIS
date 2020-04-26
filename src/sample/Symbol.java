package sample;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Symbol{
    String SymbolToRepresent;
    Font SymbolFont;
    int Space;
    String FontFamilyName;
    double FontSize;


    public void setFontFamily(String FontFamilyName){
        FontFamilyName=FontFamilyName;
    }

    public void setFontSize(double NewSize){
        FontSize=NewSize;
    }

//    Symbol(String Symbol, Font FontName){
//        SymbolToRepresent=Symbol;
//        SymbolFont=FontName;
//    }

    Symbol(String Symbol,Font NewFont){
        SymbolToRepresent=Symbol;
        SymbolFont=NewFont;
//        SymbolFont=Font.font(FontFamilyName,FontWeight.BOLD, FontPosture.REGULAR,12);

    }

    Symbol(String Symbol){
        SymbolToRepresent=Symbol;
        SymbolFont=new Font("Cambria",12);
    }

    public Font getCurrentFont(){
        return SymbolFont;
    }

}
