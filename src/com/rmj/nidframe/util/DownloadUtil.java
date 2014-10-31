package com.rmj.nidframe.util;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.rmj.nidframe.R;
import com.rmj.nidframe.manager.NidActivityManager;

import java.io.File;

/**
 * Created by G11 on 2014/10/31.
 */
public class DownloadUtil {


    public static int mDownloadCount = 0;

    /**
     * 下载指定文件（带通知栏进度）（需要调整、优化）
     *
     * @param url      文件下载地址
     * @param savePath 本地保存地址
     * @param context
     * @param isUpdate 是否为程序更新apk（是为true，普通文件为false）
     */
    public static void downloadFile(final String url, final String savePath, Context context, final boolean isUpdate) {
        final int _id = mDownloadCount++;
        final NotificationManager _manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder _builder = new NotificationCompat.Builder(context);
        _builder.setContentTitle(StringUtils.getFileNameFromUrl(url));
        _builder.setSmallIcon(R.drawable.ic_launcher);
        _builder.setOngoing(true);

        RequestCallBack<File> _callback = new RequestCallBack<File>() {

            @Override
            public void onFailure(HttpException arg0, String arg1) {
                _builder.setContentText("Download Error").setProgress(0, 0, false);
                _builder.setOngoing(false);
                _manager.notify(_id, _builder.build());
            }

            @Override
            public void onSuccess(ResponseInfo<File> arg0) {
                _builder.setContentText("Download complete").setProgress(0, 0, false);
                _builder.setOngoing(false);
                _manager.notify(_id, _builder.build());
                if (isUpdate) {
                    //软件更新时打开安装程序
                    File _file = new File(savePath);
                    Process p;
                    try {
                        p = Runtime.getRuntime().exec("chmod 755 " + _file);
                        p.waitFor();
                        //安装PendingIntent
                        Uri uri = Uri.fromFile(_file);
                        Intent installIntent = new Intent(Intent.ACTION_VIEW);
                        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        installIntent.setAction(android.content.Intent.ACTION_VIEW);
                        installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                        NidActivityManager.getInstance().currentActivity().startActivity(installIntent);

                        _manager.cancel(_id);
                    } catch (Exception e) {
                        LogUtil.exception(e);
                    }
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                _builder.setProgress(100, (int) (current * 100 / total), false);
                _manager.notify(_id, _builder.build());
            }

            @Override
            public void onStart() {
                super.onStart();
                _builder.setProgress(100, 0, false);
                _manager.notify(_id, _builder.build());
            }
        };

        new HttpUtil(null).httpDownloadFile(url, savePath, _callback);
    }

}
