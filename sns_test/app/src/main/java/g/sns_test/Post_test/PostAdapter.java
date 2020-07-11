package g.sns_test.Post_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import g.sns_test.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.RecyclerViewAdapter> {

    private List<PostItem> notes;
    private Context context;
    private  ItemClickListener itemClickListener;

    public PostAdapter( Context context, List<PostItem> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_list_item, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list_item, parent, false);

        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        PostItem note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

     class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tv_note, tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;

         public RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.post_card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
