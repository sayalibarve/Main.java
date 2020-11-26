package de.unikl.seda.snake;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.tools.GuiContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        // Create game instance
        //Scanner s = new Scanner(System.in);
        //System.out.println("Enter PlayerName");
        //String playerName = s.next();
        new SimpleButton();
        //SnakeGameEnvironment game = new SnakeGameEnvironment(playerName, 600, 400, 10);

        //Start game session
        //GuiContainer.show("Snake", game);
        }

    public static class SimpleButton {
        SimpleButton() {
            JFrame f = new JFrame("Button Example");
            //submit button
            JButton b = new JButton("Submit");
            b.setBounds(100, 100, 140, 40);
            //enter name label
            JLabel label = new JLabel();
            label.setText("Enter Name :");
            label.setBounds(10, 10, 100, 100);
            //empty label which will show event after button clicked
            JLabel label1 = new JLabel();
            label1.setBounds(10, 110, 200, 100);
            //textfield to enter name
            JTextField textfield = new JTextField();
            textfield.setBounds(110, 50, 130, 30);
            //add to frame
            f.add(label1);
            f.add(textfield);
            f.add(label);
            f.add(b);
            f.setSize(300, 300);
            f.setLayout(null);
            f.setVisible(true);
            f.setLocationRelativeTo(null);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //action listener
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    label1.setText("Name has been submitted.");
                    String playerName = textfield.getText();
                    SnakeGameEnvironment game = new SnakeGameEnvironment(playerName, 400, 600, 10);
                    f.setVisible(false);
                    //Start game session
                    GuiContainer.show("Snake", game);
                }
            });
        }
    }
}


