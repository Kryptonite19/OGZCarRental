//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//Displays last rent info for client
//-------------------------------------------------------------------------------
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

public class ClientInfoGUI extends JFrame {
    private int clientID;
    private Scanner clientSc;
    private int tempprice, temptransmission, tempdoor, tempsmoker, tempclientID, price;
    private String templicanceplate;
    private boolean roof, tralier, snowchains, childseats;
    private JLabel priceL, licanceplateL;

    public ClientInfoGUI(int clientID, boolean roof, boolean tralier, boolean snowchains, boolean childseats) {
        this.clientID = clientID;
        this.roof = roof;
        this.tralier = tralier;
        this.snowchains = snowchains;
        this.childseats = childseats;

        //set icon for our app
        ImageIcon icon = new ImageIcon("icon.png");
        this.setIconImage(icon.getImage());

        //set background color
        this.getContentPane().setBackground(Color.lightGray);


        try {
            clientSc = new Scanner(new File("Sold.txt"));
            clientSc.useDelimiter("[,\n]");

            boolean found = false;

            if (clientSc.hasNextLine()) {
                while (clientSc.hasNext() && !found) {
                    tempprice = Integer.parseInt(clientSc.next());
                    temptransmission = Integer.parseInt(clientSc.next());
                    tempdoor = Integer.parseInt(clientSc.next());
                    tempsmoker = Integer.parseInt(clientSc.next());
                    templicanceplate = clientSc.next();
                    tempclientID = Integer.parseInt(clientSc.next());

                    if (tempclientID == clientID) {
                        found = true;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (tempprice == 0) {
            price = 50;
        } else if (tempprice == 1) {
            price = 100;
        } else if (tempprice == 2) {
            price = 200;
        }
        if (roof) {
            price = price + 150;
        }
        if (tralier) {
            price = price + 250;
        }
        if (snowchains) {
            price = price + 50;
        }
        if (childseats) {
            price = price + 100;
        }

        //Labels
        licanceplateL = new JLabel("Car's licance plate : " + templicanceplate + "\n");
        priceL = new JLabel("Total price for the car : " + price);


        //Get GUI's container reference and store it in c
        Container c = getContentPane();

        //Add GUI components on to container using c
        //define layout first

        FlowLayout layout = new FlowLayout(FlowLayout.LEADING, 15, 70);
        c.setLayout(layout);

        c.add(licanceplateL);
        c.add(priceL);


    }

}
