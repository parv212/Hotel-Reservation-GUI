import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GuestInformation extends JFrame {

    private JFrame frame;
    private JLabel first_name, last_name, address, contact, no_of_adults, no_of_child;
    private JTextField fn, ln, ad, c, noa, noc;
    private JButton next;

    GuestInformation()
    {
        frame = new JFrame();

        first_name = new JLabel("First Name: ");
        last_name = new JLabel("Last Name: ");
        address = new JLabel("Address: ");
        contact = new JLabel("Contact: ");
        no_of_adults = new JLabel("No. of Adults: ");
        no_of_child = new JLabel("No. of Children: ");
        fn = new JTextField(10);
        ln = new JTextField(10);
        ad = new JTextField(20);
        c = new JTextField(10);
        noa = new JTextField(2);
        noc = new JTextField(2);
        next = new JButton("Next");

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                if ( (st.equals("Next"))) {
                    createGuestFile();
                    frame.dispose();
                    if(validateFirstName() && validateLastName() && validateAddress() && validateContact() && validateContact() && validateNumberOfChildren() && validateNumberOfAdults()) {
                        try {
                            VehicleInformation v = new VehicleInformation();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                    else{
                        frame.dispose();
                        new GuestInformation();
                    }
                }
            }
        });

        addComp(panel, first_name, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, fn, 1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, last_name, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, ln, 1, 1, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, address, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, ad, 1, 2, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, contact, 0, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, c, 1, 3, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, no_of_adults, 0, 4, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, noa, 1, 4, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, no_of_child, 0, 5, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, noc, 1, 5, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, next, 0, 6, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(350,300);
        frame.setTitle("Guest Information");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        validate();
    }

    private boolean validateFirstName(){
        boolean valid=false;
        if(fn.getText().isEmpty()) {
            JOptionPane.showMessageDialog(fn,"First Name field is empty please enter some valid value.");
            return valid;
        }
        else{
            for (int i = 0 ; i < fn.getText().length() ; i++)
            {
                if(Character.isLetter(fn.getText().charAt(i)) || (fn.getText().charAt(i) == ' ' )&& (fn.getText().charAt(i+1) != ' '))
                    valid = true;
                else
                    valid = false;
            }
            if(!valid) {
                JOptionPane.showMessageDialog(fn,"Enter a valid First Name");
                return valid;
            }
        }
        return true;
    }

    private boolean validateLastName(){
        boolean valid = false;
        if(ln.getText().isEmpty()) {
            JOptionPane.showMessageDialog(ln,"Last Name field is empty please enter some valid value.");
            return valid;
        }
        else{
            for (int i = 0 ; i < ln.getText().length() ; i++)
            {
                if(Character.isLetter(ln.getText().charAt(i)) || (ln.getText().charAt(i) == ' ' )&& (ln.getText().charAt(i+1) != ' '))
                    valid = true;
                else
                    valid = false;
            }
            if(!valid) {
                JOptionPane.showMessageDialog(ln,"Enter a valid Last Name");
                return valid;
            }
        }
        return true;
    }

    private boolean validateContact(){
        boolean valid = false;
        if(c.getText().isEmpty()) {
            JOptionPane.showMessageDialog(ln,"Contact field is empty please enter some valid value.");
            return valid;
        }
        else{
            if(c.getText().length() == 10 )
                valid = true;
            if(!valid) {
                JOptionPane.showMessageDialog(c,"Enter a valid Contact Number");
                return valid;
            }
            else{
                try{
                    Double.parseDouble(c.getText());
                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(c,"Enter a valid Contact Number");
                }
            }
        }
        return true;
    }

    private boolean validateAddress(){
        boolean valid = false;
        if(ad.getText().isEmpty()) {
            JOptionPane.showMessageDialog(ad,"Address field is empty please enter some valid value.");
            return valid;
        }
        return true;
    }

    private boolean validateNumberOfAdults(){
        boolean valid = false;
        if(noa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(noa,"Number of Adults field is empty please enter some valid value.");
            return valid;
        }
        else{
            if(noa.getText().length() > 2)
                valid = true;
            if(valid) {
                JOptionPane.showMessageDialog(noa,"Enter valid Number of Adults.");
                return valid;
            }
            else{
                try{
                    Integer.parseInt(noa.getText());
                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(noa,"Enter a valid Number of Adults");
                }
            }
        }
        return true;
    }

    private boolean validateNumberOfChildren(){
        boolean valid = false;
        if(noc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(noc,"Number of Child field is empty please enter some valid value.");
            return valid;
        }
        else{
            if(noc.getText().length() > 2)
                valid = true;
            if(valid) {
                JOptionPane.showMessageDialog(noc,"Enter valid Number of Children");
                return valid;
            }
            else{
                try{
                    Integer.parseInt(noc.getText());
                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(noc,"Enter valid Number of Children");
                }
            }
        }
        return true;
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

    public String getFirstName() {
        return "First Name : " + fn.getText();
    }

    public String getLastName() {
        return "Last Name : " + ln.getText();
    }

    public String getAddresss() {
        return "Address : " + ad.getText();
    }

    public String getContact() {
        return "Contact : " + c.getText();
    }

    public String getNoOfAdults() {
        return "No. of Adults : " + noa.getText();
    }

    public String getNoOfChildren() {
        return "No. of Children : " + noc.getText();
    }

    public void createGuestFile(){

        try {
            File f = new File("GuestInformation.txt");
            f.createNewFile();
            FileWriter fout = new FileWriter(f);
            fout.write(getFirstName() + "\n" + getLastName() + "\n" + getAddresss() + "\n" + getContact() + "\n" + getNoOfAdults() + "\n" + getNoOfChildren());
            fout.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        GuestInformation g = new GuestInformation();

    }
}
