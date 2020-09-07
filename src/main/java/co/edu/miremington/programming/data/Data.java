package co.edu.miremington.programming.data;

import co.edu.miremington.programming.Enum.Documents;
import co.edu.miremington.programming.Enum.Shift;
import co.edu.miremington.programming.entity.Employee;
import co.edu.miremington.programming.entity.Factory;
import co.edu.miremington.programming.entity.WorkingDay;
import co.edu.miremington.programming.entity.weak.FactoryEmployee;

import java.util.ArrayList;
import java.util.List;

/**
 * test data
 * @author devnix
 *
 */
public interface Data {
    List<Factory> FACTORY_LIST = new ArrayList<>();
    List<Employee> EMPLOYEE_LIST = new ArrayList<>();
    List<WorkingDay> WORKING_DAY_LIST = new ArrayList<>();
    List<FactoryEmployee> FACTORY_EMPLOYEE_LIST = new ArrayList<>();
}
