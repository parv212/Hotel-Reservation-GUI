import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RateAndPaymentInformation extends JFrame {

    private JFrame frame;
    private JLabel type_of_room, no_of_room, date_in, date_out, no_of_days, rate, payment_options, charges, other_charges, discount, total_charges;
    private JTextField nor, di, dou, nod, r, ch, oc, d, tc;
    private JComboBox tor, po;
    private JButton previous, calculate, exit;

    RateAndPaymentInformation()
    {
        frame = new JFrame();

        type_of_room = new JLabel("Type of Room: ");
        no_of_room = new JLabel("No. of Rooms: ");
        date_in =new JLabel("Date in: ");
        date_out = new JLabel("Date out: ");
        no_of_days = new JLabel("No. of Days: ");
        rate = new JLabel("Rate(in Rs.): ");
        payment_options = new JLabel("Payment Options: ");
        charges = new JLabel("Charges(in Rs.): ");
        other_charges = new JLabel("Other Charges(in Rs.): ");
        discount = new JLabel("Discount(in %): ");
        total_charges = new JLabel("Total Charges(in Rs.): ");
        nor = new JTextField(2);
        di = new JTextField(10);
        dou = new JTextField(10);
        nod = new JTextField(10);
        r = new JTextField(10);
        ch = new JTextField(10);
        oc = new JTextField(10);
        d = new JTextField(10);
        tc = new JTextField(10);
        previous = new JButton("Previous");
        calculate = new JButton("Calculate");
        exit = new JButton("Exit");

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if (st.equals("Exit")){
                    validateNumberOfRooms();
                    validateDateIn();
                    validateDateOut();
                    validateCharges();
                    validateOtherCharges();
                    validateDiscount();
                    createrateFile();
                    frame.dispose();
                }
            }
        });

        previous.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if(st.equals("Previous")) {
                    frame.dispose();
                    try {
                        new VehicleInformation();
                    }catch(Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

        });

        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if(st.equals("Calculate")) {
                    try {
                        calculate_price();
                    }catch(Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        });


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addComp(panel, type_of_room, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, no_of_room, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, nor, 1, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, date_in, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, di, 1, 2, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, date_out, 0, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, dou, 1, 3, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, no_of_days, 0, 4, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, nod, 1, 4, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, rate, 0, 5, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, r, 1, 5, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, payment_options, 0, 6, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, charges, 0, 7, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, ch, 1, 7, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, other_charges, 0, 8, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, oc, 1, 8, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, discount, 0, 9, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, d, 1, 9, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, total_charges, 0, 10, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, tc, 1, 10, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, previous, 0, 11, 2, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, calculate, 1, 11, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, exit, 3, 11, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        JPanel cb = new JPanel();
        JPanel cb1 = new JPanel();
        cb.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        cb1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        String[] torl = {"Double", "Quad", "Twin", "Deluxe", "Luxury Suite", "Master Suite"};
        String[] pol = {"Cash", "Debit Card", "Credit Card", "Cheque"};
        tor = new JComboBox(torl);
        po = new JComboBox(pol);
        cb.add(tor);
        cb1.add(po);
        addComp(panel, cb, 1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, cb1, 1, 6, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(400,450);
        frame.setTitle("Rate & Payment Information");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        validate();
    }

    private void validateNumberOfRooms(){
        boolean valid = true;
        if(nor.getText().isEmpty())
            JOptionPane.showMessageDialog(nor,"Number of Rooms field is empty please enter some valid value.");
        else{
            try{
                Integer.parseInt(nor.getText());
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(nor,"Enter valid Number of Rooms");
            }
        }
    }

    private void validateCharges(){
        boolean valid = true;
        if(ch.getText().isEmpty())
            JOptionPane.showMessageDialog(ch,"Charges field is empty please enter some valid value.");
        else{
            try{
                Double.parseDouble(ch.getText());
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(ch,"Enter valid Charge");
            }
        }
    }

    private void validateOtherCharges(){
        boolean valid = true;
        if(oc.getText().isEmpty())
            JOptionPane.showMessageDialog(oc,"Other Charges field is empty please enter some valid value.");
        else{
            try{
                Double.parseDouble(oc.getText());
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(oc,"Enter valid Other Charge");
            }
        }
    }

    private void validateDiscount(){
        boolean valid = true;
        if(d.getText().isEmpty())
            JOptionPane.showMessageDialog(d,"Discount field is empty please enter some valid value.");
        else{
            try{
                Double.parseDouble(d.getText());
            }
            catch (NumberFormatException e){
                JOptionPane.showMessageDialog(d,"Enter valid Discount");
            }
        }
    }

    private void validateDateIn() {
        if(di.getText().isEmpty())
            JOptionPane.showMessageDialog(di,"Date In feild is empty.");
        else {
            Pattern pdi = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher mdi = pdi.matcher((di.getText()));
            if (!(mdi.matches()))
                JOptionPane.showMessageDialog(di, "Enter the valid Date-in");
        }
    }

    private void validateDateOut() {
        if(dou.getText().isEmpty())
            JOptionPane.showMessageDialog(dou,"Date In feild is empty.");
        else {
            Pattern pdo = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
            Matcher mdo = pdo.matcher((dou.getText()));
            if (!(mdo.matches()))
                JOptionPane.showMessageDialog(dou, "Enter the valid Date-out");
        }
    }

    public String getTypeOfRoom(){
        return "Type of Room : " + tor.getSelectedItem().toString();
    }

    public String getNoOfRoom(){
        return "No. of Room : " + nor.getText();
    }

    public String getDateIn(){
        return "Date In : " + di.getText();
    }

    public String getDateOut(){
        return "Date out : " + dou.getText();
    }

    public String getNoOfDays(){
        return "No. of Days : " + nod.getText();
    }

    public String getRate(){
        return "Rate : " + r.getText();
    }

    public String getPaymentOptions(){
        return "Payment Method : " + po.getSelectedItem().toString();
    }

    public String getCharges(){
        return "Charges : " + ch.getText();
    }

    public String getOtherCharges(){
        return "Other Charges : " + oc.getText();
    }

    public String getDiscount(){
        return "Discount : " + d.getText();
    }

    public String getTotalCahrges(){
        return "Total Charges : " + tc.getText();
    }

    public void setRate(String cost){
        r.setText(cost);
    }

    public void setTotal_charges(String cost){
        tc.setText(cost);
    }

    public void calculate_price(){
        int r = 0;
        String s = tor.getSelectedItem().toString();
        switch(s)
        {
            case "Twin":    r = 7500;
                            setRate("7500");
                            break;
            case "Double":  r = 6000;
                            setRate("6000");
                            break;
            case "Quad":    r = 12500;
                            setRate("12500");
                            break;
            case "Deluxe":  r = 15000;
                            setRate("15000");
                            break;
            case "Luxury Suite":    r = 17500;
                                    setRate("17500");
                                    break;
            case "Master Suite":    r = 20000;
                                    setRate("20000");
                                    break;
        }
        double charges = Double.parseDouble(ch.getText());
        double othercharges = Double.parseDouble(oc.getText());
        double discount = Double.parseDouble(d.getText());
        double noofrooms = Double.parseDouble(nor.getText());
        double noofdays = Double.parseDouble(nod.getText());
        int rate = r;
        double price = charges + othercharges + (rate * noofrooms * noofdays) - ((discount/100) * rate);
        setTotal_charges(String.valueOf(price));
    }

    private void addComp(JPanel thePanel, JComponent comp, int xPos, int yPos, int compWidth, int compHeight, int place, int stretch){
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = xPos;
        gridConstraints.gridy = yPos;
        gridConstraints.gridwidth = compWidth;
        gridConstraints.gridheight = compHeight;
        gridConstraints.weightx = 0;
        gridConstraints.weighty = 0;
        gridConstraints.insets = new Insets(5,5,5,5);
        gridConstraints.anchor = place;
        gridConstraints.fill = stretch;
        thePanel.add(comp, gridConstraints);
    }

    private void createrateFile(){
        try {
            File f = new File("GuestInformation.txt");
            //f.createNewFile();
            FileWriter fout = new FileWriter(f,true);
            fout.write("\n" + getTypeOfRoom() + "\n" + getNoOfRoom() + "\n" + getDateIn() + "\n" + getDateOut() + "\n" + getNoOfDays() + "\n" + getRate() + "\n" + getPaymentOptions() + "\n" + getCharges() + "\n" + getOtherCharges() + "\n" + getDiscount() + "\n" + getTotalCahrges());
            fout.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        RateAndPaymentInformation rpi = new RateAndPaymentInformation();
    }
}
