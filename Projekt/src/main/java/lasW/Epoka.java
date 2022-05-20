package lasW;

public interface Epoka {
    void wybuch_domu_wiedmy(int X, Mapa[][] map, int P);
	void uzupelnienie_owocow(int X, Mapa[][] map, int O);
	void przedawkowanie(int X,Mapa[][] map, int PZ, int PW);
	void wykonanie_ruchow(int X,Mapa[][] map,Zliczanie stan);
	void rozmnozenie(int X, Mapa[][] map, int ZM);
}
