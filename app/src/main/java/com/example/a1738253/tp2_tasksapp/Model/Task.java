package com.example.a1738253.tp2_tasksapp.Model;

import java.util.Date;
import java.util.UUID;


//type spinner ok
//statut seek
//notification radio
//archiver switch

public class Task {

    public  enum Type {ECOLE, TRAVAIL, PERSONNEL, AUTRE}
    public enum Statut {PASSIF, NORMAL, URGENT}
    public enum  Notification {LE_JOUR,UN_JOUR, DEUX_JOUR, TROIS_JOURS, UNE_SEMAINE}

    private UUID mId;
    private String mTitre;
    private String mDescription;
    private Type mType;
    private Date mDate;
    private Statut mStatut;
    private boolean mArchive;
    private Notification mNotification;

    public Task()
    {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public void setStatut(Statut statut) {
        mStatut = statut;
    }

    public void setArchive(boolean archive) {
        mArchive = archive;
    }

    public void setNotification(Notification notification) {
        mNotification = notification;
    }

    public Statut getStatut() {
        return mStatut;

    }

    public boolean isArchive() {
        return mArchive;
    }

    public Notification getNotification() {
        return mNotification;
    }

    public UUID getId() {
        return mId;
    }

    public String getTitre() {
        return mTitre;
    }

    public String getDescription() {
        return mDescription;
    }

    public Type getType() {
        return mType;
    }

    public Date getDate() {
        return mDate;
    }


    public void setId(UUID id) {
        mId = id;
    }

    public void setTitre(String titre) {
        mTitre = titre;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public void setType(Type type) {
        mType = type;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
