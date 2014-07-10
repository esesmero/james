package org.apache.james.bond.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UtilTest {

  @Test
  public void convertStringToInt() {
    assertEquals(0, Util.convertStringToInt("asdf"));
    assertEquals(5, Util.convertStringToInt("5"));
    assertEquals(5, Util.convertStringToInt("dfadf", 5));
  }

  @Test
  public void isIPAddress() {
    assertTrue(Util.isIPAddress("0.0.0.0"));
    assertTrue(Util.isIPAddress("10.0.0.0"));
    assertTrue(Util.isIPAddress("123.21.12.32"));
    assertTrue(Util.isIPAddress("255.255.255.255"));

    assertFalse(Util.isIPAddress("255.255.255.255 "));
    assertFalse(Util.isIPAddress("256.255.255.255"));
    assertFalse(Util.isIPAddress("0000.0.0.0"));
    assertFalse(Util.isIPAddress("0.0.0"));
    assertFalse(Util.isIPAddress("dssgd"));
  }
}
