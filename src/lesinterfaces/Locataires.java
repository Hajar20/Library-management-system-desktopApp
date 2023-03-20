package lesinterfaces;


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import biblio.Bibliotheque;
import biblio.Connect;
import biblio.Locataire;

public class Locataires extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtNom;
	private JTable listLoc;
	private JComboBox<Object> cbLivre;
	private JComboBox<String> cbLevel;
	private JComboBox<Object> cbFiliere;

	/**
	 * Launch the application.
	 */
	private Bibliotheque bbl=new Bibliotheque();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Locataires frame = new Locataires();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}
	
	 public void afficher(){
		    List<Locataire> lc=bbl.getTousLesLocataire();
			DefaultTableModel m = new DefaultTableModel();
			m.setColumnIdentifiers(new String []{"ID Locataire","Le nom complet","Niveau","Filière","Livre loué"});
			for (Locataire l:lc)
				m.addRow(new Object[]{l.getId(),l.getNomPrenom(),l.getNiveau(),l.getFiliere(),l.getLivre()});
			listLoc.setModel(m);
	    }
	    public boolean formValid(){
	    	return ( !(txtID.getText().trim().equals("")) && !(txtNom.getText().trim().equals("")) && cbLivre.getSelectedIndex() != -1 && cbLevel.getSelectedIndex()!=-1 && cbFiliere.getSelectedIndex()!=-1 ); 
	    	
	    }
	    public boolean cleExiste(){
	    	String req = "select * from locataire where idLoc = '"+txtID.getText()+"'";
	    	ResultSet rs = Connect.getInstance().lire(req);
	    	try {
	    		return rs.next();
	    	} catch (SQLException e) {return false;}
	     }
	    private ArrayList<Integer> id = new ArrayList<>();
	    public void comboLivre(){
	    	ResultSet r = Connect.getInstance().lire("select * from livre");
	    	cbLivre.removeAllItems();
	    	id.clear();
	    	try {
	    		while (r.next()){
	    			cbLivre.addItem(r.getString(2));
	    			id.add(r.getInt(1));
	    		}
	    	} catch (SQLException e){ JOptionPane.showMessageDialog(null,e.getMessage()); }	
	    	cbLivre.setSelectedIndex(-1);
	    }
	    public void comboLevel(){
	    	cbLevel.removeAllItems(); 
	    	cbLevel.addItem("1ère année");
	    	cbLevel.addItem("2ème année");
	    	cbLevel.setSelectedIndex(-1);
	    }
	    public void comboFiliere(){
	    	cbFiliere.removeAllItems();
	    	cbFiliere.addItem("DSI");
	    	cbFiliere.addItem("CPI");
	    	cbFiliere.addItem("SE");
	    	cbFiliere.addItem("MI");
	    	cbFiliere.addItem("PME/PMI");
	    	cbFiliere.setSelectedIndex(-1);
	    }
	/**
	 * Create the frame.
	 */
	public Locataires() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				afficher();
				comboLivre();
				comboLevel();
				comboFiliere();				
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Gestion des locataires");
		label.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 21));
		label.setBounds(199, 11, 279, 37);
		contentPane.add(label);
		
		JLabel label1 = new JLabel("ID Locataire :");
		label1.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label1.setBounds(35, 254, 124, 14);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Le nom complet :");
		label2.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label2.setBounds(35, 290, 144, 14);
		contentPane.add(label2);
		
		JLabel label3 = new JLabel("Livre :");
		label3.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label3.setBounds(35, 329, 124, 14);
		contentPane.add(label3);
		
		JLabel label4 = new JLabel("Niveau :");
		label4.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label4.setBounds(35, 369, 124, 14);
		contentPane.add(label4);
		
		JLabel label5 = new JLabel("Fili\u00E8re :");
		label5.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		label5.setBounds(35, 406, 124, 14);
		contentPane.add(label5);
		
		txtID = new JTextField();
		txtID.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		txtID.setColumns(10);
		txtID.setBounds(189, 252, 171, 20);
		contentPane.add(txtID);
		
		txtNom = new JTextField();
		txtNom.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		txtNom.setColumns(10);
		txtNom.setBounds(189, 288, 171, 20);
		contentPane.add(txtNom);
		
		cbLivre = new JComboBox<>();
		cbLivre.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		cbLivre.setBounds(189, 327, 171, 20);
		contentPane.add(cbLivre);
		
		cbLevel = new JComboBox<>();
		cbLevel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		cbLevel.setBounds(189, 367, 171, 20);
		contentPane.add(cbLevel);
		
		cbFiliere = new JComboBox<>();
		cbFiliere.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		cbFiliere.setBounds(189, 404, 171, 20);
		contentPane.add(cbFiliere);
		
		JButton btnDel = new JButton("Supprimer");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n = listLoc.getSelectedRow();
				if (n == -1) 
					{JOptionPane.showMessageDialog(null, "Aucun ligne sélectionné"); return;}
				int rep = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ce livre?","Boîte de confirmation",JOptionPane.YES_NO_OPTION);
  
				if (rep == JOptionPane.YES_OPTION){

				int l = Integer.parseInt(listLoc.getValueAt(n,0).toString());
				
				if (!bbl.delLocataire(l))
					JOptionPane.showMessageDialog(null, "Erreur de la suppression!!");
				else
					JOptionPane.showMessageDialog(null, "Locataire '"+listLoc.getValueAt(n, 1)+"' est supprimé");
				}
				afficher();
			}
		});
		btnDel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnDel.setBounds(500, 277, 123, 23);
		contentPane.add(btnDel);
		
		JButton btnAff = new JButton("Afficher");
		btnAff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Locataire> l= bbl.getLocataire(Integer.parseInt(txtID.getText()));
			      for (Locataire lc:l){
					txtNom.setText(lc.getNomPrenom());
					cbLevel.setSelectedItem(lc.getNiveau());
					cbFiliere.setSelectedItem(lc.getFiliere());
					cbLivre.setSelectedItem(lc.getLivre());
			      }
			}
		});
		btnAff.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnAff.setBounds(500, 311, 123, 23);
		contentPane.add(btnAff);
		
		JButton btnUpdate = new JButton("Modifier");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!formValid ()){
					JOptionPane.showMessageDialog(null, "Formulaire est invalide!!");
					return;
				}
				if (!cleExiste()){
					JOptionPane.showMessageDialog(null, "Locataire que tu veut le modifier n'existe pas!!");
					return;
				}
				if (!bbl.updateLocataire(Integer.parseInt(txtID.getText()), txtNom.getText(), cbLevel.getSelectedItem().toString(), cbFiliere.getSelectedItem().toString(), id.get(cbLivre.getSelectedIndex())))
					JOptionPane.showMessageDialog(null, "Erreur de mise à jour!!");
				else
					JOptionPane.showMessageDialog(null, "Mise à jour effectué avec succès.");
				
			    afficher();
			}
		});
		btnUpdate.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnUpdate.setBounds(500, 345, 123, 23);
		contentPane.add(btnUpdate);
		
		JPanel panel = new JPanel();
		panel.setBounds(35, 109, 588, 120);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 588, 120);
		panel.add(scrollPane);
		
		listLoc = new JTable();
		scrollPane.setViewportView(listLoc);
		
		JLabel label6 = new JLabel("Liste des locataires:");
		label6.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 14));
		label6.setBounds(35, 73, 178, 28);
		contentPane.add(label6);
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rep = JOptionPane.showConfirmDialog(null, "Voulez vous quitter l'application?","Boîte de confirmation.",JOptionPane.YES_NO_OPTION);
				if (rep == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnExit.setBounds(500, 379, 123, 23);
		contentPane.add(btnExit);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 	if (!formValid()) {
			 		JOptionPane.showMessageDialog(null, "Formulaire est invalide!!");return;
			 	}
			 	if (cleExiste()){
			 		JOptionPane.showMessageDialog(null, "Locataire déjà existe!");return;
			 	}
			 	
			 	if (!bbl.laDesponibiliteDeLivre(id.get(cbLivre.getSelectedIndex())))
			 	{ JOptionPane.showMessageDialog(null, "Ce livre n'est pas disponible!");return; }
			 	 
			 	
			 	if (bbl.addLocataire(Integer.parseInt(txtID.getText()), txtNom.getText(), cbLevel.getSelectedItem().toString(), cbFiliere.getSelectedItem().toString(), id.get(cbLivre.getSelectedIndex())))
			 		JOptionPane.showMessageDialog(null, "Nouveau locataire est ajouté");
			 	else
			 		JOptionPane.showMessageDialog(null, "Erreur de l'insertion!!");
			   
			    afficher();
			}
		});
		btnAdd.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnAdd.setBounds(500, 242, 123, 23);
		contentPane.add(btnAdd);
		
		JButton btnClear = new JButton("Effacer");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID.setText(""); txtNom.setText("");cbLivre.setSelectedIndex(-1);cbLevel.setSelectedIndex(-1);cbFiliere.setSelectedIndex(-1);
			}
		});
		btnClear.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		btnClear.setBounds(500, 413, 123, 23);
		contentPane.add(btnClear);
	}
}
