package co.edu.miremington.programming;

import co.edu.miremington.programming.forms.SalaryValidation;
import org.apache.log4j.Logger;

import javax.swing.*;


/**
 * App wage calculator!
 *
 * @author devnix
 * @version 1.0.0
 *
 */
public class App {
    static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("welcome!");
        try {
            /**
             * Exercise 1
             *
             * Process process = new Process(false);
             * process.start();
             */

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SalaryValidation salaryValidation = new SalaryValidation();
                    salaryValidation.setVisible(true);
                }
            });

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

}
