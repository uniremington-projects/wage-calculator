package co.edu.miremington.programming.repository;

import co.edu.miremington.programming.data.Data;
import co.edu.miremington.programming.entity.Factory;

import java.util.List;
import java.util.Optional;

/**
 * @author devnix
 *
 */
public class FactoryRepository implements Data {

    public List<Factory> getAll() {
        return FACTORY_LIST;
    }

    public Optional<Factory> getFactoryById(Integer id) {
        return FACTORY_LIST.stream().filter(f -> f.getId() == id).findFirst();
    }

    public boolean add(Factory factory) {
        Optional<Factory> optionalFactory = this.getFactoryById(factory.getId());
        if (optionalFactory.isPresent()){
            return false;
        } else {
            FACTORY_LIST.add(factory);
            return true;
        }
    }

    public boolean update(Factory factory) {
        Optional<Factory> optionalFactory = this.getFactoryById(factory.getId());
        if (optionalFactory.isPresent()){
            return false;
        } else {
            FACTORY_LIST.stream().filter(f -> f.getId() == factory.getId()).forEach(u -> u = factory);
            return true;
        }
    }

    public boolean delete(Factory factory) {
        Optional<Factory> optionalFactory = this.getFactoryById(factory.getId());
        if (optionalFactory.isPresent()){
            FACTORY_LIST.removeIf(f -> f.getId() == factory.getId());
            return true;
        } else {
            return false;
        }
    }
}
