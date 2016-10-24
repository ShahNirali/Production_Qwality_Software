package edu.nyu.pqs.stopwatch.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.nyu.pqs.stopwatch.api.Stopwatch;


public class TestStopwatchDriver {

  @Test(expected = IllegalStateException.class)
  public void testStart_withStateRunning() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestStart_RunningState");
    stopwatch.stop();
    stopwatch.start();
  }
  
  @Test
  public void testStart_withStateBlocked() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestStart_BlockedState");
    stopwatch.start();
  }
  
  @Test(expected = IllegalStateException.class)
  public void testLap_withStateBlocked() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestLap_BlockedState");
    stopwatch.lap();
  }
  
  @Test
  public void testLap_withStateRunning() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestLap_RunningState");
    stopwatch.start();
    stopwatch.lap();
  }
  
  @Test(expected = IllegalStateException.class)
  public void testStop_beforeStart() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestStop_BeforeStart");
    stopwatch.stop();
  }
  
  @Test
  public void testStop_afterStart() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestStop_AfterStart");
    stopwatch.start();
    stopwatch.stop();   
  }
  
  @Test
  public void testEquals_withDifferentObject() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestEquals_withDifferentObject");
    StringBuffer sb = new StringBuffer();
    assertEquals(stopwatch.equals(sb), Boolean.FALSE);
  }
  
  @Test
  public void testEquals_withSameObjectReference() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestEquals_withSameObjectReference");
    assertEquals(stopwatch.equals(stopwatch), Boolean.TRUE);
  }
  
  @Test
  public void testEquals_withDifferentObjectReference() {
    StopwatchDriver stopwatch = new StopwatchDriver("TestEquals_withDifferentObjectReference");
    Stopwatch second = stopwatch;
    assertEquals(stopwatch.equals(second), Boolean.TRUE);
  }
  
  @Test
  public void testEquals_withTransitivity() {
    StopwatchDriver stopwatch1 = new StopwatchDriver("TestEquals_withTransitivity");
    StopwatchDriver stopwatch2 = new StopwatchDriver("TestEquals_withTransitivity");
    StopwatchDriver stopwatch3 = new StopwatchDriver("TestEquals_withTransitivity");
    assertEquals(stopwatch1.equals(stopwatch2), Boolean.TRUE);
    assertEquals(stopwatch2.equals(stopwatch3), Boolean.TRUE);
    assertEquals(stopwatch3.equals(stopwatch1), Boolean.TRUE);
  }
  
  @Test
  public void testEquals_withSymmetry() {
    StopwatchDriver stopwatch1 = new StopwatchDriver("TestEquals_withSymmetry");
    String id = "TestEquals_withSymmetry";
    assertEquals(stopwatch1.equals(id), Boolean.FALSE);
    assertEquals(id.equals(stopwatch1), Boolean.FALSE);
  }
  
  @Test
  public void testEquals_withConsistency() {
    StopwatchDriver stopwatch1 = new StopwatchDriver("TestEquals_withConsistency");
    StopwatchDriver stopwatch2 = new StopwatchDriver("TestEquals_withConsistency");
    assertEquals(stopwatch2.equals(stopwatch1), Boolean.TRUE);
    stopwatch2.start();
    assertEquals(stopwatch2.equals(stopwatch1), Boolean.FALSE);
  }
  
  @Test
  public void testHashCode() {
    StopwatchDriver stopwatch1 = new StopwatchDriver("TestHashcode");
    StopwatchDriver stopwatch2 = new StopwatchDriver("TestHashcode");
    int hashcode1 = stopwatch1.hashCode();
    int hashcode2 = stopwatch2.hashCode();
    assertEquals(hashcode1 == hashcode2, Boolean.TRUE);
    stopwatch2.start();
    hashcode2 = stopwatch2.hashCode();
    assertEquals(hashcode1 == hashcode2, Boolean.FALSE);
  }
  
}
