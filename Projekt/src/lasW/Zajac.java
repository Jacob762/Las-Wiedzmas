package lasW;

import java.util.Random;

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
		for(int i=y-1;i<=y+1;i++) {
			for(int j=x-1;j<=x+1;j++) {
				if((map[i][j] instanceof Puste || map[i][j] instanceof Wiedzma || map[i][j] instanceof OwocRozkoszy)&& ilosc_ruchow>0) {
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
	protected void wykonanie_ruchu(int y, int x, Mapa[][] map, Zliczanie stan) {

		for(int i=0;i<ilosc_ruchow;i++) {
		if(czy_ruch(y,x,map)) {
	
			int miejsceY=-100;
			int miejsceX=-100;
			Random los = new Random();
			int koniec=-1;
			map[y][x]=new Puste();
			/*	if(czy_jedzenie(y,x,map)) {												//JEZELI JEST JEDZENIE W POBLIZU TO POJDZIE DO JEDZENIA
																		     	//(JAK JEST KILKA TO DO LOSOWEGO)
				while(koniec!=0) {
					miejsceY = los.nextInt(3)-1;										
					miejsceX = los.nextInt(3)-1;
					if(map[y+miejsceY][x+miejsceX] instanceof OwocRozkoszy) {			//DO POPRAWY
						map[y+miejsceY][x+miejsceX] = new BuforZajaca();
						map[y][x]=new Puste();
						koniec=0;
						zjadl_w_tej_rundzie++;
					}
				}


			}																//JAK NIE MA JEDZENIA TO IDZIE W LOSOWE
			 else*/ {														// SA 2 MOZLIWOSCI - PUSTE ALBO WIEDZMA
								
				
				 
				while(koniec!=0) {													
					
					int przypadek=Funkcje.przypadek(y, x, map);
					
					switch(przypadek){
					case 0:
						miejsceY = los.nextInt(3)-1;
						miejsceX = los.nextInt(3)-1;
						break;
						
					case 1:
						miejsceY = los.nextInt(2);
						miejsceX = los.nextInt(3)-1;
						
						break;
						
					case 2:
						miejsceY = 1-los.nextInt(2);
						miejsceX = los.nextInt(2);
						
						break;
						
					case 3:
						miejsceY = 1-los.nextInt(2);
						miejsceX = -1+los.nextInt(2);
						
						break;
						
					case 4:
						miejsceY = los.nextInt(3)-1;
						miejsceX = los.nextInt(2);
						break;
					case 5:
						miejsceY = -1+los.nextInt(2);
						miejsceX = los.nextInt(2);
						
						break;
					case 6:
						miejsceY = los.nextInt(3)-1;
						miejsceX = -1+los.nextInt(2);
						
						break;
					case 7:
						miejsceY = -1+los.nextInt(2);
						miejsceX = -1+los.nextInt(2);
						break;
					case 8:
						miejsceY = -1+los.nextInt(2);
						miejsceX = los.nextInt(3)-1;
						break;
				}
				
					
					
					if(miejsceY!=0 || miejsceX!=0) {
					if(map[y+miejsceY][x+miejsceX] instanceof Puste) {
						map[y+miejsceY][x+miejsceX] = new BuforZajaca();
						map[y][x]=new Puste();
						koniec=0;
					}
					else if(map[y+miejsceY][x+miejsceX] instanceof Wiedzma) {
						map[y][x]=new Puste();
						stan.zabite_zajace++;
						koniec=0;
					}
					else if (map[y+miejsceY][x+miejsceX] instanceof OwocRozkoszy) {
						map[y+miejsceY][x+miejsceX]=new BuforZajaca();
						map[y][x]=new Puste();
						stan.zjedzone_owoce++;
						koniec=0;
					}
					
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