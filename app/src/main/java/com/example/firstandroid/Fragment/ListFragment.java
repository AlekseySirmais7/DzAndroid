package com.example.firstandroid.Fragment;

import android.os.Bundle;
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
import com.example.firstandroid.R;

import java.util.List;

public class ListFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set data params
        DataSource.SetStartMinNumberAndStartMaxNumber(
                getResources().getInteger(R.integer.startSourceMinNumber),
                getResources().getInteger(R.integer.startSourceMaxNumber) );


        List<Integer> startNumberList =  DataSource.getInstance().getData();

        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler);

        // check rotation
        int rotate = getActivity().getWindowManager().getDefaultDisplay().getRotation();

        // можно не сравнить с 0 а с int углом (после какого-то угла считать, что мы в вертикальном положении)
        int columnCount = rotate == 0 ? getResources()
                .getInteger(R.integer.columnForPort)
                :getResources().getInteger(R.integer.columnForLand);

        recyclerView.setLayoutManager(new GridLayoutManager( getActivity(), columnCount));

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

    public  class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Integer> mData;

        public MyAdapter(List<Integer> data) {
            mData = data;
        }


        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Log.d("MyAdapter", "onCreateViewHolder");
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_number, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            //Log.d("MyAdapter", "onBindViewHolder with position: " + position);
            Integer numberValue = mData.get(position);
            holder.mNumber.setText(String.valueOf(numberValue));

            int textColor = getResources().getColor(R.color.colorBlueForNumber);
            if (numberValue % 2 == 0) {
                textColor = getResources().getColor(R.color.colorRedForNumber);
            }
            holder.mNumber.setTextColor(textColor);


            holder.itemView.setOnClickListener(view -> {

                //Toast.makeText(view.getContext().getApplicationContext(), "Clicked on " + position, Toast.LENGTH_SHORT).show();

                OneNumberFragment oneNumberFrag = OneNumberFragment.newInstance(numberValue,
                        getActivity().getResources().getString(R.string.OneNumberFragmentNumberKey));

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                oneNumberFrag,
                                getResources().getString(R.string.tagForFindOneNumberFragment))
                        .addToBackStack(null)
                        .commitAllowingStateLoss();
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
