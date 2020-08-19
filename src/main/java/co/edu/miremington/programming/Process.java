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
import jdk.nashorn.internal.ir.WhileNode;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Factory employees work two shifts, day and night You want to calculate the daily wage according to the following points:
 * a) The rate for the daily hours is $ 15000
 * b) The rate for night hours is $ 20,000
 * c) If it is Sunday, the rate will increase by $ 500 pesos on day shift and $ 750 pesos the night shift
 *
 * @author devnix
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
    static boolean valid = false, exit = false;
    static Scanner sc;

    static Factory selectedFactory;
    static Employee selectedEmployee;
    static int selectedAction;
    static int selectedOption;

    private boolean simulateError;

    /**
     * @return
     * @throws Exception
     */
    public String state() throws Exception {
        if (simulateError) {
            throw new Exception("Could not load data!");
        } else {
            return "Data loaded!";
        }
    }

    /**
     * @throws Exception
     */
    public void start() throws Exception {
        logger.info(state());
        /**
         * selected Factory
         */
        do {
            selectedFactory();
        } while (attempts >= 1 && !valid);
        continueProcess();

        do {
            menu();
        } while (!exit);

        logger.info("Ended process: status 200");
        System.exit(200);
    }

    /**
     * Menu
     */
    public void menu() {
        valid = false;
        try {
            sc = new Scanner(System.in);

            do {
                logger.info("-----------------Options------------------");
                String[] actions = {"options", "Employee", "WorkingDay", "Exit"};
                selectedActionOption(actions);
            } while (attempts >= 1 && !valid);
            continueProcess();

            switch (selectedOption) {
                case 3:
                    exit = true;
                    break;
            }

            if (!exit) {

                do {
                    logger.info("-----------------Actions------------------");
                    String[] actions = (selectedOption == 1) ? new String[]{"actions", "Create", "Read", "Back"} : new String[]{"actions", "Create", "Back"};
                    selectedActionOption(actions);
                } while (attempts >= 1 && !valid);
                continueProcess();

                switch (selectedAction) {
                    case 1:
                        String[] fields = {"id", "name", "lastName", "documentType", "documentNumber", "age", "gender", "email"};
                        if (selectedOption == 1) {
                            addEmployee(fields);
                        } else {
                            fields = new String[]{"id", "employee", "shift", "workingHours"};
                            addWorkingDay(fields);
                        }
                        break;
                    case 2:
                        if (selectedOption == 1) {
                            /**
                             * selected Employee
                             */
                            do {
                                selectedEmployee();
                            } while (attempts >= 1 && !valid);
                            continueProcess();

                            /**
                             * the method that consults the Total Daily Salary is invoked
                             */
                            getTotalDailyWage();
                        }
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
            }
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

    /**
     * @param data
     */
    public void addEmployee(String[] data) {
        sc = new Scanner(System.in);
        Employee employee = new Employee();
        Field[] fields = employee.getClass().getDeclaredFields();
        logger.info("#############################################");
        logger.info("###   Employee creation request form     ####");
        logger.info("#############################################");
        logger.info("total fields: " + fields.length);
        logger.info("#	Enter employee data");
        for (Field field : fields) {
            try {
                valid = false;
                do {
                    if (required(data, field.getName())) {
                        logger.info("#	" + field.getName() + ": ");
                        sc = new Scanner(System.in);
                        if (field.getName().equalsIgnoreCase("documentType")) {
                            int type = 0;
                            try {
                                logger.info("#	Select documentType: ");
                                logger.info("1) " + Documents.IDENTIFICATION_CARD);
                                logger.info("2) " + Documents.FORENG_IDENTIFICATION_CARD);
                                logger.info("3) " + Documents.NIT);
                                type = sc.nextInt();
                                switch (type) {
                                    case 1:
                                        employee.setData(field.getName(), Documents.IDENTIFICATION_CARD);
                                        valid = true;
                                        break;
                                    case 2:
                                        employee.setData(field.getName(), Documents.FORENG_IDENTIFICATION_CARD);
                                        valid = true;
                                        break;
                                    case 3:
                                        employee.setData(field.getName(), Documents.NIT);
                                        valid = true;
                                        break;
                                    default:
                                        logger.warn("Invalid Option");
                                        break;
                                }
                            } catch (NumberFormatException nf) {
                                logger.error(nf);
                                attempts--;
                            } catch (InputMismatchException imx) {
                                logger.error(imx);
                                attempts--;
                            } catch (Exception ex) {
                                logger.error(ex);
                                attempts--;
                            }
                        } else {
                            employee.setData(field.getName(), sc.next());
                            valid = true;
                        }
                    }
                } while (attempts >= 1 && !valid);
                continueProcess();

            } catch (IndexOutOfBoundsException e) {
                logger.error(e);
                attempts--;
            } catch (InputMismatchException imx) {
                logger.error(imx);
                attempts--;
            } catch (NumberFormatException nf) {
                logger.error(nf);
                attempts--;
            } catch (Exception ex) {
                logger.error(ex);
                attempts--;
            }
        }

        if (!employeeService.add(employee)) {
            logger.warn("could not add employee with id:" + employee.getId());
        } else {
            employeeService.getEmployeeById(employee.getId()).get().toString();
        }

    }

    /**
     * @param data
     */
    public void addWorkingDay(String[] data) {
        sc = new Scanner(System.in);
        WorkingDay workingDay = new WorkingDay();
        try {
            Field[] fields = workingDay.getClass().getDeclaredFields();
            logger.info("##################################################");
            logger.info("### WorkingDay by Employee creation request form ####");
            logger.info("##################################################");
            logger.info("total fields: " + fields.length);
            logger.info("#	Enter employee data");
            for (Field field : fields) {
                if (required(data, field.getName())) {
                    logger.info("#	" + field.getName() + ": ");
                    sc = new Scanner(System.in);
                    if (field.getName().equalsIgnoreCase("employee")) {
                        /**
                         * selected Employee
                         */
                        do {
                            selectedEmployee();
                        } while (attempts >= 1 && !valid);
                        continueProcess();
                        workingDay.setData(field.getName(), selectedEmployee);
                        logger.debug("paso 1");
                    } else if (field.getName().equalsIgnoreCase("shift")) {
                        int type = 0;
                        try {
                            logger.info("#	Select documentType: ");
                            logger.info("1) " + Shift.DAY_SHIFT);
                            logger.info("2) " + Shift.NIGHT_SHIFT);
                            logger.info("3) " + Shift.DOMINICAL_DAY_SHIFT);
                            logger.info("4) " + Shift.DOMINICAL_NIGHT_SHIFT);
                            type = sc.nextInt();
                            switch (type) {
                                case 1:
                                    workingDay.setData(field.getName(), Shift.DAY_SHIFT);
                                    break;
                                case 2:
                                    workingDay.setData(field.getName(), Shift.NIGHT_SHIFT);
                                    break;
                                case 3:
                                    workingDay.setData(field.getName(), Shift.DOMINICAL_DAY_SHIFT);
                                    break;
                                case 4:
                                    workingDay.setData(field.getName(), Shift.DOMINICAL_NIGHT_SHIFT);
                                    break;
                                default:
                                    logger.warn("Invalid Option");
                                    break;
                            }
                        } catch (InputMismatchException imx) {
                            logger.error(imx);
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    } else {
                        workingDay.setData(field.getName(), sc.next());
                    }
                }
            }
            if (!workingDayService.add(workingDay)) {
                logger.warn("could not add workingDay with id:" + workingDay.getId());
            } else {
                workingDayService.getWorkingDayById(workingDay.getId()).get().toString();
            }

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

    /**
     * @param data
     * @param value
     * @return
     */
    public static boolean required(String[] data, String value) {
        for (String string : data) {
            if (string.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method to validate if you have available attempts
     */
    public static void continueProcess() {
        /**
         * logger.info("continueProcess, attempts: " + attempts);
         */
        valid = false;
        if (attempts <= 0) {
            logger.warn("Number of attempts exceeded! : status 406");
            System.exit(406);
        } else {
            attempts = 3;
        }
    }

    /**
     * method to select actions by number entered in the terminal
     */
    public static void selectedActionOption(String[] actions) {
        valid = false;
        try {
            sc = new Scanner(System.in);
            for (int i = 1; i < actions.length; i++) {
                logger.info(i + ") " + actions[i].toString());
            }
            if (actions[0].equalsIgnoreCase("options")) {
                selectedOption = sc.nextInt();
                if (selectedOption <= actions.length - 1) {
                    valid = true;
                }
            } else {
                selectedAction = sc.nextInt();
                if (selectedAction <= actions.length - 1) {
                    valid = true;
                }
            }

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

    /**
     * method to select employee by number entered in the terminal
     */
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

    /**
     * method to select factory by number entered in the terminal
     */
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

    /**
     * check the total daily salary per employee
     */
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
        logger.info("Total: $" + employeeService.getTotalDailyWage(selectedEmployee));
        logger.info("-----------------------------------------");
    }

    /**
     * Simulated data initialization method
     *
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
