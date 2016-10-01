import java.util.List;
import java.util.ArrayList;

public class AddressBook {
  private AddressEntry entry;
  private List<AddressEntry> list;
  
  private AddressBook(){
    list=new ArrayList<AddressEntry>();
  }
  
  private void createEmptyBook(){
    
  }
  
  private void add(AddressEntry address){
    list.add(address);
  }
  
  private void remove(AddressEntry address){
    list.remove(address);
  }
  
  private boolean search(AddressEntry address){
    if(list.contains(address)){
      return true;
    }
    return false;
  }
  
  public static void main(String args[]){
    AddressEntry entry=new AddressEntry.Builder().setName("ABC").setPhoneNumber(0)
  }
  
  
}
