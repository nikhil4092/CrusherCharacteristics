package project.mineralprocessing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Selection extends AppCompatActivity implements View.OnClickListener{

    TextView crusherselection,computation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        crusherselection=(TextView)findViewById(R.id.crusher_char);
        computation=(TextView)findViewById(R.id.computation);
        crusherselection.setOnClickListener(this);
        computation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.crusher_char){
            Intent i = new Intent(Selection.this,CrusherList.class);
            startActivity(i);
        }
        else if(id==R.id.computation){
            Intent i = new Intent(Selection.this,MainActivity.class);
            startActivity(i);
        }
    }
}
