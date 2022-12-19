package cinema.service;

import cinema.model.Room;
import cinema.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {

    RoomService roomService;


    public SeatService(@Lazy RoomService roomService) {
        this.roomService = roomService;
    }

    public Room getAllSeats(){
        return roomService.getRoom();
    }

    public Seat getSeatByIndex(int row, int column){
        for (Seat seat:roomService.getRoom().getAvailable_seats()) {
            if (seat.getRow() == row && seat.getColumn() == column) {
                return seat;
            }
        }
        return new Seat(0, 0,0);
    }
}
