package g.sns_test.Post;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import g.sns_test.R;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CustomViewHolder> {

    private ArrayList<PostData> mList = null;
    private Activity context = null;


    public PostAdapter(Activity context, ArrayList<PostData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView contents;
        protected TextView time;


        public CustomViewHolder(View view) {
            super(view);
            //this.title = (TextView) view.findViewById(R.id.textView_post_title);
            //this.contents = (TextView) view.findViewById(R.id.textView_post_contents);
            //this.time = (TextView) view.findViewById(R.id.textView__post_time);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.title.setText(mList.get(position).gettitle());
        viewholder.contents.setText(mList.get(position).getcontents());
        viewholder.time.setText(mList.get(position).gettime());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}