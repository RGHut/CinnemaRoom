package cinema;

import java.util.UUID;

public class Ticket {
    private final UUID token;

    private final Seat ticket;

    public Ticket(UUID token, Seat seat) {
        this.token = token;
        this.ticket = seat;
    }

    public Ticket(Seat seat) {
        this(UUID.randomUUID(), seat);
    }

    public UUID getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
