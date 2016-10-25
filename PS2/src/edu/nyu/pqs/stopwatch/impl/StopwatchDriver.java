package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.api.State;

/**
 * StopwatchDriver is a library that implements Stopwatch interface 
 * and provides thread-safe methods to start, stop, reset, lap, getlapTimes 
 * of stopwatch. It also provides implementation of equals, hashCode and toString
 * methods in accordance with properties of stopwatch.
 * 
 *  
 */
public class StopwatchDriver implements Stopwatch {
  private final String id;
  private State state = State.BLOCK;
  private List<Long> lap = new ArrayList<Long>();
  private long timeElapsed = 0L;
  private boolean lapAdded = false;
  
  public StopwatchDriver (String id) {
    this.id = id;
  }
  
  @Override
  public String getId() {
    return id;
  }
  
  @Override
  public void start() {
    if (state == State.RUNNING) {
      throw new IllegalStateException("Stopwatch is alredy in running state");
    }
    synchronized (this) {
      state = State.RUNNING;
      timeElapsed = System.currentTimeMillis();
    }
  }

  @Override
  public void lap() {
    if (state == State.BLOCK) {
      throw new IllegalStateException("Stopwatch is not running");
    }
    synchronized (this) {
      if (lapAdded == Boolean.TRUE) {
        long lastLap = lap.get(lap.size() - 1);
        lap.remove(lap.size() - 1);
        lap.add (lastLap + (System.currentTimeMillis() - timeElapsed));
        lapAdded = false;
      } else {
        lap.add (System.currentTimeMillis() - timeElapsed);
      }
      timeElapsed = System.currentTimeMillis();
    }
  }

  @Override
  public void stop() {
    if (state == State.BLOCK) {
      throw new IllegalStateException("Stopwatch is not running");
    }
    synchronized (this) {
      if (lapAdded == Boolean.TRUE) {
        long lastLap = lap.get(lap.size() - 1);
        lap.remove(lap.size() - 1);
        lap.add (lastLap + (System.currentTimeMillis() - timeElapsed));
      } else {
        lap.add (System.currentTimeMillis() - timeElapsed);
        lapAdded = true;
      }
      timeElapsed = System.currentTimeMillis();
      state = State.BLOCK;
    }
  }

  @Override
  public void reset() {
    synchronized (this) {
      state = State.BLOCK;
      lapAdded = false;
      timeElapsed = 0L;
      lap.clear();
    }
  }

  @Override
  public List<Long> getLapTimes() {
    return Collections.unmodifiableList(lap);
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof StopwatchDriver)) {
      return false;
    }
    StopwatchDriver stopwatch = (StopwatchDriver)o;
    if (id != null && id.equals(stopwatch.getId()) || (id == null && stopwatch.getId() == null)) {
      List<Long> newLap = stopwatch.getLapTimes();
      if (lap.size() != newLap.size()) {
        return false;
      }
      for (int i = 0; i < lap.size(); i++) {
        if (lap.get(i) != newLap.get(i)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result += result * 31 + id.hashCode();
    result += result * 31 + state.hashCode();
    for (Long lapTimes: lap) {
      long f = Long.valueOf(lapTimes);
      result += (int) (f ^ (f >>> 32));
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    result.append("[Id:").append(getId()).append(", ").append("Laps: ").append(lap.toString()).append(", ");
    result.append("State: ").append(state == State.RUNNING ? "running" : "not_running").append("]");
    return result.toString();
  }

}
