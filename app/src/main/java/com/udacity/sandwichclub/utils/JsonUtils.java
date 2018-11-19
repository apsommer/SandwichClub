package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

//        private String mainName;
//        private List<String> alsoKnownAs = null;
//        private String placeOfOrigin;
//        private String description;
//        private String image;
//        private List<String> ingredients = null;

        // parse JSON
        try {

            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject("name");
            String mainName = name.getString("mainName");

            JSONArray alsoKnownAsJSON = name.getJSONArray("alsoKnownAs");
            List<String> alsoKnownAs = new ArrayList<String>();
            for (int i = 0; i < alsoKnownAsJSON.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJSON.getString(i));
            }

            String placeOfOrigin = sandwich.getString("placeOfOrigin");
            String description = sandwich.getString("description");
            String image = sandwich.getString("image");

            JSONArray ingredientsJSON = sandwich.getJSONArray("ingredients");
            List<String> ingredients = new ArrayList<String>();
            for (int i = 0; i < ingredientsJSON.length(); i++) {
                ingredients.add(ingredientsJSON.getString(i));
            }

            // create a new Sandwich object with the parsed JSON
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
