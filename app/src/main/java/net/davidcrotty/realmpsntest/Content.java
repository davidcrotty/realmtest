package net.davidcrotty.realmpsntest;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Content extends RealmObject {
    @PrimaryKey
    @Required
    private String itemId;
    @Required
    private String body;

    public Content() {
    }

    public Content(String itemId, String body) {
        this.itemId = itemId;
        this.body = body;
    }
}
