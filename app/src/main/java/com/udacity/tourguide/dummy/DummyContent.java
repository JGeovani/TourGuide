package com.udacity.tourguide.dummy;

import com.udacity.tourguide.R;
import com.udacity.tourguide.model.Dweller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by geovani on 06/11/16.
 */

public class DummyContent {

    public static final List<Dweller> DWELLERS = new ArrayList<>();
    public static final Map<String, Dweller> DWELLER_MAP = new HashMap<>();
    private static final int SIZE_DWELLERS = 32;
    static {
        for (int i = 0; i < SIZE_DWELLERS; i++) {
            addDwellers(createDweller(i));
        }
    }

    private static void addDwellers(Dweller dweller) {
        DWELLERS.add(dweller);
        DWELLER_MAP.put(dweller.mID, dweller);
    }

    private static Dweller createDweller(int position) {
        return new Dweller(String.valueOf(position), "Morador respectivo " + position, R.drawable.background_praia, position % 5, "Descrição detalhada sobre o morador receptivo " + position);
    }



}
