package tiiehenry.android.view.searchview;

public interface OnQueryTextListener {
    boolean onQueryTextSubmit(String query);
    void onQueryTextChange(String query);
}
