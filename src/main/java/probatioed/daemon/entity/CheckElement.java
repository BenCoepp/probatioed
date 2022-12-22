package probatioed.daemon.entity;

import com.jayway.jsonpath.JsonPath;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class CheckElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean check;
    private String title;
    @Lob
    @Column(length=512)
    private String description;
    @Lob
    @Column(length=512)
    private String info;
    private Timestamp timestamp;

    public Long getId() {
        return id;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void print(boolean verbose){
        if(this.check){
            System.out.println("[√] " + this.title + " (" + this.description + ")");
        }else{
            System.out.println("[!] " + this.title + " (" + this.description + ")");
        }
        if(verbose){
            System.out.println(this.info);
        }
    }

    public void fromJson(String json){
        this.title = JsonPath.read(json, "$.doctor.title");
        this.description = JsonPath.read(json, "$.doctor.description");
        this.check = JsonPath.read(json, "$.doctor.check");
        this.info = JsonPath.read(json, "$.doctor.info");
    }
}

