package sebasira.com.clase_05_avz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sebas on 10/17/16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "AlarmReceiver";
    private static final int ALARM_NOTIFICATION_ID = 43254;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "Alarma recibida");

        // Creamos la notificacion
        NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(context);
        notiBuilder.setContentTitle("Titulo");              // Titulo de la notificacion
        notiBuilder.setContentText("Contenido");            // Contenido de la notificacion
        notiBuilder.setSmallIcon(R.mipmap.ic_launcher);     // Icono

        /** Text that summarizes this notification for accessibility services. As of the L release,
         *  this text is no longer shown on screen, but it is still useful to accessibility services
         *  (where it serves as an audible announcement of the notification's appearance).
         *  So after Android L, we cannot get tickerText show on the screen. For more information
         *  you can check Android Police article.
         */
        // El ticker es el texto que aparece cuando se recibe la notificacion
        notiBuilder.setTicker("Este es el ticker");         // Texto que aparece cuando se recibe

        // Si le queremos agregar sonido y vibracion (vibrar, requiere permiso)
        notiBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        notiBuilder.setVibrate(new long[]{1000,100});


        // Para iniciar una actividad al clickear la notificacion, debemos pasarle un pendingIntent
        Intent notiIntent = new Intent(context, MainActivity.class);
        PendingIntent notiPendingIntent = PendingIntent.getActivity(context, 0, notiIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notiBuilder.setContentIntent(notiPendingIntent);

        // Y para que la notificacion desaparezca al presionarla, debe ser autoCancelable
        notiBuilder.setAutoCancel(true);

        // Lanzamos la notificacion
        NotificationManager notiMan = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notiMan.notify(ALARM_NOTIFICATION_ID, notiBuilder.build());
    }
}
