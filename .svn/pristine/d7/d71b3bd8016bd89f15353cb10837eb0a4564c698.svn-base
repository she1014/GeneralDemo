package com.techfly.demo.util;

import android.app.Dialog;
import android.content.Context;

import com.techfly.demo.activity.application.MyApplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {
    public static final int METHOD_GET = 1;
    public static final int METHOD_POST = 2;
    private static Dialog dialog;
    private static HttpClient client = new DefaultHttpClient();

    /**
     * 向指定的资源路径发送请求获取响应实体对象并返回
     *
     * @param uri    资源路径
     * @param params 向服务端发送请求时的实体数据
     * @param method 请求方法
     * @return
     * @throws IOException
     */
    public static HttpEntity getEntity(String uri, List<NameValuePair> params,
                                       int method) throws IOException {
        HttpEntity entity = null;
        // 创建客户端对象
        client = new DefaultHttpClient();
        client.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT
                , 20000);

        // 创建请求对象
        HttpUriRequest request = null;
        switch (method) {
            case METHOD_GET:
                StringBuilder sb = new StringBuilder(uri);
                if (params != null && !params.isEmpty()) {
                    sb.append('?');
                    for (NameValuePair pair : params) {
                        sb.append(pair.getName()).append('=')
                                .append(pair.getValue()).append('&');
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                ContentUtil.makeLog("GET请求", sb.toString());
                request = new HttpGet(sb.toString());
                break;
            case METHOD_POST:
                request = new HttpPost(uri);
                if (params != null && !params.isEmpty()) {
                    if (MyApplication.isTest) {
                        StringBuilder sbd = new StringBuilder(uri);
                        for (NameValuePair pair : params) {
                            sbd.append(pair.getName()).append('=')
                                    .append(pair.getValue()).append('&');
                        }
                        sbd.deleteCharAt(sbd.length() - 1);
                        ContentUtil.makeLog("(*￣3￣)╭", sbd.toString());
                    }
                    // 创建请求实体对象

                    UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
                            params, HTTP.UTF_8);
                    // 设置请求实体
                    ((HttpPost) request).setEntity(reqEntity);

                }
                break;
        }
        // 执行请求获取相应对象
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            entity = response.getEntity();
        }
        return entity;
    }

    /**
     * 获取实体对象的内容长度并返回
     *
     * @param entity
     * @return
     */
    public static long getEntity(HttpEntity entity) {
        long len = 0;
        if (entity != null) {
            len = entity.getContentLength();
        }
        return len;
    }

    /**
     * 获取指定的响应实体对象的网络输入流
     *
     * @param entity
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */

    public static InputStream getStream(HttpEntity entity)
            throws IllegalStateException, IOException {
        InputStream in = null;
        if (entity != null) {
            in = entity.getContent();
        }
        return in;
    }

    /**
     * 上传头像
     */
    public static String uploadImage(String url, File file, String param,
                                     Context context) {
       if (!file.exists()) {
            ContentUtil.makeLog("上传图片", "图片不存在");
            return null;
        }

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        FileBody fileBody = new FileBody(file, "image/jpeg");
        MultipartEntity entity = new MultipartEntity();
        // image 是服务端读取文件的 key
        entity.addPart(param, fileBody);

        post.setEntity(entity);

        try {
            HttpResponse response = client.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            String result = EntityUtils.toString(response.getEntity(), "utf-8");

            if (statusCode == HttpStatus.SC_OK) {
                ContentUtil.makeLog("上传图片", "成功");
                ContentUtil.makeLog("服务器图片地址", "" + result);
                return result;
            }


        } catch (ClientProtocolException e) {
            ContentUtil.makeToast(context, "头像上传失败");
            e.printStackTrace();
        } catch (IOException e) {
            ContentUtil.makeToast(context, "头像上传失败");
            e.printStackTrace();
        }
        ContentUtil.makeToast(context, "头像上传失败");
        return null;
    }


    /**
     * 上传多张图片
     */
    public static List<String> uploadImages(String url, List<File> file, String param,
                                            Context context) {
//		if (!file.exists()) {
//			ContentUtil.makeLog("上传图片","图片不存在");
//			return null;
//		}
        List<String> result = new ArrayList<>();
        if (file.size() == 0) {
            ContentUtil.makeLog("上传图片", "图片不存在");
            return null;
        }
        ContentUtil.makeLog("上传图片", "file大小：" + file);
        for (int i = 0; i < file.size(); i++) {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            FileBody fileBody = new FileBody(file.get(i), "image/jpeg");
            MultipartEntity entity = new MultipartEntity();
            // image 是服务端读取文件的 key
            entity.addPart(param, fileBody);
            post.setEntity(entity);
            try {
                HttpResponse response = client.execute(post);
                int statusCode = response.getStatusLine().getStatusCode();
                String ok = EntityUtils.toString(response.getEntity(), "utf-8");
                if (statusCode == HttpStatus.SC_OK) {
                    ContentUtil.makeLog("上传图片", "成功");
                    result.add(ok);
                }
            } catch (Exception e) {
                ContentUtil.makeToast(context, "头像上传失败");
                e.printStackTrace();
            }
        }
        ContentUtil.makeLog("服务器图片地址", "" + result);
        return result;
    }


    public static HttpEntity getEntity(String uri, List<NameValuePair> params,
                                       int method, ArrayList<String> file,
                                       Context context) throws IOException {
        HttpParams hp = new BasicHttpParams();
        // 设置 user agent
        // String userAgent = MyApplication.appInfoJson;
        // HttpProtocolParams.setUserAgent(hp, userAgent);
        HttpEntity entity = null;
        // 创建客户端对�?
        client.getParams().setParameter(
                CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000);

        // 创建请求对象
        HttpUriRequest request = null;
        switch (method) {
            case METHOD_GET:
                StringBuilder sb = new StringBuilder(uri);
                if (params != null && !params.isEmpty()) {
                    sb.append('?');
                    for (NameValuePair pair : params) {
                        sb.append(pair.getName()).append('=')
                                .append(pair.getValue()).append('&');
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
                ContentUtil.makeLog("GET请求", sb.toString());
                request = new HttpGet(sb.toString());
                break;
            case METHOD_POST:
                request = new HttpPost(uri);
                File newfile;
                if (params != null && !params.isEmpty()) {
                    ContentUtil.makeLog("HTTP URI", "" + uri);
                    ContentUtil.makeLog("HTTP参数", "" + params.toString());
                    // 创建请求实体对象
                    MultipartEntityBuilder builder = MultipartEntityBuilder
                            .create();
                    builder.setCharset(Charset.forName("UTF-8"));// 设置请求的编码格�?
                    ContentType contentType = ContentType.create(
                            HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
                    builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                    String boundary = "-------------" + System.currentTimeMillis();
                    builder.setBoundary(boundary);
                    for (NameValuePair pair : params) {
                        StringBody stringBody = new StringBody(pair.getValue(),
                                contentType);
                        builder.addPart(pair.getName(), stringBody);

                    }

                    for (int i = 0; i < file.size(); i++) {
                        newfile = new File(file.get(i));
                        if (!newfile.exists()) {
                            dialog.dismiss();
                            ContentUtil.makeLog("上传图片", "图片不存在");
                            return null;
                        }

                        // FileBody fileBody = new FileBody(newfile,
                        // ContentType.create("image/jpeg"));
                        // builder.addPart("detailImg", fileBody);
                        builder.addBinaryBody("file[]", newfile);
                        ContentUtil.makeLog("上传图片2", newfile.toString());
                    }

                    HttpEntity entity1 = builder.build();
                    // UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(
                    // params, HTTP.UTF_8);
                    // 设置请求实体
                    ((HttpPost) request).setEntity(entity1);

                }
                break;
        }
        // 执行请求获取相应对象
        HttpResponse response = client.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            entity = response.getEntity();
        } else if (response.getStatusLine().getStatusCode() == 300) {
            ContentUtil.makeToast(context, "登录已过期，请重新登录");
//			Intent intent = new Intent(context, LoginActivity.class);
//			context.startActivity(intent);
//			context.finish();
        }
        return entity;
    }


    public static void cancel() {
        client.getConnectionManager().shutdown();
    }

}
