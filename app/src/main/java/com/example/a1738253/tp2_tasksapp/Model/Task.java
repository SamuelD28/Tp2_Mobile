package com.example.a1738253.tp2_tasksapp.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;

public class Task implements Serializable {

    /** Type enumeration. Contains the type of task that was created.
     *  We intialise the enum type with a int value in order to simplify
     *  the form manipulation.
     */
    public enum Type {
        Ecole(0),
        Travail(1),
        Personnel(2),
        Autre(3);
        private int value; //Property used to store the int value of an enum

        /** Constructor for the type enumeration
         * @param value Value of the enum type
         */
        Type(int value) {
            this.value = value;
        }

        /** Getter to retrieve the value of Type enum
         * @return Return value of type enum
         */
        public int getValue() {
            return value;
        }

        /** Method that return the Type enum corresponding to the int value
         * @param value Int Value to checked for within all the Type enum
         * @return The Type corresponding. Otherwise throws an error
         */
        public static Type valueOf(int value) {
            for (Type l : Type.values()) {
                if (l.getValue() == value) return l;
            }
            throw new IllegalArgumentException("Type not found");
        }
    }


    /** Status Enumeration.
     * Contains information if the task on going, done, missed, etc...
     */
    public enum Statut {
        NonComplete,
        EnCours,
        Complete,
    }


    /** Notification Enumeration.
     * Contains informatino about when to notify the user based on the date they have put in.
     */
    public enum  Notification {
        LeJour,
        JourAvant,
        SemaineAvant,
        Aucune,
    }

    /** Constructor for a task. We pass all the required parameter when instantiating one.
     * @param mTitre Title of task
     * @param mDescription Description of the task
     * @param mType Type of the task
     * @param mDate Date due for the task
     * @param mStatut Status about the task
     * @param mArchive Is the task archived
     * @param mNotification Notificatino about the task
     */
    public Task(String mTitre,
                String mDescription,
                Type mType,
                Calendar mDate,
                Statut mStatut,
                boolean mArchive,
                Notification mNotification)
    {
        this.mId = UUID.randomUUID();
        this.mTitre = mTitre;
        this.mDescription = mDescription;
        this.mType = mType;
        this.mDate = mDate;
        this.mStatut = mStatut;
        this.mArchive = mArchive;
        this.mNotification = mNotification;
    }

    /**Getter and Setter for the class properties**/
    public Calendar getDate() {
        return mDate;
    }

    public void setDate(Calendar mDate) {
        this.mDate = mDate;
    }

    public String getTitre() {
        return mTitre;
    }

    public void setTitre(String mTitre) {
        this.mTitre = mTitre;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public Type getType() {
        return mType;
    }

    public void setType(Type mType) {
        this.mType = mType;
    }

    public Statut getStatut() {
        return mStatut;
    }

    public void setStatut(Statut mStatut) {
        this.mStatut = mStatut;
    }

    public boolean isArchive() {
        return mArchive;
    }

    public void setArchive(boolean mArchive) {
        this.mArchive = mArchive;
    }

    public Notification getNotification() {
        return mNotification;
    }

    public void setNotification(Notification mNotification) {
        this.mNotification = mNotification;
    }

    public UUID getId() {
        return mId;
    }

    /**Properties of the class itself**/
    private UUID mId;
    private String mTitre;
    private String mDescription;
    private Type mType;
    private Calendar mDate;
    private Statut mStatut;
    private boolean mArchive;
    private Notification mNotification;

    public void UpdateTask(Task newTask)
    {
         this.mTitre = newTask.getTitre();
         this.mDescription = newTask.getDescription();
         this.mType = newTask.getType();
         this.mDate = newTask.getDate();
         this.mStatut = newTask.getStatut();
         this.mArchive = newTask.isArchive();
         this.mNotification = newTask.getNotification();
    }

    /** Method used for parsing the model as an intend between Activity
     * @return Return the parsed data
     */
    @Override
    public String toString() {
        return "Task{" +
                "mId='" + mId + '\'' +
                "mTitre='" + mTitre + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mType='" + mType + '\'' +
                ", mDate='" + mDate + '\'' +
                ", mStatut='" + mStatut + '\'' +
                ", mArchive='" + mArchive + '\'' +
                ", mNotification='" + mNotification + '\'' +
                '}';
    }
}
