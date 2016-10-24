package edu.nyu.pqs.stopwatch.impl;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import edu.nyu.pqs.stopwatch.api.Stopwatch;

public class TestStopwatchFactory {

  @Test(expected = IllegalArgumentException.class)
  public void testGetStopwatch_sameId() {
    Stopwatch stopwatch = StopwatchFactory.getStopwatch(
        "ID " + Thread.currentThread().getId());
    Stopwatch stopwatch1 = StopwatchFactory.getStopwatch(
        "ID " + Thread.currentThread().getId());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetStopwatch_emptyId() {
    Stopwatch stopwatch = StopwatchFactory.getStopwatch(
        "");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testGetStopwatch_nullId() {
    Stopwatch stopwatch = StopwatchFactory.getStopwatch(null);
  }
  
  @Test
  public void testGetStopwatch_checkStopwatchAdded() {
    Stopwatch stopwatch1 = StopwatchFactory.getStopwatch("ID " + Thread.currentThread().getId());
    List<Stopwatch> stopwatches = StopwatchFactory.getStopwatches();
    assertEquals(stopwatches.contains(stopwatch1), Boolean.TRUE);
  }
  

}
