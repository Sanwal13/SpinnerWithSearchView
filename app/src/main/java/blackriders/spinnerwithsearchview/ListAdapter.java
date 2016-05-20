package blackriders.spinnerwithsearchview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sanwal Singh on 20/5/16.
 */
public class ListAdapter extends BaseAdapter implements Filterable {

    String data;
    private Context mContext;
    private LayoutInflater inflater;
    private List<Data> orignailOrders;
    private List<Data> filterOrders;

    public ListAdapter(Context context, List<Data> orders) {
        this.mContext = context;
        this.orignailOrders = orders;
        this.filterOrders = orders;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return filterOrders.size();
    }

    @Override
    public Object getItem(int location) {
        return filterOrders.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_user, parent,
                    false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(filterOrders.get(position).getName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = filterOrders.get(position).getName().toString();
                Log.d("Dealer Name ", ": " + name);
                SearchWithSpin.txt_dealer.setText(name);
                SearchWithSpin.layout_data.setVisibility(View.GONE);
            }
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                filterOrders = (ArrayList<Data>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<Data> FilteredArrList = new ArrayList<Data>();

                if (orignailOrders == null) {
                    orignailOrders = new ArrayList<Data>(filterOrders);
                }

                if (constraint == null || constraint.length() == 0) {
                    results.count = orignailOrders.size();
                    results.values = orignailOrders;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < orignailOrders.size(); i++) {
                        data = orignailOrders.get(i).getName();
                        if (data.toLowerCase()
                                .startsWith(constraint.toString())) {
                            Log.d("", "data : " + data);

                            /*id, visitedDate, name, status, comment, sentmessage,
                                    dealer_id, followdate, createDate, userid, username, FollowupStatus,
                                    telephone*/


                            FilteredArrList.add(new Data(
                                    orignailOrders.get(i).getId(),
                                    orignailOrders.get(i).getVisitedDate(),
                                    orignailOrders.get(i).getName(),
                                    orignailOrders.get(i).getStatus(),
                                    orignailOrders.get(i).getComment(),
                                    orignailOrders.get(i).getSentmessage(),
                                    orignailOrders.get(i).getDealer_id(),
                                    orignailOrders.get(i).getFollowdate(),
                                    orignailOrders.get(i).getCreateDate(),
                                    orignailOrders.get(i).getUserid(),
                                    orignailOrders.get(i).getUsername(),
                                    orignailOrders.get(i).getFollowupStatus(),
                                    orignailOrders.get(i).getTelephone()));


                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    class ViewHolder {
        TextView tvName;
    }
}