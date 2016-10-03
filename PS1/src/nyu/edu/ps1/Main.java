package nyu.edu.ps1;

public class Main {
  public static void main(String args[]){
    AddressBook addressbook = AddressBook.newInstance();
    
    Entry entry1 = new Entry.Builder("Jack", "544, Summ").build();
    Entry entry2 = new Entry.Builder("Simon", "").build();
    Entry entry3 = new Entry.Builder("Peter", "Clifton Place")
        .setPhoneNumber("2334342").build();
    Entry entry4 = new Entry.Builder("Shell", "Hoboken")
        .setNote("Temporary Address").build();
    
    addressbook.add(entry1);
    addressbook.add(entry2);
    addressbook.add(entry3);
    
    AddressBook.save(addressbook, "AddressBook.txt");
    addressbook = AddressBook.read("AddressBook.txt");
    addressbook.remove(entry2);
    AddressBook.save(addressbook, "AddressBook.txt");
  }
}
