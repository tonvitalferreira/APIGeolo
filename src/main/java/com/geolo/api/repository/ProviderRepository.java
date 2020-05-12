package com.geolo.api.repository;

import com.geolo.api.models.LatLong;
import com.geolo.api.models.Provider;
import java.util.ArrayList;

public class ProviderRepository {

    public ArrayList<Provider> getAllProviders() {
        ArrayList<Provider> ret = new ArrayList();
        ret.add(new Provider("José ", "Cardiologista", new LatLong(-8.8848746, -36.5146785)));
        ret.add(new Provider("Amanda", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Carlos", "Dermatologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Gabriele", "Oftalmologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Enyara", "Cardiologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Vera Lucia", "Dermatologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Ademar", "Oftalmologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("João Geraldo", "Oftalmologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Alisson", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Aparecido", "Cardiologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Edniz", "Oftalmologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Rosiane", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Diana", "Oftalmologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Marley", "Cardiologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Gutember Gomes", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Danilo", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Jefferson", "Cardiologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Eduarda", "Dermatologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("William", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Fatima", "Dermatologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Catarina", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Maria Carina", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("José Alexandre", "Dermatologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Aurora", "Cardiologista", new LatLong(-8.8878441, -36.4911599)));
        ret.add(new Provider("Agatha", "Oftalmologista", new LatLong(-8.8878441, -36.4911599)));
        return ret;
    }

}
