

public class AddressEntry {
  private String name;
  private String postalAddress; 
  private int phoneNumber;
  private String emailAddress;
  private String note;
  
  public class Builder{
    private String name;
    private int phoneNumber;
    private String postalAddress="";
    private String emailAddress="";
    private String note="";
      
    public Builder(){
    }
      
    public Builder setName(String name){
      this.name = name;
      return this;
    }
    
    public Builder setPhoneNumber(int phoneNumber){
      this.phoneNumber = phoneNumber;
      return this;
    }
    
    public Builder setPostalAddress(String postalAddress){
      this.postalAddress = postalAddress;
      return this;
    }
    
    public Builder setEmailAddress(String emailAddress){
      this.emailAddress = emailAddress;
      return this;
    }
    
    public Builder setNote(String note){
      this.note = note;
      return this;
    }
    
    public AddressEntry build(){
      return new AddressEntry(this);
    }
  }
  
  private AddressEntry(Builder builder){
    this.name = builder.name;
    this.phoneNumber = builder.phoneNumber;
    this.postalAddress = builder.postalAddress;
    this.emailAddress = builder.emailAddress;
    this.note = builder.note;
  }
}
