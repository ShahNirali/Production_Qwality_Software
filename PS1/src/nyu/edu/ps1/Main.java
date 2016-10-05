package nyu.edu.ps1;

import java.util.ArrayList;

public class Main {
  public static void main(String args[]){
    AddressBook addressbook = AddressBook.newInstance();
    
    Entry entry1 = new Entry.Builder("Jack", "544, Summ").build();
    Entry entry2 = new Entry.Builder("Simon", "").build();
    Entry entry3 = new Entry.Builder("Peter", "Clifton Place")
        .phoneNumber("2334342").build();
    Entry entry4 = new Entry.Builder("Shell", "Hoboken")
        .note("Temporary Address").build();
    Entry entry5 = new Entry.Builder("Simon", "").build();
    
    addressbook.add(entry1);
    addressbook.add(entry2);
    addressbook.add(entry2);
    addressbook.add(entry3);
    addressbook.add(entry4);
    addressbook.add(entry5);
    
    addressbook.save("AddressBook.txt");
    addressbook.read("AddressBook.txt");
    addressbook.remove(entry2);
    //addressbook.save("AddressBook1.txt");
    
    ArrayList<Entry> searchResults = addressbook.search(Property.NAME, "Simon");
    
    
  }
}
