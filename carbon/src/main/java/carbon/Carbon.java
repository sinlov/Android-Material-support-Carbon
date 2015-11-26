package carbon;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import carbon.animation.AnimUtils;
import carbon.animation.AnimatedView;
import carbon.drawable.RippleDrawable;
import carbon.drawable.RippleDrawableCompat;
import carbon.drawable.RippleDrawableLollipop;
import carbon.drawable.RippleView;
import carbon.widget.InsetView;
import carbon.widget.TintedView;
import carbon.widget.TouchMarginView;

/**
 * Created by Marcin on 2014-12-18.
 */
public class Carbon {
    private Carbon() {
    }

    public static boolean antiAlias = true;
    public static boolean dim = true;

    public static float getDip(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, context.getResources().getDisplayMetrics());
    }

    public static float getSp(Context context) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 1, context.getResources().getDisplayMetrics());
    }

    public static void initRippleDrawable(RippleView rippleView, AttributeSet attrs, int defStyleAttr) {
        View view = (View) rippleView;
        if (view.isInEditMode())
            return;

        TypedArray a = view.getContext().obtainStyledAttributes(attrs, R.styleable.Carbon, defStyleAttr, 0);
        int color = a.getColor(R.styleable.Carbon_carbon_rippleColor, 0);

        if (color != 0) {
            RippleDrawable.Style style = RippleDrawable.Style.values()[a.getInt(R.styleable.Carbon_carbon_rippleStyle, RippleDrawable.Style.Background.ordinal())];
            boolean useHotspot = a.getBoolean(R.styleable.Carbon_carbon_rippleHotspot, true);

            RippleDrawable rippleDrawable;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                rippleDrawable = new RippleDrawableLollipop(color, style == RippleDrawable.Style.Background ? view.getBackground() : null, style);
            } else {
                rippleDrawable = new RippleDrawableCompat(color, style == RippleDrawable.Style.Background ? view.getBackground() : null, view.getContext(), style);
            }
            rippleDrawable.setCallback(view);
            rippleDrawable.setHotspotEnabled(useHotspot);
            rippleView.setRippleDrawable(rippleDrawable);
        }

        a.recycle();
    }

    public static void initTouchMargin(TouchMarginView view, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = ((View) view).getContext().obtainStyledAttributes(attrs, R.styleable.Carbon, defStyleAttr, 0);

        int touchMarginAll = (int) a.getDimension(R.styleable.Carbon_carbon_touchMargin, 0);
        if (touchMarginAll > 0) {
            view.setTouchMargin(touchMarginAll, touchMarginAll, touchMarginAll, touchMarginAll);
        } else {
            int top = (int) a.getDimension(R.styleable.Carbon_carbon_touchMarginTop, 0);
            int left = (int) a.getDimension(R.styleable.Carbon_carbon_touchMarginLeft, 0);
            int right = (int) a.getDimension(R.styleable.Carbon_carbon_touchMarginRight, 0);
            int bottom = (int) a.getDimension(R.styleable.Carbon_carbon_touchMarginBottom, 0);
            if (top > 0 || left > 0 || right > 0 || bottom > 0)
                view.setTouchMargin(left, top, right, bottom);
        }

        a.recycle();
    }

    public static void initInset(InsetView view, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = ((View) view).getContext().obtainStyledAttributes(attrs, R.styleable.Carbon, defStyleAttr, 0);

        if (a.hasValue(R.styleable.Carbon_carbon_inset)) {
            int touchMarginAll = (int) a.getDimension(R.styleable.Carbon_carbon_inset, InsetView.INSET_NULL);
            view.setInset(touchMarginAll, touchMarginAll, touchMarginAll, touchMarginAll);
        } else {
            int top = (int) a.getDimension(R.styleable.Carbon_carbon_insetTop, InsetView.INSET_NULL);
            int left = (int) a.getDimension(R.styleable.Carbon_carbon_insetLeft, InsetView.INSET_NULL);
            int right = (int) a.getDimension(R.styleable.Carbon_carbon_insetRight, InsetView.INSET_NULL);
            int bottom = (int) a.getDimension(R.styleable.Carbon_carbon_insetBottom, InsetView.INSET_NULL);
            view.setInset(left, top, right, bottom);
        }

        view.setInsetColor(a.getColor(R.styleable.Carbon_carbon_insetColor, 0));

        a.recycle();
    }

    public static void initTint(TintedView view, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = ((View) view).getContext().obtainStyledAttributes(attrs, R.styleable.Carbon, defStyleAttr, 0);

        if(a.hasValue(R.styleable.Carbon_carbon_tint)) {
            try{
                view.setTint(a.getColor(R.styleable.Carbon_carbon_tint,0));
            }catch (Exception e) {
                view.setTint(a.getColorStateList(R.styleable.Carbon_carbon_tint));
            }
        }else{
            view.setTint(null);
        }

        a.recycle();
    }

    static Paint paint = new Paint();

    public static void drawDebugInfo(ViewGroup viewGroup, Canvas canvas) {
        paint.setAlpha(255);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        Rect rect = new Rect();
        float vertLine = Math.min(10, viewGroup.getWidth() / 3);
        float horzLine = Math.min(10, viewGroup.getHeight() / 3);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).getHitRect(rect);
            paint.setColor(0x7fff0000);
            canvas.drawRect(rect, paint);
            //paint.setStrokeWidth(2);
            //canvas.drawLine(rect.left,rect.top,rect.left+vertLine,rect.top,paint);

            viewGroup.getChildAt(i).getDrawingRect(rect);
            rect.offset(viewGroup.getChildAt(i).getLeft(), viewGroup.getChildAt(i).getTop());
            paint.setColor(0x7f00ff00);
            canvas.drawRect(rect, paint);
        }

    }

    public static void initAnimations(AnimatedView view, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = ((View) view).getContext().obtainStyledAttributes(attrs, R.styleable.Carbon, defStyleAttr, 0);

        view.setInAnimation(AnimUtils.Style.values()[a.getInt(R.styleable.Carbon_carbon_inAnimation, 0)]);
        view.setOutAnimation(AnimUtils.Style.values()[a.getInt(R.styleable.Carbon_carbon_outAnimation, 0)]);

        a.recycle();
    }

    public static int getThemeColor(Context context, int attr) {
        Resources.Theme theme = context.getTheme();
        TypedValue typedvalueattr = new TypedValue();
        theme.resolveAttribute(attr, typedvalueattr, true);
        return typedvalueattr.resourceId != 0 ? context.getResources().getColor(typedvalueattr.resourceId) : typedvalueattr.data;
    }
}
