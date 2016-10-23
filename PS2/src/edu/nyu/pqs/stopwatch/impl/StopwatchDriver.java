package edu.nyu.pqs.stopwatch.impl;

import java.util.ArrayList;
import java.util.List;
import edu.nyu.pqs.stopwatch.api.Stopwatch;
import edu.nyu.pqs.stopwatch.api.State;

public class StopwatchDriver implements Stopwatch{
  private final String id;
  private State state;
  private List<Long> lap;
  private Long timeElapsed;
  
  public StopwatchDriver (String id) {
    this.id = id;
    this.state = State.BLOCK;
    this.lap = new ArrayList<Long>();
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
    this.timeElapsed = System.currentTimeMillis();
    this.lap.add(0L);
  }

  @Override
  public void lap() {
    if (this.state == State.BLOCK) {
      throw new IllegalStateException("Stopwatch is not running");
    }
    synchronized (lap) {
      this.lap.add(System.currentTimeMillis() - this.timeElapsed);
    }
    this.timeElapsed = System.currentTimeMillis();
  }

  @Override
  public void stop() {
    lap();
    this.state = State.BLOCK;
  }

  @Override
  public void reset() {
    this.state = State.BLOCK;
    lap.clear();
    this.timeElapsed = 0L;
  }

  @Override
  public List<Long> getLapTimes() {
    return lap;
  }
  
  @Override
  public int hashCode() {
    return 0;
  }
  
  @Override
  public String toString() {
    return this.id + "|" + this.lap.toString();
  }

}
