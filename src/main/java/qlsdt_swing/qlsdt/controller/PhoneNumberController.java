package qlsdt_swing.qlsdt.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import qlsdt_swing.qlsdt.func.PhoneNumberFunc;
import qlsdt_swing.qlsdt.entity.PhoneNumber;
import qlsdt_swing.qlsdt.view.PhoneNumberView;

public class PhoneNumberController {
    private PhoneNumberFunc phonenumberDao;
    private PhoneNumberView phonenumberView;

    public PhoneNumberController(PhoneNumberView view) {
        this.phonenumberView = view;
        phonenumberDao = new PhoneNumberFunc();

        view.addAddPhoneNumberListener(new AddPhoneNumberListener());
        view.addEditPhoneNumberListener(new EditPhoneNumberListener());
        view.addDeletePhoneNumberListener(new DeletePhoneNumberListener());
        view.addClearListener(new ClearPhoneNumberListener());
        view.addSortPhoneNumberPriceListener(new SortPhoneNumberPriceListener());
        view.addSortPhoneNumberNameListener(new SortPhoneNumberNameListener());
        view.addListPhoneNumberSelectionListener(new ListPhoneNumberSelectionListener());
        view.searchListener(new SearchListener());
    }

    public void showPhoneNumberView() {
        List<PhoneNumber> phonenumberList = phonenumberDao.getListPhoneNumbers();
        phonenumberView.setVisible(true);
        phonenumberView.showListPhoneNumbers(phonenumberList);
    }

    /**
     * Lớp AddStudentListener
     * chứa cài đặt cho sự kiện click button "Add"
     *
     * @author viettuts.vn
     */
    class AddPhoneNumberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PhoneNumber phonenumber = phonenumberView.getPhoneNumberInfo(false);
            if (phonenumber != null) {
                phonenumberDao.add(phonenumber);
                phonenumberView.showPhoneNumber(phonenumber);
                phonenumberView.showListPhoneNumbers(phonenumberDao.getListPhoneNumbers());
                phonenumberView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener
     * chứa cài đặt cho sự kiện click button "Edit"
     *
     * @author viettuts.vn
     */
    class EditPhoneNumberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PhoneNumber phonenumber = phonenumberView.getPhoneNumberInfo(false);
            if (phonenumber != null) {
                phonenumberDao.edit(phonenumber);
                phonenumberView.showPhoneNumber(phonenumber);
                phonenumberView.showListPhoneNumbers(phonenumberDao.getListPhoneNumbers());
                phonenumberView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteStudentListener
     * chứa cài đặt cho sự kiện click button "Delete"
     *
     * @author viettuts.vn
     */
    class DeletePhoneNumberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PhoneNumber phonenumber = phonenumberView.getPhoneNumberInfo(false);
            if (phonenumber != null) {
                phonenumberDao.delete(phonenumber);
                phonenumberView.clearPhoneNumberInfo();
                phonenumberView.showListPhoneNumbers(phonenumberDao.getListPhoneNumbers());
                phonenumberView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener
     * chứa cài đặt cho sự kiện click button "Clear"
     *
     * @author viettuts.vn
     */
    class ClearPhoneNumberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            phonenumberView.clearPhoneNumberInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By GPA"
     *
     * @author viettuts.vn
     */
    class SortPhoneNumberPriceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            phonenumberDao.sortPhoneNumberByPrice();
            phonenumberView.showListPhoneNumbers(phonenumberDao.getListPhoneNumbers());
        }
    }

    /**
     * Lớp SortStudentGPAListener
     * chứa cài đặt cho sự kiện click button "Sort By Name"
     *
     * @author viettuts.vn
     */
    class SortPhoneNumberNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            phonenumberDao.sortPhoneNumberByName();
            phonenumberView.showListPhoneNumbers(phonenumberDao.getListPhoneNumbers());
        }
    }

    /**
     * Lớp ListStudentSelectionListener
     * chứa cài đặt cho sự kiện chọn student trong bảng student
     *
     * @author viettuts.vn
     */
    class ListPhoneNumberSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            phonenumberView.fillPhoneNumberFromSelectedRow();
        }
    }


    /**
     * Lớp AddStudentListener
     * chứa cài đặt cho sự kiện click button "Add"
     *
     * @author viettuts.vn
     */
    class SearchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            PhoneNumber phonenumber = phonenumberView.getPhoneNumberInfo(true);
            if (phonenumber != null) {
                List<PhoneNumber> items =  phonenumberDao.getListPhoneNumbers();
                if (phonenumber.getFromPriceSearch() != 0) {
                    items = phonenumberDao.getListPhoneNumbers().stream().filter(x -> x.getPrice() >= phonenumber.getFromPriceSearch()).collect(Collectors.toList());
                }
                if (phonenumber.getToPriceSearch() != 0) {
                    items = phonenumberDao.getListPhoneNumbers().stream().filter(x -> x.getPrice() <= phonenumber.getToPriceSearch()).collect(Collectors.toList());
                }

                if (phonenumber.getPhoneNumberSearch()!= null && !Objects.equals(phonenumber.getPhoneNumberSearch(), "")) {
                    items = phonenumberDao.getListPhoneNumbers().stream().filter(x -> x.getPhoneNumber().contains(phonenumber.getPhoneNumberSearch()) ).collect(Collectors.toList());
                }
                phonenumberView.showListPhoneNumbers(items);

            }
        }
    }
}