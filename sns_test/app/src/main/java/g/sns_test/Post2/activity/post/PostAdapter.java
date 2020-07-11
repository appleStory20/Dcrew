package g.sns_test.Post2.activity.post;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import g.sns_test.Post2.model.Note;
import g.sns_test.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Note> notes;
    private ItemCLickListener itemCLickListener;

    public PostAdapter(Context context, List<Note> notes, ItemCLickListener itemCLickListener) {
        this.context = context;
        this.notes = notes;
        this.itemCLickListener = itemCLickListener;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_note, parent,false);
        return new RecyclerViewAdapter(view, itemCLickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tv_note, tv_date;
        CardView card_item;
        ItemCLickListener itemCLickListener;


        public RecyclerViewAdapter(@NonNull View itemView, ItemCLickListener itemCLickListener) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.card_item);
            this.itemCLickListener = itemCLickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemCLickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemCLickListener {
        void onItemClick(View view, int position);
    }
}

