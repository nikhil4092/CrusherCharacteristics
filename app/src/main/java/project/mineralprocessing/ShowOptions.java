package project.mineralprocessing;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ShowOptions extends AppCompatActivity {

    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_options);
        container=(LinearLayout)findViewById(R.id.container);
        LinearLayout hor = new LinearLayout(ShowOptions.this);
        hor.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params2.gravity= Gravity.CENTER;
        params2.setMargins(0, 10, 0, 10);
        hor.setLayoutParams(params2);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout hor3 = new LinearLayout(ShowOptions.this);
        hor3.setOrientation(LinearLayout.HORIZONTAL);
        hor3.setWeightSum(10.0f);
        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,6.0f);
        TextView text5 = new TextView(ShowOptions.this);
        text5.setText("CRUSHER COMBINATION");
        text5.setTextColor(Color.parseColor("#000000"));
        text5.setTextSize(12);
        text5.setLayoutParams(params);
        TextView text6 = new TextView(ShowOptions.this);
        text6.setText("INCOME in LAKHS");
        text6.setTextColor(Color.parseColor("#000000"));
        text6.setTextSize(12);
        params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
        params.gravity=Gravity.CENTER;
        text6.setLayoutParams(params);
        params.setMargins(100, 0, 0, 10);
        TextView text7 = new TextView(ShowOptions.this);
        text7.setText("EXPENSE IN LAKHS");
        text7.setTextColor(Color.parseColor("#000000"));
        text7.setTextSize(12);
        text7.setLayoutParams(params);
        hor3.addView(text5);
        hor3.addView(text6);
        hor3.addView(text7);
        hor.addView(hor3);

        for(int i=0;i<Global.cost.size();i++){
            CrusherCost c = Global.cost.get(i);
            LinearLayout hor2 = new LinearLayout(ShowOptions.this);
            hor2.setOrientation(LinearLayout.HORIZONTAL);
            hor2.setWeightSum(10.0f);
            params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,6.0f);
            TextView text3 = new TextView(ShowOptions.this);
            text3.setText(c.getOption());
            text3.setTextColor(Color.parseColor("#000000"));
            text3.setTextSize(12);
            text3.setId(10000 + i);
            text3.setLayoutParams(params);
            TextView text = new TextView(ShowOptions.this);
            text.setText(c.getIncome());
            text.setTextColor(Color.parseColor("#000000"));
            text.setTextSize(12);
            text.setId(10000 + i);
            params = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,2.0f);
            params.gravity=Gravity.CENTER;
            text.setLayoutParams(params);
            params.setMargins(100, 0, 0, 10);
            TextView text2 = new TextView(ShowOptions.this);
            text2.setText(c.getExpense());
            text2.setTextColor(Color.parseColor("#000000"));
            text2.setTextSize(12);
            text2.setId(10000 + i);
            text2.setLayoutParams(params);
            hor2.addView(text3);
            hor2.addView(text);
            hor2.addView(text2);
            hor.addView(hor2);

        }
        container.addView(hor);
    }
}
