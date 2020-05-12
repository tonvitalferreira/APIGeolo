package com.geolo.api.Repository;

import com.geolo.api.Models.Provider;
import java.util.ArrayList;

public class ProviderRepository {

    public ArrayList<Provider> getAllProviders() {
        ArrayList<Provider> ret = new ArrayList();
        ret.add(new Provider("José Carlos", "Cardiologia", -8.8848746, -36.5146785));
        return ret;
    }

}
