package nyu.edu.ps1;

import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * AddressBook is a library that is to store address of people
 * in an arraylist thus providing functionality to add, remove, search
 * along with added functionality of loading contacts from file and 
 * storing object into a file. 
 * 
 *  
 * @author nns271
 */
public class AddressBook {
  private static final Logger logger = 
      Logger.getLogger(AddressBook.class.getName());
  //Properties of AddressBook
  private ArrayList<Entry> list;
  private int hashcode;
  
  /**
   * This is a static factory method used to create an instance
   * of AddressBook. It also performs initialization of 
   * properties of AddressBook. 
   * 
   * It logs into journal after initialization is complete.
   *  
   * @return      new object of AddressBook
   */
  public static AddressBook newInstance(){
    AddressBook addressbook = new AddressBook();
    addressbook.list = new ArrayList<Entry>();
    logger.info("New AddressBook successfully created");
    return addressbook;
  }
  
  /**
   * This is an accessor method of AddressBook used to fetch 
   * entries of the AddressBook
   *  
   * @return      an arraylist of entries of object of
   *              AddressBook
   */
  public ArrayList<Entry> getList(){
    return list;
  }
  

  /**
   * Add method is used to add an entry to arraylist of AddressBook object. 
   *  
   * @param       entry object that needs to be inserted
   * @return      boolean signifying if the insertion was successful
   * @throws      an IllegalArgumentException in following cases:
   *              when @param is null
   *              when mandatory properties of entry are null 
   */
  public boolean add(Entry entry){
    if(entry == null || entry.getName() ==null || entry.getPostalAddress() == null){
      logger.warning("Error: Invalid Entry to add to addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    logger.info(entry.toString() + " added to AddressBook");
    return list.add(entry);
  }
  
  /**
   * Remove method is used to remove entry from arraylist of AddressBook 
   * object. It also removes duplicate entries from AddressBook by 
   * traversing through entire arraylist. 
   *  
   * @param       entry object that needs to be removed
   * @throws      an IllegalArgumentException when @param is null
   * @throws      IndexOutOfBoundsException when the AddressBook is empty
   *              and user tries to remove entries
   */
  public void remove(Entry entry){
    if(entry == null){
      logger.warning("Error: Invalid Entry to remove from addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    if(list.isEmpty()){
      logger.warning("Error: No entry in addressbook to remove");
      throw new IndexOutOfBoundsException("List is empty");
    }
    removeDuplicates(entry);
    logger.info(entry.toString() + " removed from AddressBook");
  }
  
  private void removeDuplicates(Entry entry){
    ArrayList<Entry> nonDupList = new ArrayList<Entry>();
    Iterator<Entry> iterator = list.iterator();
    while(iterator.hasNext()){
      Entry newEntry = iterator.next();
      if(newEntry.equals(entry)){
        iterator.remove();
      }
      else{
        nonDupList.add(newEntry);
      }
    }
    list = nonDupList;
  }
  
  /**
   * Search method is used to search a particular string in AddressBook 
   * by the property specified by the user. The property should 
   * be one present in enum class Property.
   *  
   * @param       property of the entry 
   * @param       word or sentence to search
   * @return      entries of addressBook that match the param passed
   * @throws      an IllegalArgumentException when string param is null
   * @throws      IndexOutOfBoundsException when the AddressBook is empty
   * @throws      AssertionError when property param is not the one
   *              in enum
   */
  public ArrayList<Entry> search(Property property, String searchString){
    if(searchString == null){
      logger.warning("Error: Invalid String to search in addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    if(list.isEmpty()){
      logger.warning("Error: No entry in addressbook");
      throw new IndexOutOfBoundsException("AddressBook is empty");
    }
    
    ArrayList<Entry> searchResult = new ArrayList<Entry>();
    switch(property){
    case NAME:
      for(int i = 0; i < list.size(); i++){
        if(searchString.equalsIgnoreCase(list.get(i).getName())){
          searchResult.add(list.get(i));
        }
      }
      break;
    case POSTALADDRESS: case ADDRESS:
      for(int i = 0; i < list.size(); i++){
        if(searchString.equalsIgnoreCase(list.get(i).getPostalAddress())){
          searchResult.add(list.get(i));
        }
      }
      break;
    case PHONENUMBER: case PHONE:
      for(int i = 0; i < list.size(); i++){
        if(searchString.equalsIgnoreCase(list.get(i).getPhoneNumber())){
          searchResult.add(list.get(i));
        }
      }
      break;
    case EMAILADDRESS: case EMAIL:
      for(int i = 0; i < list.size(); i++){
        if(searchString.equalsIgnoreCase(list.get(i).getEmailAddress())){
          searchResult.add(list.get(i));
        }
      }
      break;
    case NOTE:
      for(int i = 0; i < list.size(); i++){
        if(searchString.equalsIgnoreCase(list.get(i).getNote())){
          searchResult.add(list.get(i));
        }
      }
      break;
    default:
      logger.warning("Error: Invalid property to search in addressbook");
      throw new AssertionError("Unknown property");
    }
    return searchResult;
  }
  
  /**
   * Save method writes all the entries using toString method
   * of the AddressBook to file specified.
   * It writes using BufferedWriter object thus NOT using serialization 
   * property of ArrayList.
   *  
   * @param       filename to which the AddressBook should be written 
   * @throws      an IllegalArgumentException when filename is null
   * @throws      IOException when the bufferedWriter encounters problems
   *              during write operation. This is handled by try-catch
   */
  public void save(String filename){
    if(filename == null){
      logger.warning("Error: Invalid filename to save addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    
    try {
      File file = new File(filename);
      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(this.toString());
      logger.info("AddressBook successfully saved to " + filename);
      bufferedWriter.close();
    }
    catch(IOException bufferedWriter) {
      logger.warning("Error: Write failed while saving addresses to file");
    }
  }
  
  /**
   * Read method reads a file line by line and loads data into Arraylist
   * of AddressBook. This is achieved by BufferedReader's read method thus
   * NOT using serialization of Arraylist.
   *  
   * @param       filename from which the arraylist should be loaded 
   * @throws      an IllegalArgumentException when filename is null
   * @throws      FileNotFoundException when the file specified in param
   *              couldn't be found. This is handled by try-catch
   * @throws      IOException when the bufferedReader encounters problems
   *              during read operation. This is handled by try-catch
   */
  public void read(String filename){
    if(filename == null){
      logger.warning("Error: Invalid filename to read");
      throw new IllegalArgumentException("Can't be empty");
    }
    String line;
    try{
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        if(line.isEmpty()){
          continue;
        }
        String[] data = line.split("\\|");
        Entry newentry = 
            new Entry.Builder(data.length >= 1 ? (data[0].equals("null") ? null : data[0]) : null, 
                data.length >= 2 ? (data[1].equals("null") ? null : data[1]) : null)
            .phoneNumber(data.length >= 3 ? (data[2].equals("null") ? null : data[2]) : null)
            .emailAddress(data.length >= 4 ? (data[3].equals("null") ? null : data[3]) : null)
            .note(data.length >= 5 ? (data[4].equals("null") ? null : data[4]) : null).build();
        add(newentry);
      }
      bufferedReader.close();
      logger.info("Added all contacts from " + filename + " to Addressbook");
    }
    catch(FileNotFoundException fileNotFound){
      logger.warning("Error: " + filename + " not found");
    }
    catch(IOException reader){
      logger.warning("Error: Read failed while loading addresses from file");
    }
  }
  
  /**
   * Equals is overriden method of Object. It checks if two AddressBook
   * objects are similar by content by also checking if they have same
   * reference.
   *  
   * @return      boolean value i.e. true if objects are similar else 
   *              false
   */
  @Override
  public boolean equals(Object obj) {
    if(obj == this){
      return true;
    }
    if(!(obj instanceof AddressBook)){
      return false;
    }
    AddressBook newAddress = (AddressBook)obj;
    if(this != null && newAddress != null 
        && this.list == null && newAddress.getList() == null){
      return true;
    }
    if(this != null && this.list != null && newAddress.getList() != null
        && this.list.size() == newAddress.getList().size()){
      ArrayList<Entry> entries = newAddress.getList();
      for(int i = 0; i < list.size() ; i++){
        if(!(list.get(i).equals(entries.get(i)))){
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  /**
   * hashcode method returns hash value of all the entries present in
   * arraylist of AddressBook.
   *  
   * @return      hashcode of AddressBook object
   */
  @Override
  public int hashCode() {
    int result = hashcode;
    if(result == 0){
      for(Entry entry: list){
        result = 17 + entry.hashCode();
      }
      hashcode = result;
    }
    return hashcode;
  }
  
  /**
   * toString method traverses through all the entries while converting 
   * them to string.
   *  
   * @return      string of content of object AddressBook
   */
  @Override
  public String toString(){
    StringBuffer result = new StringBuffer();
    for(Entry entry: list){
      result.append(entry.toString() + "\n");
    }
    return result.toString();
  }
}
