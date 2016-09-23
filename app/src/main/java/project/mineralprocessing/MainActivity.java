package project.mineralprocessing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText inputsize,outputsize,capital,salesprice,hoursofproduction,powercost;
    Button compute;
    TextView result;
    RelativeLayout loading;
    LinearLayout layout;
    ArrayList<CrusherCharacter> list = new ArrayList<>();

    ArrayList<CrushersShortlist> shortlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        inputsize=(EditText)findViewById(R.id.inputsize);
        outputsize=(EditText)findViewById(R.id.outputsize);
        salesprice=(EditText)findViewById(R.id.salesprice);
        hoursofproduction=(EditText)findViewById(R.id.hoursofworking);
        powercost=(EditText)findViewById(R.id.powercost);
        capital=(EditText)findViewById(R.id.capital);
        capital.setVisibility(View.GONE);
        compute=(Button)findViewById(R.id.compute);
        result=(TextView)findViewById(R.id.result);
        layout=(LinearLayout)findViewById(R.id.layout);
        loading=(RelativeLayout)findViewById(R.id.loading);
        layout.setVisibility(View.GONE);
        compute.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.compute){
            float input=Float.parseFloat(inputsize.getText().toString());
            float output=Float.parseFloat(outputsize.getText().toString());
            float price=Float.parseFloat(salesprice.getText().toString());
            float hours=Float.parseFloat(hoursofproduction.getText().toString());
            float reductionratio=input/output;

            for(int i=0;i<list.size();i++) {

                for (int j =i+1; j < list.size(); j++) {

                    CrusherCharacter c = list.get(i);
                    CrusherCharacter d = list.get(j);
                    float p = c.getReductionratio()*d.getReductionratio();



                        if (p < (reductionratio + (0.1f * reductionratio)) && p > (reductionratio - (0.1f * reductionratio))) {
                            CrushersShortlist a = new CrushersShortlist();
                            a.setCrusher1(c);
                            a.setCrusher2(d);
                            shortlist.add(a);
                        }

                }
            }
            for(int i=0;i<list.size();i++) {

                for (int j = i + 1; j < list.size(); j++) {

                    for (int k = j + 1; k < list.size(); k++) {

                        CrusherCharacter c = list.get(i);
                        CrusherCharacter d = list.get(j);
                        CrusherCharacter e = list.get(k);
                        float p = c.getReductionratio() * d.getReductionratio() * e.getReductionratio();

                        if (p < (reductionratio + (0.1f * reductionratio)) && p > (reductionratio - (0.1f * reductionratio))) {
                            CrushersShortlist a = new CrushersShortlist();
                            a.setCrusher1(c);
                            a.setCrusher2(d);
                            a.setCrusher3(e);
                            shortlist.add(a);
                        }

                        //make algo for combination of crushers to get reduction ratio

                    }
                }
            }
            String list="";
            if(shortlist.size()!=0) {

                for (int i = 0; i < shortlist.size(); i++) {
                    int flag=0,flag2=0;
                    CrushersShortlist c = shortlist.get(i);
                    CrusherCharacter d = c.getCrusher1();
                    CrusherCharacter e = c.getCrusher2();
                    CrusherCharacter f = null;
                    if (c.getCrusher3() != null) {
                        f = c.getCrusher3();
                    }
                    if (c.getCrusher3() != null) {
                        float income=calcIncome(d,e,f);
                        float expense=calcExpense(d,e,f);
                        CrusherCost ccost = new CrusherCost();
                        ccost.setIncome("" + income);
                        ccost.setExpense("" + expense);
                        ccost.setOption(d.getName() + " with " + e.getName() + " and with " + f.getName());
                        for(int j=0;j<Global.cost.size();j++) {
                            if(Float.parseFloat(ccost.getIncome())>Float.parseFloat(Global.cost.get(j).getIncome())) {
                                Global.cost.add(j,ccost);
                                flag=1;
                                break;
                            }


                        }
                        if(flag==0){
                            Global.cost.add(ccost);
                        }
                    } else {
                        float income=calcIncome(d,e);
                        float expense=calcExpense(d,e);
                        CrusherCost ccost = new CrusherCost();
                        ccost.setIncome("" + income);
                        ccost.setExpense("" + expense);
                        ccost.setOption(d.getName() + " with " + e.getName());
                        for(int j=0;j<Global.cost.size();j++) {
                            if(Float.parseFloat(ccost.getIncome())>Float.parseFloat(Global.cost.get(j).getIncome())) {
                                Global.cost.add(j,ccost);
                                flag2=1;
                                break;
                            }


                        }
                        if(flag2==0){
                            Global.cost.add(ccost);
                        }
                    }

                }
            }

            final ProgressDialog progress;
            progress=new ProgressDialog(this);
            progress.setMessage("Computing...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setProgress(0);
            progress.show();
            android.os.Handler handler = new android.os.Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progress.dismiss();
                    Intent i = new Intent(MainActivity.this,ShowOptions.class);
                    startActivity(i);
                }
            }, 2000);
        }
    }

    public void getData(){
        String[] url = new String[]{
                "http://www.waverr.in/project/getdata.php"
        };

        JSONObtainer obtainer;
        obtainer = new JSONObtainer() {
            @Override
            protected void onPostExecute(JSONArray array) {

                final String things[] = {
                        "Name",
                        "InputSize",
                        "OutputSize",
                        "ReductionRatio",
                        "Production",
                        "Power"
                };

                try {

                    if(array==null){
                        Toast.makeText(MainActivity.this, "Please reload..", Toast.LENGTH_SHORT).show();
                    }
                    else if (array.length() == 0) {
                        {

                        }
                    }
                    else {


                        for (int i = 0; i < array.length(); i++) {
                            final JSONObject object = array.getJSONObject(i);
                            CrusherCharacter c = new CrusherCharacter();
                            c.setName(object.getString(things[0]));
                            c.setInputsize(Float.parseFloat(object.getString(things[1])));
                            c.setOutputsize(Float.parseFloat(object.getString(things[2])));
                            c.setReductionratio(Float.parseFloat(object.getString(things[3])));
                            c.setProduction(Float.parseFloat(object.getString(things[4])));
                            c.setPower(Float.parseFloat(object.getString(things[5])));
                            list.add(c);
                        }
                    }
                    loading.setVisibility(View.GONE);
                    layout.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    cancel(true);
                    e.printStackTrace();

                }
            }
        };
        obtainer.execute(url);
    }

    public Float calcIncome(CrusherCharacter a, CrusherCharacter b, CrusherCharacter c){
        float cost=0;
        float production = Float.parseFloat(a.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f;
        float relativesales= production*Float.parseFloat(salesprice.getText().toString());

        if((Float.parseFloat(b.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f)<production) {

            production=Float.parseFloat(b.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f;
            relativesales = production * Float.parseFloat(salesprice.getText().toString());
        }
        if((Float.parseFloat(c.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f)<production){
            production=Float.parseFloat(c.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f;
            relativesales= production*Float.parseFloat(salesprice.getText().toString());
        }

        cost+=relativesales;
        return cost/100000;
    }
    public Float calcIncome(CrusherCharacter a, CrusherCharacter b){
        float cost=0;
        float production = Float.parseFloat(a.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f;
        float relativesales= production*Float.parseFloat(salesprice.getText().toString());

        if((Float.parseFloat(b.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f)<production){
            production=Float.parseFloat(b.getProduction().toString())*Float.parseFloat(hoursofproduction.getText().toString())*30*12f;
            relativesales= production*Float.parseFloat(salesprice.getText().toString());
        }

        cost+=relativesales;
        return cost/100000;
    }
    public Float calcExpense(CrusherCharacter a, CrusherCharacter b, CrusherCharacter c){
        float cost=0;
        float annualenergy=a.getPower()*Float.parseFloat(hoursofproduction.getText().toString())*12*30;
        float totalenergycost = annualenergy*Float.parseFloat(powercost.getText().toString());
        float wearcost=0.84f*totalenergycost;
       // float othercost=0.38*
        cost+=totalenergycost+wearcost;
        annualenergy=b.getPower()*Float.parseFloat(hoursofproduction.getText().toString())*12*30;
        totalenergycost = annualenergy*Float.parseFloat(powercost.getText().toString());
        wearcost=0.84f*totalenergycost;
        // float othercost=0.38*
        cost+=totalenergycost+wearcost;
        annualenergy=c.getPower()*Float.parseFloat(hoursofproduction.getText().toString())*12*30;
        totalenergycost = annualenergy*Float.parseFloat(powercost.getText().toString());
        wearcost=0.84f*totalenergycost;
        // float othercost=0.38*
        cost+=totalenergycost+wearcost;
        return cost/100000;
    }

    public Float calcExpense(CrusherCharacter a, CrusherCharacter b){
        float cost=0;
        float annualenergy=a.getPower()*Float.parseFloat(hoursofproduction.getText().toString())*12*30;
        float totalenergycost = annualenergy*Float.parseFloat(powercost.getText().toString());
        float wearcost=0.84f*totalenergycost;
        // float othercost=0.38*
        cost+=totalenergycost+wearcost;
        annualenergy=b.getPower()*Float.parseFloat(hoursofproduction.getText().toString())*12*30;
        totalenergycost = annualenergy*Float.parseFloat(powercost.getText().toString());
        wearcost=0.84f*totalenergycost;
        // float othercost=0.38*
        cost+=totalenergycost+wearcost;
        return cost/100000;
    }
}
