package lasW;

public class Epoka_Implementacja implements Epoka {
	Zajac ZAJ = new Zajac();
	Welociraptor WEL = new Welociraptor();
	OwocRozkoszy OWO= new OwocRozkoszy(); 
	Wiedzma WIE = new Wiedzma();
	
	@Override
	public void wybuch_domu_wiedmy(int X, Mapa[][] map, int P) {
		WIE.czy_wybuch(X, map, P);
	}
	
	
	@Override
	public void uzupelnienie_owocow(int X, Mapa[][] map, int O, Zliczanie stan) {
		Funkcje.stan_aktualny(map, X, stan);
	int ilosc_owocow_do_rozmnozenia= O-stan.krzewy_rozkoszy;

	OWO.rozmnozenie(X, map, 1, 1, ilosc_owocow_do_rozmnozenia);
	}
	
	@Override
	public void rozmnozenie_zajacow(int X, Mapa[][] map) {
		Zajac ZAJ = new Zajac();
		for(int i=1;i<X+2;i++) {
			for(int j=1;j<X+2;j++) {
				if(map[i][j]instanceof Zajac) {
					ZAJ.rozmnozenie(X, map, i, j, 1);
				}
			}
		}
	}
	
	@Override
	public void rozmnozenie_welociraptorow(int X, Mapa[][] map) {

		for(int i=1;i<X+2;i++) {
			for(int j=1;j<X+2;j++) {
				if(map[i][j]instanceof Welociraptor) {
					WEL.rozmnozenie(X, map, i, j, 1);
				}
			}
		}
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
		
	}
	
	
	@Override
	public  void krok_epoki(Mapa[][] map, int X, int O, int P, int PZ, int PW,Zliczanie stan) {
		wybuch_domu_wiedmy(X,map,P);
		uzupelnienie_owocow(X,map,O,stan);
		rozmnozenie_zajacow(X,map);
		rozmnozenie_welociraptorow(X,map);
		przedawkowanie(X,map,PZ,PW);
	}
}
