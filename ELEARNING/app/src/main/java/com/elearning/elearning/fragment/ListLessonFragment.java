package com.elearning.elearning.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.elearning.elearning.R;
import com.elearning.elearning.adapter.LessonAdapter;
import com.elearning.elearning.base.BaseFragment;
import com.elearning.elearning.mvp.model.Lesson;
import com.elearning.elearning.mvp.presenter.ListLessonPresenter;
import com.elearning.elearning.mvp.view.ListLessonView;

import java.util.ArrayList;
import java.util.List;

import static com.elearning.elearning.prefs.Constant.NUMBER_COLUMNS_2;

/**
 * Created by vomin on 04/07/2017.
 */

public class ListLessonFragment extends BaseFragment implements ListLessonView {
    private RecyclerView rvListLesson;
    private ListLessonPresenter listLessonPresenter;
    private LessonAdapter lessonAdapter;
    //list Course
    private List<Lesson> listLesson = new ArrayList<>();

    @Override
    public int setFragmentView() {
        return R.layout.fragment_list_lesson;
    }

    @Override
    public void initView() {
        rvListLesson = (RecyclerView) view.findViewById(R.id.rvListLesson);
        rvListLesson.setLayoutManager(new GridLayoutManager(context, NUMBER_COLUMNS_2));
    }

    @Override
    public void initValue() {
        listLessonPresenter = new ListLessonPresenter(this);
        lessonAdapter = new LessonAdapter(context, listLesson, R.layout.item_lesson);
        rvListLesson.setAdapter(lessonAdapter);
        HomeFragment.setSendCourseID(new HomeFragment.onSendCourseID() {
            @Override
            public void onSend(int CourseId) {
                listLessonPresenter.getListLesson(3);
            }
        });
    }

    @Override
    public void initAction() {

    }

    @Override
    public void onGetListSuccess(List<Lesson> lessonList) {
        for (Lesson itemLesson : lessonList) {
            this.listLesson.add(itemLesson);
        }
       lessonAdapter.notifyDataSetChanged();
        Log.d("List Lesson", String.valueOf(lessonList));
    }

    @Override
    public void onGetListFail(String errorMessafe) {

    }
}
