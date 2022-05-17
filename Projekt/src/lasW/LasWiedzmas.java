package lasW;
import java.util.*;

public class LasWiedzmas {
	
	
public static void main(String[] args) {
	
	//USTAWIENIA
	int X = 10;	//ROZMIAR
	int Z = 3;	//ILOSC ZAJACOW
	int W = 2;	//ILOSC WELOCIRAPTOROW
	int O = 5;	//ILOSC KRZEWOW ROZKOSZY
	int D = 2;	//ROZMIAR DOMU WIEDZMY
	int P = 5;	//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	int PZ = 5;	//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	int PW = 5;	//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	int E = 5;	//ILOSC EPOK
	/////////////////////////////////////////////////////////
	
	Funkcje.sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);
	
	Mapa[][] mapa = new Mapa[X+2][X+2];
	
	Funkcje.ustawienie_poczatkowe(mapa,X,Z,W,O,D);
	
	Funkcje.wyswietlenie_mapy(mapa, X);

}

}
