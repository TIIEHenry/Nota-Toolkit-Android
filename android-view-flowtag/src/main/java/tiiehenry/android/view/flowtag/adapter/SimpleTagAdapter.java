package tiiehenry.android.view.flowtag.adapter;

import android.content.Context;
import android.view.View;

import tiiehenry.android.view.flowtag.R;

public class SimpleTagAdapter extends BaseTagAdapter<String, TagViewHolder> {

    public SimpleTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.flowtag_simple_item;
    }

    @Override
    protected TagViewHolder newViewHolder(View convertView) {
        return new TagViewHolder(convertView.findViewById(R.id.flowtag_simple_item_tv));
    }

    @Override
    protected void convert(TagViewHolder holder, String item, int position) {
        holder.textView.setText(item);

    }

}