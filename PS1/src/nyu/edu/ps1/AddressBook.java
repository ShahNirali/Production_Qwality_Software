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

public class AddressBook {
  private static final Logger logger = Logger.getLogger(AddressBook.class.getName());
  private ArrayList<Entry> list;
  
  public static AddressBook newInstance(){
    AddressBook addressbook = new AddressBook();
    addressbook.list = new ArrayList<Entry>();
    logger.info("New AddressBook successfully created");
    return addressbook;
  }
  
  public ArrayList<Entry> getList(){
    return list;
  }
  
  public boolean add(Entry entry){
    if(entry == null || entry.getName() == null || entry.getPostalAddress() == null ){
      logger.warning("Error: Invalid Entry to add to addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    logger.info(entry.toString() + " added to AddressBook");
    return list.add(entry);
  }
  
  public void remove(Entry entry){
    if(entry == null){
      logger.warning("Error: Invalid Entry to remove from addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    for(int index = 0; index < list.size(); index++){
      if(entry.equals(list.get(index))){
        list.remove(index);
      }
    }
    logger.info(entry.toString() + " removed from AddressBook");
  }
  
  public ArrayList<Entry> search(Property property, String searchString){
    if(searchString == null){
      logger.warning("Error: Invalid String to search in addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    
    ArrayList<Entry> searchResult = new ArrayList<Entry>();
    ArrayList<Entry> entries = this.getList();
    switch(property){
    case NAME:
      for(int i = 0; i < entries.size(); i++){
        if(searchString.equalsIgnoreCase(entries.get(i).getName())){
          searchResult.add(entries.get(i));
        }
      }
      break;
    case POSTALADDRESS: case ADDRESS:
      for(int i = 0; i < entries.size(); i++){
        if(searchString.equalsIgnoreCase(entries.get(i).getPostalAddress())){
          searchResult.add(entries.get(i));
        }
      }
      break;
    case PHONENUMBER: case PHONE:
      for(int i = 0; i < entries.size(); i++){
        if(searchString.equalsIgnoreCase(entries.get(i).getPhoneNumber())){
          searchResult.add(entries.get(i));
        }
      }
      break;
    case EMAILADDRESS: case EMAIL:
      for(int i = 0; i < entries.size(); i++){
        if(searchString.equalsIgnoreCase(entries.get(i).getEmailAddress())){
          searchResult.add(entries.get(i));
        }
      }
      break;
    case NOTE:
      for(int i = 0; i < entries.size(); i++){
        if(searchString.equalsIgnoreCase(entries.get(i).getNote())){
          searchResult.add(entries.get(i));
        }
      }
      break;
    default:
      logger.warning("Error: Invalid property to search in addressbook");
      throw new AssertionError("Unknown property");
    }
    return searchResult;
  }
  
  public static void save(AddressBook addressBook, String filename){
    if(addressBook == null){
      logger.warning("Error: Invalid AddressBook to save");
      throw new IllegalArgumentException("Can't be empty");
    }
    if(filename == null){
      logger.warning("Error: Invalid filename to save addressbook");
      throw new IllegalArgumentException("Can't be empty");
    }
    
    try {
      File file = new File(filename);
      if(!file.exists()){
        file.createNewFile();
      }
      else{
        logger.info("File " + filename + " exists so appending to file");
      }
      FileWriter fileWriter = new FileWriter(file,true);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(addressBook.toString());
      logger.info("AddressBook successfully saved to " + filename);
      bufferedWriter.close();
    }
    catch(IOException bufferedWriter) {
      logger.warning("Error: Write failed while saving addresses to file");
    }
  }
  
  public static AddressBook read(String filename){
    if(filename == null){
      logger.warning("Error: Invalid filename to read");
      throw new IllegalArgumentException("Can't be empty");
    }
    String line;
    AddressBook addressBook = newInstance();
    try{
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while((line = bufferedReader.readLine()) != null) {
        if(line.isEmpty()){
          continue;
        }
        String[] data = line.split("\\|");
        Entry newentry = new Entry.Builder(data[0], data[1])
            .setPhoneNumber(data.length >= 3 ? data[2] : null)
            .setEmailAddress(data.length >= 4 ? data[3] : null)
            .setNote(data.length >= 5 ? data[2] : null).build();
        addressBook.add(newentry);
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
    return addressBook;
  }
  
  @Override
  public String toString(){
    StringBuffer result = new StringBuffer();
    for(Entry entry: list){
      result.append(entry.toString() + "\n");
    }
    return result.toString();
  }
}
