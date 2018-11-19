package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

// called from ActivityMain on a list item click within a ListView
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mDescriptionTV;
    private TextView mOriginTV;
    private TextView mAlsoKnownAsTV;
    private TextView mIngredientsTV;
    private Sandwich mSandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // inflate activity_detail layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // set references for layout entities
        mDescriptionTV = findViewById(R.id.description_tv);
        mOriginTV = findViewById(R.id.origin_tv);
        mAlsoKnownAsTV = findViewById(R.id.also_known_tv);
        mIngredientsTV = findViewById(R.id.ingredients_tv);

        // get reference to ImageView
        ImageView ingredientsIv = findViewById(R.id.image_iv);

        // get intent from MainActivity
        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        // extract the list item position from the intent
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        // get sandwich details in JSON format defined in strings.xml
        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];

        // parse JSON
        mSandwich = JsonUtils.parseSandwichJson(json);
        if (mSandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        // update UI with parsed JSON results
        populateUI();

        // TODO What is this?
        Picasso.with(this)
                .load(mSandwich.getImage())
                .into(ingredientsIv);

        setTitle(mSandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        mDescriptionTV.setText(mSandwich.getDescription());
        mOriginTV.setText(mSandwich.getPlaceOfOrigin());
        mAlsoKnownAsTV.setText(mSandwich.getAlsoKnownAs().toString());
        mIngredientsTV.setText(mSandwich.getIngredients().toString());

    }
}
