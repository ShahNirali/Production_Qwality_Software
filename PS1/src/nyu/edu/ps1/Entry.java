package nyu.edu.ps1;

public class Entry {
  private String name;
  private String postalAddress; 
  private String phoneNumber;
  private String emailAddress;
  private String note;
  
  public class Builder{
    //Required parameter
    private String name;
    private String postalAddress;
    
    //Optional parameter - initialized to default values (here null)
    private String phoneNumber;
    private String emailAddress;
    private String note;
      
    public Builder(String name, String postalAddress){
      this.name = name;
      this.postalAddress = postalAddress;
    }
    
    public Builder setPhoneNumber(String phoneNumber){
      this.phoneNumber = phoneNumber;
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
  
  public String getName(){
    return this.name;
  }
  
  public String getPhoneNumber(){
    return this.phoneNumber;
  }
  
  public String getPostalAddress(){
    return this.postalAddress;
  }
  
  public String getEmailAddress(){
    return this.emailAddress;
  }
  
  public String getNote(){
    return this.note;
  }
  
  public String toString(){
    return this.getName() + " "
           + this.getPostalAddress() + " "
           + this.getPhoneNumber() + " "
           + this.getEmailAddress() + " "
           + this.getNote();
  }
}
