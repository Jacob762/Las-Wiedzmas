package lasW;


/**
 * Klasa implementujaca interface Epoka
 * @author  Nowaczyk/Klawon
 *
 */
public class Epoka_Implementacja implements Epoka {	// IMPLEMENTACJA INTERFEJSU EPOKA - TUTAJ ZNAJDUJA SIE WSZYSTKIE FUNKCJE 
	Zajac ZAJ = new Zajac();						// ODPOWIEDZIALNE ZA ZACHOWANIE AGENTOW
	Welociraptor WEL = new Welociraptor();
	OwocRozkoszy OWO= new OwocRozkoszy(); 
	Wiedzma WIE = new Wiedzma();
	Zliczanie stan = new Zliczanie();
	
	 /**
     * Metoda odpowiedzialna za wybuch terytorium wiedzmy
     * @param X Rozmiar siatki symulacji
     * @param map Tablica symulacji o rozmiarze [X+2][X+2]
     * @param P Prawdopodobienstwo wybuchu wiedzmy
     */
	@Override
	public void wybuch_domu_wiedmy(int X, Mapa[][] map, int P) {
		WIE.czy_wybuch(X, map, P);
	}
	
	
	/**
	 * Metoda odpowiedzialna za uzupelnienie krzewow rozkoszy
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param O ilosc owocow do ktorej trzeba uzupelnic
	 */
	@Override
	public void uzupelnienie_owocow(int X, Mapa[][] map, int O) {
		Funkcje.stan_aktualny(map, X, stan);
		int BO = O-stan.krzewy_rozkoszy;
		OWO.rozmnozenie(X, map, 1, 1, BO);
	}
	

	/**
	 * Metoda odpowiedzialna za rozmnazanie
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param ZM Ilosc zajacow w miocie
	 */
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
	

	/**
	 * Metoda odpowiedzialna za przedawkowanie zajacow i welociraptorow
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param PZ Prawdopodobienstwo przedawkowania zajaca
	 * @param PW Prawdopodobienstwo przedawkowania welociraptora
	 */
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
	
	

	/**
	 * Metoda odpowiedzialna za wykonanie ruchow
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param stan obiekt Klasy Zliczanie przechowujacy aktualna ilosc agentow
	 */
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
	
	
	
	/**
	 * @param map map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param X Rozmiar siatki symulacji
	 * @param O ilosc owocow do ktorej trzeba uzupelnic
	 * @param P Prawdopodobienstwo wybuchu wiedzmy
	 * @param PZ Prawdopodobienstwo przedawkowania zajaca
	 * @param PW Prawdopodobienstwo przedawkowania welociraptora
	 * @param ZM Ilosc zajacow w miocie
	 * @param stan obiekt Klasy Zliczanie przechowujacy aktualna ilosc agentow
	 * @param i Numer epoki (aktualnie nieuzywany)
	 */
	Epoka_Implementacja(Mapa[][] map, int X, int O, int P, int PZ, int PW,int ZM,Zliczanie stan,int i){

		wybuch_domu_wiedmy(X,map,P);
		uzupelnienie_owocow(X,map,O);
		przedawkowanie(X,map,PZ,PW);
		wykonanie_ruchow(X,map,stan);
		rozmnozenie(X,map,ZM);

	
	}
}
