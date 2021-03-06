//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//Client login
//-------------------------------------------------------------------------------
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javax.swing.*;


public class RentACarGUI extends JFrame implements ActionListener {

    private JLabel ClientIdL;
    private JTextField ClientIdTf;
    private JButton enterB, newaccB;
    private Scanner x, y;

    public RentACarGUI() throws NoSuchAlgorithmException {

        //set icon for our app
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        //set background color
        this.getContentPane().setBackground(Color.lightGray);

        enterB = new JButton("Enter");
        newaccB = new JButton("I don't have a client ID");

        ClientIdL = new JLabel("Client ID");
        ClientIdTf = new JTextField(15);

        //Get GUI's container reference and store it in c
        Container c = getContentPane();

        //Add GUI components on to container using c
        //define layout first
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 2, 80);
        c.setLayout(layout);

        c.add(ClientIdL);
        c.add(ClientIdTf);
        c.add(enterB);
        c.add(newaccB);

        enterB.addActionListener(this);
        newaccB.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterB) {
            try {
                int clientID = Integer.parseInt(ClientIdTf.getText().trim());
                try {
                    VerifyLogin(clientID);
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }
            } catch (NumberFormatException n) {
                System.out.println("Invalıd Client ID");
            }


        } else if (e.getSource() == newaccB) {
            //generate client ID
            try {
                y = new Scanner(new File("clients.txt"));
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            y.useDelimiter("[\n]");

            int ct = 0;
            while (y.hasNext()) {
                ct++;
                y.next();
            }
            ct++;
            String clientID = String.format("%06d\n", ct);

            ClientIdTf.setText(clientID);

            try {
                Files.write(new File("clients.txt").toPath(), clientID.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public void VerifyLogin(int username) throws NoSuchAlgorithmException {

        boolean found = false;

        int tempID;

        try {
            x = new Scanner(new File("clients.txt"));
            x.useDelimiter("[\n]");

            while (x.hasNext() && !found) {
                tempID = Integer.parseInt(x.next());
                System.out.println(tempID);

                //If client ID is correct open reservation form
                if (tempID == username) {
                    found = true;

                    RezFormGUI form = new RezFormGUI(tempID);


                    form.setTitle("OGZ Car Rental Service");
                    form.setSize(590, 700);
                    form.setLocation(670,220);
                    form.setResizable(false);
                    form.setVisible(true);
                    form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                }
                System.out.println(found);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
