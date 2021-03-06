//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//main menu window
//-------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import javax.swing.*;

public class CarRentalGUI extends JFrame implements ActionListener {

    private JLabel imageL;
    private JButton rentB ,exitB;
    private JPanel logoP ;
    private DatePicker datePicker;

    public CarRentalGUI(){

        //set icon for our app
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        //set background color
        this.getContentPane().setBackground(Color.lightGray);


        //add logo panel
        logoP = new JPanel();
        logoP.setBounds(0,0,450,200);
        this.add(logoP);
        imageL = new JLabel();
        ImageIcon logo = new ImageIcon("logo.jpg");
        imageL.setIcon(logo);
        logoP.add(imageL);

        rentB = new JButton("RENT A CAR");
        exitB = new JButton("EXIT");

        //Get GUI's container reference and store it in c
        Container c = getContentPane();

        //Add GUI components on to container using c
        //define layout first
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER,20,40);
        c.setLayout(layout);

        //Create buttons
        c.add(rentB);
        c.add(exitB);



        rentB.addActionListener(this);
        exitB.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rentB){
            RentACarGUI rez = null;
            try {
                rez = new RentACarGUI();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            rez.setTitle("OGZ Car Rental Service");
            rez.setSize(525, 260);
            rez.setLocation(700,650);
            rez.setResizable(false);
            rez.setVisible(true);
            rez.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        }
        else if (ae.getSource() == exitB){
            System.exit(0);
            //terminate application
        }
    }
}
