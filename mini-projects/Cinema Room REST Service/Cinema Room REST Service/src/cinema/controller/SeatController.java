package cinema.controller;


import cinema.model.Room;
import cinema.model.Seat;
import cinema.service.SeatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    public SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public Room getAllSeats(){
        return seatService.getAllSeats();
    }

    @GetMapping(value = "/getSeatByIndex",params = {"row","column"})
    public Seat getSeatByIndex(@RequestParam int row, @RequestParam int column){
        return seatService.getSeatByIndex(row,column);
    }
}
