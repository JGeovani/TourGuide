package com.udacity.tourguide.dummy;

import com.udacity.tourguide.R;
import com.udacity.tourguide.model.Dweller;
import com.udacity.tourguide.model.Meal;

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
    public static final Object ABOUT = 0;

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
        int[] peoples = new int[] {R.drawable.chaves, R.drawable.chiquinha, R.drawable.seumadruga};
        return new Dweller(String.valueOf(position), "Morador respectivo " + position, peoples[position % 3], position % 5, "Descrição detalhada sobre o morador receptivo " + position);
    }


    public static final List<Meal> BREAKFASTS = new ArrayList<>();
    static {
        BREAKFASTS.add(new Meal("0", "Para gastar pouco", R.drawable.cafe_matinal, "Padaria Nogueira e padaria Jorge"));
        BREAKFASTS.add(new Meal("1", "Padaria", R.drawable.cafe_matinal, "Delicia do Pão, Super Estrela, Restaurante  e Pizza..."));
    }


    public static final List<Meal> LUNCHS = new ArrayList<>();
    static {
        LUNCHS.add(new Meal("0", "Lugares para almoço", R.drawable.almoco, "Barracas de praias e restaurantes"));
        LUNCHS.add(new Meal("1", "Para gastar pouco", R.drawable.almoco, "Restaurante e Pizzaria. Super Grill + Complexo"));
    }


    public static final List<Meal> SNACS = new ArrayList<>();
    static {
        SNACS.add(new Meal("0", "Lojas de sobremesas", R.drawable.cafeelanches, "Cacau Show e Restaurantes, Creperia, Pizzaria IBI..."));
        SNACS.add(new Meal("1", "Padaria", R.drawable.cafeelanches, "Delicia do Pão, Super Estrela, Restaurante  e Pizza..."));
    }


    public static final List<Meal> DINNERS = new ArrayList<>();
    static {
        DINNERS.add(new Meal("0", "Lugares para Jantar", R.drawable.jantares, "Canoa Quebrada, Restaurante, Creperia, Pizzaria ..."));
        DINNERS.add(new Meal("1", "Lugares bons para crianças", R.drawable.jantares, "Restaurante e Pizzaria. Super Grill + Pizzaria O ..."));
        BREAKFASTS.add(new Meal("1", "Pizzaria", R.drawable.jantares, "Pizzaria O Walter, Di Lucca Pizzaria, Restaurante"));
    }


    public static final List<Meal> DRINKS = new ArrayList<>();
    static {
        DRINKS.add(new Meal("0", "Bar", R.drawable.bar, "Regart-Bar, Bar Caverna, Vip Music Bar, Cordel Pi..."));
    }
}
