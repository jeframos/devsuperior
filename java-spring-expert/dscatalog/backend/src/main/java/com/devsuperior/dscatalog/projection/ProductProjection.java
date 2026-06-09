package com.devsuperior.dscatalog.projection;

//Como a interface 'IdProjection<Long>' é genérica, é necessário informar o tipo
// de dado que será utilizado para o método getId().
public interface ProductProjection extends IdProjection<Long> {

    String getName();
}
