package com.geolo.api.services;

import com.geolo.api.models.Distance;
import com.geolo.api.models.DistanceBetweenInfo;
import com.geolo.api.models.Duration;
import com.geolo.api.models.LatLong;
import com.geolo.api.models.Provider;
import java.util.ArrayList;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class GMapsServices {

    protected static final String GOOGLE_API_KEY = "AIzaSyBQVW8pFcB8rgpse32NXErtPxiz1qlQRHc";

    public static ArrayList<DistanceBetweenInfo> getClosePositions(String mode, LatLong originLatLong, ArrayList<Provider> providers) {
        ArrayList<DistanceBetweenInfo> distances = new ArrayList();

        String destinationsValidURL = "";

        if (providers.size() == 1) {
            LatLong dest = providers.get(0).getLatLong();
            destinationsValidURL = dest.toString();
        } else {
            for (int i = 0; i < providers.size(); i++) {
                LatLong destLatLong = providers.get(i).getLatLong();
                destinationsValidURL += destLatLong.toString();

                //add | in end of line, until come before the end
                if (i < providers.size() - 1) {
                    destinationsValidURL += "%7C";
                }
            }
        }

        String gMapsURL = String.format(
                "https://maps.googleapis.com/maps/api/distancematrix/json?mode=%s&origins=%s&destinations=%s&key=%s",
                mode,
                originLatLong.toString(),
                destinationsValidURL,
                GOOGLE_API_KEY);

        //SEND GET TO THE GOOGLE API
        HttpGet post = new HttpGet(gMapsURL);

        try {
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = httpClient.execute(post);
            String responseString = EntityUtils.toString(response.getEntity());
            JSONObject res = new JSONObject(responseString);

            //CHECK IF THIS IS OK WITH REQUISITION
            if (res.getString("status").equals("OK")) {
                String originsAddresses = res.getJSONArray("origin_addresses").getString(0);
                JSONArray rows = res.getJSONArray("rows");
                JSONArray elements = rows.getJSONObject(0).getJSONArray("elements");
                for (int i = 0; i < elements.length(); i++) {
                    String myAddress = res.getJSONArray("destination_addresses").getString(i);
                    JSONObject element = elements.getJSONObject(i);
                    String elementStatus = element.getString("status");
                    if (elementStatus.equals("OK")) {
                        JSONObject distance = element.getJSONObject("distance");
                        JSONObject duration = element.getJSONObject("duration");
                        distances.add(
                                new DistanceBetweenInfo(originsAddresses,
                                        myAddress,
                                        providers.get(i),
                                        new Distance(distance.getString("text"), distance.getInt("value")),
                                        new Duration(duration.getString("text"), duration.getInt("value"))
                                )
                        );
                    }
                }
            } else {
                distances.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
            distances.clear();
        }

        return distances;
    }
}
