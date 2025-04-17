package ObjectElements;

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

        private char roomPrefix = '\n';

        public Hotel(String name, char cPrefix) {
            this.name = name;
            this.roomPrefix = cPrefix;
            this.rooms[0] = new room(dRooms, cPrefix);
            this.reservations[0] = null;
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
        public void addReservation (String name, String roomNumber, String checkInDate, String checkOutDate) {
            if (dReservations < constants.MAX_RESERVATIONS) {
                dReservations++;
                int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
                reservations[dIndex] = new reservation(name, roomNumber, checkInDate, checkOutDate, basePrice);
                rooms[dIndex].addReservation(name, checkInDate, checkOutDate, basePrice);
                System.out.printf ("Reservation for %s has been added\n", name);
            } else {
                System.out.printf("Maximum number of reservations reached for %s\n", name);
            }
        }
        public void removeReservation (String name, String roomNumber, String checkInDate, String checkOutDate) {
            if (dReservations > 0) {
                dReservations--;
                int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
                reservations[dIndex] = null;
                System.out.printf ("Reservation for %s has been removed\n", name);
            } else {
                System.out.printf("Minimum number of reservations reached for %s\n", name);
            }
        }
        public void addRoom (int dIndex){
            if (dRooms < constants.MAX_ROOMS) {
                dRooms++;
                rooms[dIndex] = new room(dRooms, roomPrefix);
                System.out.printf ("Room %s has been added\n", rooms[dIndex].getRoomNumber());
            } else {
                System.out.printf("Maximum number of rooms reached for %s\n", name);
            }
        }
        public void removeRoom (String roomNumber){
            if (dRooms > 1) {
                dRooms--;
                int dIndex = Integer.parseInt(roomNumber.substring(1)) - 1;
                rooms[dIndex] = null;
                System.out.printf ("Room %s has been removed\n", roomNumber);
            } else {
                System.out.printf("Minimum number of rooms reached for %s\n", name);
            }
        }
        public int getTotalRooms () {
            return dRooms;
        }


        private class room
        {
            private String roomNumber;
            private reservation reservation;
            private boolean isAvailable = true;

            private room (int roomNumber, char cPrefix) {
                this.roomNumber = String.format("%c%03d", cPrefix, roomNumber);
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
            public String getReservationRoomNumber () {
                return reservation != null ? reservation.getRoomNumber() : null;
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
