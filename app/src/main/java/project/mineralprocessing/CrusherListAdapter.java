package project.mineralprocessing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;


/**
 * Created by nikhil on 11/10/15.
 */
public class CrusherListAdapter extends RecyclerView.Adapter<CrusherListAdapter.ViewHolder> implements View.OnClickListener {

    List<CrusherCharacter> list;
    static Context context;


    public CrusherListAdapter(List<CrusherCharacter> list, Context context) {
        this.list = list;
        this.context = context;

    }




    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i) {
        final CrusherCharacter c = list.get(i);
        viewHolder.crushername.setText(c.getName());
        viewHolder.crushername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,CrusherDetails.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Gson gson = new Gson();
                i.putExtra("Details",gson.toJson(c));
                context.startActivity(i);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.crusher_card, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView crushername;
        public ViewHolder(View itemView) {
            super(itemView);
            crushername=(TextView)itemView.findViewById(R.id.crusher_name);
        }
    }
}
