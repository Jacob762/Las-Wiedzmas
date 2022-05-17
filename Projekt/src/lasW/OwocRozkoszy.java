package lasW;

import java.util.Random;

class OwocRozkoszy extends Rozmnazalne {
	
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
		
	}
}