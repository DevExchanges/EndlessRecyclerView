package info.devexchanges.endlessrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.BookViewHolder> {

    private List<Country> countryList;
    private Context context;
    private static final int NORMAL_ROW = 1;
    private static final int LOADING_ROW = 0;
   private OnLoadMoreListener loadMoreListener;

    public RecyclerViewAdapter(Context context, List itemList) {
        this.countryList = itemList;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_ROW) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, null, false);
            return new BookViewHolder(layoutView);
        } else {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, null, false);
            return new BookViewHolder(layoutView);
        }
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, final int position) {
        holder.countryName.setText(countryList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.countryList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (countryList.get(position).getId()== -1) {
            return LOADING_ROW;
        } else return NORMAL_ROW;

    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView countryName;

        public BookViewHolder(View itemView) {
            super(itemView);

            countryName = (TextView) itemView.findViewById(R.id.country_name);
        }
    }

    interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }
}