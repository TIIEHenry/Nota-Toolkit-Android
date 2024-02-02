package tiiehenry.android.view.searchview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchLayout extends FrameLayout {
    private EditText editSearch;
    private ImageView searchClearImgv;
    private ImageView searchImgv;
    private OnQueryTextListener onQueryTextListener;

    public SearchLayout(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public SearchLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public SearchLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public SearchLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public void inflate(LayoutInflater layoutInflater) {
        layoutInflater.inflate(R.layout.searchview_layout, this);
        editSearch = findViewById(R.id.et_search);
        searchClearImgv = findViewById(R.id.iv_clear);
        searchImgv = findViewById(R.id.iv_search);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (onQueryTextListener != null) {
                    onQueryTextListener.onQueryTextChange(editable.toString());
                }
                searchClearImgv.setVisibility(editable.toString().isEmpty() ? View.INVISIBLE : View.VISIBLE);
            }
        });
        editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3)) {
                    //点击搜索要做的操作
                    String s = v.getText().toString();
                    if (onQueryTextListener != null) {
                        return onQueryTextListener.onQueryTextSubmit(s);
                    }
                }
                return false;
            }
        });
        searchClearImgv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSearchText();
            }
        });
        searchClearImgv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clearSearchText();
                return true;
            }
        });
    }

    private void initView(Context context) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public EditText getEditText() {
        return editSearch;
    }

    public ImageView getClearView() {
        return searchClearImgv;
    }

    public ImageView getLogoView() {
        return searchImgv;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.onQueryTextListener = onQueryTextListener;
    }

    public OnQueryTextListener getOnQueryTextListener() {
        return onQueryTextListener;
    }

    public void clearSearchText() {
        editSearch.setText("");
    }

    public String getSearchText() {
        return editSearch.getText().toString();
    }

    public void setSearchText(String s) {
        editSearch.setText(s);
        editSearch.setSelection(s.length());
    }
}
