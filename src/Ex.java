import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum FuncType{
    sh_x,
    x_2,
    e_x
}
public class Ex {
    private JButton calculateButton;
    private JTextField XTxtF1;
    private JRadioButton ShRb;
    private JRadioButton X2Rb;
    private JRadioButton ExRb;
    private JPanel root;
    private JTextField YTxtF1;
    private JLabel ans;

    private FuncType type = FuncType.sh_x;


    protected double calculate_fx(double x){
        /*
        в зависимости от типа функции, считает ее
         */
        if (type == FuncType.sh_x){
            return Math.sinh(x);
        }
        if (type == FuncType.x_2){
            return x * x;
        }
        return Math.pow(Math.E, x);

    }

    public Ex(){
        root.setMinimumSize(new Dimension(410, 200));
        ShRb.setSelected(true);
        calculateButton.addActionListener(e -> {
            double x_1 = Double.parseDouble(XTxtF1.getText()); // 1
            double p_1 = Double.parseDouble(YTxtF1.getText()); // 4
            double mp = Math.abs(p_1);  // 4
            double res;
            if (x_1 > mp) {  // 1 > 4
                res = 2 * Math.pow(calculate_fx(x_1), 3) + 3 * Math.pow(p_1, 2);
            } else if (3 < x_1) { // 3 < 1
                res = Math.abs(calculate_fx(x_1) - p_1);

            }
            else if (x_1 == mp){ // 1 == 4
                res = Math.pow(calculate_fx(x_1) - p_1, 2);
            }
            else {
                ans.setText("Cant calc");
                return;
            }

            ans.setText(String.valueOf(res));


        });
        ShRb.addActionListener(e -> {
            type = FuncType.sh_x;
            ExRb.setSelected(false);
            X2Rb.setSelected(false);
        });
        X2Rb.addActionListener(e -> {
            type = FuncType.x_2;
            ExRb.setSelected(false);
            ShRb.setSelected(false);
        });
        ExRb.addActionListener(e -> {
            type = FuncType.e_x;
            ShRb.setSelected(false);
            X2Rb.setSelected(false);
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ex");
        frame.setMinimumSize(new Dimension(400, 200));
        frame.setContentPane(new Ex().root);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
