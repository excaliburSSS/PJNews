package com.scujcc.androidxtest;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import java.util.List;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;

public class ChannelListAdapter extends RecyclerView.Adapter<ChannelListAdapter.ChannelViewHolder> {
    private Context cxt;
    private List<Channel> c;
    private ChannelClickListener listener;


    public ChannelListAdapter(List<Channel> channels, ChannelClickListener listener) {
        this.listener = listener;
        this.c = channels;

    }

    @NonNull
    @Override
    public ChannelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        cxt = parent.getContext();
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.channel, parent,false);
        ChannelViewHolder holder = new ChannelViewHolder(row);
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v, holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelViewHolder holder, int position) {
        holder.bind(cxt, c.get(position));
    }

    @Override
    public int getItemCount() {
        return c.size();
    }

    public class ChannelViewHolder extends RecyclerView.ViewHolder {
        private TextView t;
        private TextView q;
        private ImageView l;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            t = itemView.findViewById(R.id.title);
            q = itemView.findViewById(R.id.quality);
            l = itemView.findViewById(R.id.imageView);
        }

        public void bind(Context context, Channel c) {
            t.setText(c.getTitle());
            q.setText(c.getQuality());
            Glide.with(context)
                    .load("http://1118cctv.com/upload/images/cctvlogo/cctv1.jpg")
                    .override(320,320)
                    .into(l);
        }
    }
}
