package project.mineralprocessing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class CrusherList extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    RelativeLayout loading;
    ArrayList<CrusherCharacter> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crusher_list);
        loading=(RelativeLayout)findViewById(R.id.productfeed_loadingPanel);
        mRecyclerView = (RecyclerView)findViewById(R.id.crusher_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CrusherListAdapter(list,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
        getData();
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
                        Toast.makeText(CrusherList.this, "Please reload..", Toast.LENGTH_SHORT).show();
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
                    mAdapter.notifyDataSetChanged();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    cancel(true);
                    e.printStackTrace();

                }
            }
        };
        obtainer.execute(url);
    }
}
