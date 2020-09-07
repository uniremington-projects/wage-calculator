package co.edu.miremington.programming.entity;

import co.edu.miremington.programming.Enum.Documents;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;

/**
 * Registration of an employee
 * @author devnix
 */
public class Employee {

    private Integer id;
    private String name;
    private String lastName;
    private Documents documentType;
    private Integer documentNumber;
    private Integer age;
    private String gender;
    private String email;
    static final Logger logger = Logger.getLogger(Employee.class);

    public Employee() {
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData(String key, Object value) {
        try {
            switch (key) {
                case "id":
                    setId(Integer.parseInt((String) value));
                    break;
                case "name":
                    setName((String) value);
                    break;
                case "lastName":
                    setLastName((String) value);
                    break;
                case "documentType":
                    setDocumentType((Documents) value);
                    break;
                case "documentNumber":
                    setDocumentNumber(Integer.parseInt((String) value));
                    break;
                case "age":
                    setAge(Integer.parseInt((String) value));
                    break;
                case "gender":
                    setGender((String) value);
                    break;
                case "email":
                    setEmail((String) value);
                    break;
                default:
                    break;
            }
        } catch (InputMismatchException imx) {
            logger.error(imx);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public String toString() {
        logger.info("-----------------------------------------");
        logger.info("id: " + id);
        logger.info("name: " + name);
        logger.info("lastName: " + lastName);
        logger.info("documentType: " + documentType);
        logger.info("documentNumber: " + documentNumber);
        logger.info("age: " + age);
        logger.info("gender: " + gender);
        logger.info("email: " + email);
        logger.info("-----------------------------------------");
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", documentType=" + documentType +
                ", documentNumber=" + documentNumber +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
