package com.geolo.api.Resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geolo.api.Models.Provider;
import com.geolo.api.Repository.ProviderRepository;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class ProviderResource {

    ProviderRepository rep = new ProviderRepository();
    ObjectMapper mapper = new ObjectMapper();

    @ApiOperation(value = "Retorna uma lista de prestadores de serviços de saude")
    @GetMapping(produces = "application/json")
    public @ResponseBody
    ArrayNode listaEventos() {
        ArrayNode list = mapper.createArrayNode();
        for (Provider pro : rep.getAllProviders()) {
            ObjectNode node = mapper.createObjectNode();
            node.put("nome", pro.getNome());
            node.put("endereco", pro.getEndereco());
            node.put("latidute", pro.getLatitude());
            node.put("longitude", pro.getLongitude());
            node.put("distanciaEmKm", (Math.random() * 1000));
            list.add(node);
        }
        return list;
    }

}
