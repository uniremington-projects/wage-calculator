package co.edu.miremington.programming;


import co.edu.miremington.programming.Enum.Documents;
import co.edu.miremington.programming.Enum.Shift;
import co.edu.miremington.programming.data.Data;
import co.edu.miremington.programming.entity.Employee;
import co.edu.miremington.programming.entity.Factory;
import co.edu.miremington.programming.entity.WorkingDay;
import co.edu.miremington.programming.entity.weak.FactoryEmployee;
import co.edu.miremington.programming.service.EmployeeService;
import co.edu.miremington.programming.service.FactoryEmployeeService;
import co.edu.miremington.programming.service.FactoryService;
import co.edu.miremington.programming.service.WorkingDayService;
import org.apache.log4j.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Falta intentar realizar el crud desde los servicios en el contructor public Process(Boolean simulateError)
 *
 * @author Frank Yellin
 * @jls 11.2 Compile-Time Checking of Exceptions
 * @see java.lang.Runtime
 * @since JDK1.8
 */
public class Process implements Data {

    static final Logger logger = Logger.getLogger(Process.class);
    static protected FactoryService factoryService = new FactoryService();
    static protected EmployeeService employeeService = new EmployeeService();
    static protected WorkingDayService workingDayService = new WorkingDayService();
    static protected FactoryEmployeeService factoryEmployeeService = new FactoryEmployeeService();

    static int attempts = 3;
    static boolean valid = false;
    static Scanner sc;

    static Factory selectedFactory;
    static Employee selectedEmployee;

    private boolean simulateError;

    public String state() throws Exception {
        if (simulateError) {
            throw new Exception("Could not load data!");
        } else {
            return "Data loaded!";
        }
    }

    public void start() throws Exception {
        logger.info(state());
        /**
         * selected Factory
         */
        do {
            selectedFactory();
        } while (attempts >= 1 && !valid);
        continueProcess();

        /**
         * selected Employee
         */
        do {
            selectedEmployee();
        } while (attempts >= 1 && !valid);
        continueProcess();

        getTotalDailyWage();

        logger.info("Ended process: status 200");
        System.exit(200);
    }

    public static void continueProcess() {
        /**
         * logger.info("continueProcess, attempts: " + attempts);
          */
        if (attempts <= 0) {
            logger.warn("Number of attempts exceeded! : status 406");
            System.exit(406);
        } else {
            attempts = 3;
        }
    }

    public static void selectedEmployee() {
        valid = false;
        try {
            sc = new Scanner(System.in);
            logger.info("Select employee\n");
            List<Employee> employeeList = employeeService.getAll();
            for (int i = 0; i < employeeList.size(); i++) {
                logger.info(i + 1 + ") " + employeeList.get(i).getName());
            }

            selectedEmployee = employeeList.get(sc.nextInt() - 1);

            selectedEmployee.toString();
            valid = true;
        } catch (IndexOutOfBoundsException e) {
            logger.error(e);
            attempts--;
        } catch (InputMismatchException imx) {
            logger.error(imx);
            attempts--;
        } catch (Exception ex) {
            logger.error(ex);
            attempts--;
        }
    }

    public static void selectedFactory() {
        valid = false;
        try {
            sc = new Scanner(System.in);
            logger.info("Select the factory\n");
            List<Factory> factoryList = factoryService.getAll();
            for (int i = 0; i < factoryList.size(); i++) {
                logger.info(i + 1 + ") " + factoryList.get(i).getName());
            }

            selectedFactory = factoryList.get(sc.nextInt() - 1);

            selectedFactory.toString();
            valid = true;
        } catch (IndexOutOfBoundsException e) {
            logger.error(e);
            attempts--;
        } catch (InputMismatchException imx) {
            logger.error(imx);
            attempts--;
        } catch (Exception ex) {
            logger.error(ex);
            attempts--;
        }
    }

    public void getTotalDailyWage() {
        logger.info("Working Day of " + selectedEmployee.getName()
                + " " + selectedEmployee.getLastName());
        for (WorkingDay workingDay : employeeService.getAllWorkingDayByEmployee(selectedEmployee)) {
            logger.info("-----------------------------------------");
            logger.info("Shift: " + workingDay.getShift().getName());
            logger.info("Hourly Rate: " + workingDay.getShift().getHourlyRate());
            logger.info("Working Hours: " + workingDay.getWorkingHours());
            logger.info("Daily Wage: $" + workingDay.getDailyWage());
        }

        logger.info("-----------------------------------------");
        logger.info("Total Daily Wage: $"  + employeeService.getTotalDailyWage(selectedEmployee));
        logger.info("-----------------------------------------");
    }

    /**
     * @param simulateError
     */
    public Process(Boolean simulateError) {
        try {
            /**
             * simulation of data returned from database
             *
             */
            logger.info("Loading data...");
            this.simulateError = simulateError;
            Factory factory = new Factory();
            factory.setId(1);
            factory.setName("New Factory");
            factory.setAddress("CRA 123 #456");
            factory.setDescription("This new factory");
            factory.setDocumentType(Documents.NIT);
            factory.setDocumentNumber(123456);

            if (!factoryService.add(factory)) {
                logger.warn("could not add factory with id:" + factory.getId());
            }


            Employee employee1 = new Employee();
            employee1.setId(1);
            employee1.setName("Jack");
            employee1.setLastName("Black");
            employee1.setAge(45);
            employee1.setDocumentType(Documents.IDENTIFICATION_CARD);
            employee1.setDocumentNumber(543212);

            Employee employee2 = new Employee();
            employee2.setId(2);
            employee2.setName("Ton");
            employee2.setLastName("Riben");
            employee2.setAge(37);
            employee2.setDocumentType(Documents.IDENTIFICATION_CARD);
            employee2.setDocumentNumber(743238);

            if (!employeeService.add(employee1)) {
                logger.warn("could not add employee with id:" + employee1.getId());
            }

            if (!employeeService.add(employee2)) {
                logger.warn("could not add employee with id:" + employee2.getId());
            }


            WorkingDay workingDay1 = new WorkingDay();
            workingDay1.setId(1);
            workingDay1.setEmployee(employee1);
            workingDay1.setShift(Shift.DAY_SHIFT);
            workingDay1.setWorkingHours(10);

            WorkingDay workingDay2 = new WorkingDay();
            workingDay2.setId(2);
            workingDay2.setEmployee(employee1);
            workingDay2.setShift(Shift.NIGHT_SHIFT);

            WorkingDay workingDay3 = new WorkingDay();
            workingDay3.setId(3);
            workingDay3.setEmployee(employee2);
            workingDay3.setShift(Shift.DOMINICAL_DAY_SHIFT);
            workingDay3.setWorkingHours(5);

            WorkingDay workingDay4 = new WorkingDay();
            workingDay4.setId(4);
            workingDay4.setEmployee(employee2);
            workingDay4.setShift(Shift.DAY_SHIFT);

            WorkingDay workingDay5 = new WorkingDay();
            workingDay5.setId(5);
            workingDay5.setEmployee(employee2);
            workingDay5.setShift(Shift.NIGHT_SHIFT);

            WorkingDay workingDayError = new WorkingDay();
            if (simulateError) {
                throw new Exception("Scheduled error");
            }

            if (!workingDayService.add(workingDay1)) {
                logger.warn("could not add workingDay with id:" + workingDay1.getId());
            }

            if (!workingDayService.add(workingDay2)) {
                logger.warn("could not add workingDay with id:" + workingDay2.getId());
            }

            if (!workingDayService.add(workingDay3)) {
                logger.warn("could not add workingDay with id:" + workingDay3.getId());
            }

            if (!workingDayService.add(workingDay4)) {
                logger.warn("could not add workingDay with id:" + workingDay4.getId());
            }

            if (!workingDayService.add(workingDay5)) {
                logger.warn("could not add workingDay with id:" + workingDay5.getId());
            }

            FactoryEmployee factoryEmployee1 = new FactoryEmployee();
            factoryEmployee1.setId(1);
            factoryEmployee1.setFactory(factory);
            factoryEmployee1.setEmployee(employee1);

            FactoryEmployee factoryEmployee2 = new FactoryEmployee();
            factoryEmployee2.setId(2);
            factoryEmployee2.setFactory(factory);
            factoryEmployee2.setEmployee(employee2);


            if (!factoryEmployeeService.add(factoryEmployee1)) {
                logger.warn("could not add factoryEmployee with id:" + factoryEmployee1.getId());
            }

            if (!factoryEmployeeService.add(factoryEmployee2)) {
                logger.warn("could not add factoryEmployee with id:" + factoryEmployee2.getId());
            }

            this.simulateError = false;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
