package lasW;

import java.util.Random;

// KLASA Klasy JEST ODPOWIEDZIALNA ZA DZIEDZICZENIE

class Zliczanie{	// KLASA ODPOWIEDZIALANA ZA PRZECHOWYWANIE ILIOSCI AGENTOW

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
		symbol=new String(Character.toChars(0x1F4A5));
	}
}

class Puste extends Mapa{
	Puste(){
		symbol=new String(Character.toChars(0x1F331));
	}

}

class Wiedzma extends Mapa{
	Wiedzma(){
		symbol=new String(Character.toChars(0x1F480));
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
	abstract void przedawkowanie (int y, int x, Mapa[][] map, int szansa_na_przedawkowanie);
}


