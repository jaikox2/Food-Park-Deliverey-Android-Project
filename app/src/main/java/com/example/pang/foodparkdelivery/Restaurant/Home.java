package com.example.pang.foodparkdelivery.Restaurant;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pang.foodparkdelivery.R;
import com.example.pang.foodparkdelivery.food;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class Home extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FoodAdapterRes adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_res_home, null);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        poultererView();

        return view;
    }

    private void poultererView(){
        final ArrayList<food> itemArray = new ArrayList<>();
        final String baseUrl = "";

        Ion.with(this)
                .load(baseUrl+"foodResList.php")
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {
                        JsonObject jsonObject;
                        for(int i=0; i < result.size(); i++){
                            food item = new food();
                            jsonObject = (JsonObject)result.get(i);
                            item.setId(jsonObject.get("id").getAsInt());
                            item.setName(jsonObject.get("name").getAsString());
                            item.setImage(jsonObject.get("img").getAsString());
                            item.setPrice(jsonObject.get("price").getAsDouble());
                            item.setBaseUrl(baseUrl);
                            itemArray.add(item);
                        }
                        adapter = new FoodAdapterRes(itemArray,getActivity(), mRecyclerView);
                        mRecyclerView.setAdapter(adapter);
                    }
                });


    }
}
