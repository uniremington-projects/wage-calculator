package co.edu.miremington.programming.entity;

import co.edu.miremington.programming.Enum.Documents;
import co.edu.miremington.programming.Enum.Shift;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;

/**
 * Record of a work day for an employee
 * @author devnix
 */
public class WorkingDay {
    private Integer id;
    private Employee employee;
    private Shift shift;
    private Integer workingHours;
    private float dailyWage;
    static final Logger logger = Logger.getLogger(Employee.class);

    public WorkingDay() {
        /**
         * default value for workingHours
         */
        this.workingHours = 8;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Integer getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Integer workingHours) {
        this.workingHours = workingHours;
    }

    public float getDailyWage() {
        dailyWage = this.workingHours * shift.getHourlyRate();
        return dailyWage;
    }

    public void setData(String key, Object value) {
        try {
            switch (key) {
                case "id":
                    setId(Integer.parseInt((String) value));
                    break;
                case "employee":
                    setEmployee((Employee) value);
                    break;
                case "shift":
                    setShift((Shift) value);
                    break;
                case "workingHours":
                    setWorkingHours(Integer.parseInt((String) value));
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
        logger.info("employee: " + employee);
        logger.info("shift: " + shift);
        logger.info("workingHours: " + workingHours);
        logger.info("dailyWage: " + getDailyWage());
        logger.info("-----------------------------------------");
        return "WorkingDay{" +
                "id=" + id +
                ", employee=" + employee +
                ", shift=" + shift +
                ", workingHours=" + workingHours +
                ", dailyWage=" + getDailyWage() +
                '}';
    }
}
