package net.appsfreeforall.sopadeletras.delabiblia.help;


import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HelpAdapter extends PagerAdapter {

    private List<View> viewList;
    public HelpAdapter() {
        this.viewList = new ArrayList<>();
    }



    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = viewList.get(position);
        collection.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }



    @Override
    public int getCount() {
        return viewList.size();
    }



    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }



    public void setData(@Nullable List<View> list) {
        this.viewList.clear();
        if (list != null && !list.isEmpty()) {
            this.viewList.addAll(list);
        }

        notifyDataSetChanged();
    }

}