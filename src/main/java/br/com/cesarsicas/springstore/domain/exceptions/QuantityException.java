package br.com.cesarsicas.springstore.domain.exceptions;

public class QuantityException extends  Exception {

    public QuantityException(){
        super("Cart has more products than available");
    }
}
