package lasW;

import java.util.Random;

class OwocRozkoszy extends Rozmnazalne {
	
	protected int ile_wolnych(int rozmiar, Mapa[][] map) {
		int wolne=0;
		for(int i=1;i<rozmiar+1;i++) {
			for(int j=1;j<rozmiar+1;j++) {
				if(map[i][j] instanceof Puste) {
					wolne++;
				}
			}
	}
		return wolne;
	}
	@Override
	protected boolean czy_miejsce_na_rozmnozenie(int rozmiar, Mapa[][] map, int ile){
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
	
		for(int i=0;i<ilosc;i++) {
			if(czy_miejsce_na_rozmnozenie(Rozmiar,map,1)) {
				if(ile_wolnych(Rozmiar,map)<ilosc) {
					for(int q=1;q<Rozmiar+1;q++) {
						for(int w=1;w<Rozmiar+1;w++) {
							if(map[q][w]instanceof Puste) {
								map[q][w]=new OwocRozkoszy();
							}
						}
					}
				}
				else {
		int miejsceY=-100;
		int miejsceX=-100;
		Random los = new Random();
		int koniec=-1;

		while(koniec!=0) {
			 miejsceY = los.nextInt(Rozmiar)+2;
			 miejsceX = los.nextInt(Rozmiar)+2;
			 if( map[miejsceY][miejsceX]instanceof Puste) {
			 map[miejsceY][miejsceX]=new OwocRozkoszy(); 
			 koniec=0;
		}
	}
				}
	}
	}
	}
	OwocRozkoszy(){
		symbol=new String(Character.toChars(0x1F351));
		
	}
}