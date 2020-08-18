package co.edu.miremington.programming.service;

import co.edu.miremington.programming.entity.weak.FactoryEmployee;
import co.edu.miremington.programming.repository.FactoryEmployeeRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class FactoryEmployeeService {

    protected FactoryEmployeeRepository factoryEmployeeRepository = new FactoryEmployeeRepository();

    public List<FactoryEmployee> getAll() { return  this.factoryEmployeeRepository.getAll(); }

    public Optional<FactoryEmployee> getFactoryEmployeeById(Integer id) {
        return this.factoryEmployeeRepository.getFactoryEmployeeById(id);
    }

    public boolean add(FactoryEmployee factoryEmployee) { return this.factoryEmployeeRepository.add(factoryEmployee); }

    public boolean update(FactoryEmployee factoryEmployee) {return this.factoryEmployeeRepository.update(factoryEmployee); }

    public boolean delete(FactoryEmployee factoryEmployee) { return this.factoryEmployeeRepository.delete(factoryEmployee); }

}
