package MainProgram;

import ObjectElements.Objects;
import java.util.ArrayList;
import java.util.Scanner;

public class AssistMethods {
    // Helper method to convert MM/DD/YYYY to an integer YYYYMMDD for easy comparison
    public static int convertToComparableDate(String date) {
        String[] parts = date.split("/");
        int month = Integer.parseInt(parts[0]);
        int day = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return year * 10000 + month * 100 + day;
    }
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

    public static void hotelSelection (Objects.Hotel hotel, Scanner userInput ){
        int dChoice = 0;
        do{
            System.out.printf ("Hotel %s menu:\n", hotel.getName());
            System.out.printf ("%s\n%s\n%s\n%s\n%s",
            "1. Total number of available rooms for selected date",
            "2. View room information",
            "3. View Reservation information",
            "4. return to view hotel menu",
            "Your choice: ");
            dChoice = userInput.nextInt();
            userInput.nextLine(); // Consume the newline character left by nextInt()  
            switch (dChoice){
                case 1:
                    System.out.print("Enter start date (MM/DD/YYYY): ");
                    String checkInDate = userInput.nextLine();
                    System.out.print("Enter end date PUTANGINAMO (MM/DD/YYYY): ");
                    String checkOutDate = userInput.nextLine();
                    System.out.printf("Total number of available rooms: %d\n", hotel.getTotaldAvailableRooms());
                    System.out.printf("Total number of booked rooms: %d\n", hotel.dateBookedRooms(checkInDate, checkOutDate));
                    break;
                case 2:
                    System.out.printf ("Hotel %s room:\n", hotel.getName());
                    for (int i = 0; i < hotel.getTotalRooms(); i++) {
                        System.out.printf ("%d. %s\n", i + 1, hotel.getRoomNumber(i));
                    }
                    System.out.print("Enter room number (e.g., A001): ");
                    String roomNumber = userInput.nextLine();
                    System.out.print("Enter month (e.g., January): ");
                    String month = userInput.nextLine(); //Spelled out
                    hotel.viewRoomInfo(roomNumber, month);
                    break;
                case 3:
                    //hotel.viewReservationInfo(userInput);
                    break;
                case 4:
                    System.out.println("Returning to view hotel menu");
                    break;
                default:
                    System.out.println("Out of range input");
            }
            System.out.print("Press Enter to continue...");
        } while (dChoice != 4);
    }
}
