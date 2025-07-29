package it.polito.oop.bus;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private String name;
    private boolean weekday;
    private boolean holiday;
    private List<Place> stops = new ArrayList<>();

    /**
     * Package-private constructor to be called by the Transport class.
     * @param name name of the route
     * @param weekday if it runs on weekdays
     * @param holiday if it runs on holidays
     */
    Route(String name, boolean weekday, boolean holiday) {
        this.name = name;
        this.weekday = weekday;
        this.holiday = holiday;
    }

    /**
     * Retrieves the name of the route.
     * @return the route name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the route operates on weekdays.
     * @return true if the route is for weekdays
     */
    public boolean isWeekday() {
        return this.weekday;
    }

    /**
     * Checks if the route operates on holidays.
     * @return true if the route is for holidays
     */
    public boolean isHoliday() {
        return this.holiday;
    }

    /**
     * Adds a stop to the route.
     * This method also updates the Place to register this route.
     * @param fermata the place to add as a stop
     */
    public void addStop(Place fermata) {
        if (fermata != null) {
            this.stops.add(fermata);
            // Establish the bidirectional link
            fermata.addRoute(this);
        }
    }

    /**
     * Retrieves the list of stops for this route in order.
     * @return an ordered list of places
     */
    public List<Place> getStops() {
        return this.stops;
    }

    /**
     * Retrieves the first stop (terminal) of the route.
     * @return the first place in the route
     */
    public Place getFirstTerminal() {
        if (stops.isEmpty()) {
            return null;
        }
        return this.stops.get(0);
    }

    /**
     * Retrieves the last stop (terminal) of the route.
     * @return the last place in the route
     */
    public Place getLastTerminal() {
        if (stops.isEmpty()) {
            return null;
        }
        return this.stops.get(this.stops.size() - 1);
    }
}