package lasW;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class LasWiedzmas {
	//USTAWIENIA
	static int X = 40;				//ROZMIAR
	static int Z = 15;				//ILOSC ZAJACOW
	static int W = 15;				//ILOSC WELOCIRAPTOROW
	static int O = 30;				//ILOSC KRZEWOW ROZKOSZY
	static int D = 15;				//ROZMIAR DOMU WIEDZMY
	static int ZM = 2; 			//ILOSC ZAJACOW W MIOCIE
	static int P = 1;				//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	static int PZ = 1;				//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	static int PW = 5;				//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	static int E = 10;			//ILOSC EPOK
	static Zliczanie sTan;
	static boolean CP = false; 	//CZY WYSWIETLAC START EPOKI (Z OWOCAMI)



public static void Start(int X, int Z, int W, int O, int D, int P, int PZ, int PW, int E) throws FileNotFoundException{ //funckja main zamieniona na start do gui no. 3, w zasadzie cale gui jest tu

	JFrame f = new JFrame("Las Wiedzma's");
	f.setSize(1500,1000);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JTextPane pane = new JTextPane();
	f.add(pane);
	
	f.setVisible(true);

	 PrintWriter zapis;
		 zapis = new PrintWriter("LasWiedzmas.txt");
		 zapis.println("Epoka;Zajace;Welociraptory;Krzewy_rozkoszy;Terytorium_Wiedzmy");
			Zliczanie stan=new Zliczanie();
			f.setLocationRelativeTo(null);
			/////////////////////////////////////////////////////////

			Funkcje.sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);

			Mapa[][] mapa = new Mapa[X+2][X+2];

			Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
			System.out.println("EPOKA: 0");
			Funkcje.wyswietlenie_mapy(mapa, X,zapis,0,pane); //zapis dziala
			XYChart chart = new XYChartBuilder().height(600).width(400).title("Wykres").xAxisTitle("Epoka").yAxisTitle("Stworzenie").build(); //tworzenie wykresu
			ArrayList<Integer> listaE = new ArrayList<>();
			listaE.add(0);
			ArrayList<Integer> listaZ = new ArrayList<>();
			listaZ.add(Z);
			ArrayList<Integer> listaW = new ArrayList<>();
			listaW.add(W);
			
			chart.addSeries("Z", listaE, listaZ);
			chart.addSeries("W", listaE, listaW);
			

			Thread t1 = new Thread(new Runnable() { //thread do stworzenia funkcji realtime, bez tego wywala blad
				
				@Override
				public void run() {
					final SwingWrapper<XYChart> sw = new SwingWrapper<XYChart>(chart);
					sw.displayChart(); //wyswietlanie wykresu (musi byc w threadzie)
					f.remove(pane);
					f.invalidate();
					f.validate();
					for(int i=1;i<=E;i++) {
						JTextPane tempPane = new JTextPane();
						f.add(tempPane);//dodanie nowo wygenerowaengo pane do gui
						new Epoka_Implementacja(mapa, X, O,  P, PZ, PW,ZM, stan, i,CP,zapis);
						System.out.println("EPOKA: "+(i));
						sTan = Funkcje.wyswietlenie_mapy(mapa, X,zapis,i,tempPane); //TO-DO tu sie jebie zapis

						listaE.add(i + 1);
						listaZ.add(LasWiedzmas.sTan.zajace);
						listaW.add(LasWiedzmas.sTan.welociraptory);
							javax.swing.SwingUtilities.invokeLater(new Runnable() { //drugi runnable w threadzie, thread pozwala mu istniec i funkcjonowac w spokokju
								@Override
									public void run() {
										chart.updateXYSeries("Z", listaE, listaZ, null);
										chart.updateXYSeries("W", listaE, listaW, null);
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

			zapis.close();
	}

}
