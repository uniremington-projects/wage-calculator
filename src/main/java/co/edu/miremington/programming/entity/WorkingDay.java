package co.edu.miremington.programming.entity;

import co.edu.miremington.programming.Enum.Shift;

/**
 * @author devnix
 * @apiNote Record of a work day for an employee
 *
 */
public class WorkingDay {
    private Integer id;
    private Employee employee;
    private Shift shift;
    private Integer workingHours;
    private float dailyWage;

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

    @Override
    public String toString() {
        return "WorkingDay{" +
                "id=" + id +
                ", employee=" + employee +
                ", shift=" + shift +
                ", workingHours=" + workingHours +
                ", dailyWage=" + dailyWage +
                '}';
    }
}
