package lasW;

import java.util.Random;

class Zliczanie{
	protected int zajace;
	protected int welociraptory;
	protected int krzewy_rozkoszy;
	protected int dom_wiedzmy;
	protected int zjedzone_zajace;
	protected int zabite_zajace;
	protected int zdeptane_krzewy;
	protected int zjedzone_owoce;
	protected int zabite_welociraptory;

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
		this.zjedzone_zajace=0;
		this.zabite_zajace=0;
		this.zdeptane_krzewy=0;
		this.zjedzone_owoce=0;
		this.zabite_welociraptory=0;
	}
}


class Mapa {

	protected String symbol;

}

class Ogrodzenie extends Mapa{

	Ogrodzenie(){
		super.symbol="*";
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

class BuforZajaca extends Mapa{
	BuforZajaca(){
		symbol="B";
	}
}

class BuforWelociraptora extends Mapa{
	BuforWelociraptora(){
		symbol="V";
	}
}

class NajedzonyZajac extends Mapa{
	NajedzonyZajac(){
		symbol="@";
	}
}

class NajedzonyWelociraptor extends Mapa{
	NajedzonyWelociraptor(){
		symbol="#";
	}
}


abstract class Rozmnazalne extends Mapa{
	abstract boolean czy_miejsce_na_rozmnozenie(int Rozmiar, Mapa[][] x, int ilsoc);

	abstract void rozmnozenie(int Rozmiar, Mapa[][] map, int y, int x, int ilosc);

}



abstract class Ruchome extends Rozmnazalne{
	protected int ilosc_ruchow;
	abstract boolean czy_ruch(int y, int x, Mapa[][] map);
	abstract boolean czy_jedzenie(int y, int x, Mapa[][] map);
	abstract void wykonanie_ruchu(int y, int x, Mapa[][] map, Zliczanie stan);
	int zjadl_w_tej_rundzie;
	abstract void przedawkowanie (int y, int x, Mapa[][] map, int szansa_na_przedawkowanie);
}


