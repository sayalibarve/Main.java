package de.unikl.seda.snake;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;
import de.unikl.seda.snake.gui.tools.GuiContainer;

import javax.swing.*;

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
            b.setBounds(80, 180, 140, 40);
            //enter name label
            JLabel label = new JLabel();
            label.setText("Enter Name:");
            label.setBounds(50, 10, 100, 100);
            //textField to enter name
            JTextField textField = new JTextField();
            textField.setBounds(150, 50, 130, 30);
            JLabel l = new JLabel();
            l.setText("Enter Square Size:");
            l.setBounds(10, 90, 150, 100);
            JTextField squareSize = new JTextField();
            squareSize.setBounds(150, 130, 130, 30);
            //add to frame
            f.add(squareSize);
            f.add(l);
            f.add(textField);
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
                    String playerName = textField.getText();
                    String size = squareSize.getText();
                    int squareSize = Integer.parseInt(size);

                    SnakeGameEnvironment game = new SnakeGameEnvironment(playerName, 400, 600,squareSize);
                    f.setVisible(false);
                    //Start game session
                    GuiContainer.show("Snake", game);
                }
            });
        }
    }
}


