package com.elearning.elearning.adapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.elearning.elearning.R;
import com.elearning.elearning.base.BaseRecyclerAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by MinhQuan on 30/06/2017.
 */

public class NavAdapter extends BaseRecyclerAdapter<NavAdapter.Item> {
    private Context context;
    private OnItemMenuListener onItemClickListener;
    private String selectedId;

    public NavAdapter(Context context) {
        this.context = context;
        initMenu();
    }

    public void setSelectedId(String selectedId) {
        this.selectedId = selectedId;
    }


    private Drawable getIcon(int id) {
        return context.getResources().getDrawable(id);
    }

    private String getTitle(int id) {
        return context.getString(id);
    }

    private void initMenu() {
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item(getTitle(R.string.nav_home), getIcon(R.drawable.menu_home)));
        itemList.add(new Item(getTitle(R.string.nav_exam), getIcon(R.drawable.menu_exam)));
        itemList.add(new Item(getTitle(R.string.nav_history), getIcon(R.drawable.menu_history)));
        itemList.add(new Item(getTitle(R.string.nav_notifications), getIcon(R.drawable.menu_nofication)));
        itemList.add(new Item(getTitle(R.string.nav_settings), getIcon(R.drawable.menu_profile)));
        itemList.add(new Item(getTitle(R.string.nav_about_us), getIcon(R.drawable.menu_information)));
        itemList.add(new Item(getTitle(R.string.nav_logout), getIcon(R.drawable.menu_logout)));
        //set Selected default is Home
        this.selectedId = itemList.get(0).getTitle();
        this.items = itemList;
    }

    public void setOnItemClickListener(OnItemMenuListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.nav_rows;
    }

    @Override
    protected void onBindViewHolder(RecyclerView.ViewHolder holder, int position, Item item) {
        NavAdapter.NavViewHolder viewHolder = (NavAdapter.NavViewHolder) holder;
        viewHolder.titleMenu.setText(item.getTitle());
        viewHolder.iconMenu.setBackground(item.getIcon());
        if (selectedId.equals(item.getTitle())) {
            viewHolder.menuRow.setBackgroundColor(context.getResources().getColor(R.color.color_selected_menu));
        } else {
            viewHolder.menuRow.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(getItemLayout(), parent, false);
        return new NavViewHolder(rootView);
    }

    private class NavViewHolder extends RecyclerView.ViewHolder {
        private TextView titleMenu;
        private ImageView iconMenu;
        private LinearLayout menuRow;

        NavViewHolder(View itemView) {
            super(itemView);
            titleMenu = (TextView) itemView.findViewById(R.id.txtTitleMenu);
            iconMenu = (ImageView) itemView.findViewById(R.id.iconMenu);
            menuRow = (LinearLayout) itemView.findViewById(R.id.menuRow);
            menuRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemMenuClick(titleMenu.getText().toString());
                    selectedId = titleMenu.getText().toString();
                    notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * Model
     */
    public class Item {
        //Title
        private String title;

        //Icon
        private Drawable icon;

        public Item() {
        }

        public Item(String title, Drawable icon) {
            this.title = title;
            this.icon = icon;
        }

        public Drawable getIcon() {
            return icon;
        }

        public void setIcon(Drawable icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public interface OnItemMenuListener {
        void onItemMenuClick(String id);
    }
}
