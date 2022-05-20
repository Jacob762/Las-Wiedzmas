package lasW;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class Funkcje {

	protected static void sprawdzenie_ustawien(int X,int Z,int W,int O,int D,int P, int PZ, int PW, int E) {
		if(X<10 || X>1000) {
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
		if(O<0 || O>X) {
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


	protected static void ustawianie_mapy(int X,Mapa[][] map) {	// USTAWIENIE WSZYSTKICH PUL NA PUSTE
		for(int i=0;i<X+2;i++) {
			for(int j=0;j<X+2;j++) {
				map[i][j]=new Puste();
			}
		}
	}

	protected static void budowa_ogrodzenia(int X,Mapa[][] map) {	//USTAWIENIE WSZYSTKICH POL OBRZEZNYCH NA OGRODZENIE
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

	protected static void ustawianie_domu_wiedzmy (int X,int D,Mapa[][] map) {	//USTAWIENIE DOMU WIEDZMY W LOSOWYM MIEJSCU
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

	protected static void ustawianie_owocow (int X,int O,Mapa[][] map) {	//USTAWIENIE KRZEWOW ROZKOSZY W LOSOWYM MIEJSCU
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

	protected static void ustawianie_zajacow (int X,int Z,Mapa[][] map) {	//USTAWIENIE ZAJACOW W LOSOWYM MIEJSCU											
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


	protected static void ustawianie_welociraptorow (int X,int Z,Mapa[][] map) {	//USTAWIENIE WELOCIRAPTOROW W LOSOWYM MIEJSCU
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



	protected static void ustawienie_poczatkowe(Mapa[][] map,int X,int Z,int W,int O,int D) {

		ustawianie_mapy(X,map);
		budowa_ogrodzenia(X,map);
		ustawianie_domu_wiedzmy(X,D,map);
		ustawianie_owocow(X,O,map);
		ustawianie_zajacow(X,Z,map);
		ustawianie_welociraptorow(X,W,map);


	}


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

	protected static void czekaj(int X) {
        try{
            Thread.sleep(X);
        }
        catch(Exception e){} 
	}
	
	protected static void przewijanie() {
	    for(int i=0; i<25; ++i)
	        System.out.println();
	    System.out.flush();
	}

	protected static Zliczanie wyswietlenie_mapy(Mapa[][] map, int X, PrintWriter zapis, int nr_epoki, JTextPane pane) { //wyswietlanie mapy zwraca zliczanie, co pomaga w tworzeniu wykresu
		Zliczanie stan=new Zliczanie();																					// tworzy rowniez na biezaco text pane

		
		for(int i=0;i<X+2;i++) {
			for( int j=0;j<X+2;j++) {
				Color color=Color.BLACK;
				System.out.print(map[i][j].symbol);
				if(map[i][j] instanceof Zajac) {color=Color.GREEN;}
				else if(map[i][j] instanceof OwocRozkoszy) {color=Color.ORANGE;}
				else if(map[i][j] instanceof Welociraptor) {color=Color.RED;}
				else if(map[i][j] instanceof Puste) {color=Color.gray;}
				addText(pane,map[i][j].symbol,color);
			}
			Color color=Color.BLACK;
			System.out.println();
			addText(pane,"\n",color);

		}
		Funkcje.stan_aktualny(map,X,stan);
		System.out.println("Zajace	Welociraptory	Krzewy rozkoszy		Terytorium Wiedzmy");
		System.out.println(stan.zajace+"	"+stan.welociraptory+"		"+stan.krzewy_rozkoszy+"			"+stan.dom_wiedzmy+"\n");
		Funkcje.czekaj(1000);
		Funkcje.przewijanie();
		return stan;
	}

	
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
