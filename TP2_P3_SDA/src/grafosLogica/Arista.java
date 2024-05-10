package grafosLogica;

import java.util.Comparator;

public class Arista {

	private int verticeInicio;
	private int verticeDestino;
	private double peso;

	public Arista(int verticeInicio, int verticeDestino, double peso) {
		this.verticeInicio = verticeInicio;
		this.verticeDestino = verticeDestino;
		this.peso = peso;
	}

	public int obtenerVerticeInicio() {
		return verticeInicio;
	}

	public int obtenerVerticeDestino() {
		return verticeDestino;
	}

	public double obtenerPeso() {
		return peso;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Arista) {
			Arista a = (Arista) o;
//			return sonSimilares(a);
			return sonIguales(a);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return (verticeInicio + verticeDestino);
	}

	private boolean sonIguales(Arista a) {
		return this.verticeInicio == a.verticeInicio
			&& this.verticeDestino == a.verticeDestino;
	}

	private boolean sonSimilares(Arista a) {
		return sonIguales(a) ||
		(this.verticeInicio == a.verticeDestino && this.verticeDestino == a.verticeInicio);
	}

	public String toString() {
		return "(" + verticeInicio + ")--" + peso + "-->(" + verticeDestino + ")";
	}

	public static Comparator<Arista> aristaComparator() {
		return new Comparator<Arista>() {

			@Override
			public int compare(Arista ar1, Arista ar2) {
//				System.out.println(ar1 + ", " + ar2);
				int a;
				if (ar1.peso != ar2.peso) {
					a = Double.compare(ar1.peso, ar2.peso);
				}
				else if (ar1.verticeInicio != ar2.verticeInicio) {
					a = Integer.compare(ar1.verticeInicio, ar1.verticeInicio);
				} else {
					a = Integer.compare(ar1.verticeDestino, ar1.verticeDestino);
				}
				return a;
			}
		};
	}

}
