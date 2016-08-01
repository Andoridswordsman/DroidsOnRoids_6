package com.droidsonroids.materialmusicfacts.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.droidsonroids.materialmusicfacts.R;
import com.droidsonroids.materialmusicfacts.common.Constants;
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan;
import uk.co.chrisjenx.calligraphy.TypefaceUtils;

public class HeaderLayout extends LinearLayout {

    @BindView(R.id.textview_title) TextView mTextViewTitle;
    @BindView(R.id.view_round) View mViewRound;

    private String titleText;
    private String subtitleText;
    private Drawable background;

    public HeaderLayout(final Context context) {
        this(context, null, 0);
    }

    public HeaderLayout(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderLayout(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_header, this);
        ButterKnife.bind(this);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HeaderLayout);
        try {
            titleText = array.getText(R.styleable.HeaderLayout_title).toString();
            subtitleText = array.getText(R.styleable.HeaderLayout_subtitle).toString();
            background = array.getDrawable(R.styleable.HeaderLayout_backgroundDrawable);
        } finally {
            array.recycle();
        }
        prepareTitleAndSubtitle(context);
        mViewRound.setBackground(background);
        invalidate();
    }

    private void prepareTitleAndSubtitle(final Context context) {
        SpannableStringBuilder sBuilder = new SpannableStringBuilder();
        sBuilder.append(titleText);
        sBuilder.append("\n");
        sBuilder.append(subtitleText);

        int totalLength = titleText.length() + subtitleText.length() + 1;
        CalligraphyTypefaceSpan titleTypeFaceSpan = new CalligraphyTypefaceSpan(TypefaceUtils.load(context.getAssets(), Constants.ROBOTO_REGULAR_PATH));

        sBuilder.setSpan(titleTypeFaceSpan, 0, totalLength, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
        sBuilder.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.textsize_cardview_title)), 0, titleText.length(),
                SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
        sBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.primary_text)), 0, titleText.length(), SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);

        sBuilder.setSpan(new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.textsize_cardview_subtitle)), totalLength - subtitleText.length(),
                totalLength, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);
        sBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.secondary_text)), totalLength - subtitleText.length(), totalLength, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE);

        mTextViewTitle.setText(sBuilder);
    }
}
