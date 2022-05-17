package lasW;
import java.util.*;

public class LasWiedzmas {


public static void main(String[] args) {

	Zliczanie stan=new Zliczanie();
	//USTAWIENIA
	int X = 10;	//ROZMIAR
	int Z = 10;	//ILOSC ZAJACOW
	int W = 5;	//ILOSC WELOCIRAPTOROW
	int O = 6;	//ILOSC KRZEWOW ROZKOSZY
	int D = 3;	//ROZMIAR DOMU WIEDZMY
	int P = 5;	//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	int PZ = 5;	//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	int PW = 5;	//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	int E = 100;	//ILOSC EPOK
	/////////////////////////////////////////////////////////

	Funkcje.sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);

	Mapa[][] mapa = new Mapa[X+2][X+2];

	Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
	System.out.println("EPOKA: 0");
	Funkcje.wyswietlenie_mapy(mapa, X);


	for(int i=0;i<E;i++) {
	Funkcje.epoka(mapa, X,O,P,PZ,PW);
	System.out.println("EPOKA: "+(i+1));
	Funkcje.wyswietlenie_mapy(mapa, X);
	///////////////////////
	

	}
}

}