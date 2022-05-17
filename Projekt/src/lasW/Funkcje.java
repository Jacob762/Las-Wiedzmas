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
		System.out.println(stan.zajace+"	"+stan.welociraptory+"		"+stan.krzewy_rozkoszy+"			"+stan.dom_wiedzmy+"\n\n\n");
		//Funkcje.czekaj(1500);																												// ODKOMENTOWAC DLA SYMULAJCI
		Funkcje.przewijanie();
	}

//////////////////////////////////////////////////////////////////////////////TO BEDZIE W INTERFACE ! PO SKONCZENIU USUNAC
public static void epoka(Mapa[][] map, int X, int O, int P, int PZ, int PW) {
Welociraptor WEL = new Welociraptor();
Zajac ZAJ = new Zajac();
OwocRozkoszy OWO= new OwocRozkoszy(); 
Zliczanie stan = new Zliczanie();
Wiedzma WIE = new Wiedzma();
Funkcje.stan_aktualny(map,X,stan);

/*
int ilosc_owocow_do_rozmnozenia = O-stan.krzewy_rozkoszy;
OWO.rozmnozenie(X, map, 1, 1, ilosc_owocow_do_rozmnozenia);


/////////////////// NA BRUDNO ROZMNAZANIE ZAJACA - DZIALA

for(int i=1;i<X+2;i++) {
for(int j=1;j<X+2;j++) {
if(map[i][j]instanceof Zajac) {
ZAJ.rozmnozenie(X, map, i, j, 1);
}
}
}

//////////////////////	NA BRUDNO ROZMNAZANIE WELOCIRAPTORA - DZIALA
for(int i=1;i<X+2;i++) {
for(int j=1;j<X+2;j++) {
if(map[i][j]instanceof Welociraptor) {
WEL.rozmnozenie(X, map, i, j, 1);
}
}
}

/////////////////////// WYBUCH WIEDZMY NA BRUDNO - DZIALA
P=100;
WIE.czy_wybuch(X, map, P);

Funkcje.wyswietlenie_mapy(map, X);

///////////////////// NA BRUDNO PRZEDAWKOWANIE ZAJACA
PZ=50;
PW=50;
	for(int i=1;i<X+2;i++) {
		for(int j=1;j<X+2;j++) {
			if(map[i][j]instanceof Zajac) {
				ZAJ.przedawkowanie(i, j, map, PZ);
			}
			else if(map[i][j]instanceof Welociraptor) {
				WEL.przedawkowanie(i, j, map, PW);
			}
		}
	}



		*/	
/// NA BRUDNO RUCH 			

for(int i=1;i<X+2;i++) {
	for(int j=1;j<X+2;j++) {
		if(map[i][j]instanceof Zajac) {
			ZAJ.wykonanie_ruchu(i, j, map);;
		}
		else if(map[i][j]instanceof Welociraptor) {
			
			WEL.wykonanie_ruchu(i, j, map);
		}
	}	
	
}
Funkcje.wyswietlenie_mapy(map, X);


}

}