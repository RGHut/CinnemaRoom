package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Room {
    private final int total_rows;

    private final int total_columns;

    private final List<Seat> available_seats;

    private final List<Ticket> boughtTickets;

    private final Stats stats;

    public Room(int rows, int columns) {
        this.total_rows = rows;
        this.total_columns = columns;

        this.available_seats = new ArrayList<Seat>(rows * columns);
//        int seatNumber = 0;
        this.boughtTickets = new ArrayList<Ticket>(rows * columns);

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                available_seats.add(new Seat(i, j));
//                seatNumber++;
            }
        }
        this.stats = new Stats(this.available_seats.size());

    }

    public Room() {
        this(9, 9);
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    @JsonIgnore
    public Stats getStats() { return stats; }

    public boolean isSeatValid(Seat seat) {
        return seat.getRow() <= this.total_rows && seat.getColumn() <= this.total_columns && seat.getRow() > 0 && seat.getColumn() > 0;
    }

    public boolean isSeatAvailable(int row, int column) {
        for (Seat seat : available_seats) {
            if (row == seat.getRow() && column == seat.getColumn()) {
                return true;
            }
        }
        return false;
    }

    public boolean isTicketValid(UUID token) {
        for (Ticket ticket : boughtTickets) {
            if (token.equals(ticket.getToken())) {
                return true;
            }
        }
        return false;
    }
    public Ticket purchaseSeat(Seat wantedSeat) {
        for (Seat seat : available_seats) {
            if (wantedSeat.getRow() == seat.getRow() && wantedSeat.getColumn() == seat.getColumn()) {
                available_seats.remove(seat);
                Ticket ticket = new Ticket(seat);
                stats.buyTicket(seat);
                boughtTickets.add(ticket);
                return ticket;
            }
        }
        return null;
    }

    public ReturnedTicket returnSeat(UUID token) {
        for (Ticket ticket : boughtTickets) {
            if (token.equals(ticket.getToken())) {
                boughtTickets.remove(ticket);
                stats.returnTicket(ticket.getTicket());
                available_seats.add(ticket.getTicket());
                return new ReturnedTicket(ticket.getTicket());
            }
        }
        return null;
    }

}
