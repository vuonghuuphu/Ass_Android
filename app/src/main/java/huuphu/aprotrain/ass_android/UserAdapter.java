package huuphu.aprotrain.ass_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {

    private Activity activity;
    private List<User> userList;

    public UserAdapter(Activity activity, List<User> userList) {
        this.activity = activity;
        this.userList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = activity.getLayoutInflater().inflate(R.layout.item_list,parent,false);
        ProductHoder hoder = new ProductHoder(itemview);
        return hoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductHoder hoder = (ProductHoder) holder;
        User model = userList.get(position);
        hoder.tvId.setText(position + 1 + "");
        hoder.tvName.setText(model.username);
        hoder.tvGender.setText(model.gender);
        hoder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(view.getContext(),EditUser.class);
                intent.putExtra("id_select",userList.get(position).id);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ProductHoder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ItemClickListener itemClickListener;
        TextView tvId;
        TextView tvName;
        TextView tvGender;


        public ProductHoder (@NonNull View itemview){
            super(itemview);
            tvId = itemview.findViewById(R.id.tv_id);
            tvName = itemview.findViewById(R.id.tv_name);
            tvGender = itemview.findViewById(R.id.tv_gender);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),true); // Gọi interface , true là vì đây là onLongClick
            return true;
        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }
    }
}