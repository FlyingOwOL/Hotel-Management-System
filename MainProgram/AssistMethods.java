package MainProgram;

import ObjectElements.Objects;
import java.util.ArrayList;

public class AssistMethods {
    public static boolean isExistingHotel (ArrayList <Objects.Hotel> hotels, String name) {
        boolean isFound = false;
        for (int i = 0; i < hotels.size() && !isFound; i++) {
            if (hotels.get(i).getName().equals(name)) {
                isFound = true;
            }
        }
        return isFound;
    }
    public static boolean isValidPrefix (String prefix){
        boolean isValid = false;
        if (prefix.length() == 1) {
            char cPrefix = prefix.charAt(0);
            if (Character.isLetter(cPrefix)) {
                isValid = true;
            } else {
                System.out.println("Invalid prefix, please enter a letter");
            }
        } else {
            System.out.println("Only first character was taken");
        }
        return isValid;
    }
}
