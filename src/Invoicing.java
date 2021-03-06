//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//Displays invoicing info
//-------------------------------------------------------------------------------
import javax.swing.*;

public class Invoicing  extends JFrame {
    private Car selectedCar;
    private int clientID , price ,transmission,door , smoker ,i ;
    private String licanceplate , date ,daysnum;
    private boolean[] extras;
    private JFrame f;


    public Invoicing(Car selectedCar) {
        this.selectedCar = selectedCar;

        clientID = selectedCar.getClientID();
        extras = selectedCar.getExtras();
        price = selectedCar.getPrice();
        transmission = selectedCar.getTransmission();
        door = selectedCar.getDoor();
        smoker = selectedCar.getSmoker();
        date = selectedCar.getDate();
        daysnum = selectedCar.getDaysnum();


        licanceplate = selectedCar.getLicanceplate();



        if(licanceplate == null ){
            f =new JFrame();
            JOptionPane.showMessageDialog( f,"The car you looking for is out of stock", "Alert" , JOptionPane.WARNING_MESSAGE);
        }
        else{

            f =new JFrame();
            JOptionPane.showMessageDialog( f,"Your client ID: " + clientID + "\n" +"Delivery date : " + date + "\n" + "Number of days the car will be rented " + daysnum + "\n" + "Licance plate of the car : " + licanceplate + "\n", "Alert" , JOptionPane.INFORMATION_MESSAGE);

        }


    }
}
