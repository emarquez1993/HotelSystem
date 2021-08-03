/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
class CurrentGuest {
    private String guestID, firstName, lastName, roomNumber, physicalCheckIn, checkOut;
    public CurrentGuest(String guestID, String firstName, String lastName, String roomNumber, String physicalCheckIn, String checkOut)
    {
        this.guestID=guestID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.roomNumber=roomNumber;
        this.physicalCheckIn=physicalCheckIn;
        this.checkOut=checkOut;
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
    public String getroomNumber()
    {
        return roomNumber;
    }
    public String getphysicalCheckIn()
    {
        return physicalCheckIn;
    }
    public String getcheckOut()
    {
        return checkOut;
    }
}
