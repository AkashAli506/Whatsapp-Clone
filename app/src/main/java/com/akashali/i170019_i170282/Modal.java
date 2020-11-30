package com.akashali.i170019_i170282;

public class Modal {
    String id,fullname,lastname,email,dob,gender,phno,bio;

    public Modal() {
    }

    public Modal(String id, String fullname, String lastname, String email, String dob, String gender, String phno, String bio) {
        this.id=id;
        this.fullname = fullname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.phno = phno;
        this.bio = bio;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
