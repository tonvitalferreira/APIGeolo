package com.geolo.api.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geolo.api.models.DistanceBetweenInfo;
import com.geolo.api.models.LatLong;
import com.geolo.api.models.Provider;
import com.geolo.api.repository.ProviderRepository;
import com.geolo.api.services.GMapsServices;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class ProviderResource {

    ProviderRepository rep = new ProviderRepository();
    ObjectMapper mapper = new ObjectMapper();

    @ApiOperation(value = "Retorna uma lista de prestadores de serviços de saude")
    @GetMapping(value = "/{originLatLong}/{especialidade}", produces = "application/json")
    public @ResponseBody
    ObjectNode show(@PathVariable(value = "originLatLong") String originLatLong) {
        ObjectNode ret = mapper.createObjectNode();
        LatLong origin = null;

        if (originLatLong.contains(",")) {
            String splitedLatLong[] = originLatLong.split(",");
            if (splitedLatLong.length == 2) {
                try {
                    double lat = Double.parseDouble(splitedLatLong[0]);
                    double _long = Double.parseDouble(splitedLatLong[1]);
                    origin = new LatLong(lat, _long);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }

        String originAddress = "not found";
        ArrayNode list = mapper.createArrayNode();

        //MOUNT THE JSON WITH CLOSE PROVIDERS
        ArrayList<DistanceBetweenInfo> closeProviders = GMapsServices.getClosePositions("driving", origin, rep.getAllProviders());
        for (DistanceBetweenInfo dInfo : closeProviders) {
            ObjectNode node = mapper.createObjectNode();
            node.put("nome", dInfo.getDestinationProvider().getNome());
            node.put("endereco", dInfo.getMyAddress());
            node.put("latidute", dInfo.getDestinationProvider().getLatLong().getLatitude());
            node.put("longitude", dInfo.getDestinationProvider().getLatLong().getLongitude());
            node.put("distanciaEmKm", dInfo.getDistance().getText());
            node.put("duracaoEmMin", dInfo.getDuration().getText());
            list.add(node);

            if (originAddress.equals("not found")) {
                originAddress = dInfo.getOriginAddress();
            }
        }

        ObjectNode addressOriginNode = mapper.createObjectNode();
        addressOriginNode.put("enderecoDeOrigem", originAddress);
        addressOriginNode.put("latitude", origin.getLatitude());
        addressOriginNode.put("longitude", origin.getLongitude());

        ret.put("origem", addressOriginNode);
        ret.put("prestadores", list);

        return ret;
    }

    /*
    @ApiOperation(value = "Retorna uma lista de prestadores de serviços de saude")
    @GetMapping(produces = "application/json")
    @RequestMapping("/close")
    public @ResponseBody
    ArrayNode showClose() {
        ArrayNode list = mapper.createArrayNode();
        ArrayList<LatLong> destinations = new ArrayList<>();
        destinations.add(new LatLong(-8.892890, -36.494820));

        for (Provider pro : rep.getAllProviders()) {
            GMapsServices.getDistanceBetween("driving", pro.getLatLong(), destinations);
            ObjectNode node = mapper.createObjectNode();
            node.put("nome", pro.getNome());
            node.put("endereco", pro.getEndereco());
            node.put("latidute", pro.getLatLong().getLatitude());
            node.put("longitude", pro.getLatLong().getLongitude());
            node.put("distanciaEmKm", (Math.random() * 1000));
            list.add(node);
        }
        return list;
    }*/
}
