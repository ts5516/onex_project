package com.onex.onexproject.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.onex.onexproject.Model.Tag;
import com.onex.onexproject.R;

import org.jetbrains.annotations.NotNull;;

public class TagAdapter extends FirestoreRecyclerAdapter<Tag, TagAdapter.TagViewHolder> {
    private OnItemClickListener listener;
    public TagAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Tag> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull TagViewHolder holder, int position, @NonNull @NotNull Tag model) {
        holder.tagTitle.setText(model.getName());
        Glide.with(holder.itemView).load(model.getImageUri()).into(holder.tagImage);
    }

    @NonNull
    @NotNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View tagView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_tag_item, parent, false);
        return new TagViewHolder(tagView);
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        private ImageView tagImage;
        private TextView tagTitle;

        public TagViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tagImage = itemView.findViewById(R.id.tagImage);
            tagTitle = itemView.findViewById(R.id.tagTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
