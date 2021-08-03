/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
class Service {
    private String reservationID, firstName, lastName, serviceID, qty;
    
    public Service(String reservationID, String firstName, String lastName, String serviceID, String qty)
    {
        this.reservationID=reservationID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.serviceID=serviceID;
        this.qty=qty;
    }
    
    public String getreservationID()
    {
        return reservationID;
    }
    
    public String getfirstName()
    {
        return firstName;
    }
    
    public String getlastName()
    {
        return lastName;
    }
    
    public String getserviceID()
    {
        return serviceID;
    }
    
    public String getqty()
    {
        return qty;
    }
}
