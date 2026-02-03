package nota.android.widget.searchview;

public interface OnQueryTextListener {
    boolean onQueryTextSubmit(String query);
    void onQueryTextChange(String query);
}
