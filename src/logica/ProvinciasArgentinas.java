package logica;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class ProvinciasArgentinas {
	private static final Provincia buenosAires = new Provincia("Buenos Aires", new Coordinate(-36.5, -60.5));
	// En el enunciado se incluye CABA. Para fines prácticos, se la considera provincia
	private static final Provincia CABA = new Provincia("Ciudad Autónoma de Buenos Aires", new Coordinate(-34.6037, -58.3816));
	private static final Provincia catamarca = new Provincia("Catamarca", new Coordinate(-27.25, -67.25));
	private static final Provincia chaco = new Provincia("Chaco", new Coordinate(-26.5, -60.75));
	private static final Provincia chubut = new Provincia("Chubut", new Coordinate(-44, -68.5));
	private static final Provincia cordoba = new Provincia("Córdoba", new Coordinate(-32, -63.8));
	private static final Provincia corrientes = new Provincia("Corrientes", new Coordinate(-28.75, -57.9));
	private static final Provincia entreRios = new Provincia("Entre Ríos", new Coordinate(-32, -59.35));
	private static final Provincia formosa = new Provincia("Formosa", new Coordinate(-24.75, -60));
	private static final Provincia jujuy = new Provincia("Jujuy", new Coordinate(-23, -66));
	private static final Provincia laRioja = new Provincia("La Rioja", new Coordinate(-29.4131, -67.25));
	private static final Provincia laPampa = new Provincia("La Pampa", new Coordinate(-37.25, -65.5));
	private static final Provincia mendoza = new Provincia("Mendoza", new Coordinate(-34.5, -68.5));
	private static final Provincia misiones = new Provincia("Misiones", new Coordinate(-27, -54.75));
	private static final Provincia neuquen = new Provincia("Neuquén", new Coordinate(-38.5, -70));
	private static final Provincia rioNegro = new Provincia("Río Negro", new Coordinate(-40.25, -67.25));
	private static final Provincia salta = new Provincia("Salta", new Coordinate(-25.25, -64.75));
	private static final Provincia sanJuan = new Provincia("San Juan", new Coordinate(-30.75, -68.8));
	private static final Provincia sanLuis = new Provincia("San Luis", new Coordinate(-33.75, -66.1));
	private static final Provincia santaCruz = new Provincia("Santa Cruz", new Coordinate(-48.6226, -70));
	private static final Provincia santaFe = new Provincia("Santa Fe", new Coordinate(-30.6107, -61));
	private static final Provincia santiagoDelEstero = new Provincia("Santiago del Estero", new Coordinate(-27.7951, -63.5));
	private static final Provincia tierraDelFuego = new Provincia("Tierra del Fuego", new Coordinate(-54.25, -67.7));
	private static final Provincia tucuman = new Provincia("Tucumán", new Coordinate(-26.9, -65.4));

	private static boolean seAgregaronLimitrofes = false;

	public static Provincia[] provinciasDeArgentina() {
		if (!seAgregaronLimitrofes)
			agregarLimitrofes();
		return new Provincia[] {
				buenosAires, CABA, catamarca, chaco, chubut, cordoba, corrientes, entreRios,
				formosa, jujuy, laRioja, laPampa, mendoza, misiones, neuquen, rioNegro, salta,
				sanJuan, sanLuis, santaCruz, santaFe, santiagoDelEstero, tierraDelFuego, tucuman
		};
	}

	private static void agregarLimitrofes() {
		buenosAires.agregarLimitrofes(CABA,cordoba,entreRios,laPampa,rioNegro,santaFe);
		CABA.agregarLimitrofes(buenosAires);
		catamarca.agregarLimitrofes(cordoba,laRioja,salta,santiagoDelEstero,tucuman);
		chaco.agregarLimitrofes(corrientes,formosa,salta,santaFe,santiagoDelEstero);
		chubut.agregarLimitrofes(rioNegro,santaCruz);
		cordoba.agregarLimitrofes(buenosAires,catamarca,laPampa,laRioja,sanLuis,santaFe,santiagoDelEstero);
		corrientes.agregarLimitrofes(chaco,entreRios,misiones, santaFe);
		entreRios.agregarLimitrofes(buenosAires,corrientes,santaFe);
		formosa.agregarLimitrofes(chaco, salta);
		jujuy.agregarLimitrofes(salta);
		laRioja.agregarLimitrofes(catamarca, cordoba,sanJuan,sanLuis);
		laPampa.agregarLimitrofes(buenosAires,cordoba,mendoza,rioNegro,sanLuis);
		mendoza.agregarLimitrofes(laPampa,neuquen,sanJuan,sanLuis);
		misiones.agregarLimitrofes(corrientes);
		neuquen.agregarLimitrofes(mendoza,rioNegro);
		rioNegro.agregarLimitrofes(buenosAires,chubut,laPampa, neuquen);
		salta.agregarLimitrofes(catamarca,chaco,formosa,jujuy,santiagoDelEstero,tucuman);
		sanJuan.agregarLimitrofes(laRioja,mendoza,sanLuis);
		sanLuis.agregarLimitrofes(cordoba,laPampa,laRioja,mendoza,sanJuan);
		santaCruz.agregarLimitrofes(chubut, tierraDelFuego);
		santaFe.agregarLimitrofes(buenosAires,chaco,cordoba,corrientes,entreRios,santiagoDelEstero);
		santiagoDelEstero.agregarLimitrofes(catamarca,chaco,cordoba,salta,santaFe,tucuman);
		tierraDelFuego.agregarLimitrofes(santaCruz);
		tucuman.agregarLimitrofes(catamarca,salta,santiagoDelEstero);
		seAgregaronLimitrofes = true;
	}

}
