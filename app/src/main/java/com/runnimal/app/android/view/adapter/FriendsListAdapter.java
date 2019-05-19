package com.runnimal.app.android.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.runnimal.app.android.R;
import com.runnimal.app.android.view.presenter.FriendsPresenter;
import com.runnimal.app.android.view.presenter.RankingPresenter;
import com.runnimal.app.android.view.presenter.TrainingsPresenter;
import com.runnimal.app.android.view.viewmodel.FriendsViewModel;
import com.runnimal.app.android.view.viewmodel.RankingViewModel;
import com.runnimal.app.android.view.viewmodel.TrainingViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FriendsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final FriendsPresenter presenter;
    private final List<FriendsViewModel> friendsList;
    private final List<FriendsViewModel> filteredList;

    public FriendsListAdapter(FriendsPresenter presenter) {
        this.presenter = presenter;
        this.friendsList = new ArrayList<>();
        this.filteredList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_user_row, parent, false);
        return new FriendsListViewHolder(view, presenter);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FriendsListViewHolder friendsListViewHolder = (FriendsListViewHolder) holder;
        FriendsViewModel friend = filteredList.get(position);
        friendsListViewHolder.render(friend);
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void addAll(Collection<FriendsViewModel> collection) {
        friendsList.addAll(collection);
        filteredList.addAll(collection);
    }

    public void filter(String text) {
        filteredList.clear();

        final String textLowerCase = text.toLowerCase();
        friendsList.stream() //
                .filter(t -> t.getName().toLowerCase().contains(textLowerCase)) //
                .collect(Collectors.toCollection(() -> filteredList));

        notifyDataSetChanged();
    }
}