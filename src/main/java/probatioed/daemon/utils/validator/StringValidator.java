package probatioed.daemon.utils.validator;

public class StringValidator {

    public static boolean validateTitle(String title){
        if(title.contains(" ")){
            return false;
        }
        return true;
    }
}
