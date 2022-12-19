package cinema.controller;

import cinema.dto.seatdto.SeatPurchaseRequest;
import cinema.model.Room;
import cinema.model.Seat;
import cinema.model.Ticket;
import cinema.service.RoomService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class RoomController {
    RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping(value = "/purchase")
    public ResponseEntity<Seat> purchaseSeat(@RequestBody SeatPurchaseRequest seatPurchaseRequest) {
       return roomService.purchaseSeat(seatPurchaseRequest);
    }

    @PostMapping(value = "/return")
    public ResponseEntity<?> refundSeat(@RequestBody Ticket ticket) {
        return roomService.refund(ticket);
    }

    @PostMapping(value = "/stats{password}")
    public ResponseEntity<?> stats(@RequestParam(value = "password", required = false) String password) {
        return roomService.stats(password);
    }

}
