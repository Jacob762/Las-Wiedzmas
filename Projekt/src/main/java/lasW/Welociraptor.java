package lasW;

import java.util.Random;

//KLASA Welociraptor JEST UMIEJSCOWIONA POZA KLASA Klasy ZE WZGLEDU NA ROZMIAR 

/**
 * Obiekty tej klasy sa umieszczane na siatce symulacji
 *
 */
class Welociraptor extends Ruchome{

	@Override
	protected boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map, int ilosc){
		int ile_pustych=0;
		for(int i=1;i<rozmiar+1;i++) {
			for(int j=1;j<rozmiar+1;j++) {
				if(map[i][j] instanceof Puste) {
					ile_pustych++;
				}
			}
		}
		if(ile_pustych>=ilosc) {
			return true;
		}
		else	return false;
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
		for(int i=y-1;i<=y+1;i++) {
			for(int j=x-1;j<=x+1;j++) {
				if((map[i][j] instanceof Puste || map[i][j] instanceof Wiedzma || map[i][j] instanceof OwocRozkoszy|| map[i][j] instanceof Zajac) && ilosc_ruchow>0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected	boolean czy_jedzenie(int y, int x, Mapa[][] map) {
		for(int i=y-1;i<=y+1;i++) {
			for(int j=x-1;j<=x+1;j++) {
				if(map[i][j] instanceof Zajac) {
					return true;
				}
			}
		}
		return false;
	}


	@Override
	protected void wykonanie_ruchu(int y, int x, Mapa[][] map, Zliczanie stan) {	// ANALOGICZNIE JAK DLA ZAJACA
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
						map[y][x]=new Puste();
						map[y+miejsceY][x+miejsceX] =  new NajedzonyWelociraptor();
						
						koniec=0;

					}
				}


			}
			
				else {
			
					
						miejsceY = los.nextInt(3)-1;
						miejsceX = los.nextInt(3)-1;
					
					
					
					miejsceY = los.nextInt(3)-1;
					miejsceX = los.nextInt(3)-1;
					if(map[y+miejsceY][x+miejsceX] instanceof Puste) {
						map[y][x]=new Puste();
						map[y+miejsceY][x+miejsceX] = new BuforWelociraptora();
						
						koniec=0;
					}
					else if(map[y+miejsceY][x+miejsceX] instanceof Wiedzma) {
						map[y][x]=new Puste();
				
						koniec=0;
					}
					else if(map[y+miejsceY][x+miejsceX] instanceof OwocRozkoszy) {
						map[y+miejsceY][x+miejsceX] = new BuforWelociraptora();
						map[y][x]=new Puste();
					
						koniec=0;
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
		symbol=new String(Character.toChars(0x1F432));
		ilosc_ruchow=1;
	}

} 