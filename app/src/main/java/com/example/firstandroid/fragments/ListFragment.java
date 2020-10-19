package com.example.firstandroid.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstandroid.DataSource;
import com.example.firstandroid.MainActivity;
import com.example.firstandroid.R;

import java.util.List;

public class ListFragment extends Fragment {


    @Override
    public void onSaveInstanceState(Bundle outState) {

        if (outState == null)
            outState = new Bundle();

        outState.putInt(getResources().getString(R.string.listFragmentMaxNumberSaveKey),
                DataSource.getInstance().getMaxNumber());

        Log.d("My CHECK:", "onSaveInstanceState: save:" + DataSource.getInstance().getMaxNumber());
        super.onSaveInstanceState(outState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        int maxNumber = -1;

        if (savedInstanceState != null) {
            maxNumber = savedInstanceState.getInt(
                    getResources().getString(R.string.listFragmentMaxNumberSaveKey),
                    -1);
            Log.d("My CHECK", "onViewCreated: get " + maxNumber);
        } else {
            Log.d("My CHECK", "savedInstanceState: is NULL! ");
        }


        if (maxNumber == -1) {
            maxNumber = getResources().getInteger(R.integer.startSourceMaxNumber);
        }

        List<Integer> startNumberList = DataSource.fullInstance(
                getResources().getInteger(R.integer.startSourceMinNumber),
                maxNumber)
                .getData();

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),
                getResources().getInteger(R.integer.columnCount)));

        ListFragment.MyAdapter adapter = new ListFragment.MyAdapter(startNumberList);

        recyclerView.setAdapter(adapter);

        // add btn action
        Button addNumberBtn = getActivity().findViewById(R.id.addNumberBtn);

        addNumberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecyclerView recyclerView = getActivity().findViewById(R.id.recycler);

                DataSource.getInstance().addNextNumber();

                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });

    }


    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Integer> mData;

        public MyAdapter(List<Integer> data) {
            mData = data;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_number, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Integer numberValue = mData.get(position);
            holder.mNumber.setText(String.valueOf(numberValue));

            int textColor = getResources().getColor(R.color.colorBlueForNumber);
            if (numberValue % 2 == 0) {
                textColor = getResources().getColor(R.color.colorRedForNumber);
            }
            holder.mNumber.setTextColor(textColor);

            holder.itemView.setOnClickListener(view -> {
                ((MainActivity) getActivity()).showNum(numberValue);
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public final TextView mNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mNumber = itemView.findViewById(R.id.numberID);
        }
    }

}
