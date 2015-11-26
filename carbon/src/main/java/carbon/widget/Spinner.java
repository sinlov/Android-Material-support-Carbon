package carbon.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;

import carbon.Carbon;
import carbon.R;
import carbon.drawable.ControlFocusedColorStateList;

/**
 * Created by Marcin on 2015-06-11.
 */
public class Spinner extends TextView implements TintedView {
    PopupMenu popupMenu;
    private int selectedItem;
    Adapter defaultAdapter;
    AdapterView.OnItemSelectedListener onItemSelectedListener;

    private boolean isShowingPopup = false;

    public Spinner(Context context) {
        this(context, null);
    }

    public Spinner(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.carbon_spinnerStyle);
    }

    public Spinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        try {
            Resources.Theme theme = context.getTheme();
            TypedValue typedvalueattr = new TypedValue();
            theme.resolveAttribute(R.attr.colorControlNormal, typedvalueattr, true);
            int color = typedvalueattr.resourceId != 0 ? context.getResources().getColor(typedvalueattr.resourceId) : typedvalueattr.data;

            int size = (int) (Carbon.getDip(getContext()) * 24);
            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);

            SVG svg3 = SVG.getFromResource(context, R.raw.carbon_dropdown);
            Canvas canvas = new Canvas(bitmap);
            svg3.setDocumentWidth(bitmap.getWidth());
            svg3.setDocumentHeight(bitmap.getHeight());
            svg3.renderToCanvas(canvas);

            BitmapDrawable dropdown = new BitmapDrawable(bitmap);
            dropdown.setBounds(0, 0, size, size);
            dropdown.setAlpha(Color.alpha(color));
            dropdown.setColorFilter(new LightingColorFilter(0, color));
            setCompoundDrawables(null, null, dropdown, null);
        } catch (SVGParseException e) {

        } catch (IllegalArgumentException e) {

        }

        popupMenu = new PopupMenu(context);
        defaultAdapter = new Adapter();
        popupMenu.setAdapter(defaultAdapter);
        popupMenu.setTint(getTint());
        popupMenu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowingPopup = false;
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show(Spinner.this);
                isShowingPopup = true;
            }
        });
    }

    public int getSelectedIndex() {
        return selectedItem;
    }

    public void setAdapter(final RecyclerView.Adapter adapter) {
        if (adapter == null) {
            popupMenu.setAdapter(defaultAdapter);
            defaultAdapter.setOnItemClickedListener(onItemClickedListener);
        } else {
            popupMenu.setAdapter(adapter);
            adapter.setOnItemClickedListener(onItemClickedListener);
        }
    }

    public RecyclerView.Adapter getAdapter() {
        return popupMenu.getAdapter();
    }

    RecyclerView.OnItemClickedListener onItemClickedListener = new RecyclerView.OnItemClickedListener() {
        @Override
        public void onItemClicked(int position) {
            setText(popupMenu.getAdapter().getItem(position).toString());
            selectedItem = position;
            if(onItemSelectedListener!=null)
                onItemSelectedListener.onItemSelected(null,null,selectedItem,0);
            popupMenu.dismiss();
        }
    };

    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setItems(String[] items) {
        popupMenu.setAdapter(defaultAdapter);
        defaultAdapter.setOnItemClickedListener(onItemClickedListener);
        defaultAdapter.setItems(items);
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder, String> {

        private String[] items = new String[0];

        @Override
        public String getItem(int position) {
            return items[position];
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.carbon_popup_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            super.onBindViewHolder(holder, position);
            holder.tv.setText(items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }


        public void setItems(String[] items) {
            this.items = items;
            notifyDataSetChanged();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.carbon_itemText);
        }
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        boolean result = super.setFrame(l, t, r, b);

        if (popupMenu != null)
            popupMenu.update();

        return result;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (isShowingPopup)
            popupMenu.showImmediate(Spinner.this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (isShowingPopup)
            popupMenu.dismissImmediate();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        //begin boilerplate code that allows parent classes to save state
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);
        //end

        ss.stateToSave = this.isShowingPopup ? 1 : 0;

        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        //begin boilerplate code so parent classes can restore state
        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        //end

        this.isShowingPopup = ss.stateToSave > 0;
    }

    static class SavedState implements Parcelable {
        public static final SavedState EMPTY_STATE = new SavedState() {
        };

        int stateToSave;

        Parcelable superState;

        SavedState() {
            superState = null;
        }

        SavedState(Parcelable superState) {
            this.superState = superState != EMPTY_STATE ? superState : null;
        }

        private SavedState(Parcel in) {
            Parcelable superState = in.readParcelable(EditText.class.getClassLoader());
            this.superState = superState != null ? superState : EMPTY_STATE;
            this.stateToSave = in.readInt();
        }

        @Override
        public int describeContents() {
            return 0;
        }


        @Override
        public void writeToParcel(@NonNull Parcel out, int flags) {
            out.writeParcelable(superState, flags);
            out.writeInt(this.stateToSave);
        }

        public Parcelable getSuperState() {
            return superState;
        }

        //required field that makes Parcelables from a Parcel
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }


    // -------------------------------
    // tint
    // -------------------------------

    ColorStateList tint;

    @Override
    public void setTint(ColorStateList list) {
        this.tint = list;
        if (popupMenu != null)
            popupMenu.setTint(list);
    }

    @Override
    public void setTint(int color) {
        if (color == 0) {
            setTint(new ControlFocusedColorStateList(getContext()));
        } else {
            setTint(ColorStateList.valueOf(color));
        }
    }

    @Override
    public ColorStateList getTint() {
        return tint;
    }
}
