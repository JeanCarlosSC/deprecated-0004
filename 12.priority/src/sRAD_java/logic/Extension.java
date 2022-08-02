package sRAD_java.logic;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Extension {

    public static Boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public static Boolean isNumber(char character) {
        return character == '0' || character == '1' || character == '2' || character == '3' || character == '4' || character == '5'
                || character == '6' || character == '7' || character == '8' || character == '9';
    }

    public static Boolean isOperator(char character) {
        return character == '+' || character == '-' || character == '*' || character == '^' || character == '/';
    }

    public static String toCOP(int integer) {
        String str = "";
        int num = integer;
        int ajusteModulo = 0;

        if(num < 0) {
            str += "-";
            num *= -1;
            ajusteModulo++;
        }
        str += "$";
        String number = Integer.toString(num);

        while (!number.isEmpty()) {
            if(number.length()% 3 == 0 && str.length() != 1+ajusteModulo) {
                str += "."+number.charAt(0);
            }
            else {
                str += number.charAt(0);
            }
            number = number.substring(1);
        }

        return str;
    }

    public static String toCOP(double a) {
        return toCOP((int) a)+String.format("%.2f",a%1).substring((a>=0)?1:2);
    }

    public static String toPTG(double percentage) {
        return "%.2f".formatted(percentage*100) + "%";
    }

    public static Boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException numberFormat){
            return false;
        }
    }

    public static ArrayList<String> toArrayList(String str) {
        ArrayList<String> arrayList = new ArrayList();
        StringTokenizer tokens = new StringTokenizer(str);
        while(tokens.hasMoreTokens()) {
            arrayList.add(tokens.nextToken());
        }
        return arrayList;
    }

}
