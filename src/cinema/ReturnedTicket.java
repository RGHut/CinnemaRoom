package cinema;

public class ReturnedTicket {
    private final Seat returned_ticket;

    public ReturnedTicket(Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }

    public Seat getReturned_ticket() {
        return returned_ticket;
    }
}
