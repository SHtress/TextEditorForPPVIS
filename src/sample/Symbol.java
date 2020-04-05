package sample;

import javafx.scene.text.Font;

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

    Symbol(String Symbol, Font FontName){
        SymbolToRepresent=Symbol;
        SymbolFont=FontName;
    }

    Symbol(String Symbol){
        SymbolToRepresent=Symbol;
        SymbolFont=new Font("OldStyle", 12);
    }

    public Font getCurrentFont(){
        return SymbolFont;
    }

}
