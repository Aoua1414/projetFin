package com.aoua.medoc.ServiceImplement;


import lombok.Data;

import java.util.Map;

@Data
public class NotificationMessaging {

    private String recipientToken;
    private String titre;
    private String body;
    private Map<String,String> data;
}
