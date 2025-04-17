package MainMethods;

import ObjectElements.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import MainProgram.AssistMethods;

public class MainMethods {

    public void createHotel (ArrayList <Objects.Hotel> hotels, Scanner userInput){
        String name;
        char cPrefix;
        boolean isNotValid = false;
        do{
            System.out.print ("Enter Hotel name: ");
            name = userInput.nextLine();
            isNotValid = AssistMethods.isExistingHotel(hotels, name);
            if (isNotValid) {
                System.out.println ("Hotel name already exists, please enter a different name");
            }
        } while (isNotValid);
        do{            
            System.out.print ("Enter Hotel room prefix (e.g A, B, C): ");
            cPrefix = userInput.nextLine().charAt(0);
            isNotValid = AssistMethods.isValidPrefix(String.valueOf(cPrefix));
        } while (!isNotValid);

        hotels.add(new Objects.Hotel(name, cPrefix));

        System.out.println ("Hotel " + name + " has been created with room prefix " + cPrefix);
    }

    public void viewHotel (ArrayList <Objects.Hotel> hotels, Scanner userInput){

    }

    public void manageHotel (ArrayList <Objects.Hotel> hotels, Scanner userInput){

    }

    public void bookRoom (ArrayList <Objects.Hotel> hotels, Scanner userInput){

    }
}
