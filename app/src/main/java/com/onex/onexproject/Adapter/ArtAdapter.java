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
import com.onex.onexproject.Frag_Profile.FragProfile_art;
import com.onex.onexproject.Model.Post;
import com.onex.onexproject.Model.User;
import com.onex.onexproject.R;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class ArtAdapter extends FirestoreRecyclerAdapter<Post, ArtAdapter.ArtViewHolder> {
    private ArtAdapter.OnItemClickListener listener;
    public ArtAdapter(@NonNull @NotNull FirestoreRecyclerOptions<Post> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull @NotNull ArtAdapter.ArtViewHolder holder, int position, @NonNull @NotNull Post model) {
        Glide.with(holder.itemView).load(model.getUri()).into(holder.image);

    }

    @NonNull
    @NotNull
    @Override
    public ArtAdapter.ArtViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_art_item, parent, false);
        return new ArtAdapter.ArtViewHolder(View);
    }

    public class ArtViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;

        public ArtViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.artPiece);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position), position, image);

                    }
                }
            });

        }
    }

    public interface OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position, ImageView imageView);
    }

    public void setOnItemClickListener(ArtAdapter.OnItemClickListener listener){
        this.listener = listener;
    }
}
