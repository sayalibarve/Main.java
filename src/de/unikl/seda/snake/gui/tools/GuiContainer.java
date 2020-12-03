package de.unikl.seda.snake.gui.tools;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/** This container class is used to simply show an empty window, which contains the @{@link SnakeGameEnvironment}.*/
public class GuiContainer extends JDialog {
    private static final int windowHeightOffset = 25;
    private static final int windowWidthOffset = 12;

    public GuiContainer(String dialogTitle, SnakeGameEnvironment drawingEnvironment) {
        super(createTaskbarFrame(dialogTitle));

        // add the drawing environment to this dialog/window
        add(drawingEnvironment);

        // Configure the dialog (size, title)
        setSize(drawingEnvironment.getWidth() + windowWidthOffset, drawingEnvironment.getHeight() + windowHeightOffset); // the offsets compensate for the operating-system dependent border size of the dialog
        setTitle(dialogTitle);
        setLocationRelativeTo(null);
        setResizable(false); // dialog window is not resizable

        // Dispose/Remove object once it has been closed.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Calling thread will only continue after this Dialog has been closed.
        setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                JFrame taskbarFrame = (JFrame) getParent();
                taskbarFrame.setVisible(false);
                taskbarFrame.dispose();
                System.exit(0);
            }
        });
    }

    /** @{@link JDialog JDialogs} do support modal displaying, but don't have a taskbar entry.
     * @{@link JFrame JFrames} do not support modal displaying, but do have a taskbar entry.
     * ==> Using a @{@link JFrame} which hosts the @{@link JDialog} in order to have both.*/
    private static JFrame createTaskbarFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    public static void show(String dialogTitle, SnakeGameEnvironment drawingEnvironment){
        GuiContainer guiContainer = new GuiContainer(dialogTitle, drawingEnvironment);
        guiContainer.setVisible(true);
    }
}
