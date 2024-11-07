package br.com.cesarsicas.springstore.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RolePermissions {
    ADMIN_CREATE("admin:create"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),

    MERCHANT_CREATE("merchant:create"),
    MERCHANT_READ("merchant:read"),
    MERCHANT_UPDATE("merchant:update"),
    MERCHANT_DELETE("merchant:delete"),

    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_DELETE("customer:delete");


    @Getter
    private final String permissions;
}
