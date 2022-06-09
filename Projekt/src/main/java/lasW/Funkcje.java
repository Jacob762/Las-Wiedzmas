package lasW;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.Random;


/**
 * Klasa zawierajaca funkcje wykorzystywane przez pozosta³e klasy
 * @author Nowaczyk/Klawon
 *
 */
public class Funkcje {

	/**
	 * Metoda sprawdzajaca czy ustawienia sa poprawne (zgodne z wymaganiami zawartymi w tebeli z opisu projektu w jezyku naturalnym)
	 * @param X Rozmiar siatki symulacji
	 * @param Z Poczatkowa ilosc zajacow
	 * @param W Poczatkowa ilosc welociraptorow
	 * @param O Poczatkowa ilosc krzewow rozkoszy
	 * @param D Poczatkowa wielkosc terytorium wiedzmy (dlugosc boku)
	 * @param P Prawdopodobienstwo wybuchu wiedzmy
	 * @param PZ Prawdopodobienstwo przedawkowania zajaca
	 * @param PW Prawdopodobienstwo przedawkowania welociraptora
	 * @param E Ilosc epok symulacji
	 */
	protected static void sprawdzenie_ustawien(int X,int Z,int W,int O,int D,int P, int PZ, int PW, int E) {
		if(X<10 || X>40) {
			System.out.println("Niepoprawna wartosc X: "+X);
			 System.exit(0);
		}

		if(Z<0 || Z>(X*X)/2) {
			System.out.println("Niepoprawna wartosc Z: "+Z);
			 System.exit(0);
		}
		if(W<0 || W>(X*X)/2) {
			System.out.println("Niepoprawna wartosc W: "+W);
			 System.exit(0);
		}
		if(O<0 || O>(X*X)/2) {
			System.out.println("Niepoprawna wartosc O: "+O);
			 System.exit(0);
		}
		if(D<0 || D>X/2) {
			System.out.println("Niepoprawna wartosc D: "+D);
			 System.exit(0);
		}
		if(P<0 || P>10) {
			System.out.println("Niepoprawna wartosc P: "+P);
			 System.exit(0);
		}
		if(PZ<0 || PZ>20) {
			System.out.println("Niepoprawna wartosc PZ: "+PZ);
			 System.exit(0);
		}
		if(PW<0 || PW>20) {
			System.out.println("Niepoprawna wartosc PW: "+PW);
			 System.exit(0);
		}
		if(E<0 || E>10000) {
			System.out.println("Niepoprawna wartosc E: "+E);
			 System.exit(0);
		}
	}


	/**
	 * Metoda wypelnieniajaca tablice symulacji obiektami klasy Puste
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 */
	protected static void ustawianie_mapy(int X,Mapa[][] map) {
		for(int i=0;i<X+2;i++) {
			for(int j=0;j<X+2;j++) {
				map[i][j]=new Puste();
			}
		}
	}

	/**
	 * Metoda wypelnieniajaca tabrzegi tablicy symulacji obiektami klasy Ogrodzenie
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 */
	protected static void budowa_ogrodzenia(int X,Mapa[][] map) {
		for(int k=0;k<X+2;k+=X+1) {
			for(int i=0;i<X+2;i++) {
			map[k][i]=new Ogrodzenie();
		}
	}

		for(int k=0;k<X+2;k+=X+1) {
			for(int i=1;i<X+2;i++) {
			map[i][k]=new Ogrodzenie();
		}
	}
	}

	/**
	 * Metoda ustawiajaca w losowym miejscu mapy terytorium wiedzmy o wielkosci DxD
	 * @param X Rozmiar siatki symulacji
	 * @param D Dlugosc boku kwadratu terytorium wiedzmy
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 */
	protected static void ustawianie_domu_wiedzmy (int X,int D,Mapa[][] map) {
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
		if(D==1) {
		 miejsceY = los.nextInt(X)+1;
		 miejsceX = los.nextInt(X)+1;
	}
		if(D>1 && D<X/2) {
			 miejsceY = los.nextInt(X-D+1)+1;
			 miejsceX = los.nextInt(X-D+1)+1;
		}


		for(int i=0;i<D;i++) {
			for(int j=0;j<D;j++) {
				map[miejsceY+i][miejsceX+j] = new Wiedzma();
			}
		}
	}

	/**
	 * Metoda ustawiajaca w losowym miejscu mapy O krzewow rozkoszy
	 * @param X Rozmiar siatki symulacji
	 * @param O Poczatkowa ilosc krzewow rozkoszy
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 */
	protected static void ustawianie_owocow (int X,int O,Mapa[][] map) {
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();

		for(int i=0;i<O;) {
			 miejsceY = los.nextInt(X)+1;
			 miejsceX = los.nextInt(X)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new OwocRozkoszy();
			 i++;
			 }
		}


	}

	/**
	 * Metoda ustawiajaca w losowym miejscu mapy Z zajacow
	 * @param X Rozmiar siatki symulacji
	 * @param Z Poczatkowa ilosc zajacow
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 */
	protected static void ustawianie_zajacow (int X,int Z,Mapa[][] map) {										
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();

		for(int i=0;i<Z;) {
			 miejsceY = los.nextInt(X)+1;
			 miejsceX = los.nextInt(X)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new Zajac();
			 i++;
			 }
		}
	}


	/**
	 * Metoda ustawiajaca w losowym miejscu mapy W welociraptorow
	 * @param X Rozmiar siatki symulacji
	 * @param W Poczatkowa ilosc welociraptorow
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 */
	protected static void ustawianie_welociraptorow (int X,int Z,Mapa[][] map) {
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();

		for(int i=0;i<Z;) {
			 miejsceY = los.nextInt(X)+1;
			 miejsceX = los.nextInt(X)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new Welociraptor();
			 i++;
			 }
		}
	}



	/**
	 * Metoda wywolujaca w odpowiedniej kolejnosci wszystkie niezbedne metody odpowiedzialne za zainicjalizowanie poczatkowej siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param X Rozmiar siatki symulacji
	 * @param Z Poczatkowa ilosc zajacow
	 * @param W Poczatkowa ilosc welociraptorow
	 * @param O Poczatkowa ilosc krzewow rozkoszy
	 * @param D Dlugosc boku kwadratu terytorium wiedzmy
	 */
	protected static void ustawienie_poczatkowe(Mapa[][] map,int X,int Z,int W,int O,int D) {

		ustawianie_mapy(X,map);
		budowa_ogrodzenia(X,map);
		ustawianie_domu_wiedzmy(X,D,map);
		ustawianie_owocow(X,O,map);
		ustawianie_zajacow(X,Z,map);
		ustawianie_welociraptorow(X,W,map);


	}


	/**
	 * Metoda uaktualniajaca dane zawarte w obiekcie klasy Zliczanie
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param X Rozmiar siatki symulacji
	 * @param ilosc Obiekt klasy ZLiczanie w ktorym maja zostac zaktualizowane dane
	 */
	protected static void stan_aktualny(Mapa[][] map, int X, Zliczanie ilosc) { 				
		for(int i=0;i<X+2;i++) {																
			for(int j=0;j<X+2;j++) {
				if(map[i][j] instanceof Zajac) {
					ilosc.zajace++;
				}
				else if (map[i][j] instanceof Welociraptor) {
					ilosc.welociraptory++;
				}
				else if (map[i][j] instanceof OwocRozkoszy) {
					ilosc.krzewy_rozkoszy++;
				}
				else if (map[i][j] instanceof Wiedzma) {
					ilosc.dom_wiedzmy++;
				}
			}
		}
	}

	/**
	 * Metoda wstrzymujaca dalsze dzialanie programu
	 * @param X Ilosc czasu oczekiwania w milisekundach
	 */
	protected static void czekaj(int X) {													
        try{
            Thread.sleep(X);
        }
        catch(Exception e){} 
	}
	

	/**
	 * Metoda odpowiedzialna za uzupelnienie JTextPane w celu pozniejszego wyswietlania symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param X Rozmiar siatki symulacji
	 * @param nr_epoki Numer aktualnie przeprowadzanego kroku-epoki symulacji
	 * @param pane Obiekt klasy JTextPane, ktory wyswietla aktualne polozenie agentow na mapie
	 * @return
	 */
	protected static Zliczanie wyswietlenie_mapy(Mapa[][] map, int X, int nr_epoki, JTextPane pane) { 
		Zliczanie stan=new Zliczanie();																				

		
		for(int i=0;i<X+2;i++) {
			for( int j=0;j<X+2;j++) {

				Color color=Color.BLACK;
				if(map[i][j] instanceof Zajac) {color=Color.GREEN;}
				else if(map[i][j] instanceof OwocRozkoszy) {color=Color.ORANGE;}
				else if(map[i][j] instanceof Welociraptor) {color=Color.RED;}
				else if(map[i][j] instanceof Puste) {color=Color.gray;}

				addText(pane,map[i][j].symbol,color);
			}
			Color color=Color.BLACK;
			addText(pane,"\n",color);

		}
		Funkcje.stan_aktualny(map,X,stan);
		addText(pane," Epoka: "+nr_epoki+" | Zajace: "+stan.zajace+" | Welociraptory: "+	stan.welociraptory+" | Krzewy rozkoszy: "+stan.krzewy_rozkoszy+" | Terytorium Wiedzmy: "+stan.dom_wiedzmy+"\n",Color.BLACK);
		System.out.println(nr_epoki+";"+stan.zajace+";"+stan.welociraptory+";"+stan.krzewy_rozkoszy+";"+stan.dom_wiedzmy);
		Funkcje.czekaj(1000);
		return stan;
	}

	
	/**
	 * Metoda zamienajaca obiekty pomocnicze dla klasy Zajac na obiekty wlasciwe
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param X Rozmiar siatki symulacji
	 * @param co parametr okreslajacy ktory buffor zamieniac
	 */
	protected static void UnBuffZajaca(Mapa[][]map,int X,int co) {
		for(int i=1;i<X+1;i++) {
			for(int j=1;j<X+1;j++) {
				if(map[i][j]instanceof BuforZajaca) {
					map[i][j]=new Zajac();
				}
				else if(map[i][j]instanceof NajedzonyZajac && co!=0) {
					map[i][j]=new Zajac();
				}
			}
		}
	}
	
	/**
	 * Metoda zamienajaca obiekty pomocnicze dla klasy Welociraptor na obiekty wlasciwe
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param X Rozmiar siatki symulacji
	 * @param co parametr okreslajacy ktory buffor zamieniac
	 */
	protected static void UnBuffWelociraptora(Mapa[][]map,int X,int co) {
		for(int i=1;i<X+1;i++) {
			for(int j=1;j<X+1;j++) {
				if(map[i][j]instanceof BuforWelociraptora && co==0) {
					map[i][j]=new Welociraptor();
				}
				else if(map[i][j]instanceof NajedzonyWelociraptor && co!=0) {
					map[i][j]=new Welociraptor();
				}
			}
		}
	}

	/**
	 * @param pane Obiekt klasy JTextPane, ktory wyswietla aktualne polozenie agentow na mapie
	 * @param text Symbol, ktory ma byc dodany do JTextPane
	 * @param color Kolor symbolu, ktory ma byc dodany do JTextPane
	 */
	public static void addText(JTextPane pane, String text,Color color){
		StyledDocument doc = pane.getStyledDocument();
		Style style = pane.addStyle("Color Style",null);
		StyleConstants.setForeground(style,color);
		   StyleConstants.setFontSize(style, 18);
		try
		{
			doc.insertString(doc.getLength(), text, style );
		}
		 catch (BadLocationException e) {
	            e.printStackTrace();
	        }
	}
	

	


}
