//-------------------------------------------------------------------------------
//Main.java                             Author : Ahmet Oğuzhan KELEŞ ID: 21895231
//                                       e-mail: ahmetoguzhan98@gmail.com
//
//Describe car class and edit text files
//-------------------------------------------------------------------------------
import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Car {
    private int door;
    private int smoker;
    private int transmission;
    private int price;
    private int clientID, tempclientID;
    private String licanceplate, date, daysnum, templicanceplate, strtemp, IDSTR;
    private static Scanner stocksc, soldsc;
    private static Writer wr;
    private String tempPlate;
    private boolean[] extras;
    private boolean resultID = false, found = false, dup = false;

    private int tempprice, temptransmission, tempdoor, tempsmoker;
    private int[] specs, tempspecs;
    private int i;
    private JFrame f;

    private StringBuilder sb;


    public Car(String date, String daysnum, int clientID, boolean[] extras, int price, int transmission, int door, int smoker) {
        this.clientID = clientID;
        this.extras = extras;
        this.price = price;
        this.transmission = transmission;
        this.door = door;
        this.smoker = smoker;
        this.date = date;
        this.daysnum = daysnum;

        specs = new int[4];

        specs[0] = price;
        specs[1] = transmission;
        specs[2] = door;
        specs[3] = smoker;

        try {
            stocksc = new Scanner(new File("CarStock.txt"));
            stocksc.useDelimiter("[,\n]");

            boolean found = false;

            while (stocksc.hasNext() && !found) {
                tempprice = Integer.parseInt(stocksc.next());
                temptransmission = Integer.parseInt(stocksc.next());
                tempdoor = Integer.parseInt(stocksc.next());
                tempsmoker = Integer.parseInt(stocksc.next());
                templicanceplate = stocksc.next();

                System.out.printf("%d %d %d \n", tempprice, temptransmission, tempdoor);

                tempspecs = new int[4];

                tempspecs[0] = tempprice;
                tempspecs[1] = temptransmission;
                tempspecs[2] = tempdoor;
                tempspecs[3] = tempsmoker;

                boolean result = Arrays.equals(specs, tempspecs);

                if (result == true) {
                    found = true;
                    licanceplate = templicanceplate;
                }

            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }

        //Check if sold else write it to sold.txt
        try {
            soldsc = new Scanner(new File("Sold.txt"));
            soldsc.useDelimiter("[,\n]");

            boolean found = false;

            if (soldsc.hasNextLine()) {
                while (soldsc.hasNext() && !found) {
                    tempprice = Integer.parseInt(soldsc.next());
                    temptransmission = Integer.parseInt(soldsc.next());
                    tempdoor = Integer.parseInt(soldsc.next());
                    tempsmoker = Integer.parseInt(soldsc.next());
                    templicanceplate = soldsc.next();
                    tempclientID = Integer.parseInt(soldsc.next());

                    if (templicanceplate.equals(licanceplate)) {

                        System.out.println("the car is sold");
                        dup = true;

                        f = new JFrame();
                        JOptionPane.showMessageDialog(f, "The car you are looking for is sold", "Alert", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }

            if (found == false) {
                sb = new StringBuilder();
                strtemp = Arrays.toString(specs).replaceAll("\\[|\\]| ", "");

                IDSTR = Integer.toString(clientID);

                sb.append(strtemp);
                sb.append(",");
                sb.append(licanceplate);
                sb.append(",");
                sb.append(IDSTR);


                if (licanceplate != null && dup == false) {
                    wr = new FileWriter(new File("Sold.txt"), true);
                    wr.write(sb + "\n");
                    wr.close();
                }

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public String getDate() {
        return date;
    }

    public String getDaysnum() {
        return daysnum;
    }

    public int getPrice() {


        return price;
    }

    public int getTransmission() {

        return transmission;
    }

    public int getDoor() {

        return door;
    }

    public int getSmoker() {

        return smoker;
    }

    public boolean[] getExtras() {
        return extras;
    }

    public int getClientID() {
        return clientID;
    }

    public String getLicanceplate() {
        return licanceplate;
    }
}


