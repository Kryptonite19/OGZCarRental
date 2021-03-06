//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//Taking order
//-------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class RezFormGUI extends JFrame implements ActionListener {

    private JComboBox priceCB, transmissionCB, dooroptCB, smokingCB, departmentCB;
    private JLabel priceL, transmissionL, dooroptL, smokingL, departmentL, daysnumL;
    private String[] extrasArr, priceArr, transmission, dooropt, smoking, deparmentArr;
    private JButton buyB, clinfoB;
    private int clientID, price;
    private JCheckBox roofCH, tralierCH, snowchainsCH, childseatsCH;
    private JTextField txtDate, daysnumTF;
    private JPanel contentPane;
    private JFrame fr;


    public RezFormGUI(int clientID) {

        //set icon for our app
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        //set background color
        this.getContentPane().setBackground(Color.lightGray);


        this.clientID = clientID;

        extrasArr = new String[]{"NONE", "Roof rack", "Tralier", "Snow Chains", "Child Seats"};
        transmission = new String[]{"Manual", "Automatic"};
        dooropt = new String[]{"2 doors", "4 doors"};
        smoking = new String[]{"Non-smoker Car", "Smoker Car"};
        priceArr = new String[]{"Low priced", "Mid priced", "High priced"};
        deparmentArr = new String[]{"Ankara Department", "Istanbul Department"};

        //Labels
        priceL = new JLabel("Price Range");
        transmissionL = new JLabel("Transmission");
        dooroptL = new JLabel("Door type");
        smokingL = new JLabel("Smoker/Non-smoker");
        departmentL = new JLabel("Choose Department");
        daysnumL = new JLabel("Number of days the car will be rented");


        //check boxes for extras
        roofCH = new JCheckBox("Roof rack +$150");
        tralierCH = new JCheckBox("Tralier +$250");
        snowchainsCH = new JCheckBox("Snow Chains +$50");
        childseatsCH = new JCheckBox("Child seats +$100");

        daysnumTF = new JTextField(5);

        //combo boxes
        priceCB = new JComboBox(priceArr);
        transmissionCB = new JComboBox(transmission);
        dooroptCB = new JComboBox(dooropt);
        smokingCB = new JComboBox(smoking);
        departmentCB = new JComboBox(deparmentArr);

        //Buy button
        buyB = new JButton("RENT NOW");
        clinfoB = new JButton("CLIENT INFO");

        //create new JPanel in contentPane
        contentPane = new JPanel();
        //set border of frame
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //set contentPane
        setContentPane(contentPane);
        //set layout null
        contentPane.setLayout(null);

        txtDate = new JTextField();
        //set bounds of text field
        txtDate.setBounds(101, 107, 86, 20);
        //add text field to contentPane
        contentPane.add(txtDate);
        //set columns
        txtDate.setColumns(10);

        //create button and there object
        JButton btnNewButton = new JButton("Rental Date");
        //perform action listener
        btnNewButton.addActionListener(new ActionListener() {
            //performed action
            public void actionPerformed(ActionEvent arg0) {
                //create frame new object  f
                final JFrame f = new JFrame();
                //set text which is collected by date picker i.e. set date
                txtDate.setText(new DatePicker(f).setPickedDate());
            }
        });
        //set button bound
        btnNewButton.setBounds(223, 106, 27, 23);
        //add button in contentPane
        contentPane.add(btnNewButton);

        //Get GUI's container reference and store it in c
        Container c = getContentPane();

        //Add GUI components on to container using c
        //define layout first

        FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 15, 70);
        c.setLayout(layout);

        //Create buttons
        c.add(daysnumL);
        c.add(daysnumTF);
        c.add(roofCH);
        c.add(tralierCH);
        c.add(snowchainsCH);
        c.add(childseatsCH);
        c.add(priceL);
        c.add(priceCB);
        c.add(transmissionL);
        c.add(transmissionCB);
        c.add(dooroptL);
        c.add(dooroptCB);
        c.add(smokingL);
        c.add(smokingCB);
        c.add(departmentL);
        c.add(departmentCB);


        c.add(buyB);
        c.add(clinfoB);
        buyB.addActionListener(this);
        clinfoB.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buyB) {

            fr = new JFrame();

            if (txtDate.getText().equals("")) {
                JOptionPane.showMessageDialog(fr, "Please enter a date", "ERROR", JOptionPane.WARNING_MESSAGE);
            } else if (daysnumTF.getText().equals(""))
                JOptionPane.showMessageDialog(fr, "Please enter a number of days ", "ERROR", JOptionPane.WARNING_MESSAGE);
            else {

                if (priceCB.getSelectedIndex() == 0) {
                    price = 50;
                } else if (priceCB.getSelectedIndex() == 1) {
                    price = 100;
                } else if (priceCB.getSelectedIndex() == 2) {
                    price = 200;
                }

                //get checkboxes as a whole using boolean array
                boolean[] extras = new boolean[4];

                extras[0] = roofCH.isSelected();
                extras[1] = tralierCH.isSelected();
                extras[2] = snowchainsCH.isSelected();
                extras[3] = childseatsCH.isSelected();

                if (extras[0]) {
                    price = price + 150;
                }
                if (extras[1]) {
                    price = price + 250;
                }
                if (extras[2]) {
                    price = price + 50;
                }
                if (extras[3]) {
                    price = price + 100;
                }

                price = price * Integer.parseInt(daysnumTF.getText());

                int input = JOptionPane.showConfirmDialog(fr, "Your total price is : $" + price, "infobox", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);


                if (input == 0) {
                    Car selectedCar = new Car(txtDate.getText(), daysnumTF.getText(), clientID, extras, priceCB.getSelectedIndex(), transmissionCB.getSelectedIndex(), dooroptCB.getSelectedIndex(), smokingCB.getSelectedIndex());

                    Invoicing In = null;
                    In = new Invoicing(selectedCar);
                } else {
                    fr.dispose();
                }
            }

        } else if (e.getSource() == clinfoB) {
            ClientInfoGUI inf = new ClientInfoGUI(clientID, roofCH.isSelected(), tralierCH.isSelected(), snowchainsCH.isSelected(), childseatsCH.isSelected());

            inf.setTitle("OGZ Car Rental Service");
            inf.setSize(525, 260);
            inf.setLocation(800,300);
            inf.setResizable(false);
            inf.setVisible(true);
            inf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }
}