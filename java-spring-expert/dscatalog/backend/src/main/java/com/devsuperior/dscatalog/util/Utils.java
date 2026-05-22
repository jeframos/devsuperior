package com.devsuperior.dscatalog.util;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.projection.IdProjection;
import com.devsuperior.dscatalog.projection.ProductProjection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    //Foi adicionado a sigla '<ID>' para facilitar o entendimento de que o método é genérico,
    // ou seja, ele pode ser utilizado para qualquer tipo de dado.
    //E a List é do tipo 'IdProjection<ID>' para que seja possível utilizar o método getId() para obter
    // o id do produto, e o tipo de dado do id é definido posteriormente, no momento da implementação.
    //Para que a lista 'List<? extends IdProjection<ID>>' possa receber qualquer tipo de Projection, é
    // necessário utilizar o coringa '?', que representa um tipo desconhecido, e o 'extends' para indicar
    // que o tipo desconhecido deve ser uma subclasse de 'IdProjection<ID>'.
    //Ao adicionar o tipo 'List<? extends IdProjection<ID>>' como parâmetro do método, ele pode receber
    // qualquer tipo de Projection, e assim evitar a repetição de código para cada tipo de Projection.
    public static <ID> List<? extends IdProjection<ID>> replace(List<? extends IdProjection<ID>> ordered, List<? extends IdProjection<ID>> unordered) {

        Map<ID, IdProjection<ID>> map = new HashMap<>();
        for (IdProjection<ID> p : unordered) {
            map.put(p.getId(), p);
        }

        List<IdProjection<ID>> result = new ArrayList<>();
        for (IdProjection<ID> pj : ordered) {
            result.add(map.get(pj.getId()));
        }

        return result;
    }
}
