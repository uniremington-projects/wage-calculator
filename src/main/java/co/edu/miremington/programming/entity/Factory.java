package co.edu.miremington.programming.entity;

import co.edu.miremington.programming.Enum.Documents;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Registration of a job factory
 * @author devnix
 */
public class Factory {

    private Integer id;
    private String name;
    private String description;
    private Documents documentType;
    private Integer documentNumber;
    private Integer phone;
    private String address;
    static final Logger logger = Logger.getLogger(Factory.class);

    public Factory() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Documents getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Documents documentType) {
        this.documentType = documentType;
    }

    public Integer getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(Integer documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        logger.info("-----------------------------------------");
        logger.info("id: " + id);
        logger.info("name: " + name);
        logger.info("description: " + description);
        logger.info("documentType: " + documentType);
        logger.info("documentNumber: " + documentNumber);
        logger.info("phone: " + phone);
        logger.info("address: " + address);
        logger.info("-----------------------------------------");
        return "Factory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", documentType=" + documentType +
                ", documentNumber=" + documentNumber +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }
}
