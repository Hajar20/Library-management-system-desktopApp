package lesinterfaces;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.logging.*;

import biblio.*;

public class Books extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNum;
	private JTextField txtTitre;
	private JTextField txtGenre;
	private JTextField txtAuteur;
	private JSpinner quantite;
	private JTable listBooks;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Books frame = new Books();
					frame.setVisible(true);
				} catch (Exception e) {
					Logger.getLogger(e.getMessage());
				}
			}
		});
	}
	/**
	 * @param biblio1
	 */
	private Bibliotheque biblio1=new Bibliotheque();
    /**
     * Complete the method
     */
	public void afficher(){
    	List<Livre> books = biblio1.getTousLesLivres();
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(new String []{"ID Livre","Titre","Genre","Auteur","Quantité"});
		for (Livre lv:books){
			model.addRow(new Object[]{lv.getId(),lv.getTitre(),lv.getGenre(),lv.getAuteur(),lv.getQuantite()});
		}
		listBooks.setModel(model);
    }
	/**
	 * 
	 * @return
	 */
    public boolean formValid(){
		final int nbre = Integer.parseInt(quantite.getValue().toString());
    	return (!(txtNum.getText().trim().equals("")) && !(txtTitre.getText().trim().equals("")) && !(txtGenre.getText().trim().equals("")) && !(txtAuteur.getText().trim().equals("")) && nbre > 1 ) ;
    }
    /**
     * 
     * @return
     */
    public boolean cleExiste(){
    	final String req = "select * from livre where idLiv = '"+txtNum.getText()+"'";
    	final ResultSet reslt = Connect.getInstance().lire(req);
    	try {
    		return reslt.next();
    	} catch (SQLException e) {return false;}
    	finally {
    		try {
				reslt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
	/**
	 * Create the frame.
	 */
	public Books() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				afficher();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JLabel label = new JLabel("Gestion des livres");
		label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 22));
		label.setBounds(187, 11, 222, 38);
		contentPane.add(label);
		
		final JLabel label1 = new JLabel("Num\u00E9ro de livre :");
		label1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label1.setBounds(37, 235, 140, 28);
		contentPane.add(label1);
		
		final JLabel label2 = new JLabel("Titre :");
		label2.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label2.setBounds(37, 274, 140, 26);
		contentPane.add(label2);
		
		final JLabel label3 = new JLabel("Genre :");
		label3.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label3.setBounds(37, 311, 140, 25);
		contentPane.add(label3);
		
		final JLabel label4 = new JLabel("Quantit\u00E9 :");
		label4.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label4.setBounds(37, 381, 140, 28);
		contentPane.add(label4);
		
		final JLabel label5 = new JLabel("L'auteur :");
		label5.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label5.setBounds(37, 347, 140, 26);
		contentPane.add(label5);
		
		txtNum = new JTextField();
		txtNum.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		txtNum.setColumns(10);
		txtNum.setBounds(187, 240, 158, 20);
		contentPane.add(txtNum);
		
		txtTitre = new JTextField();
		txtTitre.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		txtTitre.setColumns(10);
		txtTitre.setBounds(187, 278, 158, 20);
		contentPane.add(txtTitre);
		
		txtGenre = new JTextField();
		txtGenre.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		txtGenre.setColumns(10);
		txtGenre.setBounds(187, 314, 158, 20);
		contentPane.add(txtGenre);
		
		txtAuteur = new JTextField();
		txtAuteur.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		txtAuteur.setColumns(10);
		txtAuteur.setBounds(187, 351, 158, 20);
		contentPane.add(txtAuteur);
		
		final JButton btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			/**
			 * add your handling code here:
			 */
			public void actionPerformed(ActionEvent arg0) {
				if (!formValid()){
					JOptionPane.showMessageDialog(null, "Formulaire est invalide!");
				    return ;
				}
				if (cleExiste()){
					JOptionPane.showMessageDialog(null, "Le livre est déjà existe!");
				    return ;
				}
				    int qt = Integer.parseInt(quantite.getValue().toString());
				    if (biblio1.addLivre(Integer.parseInt(txtNum.getText()), txtTitre.getText(), txtGenre.getText(), txtAuteur.getText(), qt))
				    	{JOptionPane.showMessageDialog(null, "Nouveau livre est ajouté");}
				    else
				    	{JOptionPane.showMessageDialog(null, "Erreur d'insertion!!");}
				    afficher();    
			}
		});
		btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnAdd.setBounds(447, 225, 121, 23);
		contentPane.add(btnAdd);
		
		final JButton btnDel = new JButton("Supprimer");
		btnDel.addActionListener(new ActionListener() {
			/**
			 * add your handling code here:
			 */
			public void actionPerformed(ActionEvent arg0) {
				int line = listBooks.getSelectedRow();
				if (line==-1)
				{JOptionPane.showMessageDialog(null, "Aucun ligne sélectionnée!");return;}
				int rep = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ce livre?","Boîte de confirmation",JOptionPane.YES_NO_OPTION);
				
				if (rep == JOptionPane.YES_OPTION){
		        	int ligne = Integer.parseInt(listBooks.getValueAt(line,0).toString());
		        	if (biblio1.delLivre(ligne)){
		        		JOptionPane.showMessageDialog(null, "Le livre '"+listBooks.getValueAt(line, 1)+"' est supprimé.");
		        	}
		        		else
		        		{JOptionPane.showMessageDialog(null, "Erreur de la suppression!!");}
				}
		        	afficher();     
		}});
		btnDel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnDel.setBounds(447, 259, 121, 23);
		contentPane.add(btnDel);
		
		final JButton btnAff = new JButton("Afficher");
		btnAff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					List<Livre> a=biblio1.getLivre(Integer.parseInt(txtNum.getText()));
					for(Livre l:a){
						txtTitre.setText(l.getTitre());
						txtGenre.setText(l.getGenre());
						txtAuteur.setText(l.getAuteur());
						quantite.setValue(l.getQuantite());
					}
			}
		});
		btnAff.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnAff.setBounds(447, 327, 121, 23);
		contentPane.add(btnAff);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!formValid()){
					JOptionPane.showMessageDialog(null, "Formulaire est invalide!!");
					return;
				}
				if (!cleExiste()){
					JOptionPane.showMessageDialog(null, "Le livre que tu veut le modifier n'existe pas!!");
					return;
				}
				if (biblio1.updateLivre(Integer.parseInt(txtNum.getText()), txtTitre.getText(), txtGenre.getText(), txtAuteur.getText(), Integer.parseInt(quantite.getValue().toString())))
				    JOptionPane.showMessageDialog(null, "Mise à jour effectué avec succès.");		
				else
			    	JOptionPane.showMessageDialog(null, "Erreur de mise à jour!!");
		
			    afficher();
			}
		});
		btnUpdate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnUpdate.setBounds(447, 293, 121, 23);
		contentPane.add(btnUpdate);
		
		JLabel label6 = new JLabel("Liste des livres :");
		label6.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		label6.setBounds(37, 50, 140, 28);
		contentPane.add(label6);
		
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int choice;
				choice = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment quitter l'application?","Confirmer",JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnExit.setBounds(447, 361, 121, 23);
		contentPane.add(btnExit);
		
		JButton btnLoc = new JButton("Gestion des locataires");
		btnLoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Locataires().setVisible(true);
				
			}
		});
		btnLoc.setForeground(Color.BLACK);
		btnLoc.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnLoc.setBounds(37, 430, 308, 23);
		contentPane.add(btnLoc);
		
		quantite = new JSpinner();
		quantite.setModel(new SpinnerNumberModel(Integer.valueOf(1), null, null, Integer.valueOf(1)));
		quantite.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		quantite.setBounds(187, 386, 158, 20);
		contentPane.add(quantite);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNum.setText(""); txtTitre.setText("");txtGenre.setText("");txtAuteur.setText("");quantite.setValue(1);
			}
		});
		btnClear.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnClear.setBounds(447, 395, 121, 23);
		contentPane.add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setBounds(36, 76, 532, 138);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 532, 138);
		panel.add(scrollPane);
		
		listBooks = new JTable();
		scrollPane.setViewportView(listBooks);
	}
	public TableModel getListBooksModel() {
		return listBooks.getModel();
	}
	public void setListBooksModel(TableModel model) {
		listBooks.setModel(model);
	}
}
