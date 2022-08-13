import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.sql.*;
/*
 * Created by JFormDesigner on Sat Aug 13 09:06:42 PDT 2022
 */



/**
 * @author Jumi Park
 */
public class loanproject extends JFrame {
    Conneection123 con = new Conneection123();
    Connection conobj = con.connect();

    public loanproject() throws SQLException, ClassNotFoundException {

        initComponents();
    }
    public void Retrievenitems() throws SQLException {
        String number = textField1.getText();

        String quer1 = "Select from loantable where clientno";
        PreparedStatement query = conobj.prepareStatement(quer1);
        query.setString(1, number);

        ResultSet rs = query.executeQuery();
        if(rs.isBeforeFirst()==false){
            JOptionPane.showMessageDialog(null, "There is loantalbe for this LoanP");
            return;

        }
        ResultSetMetaData res = rs.getMetaData();

        int p = res.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table2.getModel();
        df.setRowCount(0);
        rs.last();

        int i = rs.getRow();

        rs.beforeFirst();

        String[][] array = new String[0][];
        if(i>0){
            array = new String[i][5];
        }
        int z =0;
        while (rs.next()){
            array[z][0] = rs.getString("Payment");
            array[z][1] = rs.getString("Principal");
            array[z][2] = rs.getString("Interest");
            array[z][3] = rs.getString("Monthly Payment");
            array[z][4] = rs.getString("Balance");
            ++z;


        }
        String[] cols ={"Payment","Principal", "Interest", "Monthly Payment", "Balance"};
        DefaultTableModel model = new DefaultTableModel(array,cols);
        table2.setModel(model);
        table2.setDefaultEditor(Object.class, null);
    }

    private void table1MouseClicked(MouseEvent e) throws SQLException {
        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        int index = table1.getSelectedRow();
        textField1.setText(df.getValueAt(index,0).toString());
        textField2.setText(df.getValueAt(index,1).toString());
        textField3.setText(df.getValueAt(index,2).toString());
        textField4.setText(df.getValueAt(index,3).toString());
        textField5.setText(df.getValueAt(index,4).toString());

        Retrievenitems();

        // TODO add your code here

    }

    private void table2MouseClicked(MouseEvent e) {
        // TODO add your code here
    }

    private void button1(ActionEvent e) throws SQLException {
        String number, name, amount, years, typeofloan;
        number = textField1.getText();
        name =  textField2.getText();
        amount = textField3.getText();
        years = textField4.getText();
        typeofloan = textField5.getText();

        String quer1 = "Select * from loantable where clientno=?";
        PreparedStatement query = conobj.prepareStatement(quer1);
        query.setString(1, number);
        
        ResultSet re = query.executeQuery();
        
        if(re.isBeforeFirst()){
            JOptionPane.showMessageDialog(null, "Record edited");
            textField1.setText("");
            textField2.setText("");
            textField3.setText("");
            textField4.setText("");
            textField5.setText("");
            textField1.requestFocus();
            
            return;
        }
        String quer2 = "INSERT INTO loantable VALUES (?, ?)";
        query = conobj.prepareStatement(quer2);
        query.setString(1,name);
        query.setString(2,number);
        query.setString(3,amount);
        query.setString(4,years);
        query.setString(5,typeofloan);
        query.executeUpdate();
        JOptionPane.showMessageDialog(null, "Record added");
        
        Updatetable();
 
        // TODO add your code here
    }

    private void button2(ActionEvent e) throws SQLException {
        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        int index1 = table1.getSelectedRow();

        String number, name, amount, years, loan;



        number = textField1.getText();
        name = textField2.getText();
        amount = textField3.getText();
        years = textField4.getText();
        loan = textField5.getText();

        String oldvalue=df.getValueAt(index1,0).toString();

        PreparedStatement query;
        query = conobj.prepareStatement("Update category set catcode=?, catdesc=? where catcode = ?");
        query.setString(1, name);
        query.setString(2, number);
        query.setString(3,amount);
        query.setString(4, years);
        query.setString(5, loan);
        query.setString(6, oldvalue);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null, "One record edited ");

        Updatetable();
        // TODO add your code here
    }

    private void button3(ActionEvent e) throws SQLException {
        String name, number, amount, year, loan;
        name = textField1.getText();
        number = textField2.getText();
        amount = textField3.getText();
        year = textField4.getText();
        loan = textField5.getText();
        
        PreparedStatement query;
        query = conobj.prepareStatement("Do you want to delete this record? ");
        
        
        query.setString(1, number);
        
        query.executeUpdate();
        
        JOptionPane.showMessageDialog(null, "You can choice Yes or No");
        
        Updatetable();
                
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Jumi Park
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        label3 = new JLabel();
        textField3 = new JTextField();
        label4 = new JLabel();
        textField4 = new JTextField();
        label5 = new JLabel();
        comboBox1 = new JComboBox<>();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        label6 = new JLabel();
        textField5 = new JTextField();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the client number");
        contentPane.add(label1, "cell 0 0");
        contentPane.add(textField1, "cell 1 0");

        //---- label2 ----
        label2.setText("Enter the client name");
        contentPane.add(label2, "cell 0 1");
        contentPane.add(textField2, "cell 1 1");

        //---- label3 ----
        label3.setText("Enter the customer loan amount:");
        contentPane.add(label3, "cell 0 2");
        contentPane.add(textField3, "cell 1 2");

        //---- label4 ----
        label4.setText("Enter the number of years to pay");
        contentPane.add(label4, "cell 0 3");
        contentPane.add(textField4, "cell 1 3");

        //---- label5 ----
        label5.setText("Enter the loan type:");
        contentPane.add(label5, "cell 0 4");

        //---- comboBox1 ----
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
            "Business",
            "Personal"
        }));
        contentPane.add(comboBox1, "cell 1 4");

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    try {
                        table1MouseClicked(e);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 5");

        //======== scrollPane2 ========
        {

            //---- table2 ----
            table2.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table2MouseClicked(e);
                }
            });
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 1 5");

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> {
            try {
                button1(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        contentPane.add(button1, "cell 0 6");

        //---- button2 ----
        button2.setText("Edit");
        button2.addActionListener(e -> {
            try {
                button2(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        contentPane.add(button2, "cell 0 6");

        //---- button3 ----
        button3.setText("Delete");
        button3.addActionListener(e -> {
            try {
                button3(e);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        contentPane.add(button3, "cell 0 6");

        //---- label6 ----
        label6.setText("Monthly Payment");
        contentPane.add(label6, "cell 1 6");
        contentPane.add(textField5, "cell 1 6 2 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Jumi Park
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JLabel label5;
    private JComboBox<String> comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JLabel label6;
    private JTextField textField5;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

public void Updatetable() throws SQLException {
    String quer1 = "Select * from loantable";
    PreparedStatement query = conobj.prepareStatement(quer1);
    ResultSet rs = query.executeQuery();
    ResultSetMetaData Res = rs.getMetaData();



    int c = Res.getColumnCount();
    DefaultTableModel df = (DefaultTableModel) table1.getModel();
    df.setRowCount(0);
    rs.last();
    int z = rs.getRow();

    rs.beforeFirst();

    String[][] array = new String[0][];
    if(z>0) {
        array= new String[z][2];
    }

    int j=0;
    while(rs.next()) {
        array[j][0] = rs.getString("catcode");
        array[j][1] = rs.getString("catdesc");
        ++j;

    }

    String[] cols = {"loantable Code", "loantable Description"};

    DefaultTableModel model = new DefaultTableModel(array,cols);
    table1.setModel(model);

    table1.setDefaultEditor(Object.class, null);




}

}
