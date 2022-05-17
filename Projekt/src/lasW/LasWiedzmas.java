package lasW;
import java.util.*;



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
	
	abstract boolean czy_miejsce(int Rozmiar, Mapa[][] x);
	abstract void rozmnozenie(int Rozmiar, Mapa[][] map);
	
}
///////////////////////////////////////////////////////////////////////
class OwocRozkoszy extends Rozmnazalne {
	
	@Override
	boolean czy_miejsce(int rozmiar, Mapa[][] map){
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
	void rozmnozenie(int Rozmiar, Mapa[][] map) {
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
	
	OwocRozkoszy(){
		symbol="O";
	
	}
}
///////////////////////////////////////////////////////////////////////
abstract class Ruchome extends Rozmnazalne{
	abstract boolean czy_ruch(int y, int x, Mapa[][] map);
}
///////////////////////////////////////////////////////////////////////
class Zajac extends Ruchome{
	
	@Override
	boolean czy_miejsce(int rozmiar, Mapa[][] map){
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
	void rozmnozenie(int Rozmiar, Mapa[][] map) {				//TRZEBA UZUPELNIC
		
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
	
	Zajac(){
		symbol="Z";
	}
	
}
///////////////////////////////////////////////////////////////////////
class Welociraptor extends Ruchome{

	@Override
	boolean czy_miejsce(int rozmiar, Mapa[][] map){
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
	void rozmnozenie(int Rozmiar, Mapa[][] map) {				//TRZEBA UZUPELNIC
		
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
	
	Welociraptor(){
		symbol="W";
	}
}
///////////////////////////////////////////////////////////////////////

public class LasWiedzmas {
	
	public static void ustawianie_mapy(int X,Mapa[][] map) {	// USTAWIENIE WSZYSTKICH PUL NA PUSTE
		for(int i=0;i<X+2;i++) {
			for(int j=0;j<X+2;j++) {
				map[i][j]=new Puste();
			}
		}
	}
	
	public static void budowa_ogrdzenia(int X,Mapa[][] map) {	//USTAWIENIE WSZYSTKICH POL OBRZEZNYCH NA OGRODZENIE
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
		budowa_ogrdzenia(X,map);
		ustawianie_domu_wiedzmy(X,D,map);
		ustawianie_owocow(X,O,map);
		ustawianie_zajacow(X,Z,map);
		ustawianie_welociraptorow(X,W,map);
	}
	
	
	
public static void main(String[] args) {
	
	//USTAWIENIA
	int X = 50;	//ROZMIAR
	int Z = 15;	//ILOSC ZAJACOW
	int W = 5;	//ILOSC WELOCIRAPTOROW
	int O = 30;	//ILOSC KRZEWOW ROZKOSZY
	int D = 15;	//ROZMIAR DOMU WIEDZMY
	int P = 5;	//PRAWDOPODOBIENSTWO WYBUCHY DOMU WIEDZMY W %
	int PZ = 5;	//SZANSA NA PRZEDAWKOWANIE ZAJACA W %
	int PW = 5;	//SZANSA NA PRZEDAWKOWANIE WELOCIRAPTOROW W %
	int E = 5;	//ILOSC EPOK
	/////////////////////////////////////////////////////////
	
	
	Mapa[][] mapa = new Mapa[X+2][X+2];
	
	ustawienie_poczatkowe(mapa,X,Z,W,O,D);
	


	for(int i=0;i<X+2;i++) {
		for( int j=0;j<X+2;j++) {
			System.out.print(mapa[i][j].symbol+" ");
		}
		System.out.println();
	}
	
}

}
