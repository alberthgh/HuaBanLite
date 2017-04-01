package com.albert.huabanlite.base.coustomwidget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 创建人：Edanel
 * 创建时间：16/2/19 下午5:18
 * 修改人：Edanel
 * 修改时间：16/2/19 下午5:18
 * 修改备注：
 */

public abstract class ArrayRecyclerAdapter<E, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> implements List<E> {


    protected int mAdapterPosition = 0;

    private final Object lock = new Object();

    private final List<E> list;

    protected View mHeaderView;
    protected RecyclerView recyclerView;

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public boolean notifyHeader = false;

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public boolean isNotifyHeader() {
        return notifyHeader;
    }

    public void setNotifyHeader(boolean notifyHeader) {
        this.notifyHeader = notifyHeader;
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public ArrayRecyclerAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        list = new ArrayList<E>();
    }

//    public ArrayRecyclerAdapter(int capacity) {
//        list = new ArrayList<E>(capacity);
//    }
//
//    public ArrayRecyclerAdapter(Collection<? extends E> collection) {
//        list = new ArrayList<E>(collection);
//    }


    @Override
    public int getItemCount() {
        return mHeaderView == null ? size() : size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public void add(int location, E object) {
        synchronized (lock) {
            list.add(location, object);
            notifyItemInserted(location);
        }
    }

    @Override
    public boolean add(E object) {
        synchronized (lock) {
            int lastIndex = mHeaderView != null ? list.size() + 1 : list.size();
            if (list.add(object)) {
                notifyItemInserted(lastIndex);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean addAll(int location, Collection<? extends E> collection) {
        synchronized (lock) {
            if (list.addAll(location, collection)) {
                notifyItemRangeInserted(location, collection.size());
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        synchronized (lock) {
            int lastIndex = mHeaderView != null ? list.size() + 1 : list.size();
            if (list.addAll(collection)) {
                if (notifyHeader) {
                    notifyItemRangeInserted(lastIndex -1 , collection.size());
                }else {
                    notifyItemRangeInserted(lastIndex, collection.size());
                }

                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void clear() {
        synchronized (lock) {
            int size = list.size();
            if (size > 0) {
                list.clear();
                notifyItemRangeRemoved(0, size);
            }
        }
    }

    public void clearExceptHeader() {
        synchronized (lock) {
            int size = list.size();
            if (size > 0) {
                list.clear();
                notifyItemRangeRemoved(1, size);
            }
        }
    }

    @Override
    public boolean contains(Object object) {
        return list.contains(object);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection) {
        return list.containsAll(collection);
    }

    @Override
    public E get(int location) {

        return mHeaderView != null ? list.get(location - 1) : list.get(location);
    }

    @Override
    public int indexOf(Object object) {
        return list.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @NonNull
    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return list.lastIndexOf(object);
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator() {
        return list.listIterator();
    }

    @NonNull
    @Override
    public ListIterator<E> listIterator(int location) {
        return list.listIterator(location);
    }

    @Override
    public E remove(int location) {
        synchronized (lock) {
            E item = list.remove(location);
            notifyItemRemoved(location);
            //notifyItemRemoved（）有坑，需加上notifyItemRangeChanged(location, getItemCount())a
            return item;
        }
    }

    @Override
    public boolean remove(Object object) {
        synchronized (lock) {
            int index = indexOf(object);
            if (list.remove(object)) {
                notifyItemRemoved(index);
                notifyItemRangeChanged(index, getItemCount());
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection) {
        boolean modified = false;

        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (collection.contains(object)) {
                synchronized (lock) {
                    int index = indexOf(object);
                    iterator.remove();
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, getItemCount());
                }

                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection) {
        boolean modified = false;

        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (!collection.contains(object)) {
                synchronized (lock) {
                    int index = indexOf(object);
                    iterator.remove();
                    notifyItemRemoved(index);
                    notifyItemRangeChanged(index, getItemCount());
                }

                modified = true;
            }
        }

        return modified;
    }

    @Override
    public E set(int location, E object) {
        synchronized (lock) {
            E origin = list.set(location, object);
            notifyItemChanged(location);
            return origin;
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @NonNull
    @Override
    public List<E> subList(int start, int end) {
        return list.subList(start, end);
    }

    @NonNull
    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] array) {
        return list.toArray(array);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof List && list.equals(o);
    }

    public void replaceWith(List<E> data) {
        if (list.isEmpty() && data.isEmpty()) {
            return;
        }

        if (list.isEmpty()) {
            addAll(data);
            return;
        }

        if (data.isEmpty()) {
            clear();
            return;
        }

        // 首先将旧列表有、新列表没有的从旧列表去除
        retainAll(data);

        // 如果列表被完全清空了，那就直接全部插入好了
        if (list.isEmpty()) {
            addAll(data);
            return;
        }

        // 然后遍历新列表，对旧列表的数据更新、移动、增加
        for (int indexNew = 0; indexNew < data.size(); indexNew++) {
            E item = data.get(indexNew);

            int indexOld = indexOf(item);

            if (indexOld == -1) {
                add(indexNew, item);
            } else if (indexOld == indexNew) {
                set(indexNew, item);
            } else {
                list.remove(indexOld);
                list.add(indexNew, item);
                notifyItemMoved(indexOld, indexNew);
            }
        }
    }


    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    public int getAdapterPosition() {
        return mAdapterPosition;
    }

    @Override
    public void onViewAttachedToWindow(VH holder) {
        super.onViewAttachedToWindow(holder);
//        mAdapterPosition = Utils.getAdapterPosition(recyclerView, holder);

        if (mHeaderView == null ){
            mAdapterPosition = holder.getAdapterPosition();
        }else {
            mAdapterPosition = holder.getAdapterPosition() - 1;
        }
    }


}
