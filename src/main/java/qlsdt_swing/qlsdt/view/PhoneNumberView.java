package qlsdt_swing.qlsdt.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import qlsdt_swing.qlsdt.entity.PhoneNumber;
import qlsdt_swing.qlsdt.utils.DateLabelFormatter;

public class PhoneNumberView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addPhoneNumberBtn;
    private JButton editPhoneNumberBtn;
    private JButton deletePhoneNumberBtn;
    private JButton clearBtn;
    private JButton sortPhoneNumberPriceBtn;
    private JButton sortPhoneNumberNameBtn;
    private JButton searchBtn;
    private JScrollPane jScrollPanePhoneNumberTable;
    private JScrollPane jScrollPaneAddress;
    private JTable phoneNumberTable;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel idNumberLabel;
    private JLabel phoneNumberLabel;
    private JLabel carrierLabel;
    private JLabel servicePackageLabel;
    private JLabel priceLabel;
    private JLabel connectionTimeLabel;

    private JTextField idField;
    private JTextField nameField;
    private JTextField idNumberField;
    private JTextField phoneNumberField;
    private JComboBox<String> carrierField;
    private JComboBox<String> servicePackageField;
    private JTextField priceField;
    private JDatePickerImpl connectionTimeField;
    private UtilDateModel model;

    private JTextField formPriceSearch;
    private JTextField toPriceSearch;
    private JTextField phoneNumberSearch;


    private JLabel formPriceSearchLabel;
    private JLabel toPriceSearchLabel;
    private JLabel phoneNumberSearchLabel;

    // Define columns for phone number table
    private String[] columnNames = new String[]{
            "ID", "Họ và tên", "CCCD", "Số điện thoại", "Nhà mạng", "Gói dịch vụ đang dùng", "Giá cước", "Thời gian hòa mạng"};
    // Default data for phone number table
    private Object data = new Object[][]{};

    public PhoneNumberView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Initialize buttons
        addPhoneNumberBtn = new JButton("Add");
        editPhoneNumberBtn = new JButton("Edit");
        deletePhoneNumberBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        searchBtn = new JButton("Search");
        sortPhoneNumberPriceBtn = new JButton("Sort By Price");
        sortPhoneNumberNameBtn = new JButton("Sort By Name");
        // Initialize phone number table
        jScrollPanePhoneNumberTable = new JScrollPane();
        phoneNumberTable = new JTable();

        // Initialize labels
        idLabel = new JLabel("ID");
        nameLabel = new JLabel("Họ và tên");
        idNumberLabel = new JLabel("CCCD");
        phoneNumberLabel = new JLabel("Số điện thoại");
        carrierLabel = new JLabel("Nhà mạng");
        servicePackageLabel = new JLabel("Gói dịch vụ đang dùng");
        priceLabel = new JLabel("Giá cước");
        connectionTimeLabel = new JLabel("Thời gian hòa mạng");

        formPriceSearchLabel = new JLabel("Tìm kiếm giá cước từ ");
        toPriceSearchLabel = new JLabel("đến");
        phoneNumberSearchLabel = new JLabel("Tìm kiếm số điện thoại");

        // Initialize text fields
        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        idNumberField = new JTextField(15);
        phoneNumberField = new JTextField(15);
        String[] carrier = new String[]{"", "Viettel", "MobiFone", "VinaPhone"};
        carrierField = new JComboBox<String>(carrier);
        String[] servicePackage = new String[]{"", "VT27", "MB01", "VP24"};
        servicePackageField = new JComboBox<String>(servicePackage);
        priceField = new JTextField(6);

        formPriceSearch = new JTextField(6);
        toPriceSearch = new JTextField(6);
        phoneNumberSearch = new JTextField(15);

        // Set up table with columns and data
        phoneNumberTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPanePhoneNumberTable.setViewportView(phoneNumberTable);
        jScrollPanePhoneNumberTable.setPreferredSize(new Dimension(700, 300));
        Border roundedBorder = new RoundedBorder(5, Color.BLACK);
        Border paddingBorder = new EmptyBorder(10, 10, 10, 10);
        Border combinedBorder = new CompoundBorder(paddingBorder, roundedBorder);
        // Create layout and panel
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1250, 420);
        panel.setLayout(layout);
        panel.setBorder(combinedBorder);
        panel.add(jScrollPanePhoneNumberTable);

        panel.add(addPhoneNumberBtn);
        panel.add(editPhoneNumberBtn);
        panel.add(deletePhoneNumberBtn);
        panel.add(clearBtn);
        panel.add(searchBtn);
        panel.add(sortPhoneNumberPriceBtn);
        panel.add(sortPhoneNumberNameBtn);

        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(idNumberLabel);
        panel.add(phoneNumberLabel);
        panel.add(carrierLabel);
        panel.add(servicePackageLabel);
        panel.add(priceLabel);
        panel.add(connectionTimeLabel);
        panel.add(formPriceSearchLabel);
        panel.add(toPriceSearchLabel);
        panel.add(phoneNumberSearchLabel);

        panel.add(idField);
        panel.add(nameField);
        panel.add(idNumberField);
        panel.add(phoneNumberField);
        panel.add(carrierField);
        panel.add(servicePackageField);
        panel.add(priceField);
        panel.add(formPriceSearch);
        panel.add(toPriceSearch);
        panel.add(phoneNumberSearch);

        model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        connectionTimeField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        connectionTimeField.setButtonFocusable(false);
        panel.add(connectionTimeField);

        // Set positions of components
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, idNumberLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idNumberLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, phoneNumberLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumberLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, carrierLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, carrierLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, servicePackageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, servicePackageLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceLabel, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, connectionTimeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, connectionTimeLabel, 220, SpringLayout.NORTH, panel);


        layout.putConstraint(SpringLayout.WEST, idField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, idNumberField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idNumberField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, phoneNumberField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumberField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, carrierField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, carrierField, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, servicePackageField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, servicePackageField, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceField, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, connectionTimeField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, connectionTimeField, 220, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, formPriceSearchLabel, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, formPriceSearchLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, toPriceSearchLabel, 700, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, toPriceSearchLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, phoneNumberSearchLabel, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumberSearchLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, formPriceSearch, 600, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, formPriceSearch, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, toPriceSearch, 750, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, toPriceSearch, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, phoneNumberSearch, 600, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, phoneNumberSearch, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, searchBtn, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchBtn, 1000, SpringLayout.WEST,panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPanePhoneNumberTable, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPanePhoneNumberTable, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addPhoneNumberBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addPhoneNumberBtn, 260, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, editPhoneNumberBtn, 60, SpringLayout.WEST, addPhoneNumberBtn);
        layout.putConstraint(SpringLayout.NORTH, editPhoneNumberBtn, 260, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, deletePhoneNumberBtn, 60, SpringLayout.WEST, editPhoneNumberBtn);
        layout.putConstraint(SpringLayout.NORTH, deletePhoneNumberBtn, 260, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 260, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deletePhoneNumberBtn);

        layout.putConstraint(SpringLayout.WEST, sortPhoneNumberPriceBtn, 400, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortPhoneNumberPriceBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortPhoneNumberNameBtn, 115, SpringLayout.WEST, sortPhoneNumberPriceBtn);
        layout.putConstraint(SpringLayout.NORTH, sortPhoneNumberNameBtn, 400, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Quản lý số điện thoại");
        this.setSize(1170, 570);
        carrierField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) carrierField.getSelectedItem();
                if (selectedItem != null) {
                    updateComboBoxB(servicePackageField, selectedItem);
                }
            }
        });


        // Disable Edit and Delete buttons
        editPhoneNumberBtn.setEnabled(false);
        deletePhoneNumberBtn.setEnabled(false);
        // Enable Add button
        addPhoneNumberBtn.setEnabled(true);
    }

    private static void updateComboBoxB(JComboBox<String> servicePackage, String selectedItem) {
        servicePackage.removeAllItems();

        switch (selectedItem) {
            case "Viettel" -> servicePackage.addItem("VT27");
            case "MobiFone" -> servicePackage.addItem("MB01");
            case "VinaPhone" -> servicePackage.addItem("VP24");
            case "" -> {
                servicePackage.addItem("");
                servicePackage.addItem("VT27");
                servicePackage.addItem("MB01");
                servicePackage.addItem("VP24");
            }
            default -> {
            }
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * Display list of phone numbers in phoneNumberTable
     *
     * @param list
     */
    public void showListPhoneNumbers(List<PhoneNumber> list) {
        int size = list.size();
        // Initialize 2D array for phone numbers
        Object[][] phoneNumbers = new Object[size][8];
        for (int i = 0; i < size; i++) {
            phoneNumbers[i][0] = list.get(i).getId();
            phoneNumbers[i][1] = list.get(i).getFullName();
            phoneNumbers[i][2] = list.get(i).getIdNumber();
            phoneNumbers[i][3] = list.get(i).getPhoneNumber();
            phoneNumbers[i][4] = list.get(i).getCarrier();
            phoneNumbers[i][5] = list.get(i).getServicePackage();
            phoneNumbers[i][6] = list.get(i).getPrice();
            phoneNumbers[i][7] = list.get(i).getConnectionTime();
        }
        phoneNumberTable.setModel(new DefaultTableModel(phoneNumbers, columnNames));
    }

    /**
     * Fill information of selected row from phoneNumberTable into text fields
     */
    public void fillPhoneNumberFromSelectedRow() {
        // Get selected row index
        int row = phoneNumberTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(phoneNumberTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(phoneNumberTable.getModel().getValueAt(row, 1).toString());
            idNumberField.setText(phoneNumberTable.getModel().getValueAt(row, 2).toString());
            phoneNumberField.setText(phoneNumberTable.getModel().getValueAt(row, 3).toString());
            carrierField.setSelectedItem(phoneNumberTable.getModel().getValueAt(row, 4).toString());
            servicePackageField.setSelectedItem(phoneNumberTable.getModel().getValueAt(row, 5).toString());
            priceField.setText(phoneNumberTable.getModel().getValueAt(row, 6).toString());
            Calendar connectionTime = setDateInDatePicker(phoneNumberTable.getModel().getValueAt(row, 7).toString());
            model.setDate(connectionTime.get(Calendar.YEAR), connectionTime.get(Calendar.MONTH), connectionTime.get(Calendar.DAY_OF_MONTH));
            model.setSelected(true);
            // Enable Edit and Delete buttons
            editPhoneNumberBtn.setEnabled(true);
            deletePhoneNumberBtn.setEnabled(true);
            // Disable Add button
            addPhoneNumberBtn.setEnabled(false);
        }
    }

    public Calendar setDateInDatePicker(String dateString) {

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Clear phone number information
     */
    public void clearPhoneNumberInfo() {
        idField.setText("");
        nameField.setText("");
        idNumberField.setText("");
        phoneNumberField.setText("");
        carrierField.setSelectedItem("");
        servicePackageField.setSelectedItem("");
        priceField.setText("");
//        connectionTimeField.setText("");
        model.setSelected(false);
        // Disable Edit and Delete buttons
        editPhoneNumberBtn.setEnabled(false);
        deletePhoneNumberBtn.setEnabled(false);
        // Enable Add button
        addPhoneNumberBtn.setEnabled(true);
    }

    /**
     * Display phone number information
     *
     * @param phoneNumber
     */
    public void showPhoneNumber(PhoneNumber phoneNumber) {
        idField.setText("" + phoneNumber.getId());
        nameField.setText(phoneNumber.getFullName());
        idNumberField.setText(phoneNumber.getIdNumber());
        phoneNumberField.setText(phoneNumber.getPhoneNumber());
        carrierField.setSelectedItem(phoneNumber.getCarrier());
        servicePackageField.setSelectedItem(phoneNumber.getServicePackage());
        priceField.setText("" + phoneNumber.getPrice());
        Calendar connectionTime = setDateInDatePicker(phoneNumber.getConnectionTime());
        model.setDate(connectionTime.get(Calendar.YEAR), connectionTime.get(Calendar.MONTH), connectionTime.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);
//        connectionTimeField.setText(phoneNumber.getConnectionTime());
        // Enable Edit and Delete buttons
        editPhoneNumberBtn.setEnabled(true);
        deletePhoneNumberBtn.setEnabled(true);
        // Disable Add button
        addPhoneNumberBtn.setEnabled(false);
    }

    /**
     * Get phone number information
     *
     * @return
     */
    public PhoneNumber getPhoneNumberInfo(boolean isSearch) {
        if (!isSearch){
            // Validate phone number
            if (!validateName() || !validateIdNumber() || !validatePhoneNumber() || !validateCarrier() || !validateServicePackage() || !validatePrice() || !validateConnectionTime()) {
                return null;
            }
            try {
                PhoneNumber phoneNumber = new PhoneNumber();
                if (idField.getText() != null && !"".equals(idField.getText())) {
                    phoneNumber.setId(Integer.parseInt(idField.getText()));
                }
                phoneNumber.setFullName(nameField.getText().trim());
                phoneNumber.setIdNumber(idNumberField.getText().trim());
                phoneNumber.setPhoneNumber(phoneNumberField.getText().trim());
                phoneNumber.setCarrier(Objects.requireNonNull(carrierField.getSelectedItem()).toString());
                phoneNumber.setServicePackage(Objects.requireNonNull(servicePackageField.getSelectedItem()).toString());
                phoneNumber.setPrice(Float.parseFloat(priceField.getText().trim()));
                phoneNumber.setConnectionTime(connectionTimeField.getJFormattedTextField().getText().trim());

                return phoneNumber;
            } catch (Exception e) {
                showMessage(e.getMessage());
            }
        }else {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPhoneNumberSearch(phoneNumberSearch.getText());
            if (formPriceSearch.getText() != null && !Objects.equals(formPriceSearch.getText(), "")){
                phoneNumber.setFromPriceSearch(Float.parseFloat(formPriceSearch.getText().trim()));
            }
            if (toPriceSearch.getText() != null && !Objects.equals(toPriceSearch.getText(), "")) {
                phoneNumber.setToPriceSearch(Float.parseFloat(toPriceSearch.getText().trim()));
            }
            return phoneNumber;
        }

        return null;
    }

    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Full Name cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean validateIdNumber() {
        String idNumber = idNumberField.getText();
        if (idNumber == null || "".equals(idNumber.trim())) {
            idNumberField.requestFocus();
            showMessage("ID Number cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = phoneNumberField.getText();
        if (phoneNumber == null || "".equals(phoneNumber.trim())) {
            phoneNumberField.requestFocus();
            showMessage("Phone Number cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean validateCarrier() {
        String carrier = carrierField.getSelectedItem().toString();
        if (carrier == null || "".equals(carrier.trim())) {
            carrierField.requestFocus();
            showMessage("Carrier cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean validateServicePackage() {
        String servicePackage = servicePackageField.getSelectedItem().toString();
        if (servicePackage == null || "".equals(servicePackage.trim())) {
            servicePackageField.requestFocus();
            showMessage("Service Package cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean validatePrice() {
        try {
            Float price = Float.parseFloat(priceField.getText().trim());
            if (price < 0) {
                priceField.requestFocus();
                showMessage("Price cannot be negative.");
                return false;
            }
        } catch (Exception e) {
            priceField.requestFocus();
            showMessage("Invalid Price!");
            return false;
        }
        return true;
    }

    private boolean validateConnectionTime() {
        String connectionTime = connectionTimeField.getJFormattedTextField().getText();
        if (connectionTime == null || "".equals(connectionTime.trim())) {
            connectionTimeField.requestFocus();
            showMessage("Connection Time cannot be empty.");
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddPhoneNumberListener(ActionListener listener) {
        addPhoneNumberBtn.addActionListener(listener);
    }

    public void addEditPhoneNumberListener(ActionListener listener) {
        editPhoneNumberBtn.addActionListener(listener);
    }

    public void addDeletePhoneNumberListener(ActionListener listener) {
        deletePhoneNumberBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortPhoneNumberPriceListener(ActionListener listener) {
        sortPhoneNumberPriceBtn.addActionListener(listener);
    }

    public void addSortPhoneNumberNameListener(ActionListener listener) {
        sortPhoneNumberNameBtn.addActionListener(listener);
    }

    public void addListPhoneNumberSelectionListener(ListSelectionListener listener) {
        phoneNumberTable.getSelectionModel().addListSelectionListener(listener);
    }

    public void searchListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }

}