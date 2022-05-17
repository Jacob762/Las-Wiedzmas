package lasW;
import java.util.*;

class Zliczanie{
	int zajace;
	int welociraptory;
	int krzewy_rozkoszy;
	int dom_wiedzmy;
	
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
}
///////////////////////////////////////////////////////////////////////
abstract class Rozmnazalne extends Mapa{
	abstract boolean czy_miejsce_na_rozmnozenie(int Rozmiar, Mapa[][] x);

	abstract public void rozmnozenie(int Rozmiar, Mapa[][] map, int y, int x, int ilosc);
	
}
///////////////////////////////////////////////////////////////////////
class OwocRozkoszy extends Rozmnazalne {
	int ilosc_poczatkowa;
	@Override
	boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map){
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
	public void rozmnozenie(int Rozmiar, Mapa[][] map ,int y, int x, int ilosc) {	// ILOSC TO ROZNICA MIEDZY POCZATKOWA ILOSCIA KRZEWOW A AKTUALNA
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
}
///////////////////////////////////////////////////////////////////////
class Zajac extends Ruchome{
	
	@Override
	boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map){
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
		
		int ile_rozmnazac=-1;
			for(int i=0;i<ile_rozmnazac;i++) {
				int miejsceY=-100;
				int miejsceX=-100;
				Random los = new Random();
				int koniec=-1;
				
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
	void wykonanie_ruchu(int y, int x, Mapa[][] map) { 
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
	
	Zajac(){
		symbol="Z";
		ilosc_ruchow=1;
	}
	
}
///////////////////////////////////////////////////////////////////////
class Welociraptor extends Ruchome{

	@Override
	boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map){
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
	public void rozmnozenie(int Rozmiar, Mapa[][] map ,int y, int x,int ilosc) {	// ILOSC TO LICZBA MLODYCH W MIOCIE (NA RAZIE 1, NIE MA W PLANACH ZMIANY, ALE KTO WIE) 
			
		int ile_rozmnazac=-1;
		for(int q=0;q<ile_rozmnazac;q++) {
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
				if(map[i][j] instanceof Zajac) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	@Override
	void wykonanie_ruchu(int y, int x, Mapa[][] map) {	// ANALOGICZNIE JAK DLA ZAJACA
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
	Welociraptor(){
		symbol="W";
		ilosc_ruchow=2;
	}
}

///////////////////////////////////////////////////////////////////////

public class LasWiedzmas {
	
	public static void sprawdzenie_ustawien(int X,int Z,int W,int O,int D,int P, int PZ, int PW, int E) {
		if(X<10 || X>1000) {
			System.out.println("Niepoprawna wartosc X: "+X);
			 System.exit(0);
		}
		
		if(Z<0 || Z>(X*X)/2) {
			System.out.println("Niepoprawna wartosc Z: "+Z);
			 System.exit(0);
		}
		if(W<0 || W>(X*X)/2) {
			System.out.println("Niepoprawna wartosc W: "+W);
			 System.exit(0);
		}
		if(O<0 || O>X) {
			System.out.println("Niepoprawna wartosc O: "+O);
			 System.exit(0);
		}
		if(D<0 || D>X/2) {
			System.out.println("Niepoprawna wartosc D: "+D);
			 System.exit(0);
		}
		if(P<0 || P>10) {
			System.out.println("Niepoprawna wartosc P: "+P);
			 System.exit(0);
		}
		if(PZ<0 || PZ>20) {
			System.out.println("Niepoprawna wartosc PZ: "+PZ);
			 System.exit(0);
		}
		if(PW<0 || PW>20) {
			System.out.println("Niepoprawna wartosc PW: "+PW);
			 System.exit(0);
		}
		if(E<0 || E>10000) {
			System.out.println("Niepoprawna wartosc E: "+E);
			 System.exit(0);
		}
	}
	
	
	public static void ustawianie_mapy(int X,Mapa[][] map) {	// USTAWIENIE WSZYSTKICH PUL NA PUSTE
		for(int i=0;i<X+2;i++) {
			for(int j=0;j<X+2;j++) {
				map[i][j]=new Puste();
			}
		}
	}
	
	public static void budowa_ogrodzenia(int X,Mapa[][] map) {	//USTAWIENIE WSZYSTKICH POL OBRZEZNYCH NA OGRODZENIE
		for(int k=0;k<X+2;k+=X+1) {
			for(int i=0;i<X+2;i++) {
			map[k][i]=new Ogrodzenie();
		}
	}
		
		for(int k=0;k<X+2;k+=X+1) {
			for(int i=1;i<X+2;i++) {
			map[i][k]=new Ogrodzenie();
		}
	}
	}

	public static void ustawianie_domu_wiedzmy (int X,int D,Mapa[][] map) {	//USTAWIENIE DOMU WIEDZMY W LOSOWYM MIEJSCU
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
		if(D==1) {
		 miejsceY = los.nextInt(X)+1;
		 miejsceX = los.nextInt(X)+1;
	}
		if(D>1 && D<X/2) {
			 miejsceY = los.nextInt(X-D+1)+1;
			 miejsceX = los.nextInt(X-D+1)+1;
		}
		
		
		for(int i=0;i<D;i++) {
			for(int j=0;j<D;j++) {
				map[miejsceY+i][miejsceX+j] = new Wiedzma();
			}
		}
	}
	
	public static void ustawianie_owocow (int X,int O,Mapa[][] map) {	//USTAWIENIE KRZEWOW ROZKOSZY W LOSOWYM MIEJSCU
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
	
		for(int i=0;i<O;) {
			 miejsceY = los.nextInt(X)+1;
			 miejsceX = los.nextInt(X)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new OwocRozkoszy();
			 i++;
			 }
		}
		
		
	}
	
	public static void ustawianie_zajacow (int X,int Z,Mapa[][] map) {	//USTAWIENIE ZAJACOW W LOSOWYM MIEJSCU
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
	
		for(int i=0;i<Z;) {
			 miejsceY = los.nextInt(X)+1;
			 miejsceX = los.nextInt(X)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new Zajac();
			 i++;
			 }
		}
	}
	
	
	public static void ustawianie_welociraptorow (int X,int Z,Mapa[][] map) {	//USTAWIENIE WELOCIRAPTOROW W LOSOWYM MIEJSCU
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
	
		for(int i=0;i<Z;) {
			 miejsceY = los.nextInt(X)+1;
			 miejsceX = los.nextInt(X)+1;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new Welociraptor();
			 i++;
			 }
		}
	}
	
	
	
	public static void ustawienie_poczatkowe(Mapa[][] map,int X,int Z,int W,int O,int D) {

		ustawianie_mapy(X,map);
		budowa_ogrodzenia(X,map);
		ustawianie_domu_wiedzmy(X,D,map);
		ustawianie_owocow(X,O,map);
		ustawianie_zajacow(X,Z,map);
		ustawianie_welociraptorow(X,W,map);
	}
	
	
	public static void stan_aktualny(Mapa[][] map, int X, Zliczanie ilosc) {
		for(int i=0;i<X+2;i++) {
			for(int j=0;j<X+2;j++) {
				if(map[i][j] instanceof Zajac) {
					ilosc.zajace++;
				}
				else if (map[i][j] instanceof Welociraptor) {
					ilosc.welociraptory++;
				}
				else if (map[i][j] instanceof OwocRozkoszy) {
					ilosc.krzewy_rozkoszy++;
				}
				else if (map[i][j] instanceof Wiedzma) {
					ilosc.dom_wiedzmy++;
				}
			}
		}
	}
	

	
	
	
public static void main(String[] args) {
	
	Zliczanie stan=new Zliczanie();
	
	//USTAWIENIA
	int X = 30;	//ROZMIAR
	int Z = 5;	//ILOSC ZAJACOW
	int W = 5;	//ILOSC WELOCIRAPTOROW
	int O = 5;	//ILOSC KRZEWOW ROZKOSZY
	int D = 5;	//ROZMIAR DOMU WIEDZMY
	int P = 5;	//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	int PZ = 5;	//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	int PW = 5;	//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	int E = 5;	//ILOSC EPOK
	/////////////////////////////////////////////////////////
	
	sprawdzenie_ustawien(X,Z,W,O,D,P,PZ,PW,E);
	
	Mapa[][] mapa = new Mapa[X+2][X+2];
	
	ustawienie_poczatkowe(mapa,X,Z,W,O,D);
	


	for(int i=0;i<X+2;i++) {
		for( int j=0;j<X+2;j++) {
			System.out.print(mapa[i][j].symbol+" ");
		}
		System.out.println();
	}
	stan_aktualny(mapa,X,stan);
	System.out.println("Zajace	Welociraptory	Krzewy rozkoszy		Terytorium Wiedzmy");
	System.out.println(stan.zajace+"	"+stan.welociraptory+"		"+stan.krzewy_rozkoszy+"			"+stan.dom_wiedzmy+"\n\n\n");
	

	 
}

}
