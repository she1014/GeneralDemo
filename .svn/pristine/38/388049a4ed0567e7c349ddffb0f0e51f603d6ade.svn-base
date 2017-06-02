package com.techfly.demo.selfview.photepicker.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.techfly.demo.R;
import com.techfly.demo.selfview.photepicker.entity.PhotoDirectory;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;
import static android.provider.MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME;
import static android.provider.MediaStore.Images.ImageColumns.BUCKET_ID;
import static android.provider.MediaStore.MediaColumns.DATA;
import static android.provider.MediaStore.MediaColumns.DATE_ADDED;

/**
 * Created by donglua on 15/5/31.
 */
public class MediaStoreHelper {

    public final static int INDEX_ALL_PHOTOS = 0;


    public static void getPhotoDirs(FragmentActivity activity, PhotosResultCallback resultCallback) {
        activity.getSupportLoaderManager()
                .initLoader(0, null, new PhotoDirLoaderCallbacks(activity, resultCallback));
    }


    static class PhotoDirLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {

        Context context;
        PhotosResultCallback resultCallback;
        ContentResolver cr;
        // ����ͼ�б�
       // HashMap<String, String> thumbnailList = new HashMap<String, String>();

        public PhotoDirLoaderCallbacks(Context context, PhotosResultCallback resultCallback) {
            this.context = context;
            this.resultCallback = resultCallback;
            cr = context.getContentResolver();
        }

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            return new PhotoDirectoryLoader(context);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

            if (data == null) return;
            //getThumbnail();
            List<PhotoDirectory> directories = new ArrayList<>();
            PhotoDirectory photoDirectoryAll = new PhotoDirectory();
            photoDirectoryAll.setName(context.getString(R.string.all_image));
            photoDirectoryAll.setId("ALL");

            while (data.moveToNext()) {

                int imageId = data.getInt(data.getColumnIndexOrThrow(_ID));
                String bucketId = data.getString(data.getColumnIndexOrThrow(BUCKET_ID));
                String name = data.getString(data.getColumnIndexOrThrow(BUCKET_DISPLAY_NAME));
                String path = data.getString(data.getColumnIndexOrThrow(DATA));

                PhotoDirectory photoDirectory = new PhotoDirectory();
                photoDirectory.setId(bucketId);
                photoDirectory.setName(name);

                if (!directories.contains(photoDirectory)) {
                    photoDirectory.setCoverPath(path);
                    photoDirectory.addPhoto(imageId, path);
                    photoDirectory.setDateAdded(data.getLong(data.getColumnIndexOrThrow(DATE_ADDED)));
                    directories.add(photoDirectory);
                } else {
                    directories.get(directories.indexOf(photoDirectory)).addPhoto(imageId, path);
                }

                photoDirectoryAll.addPhoto(imageId, path);
            }
            if (photoDirectoryAll.getPhotoPaths().size() > 0) {
                photoDirectoryAll.setCoverPath(photoDirectoryAll.getPhotoPaths().get(0));
                directories.add(INDEX_ALL_PHOTOS, photoDirectoryAll);
            }
            if (resultCallback != null) {
                resultCallback.onResultCallback(directories);
            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }


        /**
         * �����ݿ��еõ�����ͼ
         *
         * @param cur
         */
        private void getThumbnailColumnData(Cursor cur) {
            if (cur.moveToFirst()) {
                int _id;
                int image_id;
                String image_path;
                int _idColumn = cur.getColumnIndex(MediaStore.Images.Thumbnails._ID);
                int image_idColumn = cur.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID);
                int dataColumn = cur.getColumnIndex(MediaStore.Images.Thumbnails.DATA);

                do {
                    // Get the field values
                    _id = cur.getInt(_idColumn);
                    image_id = cur.getInt(image_idColumn);
                    image_path = cur.getString(dataColumn);

                    // Do something with the values.
                    // Log.i(TAG, _id + " image_id:" + image_id + " path:"
                    // + image_path + "---");
                    // HashMap<String, String> hash = new HashMap<String, String>();
                    // hash.put("image_id", image_id + "");
                    // hash.put("path", image_path);
                    // thumbnailList.qq_add(hash);
                    //thumbnailList.put("" + image_id, image_path);
                } while (cur.moveToNext());
            }
        }

        /**
         * �õ�����ͼ��������Ҫ�õ�����ͼƬ��IDֵ
         */
        private void getThumbnail() {


            String[] projection = {MediaStore.Images.Thumbnails._ID, MediaStore.Images.Thumbnails.IMAGE_ID,
                    MediaStore.Images.Thumbnails.DATA};
            Cursor cursor1 = cr.query(MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, projection,
                    null, null, null);
         /*   Cursor cursor1 = MediaStore.Images.Thumbnails.queryMiniThumbnails(cr, MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                    MediaStore.Images.Thumbnails.MINI_KIND, projection);*/
            getThumbnailColumnData(cursor1);
            cursor1.close();
        }


    }


    public interface PhotosResultCallback {
        void onResultCallback(List<PhotoDirectory> directories);
    }


}
