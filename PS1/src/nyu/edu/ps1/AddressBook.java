package nyu.edu.ps1;

import java.util.Scanner;
import java.util.logging.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AddressBook {
  private static final Logger logger = Logger.getLogger(AddressBook.class.getName());
  private ArrayList<Entry> list;
  
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
    logger.info(entry.toString() + " removed from AddressBook");
    list.remove(entry);
    
  }
  
  public ArrayList<Entry> search(Property property, String searchString){
    if(searchString == null || property == null){
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
  
  public void save(String filename){
    try {
      FileWriter fileWriter = new FileWriter(filename);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
      bufferedWriter.write(this.toString());
      bufferedWriter.close();
    }
    catch(IOException bufferedWriter) {
    }
  }
  
  public AddressBook read(String filename){
    AddressBook newBook = newInstance();
    try{
      Scanner fileRead = new Scanner(new File(filename));
      fileRead.useDelimiter("|\n");
      while(fileRead.hasNext()) {
        String name = fileRead.next();
        String postalAddress = fileRead.next();
        String phoneNumber = fileRead.next();
        String emailAddress = fileRead.next();
        String note = fileRead.next();
        
        Entry newEntry = new Entry.Builder(name, postalAddress)
        .setPhoneNumber(phoneNumber)
        .setEmailAddress(emailAddress)
        .setNote(note).build();
        newBook.add(newEntry);
      }
    }
    catch(FileNotFoundException fileNotFound){
    }
    return newBook;
  }
  
  @Override
  public String toString(){
    StringBuffer result = new StringBuffer();
    ArrayList<Entry> entries = this.getList();
    for(Entry entry: entries){
      result.append(entry.toString() + "\n");
    }
    return result.toString();
  }
  
  
}
