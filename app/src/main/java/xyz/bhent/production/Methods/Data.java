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
            "Cocktails (sans Alcool)",
            "Rhum",//has 3 columns
            "Vodka",//has 3 columns
            "Cognac",//has 3 columns
            "Whisky",//has 3 columns
            "Cocktails Eteki",
            "Vin Rouge",
            "Vin Blanc",
            "Rose",
            "Vin Mousseux",
            "Champagnes"
    };
    //subcategories for oissons Chaudes
    public static HashMap<String, String> BChaudes() {
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Café", "1,500");
        tempList.put("Thé", "1,500");
        return tempList;
    }

    //subcategories for Boissons Froides
    public static HashMap<String, String> BFroides(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Tangui PM","1,000");
        tempList.put("Menthe à Eau", "1,500");
        tempList.put("Grenadine à Eau","1,500");
        tempList.put("Jus Naturel", "1,500");
        tempList.put("Diabolo(Sprite + Menthe)","2,000");
        tempList.put("Perrier PM","2,000");
        tempList.put("Menthe au Lait","2,500");

        return tempList;
    }

    //subcategories for Boissons Gazeuzes (sans alcool)
    public static HashMap<String, String> BGsansAlcol(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Coca Cola","1,500");
        tempList.put("Coca Light", "1,500");
        tempList.put("Coca Zero", "1,500");
        tempList.put("Sprite", "1,500");
        tempList.put("Fanta", "1,500");
        tempList.put("Schweppes Soda", "1,500");
        tempList.put("Schweppes Tonic", "1,500");
        tempList.put("Orangina", "1,500");

        return tempList;
    }

    //subcategories for Boissons Energétiques
    public static HashMap<String, String> BEnerge(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Eteki Classique","2,000");
        tempList.put("Eteki Wildberry", "2,000");
        tempList.put("Red Bull", "2,000");
        tempList.put("Malta Guinness", "2,000");

        return tempList;
    }

    //subcategories for Boissons Gazeuzes (avec alcool)
    public static HashMap<String, String> BEngeAlc(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Smirnoff Ice Black","2,000");

        return tempList;
    }

    //subcategories for Bieres
    public static HashMap<String, String> Bieres(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("33 Export","2,000");
        tempList.put("Beaufort Light", "2,000");
        tempList.put("Castel","2,000");
        tempList.put("Heineken","2,000");
        tempList.put("Guinness", "2,000");
        tempList.put("Tango", "2,500");

        return tempList;
    }

    //Subcategory for Vin Rouge
    public static HashMap<String, String> Vrouge(){
        HashMap<String, String> arrayList = new HashMap<>();
        arrayList.put("Merlot", "(Verre and Bottle)");//two price verre and bottle
        arrayList.put("Medoc", "(Bottle)");
        arrayList.put("Cabernet Sauvignon Cavior", "(Bottle)");
        arrayList.put("Haut-Medoc", "(Bottle)");

        return arrayList;
    }

    //Subcategory for Vin blanc
    public static HashMap<String, String> vinBlanc(){
        HashMap<String, String> map = new HashMap<>();
        map.put("Chardonnay","(Verre and Bottle)");
        map.put("Sauvignon","(Bottle)");
        map.put("Chardonnay \"Sweet\" Cavior","(Bottle)");

        return map;
    }




    //subcategories for Rose
    public static HashMap<String, String> RoseSub(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Côte de Provence (bottle)", "17,500");

        return tempList;
    }

    //subcategories for Vin Mousseux
    public static HashMap<String, String> VinMous(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Prosecco (bottle)","15,000");
        tempList.put("JP Chenet (bottle)", "15,000");

        return tempList;
    }

    //subcategories for Champagnes
    public static HashMap<String, String> Champs(){
        HashMap<String, String> tempList = new HashMap<>();
        tempList.put("Moet Brut (bottle)", "45,000");
        tempList.put("Moet Nectar (bottle)", "60,000");
        tempList.put("Veuve Clicquot (bottle)", "60,000");
        tempList.put("Ruinart Blanc (bottle)", "70,000");
        tempList.put("Dom Pérignon (bottle)", "150,000");
        tempList.put("Champagne Cristal (bottle)", "225,000");

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





}
