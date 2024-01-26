package Telas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import AdicionandoBD.AddTarefaBD;

public class TelaAdicionar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitulo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionar frame = new TelaAdicionar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAdicionar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 240);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// area para titulo da tarefa
		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTitulo.setBounds(10, 24, 65, 20);
		contentPane.add(lblTitulo);

		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(10, 55, 178, 20);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);

		// area para data da tarefa
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblData.setBounds(246, 24, 65, 20);
		contentPane.add(lblData);

		JDateChooser boxData = new JDateChooser();
		boxData.setBounds(246, 55, 178, 20);
		contentPane.add(boxData);

		// area para descriação da tarefa
		JLabel lblNewLabel = new JLabel("Descrição");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(10, 86, 100, 20);
		contentPane.add(lblNewLabel);

		JTextArea textAreaDesc = new JTextArea(5, 15);
		textAreaDesc.setLineWrap(true);
		textAreaDesc.setWrapStyleWord(true);
		textAreaDesc.setBounds(10, 117, 178, 73);

		JScrollPane scrollPane = new JScrollPane(textAreaDesc);
		scrollPane.setBounds(10, 117, 178, 73);
		contentPane.add(scrollPane);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String tituloTarefa = textFieldTitulo.getText();
				String descricaoTarefa = textAreaDesc.getText();

				// conversão data para formato yyyy/MM/dd
				Date dataSelecionada = boxData.getDate();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String dataFormatada = sdf.format(dataSelecionada);

				System.out.println(dataFormatada);

				AddTarefaBD addTarefaBD = new AddTarefaBD();

				// Chame o método adicionarTarefa passando os parâmetros necessários
				addTarefaBD.adicionarTarefa(tituloTarefa, dataFormatada, descricaoTarefa);

			}
		});
		btnSalvar.setBounds(335, 167, 89, 23);
		contentPane.add(btnSalvar);

	}
}
