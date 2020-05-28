package pl.mzlnk.emergencyspot.ui.view.filter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.ui.view.BaseLinearLayoutView;
import pl.mzlnk.emergencyspot.ui.view.menu.SelectableItem;

public class FilterItem extends BaseLinearLayoutView implements SelectableItem {

    private ImageView icon;
    private TextView label;

    private boolean selected = false;

    public FilterItem(Context context) {
        super(context);
    }

    public FilterItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected int xmlLayoutResId() {
        return R.layout.v_filter_item;
    }

    @Override
    protected void loadViewsFromXml() {
        this.icon = findViewById(R.id.v_filter_item_img_icon);
        this.label = findViewById(R.id.v_filter_item_text_label);
    }

    @Override
    protected void prepareViews() {
        updateUi();
    }

    @Override
    public void setItemSelected(boolean selected) {
        this.selected = selected;
        updateUi();
    }

    public void setIcon(int iconResId) {
        this.icon.setImageResource(iconResId);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    private void updateUi() {
        this.icon.setAlpha(this.selected ? 1 : 0.3F);
        this.label.setAlpha(this.selected ? 1 : 0.3F);
    }

}
