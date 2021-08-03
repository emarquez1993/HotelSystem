/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
class Guest {
    private String guestID, firstName, lastName, address1, address2, city, state, zipcode, phone, email, creditCard;
    
    public Guest(String guestID, String firstName, String lastName, String address1, String address2, String city, String state, String zipcode, String phone, String email, String creditCard)
    {
        this.guestID=guestID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.state=state;
        this.zipcode=zipcode;
        this.phone=phone;
        this.email=email;
        this.creditCard=creditCard;
    }
    
    public String getguestID(){
        return guestID;
    }
    
    public String getfirstName(){
        return firstName;
    }
    
    public String getlastName(){
        return lastName;
    }
    
    public String getaddress1(){
        return address1;
    }
    
    public String getaddress2(){
        return address2;
    }
    
    public String getcity(){
        return city;
    }
    
    public String getstate(){
        return state;
    }
    
    public String getzipcode(){
        return zipcode;
    }
    
    public String getphone(){
        return phone;
    }
    
    public String getemail(){
        return email;
    }
    
    public String getcreditCard(){
        return creditCard;
    }
}
