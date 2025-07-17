
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SizeSelectorPanel extends JPanel {
    private JSpinner sizeSpinner;
    private JButton goButton;
    private boolean goState = false;

    public boolean checkPushedGoButton(){
        if(goState) {
            goState = false;
            return true;
        }
        return false;
    }

    public SizeSelectorPanel() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }

    private void initializeComponents() {
        // Create spinner with range 25-1000
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(100, 25, 1000, 1);
        sizeSpinner = new JSpinner(spinnerModel);

        // Create go button
        goButton = new JButton("Go");

        // Set preferred size for spinner
        sizeSpinner.setPreferredSize(new Dimension(80, 25));
    }

    private void setupLayout() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        add(new JLabel("Size:"));
        add(sizeSpinner);
        add(goButton);
    }

    private void setupEventHandlers() {
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedSize = (Integer) sizeSpinner.getValue();
                onGoButtonClicked(selectedSize);
            }
        });
    }

    // Override this method to handle the go button click
    protected void onGoButtonClicked(int size) {
        goState = true;
        System.out.println("Go button clicked with size: " + size);
        // Add your custom logic here
    }

    // Getter methods
    public int getSelectedSize() {
        return (Integer) sizeSpinner.getValue();
    }

    public JButton getGoButton() {
        return goButton;
    }

    public JSpinner getSizeSpinner() {
        return sizeSpinner;
    }

    // Example usage
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Size Selector Demo");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            SizeSelectorPanel panel = new SizeSelectorPanel();
            frame.add(panel);

            frame.setSize(300, 100);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}