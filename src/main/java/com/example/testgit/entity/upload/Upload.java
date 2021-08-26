package com.example.testgit.entity.upload;

import javax.persistence.*;
@Entity
@Table(name = "image")
public class Upload {
        @Id
        @GeneratedValue
        private int id;

        @Column(name = "name")
        private String name;

        @Column(name = "photo")
        private String photo;

        @Column(name = "aob")
        private int aob;

        public Upload() {
        }

        public Upload(int id, String name, String photo) {
            this.id = id;
            this.name = name;
            this.photo = photo;
        }

    public Upload(int id, String name, String photo, int aob) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.aob = aob;
    }

    public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

    public int getAob() {
        return aob;
    }

    public void setAob(int aob) {
        this.aob = aob;
    }
}
