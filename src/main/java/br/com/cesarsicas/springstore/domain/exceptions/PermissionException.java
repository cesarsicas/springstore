package br.com.cesarsicas.springstore.domain.exceptions;

public class PermissionException extends  Exception{

    public PermissionException(){
        super("User doesn't have permission to this operation");
    }
}
