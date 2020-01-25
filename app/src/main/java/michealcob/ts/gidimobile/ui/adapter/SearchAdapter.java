package michealcob.ts.gidimobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import michealcob.ts.gidimobile.R;
import michealcob.ts.gidimobile.db.country.Country;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

        ItemClickListener itemClickListener;
        List<Country> countries;
        Context context;

        public SearchAdapter(ItemClickListener _item,
                             List<Country> countryList,
                             Context _context){
                this.itemClickListener = _item;
                this.countries = countryList;
                this.context = _context;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.search, viewGroup, false);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
                }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
            ViewHolder viewHolder = holder;

            Country country = countries.get(i);

            String search = country.getCity() +", " +country.getCityCode();
            String name = country.getCity();
            viewHolder.mName.setText(name);
            viewHolder.mOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.search(search,name);
                }
            });

        }

        @Override
            public int getItemCount() {
                    if(null == countries) return 0;
                    return countries.size();
                    }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView mName;
            LinearLayout mOption;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                mName = itemView.findViewById(R.id.name);
                mOption = itemView.findViewById(R.id.ll_country);

            }
        }

        public interface ItemClickListener{
            void search(String country, String name);
        }


}

