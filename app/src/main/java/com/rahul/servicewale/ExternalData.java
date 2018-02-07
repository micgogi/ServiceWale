package com.rahul.servicewale;

/**
 * Created by rahul on 7/13/2017.
 */

public class ExternalData {

        String title;
    int des;

   int id;



    int noOfItems;


       public ExternalData(){

       }
        public ExternalData(String title, int des){

            this.des=des;
            this.title=title;


        }

        public ExternalData(int id,String title,int des,int noOfItems){
            this.id = id;
            this.title = title;
            this.des=des;
            this.noOfItems = noOfItems;
        }

    public int getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(int noOfItems) {
        this.noOfItems = noOfItems;
    }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDes() {
            return des;
        }

        public void setDes(int des) {
            this.des = des;
        }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    }


