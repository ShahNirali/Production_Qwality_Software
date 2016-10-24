package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.api.State;

public class StopwatchDriver implements Stopwatch {
  private final String id;
  private State state;
  private List<Long> lap = new ArrayList<Long>();
  
  public StopwatchDriver (String id) {
    this.id = id;
    this.state = State.BLOCK;
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
    this.state = State.RUNNING;
    this.lap.add(System.currentTimeMillis());
  }

  @Override
  public void lap() {
    if (this.state == State.BLOCK) {
      throw new IllegalStateException("Stopwatch is not running");
    }
    synchronized (lap) {
      this.lap.add(System.currentTimeMillis() - this.lap.get(lap.size()-1));
    }
  }

  @Override
  public void stop() {
    lap();
    this.state = State.BLOCK;
  }

  @Override
  public void reset() {
    this.state = State.BLOCK;
    synchronized (lap) {
      lap.clear();
    }
  }

  @Override
  public List<Long> getLapTimes() {
    return lap;
  }
  
  @Override
  public boolean equals(Object o) {
    return false;
  }
  
  @Override
  public int hashCode() {
    return 0;
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
