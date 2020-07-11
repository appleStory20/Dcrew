package g.sns_test.Post;

/*
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private Context context;
    private  List<Note> notes;
    private  ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.context = context;
        this.notes = notes;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate((R.layout.item_note, parent, false);

        return RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position){
        Note note = notes.get(position);
        holder.tv_title.setText(note.getTitle());
        holder.tv_note.setText(note.getNote());
        holder.tv_date(setText(note.getDate());
        holder.card_item.setCardBackgroundColor(note.getColor());

    }

    @Override
    public int getItemCount(){

        return notes.size();
    }


    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements  View.OnClickListener {
        TextView tv_title, tv_nickName, tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            this.itemClickListener = itemClickListener;

            tv_title = itemView.findViewById(R.id.post_title);
            tv_nickName = itemView.findViewById(R.id.post_nickname);
            tv_date = itemView.findViewById(R.id.post_date);
            card_item = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
             itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

}


 */