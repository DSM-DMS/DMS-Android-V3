//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Px;
import android.support.v7.cardview.R.attr;
import android.support.v7.cardview.R.color;
import android.support.v7.cardview.R.style;
import android.support.v7.cardview.R.styleable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public class Api17CardView extends FrameLayout {
    private static final int[] COLOR_BACKGROUND_ATTR = new int[]{16842801};
    private static final CardViewImpl IMPL;
    private boolean mCompatPadding;
    private boolean mPreventCornerOverlap;
    int mUserSetMinWidth;
    int mUserSetMinHeight;
    final Rect mContentPadding;
    final Rect mShadowBounds;
    private final CardViewDelegate mCardViewDelegate;

    public Api17CardView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public Api17CardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, attr.cardViewStyle);
    }

    public Api17CardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContentPadding = new Rect();
        this.mShadowBounds = new Rect();
        this.mCardViewDelegate = new CardViewDelegate() {
            private Drawable mCardBackground;

            public void setCardBackground(Drawable drawable) {
                this.mCardBackground = drawable;
                Api17CardView.this.setBackgroundDrawable(drawable);
            }

            public boolean getUseCompatPadding() {
                return Api17CardView.this.getUseCompatPadding();
            }

            public boolean getPreventCornerOverlap() {
                return Api17CardView.this.getPreventCornerOverlap();
            }

            public void setShadowPadding(int left, int top, int right, int bottom) {
                Api17CardView.this.mShadowBounds.set(left, top, right, bottom);
                Api17CardView.super.setPadding(left + Api17CardView.this.mContentPadding.left, top + Api17CardView.this.mContentPadding.top, right + Api17CardView.this.mContentPadding.right, bottom + Api17CardView.this.mContentPadding.bottom);
            }

            public void setMinWidthHeightInternal(int width, int height) {
                if (width > Api17CardView.this.mUserSetMinWidth) {
                    Api17CardView.super.setMinimumWidth(width);
                }

                if (height > Api17CardView.this.mUserSetMinHeight) {
                    Api17CardView.super.setMinimumHeight(height);
                }

            }

            public Drawable getCardBackground() {
                return this.mCardBackground;
            }

            public View getCardView() {
                return Api17CardView.this;
            }
        };
        TypedArray a = context.obtainStyledAttributes(attrs, styleable.CardView, defStyleAttr, style.CardView);
        ColorStateList backgroundColor;
        if (a.hasValue(styleable.CardView_cardBackgroundColor)) {
            backgroundColor = a.getColorStateList(styleable.CardView_cardBackgroundColor);
        } else {
            TypedArray aa = this.getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            int themeColorBackground = aa.getColor(0, 0);
            aa.recycle();
            float[] hsv = new float[3];
            Color.colorToHSV(themeColorBackground, hsv);
            backgroundColor = ColorStateList.valueOf(hsv[2] > 0.5F ? this.getResources().getColor(color.cardview_light_background) : this.getResources().getColor(color.cardview_dark_background));
        }

        float radius = a.getDimension(styleable.CardView_cardCornerRadius, 0.0F);
        float elevation = a.getDimension(styleable.CardView_cardElevation, 0.0F);
        float maxElevation = a.getDimension(styleable.CardView_cardMaxElevation, 0.0F);
        this.mCompatPadding = a.getBoolean(styleable.CardView_cardUseCompatPadding, false);
        this.mPreventCornerOverlap = a.getBoolean(styleable.CardView_cardPreventCornerOverlap, true);
        int defaultPadding = a.getDimensionPixelSize(styleable.CardView_contentPadding, 0);
        this.mContentPadding.left = a.getDimensionPixelSize(styleable.CardView_contentPaddingLeft, defaultPadding);
        this.mContentPadding.top = a.getDimensionPixelSize(styleable.CardView_contentPaddingTop, defaultPadding);
        this.mContentPadding.right = a.getDimensionPixelSize(styleable.CardView_contentPaddingRight, defaultPadding);
        this.mContentPadding.bottom = a.getDimensionPixelSize(styleable.CardView_contentPaddingBottom, defaultPadding);
        if (elevation > maxElevation) {
            maxElevation = elevation;
        }

        this.mUserSetMinWidth = a.getDimensionPixelSize(styleable.CardView_android_minWidth, 0);
        this.mUserSetMinHeight = a.getDimensionPixelSize(styleable.CardView_android_minHeight, 0);
        a.recycle();
        IMPL.initialize(this.mCardViewDelegate, context, backgroundColor, radius, elevation, maxElevation);
    }

    public void setPadding(int left, int top, int right, int bottom) {
    }

    public void setPaddingRelative(int start, int top, int end, int bottom) {
    }

    public boolean getUseCompatPadding() {
        return this.mCompatPadding;
    }

    public void setUseCompatPadding(boolean useCompatPadding) {
        if (this.mCompatPadding != useCompatPadding) {
            this.mCompatPadding = useCompatPadding;
            IMPL.onCompatPaddingChanged(this.mCardViewDelegate);
        }

    }

    public void setContentPadding(@Px int left, @Px int top, @Px int right, @Px int bottom) {
        this.mContentPadding.set(left, top, right, bottom);
        IMPL.updatePadding(this.mCardViewDelegate);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!(IMPL instanceof CardViewApi21Impl)) {
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);
            int heightMode;
            switch (widthMode) {
                case -2147483648:
                case 1073741824:
                    heightMode = (int) Math.ceil((double) IMPL.getMinWidth(this.mCardViewDelegate));
                    widthMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(heightMode, MeasureSpec.getSize(widthMeasureSpec)), widthMode);
                case 0:
                default:
                    heightMode = MeasureSpec.getMode(heightMeasureSpec);
                    switch (heightMode) {
                        case -2147483648:
                        case 1073741824:
                            int minHeight = (int) Math.ceil((double) IMPL.getMinHeight(this.mCardViewDelegate));
                            heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(minHeight, MeasureSpec.getSize(heightMeasureSpec)), heightMode);
                        case 0:
                        default:
                            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
                    }
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void setMinimumWidth(int minWidth) {
        this.mUserSetMinWidth = minWidth;
        super.setMinimumWidth(minWidth);
    }

    public void setMinimumHeight(int minHeight) {
        this.mUserSetMinHeight = minHeight;
        super.setMinimumHeight(minHeight);
    }

    public void setCardBackgroundColor(@ColorInt int color) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, ColorStateList.valueOf(color));
    }

    public void setCardBackgroundColor(@Nullable ColorStateList color) {
        IMPL.setBackgroundColor(this.mCardViewDelegate, color);
    }

    @NonNull
    public ColorStateList getCardBackgroundColor() {
        return IMPL.getBackgroundColor(this.mCardViewDelegate);
    }

    @Px
    public int getContentPaddingLeft() {
        return this.mContentPadding.left;
    }

    @Px
    public int getContentPaddingRight() {
        return this.mContentPadding.right;
    }

    @Px
    public int getContentPaddingTop() {
        return this.mContentPadding.top;
    }

    @Px
    public int getContentPaddingBottom() {
        return this.mContentPadding.bottom;
    }

    public void setRadius(float radius) {
        IMPL.setRadius(this.mCardViewDelegate, radius);
    }

    public float getRadius() {
        return IMPL.getRadius(this.mCardViewDelegate);
    }

    public void setCardElevation(float elevation) {
        IMPL.setElevation(this.mCardViewDelegate, elevation);
    }

    public float getCardElevation() {
        return IMPL.getElevation(this.mCardViewDelegate);
    }

    public void setMaxCardElevation(float maxElevation) {
        IMPL.setMaxElevation(this.mCardViewDelegate, maxElevation);
    }

    public float getMaxCardElevation() {
        return IMPL.getMaxElevation(this.mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return this.mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean preventCornerOverlap) {
        if (preventCornerOverlap != this.mPreventCornerOverlap) {
            this.mPreventCornerOverlap = preventCornerOverlap;
            IMPL.onPreventCornerOverlapChanged(this.mCardViewDelegate);
        }

    }

    static {
        IMPL = new CardViewApi17Impl();
        IMPL.initStatic();
    }
}
