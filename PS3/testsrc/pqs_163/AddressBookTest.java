package pqs_163;

import pqs_163.AddressBook;
import pqs_163.AddressBookEntry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotEquals;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

/* The declared package pqs_163 in AddressBook.java:#1 and 
 * AddressBookEntry.java#1 makes these classes non accessible.
 * In order to test, I created package pqs_163 and added the 
 * both classes to that package.
 */

public class AddressBookTest {
  
  @Test
  public void testAddNewContact_nullName() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact(null, "+123456789", "JFK blvd", 
        "john@nyu.edu", "Permanent Address"));
  }
  
  @Test
  public void testAddNewContact_name() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("John", null, null, null, null));
    assertEquals("John", addressbook.searchAddressBook("John").get(0).getName()); 
  }
  
  @Test
  public void testAddNewContact_allProperties() {
    AddressBook addressbook = new AddressBook();
    addressbook.addNewContact("John", "+123456789", "JFK blvd", 
        "john@nyu.edu", "Permanent Address");
    assertEquals("John +123456789 JFK blvd john@nyu.edu Permanent Address", 
        addressbook.searchAddressBook("John").get(0).toString());
  }
  
  /* 
   * Bug - addNewContact doesnot replace spaces in name. Incorrect regex in replace()
   * It inserts an entry to AddressBook with name = " ".
   */
  @Test
  public void testAddNewContact_nameWithSpcaesOnly() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact(" ", null, null, null, null));
  }
  
  /* 
   * Bug - addNewContact doesnot replace tabs in name. Incorrect regex in replace()
   * It inserts an entry to AddressBook with name = "\t" which is wrong
   */
  @Test
  public void testAddNewContact_nameWithTabsOnly() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact("\t", null, null, null, null));
  }
  
  /* 
   * Bug - addNewContact doesnot replace newLine in name. Incorrect regex in replace()
   * It inserts an entry to AddressBook with name = "\n" which shouldn't be the case
   */
  @Test
  public void testAddNewContact_nameWithNewLineOnly() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact("\n", null, null, null, null));
  }
  
  @Test
  public void testAddNewContact_nameAsEmptyString() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact("", null, null, null, null));
  }
  
  @Test
  public void testAddNewContact_nameWithNonAlphaNum() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact(" \n\t", null, null, null, null));
  }
  
  /* 
   * Bug - Cannot test addNewContact(AddressBookEntry entry) as the access specifier
   * is private hence not accessible
   */
  @Test
  public void testAddNewContact_usingAddressBookEntry() {
    AddressBook addressbook = new AddressBook();
    //addressBook.addNewContact(new AddressBookEntry("John", null, null, null, null));
  }
  
  /*
   * Bug - author doesn't differentiate between null and empty string.
   * null is considered as empty string.
   */
  @Test
  public void testAddAddressBookEntry_propretyAsEmptyString() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", "", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("");
    assertEquals(1, entries.size());
  }
  
  /* 
   * Bug - Cannot remove entry which is present in the addressbook.
   * This is because author doesn't override equals() in AddressBookEntry
   * class. An entry is said to be same when their references are same
   * and also when their properties are same.
   */
  @Test
  public void testRemoveEntry_usingAddressBookEntry() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("John", "+123456789", "JFK blvd", 
        "john@nyu.edu", "Permanent Address"));
    AddressBookEntry entry = new AddressBookEntry("John", "+123456789", "JFK blvd", 
        "john@nyu.edu", "Permanent Address");
    addressbook.removeEntry(entry);
    assertEquals(0, addressbook.searchAddressBook("John").size());
  }
  
  /*
   * Bug - User won't know the entryID as entryID for each AddressBook doesn't 
   * start from 0. This makes it difficult to remove entry.
   */
  @Test
  public void testRemoveEntry_usingEntryId() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("John", null, null, null, null));
    assertTrue(addressbook.removeEntry(0));
  }
  
  /*
   * Instead of using Integer object, author could use primitive int. 
   * Using primitive int would be faster operation.
   */
  @Test
  public void testRemoveEntry_usingNullEntryId() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("John", null, null, null, null));
    Integer entryID = null;
    assertFalse(addressbook.removeEntry(entryID));
  }
  
  @Test
  public void testRemoveEntry_usingIntEntryId() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("John", null, null, null, null));
    int entryID = addressbook.searchAddressBook("John").get(0).getEntryID().intValue();
    assertTrue(addressbook.removeEntry(entryID));
  }
  
  @Test
  public void testRemoveEntry_usingEntryIdNotPresent() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Josh", null, null, null, null));
    assertTrue(addressbook.addNewContact("Ellen", "+123456789", null, null, null));
    assertFalse(addressbook.removeEntry(100));
  }
  
  /*
   * Bug - searchAddressBook should throw exception when search string 
   * passed as parameter is null. If not then it should show the entries that
   * have null values present. Instead the user returns empty List.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSearchAddressBook_nullQuery() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jill", "+12345678", null, 
        "jill@nyu.edu", "Collegemate"));
    addressbook.searchAddressBook(null);
  }
  
  @Test
  public void testSearchAddressBook_queryNotFound() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jill", "+12345678", null, 
        "jill@nyu.edu", "Collegemate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("Joe");
    assertEquals(0, entries.size());
  }
  
  /*
   * Bug - searchAddressBook() returns ArrayList. So when search result are 
   * stored in Stack object, it throws ClassCastException. 
   * The author should use interfaces for declaration, not concrete class.
   */
  @Test
  public void testSearchAddressBook_storeResultToStack() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jill", "+12345678", null, 
        "jill@nyu.edu", "Collegemate"));
    List<AddressBookEntry> entries = new Stack<AddressBookEntry>();
    entries = addressbook.searchAddressBook("Aqua");
    assertEquals("Aqua", ((Stack<AddressBookEntry>) entries).pop().getName());
  }
  
  @Test
  public void testSearchAddressBook_exactStringMatch() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jill", "+12345678", null, 
        "jill@nyu.edu", "Collegemate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("Schoolmate");
    assertTrue(entries.get(0).containsString("Schoolmate"));
  }
  
  /*
   * Ambiguity - Javadoc of seearchAddressBook states that partial 
   * matches are ignored. However, the method supports partial
   * string search
   */
  @Test
  public void testSearchAddressBook_partialStringMatch() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jill", "+12345678", null, 
        "jill@nyu.edu", "Collegemate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("nyu");
    assertFalse(entries.get(0).containsString("nyu"));
  }
  
  @Test
  public void testSearchAddressBook_specialCharacterMatch() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("!@#$%^&*~", "+12345678", null, 
        "jill@nyu.edu", "Collegemate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("!@#$%^&*~");
    assertTrue(entries.get(0).containsString("!@#$%^&*~"));
  }
  
  @Test
  public void testSearchAddressBook_byName() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", null, 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("Aqua");
    assertEquals("Aqua", entries.get(0).getName());
  }
  
  @Test
  public void testSearchAddressBook_byPhoneNumber() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", null, 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+18765567777", null, 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("+12345678");
    assertEquals("+12345678", entries.get(0).getPhoneNumber());
  }
  
  @Test
  public void testSearchAddressBook_byPostalAddress() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", "251 Mercer Street", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+18765567777", null, 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("Mercer");
    assertEquals("251 Mercer Street", entries.get(0).getPostalAddress());
  }
  
  @Test
  public void testSearchAddressBook_byEmailAddress() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", "251 Mercer Street", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+18765567777", null, 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("aqua@nyu.edu");
    assertEquals("aqua@nyu.edu", entries.get(0).getEmailAddress());
  }
  
  @Test
  public void testSearchAddressBook_byNote() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", "251 Mercer Street", 
        "aqua@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("Schoolmate");
    assertEquals("Schoolmate", entries.get(0).getNote());
  }
  
  @Test
  public void testSearchAddressBook_usingSpaces() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Cooler Kids", "NA", "251,Mercer", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", "W4th", 
        "kehan@nyu.edu", null));
    List<AddressBookEntry> entries = addressbook.searchAddressBook(" ");
    assertEquals(1, entries.size());
    assertEquals("Cooler Kids", entries.get(0).getName());
  }
  
  @Test
  public void testSearchAddressBook_specialCharaterNewLine() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Cooler\nKids", " ", "251,Mercer", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", "W4th", 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("\n");
    assertEquals(1, entries.size());
    assertEquals("Cooler\nKids", entries.get(0).getName());
  }
  
  @Test
  public void testSearchAddressBook_specialCharaterSpaceNewLineTabs() {
    AddressBook addressbook = new AddressBook();
    assertFalse(addressbook.addNewContact(" \n\t", " ", "251,Mercer", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", "W4th", 
        "kehan@nyu.edu", "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook(" \n\t");
    assertTrue(entries.isEmpty());
  }
  
  /*
   * Bug - serachAddressBook returns all entries that exist in 
   * addressBook when searching for "" string. It should only return entries 
   * with property that have empty string
   */
  @Test
  public void testSearchAddressBook_emptyString() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", "W4th", 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jude", "+12345678", "", 
        "nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Sam", "+12345678", null, 
        null, "Schoolmate"));
    List<AddressBookEntry> entries = addressbook.searchAddressBook("");
    assertEquals(1, entries.size());
  }
  
  @Test
  public void testSaveToFile_simple() {
    AddressBook addressbook = new AddressBook();
    assertTrue(addressbook.addNewContact("Aqua", "+12345678", "251 Mercer St", 
        "aqua@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Kehan", "+12345678", "133 E 13 St", 
        "kehan@nyu.edu", "Schoolmate"));
    assertTrue(addressbook.addNewContact("Jill", "+12345678", "W 4th St", 
        "jill@nyu.edu", "Collegemate"));
    try {
      addressbook.saveToFile("AddressBook.txt");
    } catch (IOException filewrite) { }
  }
  
  /*
   * Bug - When a pathToFile is null, then readFromFile() should throw 
   * IllegalArgumentException but author throws NullPointerException
   */
  @Test (expected = IllegalArgumentException.class)
  public void testSaveToFile_illegalArgument() {
    AddressBook addressBook = new AddressBook();
    try {
      addressBook.saveToFile(null);
    } catch (IOException e) { }
  }
  
  @Test
  public void testReadFromFile_simple() {
    AddressBook addressBook = new AddressBook();
    AddressBook newbook = null;
    try {
      newbook = addressBook.readFromFile("AddressBook.txt");
    } catch (StringIndexOutOfBoundsException | IOException fileread) { }
    List<AddressBookEntry> entries = newbook.searchAddressBook("Aqua");
    entries.addAll(newbook.searchAddressBook("Collegemate"));
    assertEquals("Aqua +12345678 251 Mercer St aqua@nyu.edu Schoolmate", 
        entries.get(0).toString());
  }
  
  @Test
  public void testSaveToFileAndReadFromFile_sameEntry() {
    AddressBook addressBook = new AddressBook();
    assertTrue(addressBook.addNewContact("Helen", null, null, null, null));
    AddressBook newBook = null;
    try {
      addressBook.saveToFile("AddressBookWithNulls.txt");
      newBook = addressBook.readFromFile("AddressBookWithNulls.txt");
    } catch (IOException e) { }
    assertEquals(addressBook.searchAddressBook("Helen").get(0).toString(), 
        newBook.searchAddressBook("Helen").get(0).toString());
  }
  
  /*
   * Bug - Author assues null = "" which is wrong. Broken for fields with null and "".
   * AddressBookEntry ("Helen", null, null, null, null) != 
   * ("Helen", "", "", null, null)
   * However, the authors code gives 
   * AddressBookEntry ("Helen", null, null, null, null) = 
   * ("Helen", "", "", null, null)
   */
  @Test
  public void testSaveToFileAndReadFromFile_differentEntry() {
    AddressBook addressBook = new AddressBook();
    assertTrue(addressBook.addNewContact("Helen", "", "", null, null));
    AddressBook newBook = null;
    try {
      newBook = addressBook.readFromFile("AddressBookWithNulls.txt");
    } catch (IOException e) { }
    assertNotEquals(addressBook.searchAddressBook("Helen").get(0).toString(), 
        newBook.searchAddressBook("Helen").get(0).toString());
  }
  
  @Test
  public void testSaveToFileAndReadFromFile_entryWithNewLineCharacter() {
    AddressBook addressBook = new AddressBook();
    assertTrue(addressBook.addNewContact("Helen", "Mouse\nCat\nLion", 
        "251,Mercer St", "example.edu", null));
    AddressBook newBook = null;
    try {
      addressBook.saveToFile("AddressBookEntryWithNewLineCharacter.txt");
      newBook = addressBook.readFromFile("AddressBookEntryWithNewLineCharacter.txt");
    } catch (IOException e) { }
    assertEquals(addressBook.searchAddressBook("Helen").get(0).toString(), 
        newBook.searchAddressBook("Helen").get(0).toString());
  }
  
  /*
   * Bug - When a pathToFile is not found, then readFromFile() should throw 
   * FileNotFoundException
   */
  @Test (expected = FileNotFoundException.class)
  public void testReadFromFile_fileNotFound() {
    AddressBook addressBook = new AddressBook();
    try {
      addressBook = addressBook.readFromFile("AddressBookEntry1.txt");
    } catch (IOException e) { }
  }
  
  /*
   * Bug - When a pathToFile is null, then readFromFile() should throw 
   * IllegalArgumentException but author throws IOException
   */
  @Test (expected = IllegalArgumentException.class)
  public void testReadFromFile_illegalArgument() {
    AddressBook addressBook = new AddressBook();
    try {
      addressBook = addressBook.readFromFile(null);
    } catch (IOException e) { }
  }
  
  /*
   * Bug - readFromfile when called should add the read AddressBookEntries to 
   * the existing addressBook object. Instead, author's code creates a new addressBook, 
   * reads the AddressBookEntries, adds the entries to new AddressBook and 
   * returns the newly created AddressBook.
   * 
   */
  @Test
  public void testReadFromFile_addAddressBookEntryToSameAddressBook() {
    AddressBook addressBook = new AddressBook();
    assertTrue(addressBook.addNewContact("Helen", "Mouse\nCat\nLion", "251,Mercer St", 
        "example.edu", null));
    try {
      addressBook = addressBook.readFromFile("AddressBookWithNulls.txt");
    } catch (IOException e) { }
    assertEquals(2, addressBook.searchAddressBook("Helen").size());
  }
  
  /*
   * Bug - When a file contains null as properties, then readFromFile()
   * throws NullPointerException while reading the file.
   */
  @Test
  public void testReadFromFile_readNullFields() {
    AddressBook addressBook = new AddressBook();
    try {
      addressBook = addressBook.readFromFile(""
          + "AddressBookNullProperty.txt");
    } catch (IOException e) { }
    assertEquals("Aqua", addressBook.searchAddressBook("Aqua").get(0).getName());
  }
}

