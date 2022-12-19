package cinema.model;

import java.util.ArrayList;

public class Room {
    private int total_rows;
    private int total_columns;
    private ArrayList<Seat> available_seats = new ArrayList<>();

    public Room(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        this.available_seats = createAvaibleSeats(available_seats);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public ArrayList<Seat> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(ArrayList<Seat> avaibleSeats) {
        this.available_seats = avaibleSeats;
    }

    public ArrayList<Seat> createAvaibleSeats(ArrayList<Seat> seats) {
        for (int i = 1 ; i <= total_rows ; i++ ) {
            for (int j = 1; j <= total_columns; j++) {
                Seat seat = new Seat(i,j,0);
                if (i <= 4) {
                    seat.setPrice(10);
                } else {
                    seat.setPrice(8);
                }
                seats.add(seat);
            }
        }
        return seats;
    }
}
