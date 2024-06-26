package ehr340;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TreeNode {
    String question;
    TreeNode yesNode;
    TreeNode noNode;
    String lastYesResponse; // Store the last "Y" response for each question

    public TreeNode(String question) {
        this.question = question;
        this.yesNode = null;
        this.noNode = null;
        this.lastYesResponse = null;
    }
}

public class GeneralMedicalHistory extends javax.swing.JFrame {
int pid;
TreeNode root;
private boolean viewMode = true;

    public GeneralMedicalHistory() {
        initComponents();
    }
    public GeneralMedicalHistory(int patientid)
    {
        this.pid = patientid;
        initComponents();
        createDecisionTree();
        loadMedicalHistory(pid);
        fillBoxes(); 
        setViewMode();
    }
    
    private void setViewMode() {
        // Set text field enable/disable based on the mode
        viewMode = !viewMode;
        txtTobacco.setEditable(viewMode);
        txtTobaccoQuantity.setEditable(viewMode);
        txtTobaccoDuration.setEditable(viewMode);
        txtAlcohol.setEditable(viewMode);
        txtAlcoholQuantity.setEditable(viewMode);
        txtAlcoholDuration.setEditable(viewMode);
        txtDrug.setEditable(viewMode);
        txtDrugQuantity.setEditable(viewMode);
        txtDrugDuration.setEditable(viewMode);
        txtBloodType.setEditable(viewMode);
        txtRh.setEditable(viewMode);

        if(viewMode == false)
        {
            txtTobacco.setBackground(Color.LIGHT_GRAY);
            txtTobaccoQuantity.setBackground(Color.LIGHT_GRAY);
            txtTobaccoDuration.setBackground(Color.LIGHT_GRAY);
            txtAlcohol.setBackground(Color.LIGHT_GRAY);
            txtAlcoholQuantity.setBackground(Color.LIGHT_GRAY);
            txtAlcoholDuration.setBackground(Color.LIGHT_GRAY);
            txtDrug.setBackground(Color.LIGHT_GRAY);
            txtDrugQuantity.setBackground(Color.LIGHT_GRAY);
            txtDrugDuration.setBackground(Color.LIGHT_GRAY);
            txtBloodType.setBackground(Color.LIGHT_GRAY);
            txtRh.setBackground(Color.LIGHT_GRAY);
        }
        else
        {
            txtTobacco.setBackground(Color.WHITE);
            txtTobaccoQuantity.setBackground(Color.WHITE);
            txtTobaccoDuration.setBackground(Color.WHITE);
            txtAlcohol.setBackground(Color.WHITE);
            txtAlcoholQuantity.setBackground(Color.WHITE);
            txtAlcoholDuration.setBackground(Color.WHITE);
            txtDrug.setBackground(Color.WHITE);
            txtDrugQuantity.setBackground(Color.WHITE);
            txtDrugDuration.setBackground(Color.WHITE);
            txtBloodType.setBackground(Color.WHITE);
            txtRh.setBackground(Color.WHITE);
        }
    }


    private void createDecisionTree() {
    root = new TreeNode("Do you use tobacco?");
    TreeNode alcoholNode = new TreeNode("Do you use alcohol?");
    TreeNode drugNode = new TreeNode("Do you use any drugs?");
    root.yesNode = alcoholNode;
    root.noNode = drugNode;

    // Adding follow-up questions for quantity and duration
    alcoholNode.yesNode = new TreeNode("Is the quantity greater than 1 unit?");
    drugNode.yesNode = new TreeNode("Is the quantity greater than 1 unit?");
    alcoholNode.yesNode.yesNode = new TreeNode("Is the duration greater than 1 day?");
    drugNode.yesNode.yesNode = new TreeNode("Is the duration greater than 1 day?");
}

private void conductInterview(TreeNode node) {
    String response = JOptionPane.showInputDialog(node.question + " (Y/N)").toUpperCase();

    if (response.equals("Y")) {
        node.lastYesResponse = response; // Update last "Y" response
        if (node.yesNode != null) {
            // If there's a follow-up question, ask it
            if (node.yesNode.question != null) {
                conductInterview(node.yesNode);
            } else {
                // No follow-up question, end of interview
                System.out.println("End of interview.");
            }
        } else {
            // If it's a leaf node, no follow-up question
            System.out.println("End of interview.");
        }
    } else if (response.equals("N")) {
        if (node.noNode != null) {
            conductInterview(node.noNode);
        } else {
            // If it's a leaf node, no follow-up question
            System.out.println("End of interview.");
        }
    } else {
        // Invalid response
        System.out.println("Invalid response. Exiting interview.");
    }

    // Update text fields based on responses
    if (node.question.equals("Do you use tobacco?")) {
        txtTobacco.setText(response);
        txtTobaccoQuantity.setText(node.lastYesResponse); // Populate quantity with last "Y" response
    } else if (node.question.equals("Do you use alcohol?")) {
        txtAlcohol.setText(response);
        txtAlcoholQuantity.setText(node.lastYesResponse); // Populate quantity with last "Y" response
    } else if (node.question.equals("Do you use any drugs?")) {
        txtDrug.setText(response);
        txtDrugQuantity.setText(node.lastYesResponse); // Populate quantity with last "Y" response
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnInterview = new javax.swing.JButton();
        btnBlood = new javax.swing.JButton();
        btnViewMode = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMedicalHistory = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnDemo = new javax.swing.JButton();
        btnAllergies = new javax.swing.JButton();
        btnFamily = new javax.swing.JButton();
        btnMedications = new javax.swing.JButton();
        btnImmunizations = new javax.swing.JButton();
        btnHome = new javax.swing.JButton();
        panelFields = new javax.swing.JPanel();
        txtBloodType = new javax.swing.JTextField();
        txtRh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTobacco = new javax.swing.JTextField();
        txtTobaccoQuantity = new javax.swing.JTextField();
        txtTobaccoDuration = new javax.swing.JTextField();
        txtAlcohol = new javax.swing.JTextField();
        txtAlcoholQuantity = new javax.swing.JTextField();
        txtAlcoholDuration = new javax.swing.JTextField();
        txtDrug = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDrugQuantity = new javax.swing.JTextField();
        txtDrugDuration = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("General Medical History");

        jButton1.setText("New");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnUpdate.setText("Edit");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnInterview.setText("Substance Interview");
        btnInterview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInterviewActionPerformed(evt);
            }
        });

        btnBlood.setText("Blood Interview");
        btnBlood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBloodActionPerformed(evt);
            }
        });

        btnViewMode.setText("Toggle View Mode");
        btnViewMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewModeActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBlood, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInterview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnViewMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUpdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnInterview)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBlood)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnViewMode)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableMedicalHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableMedicalHistory);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        btnDemo.setText("View Demographics");
        btnDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDemoActionPerformed(evt);
            }
        });

        btnAllergies.setText("View Allergies");
        btnAllergies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAllergiesActionPerformed(evt);
            }
        });

        btnFamily.setText("View Family");
        btnFamily.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFamilyActionPerformed(evt);
            }
        });

        btnMedications.setText("View Medications");
        btnMedications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedicationsActionPerformed(evt);
            }
        });

        btnImmunizations.setText("View Immunization");
        btnImmunizations.setToolTipText("");
        btnImmunizations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImmunizationsActionPerformed(evt);
            }
        });

        btnHome.setText("Home");
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDemo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAllergies, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnFamily, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnMedications, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImmunizations, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAllergies)
                    .addComponent(btnMedications)
                    .addComponent(btnHome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFamily)
                    .addComponent(btnImmunizations)
                    .addComponent(btnDemo))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel10.setText("Drug Duration");

        jLabel11.setText("Drug Quantity");

        jLabel12.setText("Drug");

        jLabel1.setText("Tobacco");

        jLabel2.setText("Tobacco Quantity");

        jLabel3.setText("Tobacco Duration");

        jLabel4.setText("Alcohol");

        jLabel5.setText("Alcohol Quantity");

        jLabel6.setText("Alcohol Duration");

        jLabel8.setText("Rh");

        jLabel9.setText("Blood Type");

        javax.swing.GroupLayout panelFieldsLayout = new javax.swing.GroupLayout(panelFields);
        panelFields.setLayout(panelFieldsLayout);
        panelFieldsLayout.setHorizontalGroup(
            panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFieldsLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTobacco, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAlcoholDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlcoholQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAlcohol, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTobaccoDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTobaccoQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(79, 79, 79)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDrug, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRh, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDrugQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDrugDuration, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelFieldsLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panelFieldsLayout.setVerticalGroup(
            panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFieldsLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel1)
                    .addComponent(txtTobacco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDrug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel2)
                    .addComponent(txtTobaccoQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDrugQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel3)
                    .addComponent(txtTobaccoDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDrugDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4)
                    .addComponent(txtAlcohol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBloodType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(txtAlcoholQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelFieldsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtAlcoholDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(panelFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 17, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 22, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        insertMedicalHistory(pid, txtTobacco.getText(), txtTobaccoQuantity.getText(), txtTobaccoDuration.getText(), txtAlcohol.getText(), 
                txtAlcoholQuantity.getText(), txtAlcoholDuration.getText(), txtDrug.getText(), txtDrugQuantity.getText(), txtDrugDuration.getText(),
                txtBloodType.getText(), txtRh.getText());
        loadMedicalHistory(pid);
        fillBoxes();
        setViewMode();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateMedicalHistory(pid, txtTobacco.getText(), txtTobaccoQuantity.getText(), txtTobaccoDuration.getText(), txtAlcohol.getText(), 
                txtAlcoholQuantity.getText(), txtAlcoholDuration.getText(), txtDrug.getText(), txtDrugQuantity.getText(), txtDrugDuration.getText(),
                txtBloodType.getText(), txtRh.getText());
        loadMedicalHistory(pid);
        fillBoxes();
        setViewMode();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDemoActionPerformed
        PatientDemo patientDemo = new PatientDemo(pid);
        patientDemo.show();
        setVisible(false); //you can't see me!
        dispose();
    }//GEN-LAST:event_btnDemoActionPerformed

    private void btnAllergiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllergiesActionPerformed
        AllergyHistory allergyHistory = new AllergyHistory(pid);
        allergyHistory.show();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnAllergiesActionPerformed

    private void btnFamilyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFamilyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFamilyActionPerformed

    private void btnImmunizationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImmunizationsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImmunizationsActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        Home home = new Home();
        home.show();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnInterviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInterviewActionPerformed
        conductInterview(root);
    }//GEN-LAST:event_btnInterviewActionPerformed

    private void btnBloodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBloodActionPerformed
        conductBloodTypeInterview();
    }//GEN-LAST:event_btnBloodActionPerformed

    private void btnViewModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewModeActionPerformed
//        viewMode = !viewMode;
        setViewMode();
    }//GEN-LAST:event_btnViewModeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String update = "UPDATE generalmedicalhistorytable " + 
        "SET deleted = 1 WHERE patientID = " + pid;
        try
        {
            Connection con = ehr340.DBUtils.MakeConnection();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(update);
            con.close();
        }
        catch(Exception e){}
        loadMedicalHistory(pid);
        fillBoxes();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnMedicationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedicationsActionPerformed
        Medications medications = new Medications(pid);
        medications.show();
        setVisible(false);
        dispose();
    }//GEN-LAST:event_btnMedicationsActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GeneralMedicalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GeneralMedicalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GeneralMedicalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GeneralMedicalHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GeneralMedicalHistory().setVisible(true);
            }
        });
    }
    
    private void loadMedicalHistory(int pid)
    {
        String qry = "SELECT * " + 
                "FROM generalmedicalhistorytable WHERE patientID = " + pid + " AND deleted = 0";
        try
        {
            Connection con = ehr340.DBUtils.MakeConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            DefaultTableModel tbl = new DefaultTableModel();
            tbl.setColumnCount(14);
            while(rs.next())
            {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                rs.getString(12), rs.getString(13), rs.getString(14)};
                tbl.addRow(tbData);
            }
            tableMedicalHistory.setModel(tbl);
            con.close();
        }
        catch(Exception e){}
    }
    private void fillBoxes()
    {
        int rowCount =  tableMedicalHistory.getRowCount();
        if(rowCount != 0)
        {
            txtTobacco.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 2)));
            txtTobaccoQuantity.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 3)));
            txtTobaccoDuration.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 4)));
            txtAlcohol.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 5)));
            txtAlcoholQuantity.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 6)));
            txtAlcoholDuration.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 7)));
            txtDrug.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 8)));
            txtDrugQuantity.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 9)));
            txtDrugDuration.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 10)));
            txtBloodType.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 11)));
            txtRh.setText(getStringValue(tableMedicalHistory.getModel().getValueAt(0, 12)));
        }

    }
    private String getStringValue(Object obj) 
    {
        return (obj != null) ? obj.toString() : "";
    }
    
    private void insertMedicalHistory(int patientID, String tobacco, String tobaccoQuantity, String tobaccoDuration, String alcohol, 
            String alcoholQuantity, String alcoholDuration, String drug, String drugType, String drugDuration, String bloodType, String rh)
    {
                String storedProcedureCall = "{call insertGeneralMedicalHistory(?,?,?,?,?,?,?,?,?,?,?,?)}";

        try 
        {
            Connection con = ehr340.DBUtils.MakeConnection();

             CallableStatement statement = con.prepareCall(storedProcedureCall);
                

            // Set parameters for the stored procedure
            
            statement.setInt(1, patientID);
            statement.setString(2, tobacco);
            statement.setString(3, tobaccoQuantity);
            statement.setString(4, tobaccoDuration);
            statement.setString(5, alcohol);
            statement.setString(6, alcoholQuantity);
            statement.setString(7, alcoholDuration);
            statement.setString(8, drug);
            statement.setString(9, drugType);
            statement.setString(10, drugDuration);
            statement.setString(11, bloodType);
            statement.setString(12, rh);

            // Execute the stored procedure
            statement.execute();
            System.out.println("Medical History updated successfully.");
        } 
        catch (Exception e) 
        {
            System.err.println("Error updating demographics: " + e.getMessage());
        }
    }
    private void updateMedicalHistory(int patientID, String tobacco, String tobaccoQuantity, String tobaccoDuration, String alcohol, 
            String alcoholQuantity, String alcoholDuration, String drug, String drugType, String drugDuration, String bloodType, String rh)
    {
                String storedProcedureCall = "{call updateGeneralMedicalHistory(?,?,?,?,?,?,?,?,?,?,?,?)}";

        try 
        {
            Connection con = ehr340.DBUtils.MakeConnection();

             CallableStatement statement = con.prepareCall(storedProcedureCall);
                

            // Set parameters for the stored procedure
            
            statement.setInt(1, patientID);
            statement.setString(2, tobacco);
            statement.setString(3, tobaccoQuantity);
            statement.setString(4, tobaccoDuration);
            statement.setString(5, alcohol);
            statement.setString(6, alcoholQuantity);
            statement.setString(7, alcoholDuration);
            statement.setString(8, drug);
            statement.setString(9, drugType);
            statement.setString(10, drugDuration);
            statement.setString(11, bloodType);
            statement.setString(12, rh);

            // Execute the stored procedure
            statement.execute();
            System.out.println("Medical History updated successfully.");
        } 
        catch (Exception e) 
        {
            System.err.println("Error updating demographics: " + e.getMessage());
        }
    }
    
    private void conductBloodTypeInterview() {
        String[] bloodTypes = {"A", "B", "AB", "O"};
        String[] rhFactors = {"+", "-"};

        for (String bloodType : bloodTypes) {
            for (String rh : rhFactors) {
                String response = JOptionPane.showInputDialog("Do you have blood type " + bloodType + rh + "? (Y/N)").toUpperCase();

                if (response.equals("Y")) {
                    txtBloodType.setText(bloodType/* + rh*/); //!!!! here
                    txtRh.setText(rh);
                    return; // Exit the method after determining the blood type
                } else if (!response.equals("N")) {
                    System.out.println("Invalid response. Please answer with Y or N.");
                }
            }
        }

        System.out.println("Unable to determine blood type.");
    }    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllergies;
    private javax.swing.JButton btnBlood;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDemo;
    private javax.swing.JButton btnFamily;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnImmunizations;
    private javax.swing.JButton btnInterview;
    private javax.swing.JButton btnMedications;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnViewMode;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFields;
    private javax.swing.JTable tableMedicalHistory;
    private javax.swing.JTextField txtAlcohol;
    private javax.swing.JTextField txtAlcoholDuration;
    private javax.swing.JTextField txtAlcoholQuantity;
    private javax.swing.JTextField txtBloodType;
    private javax.swing.JTextField txtDrug;
    private javax.swing.JTextField txtDrugDuration;
    private javax.swing.JTextField txtDrugQuantity;
    private javax.swing.JTextField txtRh;
    private javax.swing.JTextField txtTobacco;
    private javax.swing.JTextField txtTobaccoDuration;
    private javax.swing.JTextField txtTobaccoQuantity;
    // End of variables declaration//GEN-END:variables
}
