package com.techfly.demo.selfview.photepicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseFragmentActivity;
import com.techfly.demo.selfview.photepicker.fragment.ImagePagerFragment;

import java.util.List;


/**
 */
public class PhotoPagerActivity extends BaseFragmentActivity {

    private ImagePagerFragment pagerFragment;
    public final static String EXTRA_CURRENT_ITEM = "current_item";
    public final static String EXTRA_PHOTOS = "photos";
    public final static String EXTRA_EDITABLE = "editable";
    public View deleteIv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo_pager);
        setTranslucentStatus(R.color.main_bg);

        int currentItem = getIntent().getIntExtra(EXTRA_CURRENT_ITEM, 0);
        List<String> paths = getIntent().getStringArrayListExtra(EXTRA_PHOTOS);
        pagerFragment = (ImagePagerFragment) getSupportFragmentManager().findFragmentById(R.id.photoPagerFragment);
        pagerFragment.setPhotos(paths, currentItem);
        updateActionBarTitle();

        pagerFragment.getViewPager().addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateActionBarTitle();
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        deleteIv = findViewById(R.id.delete_iv);
        if (!getIntent().getBooleanExtra(EXTRA_EDITABLE, false)) {
            deleteIv.setVisibility(View.GONE);
        }
        deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* snackbar.setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (pagerFragment.getPaths().size() > 0) {
                            pagerFragment.getPaths().qq_add(index, deletedPath);
                        } else {
                            pagerFragment.getPaths().qq_add(deletedPath);
                        }
                        pagerFragment.getViewPager().getAdapter().notifyDataSetChanged();
                        pagerFragment.getViewPager().setCurrentItem(index, true);
                    }
                });*/

            }
        });

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS, pagerFragment.getPaths());
        setResult(RESULT_OK, intent);
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {

            Intent intent = new Intent();
            intent.putExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS, pagerFragment.getPaths());
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateActionBarTitle() {
    /*actionBar.setTitle(
        getString(R.string.image_index, pagerFragment.getViewPager().getCurrentItem() + 1,
            pagerFragment.getPaths().size()));
            */
    }
}
