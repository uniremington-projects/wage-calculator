package co.edu.miremington.programming;

import org.apache.log4j.Logger;


/**
 * @author devnix
 * @apiNote App wage calculator!
 * @version 1.0.0
 *
 */
public class App {
    static final Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {

        logger.info("welcome!");
        try {
            Process process = new Process(false);
            process.start();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

}
