package zohar.com.fristreview.twelve;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zohar.com.fristreview.R;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private Context mContext;

    private List<Fruit> mListFruit;

    public FruitAdapter(Context context,List<Fruit> mListFruit) {
        this.mContext = context;
        this.mListFruit = mListFruit;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView;
            imageView = itemView.findViewById(R.id.image_view_fruit);
            textView = itemView.findViewById(R.id.text_view_fruit_name);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Fruit fruit = mListFruit.get(i);
        viewHolder.textView.setText(fruit.getName());
        viewHolder.imageView.setImageResource(fruit.getImageId());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit,viewGroup,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fruit fruit = mListFruit.get(holder.getAdapterPosition());
                Toast.makeText(mContext,fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public int getItemCount() {
        return mListFruit.size();
    }
}
