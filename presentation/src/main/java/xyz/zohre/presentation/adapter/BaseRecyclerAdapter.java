package xyz.zohre.presentation.adapter;


import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerAdapter<M, VH extends BaseViewHolder<M>, P extends BaseViewHolder.OnItemClickListener<M>> extends RecyclerView.Adapter<VH> {

    private final static int PROGRESS_TYPE = 2017;

    @NonNull
    private final List<M> data;
    @Nullable
    private P listener;
    private int lastKnowingPosition = -1;
    private boolean showedGuide;
    private GuideListener guideListener;

    protected BaseRecyclerAdapter() {
        this(new ArrayList<>());
    }

    protected BaseRecyclerAdapter(@NonNull List<M> data) {
        this(data, null);
    }

    protected BaseRecyclerAdapter(@NonNull List<M> data, @Nullable P listener) {
        this.data = data;
        this.listener = listener;
    }

    protected abstract VH viewHolder(ViewGroup parent, int viewType);

    protected void onBindView(VH holder, int position){
        holder.bind(data.get(position));
    }

    @NonNull
    public List<M> getData() {
        return data;
    }

    public M getItem(int position) {
        return data.get(position);
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == PROGRESS_TYPE) {
            addSpanLookup(parent);
            return (VH) ProgressBarViewHolder.newInstance(parent);
        } else {
            return viewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (holder instanceof ProgressBarViewHolder) {
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                layoutParams.setFullSpan(true);
            }
        } else if (getItem(position) != null) {
            animate(holder, position);
            onBindView(holder, position);
            onShowGuide(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) == null) {
            return PROGRESS_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(@NonNull VH holder) {
        super.onViewRecycled(holder);
    }

    @SuppressWarnings("unchecked")
    private void onShowGuide(@NonNull VH holder, int position) {
        // give the flexibility to other adapters to override this
        if (position == 0 && !isShowedGuide() && guideListener != null) {
            guideListener.onShowGuide(holder.itemView, getItem(position));
            showedGuide = true;
        }
    }

    private void animate(@NonNull VH holder, int position) {
        if (isEnableAnimation() && position > lastKnowingPosition) {
            lastKnowingPosition = position;
        }
    }

    public void insertItems(@NonNull List<M> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }

    @SuppressWarnings("WeakerAccess")
    public void addItems(@NonNull List<M> items) {
        removeProgress();
        data.addAll(items);
        notifyItemRangeInserted(getItemCount(), (getItemCount() + items.size()) - 1);
    }

    @SuppressWarnings("WeakerAccess")
    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    public boolean isEmpty() {
        return !data.isEmpty();
    }

    @SuppressWarnings("WeakerAccess")
    public boolean isEnableAnimation() {
        return false;
    }

    @SuppressWarnings("WeakerAccess")
    @Nullable
    public P getListener() {
        return listener;
    }

    public void setListener(@Nullable P listener) {
        this.listener = listener;
        notifyDataSetChanged();
    }

    private boolean isShowedGuide() {
        return showedGuide;
    }

    public void removeProgress() {
        if (isEmpty()) {
            M m = getItem(getItemCount() - 1);
            if (m == null) {
                removeItem(getItemCount() - 1);
            }
        }
    }

    private void addSpanLookup(ViewGroup parent) {
        if (parent instanceof RecyclerView) {
            if (((RecyclerView) parent).getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager layoutManager = ((GridLayoutManager) ((RecyclerView) parent).getLayoutManager());
                assert layoutManager != null;
                layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return getItemViewType(position) == PROGRESS_TYPE ? layoutManager.getSpanCount() : 1;
                    }
                });
            }
        }
    }

    public interface GuideListener<M> {
        void onShowGuide(@NonNull View itemView, @NonNull M model);
    }
}

