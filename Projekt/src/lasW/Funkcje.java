package lasW;

import java.util.Random;

public class Funkcje {
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

	public static void czekaj(int X) {
        try{
            Thread.sleep(X);
        }
        catch(Exception e){} 
	}
	
	public static void przewijanie() {
	    for(int i=0; i<25; ++i)
	        System.out.println();
	    System.out.flush();
	}

	public static void wyswietlenie_mapy(Mapa[][] map, int X) {
		Zliczanie stan=new Zliczanie();
		for(int i=0;i<X+2;i++) {
			for( int j=0;j<X+2;j++) {
				System.out.print(map[i][j].symbol+" ");
			}
			System.out.println();
		}
		Funkcje.stan_aktualny(map,X,stan);
		System.out.println("Zajace	Welociraptory	Krzewy rozkoszy		Terytorium Wiedzmy");
		System.out.println(stan.zajace+"	"+stan.welociraptory+"		"+stan.krzewy_rozkoszy+"			"+stan.dom_wiedzmy+"\n");
		//System.out.println("Zjedzone zajace		Zabite zajace		Zjedzone owoce		Zdeptane krzewy		Zapite welociraptory");
		//System.out.println(stan.zjedzone_zajace+"			"+stan.zabite_zajace+"			"+stan.zjedzone_owoce+"			"+stan.zdeptane_krzewy+"			"+stan.zabite_welociraptory+"\n\n\n");
		Funkcje.czekaj(300);																												
		Funkcje.przewijanie();
	}

	
	public static void UnBuffZajaca(Mapa[][]map,int X,int co) {
		for(int i=1;i<X+1;i++) {
			for(int j=1;j<X+1;j++) {
				if(map[i][j]instanceof BuforZajaca) {
					map[i][j]=new Zajac();
				}
				else if(map[i][j]instanceof NajedzonyZajac && co!=0) {
					map[i][j]=new Zajac();
				}
			}
		}
	}
	
	public static void UnBuffWelociraptora(Mapa[][]map,int X,int co) {
		for(int i=1;i<X+1;i++) {
			for(int j=1;j<X+1;j++) {
				if(map[i][j]instanceof BuforWelociraptora && co==0) {
					map[i][j]=new Welociraptor();
				}
				else if(map[i][j]instanceof NajedzonyWelociraptor && co!=0) {
					map[i][j]=new Welociraptor();
				}
			}
		}
	}
	
	
	public static int przypadek(int y, int x,Mapa[][] map) {	// 0 - brak ograniczen, 1 - ograniczenie z gory, 2 - lewy gorny rog, 3- prawy gorny rog, 4 - przy lewej scianie, 5 - lewy dolny rog
		int przypadek = 0;										// 6 - przy prawej scianie, 7 - prawy dolny rog, 8 - dolna krawedz
		int skraj = map.length-1;
		
		if(y==1) {
			przypadek = 1;
				if(x==1) {
					przypadek = 2;
				}
				else if(x==skraj) {
					przypadek = 3;
				}
		}
		else if(x==1) {
			przypadek = 4;
				if(y==skraj) {
					przypadek = 5;
				}
		}
		
		else if (x==skraj) {
			przypadek=6;
				if(y==skraj) {
					przypadek=7;
				}
		}
		else if(y==skraj) {
			przypadek=8;
		}
		
		return przypadek;
	}
	
	
	
	
	
	
	
//////////////////////////////////////////////////////////////////////////////TO BEDZIE W INTERFACE ! PO SKONCZENIU USUNAC
public static void epoka(Mapa[][] map, int X, int O, int P, int PZ, int PW,int ZM) {
Welociraptor WEL = new Welociraptor();
Zajac ZAJ = new Zajac();
OwocRozkoszy OWO= new OwocRozkoszy(); 
Zliczanie stan = new Zliczanie();
Wiedzma WIE = new Wiedzma();
Funkcje.stan_aktualny(map,X,stan);

WIE.czy_wybuch(X, map, P);	// WYBUCH

for(int i=1;i<X+2;i++) {		//PRZEDAWKOWANIA
	for(int j=1;j<X+2;j++) {
		if(map[i][j]instanceof Zajac) {
			ZAJ.przedawkowanie(i, j, map, PZ);
		}
		else if(map[i][j]instanceof Welociraptor) {
			WEL.przedawkowanie(i, j, map, PW);
		}
	}
}

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
UnBuffZajaca(map,X,0);
UnBuffWelociraptora(map,X,0);

for(int i=1;i<X+2;i++) {
	for(int j=1;j<X+2;j++) {
		if(map[i][j]instanceof Welociraptor) {
			WEL.wykonanie_ruchu(i, j, map, stan);
		}
	}	
}
UnBuffWelociraptora(map,X,0);


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
	UnBuffZajaca(map,X,1);
	UnBuffWelociraptora(map,X,1);
	int ilosc_owocow_do_rozmnozenia = O-stan.krzewy_rozkoszy;
	OWO.rozmnozenie(X, map, 1, 1, ilosc_owocow_do_rozmnozenia);
	
}

	
}
	