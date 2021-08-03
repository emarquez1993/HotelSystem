
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */
public class Hotel_System extends javax.swing.JFrame {
    
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public static Connection con;
    public static Statement st;
    public static ResultSet rs;

    /**
     * Creates new form Hotel_System
     */
    public Hotel_System() {
        initComponents();
        show_Guest();
        show_CurrentGuest();
        show_Service();
        show_Reservation();
    }
    
    public ArrayList<Guest> guestList()
    {
        ArrayList<Guest> guestList = new ArrayList<>();
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection("jdbc:ucanaccess://E:\\HotelSystem.accdb");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        String sql = "SELECT * FROM Guests;";
                        rs = st.executeQuery(sql);
                        Guest guest;
                        while(rs.next())
                        {
                            guest = new Guest(rs.getString("GuestsID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Address1"), rs.getString("Address2"), rs.getString("City"), rs.getString("State"), rs.getString("Zipcode"), rs.getString("Phone"), rs.getString("Email"), rs.getString("CreditCard"));
                            guestList.add(guest);
                        }
        }
        catch(Exception e)
        {
            
        }
        return guestList;
    }
    
    public void show_Guest()
    {
        ArrayList<Guest> list = guestList();
        DefaultTableModel gmodel = (DefaultTableModel)jTable_Guest.getModel();
        gmodel.setRowCount(0);
        Object[] row = new Object[11];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getguestID();
            row[1] = list.get(i).getfirstName();
            row[2] = list.get(i).getlastName();
            row[3] = list.get(i).getaddress1();
            row[4] = list.get(i).getaddress2();
            row[5] = list.get(i).getcity();
            row[6] = list.get(i).getstate();
            row[7] = list.get(i).getzipcode();
            row[8] = list.get(i).getphone();
            row[9] = list.get(i).getemail();
            row[10] = list.get(i).getcreditCard();
            gmodel.addRow(row);
        }
    }
    
    public ArrayList<CurrentGuest> currentGuestList()
    {
        ArrayList<CurrentGuest> currentGuestList = new ArrayList<>();
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        con.commit();
			con = DriverManager.getConnection("jdbc:ucanaccess://E:\\HotelSystem.accdb");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        System.out.println("before SQL");
                        String sql = "SELECT Guests.GuestsID, Guests.FirstName, Guests.LastName, CurrentGuests.RoomNumber, CurrentGuests.Physical_Check_In_Date, Reservations.CheckOut FROM (Guests INNER JOIN Reservations ON Guests.GuestsID = Reservations.GuestID) INNER JOIN CurrentGuests ON Guests.GuestsID = CurrentGuests.GuestsID;";
                        rs = st.executeQuery(sql);
                        System.out.println("After SQL.");
                        CurrentGuest cguest;
                        while(rs.next())
                        {
                            cguest = new CurrentGuest(rs.getString("GuestsID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("RoomNumber"), rs.getString("Physical_Check_In_Date"), rs.getString("CheckOut"));
                            currentGuestList.add(cguest);
                        }
        }
        catch(Exception e)
        {
            System.out.println("RIP");
        }
        return currentGuestList;
    }
    
    public void show_CurrentGuest()
    {
        ArrayList<CurrentGuest> list = currentGuestList();
        DefaultTableModel cgmodel = (DefaultTableModel)jTable_Current_Guests.getModel();
        cgmodel.setRowCount(0);
        Object[] row = new Object[6];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getguestID();
            row[1] = list.get(i).getfirstName();
            row[2] = list.get(i).getlastName();
            row[3] = list.get(i).getroomNumber();
            row[4] = list.get(i).getphysicalCheckIn();
            row[5] = list.get(i).getcheckOut();
            cgmodel.addRow(row);
        }
    }
    
    public ArrayList<Service> serviceList()
    {
        ArrayList<Service> serviceList = new ArrayList<>();
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        con.commit();
			con = DriverManager.getConnection("jdbc:ucanaccess://E:\\HotelSystem.accdb");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        System.out.println("before SQL");
                        String sql = "SELECT Reservations.ReservationID, Guests.FirstName, Guests.LastName, AddServices.ServiceID, AddServices.Quantity FROM (Guests INNER JOIN Reservations ON Guests.GuestsID = Reservations.GuestID) INNER JOIN AddServices ON Reservations.ReservationID = AddServices.ReservationID;";
                        rs = st.executeQuery(sql);
                        System.out.println("After SQL.");
                        Service service;
                        while(rs.next())
                        {
                            service = new Service(rs.getString("ReservationID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("ServiceID"), rs.getString("Quantity"));
                            serviceList.add(service);
                        }
        }
        catch(Exception e)
        {
            System.out.println("RIP");
        }
        return serviceList;
    }
    
    public void show_Service()
    {
        ArrayList<Service> list = serviceList();
        DefaultTableModel smodel = (DefaultTableModel)jTable_service.getModel();
        smodel.setRowCount(0);
        Object[] row = new Object[5];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getreservationID();
            row[1] = list.get(i).getfirstName();
            row[2] = list.get(i).getlastName();
            row[3] = list.get(i).getserviceID();
            row[4] = list.get(i).getqty();
            smodel.addRow(row);
        }
    }
    
    public ArrayList<Reservation> reservationList()
    {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                        con.commit();
			con = DriverManager.getConnection("jdbc:ucanaccess://E:\\HotelSystem.accdb");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                        System.out.println("before SQL");
                        String sql = "SELECT Reservations.ReservationID, Guests.GuestsID, Guests.FirstName, Guests.LastName, Reservations.CheckIn, Reservations.CheckOut FROM Guests INNER JOIN Reservations ON Guests.GuestsID = Reservations.GuestID;";
                        rs = st.executeQuery(sql);
                        System.out.println("After SQL.");
                        Reservation reservation;
                        while(rs.next())
                        {
                            reservation = new Reservation(rs.getString("ReservationID"), rs.getString("GuestsID"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("CheckIn"), rs.getString("CheckOut"));
                            reservationList.add(reservation);
                        }
        }
        catch(Exception e)
        {
            System.out.println("RIP");
        }
        return reservationList;
    }
    
    public void show_Reservation()
    {
        ArrayList<Reservation> list = reservationList();
        DefaultTableModel rmodel = (DefaultTableModel)jTable_Reservations.getModel();
        rmodel.setRowCount(0);
        Object[] row = new Object[6];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getreservationID();
            row[1] = list.get(i).getguestID();
            row[2] = list.get(i).getfirstName();
            row[3] = list.get(i).getlastName();
            row[4] = list.get(i).getcheckIn();
            row[5] = list.get(i).getcheckOut();
            rmodel.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        guestIdText = new javax.swing.JTextField();
        fnameText = new javax.swing.JTextField();
        lnameText = new javax.swing.JTextField();
        address1Text = new javax.swing.JTextField();
        address2Text = new javax.swing.JTextField();
        cityText = new javax.swing.JTextField();
        stateText = new javax.swing.JTextField();
        zipcodeText = new javax.swing.JTextField();
        phoneText = new javax.swing.JTextField();
        emailText = new javax.swing.JTextField();
        creditCardText = new javax.swing.JTextField();
        newReservation = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Guest = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_service = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_Current_Guests = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_Reservations = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        room1 = new javax.swing.JRadioButton();
        room2 = new javax.swing.JRadioButton();
        checkOutDate = new org.jdesktop.swingx.JXDatePicker();
        checkInDate = new org.jdesktop.swingx.JXDatePicker();
        roomStyle = new javax.swing.JComboBox<>();
        jButtonCheckin = new javax.swing.JButton();
        roomExtraYes = new javax.swing.JRadioButton();
        roomExtraNo = new javax.swing.JRadioButton();
        jLabel17 = new javax.swing.JLabel();
        wifiYes = new javax.swing.JRadioButton();
        wifiNo = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        roomServiceQty = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        diningQty = new javax.swing.JTextField();
        checkOutButton = new javax.swing.JButton();
        roomPatio = new javax.swing.JRadioButton();
        roomForest = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Guest ID");

        jLabel3.setText("First Name");

        jLabel4.setText("Last Name");

        jLabel5.setText("Address 1");

        jLabel6.setText("Address 2");

        jLabel7.setText("City");

        jLabel8.setText("State");

        jLabel9.setText("Zip Code");

        jLabel10.setText("Phone");

        jLabel11.setText("Email");

        jLabel12.setText("Credit Card");

        newReservation.setText("New Reservation");
        newReservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newReservationActionPerformed(evt);
            }
        });

        jTable_Guest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Guest ID", "First Name", "Last Name", "Address1", "Address 2", "City", "State", "Zip Code", "Phone", "Email", "Credit Card"
            }
        ));
        jTable_Guest.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_GuestMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_Guest);

        jTabbedPane1.addTab("Guests", jScrollPane1);

        jTable_service.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reservation ID", "First Name", "Last Name", "Service ID", "Qty"
            }
        ));
        jScrollPane2.setViewportView(jTable_service);

        jTabbedPane1.addTab("Services", jScrollPane2);

        jTable_Current_Guests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Guest ID", "First Name", "Last Name", "Room#", "Physical Check In Date", "Check Out Date"
            }
        ));
        jTable_Current_Guests.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Current_GuestsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable_Current_Guests);

        jTabbedPane1.addTab("Current Guests", jScrollPane4);

        jTable_Reservations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ReservationID", "GuestID", "First Name", "Last Name", "Check In Date", "Check Out Date"
            }
        ));
        jScrollPane3.setViewportView(jTable_Reservations);

        jTabbedPane1.addTab("Reservations", jScrollPane3);

        jLabel1.setText("Check In Date");

        jLabel13.setText("Check Out Date");

        jLabel14.setText("Room");

        jLabel15.setText("Room 1 or 2");

        jLabel16.setText("Room Extra");

        buttonGroup1.add(room1);
        room1.setText("1");

        buttonGroup1.add(room2);
        room2.setText("2");

        checkOutDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutDateActionPerformed(evt);
            }
        });

        checkInDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInDateActionPerformed(evt);
            }
        });

        roomStyle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Room Style", "Cottage Two Room", "Cottage Three Room", "Cottage Four Room", "Luxury 1 Queen bed", "Luxury 2 Queen beds", "Luxury Two Room", "Luxury Three Room", "Luxury Bridal", " ", " " }));

        jButtonCheckin.setText("Check In");
        jButtonCheckin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCheckinActionPerformed(evt);
            }
        });

        buttonGroup2.add(roomExtraYes);
        roomExtraYes.setText("yes");
        roomExtraYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomExtraYesActionPerformed(evt);
            }
        });

        buttonGroup2.add(roomExtraNo);
        roomExtraNo.setText("no");

        jLabel17.setText("WiFi");

        buttonGroup3.add(wifiYes);
        wifiYes.setText("yes");

        buttonGroup3.add(wifiNo);
        wifiNo.setText("no");

        jLabel18.setText("Room Service");

        jLabel19.setText("Dining");

        checkOutButton.setText("Check Out");
        checkOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkOutButtonActionPerformed(evt);
            }
        });

        buttonGroup4.add(roomPatio);
        roomPatio.setText("Patio");

        buttonGroup4.add(roomForest);
        roomForest.setText("Forest");

        jLabel20.setText("Forest or Patio");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(newReservation)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(guestIdText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkInDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(creditCardText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(zipcodeText, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonCheckin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(81, 81, 81))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(fnameText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(lnameText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(roomStyle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(checkOutDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(address1Text, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel17)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel8))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(stateText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel19))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cityText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel18))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(address2Text, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel20)
                                                        .addComponent(jLabel16)))))))
                                .addGap(68, 68, 68)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(wifiYes)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(wifiNo))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(roomExtraYes)
                                            .addGap(9, 9, 9)
                                            .addComponent(roomExtraNo)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(diningQty, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                                        .addComponent(roomServiceQty, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(room1)
                                            .addComponent(roomPatio))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(roomForest)
                                            .addComponent(room2))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(guestIdText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(checkInDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fnameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(checkOutDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lnameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(roomStyle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(address1Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(room1)
                    .addComponent(room2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomPatio)
                    .addComponent(roomForest)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(address2Text, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(roomExtraYes)
                    .addComponent(roomExtraNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(wifiYes)
                    .addComponent(wifiNo))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cityText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(roomServiceQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(stateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(diningQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(zipcodeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCheckin))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkOutButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(creditCardText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(newReservation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(106, 106, 106))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkInDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkInDateActionPerformed
        checkInDate.setFormats(dateFormat);
    }//GEN-LAST:event_checkInDateActionPerformed

    private void checkOutDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutDateActionPerformed
        checkOutDate.setFormats(dateFormat);
    }//GEN-LAST:event_checkOutDateActionPerformed

    private void jButtonCheckinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCheckinActionPerformed
            try
            {
                checkInDate.setFormats(dateFormat);
                 Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		 con = DriverManager.getConnection("jdbc:ucanaccess://E:\\HotelSystem.accdb");
		 st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 String sql = "SELECT * FROM CurrentGuests;";
                 System.out.println("Before first statement");
                 rs = st.executeQuery(sql);
                 System.out.println("After first statement.");
                 rs.last();
                 String guestID = guestIdText.getText();
                 int room = Integer.parseInt(rs.getString("RoomNumber")) + 1;
                 String roomNum = Integer.toString(room);
                 String checkin = checkInDate.getDate().toLocaleString();
                 System.out.println(checkin);
                 String insert = "INSERT INTO CurrentGuests(GuestsID, RoomNumber, Physical_Check_In_Date) VALUES ('" + guestID + "','" + roomNum + "','" + checkin + "');";
                 st.executeUpdate(insert);
                 System.out.println("After second statement.");
                 JOptionPane.showMessageDialog(null, "Guest checked in successfully.");
                 show_CurrentGuest();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }//GEN-LAST:event_jButtonCheckinActionPerformed

     private String nextAvailableIntMethod(String whichID)
    {
        String returnString;

        try
        {
            String retrieveTableSLQ = "null";

            if (whichID.equals("GuestsID"))
            {
                retrieveTableSLQ = "SELECT * FROM Guests;";
                rs = st.executeQuery(retrieveTableSLQ);
            }
            else if (whichID.equals("ReservationID"))
            {
                retrieveTableSLQ = "SELECT * FROM Reservations;";
                rs = st.executeQuery(retrieveTableSLQ);
            }
            else
            {
                // do nothing and throw error
            }

            rs.first();
            String primaryKeyNames = rs.getString(whichID);
            primaryKeyNames = primaryKeyNames.trim();

            String hold = rs.getString(whichID);
            int holdInt = Integer.parseInt(hold);
            int nextShouldBe = holdInt + 1;
            boolean shoudlBreak = false;

            if (holdInt > 1)
            {
                nextShouldBe = 1;
            }
            else
            {
                do
                {
                    if (rs.next())
                    {
                        hold = rs.getString(whichID);
                        holdInt = Integer.parseInt(hold);

                        int maxRecords = Integer.MAX_VALUE;
                        if (holdInt < maxRecords - 1)
                        {
                            if (holdInt == nextShouldBe)
                            {
                                nextShouldBe = holdInt + 1;
                            }
                            else
                            {
                                shoudlBreak = true;
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Data base is full. Currently you have " + maxRecords + " records.");
                            shoudlBreak = true;
                        }
                    }
                    else
                    {
                        rs.previous();
                        shoudlBreak = true;
                    }
                } while (shoudlBreak == false);
            }

            returnString = Integer.toString(nextShouldBe);
            int lengthReturn = returnString.length();
            String zeroString = "0000000000";
            returnString = zeroString.substring(0, 10 - lengthReturn) + returnString;
            return returnString;
        }
        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, "Error on nextAvailableIntMethod.");
            return "not found";
        }
    } // end of method: nextAvailableIntMethod


    
    private void newReservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newReservationActionPerformed
      
   
        // Inputs: Guest Info 
        String fname = fnameText.getText();
        fname = fname.trim();
        String lname = lnameText.getText();
        lname = lname.trim();
        String address1 = address1Text.getText();
        address1 = address1.trim();
        String address2 = address2Text.getText();
        address2 = address2.trim();
        String city = cityText.getText();
        city = city.trim();
        String state = stateText.getText();
        state = state.trim();
        String zipcode = zipcodeText.getText();
        zipcode = zipcode.trim();
        String phone = phoneText.getText();
        phone = phone.trim();
        String email = emailText.getText();
        email = email.trim();
        String creditCard = creditCardText.getText();
        creditCard = creditCard.trim();

        // Inputs: Dates 
        Date checkIn = checkInDate.getDate();
        Date checkOut = checkOutDate.getDate();
        

        // Calculations: Dates 
        String myDateFormat = "yyyy-MM-dd";
        DateFormat checkDateFormat = new SimpleDateFormat(myDateFormat);
        String strCheckIn = checkDateFormat.format(checkIn);
        String strCheckOut = checkDateFormat.format(checkOut);
        LocalDate checkInLocal = LocalDate.parse(strCheckIn);
        LocalDate checkOutLocal = LocalDate.parse(strCheckOut);
        short numNights = (short) ChronoUnit.DAYS.between(checkInLocal, checkOutLocal);
        LocalDate cleaningDate = checkOutLocal.plusDays(1);
        LocalDate availabilityDate = cleaningDate.plusDays(1);
        

        // Inputs: Room Info 
        String selectedRoomStyle = (String) roomStyle.getSelectedItem();
        selectedRoomStyle = selectedRoomStyle.trim();
        Boolean selectRoomPatio = roomPatio.isSelected(); /// <<< need to get value from GUI 
        Boolean selectRoomForest = roomForest.isSelected(); /// <<< need to get value from GUI 
        Boolean selectRoom1 = room1.isSelected();
        Boolean selectRoom2 = room2.isSelected();
        Boolean selectExtraYes = roomExtraYes.isSelected();
        Boolean selectExtraNo = roomExtraNo.isSelected();
        Boolean selectWifiYes = wifiYes.isSelected();
        Boolean selectWifiNo = wifiNo.isSelected();
        String inputRoomServiceQty = roomServiceQty.getText();
        String inputDiningQty = diningQty.getText();

        // Calculations: Room Info  
        String[] roomStyleArray =
        {
            "Room Style", "Cottage Two Room", "Cottage Three Room", "Cottage Four Room", "Luxury 1 Queen bed", "Luxury 2 Queen beds", "Luxury Two Room", "Luxury Three Room", "Luxury Bridal", " ", " "
        };
        String[] roomIDArray =
        {
            "error", "C2R", "C3R", "C4R", "L1Q", "L2Q", "L2R", "L3R", "LBr", "error", "error"
        };

        // >>> Generates "roomID"  (text) 
        String roomID = "null";
        for (int forCount = 0; forCount <= 10; forCount++)
        {
            if (selectedRoomStyle.equals(roomStyleArray[forCount]))
            {
                roomID = roomIDArray[forCount];
                if (roomID.equals("error"))
                {
                    // output an error message and stop execution
                }
                break;
            }
        }

        // >>> Generates "semiTypeExtraID" (text) to be used by "typeID" and "extraID" 
        String semiTypeExtraID;
        if (selectRoomPatio == true)
        {
            semiTypeExtraID = roomID.concat("_P");
        }
        else if (selectRoomForest == true)
        {
            semiTypeExtraID = roomID.concat("_F");
        }
        else
        {
            semiTypeExtraID = "error";
            // output an error message and stop execution
        }

        // >>> Generates "typeID" (text) 
        String typeID = "null";
        if (selectRoom1 == true)
        {
            typeID = semiTypeExtraID.concat("1");
        }
        else if (selectRoom2 == true)
        {
            typeID = semiTypeExtraID.concat("2");
        }
        else
        {
            typeID = "error";
            // output an error message and stop execution
        }

        // >>> Generates "extraID" (text) 
        String extraID = semiTypeExtraID.concat("E");
        short extraQty = 0;
        if (selectExtraYes == true)
        {
            extraQty = numNights;
        }
        else if (selectExtraNo == true)
        {
            extraQty = 0;
        }
        else
        {
            extraID = "error";
            // output an error message and stop execution
        }

        // >>> Generates "wifiQty" (numeric value) 
        short wifiQty = 0;
        if (selectWifiYes == true)
        {
            wifiQty = numNights;
        }
        if (selectWifiNo == true)
        {
            wifiQty = 0;
        }
        else
        {
            // output an error message and stop execution
        }

        // Guest ID and Reservation ID 
        String nextGuestIDis = nextAvailableIntMethod("GuestsID");
        String nextResvIDis = nextAvailableIntMethod("ReservationID");

        try
        {
            LocalDate today = LocalDate.now();
			short diffInToday = (short) ChronoUnit.DAYS.between(today, checkInLocal);

			if (diffInToday < 0) {
				throw new Exception("Check-In cannot happen before today.");
			}
			if (numNights == 0) {
				throw new Exception("Check-In and Check-Out cannot happen on the same date.");
			}
			if (numNights < 0) {
				throw new Exception("Check-In should be before Check-Out date.");
			}
            LocalDate currentResCheckin;
            LocalDate currentResCheckout;
            String currentRCheckin;
            String currentRCheckout;
            String roomAvailSQL = "SELECT * FROM RoomsAvailability WHERE RoomID LIKE '" + roomID + "';";
            rs = st.executeQuery(roomAvailSQL);
            rs.first();
            int roomAmt = rs.getInt("AvailableAmount");
            con.commit();
            String dateValiditySQL = "SELECT * FROM Reservations INNER JOIN RoomsAvailability ON Reservations.RoomID = RoomsAvailability.RoomID WHERE RoomID LIKE '" + roomID + "';";
            rs = st.executeQuery(dateValiditySQL);
            while(rs.next())
            {
                currentRCheckin = rs.getString("CheckIn").substring(0, 10);
                currentRCheckout = rs.getString("CheckOut").substring(0, 10);
                currentResCheckin = LocalDate.parse(currentRCheckin);
                currentResCheckout = LocalDate.parse(currentRCheckout);
                if(checkInLocal.equals(currentResCheckout) || checkOutLocal.equals(currentResCheckin) || checkInLocal.equals(currentResCheckin) || checkOutLocal.equals(currentResCheckout) || (checkInLocal.isAfter(currentResCheckin) && checkInLocal.isBefore(currentResCheckout)) || (currentResCheckin.isAfter(checkInLocal) && currentResCheckin.isBefore(checkOutLocal)))
                    roomAmt--;
            }
            if(roomAmt <= 0)
                throw new Exception("This reservation overlaps with another guest's current reservation.");
            
            // New Guest Insertion 
            // >>> Inster into "Guests" table 
            String newGuestSQL = "INSERT INTO Guests (GuestsID, FirstName, LastName, Address1, Address2, City, State, Zipcode, Phone, Email, CreditCard) "
                    + "VALUES ('" + nextGuestIDis + "' , '" + fname + "' , '" + lname + "' , '" + address1 + "' , '" + address2 + "' , '" + city + "' , '" + state + "' , '" + zipcode + "' , '" + phone + "' , '" + email + "' , '" + creditCard + "');";
            st.executeUpdate(newGuestSQL);

            // New Reservation Insertion 
            // >>> Inster into "Reservation" table 
            String newReservationSQL = "INSERT INTO Reservations (ReservationID, CheckIn, CheckOut, CleaningDate, RoomAvailable, GuestID, RoomID) "
                    + "VALUES ('" + nextResvIDis + "', #" + checkInLocal + "#, #" + checkOutLocal + "#, #" + cleaningDate + "#, #" + availabilityDate + "#, '" + nextGuestIDis + "', '" + roomID + "');";
            st.executeUpdate(newReservationSQL);
            
           System.out.println(newGuestSQL); 
           System.out.println(newReservationSQL); 

           

            // >>> Inster into "AddType" table 
            String addTypeSQL = "INSERT INTO AddType (ReservationID, TypeID, Quantity) VALUES ('" + nextResvIDis + "', '" + typeID + "', " + numNights + ");";
            st.executeUpdate(addTypeSQL);

            // >>> Inster into "AddExtra" table 
            String addExtraSQL = "INSERT INTO AddExtra (ReservationID, ExtraID, Quantity) VALUES ('" + nextResvIDis + "', '" + extraID + "', " + extraQty + ");";
            st.executeUpdate(addExtraSQL);

            // >>> Inster into "AddServices" table 
            String addDinningSQL = "INSERT INTO AddServices (ReservationID, ServiceID, Quantity) VALUES ('" + nextResvIDis + "', 'DB', " + inputDiningQty + ");";
            String addRoomServSQL = "INSERT INTO AddServices (ReservationID, ServiceID, Quantity) VALUES ('" + nextResvIDis + "', 'RS', " + inputRoomServiceQty + ");";
            String addWiFiSQL = "INSERT INTO AddServices (ReservationID, ServiceID, Quantity) VALUES ('" + nextResvIDis + "', 'WF', " + wifiQty + ");";
            st.executeUpdate(addRoomServSQL);
            st.executeUpdate(addDinningSQL);
            st.executeUpdate(addWiFiSQL);
            
            show_Guest();
            show_Service();
            show_Reservation();
        }
        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            e1.printStackTrace();
        }
 
    }//GEN-LAST:event_newReservationActionPerformed

    private void roomExtraYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomExtraYesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomExtraYesActionPerformed

    private void checkOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkOutButtonActionPerformed
        try
        {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://E:\\HotelSystem.accdb");
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            String guestid = guestIdText.getText();
            String sql = "DELETE FROM CurrentGuests WHERE GuestsID LIKE '" + guestid + "';";
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Guest checked out successfully.");
            int optionPane = JOptionPane.showConfirmDialog(null, "Would you like to print the bill?", "Print", JOptionPane.YES_NO_OPTION);
            if(optionPane == JOptionPane.YES_OPTION)
            {
                String sql1 = "SELECT * FROM Reservations WHERE GuestID LIKE '" + guestid + "';";
                rs = st.executeQuery(sql1);
                rs.first();
                String reservationID = rs.getString("ReservationID");
                Bill newBill = new Bill(reservationID, guestid);
                newBill.setVisible(true);
            }
            show_CurrentGuest();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }//GEN-LAST:event_checkOutButtonActionPerformed

    private void jTable_Current_GuestsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_Current_GuestsMouseClicked
        int i = jTable_Current_Guests.getSelectedRow();
        TableModel cgmodel = jTable_Current_Guests.getModel();
        guestIdText.setText(cgmodel.getValueAt(i, 0).toString());
        fnameText.setText(cgmodel.getValueAt(i, 1).toString());
        lnameText.setText(cgmodel.getValueAt(i, 2).toString());
        address1Text.setText("");
        address2Text.setText("");
        cityText.setText("");
        stateText.setText("");
        zipcodeText.setText("");
        phoneText.setText("");
        emailText.setText("");
        creditCardText.setText("");
        checkInDate.setDate(null);
        checkOutDate.setDate(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        roomServiceQty.setText("");
        diningQty.setText("");
        roomStyle.setSelectedIndex(0);
    }//GEN-LAST:event_jTable_Current_GuestsMouseClicked

    private void jTable_GuestMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_GuestMouseClicked
        int i = jTable_Guest.getSelectedRow();
        TableModel gmodel = jTable_Guest.getModel();
        guestIdText.setText(gmodel.getValueAt(i, 0).toString());
        fnameText.setText(gmodel.getValueAt(i, 1).toString());
        lnameText.setText(gmodel.getValueAt(i, 2).toString());
        address1Text.setText(gmodel.getValueAt(i, 3).toString());
        if(gmodel.getValueAt(i, 4)!= null)
        address2Text.setText(gmodel.getValueAt(i, 4).toString());
        else
        address2Text.setText("");
        cityText.setText(gmodel.getValueAt(i, 5).toString());
        stateText.setText(gmodel.getValueAt(i, 6).toString());
        zipcodeText.setText(gmodel.getValueAt(i, 7).toString());
        phoneText.setText(gmodel.getValueAt(i, 8).toString());
        emailText.setText(gmodel.getValueAt(i, 9).toString());
        creditCardText.setText(gmodel.getValueAt(i, 10).toString());
        checkInDate.setDate(null);
        checkOutDate.setDate(null);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        roomServiceQty.setText("");
        diningQty.setText("");
        roomStyle.setSelectedIndex(0);
    }//GEN-LAST:event_jTable_GuestMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Hotel_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Hotel_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Hotel_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Hotel_System.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hotel_System().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address1Text;
    private javax.swing.JTextField address2Text;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private org.jdesktop.swingx.JXDatePicker checkInDate;
    private javax.swing.JButton checkOutButton;
    private org.jdesktop.swingx.JXDatePicker checkOutDate;
    private javax.swing.JTextField cityText;
    private javax.swing.JTextField creditCardText;
    private javax.swing.JTextField diningQty;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField fnameText;
    private javax.swing.JTextField guestIdText;
    private javax.swing.JButton jButtonCheckin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_Current_Guests;
    private javax.swing.JTable jTable_Guest;
    private javax.swing.JTable jTable_Reservations;
    private javax.swing.JTable jTable_service;
    private javax.swing.JTextField lnameText;
    private javax.swing.JButton newReservation;
    private javax.swing.JTextField phoneText;
    private javax.swing.JRadioButton room1;
    private javax.swing.JRadioButton room2;
    private javax.swing.JRadioButton roomExtraNo;
    private javax.swing.JRadioButton roomExtraYes;
    private javax.swing.JRadioButton roomForest;
    private javax.swing.JRadioButton roomPatio;
    private javax.swing.JTextField roomServiceQty;
    private javax.swing.JComboBox<String> roomStyle;
    private javax.swing.JTextField stateText;
    private javax.swing.JRadioButton wifiNo;
    private javax.swing.JRadioButton wifiYes;
    private javax.swing.JTextField zipcodeText;
    // End of variables declaration//GEN-END:variables
}
