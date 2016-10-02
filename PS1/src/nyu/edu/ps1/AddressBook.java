package nyu.edu.ps1;

import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;

public class AddressBook {
  private static final Logger logger = Logger.getLogger(AddressBook.class.getName());
  private List<Entry> list;
  
  //Suppress default constructor for non instantiability
  private AddressBook(){
    throw new AssertionError();
  }
  
  public static AddressBook newInstance(){
    AddressBook addressbook = new AddressBook();
    addressbook.list = new ArrayList<Entry>();
    logger.info("New AddressBook successfully created");
    return addressbook;
  }
  
  public boolean add(Entry entry){
    if(entry == null || entry.getName() == null || entry.getPostalAddress() == null ){
      logger.info("Error: Invalid Entry passed as parameter");
      throw new NullPointerException();
    }
    logger.info(entry.toString() + " added to AddressBook");
    return list.add(entry);
  }
  
  public void remove(Entry entry){
    if(entry == null){
      throw new NullPointerException();
    }
    logger.info(entry.toString() + " removed from AddressBook");
    list.remove(entry);
    
  }
  
  public ArrayList<Entry> searchBook(String searchString){
    if(searchString == null){
      throw new NullPointerException();
    }
    
  }
  
  public String toString(){
    
  }
  
  
}
