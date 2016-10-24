package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.api.State;

public class StopwatchDriver implements Stopwatch {
  private final String id;
  private State state = State.BLOCK;
  private List<Long> lap = new ArrayList<Long>();
  
  public StopwatchDriver (String id) {
    this.id = id;
  }
  
  /*
   * Synchronizing object of StopwatchDriver to add lap times
   */
  private synchronized void addLap() {
    if (lap.isEmpty()) {
      lap.add(System.currentTimeMillis());
    }
    else {
      lap.add(System.currentTimeMillis() - this.lap.get(lap.size()-1));
    }
  }
  
  /*
   * Synchronizing object of StopwatchDriver to change state
   */
  private synchronized void changeState(State state) {
    this.state = state;
  }
  
  /*
   * Synchronizing object of StopwatchDriver to clear Lap
   */
  private synchronized void clearLap() {
    lap.clear();
  }
  
  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public void start() {
    if (this.state == State.RUNNING) {
      throw new IllegalStateException("Stopwatch is alredy in running state");
    }
    changeState(State.RUNNING);
    addLap();
  }

  @Override
  public void lap() {
    if (this.state == State.BLOCK) {
      throw new IllegalStateException("Stopwatch is not running");
    }
    addLap();
  }

  @Override
  public void stop() {
    lap();
    changeState(State.BLOCK);
  }

  @Override
  public void reset() {
    changeState(State.BLOCK);
    clearLap();
  }

  @Override
  public List<Long> getLapTimes() {
    return lap;
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
    result.append(this.getId()).append("|");
    for (Long lapTimes: lap) {
      result.append(Long.valueOf(lapTimes)).append(",");
    }
    result.append("|").append(state);
    return result.toString();
  }

}
