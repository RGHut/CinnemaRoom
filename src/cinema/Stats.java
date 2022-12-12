package cinema;

public class Stats {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public Stats(int current_income, int number_of_available_seats, int number_of_purchased_tickets) {
        this.current_income = current_income;
        this.number_of_available_seats = number_of_available_seats;
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }

    public Stats(int number_of_available_seats) {
        this(0, number_of_available_seats, 0);
    }

    public int getCurrent_income() {
        return current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void buyTicket(Seat seat) {
        this.current_income += seat.getPrice();
        this.number_of_available_seats --;
        this.number_of_purchased_tickets ++;
    }

    public void returnTicket(Seat seat) {
        this.current_income -= seat.getPrice();
        this.number_of_available_seats ++;
        this.number_of_purchased_tickets --;
    }

}
