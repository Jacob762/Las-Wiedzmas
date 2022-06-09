package lasW;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.*;

import static lasW.LasWiedzmas.X;
import static lasW.LasWiedzmas.Z;
import static lasW.LasWiedzmas.W;
import static lasW.LasWiedzmas.O;
import static lasW.LasWiedzmas.D;
import static lasW.LasWiedzmas.P;
import static lasW.LasWiedzmas.PZ;
import static lasW.LasWiedzmas.PW;
import static lasW.LasWiedzmas.E;



/**
 * Glowna klasa programu, zawiera main. Tworzy GUI.
 * @author Nowaczyk/Klawon
 *
 */

public class GUI implements ActionListener{
	
	/**
	 * 
	 * Glowna metoda rozpoczynajaca program - otwiera MENU glowne GUI
	 * @param args Tablica argumentow metody main
	 * @throws FileNotFoundException Wyjatek nie istnienia pliku
	 */
	public static void main (String[]args) throws FileNotFoundException {	
		new GUI(0);
	}


	JButton przycisk_start = new JButton(new String(Character.toChars(0x25B8)));
	JButton przycisk_ustawienia = new JButton(new String(Character.toChars(0x2699)));	
	JButton przycisk_wylanczania =  new JButton(new String(Character.toChars(0x2716)));
	JButton przycisk_zatwierdzania_ustawien =  new JButton(new String(Character.toChars(0x2705)));
	JButton powrot_do_menu =  new JButton(new String(Character.toChars(0x25C0)));
	JPanel panel1 = new JPanel();
	JFrame frame1 = new JFrame();
	JPanel panel2 = new JPanel();
	JFrame frame2 = new JFrame();	
	
	JTextField rozmiar = new JTextField(20);
	JTextField zajace = new JTextField(20);
	JTextField welociraptory = new JTextField(20);
	JTextField krzewy = new JTextField(20);
	JTextField wiedzma = new JTextField(20);
	JTextField wiedzma_bum = new JTextField(20);
	JTextField zajp = new JTextField(20);
	JTextField welp = new JTextField(20);
	JTextField epoki = new JTextField(20);
	
	/**
	 * 
	 * Klasa tworzy rozne okna w zaleznosci od parametrow:<
	 * 0 - MENU glowne
	 * 1 - MENU ustawien
	 * 2 - Wywolanie startu symulacji
	 * @param i
	 * @throws FileNotFoundException
	 */
	GUI(int i) throws FileNotFoundException {
		
		// 0 - EKRAN GLOWNY
		if(i==0) {
		frame1.setSize(1500,1000);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("LasWiedzma's");




		frame1.add(panel1);

		panel1.setLayout(null);
		ImageIcon tlo = new ImageIcon("tlo.jpg");
		JLabel bg = new JLabel("",tlo,JLabel.CENTER);
		bg.setBounds(0, 0, 1500, 1000);


		////////////////////////////////////////////
		//PRZYCISK START
		przycisk_start.setFont((new Font("DejaVu Sans", Font.BOLD,75)));
		przycisk_start.setBackground(Color.WHITE);
		przycisk_start.setForeground(Color.BLACK);	
		przycisk_start.setBounds(600, 700, 100, 100);
		przycisk_start.setFocusable(false);
		panel1.add(przycisk_start);
		przycisk_start.addActionListener(this);
		
		JLabel start = new JLabel("START");
		start.setFont((new Font("Calibri", Font.BOLD,15)));
		start.setForeground(Color.WHITE);
		start.setBounds(630,770,100,100);
		panel1.add(start);
		

		////////////////////////////////////////////
		//PRZYCISK USTAWIANIA
		przycisk_ustawienia.setFont((new Font("Doulos SIL", Font.BOLD,75)));
		przycisk_ustawienia.setBackground(Color.WHITE);
		przycisk_ustawienia.setForeground(Color.BLACK);	
		przycisk_ustawienia.setBounds(800, 700, 100, 100);
		panel1.add(przycisk_ustawienia);
		przycisk_ustawienia.addActionListener(this);
		
		JLabel ustawienia = new JLabel("USTAWIENIA");
		ustawienia.setFont((new Font("Calibri", Font.BOLD,15)));
		ustawienia.setForeground(Color.WHITE);
		ustawienia.setBounds(810,770,100,100);
		panel1.add(ustawienia);

		
		/////////////////////////////////////////////
		//PRZYCISK WYLANCZANIA

		przycisk_wylanczania.setFont((new Font("DejaVu Sans", Font.BOLD,75)));
		przycisk_wylanczania.setBackground(Color.WHITE);
		przycisk_wylanczania.setForeground(Color.RED);		
		przycisk_wylanczania.setBounds(1300, 800, 100, 100);
		panel1.add(przycisk_wylanczania);
		
		JLabel off = new JLabel("TURN OFF");
		off.setFont((new Font("Calibri", Font.BOLD,15)));
		off.setForeground(Color.WHITE);
		off.setBounds(1320,870,100,100);
		panel1.add(off);
		
		przycisk_wylanczania.addActionListener(this);
		
		
		/////////////////////////////////////////////// 
		
		
		panel1.add(bg);
		frame1.setLocationRelativeTo(null);

		frame1.setVisible(true);
		frame1.setLayout(null);
		}
		
		// 1 - USTAWIENIA
		if(i==1) {
			
			//WYPISANIE NAPISU USTAWIENIA
			JLabel set = new JLabel("USTAWIENIA");
			set.setFont((new Font("Calibri", Font.BOLD,40)));
			set.setForeground(Color.WHITE);
			set.setBounds(635,50,1000,100);
			panel2.add(set);
			//
			
			frame2.setSize(1500,1000);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame2.setTitle("LasWiedzma's");

			// ROZMIAR
			rozmiar.setBounds(700,230,165,25);
			panel2.add(rozmiar);
			
			
			JLabel rm = new JLabel("ROZMIAR");
			rm.setFont((new Font("Calibri", Font.BOLD,18)));
			rm.setForeground(Color.GREEN);
			rm.setBounds(620,195,100,100);
			panel2.add(rm);
			//
			// ZAJACE
			zajace.setBounds(700,280,165,25);
			panel2.add(zajace);
			
			
			JLabel zaj = new JLabel("ZAJACE");
			zaj.setFont((new Font("Calibri", Font.BOLD,18)));
			zaj.setForeground(Color.GREEN);
			zaj.setBounds(630,245,100,100);
			panel2.add(zaj);
			//
			// WELOCIRAPTORY
			welociraptory.setBounds(700,330,165,25);
			panel2.add(welociraptory);
			
			
			JLabel we = new JLabel("WELOCIRAPTORY");
			we.setFont((new Font("Calibri", Font.BOLD,18)));
			we.setForeground(Color.GREEN);
			we.setBounds(560,295,400,100);
			panel2.add(we);
			//
			// KRZEWY ROZKOSZY
			krzewy.setBounds(700,370,165,25);
			panel2.add(krzewy);
			
			
			JLabel krz = new JLabel("KRZEWY ROZKOSZY");
			krz.setFont((new Font("Calibri", Font.BOLD,18)));
			krz.setForeground(Color.GREEN);
			krz.setBounds(540,335,700,100);
			panel2.add(krz);
			//
			// DOM WIEDZMY
			wiedzma.setBounds(700,410,165,25);
			panel2.add(wiedzma);
			
			
			JLabel wdz = new JLabel("TERYTORIUM WIEDZMY");
			wdz.setFont((new Font("Calibri", Font.BOLD,18)));
			wdz.setForeground(Color.GREEN);
			wdz.setBounds(510,375,700,100);
			panel2.add(wdz);
			//
			// SZANSA NA WYBUCH WIEDZMY
			wiedzma_bum.setBounds(700,450,165,25);
			panel2.add(wiedzma_bum);
			
			
			JLabel wdzb = new JLabel("WYBUCH WIEDZMY");
			wdzb.setFont((new Font("Calibri", Font.BOLD,18)));
			wdzb.setForeground(Color.GREEN);
			wdzb.setBounds(540,415,700,100);
			panel2.add(wdzb);
			//
			// SZANSA NA PRZEDAWKOWANIE ZAJACA
			zajp.setBounds(700,490,165,25);
			panel2.add(zajp);
			
			
			JLabel zajpr = new JLabel("PRZEDAWKOWANIE ZAJACA");
			zajpr.setFont((new Font("Calibri", Font.BOLD,18)));
			zajpr.setForeground(Color.GREEN);
			zajpr.setBounds(480,455,700,100);
			panel2.add(zajpr);
			//
			// SZANSA NA PRZEDAWKOWANIE WELOCIRAPTORA
			welp.setBounds(700,530,165,25);
			panel2.add(welp);
			
			
			JLabel welpr = new JLabel("PRZEDAWKOWANIE WELOCIRAPTORA");
			welpr.setFont((new Font("Calibri", Font.BOLD,18)));
			welpr.setForeground(Color.GREEN);
			welpr.setBounds(400,495,700,100);
			panel2.add(welpr);
			//
			// ILOSC EPOK
			epoki.setBounds(700,570,165,25);
			panel2.add(epoki);
			
			
			JLabel epok = new JLabel("ILOSC EPOK");
			epok.setFont((new Font("Calibri", Font.BOLD,18)));
			epok.setForeground(Color.GREEN);
			epok.setBounds(600,535,700,100);
			panel2.add(epok);
			//
			
			// PRZYCISK DO ZATWIERDZANIA USTAWIEN
			przycisk_zatwierdzania_ustawien.setFont((new Font("Doulos SIL", Font.BOLD,75)));
			przycisk_zatwierdzania_ustawien.setBackground(Color.WHITE);
			przycisk_zatwierdzania_ustawien.setForeground(Color.GREEN);		
			przycisk_zatwierdzania_ustawien.setBounds(700, 650, 150, 150);
			panel2.add(przycisk_zatwierdzania_ustawien);
			przycisk_zatwierdzania_ustawien.addActionListener(this);	
			
			/////////////////////////////////////////////
			//PRZYCISK WYLANCZANIA

			przycisk_wylanczania.setFont((new Font("DejaVu Sans", Font.BOLD,75)));
			przycisk_wylanczania.setBackground(Color.WHITE);
			przycisk_wylanczania.setForeground(Color.RED);		
			przycisk_wylanczania.setBounds(1300, 800, 100, 100);
			panel2.add(przycisk_wylanczania);
			
			JLabel off = new JLabel("TURN OFF");
			off.setFont((new Font("Calibri", Font.BOLD,15)));
			off.setForeground(Color.WHITE);
			off.setBounds(1320,870,100,100);
			panel2.add(off);
			
			przycisk_wylanczania.addActionListener(this);
			
			
			/////////////////////////////////////////////
			//PRZYCISK POWROTU DO MENU GLOWNEGO

			powrot_do_menu.setFont((new Font("DejaVu Sans", Font.BOLD,75)));
			powrot_do_menu.setBackground(Color.WHITE);
			powrot_do_menu.setForeground(Color.GREEN);		
			powrot_do_menu.setBounds(80, 800, 100, 100);
			panel2.add(powrot_do_menu);
			
			JLabel back = new JLabel("POWROT");
			back.setFont((new Font("Calibri", Font.BOLD,15)));
			back.setForeground(Color.WHITE);
			back.setBounds(100,870,100,100);
			panel2.add(back);
			
			powrot_do_menu.addActionListener(this);
			
			
			/////////////////////////////////////////////// 
			
			frame2.add(panel2);

			panel2.setLayout(null);
			ImageIcon tlo = new ImageIcon("tlo.jpg");
			JLabel bg = new JLabel("",tlo,JLabel.CENTER);
			bg.setBounds(0, 0, 1500, 1000);


			
			
			
			/////////////////////////////////////////////// 
			
			
			panel2.add(bg);
			frame2.setLocationRelativeTo(null);

			frame2.setVisible(true);
			frame2.setLayout(null);
			
			
		}
		if(i==3){
			LasWiedzmas.Start( X, Z, W, O,  D, P, PZ, PW, E);
		}
	}
	
	
	/**
	 * 
	 * Metoda odpowiadajaca za dzialanie przyciskow, w zaleznosci od tego, ktory zosyal nacisniety:
	 * przycisk_start - zamyka okno MENU glownego i rozpoczyna symulacje
	 * przycisk_ustawienia - zamyka okno MENU glownego i otwiera MENU ustawien
	 * przycisk_zatwierdzania_ustawien - zamyka MENU ustawien i wywoluje symulacje z zadanymi ustawieniami
	 * przycisk_wylanczania - konczy dzialanie programu
	 * powrot_do_menu - zamyka okno ustawien i otwiera MENU glowne
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if((e.getSource() == przycisk_start)) {	// ROZPOCZYNA SYMULACJE NA USTAWIENIACH DOMYSLNYCH
			frame1.setVisible(false);
			przycisk_start.setForeground(Color.CYAN);
			//new GUI(3)	//SYMULACJA
			try {
				new GUI(3);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		
		else if(e.getSource() == przycisk_ustawienia) { // WLACZA OKIENKO WYBORU USTAWIEN
			frame1.setVisible(false);
			przycisk_ustawienia.setForeground(Color.CYAN);
			try {
				new GUI(1);
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			}

		}
	
		else if(e.getSource() == przycisk_zatwierdzania_ustawien) {	// ZATWIERDZA USTAWIENIA I ROZPOCZYNA SYMULACJE
			frame1.setVisible(false);
			String Xs=rozmiar.getText();
			int Xb=Integer.parseInt(Xs);
		
			String Zs=zajace.getText();
			int Zb=Integer.parseInt(Zs);
			
			String Ws=welociraptory.getText();
			int Wb=Integer.parseInt(Ws);
			
			String Os=krzewy.getText();
			int Ob=Integer.parseInt(Os);

			
			String Ds=wiedzma.getText();
			int Db=Integer.parseInt(Ds);
			
			String Ps=wiedzma_bum.getText();
			int Pb=Integer.parseInt(Ps);
			
			String PZs=zajp.getText();
			int PZb=Integer.parseInt(PZs);

			
			String PWs=welp.getText();
			int PWb=Integer.parseInt(PWs);

			
			String Es=epoki.getText();
			int Eb=Integer.parseInt(Es);
		
			
				frame2.setVisible(false);

				try {
					LasWiedzmas.Start( Xb, Zb, Wb, Ob,  Db, Pb, PZb, PWb, Eb);
				} catch (FileNotFoundException ex) {
					ex.printStackTrace();
				}
			

			

		}
	else if(e.getSource() == przycisk_wylanczania) {
		System.exit(0);
		}
		
	else if(e.getSource() == powrot_do_menu) {
		try {
			new GUI(0);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		}
		
		
	}
	
}
