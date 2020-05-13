package com.geolo.api.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.geolo.api.models.DistanceBetweenInfo;
import com.geolo.api.models.LatLong;
import com.geolo.api.models.Provider;
import com.geolo.api.models.ProviderRequestModel;
import com.geolo.api.repository.ProviderRepository;
import com.geolo.api.services.GMapsServices;
import java.util.ArrayList;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class ProviderResource {

    ProviderRepository rep = new ProviderRepository();
    ObjectMapper mapper = new ObjectMapper();

    @GetMapping(value = "/{especialidade}/{originLatLong}", produces = "application/json")
    public @ResponseBody
    ObjectNode showCloseProviders(
            @PathVariable(value = "originLatLong") String originLatLong,
            @PathVariable(value = "especialidade") String specialty) {
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
        ArrayList providers = rep.getAllProvidersBySpecialtys(specialty);
        if (providers.isEmpty()) {
            ret.put("error", true);
            ret.put("errorMsg", "Oops! Nenhum prestador de serviços com essa espcialidade foi encontrada.");
            return ret;
        }

        //MOUNT THE JSON WITH CLOSE PROVIDERS
        ArrayList<DistanceBetweenInfo> closeProviders = GMapsServices.getClosePositions("driving", origin, providers);

        for (DistanceBetweenInfo dInfo : closeProviders) {
            ArrayNode specialtys = mapper.createArrayNode();
            for (String spec : dInfo.getDestinationProvider().getSpecialtys()) {
                specialtys.add(spec);
            }

            ObjectNode node = mapper.createObjectNode();
            node.put("nome", dInfo.getDestinationProvider().getName());
            node.put("especialidades", specialtys);
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

        //MOUNT THE REQUEST OBJECT TO VIEW
        ObjectNode addressOriginNode = mapper.createObjectNode();
        addressOriginNode.put("enderecoDeOrigem", originAddress);
        addressOriginNode.put("latitude", origin.getLatitude());
        addressOriginNode.put("longitude", origin.getLongitude());

        ret.put("error", false);
        ret.put("origem", addressOriginNode);
        ret.put("prestadores", list);

        return ret;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
    public @ResponseBody
    ObjectNode addProvider(@RequestBody JsonNode provider) {
        String name = provider.get("nome").asText(), specialty = provider.get("especialidade").asText();
        double latitude = provider.get("latitude").asDouble(), longitude = provider.get("longitude").asDouble();

        ObjectNode retNode = mapper.createObjectNode();
        if (rep.addProvider(new Provider(name, specialty, new LatLong(latitude, longitude)))) {
            retNode.put("error", false);
            retNode.put("msg", "O prestador de serviço \"" + name + "\" foi adicionado com sucesso!");
        } else {
            retNode.put("error", true);
            retNode.put("msg", "Erro ao adicionar o prestador de serviço \"" + name + "\"!");
        }

        return retNode;
    }

    @PutMapping(produces = "application/json")
    public @ResponseBody
    ObjectNode addSpecialty(@RequestBody JsonNode provider) {
        String name = provider.get("nome").asText(), specialty = provider.get("especialidade").asText();

        ObjectNode retNode = mapper.createObjectNode();
        if (rep.addSpecialtyByProviderName(name, specialty)) {
            retNode.put("error", false);
            retNode.put("msg", "Especialidade \"" + specialty + "\" adicionado à \"" + name + "\" com sucesso!");
        } else {
            retNode.put("error", true);
            retNode.put("msg", "\"" + name + "\" não foi encontrado no sistema!");
        }

        return retNode;
    }
}
