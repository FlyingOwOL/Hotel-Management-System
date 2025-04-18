package ObjectElements;

import MainProgram.AssistMethods;

public class Objects 
{
    public static class constants   //definitions of constants
    {
        public static final int MAX_ROOMS = 50;
        public static final int MAX_RESERVATIONS = 50;
    }

    public static class Hotel
    {
        private String name;
        private room[] rooms = new room[constants.MAX_ROOMS];
        private int dRooms = 1;
        public double basePrice = 1299;
        private reservation[] reservations = new reservation[constants.MAX_RESERVATIONS];
        private int dReservations = 0;
        private double estimateEarning = 0.0;
        private room[] availableRooms = new room[constants.MAX_ROOMS];
        private int dAvailableRooms = 0;
        private room[] bookedRooms = new room[constants.MAX_ROOMS];
        private int dBookedRooms = 0;

        private char roomPrefix = '\n';

        public Hotel(String name, char cPrefix) {
            this.name = name;
            this.roomPrefix = cPrefix;
            this.rooms[0] = new room(dRooms, cPrefix, basePrice);
            this.reservations[0] = null;
            this.dAvailableRooms = 1;
        }
        public double getEstimateEarning () {
            return estimateEarning;
        }
        public String getName () {
            return name;
        }
        public void changeName (String newName){
            this.name = newName;
        }
        public double getBasePrice () {
            return basePrice;
        }
        public void changeBasePrice (double newBasePrice) {
            this.basePrice = newBasePrice;
        }
        public int getTotaldReservations () {
            return dReservations;
        }
        public String viewReservation (int dIndex){
            String reservation = String.format("Reservation for %s at room %s",
            reservations[dIndex].getName(),
            reservations[dIndex].getRoomNumber());
            return reservation;
        }
        public void addReservation (String name, String roomNumber, String checkInDate, String checkOutDate) {
            if (dReservations < constants.MAX_RESERVATIONS) {
                dReservations++;
                int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
                reservations[dIndex] = new reservation(name, roomNumber, checkInDate, checkOutDate, basePrice);
                rooms[dIndex].addReservation(name, checkInDate, checkOutDate, basePrice);
                System.out.printf ("Reservation for %s has been added\n", name);
                this.estimateEarning += rooms[dIndex].getReservationTotalAmount();
                this.bookedRooms[dIndex] = rooms[dIndex];
                this.dBookedRooms++;
                this.availableRooms[dIndex] = null;
            } else {
                System.out.printf("Maximum number of reservations reached for %s\n", name);
            }
        }
        public void removeReservation (String name, String roomNumber) {
            if (dReservations > 0) {
                dReservations--;
                int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
                reservations[dIndex] = null;
                System.out.printf ("Reservation for %s has been removed\n", name);
                this.bookedRooms[dIndex] = null;
                this.dBookedRooms--;
                this.availableRooms[dIndex] = rooms[dIndex];
                this.dAvailableRooms++;
            } else {
                System.out.printf("Room %s or reservation for %s not found\n", roomNumber, name);
            }
        }
        public int dateBookedRooms(String checkInDate, String checkOutDate) { // format MM/DD/YYYY
            int bookedCount = 0;
        
            for (int i = 0; i < dRooms; i++) {
                if (!rooms[i].isAvailable()) {
                    String roomCheckIn = rooms[i].getReservationCheckInDate();
                    String roomCheckOut = rooms[i].getReservationCheckOutDate();
        
                    // Convert all dates to comparable values (e.g. YYYYMMDD)
                    int paramStart = AssistMethods.convertToComparableDate(checkInDate);
                    int paramEnd = AssistMethods.convertToComparableDate(checkOutDate);
                    int roomStart = AssistMethods.convertToComparableDate(roomCheckIn);
                    int roomEnd = AssistMethods.convertToComparableDate(roomCheckOut);
        
                    // Check if there is any overlap between the room's booking and the given date range
                    if (roomEnd >= paramStart && roomStart <= paramEnd) {
                        bookedCount++;
                    }
                }
            }
            return bookedCount;
        }
        public int getTotaldAvailableRooms () {
            return dAvailableRooms;
        }
        public int getTotaldBookedRooms () {
            return dBookedRooms;
        }
        public void viewRoomInfo (String roomNumber, String Month) { // Month is spelled out
            int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
            String availability = rooms[dIndex].isAvailable() ? "available this " + Month : "not available on ";
            String reservedDate = rooms[dIndex].getReservationCheckInDate() + " - " + rooms[dIndex].getReservationCheckOutDate();
            String reservationDate = rooms[dIndex].isAvailable() ? " " : reservedDate;
            System.out.printf ("Room %s is %s %s\n", 
            rooms[dIndex].getRoomNumber(), 
            availability, 
            reservationDate);
            System.out.printf ("Price per night: ₱%.2f\n", basePrice);
            if (!rooms[dIndex].isAvailable()) {
                System.out.printf ("Reservation name: %s\n", rooms[dIndex].getReservationName());
                System.out.printf ("Check-in date: %s\n", rooms[dIndex].getReservationCheckInDate());
                System.out.printf ("Check-out date: %s\n", rooms[dIndex].getReservationCheckOutDate());
                System.out.printf ("Total amount: %.2f\n", rooms[dIndex].getReservationTotalAmount());
            }
        }
        public void viewReservationInfo (String roomNumber, String name) {
            int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
            if (rooms[dIndex].getReservationName().equals(name)) {
                viewRoomInfo(roomNumber,  "");
            } else {
                System.out.printf ("No reservation found for this room number %s or %s\n", roomNumber, name);
            }
        }
        public void addRoom (){
            if (dRooms < constants.MAX_ROOMS) {
                dRooms++;
                rooms[dRooms] = new room(dRooms, roomPrefix, 1299);
                System.out.printf ("Room %s has been added\n", rooms[dRooms].getRoomNumber());
            } else {
                System.out.printf("Maximum number of rooms reached for %s\n", name);
            }
        }
        public void removeRoom (String roomNumber){
            int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;            

            if (dRooms > 1) {
                if (rooms[dIndex].isAvailable){
                    dRooms--;
                    rooms[dIndex] = null;
                    System.out.printf ("Room %s has been removed\n", roomNumber);
                } else {
                    System.out.printf ("Room %s is currently being used\n", roomNumber);
                }
            } else {
                System.out.printf("Minimum number of rooms reached for %s\n", name);
            }
        }
        public void changeRoomPrice (String roomNumber, double newBasePrice) {
            int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
            if (rooms[dIndex].isAvailable) {
                if (newBasePrice >= 100) {
                    rooms[dIndex].setBasePrice(newBasePrice);
                    System.out.printf ("Room %s price has been changed to %.2f\n", roomNumber, rooms[dIndex].getBasePrice());
                } else {
                    System.out.printf ("Invalid price for room %s\n", roomNumber);
                }
            } else {
                System.out.printf ("Room %s is currently being used\n", roomNumber);
            }
        }
        public int getTotalRooms () {
            return dRooms;
        }
        public String getRoomNumber (int dIndex) {
            return rooms[dIndex].getRoomNumber();
        }

        private class room
        {
            private String roomNumber;
            private reservation reservation;
            private double basePrice;
            private boolean isAvailable = true;

            private room (int roomNumber, char cPrefix, double basePrice) {
                this.roomNumber = String.format("%c%03d", cPrefix, roomNumber);
                this.basePrice = basePrice;
            }
            public double getBasePrice () {
                return basePrice;
            }
            public void setBasePrice (double basePrice) {
                this.basePrice = basePrice;
            }
            public String getRoomNumber () {
                return roomNumber;
            }
            public boolean isAvailable () {
                return isAvailable;
            }
            public void addReservation (String name, String checkInDate, String checkOutDate, double basePrice) {
                this.reservation = new reservation(name, roomNumber, checkInDate, checkOutDate, basePrice);
                this.isAvailable = false;
            }
            public String getReservationName () {
                return reservation != null ? reservation.getName() : null;
            }
            public String getReservationCheckInDate () {
                return reservation != null ? reservation.getCheckInDate() : null;
            }
            public String getReservationCheckOutDate () {
                return reservation != null ? reservation.getCheckOutDate() : null;
            }
            public double getReservationTotalAmount () {
                return reservation != null ? reservation.getTotalAmount() : 0.0;
            }
        } 
        
        private class reservation
        {
            private String name;
            private String roomNumber;
            private String checkInDate;
            private String checkOutDate;
            private double totalAmount;
            private reservation (String name, String roomNumber, String checkInDate, String checkOutDate, double basePrice) {
                int inMonth = Integer.parseInt (checkInDate.substring(0, 2));
                int outMonth = Integer.parseInt (checkOutDate.substring(0, 2));
                int inDay = Integer.parseInt (checkInDate.substring(3, 5));
                int outDay = Integer.parseInt (checkOutDate.substring(3, 5));
                this.name = name;
                this.roomNumber = roomNumber;
                this.checkInDate = checkInDate;
                this.checkOutDate = checkOutDate;
                this.totalAmount = ((outMonth - inMonth) * 30 + (outDay - inDay)) * basePrice; //assuming each month has 30 days
            }
            public String getName () {
                return name;
            }
            public String getRoomNumber () {
                return roomNumber;
            }
            public String getCheckInDate () {
                return checkInDate;
            }
            public String getCheckOutDate () {
                return checkOutDate;
            }
            public double getTotalAmount () {
                return totalAmount;
            }
        }        
    }
}
