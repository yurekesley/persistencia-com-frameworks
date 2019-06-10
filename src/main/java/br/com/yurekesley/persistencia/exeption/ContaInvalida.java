package br.com.yurekesley.persistencia.exeption;

public class ContaInvalida extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public ContaInvalida(String msg) {
        super(msg);
    }
}