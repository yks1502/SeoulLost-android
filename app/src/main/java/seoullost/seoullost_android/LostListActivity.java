package seoullost.seoullost_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LostListActivity extends AppCompatActivity {
    private ApiApplication apiApplication;
    private ApiService apiService;
    private ItemResponse lostResponse;
    private LinearLayoutManager linearLayoutManager;
    private LostListAdapter lostListAdapter;

    @BindView(R.id.lostlist_view)
    protected RecyclerView lostRecyclerView;

    private boolean isLoading = false;

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = linearLayoutManager.getChildCount();
            int totalItemCount = linearLayoutManager.getItemCount();
            int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && !isLoading && lostResponse.getNext() != -1) {
                getNextPage();
                isLoading = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lostlist);
        apiApplication = (ApiApplication) getApplicationContext();
        apiService = apiApplication.getApiService();
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        lostListAdapter = new LostListAdapter();
        lostRecyclerView.setLayoutManager(linearLayoutManager);
        lostRecyclerView.setItemAnimator(new DefaultItemAnimator());
        lostRecyclerView.setAdapter(lostListAdapter);
        lostRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        Call<ItemResponse> lostListCall = apiService.getLostList(1);
        lostListCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                lostResponse = response.body();
                lostListAdapter.addLostList(lostResponse.getResults());
                lostListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {

            }
        });
    }

    private void getNextPage() {
        int nextPage = lostResponse.getNext();
        Call<ItemResponse> lostListCall = apiService.getLostList(nextPage);
        lostListCall.enqueue(new Callback<ItemResponse>() {
            @Override
            public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                lostResponse = response.body();
                lostListAdapter.addLostList(lostResponse.getResults());
                lostListAdapter.notifyDataSetChanged();
                isLoading = false;
            }

            @Override
            public void onFailure(Call<ItemResponse> call, Throwable t) {

            }
        });
    }
}
