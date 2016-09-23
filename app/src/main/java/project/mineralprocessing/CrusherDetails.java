package project.mineralprocessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;


public class CrusherDetails extends AppCompatActivity {

    TextView name,feedsize,productsize,rr,productioncapacity,power;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crusher_details);
        name=(TextView)findViewById(R.id.name);
        feedsize=(TextView)findViewById(R.id.feedsize);
        productsize=(TextView)findViewById(R.id.productsize);
        rr=(TextView)findViewById(R.id.rr);
        productioncapacity=(TextView)findViewById(R.id.productioncapacity);
        power=(TextView)findViewById(R.id.power);
        Gson gson = new Gson();
        Intent i = getIntent();
        String details = i.getStringExtra("Details");
        CrusherCharacter c = gson.fromJson(details, CrusherCharacter.class);
        name.setText(c.getName());
        feedsize.setText("Feed Size(mm): "+c.getInputsize());
        productsize.setText("Output Size(mm): "+c.getOutputsize());
        rr.setText("Reduction Ratio: "+c.getReductionratio());
        productioncapacity.setText("Production Capacity(T/hr): "+c.getProduction());
        power.setText("Power consumtion(KWh): "+c.getPower());
    }
}
