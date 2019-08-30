package com.africa.cloud.commandes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.africa.cloud.commandes.model.Lunette;
import com.africa.cloud.commandes.model.LunetteAdapter;
import com.africa.cloud.commandes.service.GitHubClient;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProduitFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProduitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProduitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    FragmentTransaction ft=null;
    FragmentManager fm=null;


    private static final String TAG ="cool" ;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    LunetteAdapter lunetteAdapter;
    ArrayAdapter adapter;
    private List<Lunette> lunetteList = new ArrayList<>();
    SearchView searchView;
    Activity context;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProduitFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProduitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProduitFragment newInstance(String param1, String param2) {
        ProduitFragment fragment = new ProduitFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getActivity();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produit, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    public void onStart(){
        super.onStart();

        searchView = (SearchView) context.findViewById(R.id.searchView);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(lunetteList.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(context, "Rien ne correspond a ce produit",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });


        recyclerView = (RecyclerView) context.findViewById(R.id.lunette_recycler_view);






        mAdapter = new LunetteAdapter(lunetteList,context);
        prepareLunetteData();
        //   parseJSON();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter( mAdapter);


    }

    private void prepareLunetteData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.21:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubClient user = retrofit.create(GitHubClient.class);

        user.getLunette().enqueue(new Callback<List<Lunette>>() {
            @Override
            public void onResponse(Call<List<Lunette>> call, Response<List<Lunette>> response) {

                if(response.isSuccessful()){
                    Toast.makeText(context, response.code()+"", Toast.LENGTH_SHORT).show();
                    List<Lunette>  lunetteList = response.body();


                    for (Lunette l : lunetteList )
                    {

                        System.out.print(lunetteList);

                    }



                    mAdapter = new LunetteAdapter(lunetteList, context);
                    recyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onFailure(Call<List<Lunette>> call, Throwable t) {

                Toast.makeText(context, "Veuillez verifier votre connexion"+t.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });


    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
