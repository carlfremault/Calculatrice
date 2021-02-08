package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.Control;
import control.Global;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Vue de l'application
 * 
 * @author Carl Fremault
 *
 */
public class FrmCalculatrice extends JFrame implements Global {

	private JPanel contentPane;

	private Control control;

	private JLabel lblScreenUpper;
	private JLabel lblScreenLower;

	private void buttonPressed(MouseEvent e) {
		control.evenementFrmCalculatrice(e);
	}

	/**
	 * Méthode qui gère l'affichage sur l'écran.
	 * 
	 * @param string = Information à afficher
	 * @param position = position où afficher (UPPER ou LOWER en fonction)
	 */
	public void setLabelText(String string, String position) {
		switch (position) {
		case UPPER:
			this.lblScreenUpper.setText(string);
			break;
		case LOWER:
			this.lblScreenLower.setText(string);
			break;
		}
	}

	/**
	 * Constructeur de l'interface utilisateur
	 * @param control = le contrôleur associé
	 */
	public FrmCalculatrice(Control control) {

		this.control = control;

		setResizable(false);
		setTitle("Calculatrice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 345, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// Screen

		lblScreenUpper = new JLabel("");
		lblScreenUpper.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScreenUpper.setOpaque(true);
		lblScreenUpper.setBackground(Color.WHITE);
		lblScreenUpper.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblScreenUpper.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScreenUpper.setBounds(10, 10, 310, 35);
		contentPane.add(lblScreenUpper);

		lblScreenLower = new JLabel(ZERO);
		lblScreenLower.setHorizontalAlignment(SwingConstants.RIGHT);
		lblScreenLower.setOpaque(true);
		lblScreenLower.setBackground(Color.WHITE);
		lblScreenLower.setBorder(new EmptyBorder(10, 10, 10, 10));
		lblScreenLower.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScreenLower.setBounds(10, 45, 310, 35);
		contentPane.add(lblScreenLower);

		// Buttons

		JButton btnCE = new JButton(CE);
		btnCE.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnCE.setBackground(Color.LIGHT_GRAY);
		btnCE.setBounds(10, 90, 70, 35);
		contentPane.add(btnCE);

		JButton btn7 = new JButton(SEVEN);
		btn7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn7.setBackground(Color.LIGHT_GRAY);
		btn7.setBounds(10, 130, 70, 35);
		contentPane.add(btn7);

		JButton btn4 = new JButton(FOUR);
		btn4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn4.setBackground(Color.LIGHT_GRAY);
		btn4.setBounds(10, 170, 70, 35);
		contentPane.add(btn4);

		JButton btn1 = new JButton(ONE);
		btn1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn1.setBackground(Color.LIGHT_GRAY);
		btn1.setBounds(10, 210, 70, 35);
		contentPane.add(btn1);

		JButton btnPlusMinus = new JButton(PLUSMINUS);
		btnPlusMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnPlusMinus.setBackground(Color.LIGHT_GRAY);
		btnPlusMinus.setBounds(10, 250, 70, 35);
		contentPane.add(btnPlusMinus);

		JButton btnC = new JButton(C);
		btnC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnC.setBackground(Color.LIGHT_GRAY);
		btnC.setBounds(90, 90, 70, 35);
		contentPane.add(btnC);

		JButton btn8 = new JButton(EIGHT);
		btn8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn8.setBackground(Color.LIGHT_GRAY);
		btn8.setBounds(90, 130, 70, 35);
		contentPane.add(btn8);

		JButton btn5 = new JButton(FIVE);
		btn5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn5.setBackground(Color.LIGHT_GRAY);
		btn5.setBounds(90, 170, 70, 35);
		contentPane.add(btn5);

		JButton btn2 = new JButton(TWO);
		btn2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn2.setBackground(Color.LIGHT_GRAY);
		btn2.setBounds(90, 210, 70, 35);
		contentPane.add(btn2);

		JButton btn0 = new JButton(ZERO);
		btn0.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn0.setBackground(Color.LIGHT_GRAY);
		btn0.setBounds(90, 250, 70, 35);
		contentPane.add(btn0);

		JButton btnBackspace = new JButton(BACKSPACE);
		btnBackspace.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnBackspace.setBackground(Color.LIGHT_GRAY);
		btnBackspace.setBounds(170, 90, 70, 35);
		contentPane.add(btnBackspace);

		JButton btn9 = new JButton(NINE);
		btn9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn9.setBackground(Color.LIGHT_GRAY);
		btn9.setBounds(170, 130, 70, 35);
		contentPane.add(btn9);

		JButton btn6 = new JButton(SIX);
		btn6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn6.setBackground(Color.LIGHT_GRAY);
		btn6.setBounds(170, 170, 70, 35);
		contentPane.add(btn6);

		JButton btn3 = new JButton(THREE);
		btn3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btn3.setBackground(Color.LIGHT_GRAY);
		btn3.setBounds(170, 210, 70, 35);
		contentPane.add(btn3);

		JButton btnComma = new JButton(COMMA);
		btnComma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnComma.setBackground(Color.LIGHT_GRAY);
		btnComma.setBounds(170, 250, 70, 35);
		contentPane.add(btnComma);

		JButton btnDivide = new JButton(DIVIDE);
		btnDivide.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnDivide.setBackground(Color.LIGHT_GRAY);
		btnDivide.setBounds(250, 90, 70, 35);
		contentPane.add(btnDivide);

		JButton btnMultiply = new JButton(MULTIPLY);
		btnMultiply.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnMultiply.setBackground(Color.LIGHT_GRAY);
		btnMultiply.setBounds(250, 130, 70, 35);
		contentPane.add(btnMultiply);

		JButton btnMinus = new JButton(MINUS);
		btnMinus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnMinus.setBackground(Color.LIGHT_GRAY);
		btnMinus.setBounds(250, 170, 70, 35);
		contentPane.add(btnMinus);

		JButton btnPlus = new JButton(PLUS);
		btnPlus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnPlus.setBackground(Color.LIGHT_GRAY);
		btnPlus.setBounds(250, 210, 70, 35);
		contentPane.add(btnPlus);

		JButton btnEquals = new JButton(EQUALS);
		btnEquals.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buttonPressed(e);
			}
		});
		btnEquals.setBackground(Color.LIGHT_GRAY);
		btnEquals.setBounds(250, 250, 70, 35);
		contentPane.add(btnEquals);

	}
}