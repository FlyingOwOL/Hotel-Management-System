package MainProgram;

import ObjectElements.Objects;
import MainMethods.MainMethods;
import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args) 
	{
        ArrayList <Objects.Hotel> hotels = new ArrayList <Objects.Hotel>();
        MainMethods methods = new MainMethods();       
	    Scanner userInput = new Scanner (System.in);
        int dMenuInput;
	    do{
            System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n%s",
            "Select option:",
            "1.Create Hotel",
            "2.View Hotel",
            "3.Manage Hotel",
            "4.Book Room",
            "5.Exit Program",
            "Number of your choice:");
            dMenuInput = userInput.nextInt();
            switch(dMenuInput)
            {
                case 1:
                    methods.createHotel(hotels, userInput);
                    break;
                case 2:
                    methods.viewHotel(hotels, userInput);
                    break;
                case 3:
                    methods.manageHotel(hotels, userInput);
                    break;
                case 4:
                    methods.bookRoom(hotels, userInput);
                    break;
                case 5:
                    System.out.println("Now exiting program");
                    break;
                default:
                    System.out.println("Out of range input");
                
            }
	    }while(dMenuInput!=5);
        userInput.close();
	}
}
