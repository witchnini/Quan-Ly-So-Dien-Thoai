package qlsdt_swing.qlsdt.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "phoneNumbers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumberXML {
    
    private List<PhoneNumber> phoneNumber;

    public List<PhoneNumber> getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}