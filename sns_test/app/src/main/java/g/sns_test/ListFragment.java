package g.sns_test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import g.sns_test.Post_test.InsertPostEdit;

public class ListFragment extends Fragment  {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Button insertButton = (Button) view.findViewById(R.id.insertButton);


        //recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      // final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
      //  layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
      //  mRecyclerView.setLayoutManager(layoutManager);


        insertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getActivity(), InsertPostEdit.class);
                //Intent intent = new Intent(getActivity(), InsertPostActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}





/*



    public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
        private ArrayList<PostItem> dataList;

        public ListAdapter(ArrayList<PostItem> data) {
            this.dataList = data;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewText;
            TextView textViewComment;
            TextView textViewDate;

            public ViewHolder(View itemView) {
                super(itemView);
                this.textViewText = (TextView) itemView.findViewById(R.id.text);
                this.textViewComment = (TextView) itemView.findViewById(R.id.comment);
                this.textViewDate = (TextView) itemView.findViewById(R.id.date);
            }
        }

        @Override
        public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recyclerview_item, parent, false);

            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position) {
            holder.textViewText.setText(dataList.get(position).getText());
            holder.textViewComment.setText(dataList.get(position).getComment());
            holder.textViewDate.setText(dataList.get(position).getDate());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Item " + position + " is clicked.", Toast.LENGTH_SHORT).show();
                }
            });
        }


        @Override
        public int getItemCount() {
            return dataList.size();

        }
    }
*/