package sda2.jsonexcercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Activity to read teh Json string and display the content on button clicks
 */
public class MainActivity extends AppCompatActivity {
private String jsonString;
private   int countGreen=0;
private String colorString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonString ="{\n"+
                "     \"colors\": [\n"+
                "       {\n"+
                "         \"color\": \"black\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [255,255,255,1],\n"+
                "           \"hex\": \"#000\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"white\",\n"+
                "         \"category\": \"value\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [0,0,0,1],\n"+
                "           \"hex\": \"#FFF\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"red\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [255,0,0,1],\n"+
                "           \"hex\": \"#FF0\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"blue\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [0,0,255,1],\n"+
                "           \"hex\": \"#00F\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"yellow\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"primary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [255,255,0,1],\n"+
                "           \"hex\": \"#FF0\"\n"+
                "         }\n"+
                "       },\n"+
                "       {\n"+
                "         \"color\": \"green\",\n"+
                "         \"category\": \"hue\",\n"+
                "         \"type\": \"secondary\",\n"+
                "         \"code\": {\n"+
                "           \"rgba\": [0,255,0,1],\n"+
                "           \"hex\": \"#0F0\"\n"+
                "         }\n"+
                "       }\n"+
                "     ]\n"+
                "   }";

        //read Json String
        readJson();

    }


    //methid to parse the Json file
    private void readJson() {

        try {

            JSONObject jsonMain = (JSONObject) new JSONTokener(jsonString).nextValue();

            JSONArray jsonArray = (JSONArray) jsonMain.getJSONArray("colors");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonelement = (JSONObject) jsonArray.get(i);

                JSONObject jsonCode = (JSONObject) jsonelement.getJSONObject("code");

                JSONArray jsonrgba = (JSONArray) jsonCode.getJSONArray("rgba");

                if (jsonrgba.getInt(1) == 255) {
                    countGreen += 1;
                    colorString +=jsonelement.getString("color")+" - ";
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    //to count the number of greens
    public void count(View view) {

        TextView txtView = (TextView) findViewById(R.id.txt_text);
        txtView.setText("Number of Greens : "+ countGreen);

    }

    //to list the color fields having green
    public void list(View view) {

        TextView txtView = (TextView) findViewById(R.id.txt_text);
        txtView.setText("Color Field : "+ colorString);

    }

    //to modify the Json string for new element
    public void modify(View view) {

        try {
            JSONObject jsonMain = (JSONObject) new JSONTokener(jsonString).nextValue();

            JSONArray jsonArray = (JSONArray) jsonMain.getJSONArray("colors");

            JSONObject newJson = new JSONObject();

            newJson.put("color","orange");
            newJson.put("category","hue");

            JSONObject newRgba = new JSONObject();
            JSONArray newarr = new JSONArray();
            newarr.put(255);
            newarr.put(165);
            newarr.put(0);
            newarr.put(1);
            newRgba.put("rgba",newarr);
            newRgba.put("hex","#FA0");

            newJson.put("code",newRgba);

            jsonArray.put(newJson);

            JSONObject jsonnewMain = new JSONObject();
            jsonnewMain.put("colors",jsonArray);

            String result = jsonnewMain.toString(2);

            TextView txtView = (TextView) findViewById(R.id.txt_text);
            txtView.setText(result);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
