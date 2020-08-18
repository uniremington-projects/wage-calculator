package co.edu.miremington.programming.repository;

import co.edu.miremington.programming.data.Data;
import co.edu.miremington.programming.entity.weak.FactoryEmployee;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class FactoryEmployeeRepository implements Data {

    /**
     *
     * @return
     */
    public List<FactoryEmployee> getAll() {
        return FACTORY_EMPLOYEE_LIST;
    }

    public Optional<FactoryEmployee> getFactoryEmployeeById(Integer id) {
        return FACTORY_EMPLOYEE_LIST.stream().filter(f -> f.getId() == id).findFirst();
    }

    /**
     *
     * @param factoryEmployee
     * @return
     */
    public boolean add(FactoryEmployee factoryEmployee) {
        Optional<FactoryEmployee> optionalFactoryEmployee = this.getFactoryEmployeeById(factoryEmployee.getId());
        if (optionalFactoryEmployee.isPresent()){
            return false;
        } else {
            FACTORY_EMPLOYEE_LIST.add(factoryEmployee);
            return true;
        }
    }

    /**
     *
     * @param factoryEmployee
     * @return
     */
    public boolean update(FactoryEmployee factoryEmployee) {
        Optional<FactoryEmployee> optionalFactoryEmployee = this.getFactoryEmployeeById(factoryEmployee.getId());
        if (optionalFactoryEmployee.isPresent()){
            return false;
        } else {
            FACTORY_EMPLOYEE_LIST.stream().filter(f -> f.getId() == factoryEmployee.getId()).forEach(u -> u = factoryEmployee);
            return true;
        }
    }

    /**
     *
     * @param factoryEmployee
     * @return
     */
    public boolean delete(FactoryEmployee factoryEmployee) {
        Optional<FactoryEmployee> optionalFactoryEmployee = this.getFactoryEmployeeById(factoryEmployee.getId());
        if (optionalFactoryEmployee.isPresent()){
            FACTORY_EMPLOYEE_LIST.removeIf(f -> f.getId() == factoryEmployee.getId());
            return true;
        } else {
            return false;
        }
    }
}
