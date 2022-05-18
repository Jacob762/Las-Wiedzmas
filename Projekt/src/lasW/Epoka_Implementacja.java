package lasW;
import java.io.*;

public class Epoka_Implementacja implements Epoka {
	Zajac ZAJ = new Zajac();
	Welociraptor WEL = new Welociraptor();
	OwocRozkoszy OWO= new OwocRozkoszy(); 
	Wiedzma WIE = new Wiedzma();
	Zliczanie stan = new Zliczanie();
	
	@Override
	public void wybuch_domu_wiedmy(int X, Mapa[][] map, int P) {
		WIE.czy_wybuch(X, map, P);
	}
	
	
	@Override
	public void uzupelnienie_owocow(int X, Mapa[][] map, int O) {
		Funkcje.stan_aktualny(map, X, stan);
		int BO = O-stan.krzewy_rozkoszy;
	OWO.rozmnozenie(X, map, 1, 1, BO);
	}
	

	@Override
	public void rozmnozenie(int X, Mapa[][] map, int ZM) {

		for(int i=1;i<X+1;i++) {
			for(int j=1;j<X+1;j++) {
				if(map[i][j]instanceof NajedzonyWelociraptor) {
					WEL.rozmnozenie(X, map, i, j, 1);
				}
				else if(map[i][j]instanceof NajedzonyZajac) {
					ZAJ.rozmnozenie(X, map, i, j, ZM);
				}
			}
		}
		Funkcje.UnBuffZajaca(map,X,1);
		Funkcje.UnBuffWelociraptora(map,X,1);
	}
	
	@Override
	public void przedawkowanie(int X,Mapa[][] map, int PZ, int PW) {
		
		
		for(int i=1;i<X+2;i++) {		
			for(int j=1;j<X+2;j++) {
				if(map[i][j]instanceof Zajac) {
					ZAJ.przedawkowanie(i, j, map, PZ);
				}
				else if(map[i][j]instanceof Welociraptor) {
					WEL.przedawkowanie(i, j, map, PW);
				}
			}
		}
	}
	
	
	@Override
	public void wykonanie_ruchow(int X,Mapa[][] map,Zliczanie stan) {
		for(int i=1;i<X+2;i++) {
			for(int j=1;j<X+2;j++) {
				if(map[i][j]instanceof Zajac) {
					ZAJ.wykonanie_ruchu(i, j, map,stan);
				}
				else if(map[i][j]instanceof Welociraptor) {
					WEL.wykonanie_ruchu(i, j, map,stan);
				}
			}	
		}
		Funkcje.UnBuffZajaca(map,X,0);
		Funkcje.UnBuffWelociraptora(map,X,0);

		for(int i=1;i<X+2;i++) {
			for(int j=1;j<X+2;j++) {
				if(map[i][j]instanceof Welociraptor) {
					WEL.wykonanie_ruchu(i, j, map, stan);
				}
			}	
		}
		Funkcje.UnBuffWelociraptora(map,X,0);

	}
	
	
	
	Epoka_Implementacja(Mapa[][] map, int X, int O, int P, int PZ, int PW,int ZM,Zliczanie stan,int i, boolean CP, PrintWriter zapis){
		wybuch_domu_wiedmy(X,map,P);
		uzupelnienie_owocow(X,map,O);
		if(CP) {
		System.out.println("START	EPOKA: "+(i));
		Funkcje.wyswietlenie_mapy(map, X,zapis, i);
		}
		przedawkowanie(X,map,PZ,PW);
		wykonanie_ruchow(X,map,stan);
		rozmnozenie(X,map,ZM);

	
	}
}
