package xyz.bhent.production.Methods;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by etinge mabian on 7/28/16.
 * bhent.xyz
 */
public class Data {
    public Data(){}

    public static String[] Categories = {
            "Boissons Chaudes",
            "Boissons Froides",
            "Boissons Gazeuzes (sans alcool)",
            "Boissons Energétiques",
            "Boissons Gazeuzes (avec alcool)",
            "Bieres",
            "Liqueurs",//has three columns
            "Cocktails (avec alcool)",
            "Cocktails (sans Alcool)",//left out
            "Rhum",//has 3 columns
            "Vodka",//has 3 columns
            "Cognac",//has 3 columns
            "Whisky",//has 3 columns
            "Cocktails Eteki",//left out
            "Vin Rouge", //has 2columns
            "Vin Blanc", // has 2 columns
            "Rose",
            "Vin Mousseux",
            "Champagnes"
    };
    //subcategories for oissons Chaudes
    public static HashMap<String, Integer> BChaudes() {
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Café", 1500);
        tempList.put("Thé", 1500);
        return tempList;
    }

    //subcategories for Boissons Froides
    public static HashMap<String, Integer> BFroides(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Tangui PM",1000);
        tempList.put("Menthe à Eau",1500);
        tempList.put("Grenadine à Eau",1500);
        tempList.put("Jus Naturel",1500);
        tempList.put("Diabolo(Sprite + Menthe)",2000);
        tempList.put("Perrier PM",2000);
        tempList.put("Menthe au Lait",2500);

        return tempList;
    }

    //subcategories for Boissons Gazeuzes (sans alcool)
    public static HashMap<String, Integer> BGsansAlcol(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Coca Cola",1500 );
        tempList.put("Coca Light", 1500);
        tempList.put("Coca Zero", 1500);
        tempList.put("Sprite", 1500);
        tempList.put("Fanta", 1500);
        tempList.put("Schweppes Soda", 1500);
        tempList.put("Schweppes Tonic", 1500);
        tempList.put("Orangina", 1500);

        return tempList;
    }

    //subcategories for Boissons Energétiques
    public static HashMap<String, Integer> BEnerge(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Eteki Classique",2000);
        tempList.put("Eteki Wildberry", 2000);
        tempList.put("Red Bull", 2000);
        tempList.put("Malta Guinness", 2000);

        return tempList;
    }

    //subcategories for Boissons Gazeuzes (avec alcool)
    public static HashMap<String, Integer> BEngeAlc(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Smirnoff Ice Black",2000);

        return tempList;
    }

    //subcategories for Bieres
    public static HashMap<String, Integer> Bieres(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("33 Export",2000);
        tempList.put("Beaufort Light",2000);
        tempList.put("Castel",2000);
        tempList.put("Heineken",2000);
        tempList.put("Guinness",2000);
        tempList.put("Tango",2500);

        return tempList;
    }



    //subcategories for Rose
    public static HashMap<String, Integer> RoseSub(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Côte de Provence",17500);

        return tempList;
    }

    //subcategories for Vin Mousseux
    public static HashMap<String, Integer> VinMous(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Prosecco",15000);
        tempList.put("JP Chenet",15000);

        return tempList;
    }

    //subcategories for Champagnes
    public static HashMap<String, Integer> Champs(){
        HashMap<String, Integer> tempList = new HashMap<>();
        tempList.put("Moet Brut",45000);
        tempList.put("Moet Nectar",60000);
        tempList.put("Veuve Clicquot",60000);
        tempList.put("Ruinart Blanc",70000);
        tempList.put("Dom Pérignon",150000);
        tempList.put("Champagne Cristal",225000);

        return tempList;
    }


    //Cocktails Alco categories
    public static HashMap<String, String> cocktailAlco(){
       HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Americano", "(Martini rouge, Campari, Soda)");
        tempList.put("Caipirinha", "(Cachaca, Jus de citron, Lime, Sucre)");
        tempList.put("Margarita", "(Tequila, Cointreau, Jus de citron)");
        tempList.put("Pina Colada", "Rhum, Malibu, Jus d’ananas)");
        tempList.put("Tequila Sunrise", "(Tequila, Jus de orange, sirop grenadine)");
        tempList.put("Mojito", "(Rhum, , Lime, Sucre, Fresh Menthe, Sprite)");
        tempList.put("B52", "(Kahlua, Baileys, Grand Marnier)");
        tempList.put("Cosmopolitan", "(Vodka, Cointreau, Jus de citron, Grenadine)");
        tempList.put("Daiquiri", "(Rhum, Jus de citron,sirop sucre)");
        tempList.put("Manhattan", "(Whisky, Martini Rouge, Bitter angostura)");
        tempList.put("Martini Cocktail", "(Gin, Martini Dry)");
        tempList.put("Negroni", "(Martini rouge, Campari, Gin)");
        tempList.put("Long Island Ice-Tea", "(Gin, Vodka, Rhum, Cointreau, Jus de citron, Coca)");
        tempList.put("Mexican Ice-Tea", "(Gin, Vodka, Rhum, Tequila, Jus de citron, Coca)");
        return tempList;
    }

    //Cocktails non Alco categories
    public static HashMap<String, String> cocktailNonAlc(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Black&White", "(Jus de ananas, jus de orange, jus de citron, grenadine)");
        tempList.put("Florida", "(Jus pamplemousse, jus de orange, jus de citron, soda)");
        tempList.put("Virgin Mojito", "(Sucre, Lime, Fresh Menthe, Sprite)");
        return tempList;
    }


    //Cocktails Eteki categories
    public static HashMap<String, String> cocktailEteki(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Virgin Etekjito", "(Lime, Sucre, Fresh Menthe, Eteki Wildberry)");
        tempList.put("Blueteki", "(Vodka, Malibu, Blue Curacao, Eteki Wildberry)");
        tempList.put("Etekjito", "(Rhum, Lime, Sucre, Fresh Menthe, Eteki)");
        tempList.put("Eteki Samba", "(Ballantine's Brasil, Cointreau, Eteki)");
        tempList.put("Eteki Boom Boom (4 shots)", "(Vodka Vanille, Eteki Wildberry)");
        return tempList;
    }

    //subcategory of Americano
    public static HashMap<String, Integer> americano(){
        HashMap<String, Integer> tempAmericano = new HashMap<>();
        tempAmericano.put("Martini rouge",5000);
        tempAmericano.put("Campari",5000);
        tempAmericano.put("Soda", 5000);
        return tempAmericano;
    }



}
