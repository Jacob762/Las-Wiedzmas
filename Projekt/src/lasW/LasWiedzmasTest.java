package lasW;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class LasWiedzmasTest {

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
	Assert.assertEquals(ilosc,(X+2)*(X+2));
}

	@Test
	void test_poprawnej_implementacji_ogrodzenia() {	//SPRAWDZENIE CZY POPRAWNIE TWORZY OGRODZNIE
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
	Assert.assertEquals(ilosc,0);	// CZY W ODPOWIEDNIM MIEJSCU


}



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

		Assert.assertEquals(ilosc,0);	// SPRAWDZAMY CZY W DOBRYM MIEJSCU (JEZELI BY BYL AGENT W OGRODZENIU TO ZLE)

}



}
