package com.aoua.medoc.controllers;


import com.aoua.medoc.MyHandler;
import com.aoua.medoc.ServiceImplement.FirebaseMessagingService;
import com.aoua.medoc.ServiceImplement.NotificationMessaging;
import com.aoua.medoc.models.*;
import com.aoua.medoc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/notification")

public class NotificationController {

    @Autowired
    FirebaseMessagingService firebaseMessagingService;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  TraitementRepository traitementRepository;
    @Autowired
    private  NotificationRepository notificationRepository;
    @Autowired
    private  RdvRepository rdvRepository;
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private MyHandler myHandler;

    public NotificationController() {
        // Constructeur sans argument
    }

    @PostMapping("/sendnotif")
    public  String sendNotification(@RequestBody NotificationMessaging notification){
        return firebaseMessagingService.sendNotification(notification);
    }
    public NotificationController(UserRepository userRepository,
                                  TraitementRepository traitementRepository,
                                  NotificationRepository notificationRepository,
                                  RdvRepository rdvRepository,
                                  RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.traitementRepository = traitementRepository;
        this.notificationRepository = notificationRepository;
        this.rdvRepository = rdvRepository;
        this.roleRepository = roleRepository;


        //push/////////
        //this.webSocketHandler = webSocketHandler;
    }
//    public void envoyermessage(long id_user, long id, boolean traitement) {
//        User user = userRepository.findById(id_user).get();
//
//        if (traitement) {
//            Traitement traitement1 = traitementRepository.findById(id).get();
//            if (traitement1.getPremiere_prise().equals(LocalTime.now())) {
//                Notification notification = new Notification();
//                notification.setTitre("Rappel");
//                notification.setMessage("Veuillez prendre votre medicament : " + traitement1.getNom_medoc() + "\n" + traitement1.getNbrePillule() + " comprimes.");
//                notification.setUser(user);
//                notificationRepository.save(notification);
//            }
//
//            if (traitement1.getFois_parjour() > 1) {
//                LocalTime nextDoseTime = traitement1.getPremiere_prise();
//                while (nextDoseTime.isBefore(LocalTime.now()) && LocalDate.now().isBefore(traitement1.getDate_fin())) {
//                    nextDoseTime = nextDoseTime.plusHours(traitement1.getIntervalle().getHour());
//                }
//                if (nextDoseTime.equals(LocalTime.now())) {
//                    Notification notification = new Notification();
//                    notification.setTitre("Rappel");
//                    notification.setMessage("Veuillez prendre votre medicament : " + traitement1.getNom_medoc() + "\n" + traitement1.getNbrePillule() + " comprimes.");
//                    notification.setUser(user);
//                    notificationRepository.save(notification);
//                }
//            }
//
//        } else {
//            Rdv rdv = rdvRepository.findById(id).get();
//            LocalDateTime rdvTime = LocalDateTime.of(rdv.getDate(), rdv.getHeure());
//            if (rdvTime.minusHours(1).isBefore(LocalDateTime.now()) && rdvTime.isAfter(LocalDateTime.now())) {
//                Notification notification = new Notification();
//                notification.setTitre("Rappel");
//                notification.setMessage("Votre rendez-vous est prevu pour : " + rdv.getHeure() + "\n" + rdv.getService_medical() + " \n " + rdv.getDate());
//                notification.setUser(user);
//                notificationRepository.save(notification);
//            }
//        }
//    }

    @Scheduled(fixedDelay = 1000)
    public void EnvoyerNotifMedi() throws IOException {

        //recuperation de tout les traitement
        //List<Traitement> allTraitement=traitementRepository.findAll();
        //recuperation des utilisateurs avec le role user
        ///Role roleUser=roleRepository.findByName(ERole.ROLE_USER);
        List<User> userList=userRepository.findAll();

        LocalDate today= LocalDate.now();
        for (User u:userList) {

            List<Traitement> traitementList=traitementRepository.findByUser(u);
            for (Traitement t:traitementList) {
                if ((today.isAfter(t.getDate_debut()) || today.equals(t.getDate_debut())) && (today.isBefore(t.getDate_fin()) || today.equals(t.getDate_fin()))){
                    List<Notification> notificationList=notificationRepository.findByDateAndUserAndTraitement(today,u,t);

                    if(notificationList.size()<t.getFois_parjour()){
                        LocalTime nowTime=LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute());

                        for (long i=1;i<=t.getFois_parjour();i++){

                            LocalTime heureprise=t.getPremiere_prise().plusHours(t.getIntervalle().getHour()*(i-1));

                            if(notificationList.size()==(i-1)){

                                if (nowTime.equals(heureprise)){
                                    Notification notification=new Notification();
                                    notification.setUser(u);
                                    notification.setTitre("titre");
                                    notification.setMessage(" Veuillez prendre votre medicament : "+t.getNom_medoc()+" "+t.getNbrePillule());
                                    notification.setDate(today);
                                    notification.setHeure(heureprise);
                                    notification.setTraitement(t);
                                    notificationRepository.save(notification);
                                    System.out.println("notification genere");

                                    //=============== Ajout de message ===============
                                    myHandler.pushMessage(notification.getMessage());
                                }
                            }

                        }
                    }
                }

                //list de ses traitement

            }

        }
    }



// poooooooooooouuuuuuuuuuurrrrrrrrrrrrr push
  //  private final MyWebSocketHandler webSocketHandler;
    public NotificationController(UserRepository userRepository, TraitementRepository traitementRepository){
        this.userRepository = userRepository;
        this.traitementRepository = traitementRepository;
    //    this.webSocketHandler=webSocketHandler;
    }
   /* @PostMapping("/send-message")
    public void sendMessage(@RequestBody String message) throws IOException {
        webSocketHandler.sendMessageToAllSessions(message);
    }*/
 //////fin de push



    @Scheduled(fixedDelay = 60000)
    public void EnvoyerNotifRdv(){
        List<User> userList=userRepository.findAll();

        LocalDate today= LocalDate.now();

        for (User u:userList) {
            List<Rdv> rdvList=rdvRepository.findByDateAndUser(today,u);

            for (Rdv rdv :rdvList) {
                LocalTime nowTime=LocalTime.of(LocalTime.now().getHour(),LocalTime.now().getMinute());

        //    l'envoi des notif de  rdv

                if (rdv.getHeure().equals(nowTime)){
                    Notification notification=new Notification();
                    notification.setUser(u);
                    notification.setTitre("titre");
                    notification.setMessage(" Votre rendez-vous : "+rdv.getMotif()+" "+rdv.getService_medical()+" "+rdv.getHeure());
                    notification.setDate(today);
                    notification.setHeure(nowTime);
                    notification.setRdv(rdv);
                    System.out.println("rdv genere");

                    notificationRepository.save(notification);
                }
            }
        }

    }


}
//    public void envoyermessage(long id_user, long id, boolean traitement) {
//        User user = userRepository.findById(id_user).get();
//
//        //notif pour la prise de medoc // le cas d'une seule fois par jour
//
//        if (traitement) {
//            Traitement traitement1 = traitementRepository.findById(id).get();
//            if (traitement1.getPremiere_prise().equals(LocalTime.now())) {
//                Notification notification = new Notification();
//                notification.setTitre("Rappel");
//                notification.setMessage("Veuillez prendre votre medicament : " + traitement1.getNom_medoc() + "\n" + traitement1.getNbrePillule() + " comprimes.");
//                notification.setUser(user);
//                notificationRepository.save(notification);
//            }
//
//            //plus d'une fois par jour
//
//            if (traitement1.getFois_parjour() > 1) {
//                if (LocalDate.now().isAfter(traitement1.getDate_fin())) {
//                    for (LocalTime i = traitement1.getPremiere_prise();
//                         i == LocalTime.of(00, 00, 00);
//                         i.plusHours(traitement1.getIntervalle().getHour())) {
//                        Notification notification = new Notification();
//                        notification.setTitre("Rappel");
//                        notification.setMessage("Veuillez prendre votre medicament : " + traitement1.getNom_medoc() + "\n" + traitement1.getNbrePillule() + " comprimes.\nProchaine prise dans " + traitement1.getIntervalle().getHour() + " heures.");
//                        notification.setUser(user);
//                        notificationRepository.save(notification);
//                    }
//                }
//            }
//
//            // notif pour rdv dans une heure
//
//        } else {
//            Rdv rdv = rdvRepository.findById(id).get();
//            if (LocalTime.now().equals(rdv.getDate_rdv().atTime(rdv.getHeure_rdv().minusHours(2)))) {
//                Notification notification = new Notification();
//                notification.setTitre("Rappel");
//                notification.setMessage("Votre rendez-vous est prevu pour  : " + rdv.getHeure_rdv() + "\n" + rdv.getService_medical() + " \n " + rdv.getMotif());
//                notification.setUser(user);
//                notificationRepository.save(notification);
//
//                // notif date exacte
//            } else if (rdv.getDate_rdv().getDayOfWeek().equals(LocalDate.now().getDayOfWeek())) {
//                if (LocalTime.now().equals(rdv.getHeure_rdv())) {
//
//                    Notification notification = new Notification();
//                    notification.setTitre("Rappel");
//                    notification.setMessage("Il est l'heure pour votre RENDEZ-VOUS : " + "\n" + rdv.getService_medical() + " \n " + rdv.getMotif());
//                    notification.setUser(user);
//                    notificationRepository.save(notification);
//                }
//            }
//        }
//    }
//}
