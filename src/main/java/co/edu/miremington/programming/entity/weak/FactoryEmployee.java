package co.edu.miremington.programming.entity.weak;

import co.edu.miremington.programming.entity.Employee;
import co.edu.miremington.programming.entity.Factory;

public class FactoryEmployee {
    private Integer id;
    private Factory factory;
    private Employee employee;

    public FactoryEmployee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "FactoryEmployee{" +
                "id=" + id +
                ", factory=" + factory +
                ", employee=" + employee +
                '}';
    }
}
