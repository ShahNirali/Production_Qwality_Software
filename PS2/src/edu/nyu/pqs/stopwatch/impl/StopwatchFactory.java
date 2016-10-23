package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

/**
 * The StopwatchFactory is a thread-safe factory class for Stopwatch objects.
 * It maintains references to all created Stopwatch objects and provides a
 * convenient method for getting a list of those objects.
 *
 */
public class StopwatchFactory {

  private static final List<Stopwatch> StopwatchCollection = new ArrayList<Stopwatch>();
  
  /**
   * Creates and returns a new Stopwatch object
   * @param id The identifier of the new object
   * @return The new Stopwatch object
   * @throws IllegalArgumentException if <code>id</code> is empty, null, or
   *     already taken.
   */
  public static Stopwatch getStopwatch(String id) {
    if (id == null || id.isEmpty()) {
      throw new IllegalArgumentException("Stopwatch identifier can't be empty");
    }
    List<Stopwatch> stopwatches = getStopwatches();
    for (Stopwatch stopwatch: stopwatches) {
      if (stopwatch.getId().equals(id)) {
        throw new IllegalArgumentException("Stopwatch identifier already present");
      }
    }
    Stopwatch stopwatch = new StopwatchImpl(id);
    StopwatchCollection.add(stopwatch);
    return stopwatch;
  }

  /**
   * Returns a list of all created stopwatches
   * @return a List of al creates Stopwatch objects.  Returns an empty
   * list if no Stopwatches have been created.
   */
  public static List<Stopwatch> getStopwatches() {
    return StopwatchCollection;
  }
}
