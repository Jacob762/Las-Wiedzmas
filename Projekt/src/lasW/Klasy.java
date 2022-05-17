package lasW;

import java.util.Random;

class Zliczanie{
	protected int zajace;
	protected int welociraptory;
	protected int krzewy_rozkoszy;
	protected int dom_wiedzmy;

	Zliczanie(){
		zajace=0;
		welociraptory=0;
		krzewy_rozkoszy=0;
		dom_wiedzmy=0;
	}

	Zliczanie(int zajace,int welociraptory,int krzewy_rozkoszy, int dom_wiedzmy){
		this.zajace=zajace;
		this.welociraptory=welociraptory;
		this.krzewy_rozkoszy=krzewy_rozkoszy;
		this.dom_wiedzmy=dom_wiedzmy*dom_wiedzmy;
	}
}


class Mapa {

	protected String symbol;

}

class Ogrodzenie extends Mapa{

	Ogrodzenie(){
		symbol="*";
	}
}

class Puste extends Mapa{
	Puste(){
		symbol="P";
	}

}

class Wiedzma extends Mapa{
	Wiedzma(){
		symbol="X";
	}
	protected void czy_wybuch(int X,Mapa[][] map,int szansa) {													
		Random los = new Random();
		int czy=los.nextInt(100);
		if(czy<szansa) {
			for(int i=1;i<X+1;i++) {
				for(int j=1;j<X+1;j++) {
					if(map[i][j]instanceof Wiedzma) {
						map[i][j]=new Puste();
					}
				}
			}
		}
	}
}

abstract class Rozmnazalne extends Mapa{
	abstract boolean czy_miejsce_na_rozmnozenie(int Rozmiar, Mapa[][] x);

	abstract void rozmnozenie(int Rozmiar, Mapa[][] map, int y, int x, int ilosc);

}



abstract class Ruchome extends Rozmnazalne{
	protected int ilosc_ruchow;
	abstract boolean czy_ruch(int y, int x, Mapa[][] map);
	abstract boolean czy_jedzenie(int y, int x, Mapa[][] map);
	abstract void wykonanie_ruchu(int y, int x, Mapa[][] map);
	int zjadl_w_tej_rundzie;
	abstract void przedawkowanie (int y, int x, Mapa[][] map, int szansa_na_przedawkowanie);
}


