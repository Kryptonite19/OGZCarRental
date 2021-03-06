//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//Creates main menu window
//-------------------------------------------------------------------------------

import javax.swing.*;

public class Main {
    public static void main(String[] args){

        CarRentalGUI mainmenu = new CarRentalGUI();

        mainmenu.setTitle("OGZ Car Rental Service");
        mainmenu.setSize(300, 450);
        mainmenu.setLocation(800,300);
        mainmenu.setResizable(false);
        mainmenu.setVisible(true);
        mainmenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}