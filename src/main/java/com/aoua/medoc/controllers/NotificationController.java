package com.aoua.medoc.controllers;


import com.aoua.medoc.models.Notification;
import com.aoua.medoc.models.Rdv;
import com.aoua.medoc.models.Traitement;
import com.aoua.medoc.models.User;
import com.aoua.medoc.repository.NotificationRepository;
import com.aoua.medoc.repository.RdvRepository;
import com.aoua.medoc.repository.TraitementRepository;
import com.aoua.medoc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    private final UserRepository userRepository;
    private final TraitementRepository traitementRepository;
    private final NotificationRepository notificationRepository;
    private final RdvRepository rdvRepository;

    public NotificationController(UserRepository userRepository,
                                  TraitementRepository traitementRepository,
                                  NotificationRepository notificationRepository,
                                  RdvRepository rdvRepository) {
        this.userRepository = userRepository;
        this.traitementRepository = traitementRepository;
        this.notificationRepository = notificationRepository;
        this.rdvRepository = rdvRepository;
    }

    public void envoyermessage(long id_user, long id, boolean traitement){
        User user =userRepository.findById(id_user).get();

        if (traitement){
            Traitement traitement1 = traitementRepository.findById(id).get();
            if (traitement1.getPremiere_prise().equals(LocalTime.now())) {
                Notification notification = new Notification();
                notification.setTitre("Rappel");
                notification.setMessage("Veuillez prendre votre medicament : "+traitement1.getNom_medoc()+"\n"+ traitement1.getNbrePillule()+" comprimes.");
                notification.setUser(user);
                notificationRepository.save(notification);
            }
            if (traitement1.getFois_parjour()>1) {
                if (LocalDate.now().isAfter(traitement1.getDate_fin())) {
                    for (LocalTime i = traitement1.getPremiere_prise();
                         i == LocalTime.of(00, 00, 00);
                         i.plusHours(traitement1.getIntervalle().getHour())) {
                        Notification notification = new Notification();
                        notification.setTitre("Rappel");
                        notification.setMessage("Veuillez prendre votre medicament : "+traitement1.getNom_medoc()+"\n"+ traitement1.getNbrePillule()+" comprimes.\nProchaine prise dans "+traitement1.getIntervalle().getHour()+" heures.");
                        notification.setUser(user);
                        notificationRepository.save(notification);
                    }
                }

            }

        } else {
            Rdv rdv = rdvRepository.findById(id).get();
            if (LocalTime.now().equals(rdv.getDate_rdv().atTime(rdv.getHeure_rdv().minusHours(2)))) {
                Notification notification = new Notification();
                notification.setTitre("Rappel");
                notification.setMessage("Votre rendez-vous est prevu pour  : "+rdv.getHeure_rdv()+"\n"+ rdv.getService_medical() + " \n " +rdv.getMotif());
                notification.setUser(user);
                notificationRepository.save(notification);

            } else if (rdv.getDate_rdv().getDayOfWeek().equals(LocalDate.now().getDayOfWeek())) {
                if (LocalTime.now().equals(rdv.getHeure_rdv())){

                    Notification notification = new Notification();
                    notification.setTitre("Rappel");
                    notification.setMessage("Il est l'heure pour votre RENDEZ-VOUS : " + "\n"+ rdv.getService_medical() + " \n " +rdv.getMotif());
                    notification.setUser(user);
                    notificationRepository.save(notification);

                }
            }
        }


    }
}
