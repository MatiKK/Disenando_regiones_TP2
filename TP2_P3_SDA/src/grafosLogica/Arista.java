package grafosLogica;

import java.util.Comparator;
import java.util.Objects;

public class Arista<T extends Comparable<T>> {

	private T verticeInicio;
	private T verticeDestino;
	private double peso;

	public Arista(T verticeInicio, T verticeDestino, double peso) {
		this.verticeInicio = verticeInicio;
		this.verticeDestino = verticeDestino;
		this.peso = peso;
	}

	public T obtenerVerticeInicio() {
		return verticeInicio;
	}

	public T obtenerVerticeDestino() {
		return verticeDestino;
	}

	public double obtenerPeso() {
		return peso;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Arista) {
			Arista<?> a = (Arista<?>) o;
//			return sonSimilares(a);
			return sonIguales(a);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(verticeInicio) + Objects.hash(verticeDestino);
	}

	private boolean sonIguales(Arista<?> a) {
		return this.verticeInicio == a.verticeInicio
			&& this.verticeDestino == a.verticeDestino;
	}

	@SuppressWarnings("unused")
	private boolean sonSimilares(Arista<?> a) {
		return sonIguales(a) ||
		(this.verticeInicio.equals(a.verticeDestino) && this.verticeDestino.equals(a.verticeInicio));
	}

	public String toString() {
		return "(" + verticeInicio + ")--" + peso + "-->(" + verticeDestino + ")";
	}

	public static <T extends Comparable<T>> Comparator<Arista<T>> aristaComparator() {
		return new Comparator<Arista<T>>() {
			@Override
			public int compare(Arista<T> ar1, Arista<T> ar2) {
//				System.out.println(ar1 + ", " + ar2);
				int a;
				if (ar1.peso != ar2.peso) {
					a = Double.compare(ar1.peso, ar2.peso);
				}
				else if (!ar1.verticeInicio.equals(ar2.verticeInicio)) {
					a = ar1.verticeInicio.compareTo(ar2.verticeInicio);
				} else {
					a = ar1.verticeDestino.compareTo(ar2.verticeDestino);
				}
				return a;
			}
		};
	}

}
