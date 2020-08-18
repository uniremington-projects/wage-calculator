package co.edu.miremington.programming.service;

import co.edu.miremington.programming.entity.Employee;
import co.edu.miremington.programming.entity.Factory;
import co.edu.miremington.programming.entity.WorkingDay;
import co.edu.miremington.programming.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class EmployeeService {

    protected EmployeeRepository employeeRepository = new EmployeeRepository();

    public List<Employee> getAll() { return  this.employeeRepository.getAll(); }

    public Optional<Employee> getEmployeeById(Integer id) {
        return this.employeeRepository.getEmployeeById(id);
    }

    public List<Employee> getEmployeeByFactory(Factory factory) {
        return this.employeeRepository.getEmployeeByFactory(factory);
    }

    public List<WorkingDay> getAllWorkingDayByEmployee(Employee employee) {
        return this.employeeRepository.getAllWorkingDayByEmployee(employee);
    }

    public float getTotalDailyWage(Employee employee) {
        float total = 0;
        for (WorkingDay data : this.employeeRepository.getAllWorkingDayByEmployee(employee)) {
            total += data.getDailyWage();
        }
        return total;
    }

    public boolean add(Employee employee) { return this.employeeRepository.add(employee); }

    public boolean update(Employee employee) {return this.employeeRepository.update(employee); }

    public boolean delete(Employee employee) { return this.employeeRepository.delete(employee); }

}
