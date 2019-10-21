package com.example.github_repo_lister;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoriesViewHolder> {

    private List<RepositoriesData> repositoriesDataList;

    RepositoriesAdapter(List<RepositoriesData> repositoriesData) {
        this.repositoriesDataList = repositoriesData;
    }

    @NonNull
    @Override
    public RepositoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.repositories_ui, parent, false);
        return new RepositoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RepositoriesViewHolder holder, final int position) {
        RepositoriesData repositoriesData = repositoriesDataList.get(position);
        holder.Name.setText(repositoriesData.getName());
        holder.Stargazers_count.setText(repositoriesData.getStargazers_count​());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(view.getContext(), DetailsActivity.class);
                intent.putExtra("name", repositoriesDataList.get(position).getName());
                intent.putExtra("stargazers_count", repositoriesDataList.get(position).getStargazers_count​());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repositoriesDataList.size();
    }

    static class RepositoriesViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        TextView Stargazers_count;

        RepositoriesViewHolder(View v) {
            super(v);
            Name = v.findViewById(R.id.txtName);
            Stargazers_count = v.findViewById(R.id.txtStargazers_count);
        }
    }
}