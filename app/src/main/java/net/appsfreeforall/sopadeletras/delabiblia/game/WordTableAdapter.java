package net.appsfreeforall.sopadeletras.delabiblia.game;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import net.appsfreeforall.sopadeletras.delabiblia.R;
import net.appsfreeforall.sopadeletras.delabiblia.data.Constants;
import net.appsfreeforall.sopadeletras.delabiblia.data.Settings;

import java.util.List;



public class WordTableAdapter extends BaseAdapter implements ListAdapter {



    private Context context;
    private List<Word> words;
    private boolean night;
    public float textSize;

    public WordTableAdapter(Context ctx, List<Word> wordList) {
        context = ctx;
        words = wordList;
        night = Settings.getBooleanValue(ctx, Constants.NIGHT_MODE_ON, false);
    }



    public void update(){
        night = Settings.getBooleanValue(context, Constants.NIGHT_MODE_ON, false);
        notifyDataSetChanged();
    }


    public void setWords(List<Word> wordList){
        words = wordList;
    }


    @Override
    public void notifyDataSetChanged() {
        if(words.size() == 0)return;
        super.notifyDataSetChanged();
    }



    public int getCount() {
        return words.size();

    }


    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }



    @Override
    public boolean isEnabled(int position) {
        return false;
    }



    public void setWordFound() {
        notifyDataSetChanged();
    }

    public Object getItem(int position) {
        return words.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.word_item, null);
        }

        if(position < words.size()) {

            Word word = (Word) getItem(position);
            WordView tv =  v.findViewById(R.id.txt_word);
            tv.setWord(word);
            v.setFocusable(false);
            tv.setFocusable(false);

            if (night) {
                tv.setTextColor(ContextCompat.getColor(context, R.color.text_color_n));
            } else {
                tv.setTextColor(ContextCompat.getColor(context, R.color.text_color));
            }

            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        return v;
    }

}
