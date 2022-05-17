package lasW;
import java.util.*;

public class LasWiedzmas {


public static void main(String[] args) {

	Zliczanie stan=new Zliczanie();
	//USTAWIENIA
	int X = 40;			//ROZMIAR
	int Z = 20;			//ILOSC ZAJACOW
	int W = 5;			//ILOSC WELOCIRAPTOROW
	int O = 40;			//ILOSC KRZEWOW ROZKOSZY
	int D = 10;			//ROZMIAR DOMU WIEDZMY
	int ZM = 2; 		//ILOSC ZAJACOW W MIOCIE
	int P = 1;			//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	int PZ = 1;			//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	int PW = 10;		//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	int E = 100;		//ILOSC EPOK
	boolean CP = false;  //CZY WYSWIETLAC START EPOKI (Z OWOCAMI)
	/////////////////////////////////////////////////////////

	Funkcje.sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);

	Mapa[][] mapa = new Mapa[X+2][X+2];

	Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
	System.out.println("EPOKA: 0");
	Funkcje.wyswietlenie_mapy(mapa, X);

	for(int i=0;i<E;i++) {
	
	Epoka_Implementacja krok_epoki = new Epoka_Implementacja(mapa, X, O,  P, PZ, PW,ZM, stan, i,CP);
	System.out.println("EPOKA: "+(i+1));
	Funkcje.wyswietlenie_mapy(mapa, X);
	///////////////////////

	}
}

}