package lasW;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

// KLASA ODPOWIEDZIALNA ZA PRZEBIEG SYMULACJI
/**
 * Klasa odpowiedzialna za ustawienie domyslnych parametrow symulacji
 * @author Nowaczyk/Klawon
 *
 */
public class LasWiedzmas {
	
	//USTAWIENIA DOMYSLNE
	static int X = 40;			//ROZMIAR
	static int Z = 15;			//ILOSC ZAJACOW
	static int W = 15;			//ILOSC WELOCIRAPTOROW
	static int O = 30;			//ILOSC KRZEWOW ROZKOSZY
	static int D = 15;			//ROZMIAR DOMU WIEDZMY
	static int ZM = 2; 			//ILOSC ZAJACOW W MIOCIE
	static int P = 1;			//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	static int PZ = 1;			//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	static int PW = 5;			//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	static int E = 10;			//ILOSC EPOK
	///////////////////
	
	static Zliczanie sTan;


	


/**
 * Funkcja odpowiadajaca za przeprowadzenie i wyswietlenie symulacji z odpowiednimi parametrami
 * @param X Rozmiar siatki symulacji
 * @param Z Poczatkowa ilosc zajacow
 * @param W Poczatkowa ilosc welociraptorow
 * @param O Poczatkowa ilosc krzewow rozkoszy
 * @param D Poczatkowa wielkosc terytorium wiedzmy (dlugosc boku)
 * @param P Prawdopodobienstwo wybuchu wiedzmy
 * @param PZ Prawdopodobienstwo przedawkowania zajaca
 * @param PW Prawdopodobienstwo przedawkowania welociraptora
 * @param E Ilosc epok symulacji
 * @throws FileNotFoundException Wyjatek nie istnienia pliku
 */
public static void Start(int X, int Z, int W, int O, int D, int P, int PZ, int PW, int E) throws FileNotFoundException{


	
	JFrame f = new JFrame("Las Wiedzma's");
	f.setSize(1500,1000);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.setBackground(Color.WHITE);
	JTextPane pane = new JTextPane();
	f.add(pane);
	f.setVisible(true);


			Zliczanie stan=new Zliczanie();
			f.setLocationRelativeTo(null);
			/////////////////////////////////////////////////////////

			Funkcje.sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);

			Mapa[][] mapa = new Mapa[X+2][X+2];

			Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
			System.out.println("Epoka;Zajac;Welociraptor;Krzewy_rozkoszy;Terytorium_wiedzmy");
			Funkcje.wyswietlenie_mapy(mapa, X,0,pane);
			XYChart chart = new XYChartBuilder().height(600).width(400).title("Wykres").xAxisTitle("Epoka").yAxisTitle("Stworzenie").build(); //TWORZENIE WYKRESU
			ArrayList<Integer> listaE = new ArrayList<>();
			listaE.add(0);
			ArrayList<Integer> listaZ = new ArrayList<>();
			listaZ.add(Z);
			ArrayList<Integer> listaW = new ArrayList<>();
			listaW.add(W);
			ArrayList<Integer> listaO = new ArrayList<>();
			listaO.add(O);
			chart.addSeries("Zajace", listaE, listaZ);
			chart.addSeries("Welocilaptory", listaE, listaW);
			chart.addSeries("Owoce rozkoszy",listaE, listaO);

			Thread t1 = new Thread(new Runnable() { // THREAD DO STWORZENIA FUNKCJI REALTIME
													// BEZ TEGO WYWALA BLAD
				@Override
				public void run() {
					final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
					sw.displayChart(); 		// WYSWIETLANIE WYKRESU (MUSI BYC W THREADZIE)
					f.remove(pane);
					f.invalidate();
					f.validate();
					for(int i=1;i<=E;i++) {
						JTextPane tempPane = new JTextPane();
						f.add(tempPane);	// DODANIE NOWO WYGENEROWANEGO PANE DO GUI
						new Epoka_Implementacja(mapa, X, O,  P, PZ, PW,ZM, stan, i);

						sTan = Funkcje.wyswietlenie_mapy(mapa, X,i,tempPane); 

						listaE.add(i);
						listaZ.add(LasWiedzmas.sTan.zajace);
						listaW.add(LasWiedzmas.sTan.welociraptory);
						listaO.add(LasWiedzmas.sTan.krzewy_rozkoszy);
							javax.swing.SwingUtilities.invokeLater(new Runnable() { // DRUGI RUNNABLE W THREADZIE, THREAD POZWALA MU
								@Override											// ISTNIEC W SPOKOJU
									public void run() {
										chart.updateXYSeries("Zajace", listaE, listaZ, null);
										chart.updateXYSeries("Welocilaptory", listaE, listaW, null);
										chart.updateXYSeries("Owoce rozkoszy",listaE,listaO,null);
										sw.repaintChart();
									}
								});
						f.remove(tempPane);
						f.invalidate();
						f.validate();

					}
				}
			});
			t1.start();


	}

}
