package lasW;

public interface Epoka {
	
    void wybuch_domu_wiedmy(int X, Mapa[][] map, int P);
	void uzupelnienie_owocow(int X, Mapa[][] map, int O,Zliczanie stan);
	void rozmnozenie_zajacow(int X, Mapa[][] map);
	void rozmnozenie_welociraptorow(int X, Mapa[][] map);
	void przedawkowanie(int X,Mapa[][] map, int PZ, int PW);
	void wykonanie_ruchow(int X,Mapa[][] map,Zliczanie stan);
	
	
	void krok_epoki(Mapa[][] map, int X, int O, int P, int PZ, int PW, Zliczanie stan);
}
