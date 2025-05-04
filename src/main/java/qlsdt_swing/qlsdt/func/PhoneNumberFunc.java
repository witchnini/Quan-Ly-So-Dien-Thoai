package qlsdt_swing.qlsdt.func;

import java.util.*;

import qlsdt_swing.qlsdt.entity.PhoneNumber;
import qlsdt_swing.qlsdt.entity.PhoneNumberXML;
import qlsdt_swing.qlsdt.utils.FileUtils;

/**
 * StudentFunc class
 *
 * @author viettuts.vn
 */
public class PhoneNumberFunc {
    private static final String PHONENUMBER_FILE_NAME = "phonenumber.xml";
    private List<PhoneNumber> listPhoneNumbers;

    public PhoneNumberFunc() {
        this.listPhoneNumbers = new ArrayList<PhoneNumber>();
    }

    /**
     * Lưu các đối tượng student vào file student.xml
     *
     * @param phonenumbers
     */
    public void writeListPhoneNumbers(List<PhoneNumber> phonenumbers) {
        PhoneNumberXML phonenumberXML = new PhoneNumberXML();
        phonenumberXML.setPhoneNumber(phonenumbers);
        FileUtils.writeXMLtoFile(PHONENUMBER_FILE_NAME, phonenumberXML);
    }
    /**
     * Đọc các đối tượng student từ file student.xml
     *
     * @return list student
     */


    /**
     * thêm student vào listStudents và lưu listStudents vào file
     *
     * @param phonenumber
     */
    public void add(PhoneNumber phonenumber) {
        int id = (listPhoneNumbers.size() > 0) ? (listPhoneNumbers.size() + 1) : 1;
        phonenumber.setId(id);
        listPhoneNumbers.add(phonenumber);
        writeListPhoneNumbers(listPhoneNumbers);
    }

    /**
     * cập nhật student vào listStudents và lưu listStudents vào file
     *
     * @param phonenumber
     */
    public void edit(PhoneNumber phonenumber) {
        int size = listPhoneNumbers.size();
        for (int i = 0; i < size; i++) {
            if (Objects.equals(listPhoneNumbers.get(i).getId(), phonenumber.getId())) {
                listPhoneNumbers.get(i).setIdNumber(phonenumber.getIdNumber());
                listPhoneNumbers.get(i).setFullName(phonenumber.getFullName());
                listPhoneNumbers.get(i).setPhoneNumber(phonenumber.getPhoneNumber());
                listPhoneNumbers.get(i).setCarrier(phonenumber.getCarrier());
                listPhoneNumbers.get(i).setServicePackage(phonenumber.getServicePackage());
                listPhoneNumbers.get(i).setPrice(phonenumber.getPrice());
                listPhoneNumbers.get(i).setConnectionTime(phonenumber.getConnectionTime());
                writeListPhoneNumbers(listPhoneNumbers);
                break;
            }
        }
    }

    /**
     * xóa student từ listStudents và lưu listStudents vào file
     *
     * @param phonenumber
     */
    public boolean delete(PhoneNumber phonenumber) {
        boolean isFound = false;
        int size = listPhoneNumbers.size();
        for (int i = 0; i < size; i++) {
            if (listPhoneNumbers.get(i).getId() == phonenumber.getId()) {
                phonenumber = listPhoneNumbers.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listPhoneNumbers.remove(phonenumber);
            writeListPhoneNumbers(listPhoneNumbers);
            return true;
        }
        return false;
    }

    /**
     * sắp xếp danh sách student theo name theo tứ tự tăng dần
     */
    public void sortPhoneNumberByName() {
        Collections.sort(listPhoneNumbers, new Comparator<PhoneNumber>() {
            public int compare(PhoneNumber phonenumber1, PhoneNumber phonenumber2) {
                return phonenumber1.getFullName().compareTo(phonenumber2.getFullName());
            }
        });
    }

    /**
     * sắp xếp danh sách student theo GPA theo tứ tự tăng dần
     */
    public void sortPhoneNumberByPrice() {
        Collections.sort(listPhoneNumbers, new Comparator<PhoneNumber>() {
            public int compare(PhoneNumber phonenumber1, PhoneNumber phonenumber2) {
                if (phonenumber1.getPrice() > phonenumber2.getPrice()) {
                    return 1;
                }
                return -1;
            }
        });
    }

    public List<PhoneNumber> getListPhoneNumbers() {
        return listPhoneNumbers;
    }

    public void setListPhoneNumbers(List<PhoneNumber> listPhoneNumbers) {
        this.listPhoneNumbers = listPhoneNumbers;
    }
}