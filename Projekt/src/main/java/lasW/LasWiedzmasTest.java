package lasW;

import java.util.*;
import java.io.PrintWriter;


import org.junit.Assert;
import org.junit.jupiter.api.Test;

// KLASA WYKONUJACA TESTY JEDNOSTKOWE
/**
 * Klasa zawierajaca testy jednostkowe
 * @author Nowaczyk/Klawon
 *
 */
class LasWiedzmasTest {
	PrintWriter zapis;
	boolean CP=false;
	Wiedzma WIE = new Wiedzma();
	Zajac ZAJ = new Zajac();
	Welociraptor WEL = new Welociraptor();
	OwocRozkoszy OWO = new OwocRozkoszy();
	
	
	/**
	 * Test poprawnej implementacji pustej siatki - sprawdza czy tworzy sie odpowiedni rozmiar i czy wszedzie sa obiekty klasy Puste
	 */
	@Test
	void test_poprawnej_implementacji_pustej_siatki() {	// SPRAWDZENIE CZY TWORZY ODPOWIEDNIA WIELKOSC SIATKI I WYPELNIA PUSTYMI ELEMENTAMI
		Random los=new Random();
	int X= los.nextInt(1000-10)+11;
	int ilosc=0;
	Mapa[][] mapa = new Mapa[X+2][X+2];
	Funkcje.ustawianie_mapy(X,mapa);
	for(int i=0;i<X+2;i++) {
		for(int j=0;j<X+2;j++) {
			if(mapa[i][j] instanceof Puste) {
				ilosc++;
			}
		}
	}
	Assert.assertEquals((X+2)*(X+2),ilosc);
}

	/**
	 * Test poprawnej implementacji ogrodznia- sprawdza czy obiekty klasy Ogrodzenie sa ustawiane w poprawnej ilosci i miejscu
	 */
	@Test
	void test_poprawnej_implementacji_ogrodzenia() {	// SPRAWDZENIE CZY POPRAWNIE TWORZY OGRODZNIE
		Random los=new Random();
	int X= los.nextInt(1000-10)+11;
	int ilosc=0;
	Mapa[][] mapa = new Mapa[X+2][X+2];
	Funkcje.ustawianie_mapy(X,mapa);
	Funkcje.budowa_ogrodzenia(X,mapa);
	for(int i=0;i<X+2;i++) {
		for(int j=0;j<X+2;j++) {
			if(mapa[i][j] instanceof Ogrodzenie) {
				ilosc++;
			}
		}
	}
	Assert.assertEquals(ilosc,2*(X+2)+2*X);	// CZY ODPOWIEDNIA ILOSC ELEMENTOW

	ilosc=0;
	for(int i=1;i<X+1;i++) {
		for(int j=1;j<X+1;j++) {
			if(mapa[i][j] instanceof Ogrodzenie) {
				ilosc++;
			}
		}
	}
	Assert.assertEquals(0,ilosc);	// CZY W ODPOWIEDNIM MIEJSCU


}


	/**
	 * Test poprawnej implementacji agentow- sprawdza czy obiekty klasy Zajac i Welociraptor sa dodawane w odpowiednim miejscu i ilosci
	 */
	@Test
	void test_poprawnej_implementacji_agentow() {	// SPRAWDZENIE CZY OBSADZA AGENTOW W DOBRYM MIEJSCU I ILOSCI
		Zliczanie stan=new Zliczanie();
		Random los=new Random();
		int X,Z,W,O,D;
		 X=los.nextInt(1000-10)+11;
		 Z=los.nextInt((X*X)/2);
		 W=los.nextInt((X*X)/2);
		 O=los.nextInt(X/2);
		 D=los.nextInt(X/2);
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		Funkcje.stan_aktualny(mapa,X,stan);
		Zliczanie powinno_byc=new Zliczanie(Z,W,O,D);
		Assert.assertEquals(powinno_byc.zajace,stan.zajace);
		Assert.assertEquals(powinno_byc.dom_wiedzmy,stan.dom_wiedzmy);
		Assert.assertEquals(powinno_byc.welociraptory,stan.welociraptory);
		Assert.assertEquals(powinno_byc.krzewy_rozkoszy,stan.krzewy_rozkoszy);	// KONIEC SPRAWDZANIA ILOSCI

		int ilosc=0;

		for(int k=0;k<X+2;k+=X+1) {
			for(int i=0;i<X+2;i++) {
			if(!(mapa[k][i] instanceof Ogrodzenie)) {
				ilosc++;
			}
		}
	}

		for(int k=0;k<X+2;k+=X+1) {
			for(int i=1;i<X+2;i++) {
				if(!(mapa[k][i] instanceof Ogrodzenie)) {
					ilosc++;
				}
		}
	}

		Assert.assertEquals(0,ilosc);	// SPRAWDZAMY CZY W DOBRYM MIEJSCU (JEZELI BY BYL AGENT W OGRODZENIU TO ZLE)
}
	
	
	/**
	 * Test poprawnego wybuchu terytorium wiedzmy
	 */
	@Test
	void test_wybuchu_domu_wiedzmy(){	// SPRAWDZAMY CZY TERYTORIUM WIEDZMY POPRAWNIE WYBUCHA
		Random los=new Random();
		int X= los.nextInt(1000-10)+11;
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawianie_mapy(X,mapa);
		
		
		int P=100;
		WIE.czy_wybuch(X, mapa, P);
		Zliczanie stan=new Zliczanie();
		Funkcje.stan_aktualny(mapa, X, stan);
		
		Assert.assertEquals(0,stan.dom_wiedzmy);
	}
	
	/**
	 * Test poprawnego przedawkowania agentow
	 */
	@Test
	void test_przedawkowania(){			// SPRAWDZAMY CZY ZAJACE I WELOCIRAPTORY UMIERAJA Z PRZEDAWKOWANIA
		Zliczanie stan=new Zliczanie();
		Random los=new Random();
		int X,Z,W,O,D;
		 X=los.nextInt(1000-10)+11;
		 Z=los.nextInt((X*X)/2);
		 W=los.nextInt((X*X)/2);
		 O=los.nextInt(X/2);
		 D=los.nextInt(X/2);
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		int PZ=100,PW=100;
		
		
		for(int i=1;i<X+1;i++) {
			for(int j=1;j<X+1;j++) {
				if(mapa[i][j] instanceof Zajac) {
					ZAJ.przedawkowanie(i, j, mapa, PZ);
				}
				else if(mapa[i][j] instanceof Welociraptor) {
					WEL.przedawkowanie(i, j, mapa, PW);
				}
			}
		}
		Funkcje.stan_aktualny(mapa, X, stan);
		Assert.assertEquals(0, stan.welociraptory);
		Assert.assertEquals(0, stan.zajace);
	}
	
	
	/**
	 * Test odrastania krzewow
	 */
	@Test
	void test_odrastania_krzewow() {					//SPRAWDZANIE CZY KRZEWY ODRASTAJA
		
		Zliczanie stan=new Zliczanie();
		Random los=new Random();
		int X,Z,W,O,D;
		 X=los.nextInt(1000-10)+11;
		 Z=los.nextInt((X*X)/2);
		 W=los.nextInt((X*X)/2);
		 O=los.nextInt(X/2);
		 D=los.nextInt(X/2);
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		
		for(int i=0;i<X+2;i++) {						// USUWANIE WSZYSTKICH KRZEWOW
			for (int j=0;j<X+2;j++) {
				if(mapa[i][j]instanceof OwocRozkoszy) {
					mapa[i][j]=new Puste();
				}
			}
		}
		int ilosc_owocow_do_rozmnozenia = O-stan.krzewy_rozkoszy;
		OWO.rozmnozenie(X, mapa, 1, 1, ilosc_owocow_do_rozmnozenia);
		
		Funkcje.stan_aktualny(mapa, X, stan);
		
		Assert.assertEquals(O, stan.krzewy_rozkoszy);
	
	}
	
	
	/**
	 * Test poprawnego przemieszczania sie zajaca
	 */
	@Test
	void test_ruchu_zajaca() {						//SPRAWDZANIE CZY ZAJAC SIE PRZEMIESZCZAJA
		
		//POZYCJA POCZATKOWA I KONCOWA
		int Yp=-1,Yk=-1,Xp=-1,Xk=-1;
		Zliczanie stan=new Zliczanie();
		Random los=new Random();
		int X,Z,W,O,D;
		 X=los.nextInt(1000-10)+11;
		 Z=1;
		 W=0;
		 O=0;
		 D=0;
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		
		for(int i=0;i<X+2;i++) {						// SZUKANIE WSPOLRZEDNYCH POCZATKOWYCH
			for (int j=0;j<X+2;j++) {
				if(mapa[i][j]instanceof Zajac) {
					Yp=i;
					Xp=j;
					break;
				}
			}
		}

		new Epoka_Implementacja(mapa, X, 0,  0, 0, 0,0, stan, 0);
		
		for(int i=0;i<X+2;i++) {						// SZUKANIE WSPOLRZEDNYCH KONCOWYCH
			for (int j=0;j<X+2;j++) {
				if(mapa[i][j]instanceof Zajac) {
					Yk=i;
					Xk=j;
					break;
				}
			}
		}
		double odleglosc = Math.sqrt((Math.pow((Yp-Yk),2)+(Math.pow((Xp-Xk),2))));	// LICZENIE ODLEGLOSCI EUKLIDESOWEJ
	
		if(odleglosc>0 && odleglosc<2) {											// JEZELI W PRZEDZIALE (0,2) TO OK
			assert(true);
		}
		else {
			assert(false);
		}
	
		
	}
	
	/**
	 * Test poprawnego przemieszczania sie welociraptora
	 */
	@Test
	void test_ruchu_welociraptora() {						//SPRAWDZANIE CZY WELOCIRAPTORY SIE PRZEMIESZCZAJA
		
		//POZYCJA POCZATKOWA I KONCOWA
		int Yp=-1,Yk=-1,Xp=-1,Xk=-1;
		Zliczanie stan=new Zliczanie();
		Random los=new Random();
		int X,Z,W,O,D;
		 X=los.nextInt(1000-10)+11;
		 Z=1;
		 W=0;
		 O=0;
		 D=0;
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		
		for(int i=0;i<X+2;i++) {						// SZUKANIE WSPOLRZEDNYCH POCZATKOWYCH
			for (int j=0;j<X+2;j++) {
				if(mapa[i][j]instanceof Welociraptor) {
					Yp=i;
					Xp=j;
					break;
				}
			}
		}
		
		new Epoka_Implementacja(mapa, X, 0,  0, 0, 0,0, stan, 0);
		
		for(int i=0;i<X+2;i++) {						// SZUKANIE WSPOLRZEDNYCH KONCOWYCH
			for (int j=0;j<X+2;j++) {
				if(mapa[i][j]instanceof Welociraptor) {
					Yk=i;
					Xk=j;
					break;
				}
			}
		}
		double odleglosc = Math.sqrt((Math.pow((Yp-Yk),2)+(Math.pow((Xp-Xk),2))));	// LICZENIE ODLEGLOSCI EUKLIDESOWEJ
	
		if(odleglosc>=0 && odleglosc<3) {											// JEZELI W PRZEDZIALE <0,3) TO OK
			assert(true);
		}
		else {
			assert(false);
		}
	}
	
	
	/**
	 * Test poprawnego zjadania owocu - obsadzenie krzewu rozkoszy i zajaca przy sobie w celu sprawdzenia czy w jednym ruchu zje owoc
	 */
	@Test
	void test_zjadania_owocu() {						//SPRAWDZANIE CZY ZAJAC ZJE OWOC
		
		
		Zliczanie stan=new Zliczanie();
		int X,Z,W,O,D;
		 X=2;
		 Z=1;
		 W=0;
		 O=1;
		 D=0;
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		new Epoka_Implementacja(mapa, X, 0,  0, 0, 0,0, stan, 0);
		Funkcje.stan_aktualny(mapa, X, stan);
		
		Assert.assertEquals(0, stan.krzewy_rozkoszy);
	
}

	/**
	 * Test poprawnego zjadania zajaca - obsadzenie welociraptora i zajaca przy sobie w celu sprawdzenia czy w jednym ruchu zajac zostanie zjedzony
	 */
	@Test
	void test_zjadania_zajaca() {						//SPRAWDZANIE CZY WELOCIRAPTOR ZJE ZAJACA
		Zliczanie stan=new Zliczanie();
		int X,Z,W,O,D;
		 X=2;
		 Z=1;
		 W=1;
		 O=0;
		 D=0;
		Mapa[][] mapa = new Mapa[X+2][X+2];
		Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
		new Epoka_Implementacja(mapa, X, 0,  0, 0, 0,0, stan, 0);
		Funkcje.stan_aktualny(mapa, X, stan);
		
		Assert.assertEquals(0, stan.zajace);
	
}

}
