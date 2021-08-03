/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
class Reservation {
    private String reservationID, guestID, firstName, lastName, checkIn, checkOut;
    public Reservation(String reservationID, String guestID, String firstName, String lastName, String checkIn, String checkOut)
    {
        this.reservationID=reservationID;
        this.guestID=guestID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
    }
    
    public String getreservationID()
    {
        return reservationID;
    }
    
    public String getguestID()
    {
        return guestID;
    }
    
    public String getfirstName()
    {
        return firstName;
    }
    
    public String getlastName()
    {
        return lastName;
    }
    
    public String getcheckIn()
    {
        return checkIn;
    }
    
    public String getcheckOut()
    {
        return checkOut;
    }
}
