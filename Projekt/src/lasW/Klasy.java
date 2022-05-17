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
///////////////////////////////////////////////////////////////////////
class Ogrodzenie extends Mapa{

	Ogrodzenie(){
		symbol="*";
	}
}
///////////////////////////////////////////////////////////////////////
class Puste extends Mapa{
	Puste(){
		symbol="P";
	}

}
///////////////////////////////////////////////////////////////////////
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
///////////////////////////////////////////////////////////////////////
abstract class Rozmnazalne extends Mapa{
	abstract boolean czy_miejsce_na_rozmnozenie(int Rozmiar, Mapa[][] x);

	abstract void rozmnozenie(int Rozmiar, Mapa[][] map, int y, int x, int ilosc);

}
///////////////////////////////////////////////////////////////////////
class OwocRozkoszy extends Rozmnazalne {
	int ilosc_poczatkowa;
	@Override
	protected boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map){
		for(int i=1;i<rozmiar+1;i++) {
			for(int j=1;j<rozmiar+1;j++) {
				if(map[i][j] instanceof Puste) {
					return true;
				}
			}
		}
		return false;
	}



	@Override
	protected void rozmnozenie(int Rozmiar, Mapa[][] map ,int y, int x, int ilosc) {	// ILOSC TO ROZNICA MIEDZY POCZATKOWA ILOSCIA KRZEWOW A AKTUALNA
		if(czy_miejsce_na_rozmnozenie(Rozmiar,map)) {
		for(int i=0;i<ilosc;i++) {
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
		int koniec=-1;

		while(koniec!=0) {
			 miejsceY = los.nextInt(Rozmiar)+1;
			 miejsceX = los.nextInt(Rozmiar)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new OwocRozkoszy(); 
			 koniec=0;
		}
	}
	}
	}
	}
	OwocRozkoszy(){
		symbol="O";
		ilosc_poczatkowa=2;
	}
}
///////////////////////////////////////////////////////////////////////
abstract class Ruchome extends Rozmnazalne{
	protected int ilosc_ruchow;
	abstract boolean czy_ruch(int y, int x, Mapa[][] map);
	abstract boolean czy_jedzenie(int y, int x, Mapa[][] map);
	abstract void wykonanie_ruchu(int y, int x, Mapa[][] map);
	int zjadl_w_tej_rundzie;
	abstract void przedawkowanie (int y, int x, Mapa[][] map, int szansa_na_przedawkowanie);
}
///////////////////////////////////////////////////////////////////////
class Zajac extends Ruchome{

	@Override
	protected boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map){
		for(int i=1;i<rozmiar+1;i++) {
			for(int j=1;j<rozmiar+1;j++) {
				if(map[i][j] instanceof Puste) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void rozmnozenie(int Rozmiar, Mapa[][] map ,int y, int x,int ilosc) {	 // ILOSC TO LICZBA MLODYCH W MIOCIE (NA RAZIE 1, NIE MA W PLANACH ZMIANY, ALE KTO WIE) 
			
		for(int i=0;i<ilosc;i++) {
				if(czy_miejsce_na_rozmnozenie(Rozmiar,map)) {
				int miejsceY=-100;
				int miejsceX=-100;
				Random los = new Random();
				int koniec=-1;
				double szansa = los.nextDouble();
			
				if(szansa>0.5) {
				while(koniec!=0) {
					 miejsceY = los.nextInt(Rozmiar)+1;
					 miejsceX = los.nextInt(Rozmiar)+1;
					 if( map[miejsceY][miejsceX]instanceof Puste) {
					 map[miejsceY][miejsceX]=new Zajac(); 
					 koniec=0;
				}
			}
			}
			}
		}
	}


	@Override
	 boolean czy_ruch(int y, int x, Mapa[][] map) {
		for(int i=y-1;i<=y+1;y++) {
			for(int j=x-1;j<=x+1;j++) {
				if(map[i][j] instanceof Puste || map[i][j] instanceof Wiedzma || map[i][j] instanceof OwocRozkoszy) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	boolean czy_jedzenie(int y, int x, Mapa[][] map) {
		for(int i=y-1;i<=y+1;y++) {
			for(int j=x-1;j<=x+1;j++) {
				if(map[i][j] instanceof OwocRozkoszy) {
					return true;
				}
			}
		}
		return false;
	}




	@Override
	protected void wykonanie_ruchu(int y, int x, Mapa[][] map) { 
		for(int i=0;i<ilosc_ruchow;i++) {
		if(czy_ruch(y,x,map)) {
			int miejsceY=-100;
			int miejsceX=-100;
			Random los = new Random();
			int koniec=-1;
			if(czy_jedzenie(y,x,map)) {												//JEZELI JEST JEDZENIE W POBLIZU TO POJDZIE DO JEDZENIA
																					//(JAK JEST KILKA TO DO LOSOWEGO)
				while(koniec!=0) {
					miejsceY = los.nextInt(3)-1;
					miejsceX = los.nextInt(3)-1;
					if(map[y+miejsceY][x+miejsceX] instanceof OwocRozkoszy) {
						map[y+miejsceY][x+miejsceX] = new Zajac();
						map[y][x]=new Puste();
						koniec=0;
						zjadl_w_tej_rundzie++;
					}
				}


			}
			else {																	//JAK NIE MA JEDZENIA TO IDZIE W LOSOWE
				while(koniec!=0) {													// SA 2 MOZLIWOSCI - PUSTE ALBO WIEDZMA
					miejsceY = los.nextInt(3)-1;
					miejsceX = los.nextInt(3)-1;
					if(map[y+miejsceY][x+miejsceX] instanceof Puste) {
						map[y+miejsceY][x+miejsceX] = new Zajac();
						map[y][x]=new Puste();
						koniec=0;
					}
					else if(map[y+miejsceY][x+miejsceX] instanceof Wiedzma) {
						map[y][x]=new Puste();
						koniec=0;
					}
				}

			}


		}

	}
	}

	
	@Override
	protected void przedawkowanie (int y, int x, Mapa[][] map, int szansa_na_przedawkowanie) {
		Random los = new Random();
		int czy=los.nextInt(100);
		if(czy<szansa_na_przedawkowanie) {
			map[y][x]=new Puste();
		}
	}
	
	
	
	
	
	Zajac(){
		symbol="Z";
		ilosc_ruchow=1;
	}

}
///////////////////////////////////////////////////////////////////////
class Welociraptor extends Ruchome{

	@Override
	protected boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map){
		for(int i=1;i<rozmiar+1;i++) {
			for(int j=1;j<rozmiar+1;j++) {
				if(map[i][j] instanceof Puste) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void rozmnozenie(int Rozmiar, Mapa[][] map ,int y, int x,int ilosc) {	// ILOSC TO LICZBA MLODYCH W MIOCIE (NA RAZIE 1, NIE MA W PLANACH ZMIANY, ALE KTO WIE) 

		for(int q=0;q<ilosc;q++) {
		boolean czy_moze_rozmnozyc_obok_siebie = false;		
		for(int i=-1;i<=1;i++) {
			for(int j=-1;j<=1;j++) {
				if(map[y+i][x+j] instanceof Puste) {		//SPRAWDZENIE CZY MOZE ROZMNOZYC SIE OBOK SIEBIE
					czy_moze_rozmnozyc_obok_siebie = true;
				}
			}
		}
		int koniec=-1;
		if(czy_moze_rozmnozyc_obok_siebie) {				//JEZELI MOZE SIE ROZMNOZYC OBOK SIEBIE TO TO ZROBI	
			int miejsceY=-100;
			int miejsceX=-100;
			Random los = new Random();

			while(koniec!=0) {
				miejsceY = los.nextInt(3)-1;
				miejsceX = los.nextInt(3)-1;
				if(map[y+miejsceY][x+miejsceX] instanceof Puste) {
					map[y+miejsceY][x+miejsceX] = new Welociraptor();
					koniec=0;
				}
			}


		}

		else {											//JAK NIE MOZE SIE ROZMNOZYC OBOK SIEBIE TO SIE ROZMNOZY W LOSOWYM MIEJSCU

				int miejsceY=-100;
				int miejsceX=-100;
				Random los = new Random();
				while(koniec!=0) {
					 miejsceY = los.nextInt(Rozmiar)+1;
					 miejsceX = los.nextInt(Rozmiar)+1;
					 if( map[miejsceY][miejsceX]instanceof Puste) {
					 map[miejsceY][miejsceX]=new Welociraptor(); 
					 koniec=0;
				}



			}


		}

		}
	}


	@Override
	protected boolean czy_ruch(int y, int x, Mapa[][] map) {
		for(int i=y-1;i<=y+1;y++) {
			for(int j=x-1;j<=x+1;j++) {
				if(map[i][j] instanceof Puste || map[i][j] instanceof Wiedzma || map[i][j] instanceof OwocRozkoszy) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected	boolean czy_jedzenie(int y, int x, Mapa[][] map) {
		for(int i=y-1;i<=y+1;y++) {
			for(int j=x-1;j<=x+1;j++) {
				if(map[i][j] instanceof Zajac) {
					return true;
				}
			}
		}
		return false;
	}


	@Override
	protected void wykonanie_ruchu(int y, int x, Mapa[][] map) {	// ANALOGICZNIE JAK DLA ZAJACA
for(int i=0;i<ilosc_ruchow;i++) {
		if(czy_ruch(y,x,map)) {
			int miejsceY=-100;
			int miejsceX=-100;
			Random los = new Random();
			int koniec=-1;
			if(czy_jedzenie(y,x,map)) {
				while(koniec!=0) {
					miejsceY = los.nextInt(3)-1;
					miejsceX = los.nextInt(3)-1;
					if(map[y+miejsceY][x+miejsceX] instanceof Zajac) {
						map[y+miejsceY][x+miejsceX] = new Welociraptor();
						map[y][x]=new Puste();
						koniec=0;
						zjadl_w_tej_rundzie++;
					}
				}


			}
			else {
				while(koniec!=0) {
					miejsceY = los.nextInt(3)-1;
					miejsceX = los.nextInt(3)-1;
					if(map[y+miejsceY][x+miejsceX] instanceof Puste) {
						map[y+miejsceY][x+miejsceX] = new Welociraptor();
						map[y][x]=new Puste();
						koniec=0;
					}
					else if(map[y+miejsceY][x+miejsceX] instanceof Wiedzma) {
						map[y][x]=new Puste();
						koniec=0;
					}
				}

			}


		}
	}
	}
	
	@Override
	protected void przedawkowanie (int y, int x, Mapa[][] map, int szansa_na_przedawkowanie) {
		Random los = new Random();
		int czy=los.nextInt(100);
		if(czy<szansa_na_przedawkowanie) {
			map[y][x]=new Puste();
		}
	}
	
	
	Welociraptor(){
		symbol="W";
		ilosc_ruchow=2;
	}
} 