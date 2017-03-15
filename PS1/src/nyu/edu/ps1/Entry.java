package nyu.edu.ps1;

/**
 * This class is used to form addressbook object that has following property:
 * Name
 * PostalAddress
 * PhoneNumber
 * EmailAddress
 * Note
 * 
 *  
 * @author Nirali      
 */
public class Entry {
  //Properties of AddressBook
  private String name;
  private String postalAddress; 
  private String phoneNumber;
  private String emailAddress;
  private String note;
  
  private int hashcode;
 
  /**
   * This class is used to form Entry object with required and optional 
   * member fields name, postaladdress, phonenumber, emailaddress and note.
   *  
   * @author nns271      
   */
  public static class Builder{
    //Required parameter
    private String name;
    private String postalAddress;
    
    //Optional parameter - initialized to default values (here null)
    private String phoneNumber;
    private String emailAddress;
    private String note;
      
    /**
     * This is constructor of Builder class. 
     *  
     * @param       name to set name field of entry
     * @param       postalAddress to set postalAddress field of entry
     */
    public Builder(String name, String postalAddress){
      this.name = name;
      this.postalAddress = postalAddress;
    }
    
    /**
     * Used to set phonenumber of entry
     *  
     * @return      builder object with phonenumber value set
     */
    public Builder phoneNumber(String phoneNumber){
      this.phoneNumber = phoneNumber;
      return this;
    }
    
    /**
     * Used to set emailAddress of entry
     *  
     * @return      builder object with emailAddress value set
     */
    public Builder emailAddress(String emailAddress){
      this.emailAddress = emailAddress;
      return this;
    }
    
    /**
     * Used to set note of entry
     *  
     * @return      builder object with note value set
     */
    public Builder note(String note){
      this.note = note;
      return this;
    }
    
    /**
     * build method is used to form Entry object
     *  
     * @return      entry object with member variables set 
     */
    public Entry build(){
      return new Entry(this);
    }
  }
  
  private Entry(Builder builder){
    this.name = builder.name;
    this.phoneNumber = builder.phoneNumber;
    this.postalAddress = builder.postalAddress;
    this.emailAddress = builder.emailAddress;
    this.note = builder.note;
  }
  
  /**
   * Accessor method for property name
   *  
   * @return      string i.e. name value of entry object
   */
  public String getName(){
    return this.name;
  }
  
  /**
   * Accessor method for property PhoneNumber
   *  
   * @return      string i.e. phonenumber value of entry object
   */
  public String getPhoneNumber(){
    return this.phoneNumber;
  }
  
  /**
   * Accessor method for property postalAddress
   *  
   * @return      string i.e. postalAddress value of entry object
   */
  public String getPostalAddress(){
    return this.postalAddress;
  }
  
  /**
   * Accessor method for property EmailAddress
   *  
   * @return      string i.e. emailaddress value of entry object
   */
  public String getEmailAddress(){
    return this.emailAddress;
  }
  
  /**
   * Accessor method for property Note
   *  
   * @return      string i.e. note value of entry object
   */
  public String getNote(){
    return this.note;
  }
  
  /**
   * equals method is overriden method of Object to check if two Entry 
   * objects are similar.
   *  
   * @return      boolean value i.e true if two objects have same content
   *              else false
   */
  @Override
  public boolean equals(Object obj) {
    if(obj == this){
      return true;
    }
    if(!(obj instanceof Entry)){
      return false;
    }
    Entry newEntry = (Entry)obj;
    if((name != null && name.equals(newEntry.getName()) || (name == null && newEntry.getName() == null))
        && (postalAddress != null && postalAddress.equals(newEntry.getPostalAddress()) 
        || (postalAddress == null && newEntry.getPostalAddress() == null))
        && (phoneNumber != null && phoneNumber.equals(newEntry.getPhoneNumber())
        || (phoneNumber == null && newEntry.getPhoneNumber() == null))
        && (emailAddress != null && emailAddress.equals(newEntry.getEmailAddress())
        || (emailAddress == null && newEntry.getEmailAddress() == null))
        && (note != null && note.equals(newEntry.getNote())
        || (note == null && newEntry.getNote() == null))){
      return true;
    }
    return false;
  }
  
  /**
   * hashcode method form hashcode for object Entry by using hashcode of string
   *  
   * @return      int value formed by adding all hashes
   */
  @Override
  public int hashCode() {
    int result = hashcode;
    if(result == 0){
      result = 17;
      if(name != null){
        result += result * 31 + name.hashCode();
      }
      if(postalAddress != null){
        result += result * 31 + postalAddress.hashCode();
      }
      if(phoneNumber != null){
        result += result * 31 + phoneNumber.hashCode();
      }
      if(emailAddress != null){
        result += result * 31 + emailAddress.hashCode();
      }
      if(note != null){
        result += result * 31 + note.hashCode();
      }
      hashcode = result;
    }
    return hashcode;
  }
  
  /**
   * toString method converts Entry object to string
   *  
   * @return      string of content of object entry
   */
  @Override
  public String toString(){
    return (this.name == null ? "null" : this.name) + "|"
           + (this.postalAddress == null ? "null" : this.postalAddress) + "|"
           + (this.phoneNumber == null ? "null" : this.phoneNumber) + "|"
           + (this.emailAddress == null ? "null" : this.emailAddress) + "|"
           + (this.note == null ? "null" : this.note);
  }
}
