package it.polito.oop.bus;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Place {

    private String name;
    private Set<Route> routes = new HashSet<>();

    /**
     * Package-private constructor to be called from Transport class.
     * @param name the name of the place
     */
    Place(String name) {
        this.name = name;
    }

    /**
     * Retrieves the name of the place.
     * @return the place name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves all routes that pass through this place.
     * @return a collection of routes
     */
    public Collection<Route> getRoutes() {
        return this.routes;
    }

    /**
     * Retrieves all routes for which this place is a terminal (first or last stop).
     * @return a collection of terminal routes
     */
    public Collection<Route> getTerminalRoutes() {
        // A route is a terminal route for this place if this place is its first or last stop.
        return this.routes.stream()
                .filter(r -> this.equals(r.getFirstTerminal()) || this.equals(r.getLastTerminal()))
                .collect(Collectors.toList());
    }

    /**
     * Package-private method to add a route to this place's set of routes.
     * Called by Route.addStop().
     * @param route the route to add
     */
    void addRoute(Route route) {
        this.routes.add(route);
    }
}