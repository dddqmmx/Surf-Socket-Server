package com.dd.surf.pojo;

public class Relation {
    private int id;
    private int userId;
    private int otherSideId;
    private int relationType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOtherSideId() {
        return otherSideId;
    }

    public void setOtherSideId(int otherSideId) {
        this.otherSideId = otherSideId;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }
}
