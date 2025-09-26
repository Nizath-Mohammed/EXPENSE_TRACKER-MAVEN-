package com.expense_tracker.GUI;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.FlowLayout; 
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
public class IntroFrame extends JFrame {
    private JButton categoryButton;
    private JButton expenseButton;
    public IntroFrame() {
        setTitle("Intro Frame");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeComponent();
        setUpActionListener();
    }
    private void initializeComponent() {
        setLayout(new GridBagLayout());
        // ensure Horizontal gap of 20 and vertical gap of 0
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
        this.categoryButton = new JButton("CATEGORY");
        this.expenseButton = new JButton("EXPENSE");
        this.categoryButton.setPreferredSize(new Dimension(100,100));
        this.expenseButton.setPreferredSize(new Dimension(100,100));

        centerPanel.add(categoryButton);
        centerPanel.add(expenseButton);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets= new Insets(8,8,8,8);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(centerPanel,gbc);
    }
    private void setUpActionListener(){
        this.categoryButton.addActionListener(
            (e)->{
                new catagoryFrame().setVisible(true);
            }

        );
        this.expenseButton.addActionListener(
            (e)->{
                new ExpenseFrame().setVisible(true);
            }
        );
    }
    private class catagoryFrame extends JFrame{
        private JTextField CategoryNameField;
        private JButton addButton;
        private JButton viewButton;
        private JButton deleteButton;
        private JTable categoryTable;
        private DefaultTableModel tableModel;
        
        public catagoryFrame(){
            setTitle("catagory Frame");
            setSize(400,400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            initializeComponent();
            setUpActionListener();
        }
        private void initializeComponent(){
            setLayout(new BorderLayout());
            JPanel northPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc= new GridBagConstraints();
            gbc.insets= new Insets(5,5,5,5);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            northPanel.add(new JLabel("Catagory Name"),gbc);
            gbc.gridx =1; 
            this.CategoryNameField= new JTextField();
            this.CategoryNameField.setPreferredSize(new Dimension(150,25));
            northPanel.add(this.CategoryNameField,gbc);
            add(northPanel,BorderLayout.NORTH);
            JPanel centerPanel =new JPanel(new FlowLayout(FlowLayout.CENTER,20,0));
            this.addButton = new JButton("ADD");
            this.viewButton = new JButton("VIEW");  
            this.deleteButton =new JButton("delete");
            centerPanel.add(this.addButton);
            centerPanel.add(this.viewButton);
            centerPanel.add(this.deleteButton);
            String[] columnNames={"Catagory Name"};
            this.tableModel =new DefaultTableModel(columnNames,0){
                @Override 
                public boolean isCellEditable(int row,int column){
                    return false;
                }
            };
            this.categoryTable =new JTable(tableModel);
            this.categoryTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.categoryTable.getSelectionModel().addListSelectionListener(
                (e)->{
                    if(!e.getValueIsAdjusting()){
                            int row = categoryTable.getSelectedRow();
                            if(row!=-1){
                                String selectedCategory =tableModel.getValueAt(row,0).toString();
                                CategoryNameField.setText(selectedCategory);
                            }
                    }
                }

            );
            JScrollPane scrollPane =new JScrollPane(this.categoryTable);
            scrollPane.setPreferredSize(new Dimension(350,300));
            centerPanel.add(new JPanel()); // empty panel for spacing
            centerPanel.add(scrollPane);
            add(centerPanel,BorderLayout.CENTER);





        }
        private void setUpActionListener(){
            this.addButton.addActionListener(
                (e)->{

                }
            );
            this.viewButton.addActionListener(
                (e)->{
                    String curr =this.CategoryNameField.getText().toString().trim();
                    if(!curr.isEmpty()){
                        new DetailFrame().setVisible(true);
                    }
                }
            );
            this.deleteButton.addActionListener(
                (e)->{

                }
            );
        }
        private class DetailFrame extends JFrame{
            public DetailFrame(){
                setTitle("Detail Frame");
                setSize(400,600);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            }
        }
        

    }
    private class ExpenseFrame extends JFrame{
        public ExpenseFrame(){
            setTitle("Expense Frame");
            setSize(400,600);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            initializeComponent();
        }

    }
}
