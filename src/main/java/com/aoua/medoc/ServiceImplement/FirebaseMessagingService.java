package com.aoua.medoc.ServiceImplement;


import com.google.firebase.FirebaseException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseMessagingService {
@Autowired
    FirebaseMessaging firebaseMessaging;
   // FirebaseMessagingService firebaseMessagingService;

    public  String sendNotification(NotificationMessaging notificationMessaging)  {
        Notification notification = Notification.builder()
                .setTitle(notificationMessaging.getTitre())
                .setBody(notificationMessaging.getBody())
                .build();

        Message message = Message.builder()
                .setToken(notificationMessaging.getRecipientToken())
                .setNotification(notification)
                .putAllData(notificationMessaging.getData()).build();


        try{

            firebaseMessaging.send(message);
            return "Success";
        } catch (FirebaseException e){
            e.printStackTrace();
            return "erreur sending";
        }
    }
}
