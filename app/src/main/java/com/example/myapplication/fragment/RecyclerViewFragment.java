package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;
import com.example.myapplication.adapter.ExampleAdapter;
import com.example.myapplication.viewmodel.MyViewModel;
import com.example.myapplication.viewmodel.MyViewModelFactory;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecyclerViewFragment extends DaggerFragment {

    public static final String TAG = RecyclerViewFragment.class.getSimpleName();

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.tv_rv_title) TextView mTextView;

    @Inject DatabaseHelper mDatabaseHelper;
    @Inject MyViewModelFactory mMyViewModelFactory;

    private CompositeDisposable mCompositeDisposable;

    private MyViewModel mViewModel;

    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_recycler_view_example, container, false);
        return view;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = new CompositeDisposable();

        mTextView.setText("This is Fragment");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        ExampleAdapter adapter = new ExampleAdapter(getActivity());

        subscribe(adapter.onItemClick(), item -> Toast.makeText(getActivity(), item, Toast.LENGTH_LONG).show());

        adapter.onItemCheck().observe(getViewLifecycleOwner(), pair -> Toast.makeText(getActivity(), "Text: " + pair.first + ", Is Checked: " + pair.second, Toast.LENGTH_LONG).show());

        mRecyclerView.setAdapter(adapter);

        mViewModel = new ViewModelProvider(this, mMyViewModelFactory).get(MyViewModel.class);
    }

    @Override public void onStart() {
        super.onStart();
        subscribe(mDatabaseHelper.onNewData(), data -> Toast.makeText(getActivity(), data, Toast.LENGTH_LONG).show());
        mDatabaseHelper.retrieveData();
    }

    @Override public void onStop() {
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
            mCompositeDisposable = null;
        }
        super.onStop();
    }

    private <T> void subscribe(Observable<T> observable, Consumer<T> consumer) {
        mCompositeDisposable.add(observable.subscribe(consumer));
    }

    private <T> void subscribe(Observable<T> observable, Consumer<T> consumer, Consumer<Throwable> onError) {
        mCompositeDisposable.add(observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, onError));
    }
}
