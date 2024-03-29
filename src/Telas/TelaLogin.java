package Telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import AdicionandoBD.AddUsuarioBD;
import ValidacaoBD.ValidacaoUser;

public class TelaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldNum;
	private static TelaLogin frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 450);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(145, 96, 58, 25);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNome);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(145, 132, 310, 33);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		JLabel lblNumero = new JLabel("Número");
		lblNumero.setBounds(145, 176, 87, 25);
		lblNumero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNumero);

		textFieldNum = new JTextField();
		textFieldNum.setBounds(145, 212, 310, 33);
		contentPane.add(textFieldNum);
		textFieldNum.setColumns(10);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String txtNumero = textFieldNum.getText();
		        String nome = textFieldNome.getText();
		        //verifica os campos se estão vazios
		        if (nome.isEmpty() || txtNumero.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Preencha todos os campos e tente novamente!", "Erro", JOptionPane.ERROR_MESSAGE);
		        } else {
		            try {
		            	//validação de usuário 
		                Long numero = Long.parseLong(txtNumero);
		                ValidacaoUser validacaoUser = new ValidacaoUser();
		                
		                if (validacaoUser.Validacao(nome, numero)) {
		                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso!", "Operação bem-sucedida", JOptionPane.INFORMATION_MESSAGE);
		                    frame.setVisible(false);
		                    TelaInicial telaInicial = new TelaInicial();
		                    telaInicial.setVisible(true);
		                } else {
		                    JOptionPane.showMessageDialog(null, "Login inválido, tente novamente!", "Campos Inválidos", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Número inválido! Digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    }
		});
		btnEntrar.setBounds(199, 283, 95, 30);
		contentPane.add(btnEntrar);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txtNumero = textFieldNum.getText();

				if (txtNumero.matches("\\d+")) {
					Long numero = Long.parseLong(txtNumero);

					String nome = textFieldNome.getText();

					AddUsuarioBD addUsuarioBD = new AddUsuarioBD();

					// Chame o método adicionarTarefa passando os parâmetros necessários
					addUsuarioBD.adcNumAndUser(nome, numero);
				} else {
					System.out.println("Erro: O número deve conter apenas dígitos.");
					// Lógica adicional para lidar com a entrada inválida
				}
			}
		});
		btnRegistrar.setBounds(299, 283, 95, 30);
		contentPane.add(btnRegistrar);		
	}
}