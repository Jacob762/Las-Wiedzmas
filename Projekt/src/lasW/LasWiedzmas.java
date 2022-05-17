package lasW;
import java.util.*;

public class LasWiedzmas {


public static void main(String[] args) {

	Zliczanie stan=new Zliczanie();
	//USTAWIENIA
	int X = 10;	//ROZMIAR
	int Z = 2;	//ILOSC ZAJACOW
	int W = 1;	//ILOSC WELOCIRAPTOROW
	int O = 2;	//ILOSC KRZEWOW ROZKOSZY
	int D = 1;	//ROZMIAR DOMU WIEDZMY
	int P = 5;	//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	int PZ = 5;	//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	int PW = 5;	//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	int E = 1;	//ILOSC EPOK
	/////////////////////////////////////////////////////////

	Funkcje.sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);

	Mapa[][] mapa = new Mapa[X+2][X+2];

	Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
	Funkcje.wyswietlenie_mapy(mapa, X);

	/*
	///////////////////////////	NA BRUDNO USUWANIE WSZYSTKICH KRZEWOW I SPRAWDZENIE CZY POJAWIA SIE Z POWROTEM
	for(int i=0;i<X+2;i++) {
		for (int j=0;j<X+2;j++) {
			if(mapa[i][j]instanceof OwocRozkoszy) {
				mapa[i][j]=new Puste();
			}
		}
	}
	*/
	for(int i=0;i<E;i++) {
	Funkcje.epoka(mapa, X,O,P,PZ,PW);
	Funkcje.wyswietlenie_mapy(mapa, X);
	///////////////////////
	

	}
}

}