package com.devsuperior.dscatalog.projection;

//Esse tipo de interface é chamada de generica, pois pode ser implementada por outras interfaces, e o tipo
// de dado para o método getId() pode ser definido posteriormente, no momento da implementação.
//O tipo <E> representa um coringa, que pode aceitar a implementação com qualquer tipagem para getId().
//Ex: Tipo Long, Integer, UUID dentre outros tipos.

//Essa interface deve implementar a classe 'Product', e extender a interface 'ProductProjection'
// pois o método getId() é utilizado para obter o id do produto, e o tipo de dado do id é Long.
//E será utilizada de forma generica no método 'replace' da classe Utils, e assim evitar a repetição
// de código para cada tipo de Projection.
public interface IdProjection<E> {
    E getId();
}
