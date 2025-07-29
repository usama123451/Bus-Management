# Bus-Management
Develop an application that manages urban bus routes.
R1 - Transport Definition  
---------------------------

The system is managed through an instance of the `Transport` class, which defines all specific parameters for an urban area.

The constructor takes as parameters the name of the transport company (e.g., "GTT") and the name of the reference municipality (e.g., "Turin").  
These attributes are accessible through their respective getter methods: `getCompany()` and `getMunicipality()`.  
Additionally, getter and setter methods are defined for the ticket price: `getPrice()` and `setPrice()`.  
The setter method ensures that the price is greater than zero; otherwise, it throws an `IllegalArgumentException`.

R2 - Route Definition  
-----------------------

Bus routes are defined using the `createRoute()` method of the `Transport` class.  
This method takes as parameters the route name, whether it operates on weekdays, whether it operates on holidays, and returns an object of class `Route`.  
A route must operate on weekdays, holidays, or both; otherwise, an `IllegalArgumentException` is thrown.  

The `Route` class provides the getter methods `getName()`, `isWeekdayService()`, and `isHolidayService()` to obtain the characteristics of the route.  
It is possible to retrieve the collection of all routes via the `getRoutes()` method of the `Transport` class.  
Additionally, a `Route` object corresponding to a given name can be obtained using the `getRoute()` method.

R3 - Places Definition  
----------------------------

It is possible to define the locations served by transport routes using the `createPlace()` method of the `Transport` class,  
which takes the location name as a parameter and returns the corresponding `Place` object.  

The `Place` class offers the `getName()` method to retrieve the location name.  
The collection of all locations can be retrieved using the `getPlaces()` method of the `Transport` class.  
Additionally, a `Place` object corresponding to a given name can be obtained using the `getPlace()` method.

R4 - Stops  
--------------

Routes can be linked to locations by defining terminals and intermediate stops using the `addStop()` method of the `Route` class,  
which takes a `Place` as a parameter. The first and last locations added to a route are automatically considered terminals.  

The list of stops can be retrieved using the `getStops()` method.  
The two terminal stops can be obtained using the `getFirstTerminal()` and `getLastTerminal()` methods.

R5 - Routes at Stops  
--------------------------

From a given location, it is possible to determine which routes stop there using the `getRoutes()` method of the `Place` class.

Additionally, the `getTerminalRoutes()` method of the `Place` class allows retrieving routes that have a terminal at a given location.

Method `numLinesPerStop()` of `Transport` class returns a Map having the places as keys and the number of routes stopping in that place as the values.
