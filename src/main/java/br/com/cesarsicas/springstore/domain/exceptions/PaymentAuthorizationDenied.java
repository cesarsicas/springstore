package br.com.cesarsicas.springstore.domain.exceptions;

public class PaymentAuthorizationDenied extends  Exception{

    public PaymentAuthorizationDenied(){
        super("Payment not authorized");
    }
}
