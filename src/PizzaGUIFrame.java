import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.swing.SwingConstants.NORTH;

public class PizzaGUIFrame extends JFrame
{
    JPanel titlePanel, crustPanel, sizePanel, toppingsPanel, mainPanel, commandPanel, orderPanel, recieptPanel;
    JLabel titleLabel, crustLabel, toppingsLabel, sizeLabel;
    JButton quitButton, orderButton, clearButton;
    JRadioButton thinButton, regularButton, deepDishButton;
    JTextArea recieptArea;
    JCheckBox topping1, topping2, topping3, topping4, topping5, topping6;
    JComboBox<String> pizzaSize = new JComboBox<>();
    ButtonGroup crust = new ButtonGroup();

    static final double TAX = 0.07;
    public PizzaGUIFrame()
    {
        setTitle("Pick Your Pizza");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        createTitlePanel();
        createOrderPanel();
        createCommandPanel();

        setVisible(true);
    }

    public void createTitlePanel()
    {
        titlePanel = new JPanel();
        titleLabel = new JLabel("Pick Your Pizza Order Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans Ms", Font.BOLD, 30));

        titlePanel.add(titleLabel);

        mainPanel.add(titlePanel, BorderLayout.PAGE_START);
    }

    public void createOrderPanel()
    {
        crustPanel = new JPanel();
        sizePanel = new JPanel();
        toppingsPanel = new JPanel();

        crustLabel = new JLabel("Crust: ");
        crustLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        thinButton = new JRadioButton("Thin");
        regularButton = new JRadioButton("Regular");
        deepDishButton = new JRadioButton("Deep Dish");
        crust = new ButtonGroup();
        crust.add(thinButton);
        crust.add(regularButton);
        crust.add(deepDishButton);

        sizeLabel = new JLabel("Pizza Size: ");
        String[] size = {"Small", "Medium", "Large", "Super"};
        pizzaSize = new JComboBox<String>(size);
        pizzaSize.setSelectedIndex(0);

        toppingsLabel = new JLabel("Toppings: \n");
        toppingsLabel.setFont(new Font("Comic Sans Ms", Font.PLAIN, 15));
        topping1 = new JCheckBox("Toad Tidbits");
        topping2 = new JCheckBox("Brittle Bones");
        topping3 = new JCheckBox("Witchy's Mystery Sauce");
        topping4 = new JCheckBox("Glue");
        topping5 = new JCheckBox("Fresh Rotten Cabbage");
        topping6 = new JCheckBox("Cavern Crumbles");
        toppingsPanel.setLayout(new GridLayout(2, 3));

        recieptArea = new JTextArea(20, 30);

        recieptPanel = new JPanel();
        orderPanel = new JPanel();

        crustPanel.add(crustLabel);
        crustPanel.add(thinButton);
        crustPanel.add(regularButton);
        crustPanel.add(deepDishButton);

        sizePanel.add(sizeLabel);
        sizePanel.add(pizzaSize);

        toppingsPanel.add(toppingsLabel);
        toppingsPanel.add(topping1);
        toppingsPanel.add(topping2);
        toppingsPanel.add(topping3);
        toppingsPanel.add(topping4);
        toppingsPanel.add(topping5);
        toppingsPanel.add(topping6);

        orderPanel.setLayout(new GridLayout(3, 1));

        orderPanel.add(crustPanel);
        orderPanel.add(sizePanel);
        orderPanel.add(toppingsPanel);

        recieptPanel.add(recieptArea);

        mainPanel.add(orderPanel, BorderLayout.EAST);
        mainPanel.add(recieptPanel, BorderLayout.WEST);
    }

    public void createCommandPanel()
    {
        commandPanel = new JPanel();
        commandPanel.setLayout(new GridLayout(1, 3));

        orderButton= new JButton("Order");
        clearButton = new JButton("Clear");
        quitButton = new JButton("Quit");
        orderButton.addActionListener((ActionEvent ae) ->
        {
            double total = 0;
            recieptArea.append("Pizza Order");
            recieptArea.append("\n");
            recieptArea.append("========================================");
            if (thinButton.isSelected())
            {
                recieptArea.append("\n Thin Crust                         $1.00");
                total += 1;
            }
            else if (regularButton.isSelected())
            {
                recieptArea.append("\n Regular Crust                         $2.00");
                total += 2;
            }
            else if (deepDishButton.isSelected())
            {
                recieptArea.append("\n Deep Dish Crust              $4.00");
                total += 4;
            }

            switch(pizzaSize.getSelectedIndex())
            {
                case 0:
                    recieptArea.append("\n Small                         $8.00");
                    total += 8;
                    break;
                case 1:
                    recieptArea.append("\n Regular                         $12.00");
                    total += 12;
                    break;
                case 2:
                    recieptArea.append("\n Large                         $16.00");
                    total += 16;
                    break;
                case 3:
                    recieptArea.append("\n Super                               $20.00");
                    total += 20;
                    break;
            }

            if (topping1.isSelected())
            {
                recieptArea.append("\n Toad Tidbits                      $1.00");
                total += 1;
            }
            if (topping2.isSelected())
            {
                recieptArea.append("\n Brittle Bones                     $1.00");
                total += 1;
            }
            if (topping3.isSelected())
            {
                recieptArea.append("\n Witchy's Mystery Sauce            $1.00");
                total += 1;
            }
            if (topping4.isSelected())
            {
                recieptArea.append("\n Glue                              $1.00");
                total += 1;
            }
            if (topping5.isSelected())
            {
                recieptArea.append("\n Fresh Rotten Cabbage              $1.00");
                total += 1;
            }
            if (topping6.isSelected())
            {
                recieptArea.append("\n Cavern Crumbles                   $1.00");
                total += 1;
            }

            recieptArea.append("\n");
            recieptArea.append("\n");
            recieptArea.append("Sub-total: $" + total);
            double taxCalc = total * TAX;
            double finalTotal = total + taxCalc;
            recieptArea.append("\n");
            recieptArea.append("Tax: $" + taxCalc);
            recieptArea.append("\n");
            recieptArea.append("***************************************");
            recieptArea.append("\n");
            recieptArea.append("Total: $" + finalTotal);
            recieptArea.append("\n");
            recieptArea.append("========================================");
        });

        clearButton.addActionListener((ActionEvent ae) ->
        {
                crust.clearSelection();
                pizzaSize.setSelectedIndex(0);
                topping1.setSelected(false);
                topping2.setSelected(false);
                topping3.setSelected(false);
                topping4.setSelected(false);
                topping5.setSelected(false);
                topping6.setSelected(false);
        });

        quitButton.addActionListener((ActionEvent ae) -> System.exit(0));

        commandPanel.add(orderButton);
        commandPanel.add(clearButton);
        commandPanel.add(quitButton);

        mainPanel.add(commandPanel, BorderLayout.PAGE_END);
    }
}