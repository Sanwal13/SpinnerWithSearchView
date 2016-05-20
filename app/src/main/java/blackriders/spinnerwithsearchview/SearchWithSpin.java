package blackriders.spinnerwithsearchview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sanwal Singh on 20/5/16.
 */
public class SearchWithSpin extends Activity {

    public static TextView txt_dealer;
    public static LinearLayout layout_data;
    String url = "http://www.snipercorporation.com/index.php?route=api/api/getClient";
    String TAG = "search_activity";
    ProgressDialog dialog;
    String id, visitedDate, name, status, comment, sentmessage, dealer_id, followdate, createDate,
            userid, username, FollowupStatus, telephone;
    List<Data> datas;
    EditText edit_dealer;
    ListView list_dealer;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        datas = new ArrayList<Data>();

        txt_dealer = (TextView) findViewById(R.id.txt_dealer);
        edit_dealer = (EditText) findViewById(R.id.edit_dealer);
        list_dealer = (ListView) findViewById(R.id.list_dealer);
        layout_data = (LinearLayout) findViewById(R.id.layout_data);

        txt_dealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layout_data.getVisibility() == View.GONE) {
                    layout_data.setVisibility(View.VISIBLE);
                } else {
                    layout_data.setVisibility(View.GONE);
                }
            }
        });

        edit_dealer.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                Log.d("", "CharSequence : " + s);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

       /* list_dealer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = datas.get(position).getName().toString();
                Log.d("Dealer Name ", ": " + name);
                txt_dealer.setText(name);
                layout_data.setVisibility(View.GONE);
            }
        });*/
        doLogin();

    }

    private void doLogin() {

        String tag_string_req = "req_login";
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Please wait ...");
        dialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Response: " + response.toString());
                dialog.dismiss();

                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);

                        id = jsonObject.getString("id");
                        visitedDate = jsonObject.getString("visitedDate");
                        name = jsonObject.getString("name");
                        status = jsonObject.getString("status");
                        comment = jsonObject.getString("comment");
                        sentmessage = jsonObject.getString("sentmessage");
                        dealer_id = jsonObject.getString("dealer_id");
                        followdate = jsonObject.getString("followdate");
                        createDate = jsonObject.getString("createDate");
                        userid = jsonObject.getString("userid");
                        username = jsonObject.getString("username");
                        FollowupStatus = jsonObject.getString("FollowupStatus");
                        telephone = jsonObject.getString("telephone");
                        Data data = new Data(id, visitedDate, name, status, comment, sentmessage,
                                dealer_id, followdate, createDate, userid, username, FollowupStatus,
                                telephone);
                        datas.add(data);

                    }
                    adapter = new ListAdapter(SearchWithSpin.this, datas);
                    list_dealer.setAdapter(adapter);
                    txt_dealer.setText(datas.get(0).getName().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Check internet connection.",
                        Toast.LENGTH_LONG).show();
                dialog.dismiss();

            }
        });
        strReq.setRetryPolicy(new RetryPolicy() {

            @Override
            public void retry(VolleyError arg0) throws VolleyError {
                // TODO Auto-generated method stub
                Log.e("", "RE-TRY -: " + arg0);
            }

            @Override
            public int getCurrentTimeout() {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public int getCurrentRetryCount() {
                // TODO Auto-generated method stub
                return 0;
            }
        });
        strReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}
