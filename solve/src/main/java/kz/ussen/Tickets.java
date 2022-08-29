package kz.ussen;

import java.util.List;

public class Tickets {

    private List<TicketsInner> tickets;

    public List<TicketsInner> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketsInner> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Tickets{" +
                "tickets=" + tickets +
                '}';
    }
}
