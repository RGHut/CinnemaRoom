package cinema;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class CinemaController {
    Room room = new Room();

    @GetMapping("/seats")
    public Room getRoom() {
        return room;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {
        if (room.isSeatValid(seat)){
            if (room.isSeatAvailable(seat.getRow(), seat.getColumn())) {
                return ResponseEntity.status(200).body(room.purchaseSeat(seat));
            } else {
                return ResponseEntity.status(400).body("{\"error\":" + "\"The ticket has been already purchased!\"" + "}");
            }
        } else {
            return ResponseEntity.status(400).body("{\"error\":" + "\"The number of a row or a column is out of bounds!\"" +"}");
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody String token) {
        UUID uuid;
        try {
            uuid = UUID.fromString(token.split(":")[1].split("\"")[1]);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("{\"error\":" + "\"Wrong token!\"" + "}");
        }
        if (room.isTicketValid(uuid)) {
            return ResponseEntity.status(200).body(room.returnSeat(uuid));
        } else {
            return ResponseEntity.status(400).body("{\"error\":" + "\"Wrong token!\"" + "}");
        }
    }

    @PostMapping("/stats")
    public ResponseEntity<?> viewStats(@RequestParam(required = false) String password){
//        password.split(":")[1].split("\"")[1]
//        try {
            if ("super_secret".equals(password)) {
                return ResponseEntity.status(200).body(room.getStats());
            } else {
                return ResponseEntity.status(401).body("{\"error\":" + "\"The password is wrong!\"" + "}");
            }
//        } catch (Exception e) {
//            System.out.println(e);
//            return ResponseEntity.status(401).body("{\"error\":" + "\"The password is wrong!\"" + "}");
//        }
    }


}
