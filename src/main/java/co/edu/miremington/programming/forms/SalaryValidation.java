package co.edu.miremington.programming.forms;

import com.sun.corba.se.impl.orbutil.ObjectStreamClassUtil_1_3;
import org.apache.log4j.Logger;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;

public class SalaryValidation extends JFrame {
    static final Logger logger = Logger.getLogger(SalaryValidation.class);
    private JTabbedPane tabbedPane1;
    private JTextField tfFullName;
    private JTextField tfHoursLab;
    private JTextField tfValueHours;
    private JButton verificarButton;
    private JPanel rootPanel;
    private JLabel lbStandardTime;
    private JLabel lbOvertime;
    private JLabel lbTotalTime;
    private JLabel lbStandardValue;
    private JLabel lbExtraValue;
    private JLabel lbTotalValue;
    private JLabel lbNetSalary;
    private JLabel lbHold;
    private JLabel lbTotal;
    private JLabel lbEmployee;
    private JTabbedPane tpOutput;
    private JButton btClean;

    public SalaryValidation() throws HeadlessException {
        add(rootPanel);
        setTitle("Validar Salario");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        tpOutput.setVisible(false);
        tfFullName.requestFocus();
        verificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate(tfFullName.getText(), tfHoursLab.getText(), tfValueHours.getText())) {
                    validateStandardTime();
                }
            }
        });
        btClean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfHoursLab.setText("");
                tfFullName.setText("");
                tfValueHours.setText("");
                tpOutput.setVisible(false);
                tfFullName.requestFocus();
            }
        });
    }

    public boolean validate(String... params) {
        for (int i = 0; i <= params.length - 1; i++) {
            if (params[i].toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Algunos campos estan vacios");
                return false;
            }
        }
        return true;
    }

    public void validateStandardTime() {
        try {
            lbTotalTime.setText(tfHoursLab.getText());
            int valueHours = Integer.parseInt(lbTotalTime.getText());
            if (Integer.parseInt(tfHoursLab.getText()) >= 40) {
                lbStandardTime.setText("40");
                int valueOverTime = valueHours - 40;
                lbOvertime.setText(String.valueOf(valueOverTime));
            } else {
                lbStandardTime.setText(String.valueOf(valueHours));
                lbOvertime.setText("0.0");
            }
            calculateGrossSalary();
        } catch (NumberFormatException nf) {
            logger.error(nf);
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    public void calculateGrossSalary() {
        /**
         * Se paga el valor normal
         */
        lbStandardValue.setText(String.valueOf(
                Double.parseDouble(lbStandardTime.getText()) * Double.parseDouble(tfValueHours.getText())
        ));

        /**
         * Se paga un 35% más sobre el valor de la hora
         */
        double newValue = Double.parseDouble(tfValueHours.getText()) * 1.35;
        lbExtraValue.setText(String.valueOf(
                Double.parseDouble(lbOvertime.getText()) * newValue
        ));

        /**
         * total valor bruto, suma de stardardValue y extraValue
         */
        lbTotalValue.setText(String.valueOf(
                (Double.parseDouble(lbExtraValue.getText()) + Double.parseDouble(lbStandardValue.getText()))
        ));
        validateHold();
    }

    public void validateHold () {
        /**
         * total valor bruto, suma de stardardValue y extraValue
         */
        double newValue = Double.parseDouble(lbTotalValue.getText()), holdValue = .0;
        lbNetSalary.setText(lbTotalValue.getText());
        logger.info("newValue: " + newValue);
        if (newValue <= 1200000.0) {
            holdValue = .10;
            logger.info(.10 * 100 + "%");
        } else if (newValue <= 2000000.0) {
            holdValue = .20;
            logger.info(.20 * 100 + "%");
        } else if (newValue > 2000000.0) {
            holdValue = .25;
            logger.info(.25 * 100 + "%");
        } else {
            logger.info(holdValue * 100 + "%");
        }
        lbHold.setText(String.valueOf(
               holdValue * 100 + "%"
        ));

        newValue = newValue - (newValue * holdValue);
        lbTotal.setText(
                 "Total: $" + newValue + ""
        );

        lbEmployee.setText(tfFullName.getText());
        tpOutput.setVisible(true);
    }


}
