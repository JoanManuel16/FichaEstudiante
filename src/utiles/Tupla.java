package utiles;


public class Tupla<T,K> {
private T N1;
private K N2;
public Tupla(T x1,K x2) {
	N1 = x1;
	N2 = x2;
}

public T getN1() {
	return N1;
}

public K getN2() {
	return N2;
}

public void setN1(T elem) {
	N1 = elem;
}

public void setN2(K elem) {
	N2 = elem;
}

}
