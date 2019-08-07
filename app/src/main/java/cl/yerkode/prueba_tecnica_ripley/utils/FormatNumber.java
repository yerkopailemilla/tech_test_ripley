package cl.yerkode.prueba_tecnica_ripley.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class FormatNumber {


    public static String toCLP(int amount){
        NumberFormat formatter = new DecimalFormat("#,###");
        double myNumber = amount;
        return  "$" + formatter.format(myNumber);
    }
}
