package com.geolo.api.repository;

import com.geolo.api.models.LatLong;
import com.geolo.api.models.Provider;
import java.util.ArrayList;

public class ProviderRepository {

    private ArrayList<Provider> providers;

    public ProviderRepository() {
        providers = new ArrayList();
        providers.add(new Provider("José", "Cardiologista", new LatLong(-8.8848746, -36.5146785)));
        providers.add(new Provider("Amanda", "Dentista", new LatLong(-8.8878441, -36.4911599)));
        providers.add(new Provider("Carlos", "Dermatologista", new LatLong(-8.8907514, -36.4900158)));
        providers.add(new Provider("Gabriele", "Oftalmologista", new LatLong(-8.8879425, -36.4871137)));
        providers.add(new Provider("Enyara", "Cardiologista", new LatLong(-8.8847321, -36.4978377)));
        providers.add(new Provider("Vera Lucia", "Dermatologista", new LatLong(-8.8903161, -36.5022568)));
        providers.add(new Provider("Ademar", "Oftalmologista", new LatLong(-8.8938198, -36.5089275)));
        providers.add(new Provider("João Geraldo", "Oftalmologista", new LatLong(-8.9032745, -36.498466)));
        providers.add(new Provider("Alisson", "Dentista", new LatLong(-8.9095118, -36.4915349)));
        providers.add(new Provider("Aparecido", "Cardiologista", new LatLong(-8.9071892, -36.4957654)));
        providers.add(new Provider("Edniz", "Oftalmologista", new LatLong(-8.9171951, -36.4866176)));
        providers.add(new Provider("Rosiane", "Dentista", new LatLong(-8.8919054, -36.4769081)));
        providers.add(new Provider("Diana", "Oftalmologista", new LatLong(-8.8797874, -36.4803641)));
        providers.add(new Provider("Marley", "Cardiologista", new LatLong(-8.8878441, -36.4911599)));//aqui
        providers.add(new Provider("Gutember Gomes", "Dentista", new LatLong(-8.8861449, -36.4576098)));
        providers.add(new Provider("Danilo", "Dentista", new LatLong(-8.8821287, -36.4949965)));
        providers.add(new Provider("Jefferson", "Cardiologista", new LatLong(-8.8611813, -36.5164562)));
        providers.add(new Provider("Eduarda", "Dermatologista", new LatLong(-8.89591, -36.5078773)));
        providers.add(new Provider("William", "Dentista", new LatLong(-8.8921117, -36.4789217)));
        providers.add(new Provider("Fatima", "Dermatologista", new LatLong(-8.8921117, -36.4789217)));
    }

    public ArrayList<Provider> getAllProviders() {
        return providers;
    }

    public ArrayList<Provider> getAllProvidersBySpecialtys(String specialty) {
        ArrayList<Provider> ret = new ArrayList();

        for (Provider pro : providers) {
            if (pro.getSpecialtys().contains(specialty)) {
                ret.add(pro);
            }
        }

        return ret;
    }

    public boolean addProvider(Provider pro) {
        providers.add(pro);
        return true;
    }

    public boolean addSpecialtyByProviderName(String name, String specialty) {
        boolean ret = false;

        //WITHOUT BREAK, BECAUSE WE CAN HAVE MORE THAN ONE PROVIDER WITH THE SAME NAME
        for (Provider pro : providers) {
            if (pro.getName().equals(name)) {
                pro.addSpecialty(specialty);
                ret = true;
            }
        }

        return ret;
    }

}
