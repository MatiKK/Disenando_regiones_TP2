package grafosLogica;

import java.util.Comparator;
import java.util.Objects;

public class Arista<T extends Comparable<T>> implements Comparable<Arista<T>>{

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
			return sonSimilares(a);
//			return sonIguales(a);
		}

		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(verticeInicio) + Objects.hash(verticeDestino);
	}

	private boolean sonIguales(Arista<?> a) {
		return this.verticeInicio.equals(a.verticeInicio)
			&& this.verticeDestino.equals(a.verticeDestino);
	}

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
				return ar1.compareTo(ar2);
			}
		};
	}

	public int compareTo(Arista<T> ar) {
		int a;
		if (this.peso != ar.peso) {
			a = Double.compare(this.peso, ar.peso);
		}
		else if (!this.verticeInicio.equals(ar.verticeInicio)) {
			a = this.verticeInicio.compareTo(ar.verticeInicio);
		} else {
			a = this.verticeDestino.compareTo(ar.verticeDestino);
		}
		return a;
	}

	public void cambiarPeso(double nuevoPeso) {
		peso = nuevoPeso;
	}

}
