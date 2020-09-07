package co.edu.miremington.programming.repository;

import co.edu.miremington.programming.data.Data;
import co.edu.miremington.programming.entity.Employee;
import co.edu.miremington.programming.entity.Factory;
import co.edu.miremington.programming.entity.WorkingDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class EmployeeRepository implements Data {


    public List<Employee> getAll() {
        return EMPLOYEE_LIST;
    }


    public Optional<Employee> getEmployeeById(Integer id) {
        return EMPLOYEE_LIST.stream().filter(f -> f.getId() == id).findFirst();
    }


    public List<Employee> getEmployeeByFactory(Factory factory) {
        List<Employee> response = new ArrayList<>();
        FACTORY_EMPLOYEE_LIST.stream().filter(f -> f.getFactory().getId() == factory.getId()).forEach(item -> {
            response.add(item.getEmployee());
        });
        return response;
    }

    public List<WorkingDay> getAllWorkingDayByEmployee(Employee employee) {
        List<WorkingDay> response = new ArrayList<>();
        WORKING_DAY_LIST.stream().filter(f -> f.getEmployee().getId() == employee.getId()).forEach(item -> {
            response.add(item);
        });
        return response;
    }

    public boolean add(Employee employee) {
        Optional<Employee> optionalEmployee = this.getEmployeeById(employee.getId());
        if (optionalEmployee.isPresent()){
            return false;
        } else {
            EMPLOYEE_LIST.add(employee);
            return true;
        }
    }

    public boolean update(Employee employee) {
        Optional<Employee> optionalEmployee = this.getEmployeeById(employee.getId());
        if (optionalEmployee.isPresent()){
            return false;
        } else {
            EMPLOYEE_LIST.stream().filter(f -> f.getId() == employee.getId()).forEach(u -> u = employee);
            return true;
        }
    }

    public boolean delete(Employee employee) {
        Optional<Employee> optionalEmployee = this.getEmployeeById(employee.getId());
        if (optionalEmployee.isPresent()){
            EMPLOYEE_LIST.removeIf(f -> f.getId() == employee.getId());
            return true;
        } else {
            return false;
        }
    }
}
