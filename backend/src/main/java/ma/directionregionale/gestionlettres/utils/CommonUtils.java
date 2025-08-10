package ma.directionregionale.gestionlettres.utils;

public class CommonUtils {
    public static Boolean isInt(String str){
        try{
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
