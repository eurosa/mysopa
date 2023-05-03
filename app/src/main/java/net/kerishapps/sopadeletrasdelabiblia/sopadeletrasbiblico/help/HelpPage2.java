package net.kerishapps.sopadeletrasdelabiblia.sopadeletrasbiblico.help;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import net.kerishapps.sopadeletrasdelabiblia.sopadeletrasbiblico.R;
import net.kerishapps.sopadeletrasdelabiblia.sopadeletrasbiblico.data.Constants;
import net.kerishapps.sopadeletrasdelabiblia.sopadeletrasbiblico.data.Settings;


public class HelpPage2 extends Help {

    public HelpPage2(Context context) {
        super(context);
        this.context = context;
    }


    public HelpPage2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    public HelpPage2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        boolean night = Settings.getBooleanValue(context, Constants.NIGHT_MODE_ON, false);

        ImageView hint = findViewById(R.id.hint);
        TextView hints_left = findViewById(R.id.hints_left);

        if(night) {
            hint.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.hint_icon_n));
            hints_left.setTextColor(ContextCompat.getColor(context, R.color.button_text_n));
        }else{
            hint.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.hint_icon));
            hints_left.setTextColor(ContextCompat.getColor(context, R.color.button_text));
        }

        ((TextView)findViewById(R.id.title)).setText(resources.getString(R.string.hints_title));
        ((TextView)findViewById(R.id.text)).setText(resources.getString(R.string.hints_text));
    }

}
