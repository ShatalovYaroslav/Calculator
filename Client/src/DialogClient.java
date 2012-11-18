import calculator.EvaluationException;
import calculator.FiniteStateMachineCalculator;
import calculator.function.FunctionFactory;
import calculator.operator.BinaryOperatorFactory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Set;

public class DialogClient extends JFrame {


    private final FiniteStateMachineCalculator calculator = new FiniteStateMachineCalculator();

    private final JLabel resultNameLabel = new JLabel("Result:  ");
    private final JLabel resultingLabel = new JLabel();
    private final JTextField textField = new JTextField();

    private final JButton clearButton = new JButton("Clear");
    private final JButton calculateButton = new JButton("Calculate");

    public DialogClient() {
        super("Calculator");
        createGUI();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 350, 170);
        setVisible(true);
        setMinimumSize(new Dimension(350, 150));
    }

    public void createGUI() {

        final JPanel contentPanel = new JPanel();
        final JPanel resultPanel = new JPanel();
        final JPanel buttonPanel = new JPanel();

        final JMenuBar menuBar = new JMenuBar();
        final JMenu helpMenu = new JMenu("Help");

        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        resultPanel.add(resultNameLabel);
        resultPanel.add(resultingLabel);
        buttonPanel.add(clearButton);
        buttonPanel.add(calculateButton);

        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.add(textField, BorderLayout.NORTH);
        contentPanel.add(resultPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPanel);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                resultingLabel.setText("");
            }
        });

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!perform()) {
                    throw new IllegalStateException(
                            "The calculation hasn't done correctly");
                }
            }
        });

        final JFrame helpFrame = new JFrame();
        final Container content = helpFrame.getContentPane();
        final JTextArea textArea = new JTextArea();
        textArea.append(createHelpText());
        content.add(textArea);
        helpFrame.pack();

        helpMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                helpFrame.setVisible(true);
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });
    }

    public boolean perform() {

        try {
            final BigDecimal result = calculator.evaluate(textField.getText());
            resultingLabel.setText(result.toPlainString());
            return true;
        } catch (EvaluationException e) {
            resultingLabel.setText("Error: "
                    + e.getMessage() + ", position " + e.getErrorPosition());
            textField.setCaretPosition(e.getErrorPosition());
            textField.getCaret().setVisible(true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String createHelpText() {
        String helpText = "";
        FunctionFactory funcFactory = new FunctionFactory();
        Set<String> functions = funcFactory.getFunctionRepresentations();

        BinaryOperatorFactory operFactory = new BinaryOperatorFactory();
        Set<String> operators = operFactory.getOperatorRepresentations();

        helpText += "Calculator provide functions: \n";
        for (String func : functions) {
            helpText += "\t" + func + "\n";
        }

        helpText += "Calculator provide operators: \n";
        for (String operator : operators) {
            helpText += "\t" + operator + "\n";
        }

        return helpText;
    }
}



