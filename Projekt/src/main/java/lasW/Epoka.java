package lasW;

/**
 * Interfejs przebiegu kroku epoki
 * @author Nowaczyk/Klawon
 *
 */
public interface Epoka {	// INTERFEJS PRZEBIEGU KROKOW EPOKI
    /**
     * Metoda odpowiedzialna za wybuch terytorium wiedzmy
     * @param X Rozmiar siatki symulacji
     * @param map Tablica symulacji o rozmiarze [X+2][X+2]
     * @param P Prawdopodobienstwo wybuchu wiedzmy
     */
    void wybuch_domu_wiedmy(int X, Mapa[][] map, int P);
    
	/**
	 * Metoda odpowiedzialna za uzupelnienie krzewow rozkoszy
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param O ilosc owocow do ktorej trzeba uzupelnic
	 */
	void uzupelnienie_owocow(int X, Mapa[][] map, int O);
	
	/**
	 * Metoda odpowiedzialna za przedawkowanie zajacow i welociraptorow
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param PZ Prawdopodobienstwo przedawkowania zajaca
	 * @param PW Prawdopodobienstwo przedawkowania welociraptora
	 */
	void przedawkowanie(int X,Mapa[][] map, int PZ, int PW);
	
	/**
	 * Metoda odpowiedzialna za wykonanie ruchow
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param stan obiekt Klasy Zliczanie przechowujacy aktualna ilosc agentow
	 */
	void wykonanie_ruchow(int X,Mapa[][] map,Zliczanie stan);
	
	/**
	 * Metoda odpowiedzialna za rozmnazanie
	 * @param X Rozmiar siatki symulacji
	 * @param map Tablica symulacji o rozmiarze [X+2][X+2]
	 * @param ZM Ilosc zajacow w miocie
	 */
	void rozmnozenie(int X, Mapa[][] map, int ZM);
}
