package it.polito.oop.bus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Transport {

    private String company;
    private String municipality;
    private double price = -1.0;
    private Map<String, Route> routes = new HashMap<>();
    private Map<String, Place> places = new HashMap<>();

    /**
     * Constructs a Transport object.
     * @param company the name of the transport company
     * @param municipality the municipality it operates in
     */
    public Transport(String company, String municipality) {
        this.company = company;
        this.municipality = municipality;
    }

    /**
     * Retrieves the company name.
     * @return the company name
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * Retrieves the municipality name.
     * @return the municipality name
     */
    public String getMunicipality() {
        return this.municipality;
    }

    /**
     * Sets the ticket price. Price must be a positive value.
     * @param price the ticket price
     * @throws IllegalArgumentException if price is not positive
     */
    public void setPrice(double price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be a positive number.");
        }
        this.price = price;
    }

    /**
     * Gets the ticket price.
     * @return the ticket price, or -1.0 if not set
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Creates a new route. A route must operate on at least one type of day.
     * @param name the name of the route
     * @param weekday whether it operates on weekdays
     * @param holiday whether it operates on holidays
     * @return the newly created Route object
     * @throws IllegalArgumentException if the route operates on neither weekdays nor holidays
     */
    public Route createRoute(String name, boolean weekday, boolean holiday) throws IllegalArgumentException {
        if (!weekday && !holiday) {
            throw new IllegalArgumentException("A route must operate on weekdays or holidays or both.");
        }
        Route newRoute = new Route(name, weekday, holiday);
        this.routes.put(name, newRoute);
        return newRoute;
    }

    /**
     * Retrieves all routes for this transport system.
     * @return a collection of all routes
     */
    public Collection<Route> getRoutes() {
        return this.routes.values();
    }

    /**
     * Retrieves a specific route by its name.
     * @param name the name of the route to find
     * @return the Route object, or null if not found
     */
    public Route getRoute(String name) {
        return this.routes.get(name);
    }

    /**
     * Creates a new place (stop). If a place with the same name already exists,
     * it returns the existing object to ensure uniqueness.
     * @param name the name of the place
     * @return the created or existing Place object
     */
    public Place createPlace(String name) {
        return this.places.computeIfAbsent(name, Place::new);
    }

    /**
     * Retrieves all places (stops) in the system.
     * @return a collection of all places
     */
    public Collection<Place> getPlaces() {
        return this.places.values();
    }

    /**
     * Retrieves a specific place by its name.
     * @param name the name of the place to find
     * @return the Place object, or null if not found
     */
    public Place getPlaces(String name) {
        return this.places.get(name);
    }

    /**
     * Computes a map of stop names to the number of routes that serve them.
     * @return a map where keys are stop names and values are the count of routes
     */
    public Map<String, Integer> numLinesPerStop() {
        return this.places.values().stream()
                .collect(Collectors.toMap(
                        Place::getName,
                        p -> p.getRoutes().size()));
    }
}