package com.picode.sena.mynotespapbprojectakhir;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;


/**
 * Class ini untuk object 1 reminder yang akan digunakan dalam recycler view reminder
 */
public class ModelReminder {

    private final String CHANNEL_ID = "Notif-ZZ";
    private final String GROUP_KEY_NOTIF_COUNTDOWN = "com.picode.sena.mynotes.COUNTDOWN";

    private Context context;
    private int id;
    private int secondFix;
    private int second;

    public ModelReminder(Context context, int id, int second) {
        this.context = context;
        this.id = id;
        this.second = second;
        this.secondFix = second;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void start(final InterfaceTicker listener) {
        this.secondFix = second;
        CountDownAsync countDownAsync = new CountDownAsync(listener);
        countDownAsync.execute(second);
    }

    private void notif() {
        // Notifikasi
        // Bisa dipelajari di PPT PAPB-9 atau
        // LINK : https://developer.android.com/training/notify-user/build-notification

        // Buat channel untuk notifikasi
        createNotificationChannel();

        // Buat Notifikasi
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Countdown Reminder")
                .setContentText("Selesai dalam " + this.secondFix + " detik")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setColorized(true)
                .setColor(Color.GREEN)
                .setGroup(GROUP_KEY_NOTIF_COUNTDOWN)
                .setGroupSummary(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[]{100, 200, 500, 200, 100});

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(id, mBuilder.build());

    }

    /**
     * Daftarkan channel id ke sistem
     */
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // The id of the group.
            // The user-visible name of the group.
            CharSequence groupName = "Notification";
            NotificationManager mNotificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(GROUP_KEY_NOTIF_COUNTDOWN, groupName));

            CharSequence name = "Thread Notification Channel";
            String description = "Notification Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setGroup(GROUP_KEY_NOTIF_COUNTDOWN);
            mNotificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Class aync menggunakan background thread untuk memproses timer
     */
    class CountDownAsync extends AsyncTask<Object, Integer, Integer> {

        /**
         * Listener ini digunakan untuk jembatan komunikasi antara class CountDownAsync dengan
         * Class AdapterRecyclerView
         */
        private InterfaceTicker listener;

        public CountDownAsync(InterfaceTicker listener) {
            this.listener = listener;
        }

        @Override
        protected Integer doInBackground(Object... objects) {
            int second = (Integer) objects[0];
            try {
                while (true) {
                    Thread.sleep(1000);
                    publishProgress(--second);
                    if (second == 0) {
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            /**
             * Memanggil listener onTicker untuk mengupdate detik saat ini
             * Lihat pada class AdapterRecyclerView, KOMEN 1
             */
            listener.onTicker(values[0]);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            notif();

            /**
             * Memanggil listener onDone untuk memberitahu ke MainThread bahwa proses selesai
             * Lihat pada class AdapterRecyclerView, KOMEN 2
             */

            listener.onDone();
        }
    }
}
