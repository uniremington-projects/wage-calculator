package co.edu.miremington.programming.service;

import co.edu.miremington.programming.entity.Employee;
import co.edu.miremington.programming.entity.Factory;
import co.edu.miremington.programming.entity.WorkingDay;
import co.edu.miremington.programming.repository.EmployeeRepository;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 */
public class EmployeeService {

    static final Logger logger = Logger.getLogger(EmployeeService.class);
    protected EmployeeRepository employeeRepository = new EmployeeRepository();

    public List<Employee> getAll() {
        return this.employeeRepository.getAll();
    }

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
        double withholding = 0;
        int workingHours = 0;

        for (WorkingDay data : this.employeeRepository.getAllWorkingDayByEmployee(employee)) {
            total += data.getDailyWage();
            workingHours += data.getWorkingHours();
        }

        if (workingHours > 40 ) {
            logger.info("more than 40 hours: yes");
            total += total * 0.5;
        } else {
            logger.info("more than 40 hours: No");
        }

        logger.info("WorkingHours: " + workingHours);
        logger.info("-----------------------------------------");
        logger.info("Total Daily Wage: $" + total);

        if (total > 1400000) {
            withholding = total * 0.2;
        } else if (total > 880000 && total <= 1400000) {
            withholding = total * 0.15;
        } else if (total <= 880000) {
            withholding = total * 0.05;
        }
        logger.info("Withholding: $" + withholding);
        total -= withholding;
        return total;
    }

    public boolean add(Employee employee) {
        return this.employeeRepository.add(employee);
    }

    public boolean update(Employee employee) {
        return this.employeeRepository.update(employee);
    }

    public boolean delete(Employee employee) {
        return this.employeeRepository.delete(employee);
    }

}
