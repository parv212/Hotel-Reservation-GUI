import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleInformation extends JFrame {

    private JFrame frame;
    private JLabel no_of_vehicles, type_of_vehicle, model, plate_no, no_vehicle;
    private JTextField n1,m,pn;
    private JCheckBox nv;
    private JButton previous, next;
    private JComboBox tov;

    public VehicleInformation()
    {
        frame = new JFrame();
        no_of_vehicles = new JLabel("No. of Vehicle: ");
        type_of_vehicle = new JLabel("Type of Vehicle: ");
        model = new JLabel("Model ");
        plate_no = new JLabel("Plate no. ");
        no_vehicle = new JLabel("No Vehicle ");
        n1 = new JTextField(2);
        m = new JTextField(10);
        pn = new JTextField(10);
        nv = new JCheckBox();
        previous = new JButton("Previous");
        next = new JButton("Next");

        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String st = e.getActionCommand();
                validateNumberOfVehicles();
                validateTypeOfVehicle();
                validateModel();
                validatePlateNo();
                if (st.equals("Next")){
                    createVehicleFile();
                    frame.dispose();
                    try {
                        new RateAndPaymentInformation();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

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
                        new GuestInformation();
                    }catch(Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }

        });

        nv.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(nv.isSelected()){}
                try {
                    n1.setText("");
                    pn.setText("");
                    m.setText("");
                } catch(Exception e1) {
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        addComp(panel, no_of_vehicles, 0, 0, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, n1, 1, 0, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, type_of_vehicle, 0, 1, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, model, 0, 2, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, m, 1, 2, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, plate_no, 0, 3, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, pn, 1, 3, 2, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, nv, 0, 4, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, no_vehicle, 1, 4, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);
        addComp(panel, previous, 0, 5, 1, 1, GridBagConstraints.EAST, GridBagConstraints.NONE);
        addComp(panel, next, 1, 5, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        JPanel tovpanel = new JPanel();
        tovpanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        String[] tovl = {"No Vehicle", "Two Wheeler", "Four Wheeler"};
        tov = new JComboBox(tovl);
        tovpanel.add(tov);
        addComp(panel, tovpanel, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Vehicle Information");
        frame.setSize(300,250);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        validate();
    }

    private void validateModel(){
        if(m.getText().isEmpty() && !nv.isSelected())
            JOptionPane.showMessageDialog(m,"Model field is empty please enter some valid value.");
    }

    private void validateTypeOfVehicle(){
        if(!m.getText().isEmpty() && !nv.isSelected() && tov.getSelectedItem().toString() == "No Vehicle")
            JOptionPane.showMessageDialog(tov,"Type of Vehicle field is showing No Vehicle please enter some valid value.");
    }

    private void validateNumberOfVehicles(){
        boolean valid = true;
        if(n1.getText().isEmpty() && !nv.isSelected())
            JOptionPane.showMessageDialog(n1,"Number of Vehicles field is empty please enter some valid value.");
        else{
            if(n1.getText().length() > 2 && !nv.isSelected())
                valid = false;
            if(!valid)
                JOptionPane.showMessageDialog(n1,"Enter valid Number of Vehicles");
            else{
                try{
                    if(!nv.isSelected())
                        Integer.parseInt(n1.getText());
                }
                catch (NumberFormatException e){
                    JOptionPane.showMessageDialog(n1,"Enter valid Number of Vehicles");
                }
            }
        }
    }

    private void validatePlateNo(){
        if(pn.getText().isEmpty() && !nv.isSelected())
            JOptionPane.showMessageDialog(pn,"Plate No. field is empty please enter some valid value.");
        else {
            Pattern pplate = Pattern.compile("\\D{2}-\\d{2}-\\D{2}-\\d{4}");
            Matcher mplate = pplate.matcher((pn.getText()));
            if (!(mplate.matches()) && !nv.isSelected())
                JOptionPane.showMessageDialog(pn, "Enter the valid Plate-No");
        }
    }

    public String getNoOfVehicle(){
        return "No. of Vehicles : " + nv.getText();
    }

    public String getTypeOfVehicle(){
        return "Type Of Vehicle : " + tov.getSelectedItem().toString();
    }

    public String getModel(){
        return "Model : " + m.getText();
    }

    public String getPlateNo(){
        return "Plate No : " + pn.getText();
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

    private void createVehicleFile(){
        try {
            File f = new File("GuestInformation.txt");
            //f.createNewFile();
            FileWriter fout = new FileWriter(f,true);
            fout.write("\n" + getNoOfVehicle() + "\n" + getTypeOfVehicle() + "\n" + getModel() + "\n" + getPlateNo());
            fout.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        VehicleInformation v = new VehicleInformation();
    }
}
