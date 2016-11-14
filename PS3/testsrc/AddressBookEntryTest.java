import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/* The declared package pqs_163 in AddressBook.java:#1 and 
 * AddressBookEntry.java#1 makes these classes non accessible.
 * In order to test, I commented the first line in both classes.
 */

public class AddressBookEntryTest {
  
  @Test
  public void testConstructor_simple() {
    AddressBookEntry entry = new AddressBookEntry("John", "+12345", 
        "251,Mercer St", "nyu.edu", "Classmate");
    assertEquals("John", entry.getName());
    assertEquals("+12345", entry.getPhoneNumber());
    assertEquals("251,Mercer St", entry.getPostalAddress());
    assertEquals("nyu.edu", entry.getEmailAddress());
    assertEquals("Classmate", entry.getNote());
  }
  
  /*
   * Ambiguity - the second argument in constructor is not PostalAddress. 
   * Author should use Builder pattern so that the programmer doesnot have 
   * to remember the parameter sequencing in constructor.
   */
  @Test
  public void testConstructor_withNameAndPostalAddressOnly() {
    AddressBookEntry entry = new AddressBookEntry("John", "251,Mercer St.",
        null, null, null);
    assertEquals("John", entry.getName());
    assertEquals("251,Mercer St", entry.getPostalAddress());
  }
  
  @Test
  public void testConstructor_withNullName() {
    AddressBookEntry entry = new AddressBookEntry(null, "251,Mercer St.",
        null, null, "Schoolmate");
    assertEquals(null, entry.getPhoneNumber());
  }
  
  /*
   * Bug - Name with only spaces shouldn't be allowed to form an entry.
   */
  @Test
  public void testConstructor_nameWithSpaces() {
    AddressBookEntry entry = new AddressBookEntry("  ", "+12345.",
        null, null, "Schoolmate");
    assertEquals(null, entry.getPhoneNumber());
  }
  
  /*
   * Bug - Name with only tabs shouldn't be allowed to form an entry.
   */
  @Test
  public void testConstructor_nameWithTabs() {
    AddressBookEntry entry = new AddressBookEntry("\t\t", "+123456789",
        null, null, "Schoolmate");
    assertEquals(null, entry.getPhoneNumber());
  }
  
  /*
   * Bug - empty name shouldn't be allowed to form an entry.
   */
  @Test
  public void testConstructor_nameWithEmptyString() {
    AddressBookEntry entry = new AddressBookEntry("", "+123456789",
        null, null, "Schoolmate");
    assertEquals(null, entry.getPhoneNumber());
  }
  
  @Test
  public void testContainsString_nullQuery() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", "+123456789",
        null, null, "Schoolmate");
    assertFalse(entry.containsString(null));
  }
  
  @Test
  public void testContainsString_simple() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", "+123456789",
        null, null, "Schoolmate");
    assertTrue(entry.containsString("Schoolmate"));
  }
  
  /*
   * Ambiguity - Javadoc of containsString states that partial queries are 
   * not supported. However, the method supports partial query search.
   */
  @Test
  public void testContainsString_partialMatch() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", "+123456789",
        null, null, "Schoolmate");
    assertFalse(entry.containsString("School"));
  }
  
  @Test
  public void testContainsString_spaces() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", "+123456789",
        null, null, "School mate");
    AddressBookEntry entry1 = new AddressBookEntry("Jude", "+123456789",
        null, null, "Schoolmate");
    assertTrue(entry.containsString(" "));
    assertFalse(entry1.containsString(" "));
  }
  
  @Test
  public void testToString_simple() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", "+987654",
        "251,Mercer", "nyu.edu", "Schoolmate");
    assertEquals("Kenith +987654 251,Mercer St nyu.edu School mate", entry.toString());
  }
  
  /*
   * Ambiguity - toString() uses " " as delimeter. When entry has " ", it becomes 
   * to interpret results.
   */
  @Test
  public void testToString_entryWithSpaces() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", "+987654",
        "251 Mercer St", "nyu.edu", "School mate");
    assertEquals("Kenith +987654 251 Mercer St nyu.edu School mate", entry.toString());
  }
  
  /*
   * Bug - toString() adds extra spaces even when a data member is null
   */
  @Test
  public void testToString_nullProperties() {
    AddressBookEntry entry = new AddressBookEntry("Kenith", null,null,null,null);
    assertEquals("Kenith", entry.toString());
  }
}
