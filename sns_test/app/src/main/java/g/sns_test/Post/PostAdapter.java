package g.sns_test.Post;

/*
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.CustomViewHolder> {

    private ArrayList<PostData> mList = null;
    private Activity context = null;


    public PostAdapter(Activity context, ArrayList<PostData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;
        protected TextView nickname;
        protected TextView time;


        public CustomViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.post_title);
            this.nickname = (TextView) view.findViewById(R.id.post_nickname);
            this.time = (TextView) view.findViewById(R.id.post_date);

            //this.title = (TextView) view.findViewById(R.id.textView_post_title);
            //this.contents = (TextView) view.findViewById(R.id.textView_post_contents);
            //this.time = (TextView) view.findViewById(R.id.textView__post_time);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.simple_article_list_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.title.setText(mList.get(position).getTitle());
        viewholder.nickname.setText(mList.get(position).getNickname() );
        viewholder.time.setText(mList.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}

 */